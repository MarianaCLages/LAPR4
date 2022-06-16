package eapli.base.servers.agvManagerManagement.domain;

import eapli.base.agvmanagement.repositories.AGVRepository;
import eapli.base.customermanagement.application.VerifyCustomerService;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.servers.orderManagement.domain.TcpOrderCliRequests;
import eapli.base.servers.utils.TcpProtocolParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TcpAgvRequests {


    private static final Logger LOGGER = LogManager.getLogger(TcpAgvRequests.class);
    //private static final AGVRepository agvRepository = PersistenceContext.repositories().agvRepository();
    //private static final VerifyCustomerService verifyCustomerService = new VerifyCustomerService();

    public static List<String> handleRequests(Socket sock, byte request) {


        try {


            List<String> stringList = new ArrayList<>();

            //Mandar um pedido para o servidor -> código: 0 (Teste)
            byte[] clienteMessage = {(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0};

            DataOutputStream sOut = new DataOutputStream(sock.getOutputStream());
            DataInputStream sIn = new DataInputStream(sock.getInputStream());

            sOut.write(clienteMessage);
            sOut.flush();

            //Esperar a resposta do servidor a dizer que entendeu a mensagem
            byte[] serverMessage = new byte[5];
            sIn.read(serverMessage);

            if (serverMessage[1] == 2) {

                request = 1;
                clienteMessage[1] = request;

                sOut.write(clienteMessage);
                sOut.flush();

                byte[] protocolMessage = new byte[4];

                try {
                    if (request == 1) {

                        sIn.readFully(serverMessage);
                        int elementSize = serverMessage[1];
                        int[] ids = new int[elementSize];

                        System.out.println("ELEMENT SIZE:" + elementSize);
                        //Vai enviar os ids
                        for (int i = 0; i < elementSize; i++) {

                            sIn.readFully(protocolMessage);
                            ids[i] = protocolMessage[3] & 0xFF;

                            System.out.println(ids[i]);
                        }

                        //Number of Elements in the matrix
                        sIn.readFully(protocolMessage);

                        int length = protocolMessage[3] & 0xFF;
                        int[][] matrix = new int[length][length];
                        System.out.println("MATRIX:" + matrix.length);

                        for (int i = 0; i < matrix.length - 1; i++) {
                            for (int j = 0; j < matrix[i].length - 1; j++) {
                                //System.out.println("I:" + i + " J:" + j);
                                sIn.readFully(protocolMessage);
                                matrix[i][j] = protocolMessage[3];
                            }
                        }

                        for (int i = 0; i < length - 1; i++) {
                            for (int j = 0; j < length - 1; j++) {
                                System.out.print("|" + matrix[i][j] + "|");

                            }
                            System.out.print("\n");
                        }


                    } else if (request == 2) {


                        int length = 10;
                        byte[] cli = new byte[4];
                        System.out.println("LENGTH:" + length);

                        for (int i = 0; i < length; i++) {
                            String s = "1,2,22,3,6,8,99,1,2,3,6,9,1,22,5";
                            int a = s.length() + 4;
                            cli[2] = (byte) a;

                            sOut.write(cli);

                            byte[] messageToBeSent = TcpProtocolParser.createProtocolMessageWithAString(s, 0);
                            System.out.println(TcpProtocolParser.readProtocolMessageIntoString(messageToBeSent, s.length()));
                            sOut.write(messageToBeSent);
                            sOut.flush();
                        }
                    }

                } catch (Exception e) {
                    LOGGER.error(e.getMessage());
                }

                //Mandar um pedido para o servido -> codigo: 1 (Fim)
                clienteMessage[1] = 1;
                sOut.write(clienteMessage);
                sOut.flush();

                //Wait server reply
                sIn.read(serverMessage, 0, 5);

                serverMessage[1] = 2;
                if (serverMessage[1] == 2) {
                    LOGGER.info("Closing Connection...\n");
                    sock.close();
                    LOGGER.info("Connection Closed successfully\n");

                } else {
                    LOGGER.error("ERROR: Erro no pacote do Servidor");
                }

            } else {
                LOGGER.error("ERROR: Erro no pacote do Servidor");
            }

            return stringList;

        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }


}
