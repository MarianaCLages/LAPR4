package base.app.agvtwin.domain;

import eapli.base.agvmanagement.domain.AGV;
import eapli.base.servers.utils.TcpProtocolParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class AGVTwinThread extends Thread {
    private static final Logger LOGGER = LogManager.getLogger(AGVTwinThread.class);


    private AGV agv;
    private int sockNum;
    private String dns;

    public AGVTwinThread(AGV agv, int sockNum, String dns) {
        this.agv = agv;
        this.sockNum = sockNum;
        this.dns = dns;
    }

    public void run() {

        byte[] serverMessage;

        try {
            InetAddress serverIP = InetAddress.getByName(dns);
            System.out.println("Connecting to " + dns);
            System.out.println("Connecting to " + sockNum);
            System.out.println("Connecting from " + InetAddress.getLocalHost());
            Socket sock = new Socket(serverIP, sockNum);

            DataOutputStream sOut = new DataOutputStream(sock.getOutputStream());
            DataInputStream sIn = new DataInputStream(sock.getInputStream());


            //Mandar um pedido para o servidor -> código: 0 (Teste)
            byte[] clienteMessage = {(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
            sOut.write(clienteMessage);
            sOut.flush();

            //Receber a resposta do servidor -> código: 2 (confirmação)
            serverMessage = new byte[5];
            sIn.read(serverMessage);
            System.out.println("Received from server: " + message(serverMessage, 5));


            serverMessage[1] = 0x0A;


            System.out.println("I'm ready to receive a new task...");
            sOut.write(serverMessage);
            sOut.flush();

            //espera uma mensagem do servidor
            serverMessage = new byte[5];
            sIn.read(serverMessage);
            System.out.println("Received: " + Arrays.toString(serverMessage));

            //envia o id do agv
            byte[] id = TcpProtocolParser.createProtocolMessageWithAString(agv.identity().toString(), 0);
            sOut.write(id);

            //Esperar a resposta do servidor a dizer que entendeu a mensagem
            clienteMessage = new byte[5];
            sIn.read(clienteMessage);


            LOGGER.info("AGVTwinThread: Received message from server: {}", clienteMessage);


        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public String message(byte[] clientMessage, int lenght) {

        byte[] message = Arrays.copyOfRange(clientMessage, 4, lenght + 4);

        return new String(message, StandardCharsets.UTF_8);
    }

}
