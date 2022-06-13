package eapli.base.tcpServer.agvManager.presentation;

import eapli.base.tcpServer.agvManager.domain.FifoAGVTwin;
import eapli.base.tcpServer.agvManager.domain.TcpAGVSrvThread;
import eapli.base.tcpServer.orderManagement.domain.TcpOrderSrvThread;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class TcpAgvSrv {

    private static final Logger LOGGER = LogManager.getLogger(TcpAgvSrv.class);
    private static ServerSocket sock;
/*
    private static SSLServerSocket sock = null;
    //private static ServerSocket sock = null;


    private static final String TRUSTED_STORE = "base.app.server/src/main/java/eapli/base/tcpServer/agvManager/domain/TLS_AGV/agvServer_J.jks";
    private static final String KEYSTORE_PASS = "forgotten";
*/
    public static void serverRun(int serverSockNum) throws IOException {
        Socket cliSock;
        Semaphore semOrder = new Semaphore(0);
        Semaphore semAGV = new Semaphore(0);
        List<String> orders = Collections.synchronizedList(new LinkedList<>());
        List<String> agvs = Collections.synchronizedList(new LinkedList<>());
/*
        // Trust these certificates provided by authorized clients
        System.setProperty("javax.net.ssl.trustStore", TRUSTED_STORE);
        System.setProperty("javax.net.ssl.trustStorePassword", KEYSTORE_PASS);

        // Use this certificate and private key as server certificate
        System.setProperty("javax.net.ssl.keyStore", TRUSTED_STORE);
        System.setProperty("javax.net.ssl.keyStorePassword", KEYSTORE_PASS);

        SSLServerSocketFactory sslF = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
*/
        try {
            /*
            sock = (SSLServerSocket) sslF.createServerSocket(serverSockNum);
            sock.setNeedClientAuth(true);*/
            sock = new ServerSocket(serverSockNum);
            LOGGER.info("Server socket created");
            FifoAGVTwin fifoAGVTwin = new FifoAGVTwin(semOrder, semAGV, orders, agvs);
            fifoAGVTwin.start();
        } catch (IOException ex) {
            sock.close();
            LOGGER.error("Failed to open the agv server socket");
            ex.printStackTrace();
            System.exit(1);
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            LOGGER.fatal("Shutdown!");
            try {
                sock.close();
            } catch (IOException e) {
                LOGGER.error("Couldn't close the socket");
            }
        }));

        while (true) {
            cliSock = sock.accept();
            LOGGER.info("New request from " + cliSock.getInetAddress().getHostAddress());
            new Thread(new TcpAGVSrvThread(cliSock, semOrder, semAGV, orders, agvs)).start();
        }

    }

}