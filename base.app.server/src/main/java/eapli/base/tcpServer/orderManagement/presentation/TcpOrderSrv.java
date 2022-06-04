package eapli.base.tcpServer.orderManagement.presentation;

import eapli.base.tcpServer.orderManagement.domain.TcpOrderSrvThread;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import java.io.*;
import java.net.*;

public class TcpOrderSrv {
    private static ServerSocket sock;
    private static final Logger LOGGER = LogManager.getLogger(TcpOrderSrvThread.class);

    private static final String TRUSTED_STORE = "base.app.server/src/main/java/eapli/base/tcpServer/orderManagement/presentation/SSL_ORDER/orderServer_J.jks";
    private static final String KEYSTORE_PASS = "forgotten";

    public static void serverRun(int serverSockNum) throws Exception {
        SSLServerSocket sock = null;
        Socket cliSock;

        // Trust these certificates provided by authorized clients
        System.setProperty("javax.net.ssl.trustStore", TRUSTED_STORE);
        System.setProperty("javax.net.ssl.trustStorePassword", KEYSTORE_PASS);

        // Use this certificate and private key as server certificate
        System.setProperty("javax.net.ssl.keyStore", TRUSTED_STORE);
        System.setProperty("javax.net.ssl.keyStorePassword", KEYSTORE_PASS);

        SSLServerSocketFactory sslF = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();

        try {
            sock = (SSLServerSocket) sslF.createServerSocket(serverSockNum);
            sock.setNeedClientAuth(true);

        } catch (IOException ex) {
            LOGGER.error("Failed to open the order server socket");
            System.exit(1);
        }

        while (true) {
            cliSock = sock.accept();
            new Thread(new TcpOrderSrvThread(cliSock)).start();
        }
    }

}