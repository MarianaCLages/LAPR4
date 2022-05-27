package eapli.base.httpsServerAjax;

import eapli.base.httpsServer.domain.HTTPmessage;

import java.io.*;
import java.net.*;

/**
 * @author asc@isep.ipp.pt
 */
public class DemoConsumer {
    static private Socket sock;
    static private InetAddress serverIP;
    static private int serverPort;
    static private DataOutputStream sOut;
    static private DataInputStream sIn;

    private static String PORT = "30639";
    private static String DNS = "vs-gate.dei.isep.ipp.pt";

    public static void main(String args[]) throws Exception {

        try {
            serverIP = InetAddress.getByName(DNS);
        } catch (UnknownHostException ex) {
            System.out.println("Invalid SERVER-ADDRESS." + DNS);
            System.exit(1);
        }

        try {
            serverPort = Integer.parseInt(PORT);
        } catch (NumberFormatException ex) {
            System.out.println("Invalid SERVER-PORT." + PORT);
            System.exit(1);
        }

        HTTPmessage request = new HTTPmessage();
        request.setRequestMethod("GET");
        request.setURI("/info");

        try {
            sock = new Socket(serverIP, serverPort);
        } catch (IOException ex) {
            System.out.println("Failed to connect to provided SERVER-ADDRESS and SERVER-PORT.");
            System.out.println("Application aborted.");
            System.exit(1);
        }

        try {
            sOut = new DataOutputStream(sock.getOutputStream());
            sIn = new DataInputStream(sock.getInputStream());
        } catch (IOException ex) {
            System.out.println("Error accessing socket's streams. Aborted.");
            try {
                sock.close();
            } catch (IOException ex2) {
                System.out.println("Error closing socket.");
            }
            System.out.println("Application aborted.");
            System.exit(1);
        }

        System.out.println("Casting a vote");
        request.send(sOut);                // send HTTP request
        HTTPmessage response = new HTTPmessage(sIn);    // receive HTTP response
        System.out.println("HTTP server response status: " + response.getStatus());
        try {
            sock.close();
        } catch (IOException ex2) {
            System.out.println("Error closing socket.");
        }
    }

} // MAIN METHOD
// CLASS
    
