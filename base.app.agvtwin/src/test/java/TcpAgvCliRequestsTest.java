import domain.AGVTwinThread;
import eapli.base.agvmanagement.domain.AGV;
import eapli.base.agvmanagement.repositories.AGVRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TcpAgvCliRequestsTest {


    AGVRepository agvRepository = PersistenceContext.repositories().agvRepository();

    @Test
    void testAGVThread() {


        int socket = 1;

        List<AGV> agvList = agvRepository.findFreeAGVS();

        int expected = agvList.size();


        for (AGV agv : agvList) {
            new Thread(new AGVTwinThread(agv, socket)).start();
            socket++;
        }

        if (expected == 0) {
            Assertions.assertEquals(expected, socket - 1);
        } else {
            Assertions.assertEquals(expected, socket);
        }

    }

}
