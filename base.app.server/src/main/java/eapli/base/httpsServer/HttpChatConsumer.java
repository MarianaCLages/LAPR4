package eapli.base.httpsServer;

import eapli.base.httpsServer.domain.HTTPmessage;
import eapli.base.httpsServer.domain.MessageFetcher;

import java.io.*;
import java.net.*; 

public class HttpChatConsumer {
	private static InetAddress serverIP;
	private static int serverPort;
	public static boolean userExit;

	private static String PORT = "30639";
	private static String DNS = "vs-gate.dei.isep.ipp.pt";
	
	public static void main(String args[]) throws Exception {
		String nickName, textLine;

		try { serverIP = InetAddress.getByName(DNS); }
		catch(UnknownHostException ex) {
   			System.out.println("Invalid SERVER-ADDRESS: " + DNS);
    			System.exit(1); }

		try { serverPort = Integer.parseInt(PORT); }
		catch(NumberFormatException ex) {
			System.out.println("Invalid SERVER-PORT-NUMBER: " + PORT);
			System.exit(1);
			}

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		System.out.print("Enter your nickname: ");
		nickName = in.readLine();

		userExit=false;

		System.out.println("Press ENTER to fetch all current messages from the server and start chatting.");
		System.out.println("Once chatting, type exit to quit. NOW PRESS ENTER PLEASE.");
		textLine = in.readLine();
		// start a thread to fetch messages from the HTTP server (GET)
		MessageFetcher msgF  = new MessageFetcher(serverIP, serverPort);
		msgF.start();

		while(!userExit) { // read messages from the console and send them to the HTTP server (POST)
			textLine=in.readLine();
			if(textLine.equals("exit")) {
				userExit=true;
				
			}
			else {
				if(!postMessage("(" + nickName + ") " + textLine)) {
					System.out.println("Sorry, server not responding, message not posted");
					// msgF.abort();
				}
			}
		}
		msgF.abort();
		msgF.join();
	} 

	private static boolean postMessage(String msg) {
		Socket TCPconn;
		DataOutputStream sOut;
		DataInputStream sIn;
		try {
			TCPconn = new Socket(serverIP, serverPort);
		}
		catch(IOException ex) {
			return false;
		}
		
		try {
			sOut = new DataOutputStream(TCPconn.getOutputStream());
			sIn = new DataInputStream(TCPconn.getInputStream());
			HTTPmessage request = new HTTPmessage();
			request.setRequestMethod("POST");
			request.setURI("/messages");
			request.setContentFromString(msg, "text/plain");
			request.send(sOut);
			HTTPmessage response = new HTTPmessage(sIn);
			if(!response.getStatus().startsWith("200")) {
				throw new IOException();
			}

		}
		catch(IOException ex) {
			try { TCPconn.close(); } catch(IOException ex2) { }
			return false;
		}
		try { TCPconn.close(); } catch(IOException ex2) { }
		return true;
	}


} // HttpChatConsumer CLASS