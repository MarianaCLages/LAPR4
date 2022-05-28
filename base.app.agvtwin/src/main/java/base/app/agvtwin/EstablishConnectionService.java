package base.app.agvtwin;


import eapli.base.Application;

public class EstablishConnectionService {

    public static void main(String[] args) {
        createConnectionWithTheTcpAGVManagementServer();
    }

    public static void createConnectionWithTheTcpAGVManagementServer() {
        TwinClient.tcpEstablish(Application.settings().getTcpAgvManagerServerDns(), Integer.parseInt(Application.settings().getTcpAgvManagerClientSocketPort()));
    }
}