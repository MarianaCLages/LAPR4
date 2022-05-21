package eapli.base.client.order.csvprotocol.application;

import eapli.base.Application;
import eapli.base.client.order.csvprotocol.presentation.TcpCliSum;
import eapli.framework.application.ApplicationService;

@ApplicationService
public class EstablishConnectionService {

        public static void main(String[] args) throws Exception {
            createConnection();
        }

        public static void createConnection() throws Exception {
            TcpCliSum.tcpEstablish(Application.settings().getTcpServerDns(),Integer.parseInt(Application.settings().getTcpClientSocketPort()));
        }

}