package eapli.base.tcpServer.agvManager.domain;

import eapli.base.agvmanagement.domain.AGV;
import eapli.base.agvmanagement.repositories.AGVRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.servers.utils.TcpProtocolParser;
import eapli.base.warehousemanagement.domain.Warehouse;
import eapli.base.warehousemanagement.repositories.WarehouseRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.Semaphore;

public class TcpAGVSrvThread implements Runnable {
    private static final Logger LOGGER = LogManager.getLogger(TcpAGVSrvThread.class);
    private final REQUESTS_API_RequestFactory requestFactory = new REQUESTS_API_RequestFactory();
    private final AGVRepository agvRepository = PersistenceContext.repositories().agvRepository();
    private final WarehouseRepository warehouseRepository = PersistenceContext.repositories().warehouseRepository();
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

            InputStream input = clientSocket.getInputStream();
            OutputStream output = clientSocket.getOutputStream();

            clientIP = clientSocket.getInetAddress();

            LOGGER.info("New client request from {}, port number {}", clientSocket.getPort(), clientIP.getHostAddress());

            byte[] clientMessage = new byte[5];


/*
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(output),true);

            String read;
            read = reader.readLine();
*/
            sIn.readFully(clientMessage);


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
                    System.out.println("Há no total" + serverMessage[1] + " IDs de AGVs para serem enviados");
                    sOut.write(serverMessage);
                    sOut.flush();
                    byte[] protocolMessage = new byte[4];

                    //trash
                    ///sOut.write(protocolMessage);
                    //ENVIAR OS IDS TODOS PARA O CLIENTE
                    for (AGV agv : agvList) {


                        /*
                        String s = "Val"+ agv.identity();

                        byte [] protocolMessage = TcpProtocolParser.createProtocolMessageWithAString(s,0);*/
                        protocolMessage[3] = (byte) Math.toIntExact(agv.identity());
                        sOut.write(protocolMessage);
                        sOut.flush();
                        System.out.println("ID NUMERO:" + protocolMessage[3] + "ENVIADO COM SUCESSO");
                    }

                    //ENVIAR A WAREHOUSE PARA O CLIENTE
                    Warehouse warehouse = warehouseRepository.findWarehouse();


                    String[][] plant = warehouse.generatePlant();
                    StringBuilder plantString = new StringBuilder();
                    //transforms the plant into a string
                   /* for (int i = 0; i < plant.length - 1; i++) {
                        for (int j = 0; j < plant[i].length - 1; j++) {
                            plantString.append(plant[i][j]);

                        }
                        plantString.append("\n");
                    }*/
                    protocolMessage[3] = (byte) (plant.length);
                    System.out.println("Enviando o tamanho da matriz com valor de" + protocolMessage[3]);
                    sOut.write(protocolMessage);
                    sOut.flush();

                    int[][] matrix = new int[plant.length][plant.length];

                    for (int i = 0; i < plant.length ; i++) {
                        for (int j = 0; j < plant[i].length; j++) {
                            if (plant[i][j].contains("D")) {
                                protocolMessage[3] = 2;
                                matrix[i][j] = 2;
                                sOut.write(protocolMessage);
                                sOut.flush();
                            } else if (plant[i][j].contains("R") || plant[i][j].contains("A")) {
                                protocolMessage[3] = 1;
                                matrix[i][j] = 1;
                                sOut.write(protocolMessage);
                                sOut.flush();
                            } else {
                                protocolMessage[3] = 0;
                                matrix[i][j] = 0;
                                sOut.write(protocolMessage);
                                sOut.flush();

                            }
                        }

                    }

                    for (int i = 0; i < plant.length; i++) {
                        for (int j = 0; j < plant[i].length; j++) {
                            System.out.print(matrix[i][j]);

                        }
                        System.out.print("\n");
                    }

                    System.out.println("Plant Length:" + plant.length);

                    //Espera pela resposta do cliente
                    sIn.read(clientMessage, 0, 5);

                    if(clientMessage[1] == 1){
                        closeConnection(sIn,sOut);
                    }


                } else if (clientMessage[1] == 2) {

                    byte[] protocolMessage = new byte[4];
                    String s = new String();
                    //15 Elements.
                    System.out.println("LENGTH:" + agvRepository.findAllAGVS().size());

                    for (int i = 0; i < agvRepository.findAllAGVS().size() - 1; i++) {
                        for (int j = 0; j < 15; j++) {
                            sIn.readFully(protocolMessage);
                            int value = protocolMessage[3] & 0xFF;

                            s = s + value + " ";

                        }
                        String array[] = s.split(" ");
                        System.out.println("\n\n\n<<AGV STATUS INFORMATION>>\nVelocity: (" + array[0] + "," + array[1] + ")" +
                                "Sensors:\nLeft: " + array[2] + "\nRight: " + array[3] + "\nFront: " + array[4] + "\nBack: " + array[5] + "\nFront Left: " + array[6] + "\nFront Right: " + array[7] + "\nBack Right:" + array[8] + "\nBack Left:" + array[9] + "\nCurrent Position x-" + array[10] + " y-" + array[11] + "\nNext Position: x- " + array[12] + " y- " + array[13] + "\nBattery: " + array[14]);
                        s = new String();
                    }

/*
                        sIn.readFully(protocolMessage);
                        System.out.println("FULLY READ THE SIZE");
                        int strLenght = (protocolMessage[2] + protocolMessage[3] * 256);
                        byte[] stringProtocolMessage = new byte[strLenght];
                        System.out.println("PROTOCOL MESSAGE CREATED WITH "+strLenght);
                        sIn.readFully(stringProtocolMessage);


                        System.out.println("Information Received...");
                        String s = TcpProtocolParser.readProtocolMessageIntoString(stringProtocolMessage, strLenght);
                        System.out.println("STRING:" +s);
                        String array[] = s.split(",");
                        System.out.println("<<AGV STATUS>>\nVelocity: (" + array[0] + "," + array[1] + ")\n" +
                                "Sensors:\nLeft: " + array[2] + "\nRight: " + array[3] + "\nFront: " + array[4] + "\nBack: " + array[5] + "\nFront Left: " + array[5] + "\nFront Right: " + array[6] + "\nBack Right: " + array[7] + "\nBack Left: " + array[8] + "\nCurrent Position: x-" + array[9] + " y-" + array[10] + "\nNext Position: x- " + array[11] + " y- " + array[12] + "\nBattery:" + array[13]);

                    }*/
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