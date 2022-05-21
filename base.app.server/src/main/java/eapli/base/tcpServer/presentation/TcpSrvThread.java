package eapli.base.tcpServer.presentation;

import eapli.base.ordermanagement.application.ViewAllOrdersService;
import eapli.base.ordermanagement.dto.OrderDto;
import eapli.base.tcpServer.domain.orderManagement.BookingProtocolRequest;
import eapli.base.tcpServer.domain.orderManagement.CsvBookingProtocolMessageParser;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

class TcpSrvThread implements Runnable {
    private static final Logger LOGGER = LogManager.getLogger(TcpSrvThread.class);

    private Socket clientSocket;
    private DataOutputStream sOut;
    private DataInputStream sIn;
    private ViewAllOrdersService viewAllOrdersService = new ViewAllOrdersService();

    public TcpSrvThread(Socket cli_socket) {
        clientSocket = cli_socket;
    }

    public void run() {

        try {
            InetAddress clientIP;

            DataInputStream sIn = new DataInputStream(this.clientSocket.getInputStream());
            DataOutputStream sOut = new DataOutputStream(this.clientSocket.getOutputStream());

            clientIP = clientSocket.getInetAddress();
            System.out.println("New client connection from " + clientIP.getHostAddress() + ", port number " + clientSocket.getPort());

            byte[] clienteMessage = new byte[5];
            sIn.read(clienteMessage, 0, 5);

            if (clienteMessage[1] == 0) {

                System.out.println("==> Pedido de Teste do cliente recebido com Sucesso");

                //Dizer ao cliente que entendeu
                System.out.println("==> Mandar mensagem ao cliente a dizer que entendeu");
                byte[] serverMessage = {(byte) 0, (byte) 2, (byte) 0, (byte) 0, (byte) 0};
                sOut.write(serverMessage);
                sOut.flush();

                //Esperar pela resposta do cliente
                sIn.read(clienteMessage, 0, 5);
                int option = clienteMessage[1];

                ObjectInputStream sInputObject = new ObjectInputStream(this.clientSocket.getInputStream());
                ObjectOutputStream sOutputObject = new ObjectOutputStream(this.clientSocket.getOutputStream());

                if(option == 1) {

                    sOutputObject.writeObject(viewAllOrdersService.viewAllOrders());
                    sOutputObject.flush();

                } else {
                    //NOT YET IMPLEMENTED
                }

            }

            //Espera pela resposta do cliente
            sIn.read(clienteMessage, 0, 5);

            if (clienteMessage[1] == 1) {
                System.out.println("==> Pedido de Fim do Cliente recebido com Sucesso");
                //Dizer ao cliente que entendeu
                System.out.println("==> Mandar mensagem ao cliente para fechar socket");
                byte[] serverMessageEnd = {(byte) 0, (byte) 2, (byte) 0, (byte) 0, (byte) 0};
                sOut.write(serverMessageEnd);
                sOut.flush();
                System.out.println("==> Client " + clientIP.getHostAddress() + ", port number: " + this.clientSocket.getPort() + " disconnected");

            } else {
                System.out.println("==> ERROR: Erro no pacote do Cliente");
            }


        } catch (IOException e) {
            System.out.println("A");
        }

    }
}