package eapli.base.httpsServer;

import eapli.base.httpsServer.domain.HttpChatRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * @author ANDRE MOREIRA (asc@isep.ipp.pt)
 */
public class HttpServerChat {
    private static final Logger LOGGER = LogManager.getLogger(HttpServerChat.class);

    static private final String BASE_FOLDER = "base.app.server/src/eval/java/eapli/base/httpsServer/www";
    static private ServerSocket sock;
    static private String SERVER_SOCKET = "2228";

    public static void main(String args[]) throws Exception {
        Socket cliSock;

        try {
            sock = new ServerSocket(Integer.parseInt(SERVER_SOCKET));
        } catch (IOException ex) {
            LOGGER.error("Server failed to open local port " + SERVER_SOCKET);
            System.exit(1);
        }
        LOGGER.info("Server ready, listening on port number " + SERVER_SOCKET);
        addMsg("HTTP Chat Server is ready ...");

        while (true) {
            cliSock = sock.accept();
            HttpChatRequest req = new HttpChatRequest(cliSock, BASE_FOLDER);
            req.start();
        }
    }

    // MESSAGES ARE ACCESSED BY THREADS - LOCKING REQUIRED
    private static int nextMsgNum = 0;
    private static final ArrayList<String> MSG_LIST = new ArrayList<>();

    public static String getMsg(int msgNumber) {
        synchronized (MSG_LIST) {
            while (msgNumber >= nextMsgNum) {
                try {
                    MSG_LIST.wait();
                }    // wait for a notification on MSG_LIST's monitor
                // while waiting MSG_LIST's intr lock is released
                catch (InterruptedException ex) {
                    LOGGER.error("Thread error: interrupted");
                    return null;
                }
            }
            return MSG_LIST.get(msgNumber);
        }
    }

    public static void addMsg(String msg) {
        synchronized (MSG_LIST) {
            MSG_LIST.add(nextMsgNum, msg);
            nextMsgNum++;
            MSG_LIST.notifyAll(); // notify all threads waiting on MSG_LIST's monitor
        }
    }


}

