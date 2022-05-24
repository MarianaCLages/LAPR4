package eapli.base.httpsServer.domain;

import eapli.base.httpsServer.HttpChatConsumer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class MessageFetcher extends Thread {
    private Socket TCPconn;
    private InetAddress serverIP;
    private int serverPort;
    private int nextMessage;

    public MessageFetcher(InetAddress ip, int port) { serverIP=ip; serverPort=port; }

    public void run() {
        nextMessage=0;
        while(!HttpChatConsumer.userExit) {
            String message = getNextMessage();
            if(HttpChatConsumer.userExit) return;
            if(message==null) {
                System.out.println("Server not responding ...");
                nextMessage=0;
                try {Thread.sleep(5000); }
                catch(InterruptedException ie) {
                    System.out.println("Thread Interrupted ...");
                }
            }
            else {
                System.out.println(message);
                nextMessage++;
            }
        }
    }


    private String getNextMessage() {
        DataOutputStream sOut;
        DataInputStream sIn;
        try {
            synchronized (serverIP) { TCPconn = new Socket(serverIP, serverPort);}
        }
        catch(IOException ex) {
            synchronized (serverIP) { TCPconn = null;}
            return null;
        }

        HTTPmessage response;
        try {
            sOut = new DataOutputStream(TCPconn.getOutputStream());
            sIn = new DataInputStream(TCPconn.getInputStream());
            HTTPmessage request = new HTTPmessage();
            request.setRequestMethod("GET");
            request.setURI("/messages/" + nextMessage);
            request.send(sOut);
            response = new HTTPmessage(sIn);	// the server may hold the response
            if(!response.getStatus().startsWith("200")) {
                throw new IOException();
            }

        }
        catch(IOException ex) {
            synchronized (serverIP) {
                try { TCPconn.close(); } catch(IOException ex2) { }
                TCPconn = null;
            }
            return null;
        }
        synchronized (serverIP) {
            try { TCPconn.close(); } catch(IOException ex2) { }
            TCPconn = null;
        }
        return(new String(response.getContent()));
    }

    public void abort() { // close the socket to force the thread's exit
        synchronized (serverIP) {
            if(TCPconn==null) return;
            try { TCPconn.close(); } catch(IOException ex2) { }
        }
    }

} // MessageFetcher CLASS
