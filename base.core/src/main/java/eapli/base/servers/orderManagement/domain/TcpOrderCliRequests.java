package eapli.base.servers.orderManagement.domain;


import eapli.base.customermanagement.application.VerifyCustomerService;
import eapli.base.servers.utils.TcpProtocolParser;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.UserSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TcpOrderCliRequests {
    private static final Logger LOGGER = LogManager.getLogger(TcpOrderCliRequests.class);
    private static final VerifyCustomerService verifyCustomerService = new VerifyCustomerService();


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

                clienteMessage[1] = request;

                sOut.write(clienteMessage);
                sOut.flush();

                byte[] protocolMessage = new byte[4];

                try {
                    if (request == 1) {

                        //VIEW ALL ORDERS REQUEST

                        sIn.readFully(serverMessage);

                        int elementListSize = serverMessage[1];

                        for (int i = 0; i < elementListSize; i++) {

                            sIn.readFully(protocolMessage);
                            int strLenght = (protocolMessage[2] + protocolMessage[3] * 256);

                            byte[] stringProtocolMessage = new byte[strLenght];
                            sIn.readFully(stringProtocolMessage);

                            System.out.println(TcpProtocolParser.readProtocolMessageIntoString(stringProtocolMessage, strLenght) + "\n");

                        }

                    } else if (request == 2) {

                        //SOMETHING

                    } else if (request == 3) {

                        //ALL ORDERS TO BE PREPARED

                        sIn.readFully(serverMessage);
                        int elementListSize = serverMessage[1];

                        for (int i = 0; i < elementListSize; i++) {

                            sIn.readFully(protocolMessage);

                            int strLenght = TcpProtocolParser.lenght(protocolMessage);

                            byte[] stringProtocolMessage = new byte[strLenght];
                            sIn.readFully(stringProtocolMessage);

                            stringList.add(TcpProtocolParser.readProtocolMessageIntoString(stringProtocolMessage, strLenght) + "\n");

                        }

                    } else if (request == 10) {

                        String email = verifyCustomerService.getCustomerEmail();
                        int stringSize = email.length();

                        protocolMessage[3] = (byte) stringSize;
                        sOut.write(protocolMessage);
                        sOut.flush();

                        protocolMessage = TcpProtocolParser.createProtocolMessageWithAString(email, 0);
                        sOut.write(protocolMessage);
                        sOut.flush();

                        sIn.readFully(serverMessage);
                        int elementListSize = serverMessage[1];

                        for (int i = 0; i < elementListSize; i++) {

                            sIn.readFully(protocolMessage);

                            int strLenght = TcpProtocolParser.lenght(protocolMessage);

                            byte[] stringProtocolMessage = new byte[strLenght];
                            sIn.readFully(stringProtocolMessage);

                            stringList.add(TcpProtocolParser.readProtocolMessageIntoString(stringProtocolMessage, strLenght) + "\n");

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