package eapli.base.tcpServer.agvManagerManagement.domain;

import eapli.base.servers.utils.TcpProtocolParser;
import org.slf4j.Logger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class WARN_SERVER_NEW_ORDER extends REQUESTS_API_Request {
    Logger logger = org.slf4j.LoggerFactory.getLogger(WARN_SERVER_NEW_ORDER.class);

    @Override
    public String execute(Semaphore agvSemaphore, Semaphore orderSemaphore, LinkedList<String> agvList, LinkedList<String> orderList, DataInputStream sIn, DataOutputStream sOut) {
        try {
            //sends an ok message to the client
            byte[] server = {(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
            server[1] = 0x02;
            sOut.write(server);
            sOut.flush();


            byte[] clientMessage = new byte[10];
            sIn.read(clientMessage);

            String orderId = orderId(clientMessage, TcpProtocolParser.lenght(clientMessage));


            orderList.add(orderId);
            orderSemaphore.release();
            logger.info("Client sent a new order: {}", clientMessage);
            logger.info("New order received: {}", orderId);

            //sends an OK message
            clientMessage[0] = 0;
            clientMessage[1] = 0x02;
            clientMessage[2] = 0;
            clientMessage[3] = 0;
            clientMessage[4] = 0;
            clientMessage[5] = 0;

            sOut.write(clientMessage);
            sOut.flush();

            //prints all the orders
            for (String order : orderList) {
                logger.info("Order: {}", order);
            }


            return "OK";
        } catch (IOException e) {
            logger.error("Error reading from client", e);
            return "ERROR";
        }
    }


    //methods that transforms the byte array into a string
    public String orderId(byte[] clientMessage, int lenght) {

        byte[] orderId = Arrays.copyOfRange(clientMessage, 4, lenght + 4);

        return new String(orderId, StandardCharsets.UTF_8);
    }
}
