import eapli.base.Application;


public class EstablishConnectionService {

    public static void main(String[] args) {
        createConnectionWithTheTcpAGVManagementServer((byte) 0);
    }

    public static void createConnectionWithTheTcpAGVManagementServer(byte request) {
        TcpCli.tcpEstablish(Application.settings().getTcpAgvManagerServerDns(), Integer.parseInt(Application.settings().getTcpAgvManagerClientSocketPort()), 2, request);
    }
}