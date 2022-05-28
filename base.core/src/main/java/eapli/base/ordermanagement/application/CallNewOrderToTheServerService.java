package eapli.base.ordermanagement.application;

import eapli.base.Application;
import eapli.base.servers.utils.TcpProtocolParser;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import static org.hibernate.bytecode.BytecodeLogger.LOGGER;

public class CallNewOrderToTheServerService {

    public boolean callNewOrderToTheServerService(Long clientOrder) throws IOException {

        String dns = Application.settings().getTcpAgvManagerServerDns();

        if (dns == null) {
            LOGGER.error("Server IPv4/IPv6 address or DNS name is required as argument");
            return false;
        }

        InetAddress serverIP;
        byte[] clientMessage = {(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0};

        try {
            serverIP = InetAddress.getByName(dns);
        } catch (UnknownHostException ex) {
            LOGGER.error("Invalid server specified: " + dns);
            return false;
        }


        Socket sock = new Socket(serverIP, Integer.parseInt(Application.settings().getTcpAgvManagerClientSocketPort()));

        DataOutputStream sOut = new DataOutputStream(sock.getOutputStream());
        DataInputStream sIn = new DataInputStream(sock.getInputStream());

        clientMessage[1] = 0;
        sOut.write(clientMessage);
        sOut.flush();

        byte[] serverMessage = new byte[5];
        sIn.read(serverMessage);


        if (serverMessage[1] == 2) {

            //sends the warning about a new order
            clientMessage[1] = 0x0B;
            clientMessage[0] = 0;
            clientMessage[2] = 0;
            clientMessage[3] = 0;
            clientMessage[4] = 0;

            sOut.write(clientMessage);
            sOut.flush();

            //waits for the server to aknowledge the order
            serverMessage = new byte[5];
            sIn.read(serverMessage);

            if (serverMessage[1] == 2) {
                System.out.println(clientOrder);
                System.out.println(clientOrder.intValue());
                System.out.println(clientOrder.toString());
                serverMessage = TcpProtocolParser.createProtocolMessageWithAString(String.valueOf(clientOrder.intValue()), 0);
                sOut.write(serverMessage);
                sIn.readFully(serverMessage);
            }
            return true;
        } else {
            return false;
        }
    }
}
