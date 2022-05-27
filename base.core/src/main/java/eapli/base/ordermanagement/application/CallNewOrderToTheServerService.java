package eapli.base.ordermanagement.application;

import eapli.base.Application;
import eapli.base.ordermanagement.domain.ClientOrder;
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


        Socket sock = new Socket(serverIP, 20);

        DataOutputStream sOut = new DataOutputStream(sock.getOutputStream());
        DataInputStream sIn = new DataInputStream(sock.getInputStream());

        clientMessage[1] = 1;
        sOut.write(clientMessage);
        sOut.flush();

        byte[] serverMessage = new byte[5];
        sIn.read(serverMessage);


        if (serverMessage[1] == 2) {
            serverMessage = TcpProtocolParser.createProtocolMessageWithAString(String.valueOf(clientOrder.intValue()), 0);
            sOut.write(serverMessage);
            sIn.readFully(serverMessage);

            return true;
        } else {
            return false;
        }
    }
}
