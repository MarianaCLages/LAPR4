package eapli.base.tcpServer.orderManagement.domain;

import eapli.base.customermanagement.application.VerifyCustomerService;
import eapli.base.ordermanagement.application.OrdersIntegrityService;
import eapli.base.ordermanagement.application.ViewAllOrdersService;
import eapli.base.ordermanagement.dto.OrderDto;
import eapli.base.productmanagement.application.SearchProductService;
import eapli.base.productmanagement.dto.ProductDTO;
import eapli.base.servers.utils.TcpProtocolParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Order;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

public class TcpOrderSrvThread implements Runnable {
    private static final Logger LOGGER = LogManager.getLogger(TcpOrderSrvThread.class);

    private final Socket clientSocket;
    private final ViewAllOrdersService viewAllOrdersService = new ViewAllOrdersService();
    private final SearchProductService searchProductService = new SearchProductService();
    private final OrdersIntegrityService ordersIntegrityService = new OrdersIntegrityService();
    private final VerifyCustomerService verifyCustomerService = new VerifyCustomerService();

    public TcpOrderSrvThread(Socket cli_socket) {
        clientSocket = cli_socket;
    }

    public void run() {

        try {
            InetAddress clientIP;

            DataInputStream sIn = new DataInputStream(this.clientSocket.getInputStream());
            DataOutputStream sOut = new DataOutputStream(this.clientSocket.getOutputStream());

            clientIP = clientSocket.getInetAddress();

            LOGGER.info("New client connection from " + clientIP.getHostAddress() + ", port number " + clientSocket.getPort());

            byte[] clienteMessage = new byte[5];
            sIn.read(clienteMessage, 0, 5);

            if (clienteMessage[1] == 0) {

                LOGGER.info("Pedido de Teste do cliente recebido com Sucesso");

                //Dizer ao cliente que entendeu
                LOGGER.info("Mandar mensagem ao cliente a dizer que entendeu");
                byte[] serverMessage = {(byte) 0, (byte) 2, (byte) 0, (byte) 0, (byte) 0};
                sOut.write(serverMessage);
                sOut.flush();

                //Esperar pela resposta do cliente
                sIn.read(clienteMessage, 0, 5);
                LOGGER.info("A ler a request por parte do cliente e processando dados...");

                if (clienteMessage[1] == 1) {

                    List<OrderDto> orderDtoList = viewAllOrdersService.viewAllOrders();

                    //Avisar o cliente quantos dados vão ser lidos
                    serverMessage[1] = (byte) orderDtoList.size();
                    sOut.write(serverMessage);
                    sOut.flush();

                    for (OrderDto dto : orderDtoList) {
                        byte[] protocolMessage = TcpProtocolParser.createProtocolMessageWithAString(dto.toString(), 0);
                        sOut.write(protocolMessage);
                        sOut.flush();

                    }

                } else if (clienteMessage[1] == 0) {

                    LOGGER.info("Sending FILTERED PRODUCTS LIST...");
                    LOGGER.info("Sending changed PRODUCTS LIST...");
                    LOGGER.info("Updating the clients shopping cart...");

                } else if (clienteMessage[1] == 99) {

                    sIn.readFully(clienteMessage);
                    int lenght = TcpProtocolParser.lenght(clienteMessage);

                    byte[] emailB = new byte[lenght + 4];

                    sIn.read(emailB, 0, lenght);

                    String email = TcpProtocolParser.readProtocolMessageIntoString(emailB, lenght);
                    email += "com";

                    LOGGER.info("Sending to the Customer with an email:  " + email + "  his open orders...");
                    LOGGER.info("Success!");

                } else if (clienteMessage[1] == 3) {

                    //ALL PRODUCTS

                    List<ProductDTO> productDTOS = searchProductService.getAll();

                    //Avisar o cliente quantos dados vão ser lidos
                    serverMessage[1] = (byte) productDTOS.size();
                    sOut.write(serverMessage);
                    sOut.flush();

                    for (ProductDTO dto : productDTOS) {
                        byte[] protocolMessage = TcpProtocolParser.createProtocolMessageWithAString(dto.toString(), 0);
                        sOut.write(protocolMessage);
                        sOut.flush();

                    }

                } else if (clienteMessage[1] == 10) {

                    sIn.readFully(clienteMessage);
                    int lenght = TcpProtocolParser.lenght(clienteMessage);

                    byte[] emailB = new byte[lenght + 4];

                    sIn.read(emailB, 0, lenght);

                    String email = TcpProtocolParser.readProtocolMessageIntoString(emailB, lenght);
                    email += "com";

                    List<OrderDto> list = ordersIntegrityService.getAllOrdersFromCustomer(verifyCustomerService.getCustomer(email));

                    //Avisar o cliente quantos dados vão ser lidos
                    serverMessage[1] = (byte) list.size();
                    sOut.write(serverMessage);
                    sOut.flush();

                    for (OrderDto dto : list) {
                        byte[] protocolMessage = TcpProtocolParser.createProtocolMessageWithAString(dto.toString(), 0);
                        sOut.write(protocolMessage);
                        sOut.flush();
                    }


                }

            }

            //Espera pela resposta do cliente
            sIn.read(clienteMessage, 0, 5);

            if (clienteMessage[1] == 1) {
                LOGGER.info("Pedido de Fim do Cliente recebido com Sucesso");
                //Dizer ao cliente que entendeu
                System.out.println("Mandar mensagem ao cliente para fechar socket");
                byte[] serverMessageEnd = {(byte) 0, (byte) 2, (byte) 0, (byte) 0, (byte) 0};
                sOut.write(serverMessageEnd);
                sOut.flush();
                LOGGER.info("Client " + clientIP.getHostAddress() + ", port number: " + this.clientSocket.getPort() + " disconnected");
                clientSocket.close();

            } else {
                LOGGER.error("ERROR: Erro no pacote do Cliente");
                LOGGER.warn("WARN: Verificar ligação TCP");
            }


        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }

    }
}