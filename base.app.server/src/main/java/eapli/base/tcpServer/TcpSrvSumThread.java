package eapli.base.tcpServer;

import eapli.base.ordermanagement.application.ViewAllOrdersService;
import eapli.base.ordermanagement.dto.OrderDto;
import org.apache.commons.lang3.SerializationUtils;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

class TcpSrvSumThread implements Runnable {
    private Socket s;
    private DataOutputStream sOut;
    private DataInputStream sIn;
    private ViewAllOrdersService viewAllOrdersService = new ViewAllOrdersService();

    public TcpSrvSumThread(Socket cli_s) {
        s = cli_s;
    }

    public void run() {
        long f, i, num, sum;
        InetAddress clientIP;
        String response;
        boolean flag = true;

        clientIP = s.getInetAddress();
        System.out.println("New client connection from " + clientIP.getHostAddress() +
                ", port number " + s.getPort());

        try {
            sOut = new DataOutputStream(s.getOutputStream());
            sIn = new DataInputStream(s.getInputStream());

            do {
                byte[] bytes = new byte[1];
                sIn.readFully(bytes);

                response = new String(bytes, "UTF-8");

                if (response.equals("Y") || response.equals("y")) {
                    List<OrderDto> list = viewAllOrdersService.viewAllOrders();

                    list.forEach(p -> System.out.println(p));

                    byte[] data = SerializationUtils.serialize((Serializable) list.get(0));

                    sOut.write(data);

                } else {
                    flag = false;
                }

            } while (flag);

            /*do {
                sum = 0;
                do {
                    num = 0;
                    f = 1;
                    for (i = 0; i < 4; i++) {
                        num = num + f * sIn.read();
                        f = f * 256;
                    }
                    sum = sum + num;
                }
                while (num > 0);
                num = sum;
                for (i = 0; i < 4; i++) {
                    sOut.write((byte) (num % 256));
                    num = num / 256;
                }
            }
            while (sum > 0);
            */

            System.out.println("Client " + clientIP.getHostAddress() + ", port number: " + s.getPort() +
                    " disconnected");
            s.close();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

