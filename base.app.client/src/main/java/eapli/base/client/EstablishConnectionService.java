package eapli.base.client;

import eapli.base.Application;
import eapli.base.client.TcpCli;
import eapli.framework.application.ApplicationService;

@ApplicationService
public class EstablishConnectionService {

    public static void main(String[] args) {
        createConnectionWithTheTcpOrderServer();
        //createConnectionWithTheTcpAGVManagementServer();
    }

    public static void createConnectionWithTheTcpOrderServer() {
        TcpCli.tcpEstablish(Application.settings().getOrderTcpServerDns(), Integer.parseInt(Application.settings().getOrderTcpClientSocketPort()), 1);
    }

    public static void createConnectionWithTheTcpAGVManagementServer() {
        TcpCli.tcpEstablish(Application.settings().getTcpAgvManagerServerDns(), Integer.parseInt(Application.settings().getTcpAgvManagerClientSocketPort()), 2);
    }

}