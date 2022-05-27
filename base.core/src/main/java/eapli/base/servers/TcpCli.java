package eapli.base.servers;

//import eapli.base.servers.agvManagerManagement.domain.TcpAgvCliRequests;
//import eapli.base.servers.agvManagerManagement.domain.TcpAgvCliRequests;
import eapli.base.servers.orderManagement.domain.TcpOrderCliRequests;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class TcpCli {
    private static final Logger LOGGER = LogManager.getLogger(TcpCli.class);

    static InetAddress serverIP;
    static Socket sock;

    public static List<String> tcpEstablish(String dns, int sockNum, int serverExecutor, byte request) {

        List<String> listStrings = new ArrayList<>();

        if (dns == null) {
            LOGGER.error("Server IPv4/IPv6 address or DNS name is required as argument");
            return null;
        }

        try {
            serverIP = InetAddress.getByName(dns);
        } catch (UnknownHostException ex) {
            LOGGER.error("Invalid server specified: " + dns);
            return null;
        }

        try {
            sock = new Socket(serverIP, sockNum);
        } catch (IOException ex) {
            LOGGER.error("Failed to establish TCP connection");
            return null;
        }

        if (serverExecutor == 1) {
            listStrings = TcpOrderCliRequests.handleRequests(sock, request);
        }
        /*else if (serverExecutor == 2) {
            TcpAgvCliRequests.handleRequests(sock, request);
        }*/

        return listStrings;

    }

}