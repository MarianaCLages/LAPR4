package eapli.base.servers.agvManagerManagement.domain;

import eapli.base.servers.utils.TcpProtocolParser;
import eapli.base.warehousemanagement.application.binservice.MoveProductToAnotherBinService;
import eapli.framework.io.util.Console;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.Socket;

public class TcpAgvCliRequests {
    private static final Logger LOGGER = LogManager.getLogger(TcpAgvCliRequests.class);
    private MoveProductToAnotherBinService moveProductToAnotherBinService = new MoveProductToAnotherBinService();

    public static void handleRequests(Socket sock,byte request) {

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
                        option = Console.readInteger("\n### Request Menu ###\n\n4: Move Product to another bin:");

                        if (option > 1) {
                            flag = true;

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

                //ObjectOutputStream sOutObject = new ObjectOutputStream(sock.getOutputStream());
                //ObjectInputStream sInObject = new ObjectInputStream(sock.getInputStream());
                byte[] protocolMessage = new byte[4];

                try {
                    if (option == 4) {

                        //A
                        option = Console.readInteger("\nEnter the Product ID\n");

                        clienteMessage[4] = (byte) option;

                        sOut.write(clienteMessage);
                        sOut.flush();

                        sIn.readFully(protocolMessage);

                        int strLenght = (protocolMessage[2] + protocolMessage[3] * 256);

                        byte[] stringProtocolMessage = new byte[strLenght];
                        sIn.readFully(stringProtocolMessage);

                        System.out.println("Product Reconginzed with the ID " + option + "...\n");
                        System.out.println(TcpProtocolParser.readProtocolMessageIntoString(stringProtocolMessage, strLenght) + "\n");

                        int option2 = Console.readInteger("\nEnter the Bin ID");

                        clienteMessage[4] = (byte) option2;
                        sOut.write(clienteMessage);
                        sOut.flush();

                        sIn.readFully(protocolMessage);

                        strLenght = (protocolMessage[2] + protocolMessage[3] * 256);

                        stringProtocolMessage = new byte[strLenght];

                        sIn.readFully(stringProtocolMessage);

                        System.out.println("Bin Recognized with the ID" + option2);
                        System.out.println(TcpProtocolParser.readProtocolMessageIntoString(stringProtocolMessage, strLenght) + "\n");


                        System.out.println("Successfully moved the Product!");

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

                if (serverMessage[1] == 2 || serverMessage[1] == 3) {
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
