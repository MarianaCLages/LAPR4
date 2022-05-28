package eapli.base.tcpServer.agvManagerManagement.domain;

import eapli.base.servers.utils.TcpProtocolParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Semaphore;

public class AGV_Request_ORDER extends REQUESTS_API_Request {
    Logger LOGGER = LoggerFactory.getLogger(AGV_Request_ORDER.class);

    @Override
    public String execute(Semaphore agvSemaphore, Semaphore orderSemaphore, List<String> agvList, List<String> orderList, DataInputStream sIn, DataOutputStream sOut) throws IOException {
        //sends akc to client
        byte[] akc = new byte[5];
        akc[0] = 0x00;
        akc[1] = 0x02;
        akc[2] = 0x00;
        akc[3] = 0x00;
        akc[4] = 0x00;

        sOut.write(akc);

        //receives the agv id
        byte[] agvId = new byte[10];
        sIn.read(agvId);
        LOGGER.info("Agv id: {}", agvId);
        String agvIdString = agvId(agvId, TcpProtocolParser.lenght(agvId));
        LOGGER.info("Agv id: {}", agvIdString);

        //adds the agv to the list
        agvList.add(agvIdString);
        agvSemaphore.release();

        //logs all the agvs
        LOGGER.info("Agvs: {}", agvList);

        //Acks the agv id
        byte[] ackAgvId = new byte[5];
        ackAgvId[0] = 0x00;
        ackAgvId[1] = 0x02;
        ackAgvId[2] = 0x00;
        ackAgvId[3] = 0x00;
        ackAgvId[4] = 0x00;

        sOut.write(ackAgvId);

        return "";
    }


    //methods that transforms the byte array into a string
    public String agvId(byte[] clientMessage, int lenght) {

        byte[] agvId = Arrays.copyOfRange(clientMessage, 4, lenght + 4);

        return new String(agvId, StandardCharsets.UTF_8);
    }
}
