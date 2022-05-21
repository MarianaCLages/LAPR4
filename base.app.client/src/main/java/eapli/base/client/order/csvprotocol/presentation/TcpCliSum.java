package eapli.base.client.order.csvprotocol.presentation;

import eapli.base.ordermanagement.dto.OrderDto;
import org.apache.commons.lang3.SerializationUtils;

import java.io.*;
import java.net.*;

public class TcpCliSum {
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

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        DataOutputStream sOut = new DataOutputStream(sock.getOutputStream());
        DataInputStream sIn = new DataInputStream(sock.getInputStream());

        String frase;
        byte[] order = new byte[20];

        try {

            System.out.print("Do you wish to see all orders available in the system? (Options : Y/N)");
            frase = in.readLine();

            if (!frase.equals("Y") || !frase.equals("y") || !frase.equals("n") || !frase.equals("N")) {
                throw new IllegalArgumentException("Please enter a valid option");

            }

            if (frase.equals("Y") || frase.equals("y")) {

                sOut.write(frase.getBytes());

                sIn.readFully(order);

                OrderDto orderDto = SerializationUtils.deserialize(order);

                System.out.println(orderDto);

            }


        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
        
        sock.close();
    }
}