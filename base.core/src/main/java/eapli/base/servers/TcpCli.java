package eapli.base.servers;

import eapli.base.servers.orderManagement.domain.TcpOrderCliRequests;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class TcpCli {
    private static final Logger LOGGER = LogManager.getLogger(TcpCli.class);

    static InetAddress serverIP;
    static SSLSocket sock;

    public static List<String> tcpEstablish(String dns, int sockNum, int serverExecutor, byte request) {

        List<String> listStrings = new ArrayList<>();

        // Trust these certificates provided by servers
        System.setProperty("javax.net.ssl.trustStore", "cliente1Executor.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "123abc456def");

        // Use this certificate and private key for client certificate when requested by the server
        System.setProperty("javax.net.ssl.keyStore", "cliente1Executor.jks");
        System.setProperty("javax.net.ssl.keyStorePassword", "123abc456def");

        SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();

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
            sock = (SSLSocket) sf.createSocket(serverIP, sockNum);
        } catch (IOException ex) {
            LOGGER.error("Failed to establish TCP connection");
            return null;
        }

        if (serverExecutor == 1) {
            try {
                sock.startHandshake();
                listStrings = TcpOrderCliRequests.handleRequests(sock, request);
            } catch (IOException e) {
                //FOR TEST PURPOSES ONLY
                e.printStackTrace();
            }
        }

        return listStrings;

    }

}