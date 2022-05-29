package eapli.base.tcpServer.agvManager.domain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.Semaphore;

public class TcpAGVSrvThread implements Runnable {
    private static final Logger LOGGER = LogManager.getLogger(TcpAGVSrvThread.class);
    private final REQUESTS_API_RequestFactory requestFactory = new REQUESTS_API_RequestFactory();
    List<String> orders;
    List<String> agvs;
    Semaphore semOrder;
    Semaphore semAGV;

    private final Socket clientSocket;

    public TcpAGVSrvThread(Socket cli_socket, Semaphore semOrder, Semaphore semAGV, List<String> orders, List<String> agvs) {
        clientSocket = cli_socket;
        this.semOrder = semOrder;
        this.semAGV = semAGV;
        this.orders = orders;
        this.agvs = agvs;

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

                requestFactory.setRequestType(clientMessage);
                REQUESTS_API_Request request = requestFactory.build();
                if (request == null) {
                    LOGGER.info("Request not recognized");
                    LOGGER.info("Request: {}", clientMessage);

                } else {
                    LOGGER.info("Request recognized");
                    LOGGER.info("Request: {}", request.getClass());
                    LOGGER.info("Request: {}", clientMessage);
                    request.execute(semAGV, semOrder, agvs, orders, sIn, sOut);
                }

                //Espera pela resposta do cliente
                sIn.read(clientMessage, 0, 5);

                if (clientMessage[1] == 1) {
                    closeConnection(sIn, sOut);
                } else {
                    requestInvalid(sIn, sOut, clientMessage);
                }

            } else {
                closeConnection(sIn, sOut);
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