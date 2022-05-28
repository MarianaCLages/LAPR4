package eapli.base.ordermanagement.application;

import eapli.base.Application;
import eapli.base.agvmanagement.dto.AGVDto;
import eapli.base.ordermanagement.dto.OrderDto;
import eapli.base.servers.utils.TcpProtocolParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class AssignOrderToAGVService {
    Logger LOGGER = LogManager.getLogger(AssignOrderToAGVService.class);

    public boolean assignOrderToAGV(OrderDto orderDto, AGVDto agvDto) {
        //connects to the tcp agv server
        try {
            InetAddress ip = InetAddress.getByName(Application.settings().getTcpAgvManagerServerDns());
            int port = Integer.parseInt(Application.settings().getTcpAgvManagerClientSocketPort());

            Socket socket = new Socket(ip, port);

            //sends an test message to the tcp agv server
            byte[] message = {(byte) 0, (byte) 1, (byte) 0, (byte) 0, (byte) 0};
            socket.getOutputStream().write(message);

            //receives the answer from the tcp agv server
            byte[] answer = new byte[5];
            socket.getInputStream().read(answer);

            if (answer[0] == (byte) 0 && answer[1] == (byte) 2 && answer[2] == (byte) 0 && answer[3] == (byte) 0 && answer[4] == (byte) 0) {
                LOGGER.info("Connection established with the tcp agv server");

                //sends the order to the tcp agv server
                message = new byte[]{(byte) 0x0, (byte) 0x07, (byte) 0x0, (byte) 0x0, (byte) 0x0};
                socket.getOutputStream().write(message);
                //waists for the answer from the tcp agv server
                answer = new byte[5];
                socket.getInputStream().read(answer);
                if (answer[1] == (byte) 0x2) {
                    LOGGER.info("Order sent to the tcp agv server");

                    //sends the order
                    byte[] message1 = TcpProtocolParser.createProtocolMessageWithAString(String.valueOf(orderDto.getId()), 0);
                    socket.getOutputStream().write(message1);
                    //waists for the answer from the tcp agv server
                    answer = new byte[5];
                    socket.getInputStream().read(answer);

                    //aknowledges the order
                    if (answer[1] == (byte) 2) {
                        LOGGER.info("Order acknowledged by the tcp agv server");
                        //sends the agv
                        byte[] message2 = TcpProtocolParser.createProtocolMessageWithAString(String.valueOf(agvDto.getId()), 0);

                        socket.getOutputStream().write(message2);
                        //waits for the answer from the tcp agv server
                        answer = new byte[5];
                        socket.getInputStream().read(answer);

                        //acknowledges the agv
                        if (answer[1] == (byte) 2) {
                            LOGGER.info("AGV acknowledged by the tcp agv server");
                            return true;
                        } else {
                            LOGGER.error("AGV not acknowledged by the tcp agv server");
                            return false;
                        }
                    } else {
                        LOGGER.error("Order not acknowledged by the tcp agv server");
                        return false;
                    }
                } else {
                    LOGGER.error("Error sending the order to the tcp agv server");
                    return false;
                }
            } else {
                LOGGER.error("Error connecting with the tcp agv server");
                LOGGER.error("Error code: " + answer[0] + " " + answer[1] + " " + answer[2] + " " + answer[3] + " " + answer[4]);
                return false;
            }

        } catch (IOException e) {
            LOGGER.error("Error connecting with the tcp agv server,exception: {}", e.getMessage());
            return false;
        }
    }

}
