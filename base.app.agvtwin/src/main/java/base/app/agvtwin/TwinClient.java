package base.app.agvtwin;

import base.app.agvtwin.domain.AGVTwinThread;
import eapli.base.agvmanagement.domain.AGV;
import eapli.base.agvmanagement.repositories.AGVRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.InetAddress;
import java.net.Socket;

public class TwinClient {
    private static final Logger LOGGER = LogManager.getLogger(eapli.base.servers.TcpCli.class);

    static InetAddress serverIP;
    static Socket sock;

    public static void tcpEstablish(String dns, int sockNum) {
        AGVRepository agvRepository = PersistenceContext.repositories().agvRepository();
        //Mandar um pedido para o servidor -> código: 0 (Teste)
        byte[] clienteMessage = {(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0};


        Iterable<AGV> agvList = agvRepository.findAll();


        for (AGV agv : agvList) {
        Thread t = new Thread(new AGVTwinThread(agv, sockNum, dns));
        t.start();
        }

    }
}
