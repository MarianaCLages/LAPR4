package eapli.base.tcpServer.agvManagerManagement.presentation;

import eapli.base.tcpServer.agvManagerManagement.domain.FifoAGVTwin;
import eapli.base.tcpServer.agvManagerManagement.domain.TcpAGVSrvThread;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class TcpAgvSrv {
    private static ServerSocket sock;
    private static final Logger LOGGER = LogManager.getLogger(TcpAgvSrv.class);

    public static void serverRun(int serverSockNum) throws IOException {
        Socket cliSock;

        Semaphore semOrder = new Semaphore(0);
        Semaphore semAGV = new Semaphore(0);
        List<String> orders = Collections.synchronizedList(new LinkedList<>());
        List<String> agvs = Collections.synchronizedList(new LinkedList<>());

        try {
            sock = new ServerSocket(serverSockNum);
            LOGGER.info("Server socket created");
            FifoAGVTwin fifoAGVTwin = new FifoAGVTwin(semOrder, semAGV, orders, agvs);
            fifoAGVTwin.start();
        } catch (IOException ex) {
            sock.close();
            LOGGER.error("Failed to open the order server socket");
            ex.printStackTrace();
            System.exit(1);
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            LOGGER.fatal("Shutdown!");
            try {
                sock.close();
            } catch (IOException e) {
                LOGGER.error("Could't close socket");
            }
        }));


        while (true) {
            cliSock = sock.accept();
            LOGGER.info("New request from " + cliSock.getInetAddress().getHostAddress());
            new Thread(new TcpAGVSrvThread(cliSock, semOrder, semAGV, orders, agvs)).start();
        }

    }

}