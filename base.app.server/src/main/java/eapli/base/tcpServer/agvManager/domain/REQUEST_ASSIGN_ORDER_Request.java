package eapli.base.tcpServer.agvManager.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Semaphore;

public class REQUEST_ASSIGN_ORDER_Request extends REQUESTS_API_Request {

    Logger LOGGER = LoggerFactory.getLogger(REQUEST_ASSIGN_ORDER_Request.class);

    @Override
    public String execute(Semaphore agvSemaphore, Semaphore orderSemaphore, List<String> agvList, List<String> orderList, DataInputStream sIn, DataOutputStream sOut) {
        try {
            //sends akc to client
            byte[] akc = new byte[5];
            akc[0] = 0x00;
            akc[1] = 0x02;
            akc[2] = 0x00;
            akc[3] = 0x00;
            akc[4] = 0x00;

            sOut.write(akc);

            //receives order id
            byte[] orderId = new byte[10];
            sIn.read(orderId);
            LOGGER.info("Order id: {}", orderId);

            //Acks order id
            byte[] ackOrderId = new byte[5];
            ackOrderId[0] = 0x00;
            ackOrderId[1] = 0x02;
            ackOrderId[2] = 0x00;
            ackOrderId[3] = 0x00;
            ackOrderId[4] = 0x00;
            sOut.write(ackOrderId);

            //receives agv id
            byte[] agvId = new byte[10];
            sIn.read(agvId);
            LOGGER.info("Agv id: {}", agvId);

            //Acks agv id
            byte[] ackAgvId = new byte[5];
            ackAgvId[0] = 0x00;
            ackAgvId[1] = 0x02;
            ackAgvId[2] = 0x00;
            ackAgvId[3] = 0x00;
            ackAgvId[4] = 0x00;
            sOut.write(ackAgvId);

            //TODO: avisar o digital twin


        } catch (IOException e) {
            LOGGER.error("Error sending AKC to client");
        }
        return "";
    }
}
