package eapli.base.httpsServerAjax;

import eapli.base.httpsServer.domain.HTTPmessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.*;

public class DemoConsumer {
    private static Socket sock;
    private static InetAddress serverIP;
    private static int serverPort;
    private static DataOutputStream sOut;
    private static DataInputStream sIn;

    private final static String PORT = "30639";
    private final static String DNS = "vs-gate.dei.isep.ipp.pt";

    private static final Logger LOGGER = LogManager.getLogger(DemoConsumer.class);

    public static void main(String args[]) throws Exception {

        try {
            serverIP = InetAddress.getByName(DNS);
        } catch (UnknownHostException ex) {
            LOGGER.error("Invalid SERVER-ADDRESS." + DNS);
            System.exit(1);
        }

        try {
            serverPort = Integer.parseInt(PORT);
        } catch (NumberFormatException ex) {
            LOGGER.error("Invalid SERVER-PORT." + PORT);
            System.exit(1);
        }

        HTTPmessage request = new HTTPmessage();
        request.setRequestMethod("GET");
        request.setURI("/info");

        try {
            sock = new Socket(serverIP, serverPort);
        } catch (IOException ex) {
            LOGGER.error("Failed to connect to provided SERVER-ADDRESS and SERVER-PORT.");
            LOGGER.error("Application aborted.");
            System.exit(1);
        }

        try {
            sOut = new DataOutputStream(sock.getOutputStream());
            sIn = new DataInputStream(sock.getInputStream());
        } catch (IOException ex) {
            LOGGER.error("Error accessing socket's streams. Aborted.");
            try {
                sock.close();
            } catch (IOException ex2) {
                LOGGER.error("Error closing socket.");
            }
            LOGGER.warn("Application aborted.");
            System.exit(1);
        }

        request.send(sOut);                // send HTTP request
        HTTPmessage response = new HTTPmessage(sIn);    // receive HTTP response
        LOGGER.info("HTTP server response status: " + response.getStatus());
        try {
            sock.close();
        } catch (IOException ex2) {
            LOGGER.error("Error closing socket.");
        }
    }

}