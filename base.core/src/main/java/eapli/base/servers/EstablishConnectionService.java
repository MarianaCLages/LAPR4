package eapli.base.servers;

import eapli.base.Application;
import eapli.framework.application.ApplicationService;

import java.util.List;

@ApplicationService
public class EstablishConnectionService {

    public static void main(String[] args) {
        createConnectionWithTheTcpOrderServerValidD((byte) 0);

    }

    private static void createConnectionWithTheTcpOrderServerValidD(byte request) {
        TcpCli.tcpEstablish(Application.settings().getOrderTcpServerDns(), Integer.parseInt(Application.settings().getOrderTcpClientSocketPort()), 1, request);
    }

    public List<String> createConnectionWithTheTcpOrderServer(byte request) {
        return TcpCli.tcpEstablish(Application.settings().getOrderTcpServerDns(), Integer.parseInt(Application.settings().getOrderTcpClientSocketPort()), 1, request);
    }

    public void createConnectionWithTheTcpOrderServerValid(byte request) {
        TcpCli.tcpEstablish(Application.settings().getOrderTcpServerDns(), Integer.parseInt(Application.settings().getOrderTcpClientSocketPort()), 1, request);
    }

    public static void createConnectionWithTheTcpAGVManagementServer(byte request) {
        TcpCli.tcpEstablish(Application.settings().getTcpAgvManagerServerDns(), Integer.parseInt(Application.settings().getTcpAgvManagerClientSocketPort()), 2, request);
    }

}