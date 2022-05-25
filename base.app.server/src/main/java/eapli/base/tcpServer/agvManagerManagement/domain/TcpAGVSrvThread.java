package eapli.base.tcpServer.agvManagerManagement.domain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class TcpAGVSrvThread implements Runnable {
    private static final Logger LOGGER = LogManager.getLogger(TcpAGVSrvThread.class);

    private Socket clientSocket;

    public TcpAGVSrvThread(Socket cli_socket) {
        clientSocket = cli_socket;
    }

    public void run() {


        try {
            InetAddress clientIP;

            DataInputStream sIn = new DataInputStream(this.clientSocket.getInputStream());
            DataOutputStream sOut = new DataOutputStream(this.clientSocket.getOutputStream());

            clientIP = clientSocket.getInetAddress();

            LOGGER.info("New client request from {}, port number {}", clientSocket.getPort(), clientIP.getHostAddress());

            byte[] clientMessage = new byte[5];
            sIn.read(clientMessage, 0, 5);

            //metodo para estabelecer a comunicacao com o cliente
            if (connectionMade(sOut, clientMessage)) {
                LOGGER.info("Connection made with {}, port number {} ", clientIP.getHostAddress(), clientSocket.getPort());

                //Esperar pela resposta do cliente
                sIn.read(clientMessage, 0, 5);
                LOGGER.info("A ler a request por parte do cliente e processando dados...");
                //neste momento a client message contem o pedido do cliente
                //processar pedido do cliente
                if (clientMessage[1] == 0x05) {
                    LOGGER.info("Pedido de Posição de AGV");
                    //TODO: implementar pedido de posição de AGV

                    LOGGER.info("Posição de AGV enviada");
                } else if (clientMessage[1] == 0x06) {
                    LOGGER.info("Pedir a um AGV para mudar a posição para um produto");
                    //TODO: implementar pedido de mudar a posição de um produto
                } else if (clientMessage[1] == 0x07) {
                    LOGGER.info("Designar um AGV para uma tarefa");
                    //TODO: implementar designar um AGV para uma tarefa
                } else {
                    requestInvalid(sIn, sOut, clientMessage);
                    return;
                }

               /* if (clientMessage[4] == 4) {

                    sIn.read(clientMessage);
                    int productId = clientMessage[4];
                    Long productLong = (long) productId;

                    byte[] protocolMessage = TcpProtocolParser.createProtocolMessageWithAString(searchProductService.searchProduct(productLong).toString(), 0);
                    sOut.write(protocolMessage);
                    sOut.flush();

                    sIn.read(clientMessage);
                    int binId = clientMessage[4];
                    Long binLong = (long) binId;

                    protocolMessage = TcpProtocolParser.createProtocolMessageWithAString(findBinByIdService.findBinByIdService(binLong).toString(), 0);
                    sOut.write(protocolMessage);
                    sOut.flush();

                }*/

            }

            //Espera pela resposta do cliente
            sIn.read(clientMessage, 0, 5);

            if (clientMessage[1] == 1) {
                closeConnection(sIn, sOut);
            } else {
                requestInvalid(sIn, sOut, clientMessage);
            }

        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    protected void requestInvalid(DataInputStream sIn, DataOutputStream sOut, byte[] clientMessage) throws IOException {
        LOGGER.info("Pedido nao reconhecido");
        //enviar mensagem de erro
        byte[] errorMessage = {0x00, 0x03, 0x00, 0x00};
        sOut.write(errorMessage);
        sOut.flush();
        sIn.read(clientMessage, 0, 5);
        closeConnection(sIn, sOut);
    }

    protected void closeConnection(DataInputStream sIn, DataOutputStream sOut) throws IOException {
        //pedido de fechar a conexao
        LOGGER.info("Pedido de fechar a conexao recebido");
        byte[] disconnectMessage = {0x00, 0x03, 0x00, 0x00};
        sOut.write(disconnectMessage);
        sOut.flush();
        //fechar a conexao
        sIn.close();
        sOut.close();
        clientSocket.close();

        LOGGER.info("Conexao fechada");
    }

    protected boolean connectionMade(DataOutputStream sOut, byte[] clienteMessage) {
        if (clienteMessage[0] == 0) {
            LOGGER.info("Pedido de Teste do cliente recebido com Sucesso");

            //Dizer ao cliente que entendeu
            LOGGER.info("Mandar mensagem ao cliente a dizer que entendeu");
            byte[] serverMessage = {(byte) 0, (byte) 2, (byte) 0, (byte) 0, (byte) 0};
            try {
                sOut.write(serverMessage);
                sOut.flush();
            } catch (IOException e) {
                LOGGER.error("ERROR: Erro ao estabelecer ligação com o cliente");
            }
            return true;
        } else {
            LOGGER.error("Message received from client is not a test message");
            return false;
        }
    }
}