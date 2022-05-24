package eapli.base.client;

import eapli.base.client.agvManagerManagement.domain.TcpAgvCliRequests;
import eapli.base.client.orderManagement.domain.TcpOrderCliRequests;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.*;

public class TcpCli {
    private static final Logger LOGGER = LogManager.getLogger(TcpCli.class);

    static InetAddress serverIP;
    static Socket sock;

    public static void tcpEstablish(String dns, int sockNum, int serverExecutor) {
        if (dns == null) {
            LOGGER.error("Server IPv4/IPv6 address or DNS name is required as argument");
            System.exit(1);
        }

        try {
            serverIP = InetAddress.getByName(dns);
        } catch (UnknownHostException ex) {
            LOGGER.error("Invalid server specified: " + dns);
            System.exit(1);
        }

        try {
            sock = new Socket(serverIP, sockNum);
        } catch (IOException ex) {
            LOGGER.error("Failed to establish TCP connection");
            System.exit(1);
        }

        if (serverExecutor == 1) {
            TcpOrderCliRequests.handleRequests(sock);
        } else if (serverExecutor == 2) {
            TcpAgvCliRequests.handleRequests(sock);
        }

    }

}