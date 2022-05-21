package eapli.base.tcpServer;

import java.io.*;
import java.net.*;

public class TcpSrv {
    static ServerSocket sock;

    public static void serverRun(int serverSockNum) throws Exception {
        Socket cliSock;

        try { sock = new ServerSocket(serverSockNum); }
        catch(IOException ex) {
            System.out.println("Failed to open server socket");
            System.exit(1);
        }

        while(true) {
            cliSock=sock.accept();
            new Thread(new TcpSrvSumThread(cliSock)).start();
        }
    }
}