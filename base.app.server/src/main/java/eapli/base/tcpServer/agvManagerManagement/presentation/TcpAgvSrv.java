package eapli.base.tcpServer.agvManagerManagement.presentation;

import eapli.base.tcpServer.agvManagerManagement.domain.AssignOrderToAnAGVThread;
import eapli.base.tcpServer.agvManagerManagement.domain.TcpAGVSrvThread;
import eapli.base.tcpServer.orderManagement.domain.TcpOrderSrvThread;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.*;

public class TcpAgvSrv {


    private static ServerSocket sock;
    private static final Logger LOGGER = LogManager.getLogger(TcpAgvSrv.class);

    public static void serverRun(int serverSockNum) throws IOException {
        Socket cliSock;

        try {
            sock = new ServerSocket(serverSockNum);
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
            new Thread(new TcpAGVSrvThread(cliSock)).start();
            new Thread(new AssignOrderToAnAGVThread()).start();
        }

    }

}