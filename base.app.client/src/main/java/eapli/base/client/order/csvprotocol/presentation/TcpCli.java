package eapli.base.client.order.csvprotocol.presentation;

import eapli.base.tcpServer.presentation.TcpProtocolParser;
import eapli.base.warehousemanagement.application.binservice.MoveProductToAnotherBinService;
import eapli.framework.io.util.Console;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.*;

public class TcpCli {
    private static final Logger LOGGER = LogManager.getLogger(TcpCli.class);

    static InetAddress serverIP;
    static Socket sock;


    static MoveProductToAnotherBinService moveProductToAnotherBinService = new MoveProductToAnotherBinService();
    public static void tcpEstablish(String dns, int sockNum) throws Exception {
        if (dns == null) {
            System.out.println("Server IPv4/IPv6 address or DNS name is required as argument");
            System.exit(1);
        }

        try {
            serverIP = InetAddress.getByName(dns);
        } catch (UnknownHostException ex) {
            System.out.println("Invalid server specified: " + dns);
            System.exit(1);
        }

        try {
            sock = new Socket(serverIP, sockNum);
        } catch (IOException ex) {
            System.out.println("Failed to establish TCP connection");
            System.exit(1);
        }

        cliWriteOption();

    }

    private static void cliWriteOption() {
        try {
            //Mandar um pedido para o servidor -> código: 0 (Teste)
            byte[] clienteMessage = {(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0};

            DataOutputStream sOut = new DataOutputStream(sock.getOutputStream());
            DataInputStream sIn = new DataInputStream(sock.getInputStream());

            sOut.write(clienteMessage);
            sOut.flush();

            //Esperar a resposta do servidor a dizer que entendeu a mensagem
            byte[] serverMessage = new byte[5];
            sIn.read(serverMessage);

            if (serverMessage[1] == 2) {

                boolean flag = true;
                int option = 0;

                do {
                    try {
                        option = Console.readInteger("\n### Request Menu ###\n\n1: View All Orders:\n2: View an order (inputting the order ID):\n3: Get all orders to be prepared:\n4: Move Product to another bin:");

                        if (option > 4) {
                            flag = true;
                            throw new IllegalArgumentException("\nPlease enter a valid option!");
                        } else if (option < 0) {
                            flag = true;
                            throw new IllegalArgumentException("\nPlease don't a enter negative values");
                        }

                        flag = false;

                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Please enter a number! Don't enter random characters!");
                    }
                } while (flag);

                clienteMessage[4] = (byte) option;

                sOut.write(clienteMessage);
                sOut.flush();

                ObjectOutputStream sOutObject = new ObjectOutputStream(sock.getOutputStream());
                ObjectInputStream sInObject = new ObjectInputStream(sock.getInputStream());
                byte[] protocolMessage = new byte[4];

                try {
                    if (option == 1) {

                        sIn.readFully(serverMessage);

                        int elementListSize = serverMessage[4];

                        System.out.println("\n\n### Order list: ###\n");

                        for (int i = 0; i < elementListSize; i++) {

                            sIn.readFully(protocolMessage);
                            int strLenght = (protocolMessage[2] + protocolMessage[3] * 256);

                            byte[] stringProtocolMessage = new byte[strLenght];
                            sIn.readFully(stringProtocolMessage);

                            System.out.println(TcpProtocolParser.readProtocolMessageIntoString(stringProtocolMessage, strLenght) + "\n");

                        }

                    } else if (option == 2) {
                        option = Console.readInteger("\nEnter the order ID\n");

                        clienteMessage[4] = (byte) option;

                        sOut.write(clienteMessage);
                        sOut.flush();

                        sIn.readFully(protocolMessage);

                        int strLenght = (protocolMessage[2] + protocolMessage[3] * 256);

                        byte[] stringProtocolMessage = new byte[strLenght];
                        sIn.readFully(stringProtocolMessage);

                        System.out.println(TcpProtocolParser.readProtocolMessageIntoString(stringProtocolMessage, strLenght) + "\n");


                    } else if (option == 3) {

                        sIn.readFully(serverMessage);
                        int elementListSize = serverMessage[4];

                        System.out.println("\n\n### Order to prepared list: ###\n");

                        for (int i = 0; i < elementListSize; i++) {

                            sIn.readFully(protocolMessage);

                            int strLenght = (protocolMessage[2] + protocolMessage[3] * 256);

                            byte[] stringProtocolMessage = new byte[strLenght];
                            sIn.readFully(stringProtocolMessage);

                            System.out.println(TcpProtocolParser.readProtocolMessageIntoString(stringProtocolMessage, strLenght) + "\n");

                        }

                    }else if(option == 4){

                        option = Console.readInteger("\nEnter the Product ID\n");


                        clienteMessage[4] = (byte) option;

                        sOut.write(clienteMessage);
                        sOut.flush();

                        sIn.readFully(protocolMessage);

                        int strLenght = (protocolMessage[2] + protocolMessage[3] * 256);

                        byte[] stringProtocolMessage = new byte[strLenght];
                        sIn.readFully(stringProtocolMessage);

                        System.out.println("Product Reconginzed with the ID "+option+"...\n");
                        System.out.println(TcpProtocolParser.readProtocolMessageIntoString(stringProtocolMessage, strLenght) + "\n");

                        int option2 = Console.readInteger("\nEnter the Bin ID");

                        clienteMessage[4] = (byte) option2;
                        sOut.write(clienteMessage);
                        sOut.flush();

                        sIn.readFully(protocolMessage);

                        strLenght = (protocolMessage[2] + protocolMessage[3] * 256);

                        stringProtocolMessage = new byte[strLenght];

                        sIn.readFully(stringProtocolMessage);

                        System.out.println("Bin Recognized with the ID"+option2);
                        System.out.println(TcpProtocolParser.readProtocolMessageIntoString(stringProtocolMessage,strLenght)+ "\n");

                        OrderTaker orderTaker = new OrderTaker(option,option2);

                        Thread takersThread = new Thread(orderTaker,"Taker- 0");
                        //moveProductToAnotherBinService.moveProductToAnotherBinService(option2,option);
                        takersThread.start();
                        System.out.println("Sucessfully moved the Product!");


                    }

                } catch (Exception e) {
                    LOGGER.error(e.getMessage());
                }

                //Mandar um pedido para o servido -> codigo: 1 (Fim)
                clienteMessage[1] = 1;
                sOut.write(clienteMessage);
                sOut.flush();

                //Wait server reply
                sIn.read(serverMessage, 0, 5);

                if (serverMessage[1] == 2) {
                    LOGGER.info("Closing Connection...");
                    sock.close();
                    LOGGER.info("Connection Closed successfully");

                } else {
                    LOGGER.error("ERROR: Erro no pacote do Servidor");
                }

            } else {
                LOGGER.error("ERROR: Erro no pacote do Servidor");
            }

        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }


    }

}