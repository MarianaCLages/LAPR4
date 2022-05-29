package eapli.base.infrastructure.smoketests;

import eapli.base.httpsServer.domain.HTTPmessage;
import eapli.framework.actions.Action;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

public class AccessDashBoardSmokTest implements Action {
    private static Socket sock;
    private static InetAddress serverIP;
    private static int serverPort;
    private static DataOutputStream sOut;
    private static DataInputStream sIn;

    private final static String PORT = "30639";
    private final static String DNS = "vs-gate.dei.isep.ipp.pt";


    private static final Logger LOGGER = LoggerFactory.getLogger(AccessDashBoardSmokTest.class);

    @Override
    public boolean execute() {

        try {
            serverIP = InetAddress.getByName(DNS);
        } catch (UnknownHostException ex) {
            LOGGER.error("Invalid SERVER-ADDRESS." + DNS);
            return false;
        }

        try {
            serverPort = Integer.parseInt(PORT);
        } catch (NumberFormatException ex) {
            LOGGER.error("Invalid SERVER-PORT." + PORT);
            return false;
        }

        HTTPmessage request = new HTTPmessage();
        request.setRequestMethod("GET");
        request.setURI("/info");


        try {
            sock = new Socket(serverIP, serverPort);
        } catch (IOException ex) {
            LOGGER.error("Failed to connect to provided SERVER-ADDRESS and SERVER-PORT.");
            LOGGER.error("Application aborted.");
            return false;
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
                return false;
            }
            LOGGER.warn("Application aborted.");
            return false;
        }

        try {
            request.send(sOut);                // send HTTP request

            HTTPmessage response = new HTTPmessage(sIn);    // receive HTTP response
            LOGGER.info("HTTP server response status: " + response.getStatus());
        } catch (IOException e) {
            return false;
        }

        try {
            sock.close();
        } catch (IOException ex2) {
            LOGGER.error("Error closing socket.");
            return false;
        }

        return true;
    }
}
