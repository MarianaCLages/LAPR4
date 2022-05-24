package eapli.base.client.order.csvprotocol.application;

import eapli.base.Application;
import eapli.base.client.TcpCli;
import eapli.framework.application.ApplicationService;

@ApplicationService
public class EstablishConnectionService {

    public static void main(String[] args) throws Exception {
        createConnectionWithTheTcpOrderServer();
    }

    public static void createConnectionWithTheTcpOrderServer() throws Exception {
        TcpCli.tcpEstablish(Application.settings().getOrderTcpServerDns(), Integer.parseInt(Application.settings().getOrderTcpClientSocketPort()), Integer.parseInt(Application.settings().getTcpServerExecutor1()));
    }

    public static void createConnectionWithTheTcpAGVManagementServer() throws Exception {
        TcpCli.tcpEstablish(Application.settings().getOrderTcpServerDns(), Integer.parseInt(Application.settings().getOrderTcpClientSocketPort()), Integer.parseInt(Application.settings().getTcpServerExecutor2()));
    }

}