package domain;

import eapli.base.Application;
import eapli.base.agvmanagement.domain.AGV;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class AGVTwinThread extends Thread {


    private AGV agv;
    private int sockNum;

    public AGVTwinThread(AGV agv,int sockNum){
        this.agv = agv;
        this.sockNum = sockNum;
    }

    public void run(){

        byte[] serverMessage = {(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0};

        String dns = Application.settings().getTcpAgvManagerServerDns();

        try {
            InetAddress serverIP = InetAddress.getByName(dns);
            Socket sock = new Socket(serverIP, sockNum);

            DataOutputStream sOut = new DataOutputStream(sock.getOutputStream());
            DataInputStream sIn = new DataInputStream(sock.getInputStream());

            serverMessage[1] = 0x0A;
            System.out.println("I'm ready to receive a new task...");
            sIn.read(serverMessage);


        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
