package eapli.base.tcpServer.agvManagerManagement.domain;

import eapli.base.ordermanagement.application.ViewAllOrdersService;
import eapli.base.ordermanagement.dto.OrderDto;
import eapli.base.productmanagement.application.SearchProductService;
import eapli.base.tcpServer.utils.TcpProtocolParser;
import eapli.base.warehousemanagement.application.binservice.FindBinByIdService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

public class TcpAGVSrvThread implements Runnable {
    private static final Logger LOGGER = LogManager.getLogger(TcpAGVSrvThread.class);

    private Socket clientSocket;
    private DataOutputStream sOut;
    private DataInputStream sIn;
    private ViewAllOrdersService viewAllOrdersService = new ViewAllOrdersService();
    private SearchProductService searchProductService = new SearchProductService();
    private FindBinByIdService findBinByIdService = new FindBinByIdService();

    public TcpAGVSrvThread(Socket cli_socket) {
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

                ObjectInputStream sInputObject = new ObjectInputStream(this.clientSocket.getInputStream());
                ObjectOutputStream sOutputObject = new ObjectOutputStream(this.clientSocket.getOutputStream());

                if (clienteMessage[4] == 4) {

                    sIn.read(clienteMessage);
                    int productId = clienteMessage[4];
                    Long productLong = Long.valueOf(productId);

                    byte[] protocolMessage = TcpProtocolParser.createProtocolMessageWithAString(searchProductService.searchProduct(productLong).toString(), 0);
                    sOut.write(protocolMessage);
                    sOut.flush();

                    sIn.read(clienteMessage);
                    int binId = clienteMessage[4];
                    Long binLong = Long.valueOf(binId);

                    protocolMessage = TcpProtocolParser.createProtocolMessageWithAString(findBinByIdService.findBinByIdService(binLong).toString(), 0);
                    sOut.write(protocolMessage);
                    sOut.flush();

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