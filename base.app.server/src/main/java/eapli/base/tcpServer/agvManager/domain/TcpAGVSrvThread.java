package eapli.base.tcpServer.agvManager.domain;

import eapli.base.agvmanagement.domain.AGV;
import eapli.base.agvmanagement.repositories.AGVRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.servers.utils.TcpProtocolParser;
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
    private final AGVRepository agvRepository = PersistenceContext.repositories().agvRepository();
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

            if (clientMessage[1] == 0) {

                LOGGER.info("Pedido de Teste do cliente recebido com Sucesso");

                //Dizer ao cliente que entendeu
                LOGGER.info("Mandar mensagem ao cliente a dizer que entendeu");
                byte[] serverMessage = {(byte) 0, (byte) 2, (byte) 0, (byte) 0, (byte) 0};
                sOut.write(serverMessage);
                sOut.flush();

                //Esperar pela resposta do cliente
                sIn.read(clientMessage, 0, 5);
                LOGGER.info("A ler a request por parte do cliente e processando dados...");
                System.out.println("Client Message= " + clientMessage[1]);

                if (clientMessage[1] == 1) {

                    List<AGV> agvList = agvRepository.findAllAGVS();
                    serverMessage[1] = (byte) agvList.size();
                    sOut.write(serverMessage);
                    sOut.flush();

                    for (AGV agv : agvList) {

                        byte[] protocolMessage = new byte[4];
                        /*
                        String s = "Val"+ agv.identity();

                        byte [] protocolMessage = TcpProtocolParser.createProtocolMessageWithAString(s,0);*/
                        protocolMessage[3] = (byte) Math.toIntExact(agv.identity());
                        sOut.write(protocolMessage);
                        sOut.flush();
                    }

                } else if (clientMessage[1] == 2) {

                    for (AGV agv : agvRepository.findAllAGVS()) {
                        byte[] protocolMessage = new byte[4];

                        sIn.readFully(protocolMessage);

                        int strLenght = (protocolMessage[2] + protocolMessage[3] * 256);
                        byte[] stringProtocolMessage = new byte[strLenght];
                        sIn.readFully(stringProtocolMessage);

                        //The Message had to be divided in 2 parts.

                        System.out.println("Information Received...");
                        String s = TcpProtocolParser.readProtocolMessageIntoString(stringProtocolMessage, strLenght);

                        String array[] = s.split(",");
                        System.out.println("<<AGV STATUS>>\nVelocity: (" + array[0] + "," + array[1] + ")\n" +
                                "Sensors:\nLeft: " + array[2] + "\nRight: " + array[3] + "\nFront: " + array[4] + "\nBack: " + array[5] + "\nFront Left: " + array[5] + "\nFront Right: " + array[6] + "\nBack Right: " + array[7] + "\nBack Left: " + array[8] + "\nCurrent Position: x-" + array[9] + " y-" + array[10] + "\nNext Position: x- " + array[11] + " y- " + array[12] + "\nBattery:" + array[13]);

                    }
                }
                //Espera pela resposta do cliente
                sIn.read(clientMessage, 0, 5);

            }
            if (clientMessage[1] == 1) {
                closeConnection(sIn, sOut);
            }

            //metodo para estabelecer a comunicacao com o cliente
            else if (connectionMade(sOut, clientMessage)) {
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
        byte[] disconnectMessage = {0x00, 0x02, 0x00, 0x00};
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