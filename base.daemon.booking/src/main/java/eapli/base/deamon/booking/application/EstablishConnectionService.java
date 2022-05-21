package eapli.base.deamon.booking.application;

import eapli.base.Application;
import eapli.base.deamon.booking.presentation.TcpCliSum;
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