package base.app.agvtwin;

import base.app.agvtwin.domain.AGVTwinThread;
import eapli.base.Application;
import eapli.base.agvmanagement.domain.AGV;
import eapli.base.agvmanagement.repositories.AGVRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.InetAddress;
import java.net.Socket;

public class TwinClient {
    private static final Logger LOGGER = LogManager.getLogger(TwinClient.class);

    static InetAddress serverIP;
    static Socket sock;

    public static void tcpEstablish(String dns, int sockNum) {
        AGVRepository agvRepository = PersistenceContext.repositories().agvRepository();

        Iterable<AGV> agvList = agvRepository.findAll();
        int serverPort = Integer.parseInt(Application.settings().getTcpTwinServerSocketPort());

        for (AGV agv : agvList) {
            Thread t = new AGVTwinThread(agv, sockNum, dns, serverPort);
            t.start();
            serverPort--;
        }

    }
}
