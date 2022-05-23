package eapli.base.client.order.csvprotocol.presentation;

import eapli.base.ordermanagement.dto.OrderDto;
import eapli.base.tcpServer.presentation.TcpProtocolParser;
import eapli.framework.io.util.Console;
import org.apache.commons.io.IOExceptionList;
import org.apache.commons.lang3.SerializationUtils;

import java.io.*;
import java.net.*;
import java.util.List;

public class TcpCli {
    static InetAddress serverIP;
    static Socket sock;

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

    private static void cliWriteOption() throws IOException {
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
                        option = Console.readInteger("1: View All Orders:\n2: View an order (inputting the order ID):");

                        if (option > 2) {
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

                try {
                    if (option == 1) {

                        System.out.println("A");

                        /*

                        List<OrderDto> list = (List<OrderDto>) sInObject.readObject();

                        System.out.println("A");

                        for (OrderDto dto : list) {
                            System.out.println(dto);

                        }

                        */

                        byte[] protocolMessage = new byte[4];
                        sIn.readFully(protocolMessage);

                        int strLenght = (protocolMessage[2] + protocolMessage[3] * 256);

                        byte[] stringProtocolMessage = new byte[strLenght];
                        sIn.readFully(stringProtocolMessage);

                        TcpProtocolParser.readProtocolMessageIntoString(stringProtocolMessage, strLenght);

                        // 2 - 3

                    } else {
                        System.out.println("Not yet implemented!");

                    }

                } catch (Exception e) {

                }

                //Mandar um pedido para o servido -> codigo: 1 (Fim)
                clienteMessage[1] = 1;
                sOut.write(clienteMessage);
                sOut.flush();

                //Wait server reply
                sIn.read(serverMessage, 0, 5);

                if (serverMessage[1] == 2) {
                    sock.close();

                } else {
                    System.out.println("--> ERROR: Erro no pacote do Servidor");
                }

            } else {
                System.out.println("--> ERROR: Erro no pacote do Servidor");
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }


    }

}