package eapli.base.tcpServer.agvManager.domain;

import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.Semaphore;

public class FifoAGVTwin extends Thread {
    private static final Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger(FifoAGVTwin.class);
    List<String> orders;
    List<String> agvs;
    Semaphore semOrder;
    Semaphore semAGV;

    public FifoAGVTwin(Semaphore semOrder, Semaphore semAGV, List<String> orders, List<String> agvs) {
        super();
        this.orders = orders;
        this.agvs = agvs;
        this.semOrder = semOrder;
        this.semAGV = semAGV;
    }

    @Override
    public void run() {
        try {
            LOGGER.info("All agv {}", agvs);

            LOGGER.info("All orders: {}", orders);
            LOGGER.info("Sem: {}", semOrder.availablePermits());
            LOGGER.info("Sem: {}", semAGV.availablePermits());


            LOGGER.info("Waiting for AGV");
            semAGV.acquire();
            LOGGER.info("All orders: {}", orders);
            LOGGER.info("Sem: {}", semOrder.availablePermits());
            LOGGER.info("Sem: {}", semAGV.availablePermits());

            String agv = agvs.remove(0);


            LOGGER.info("Waiting for ORDER");
            semOrder.acquire();
            String order = orders.remove(0);

            LOGGER.info("Order {} assigned to AGV {}", order, agv);

        } catch (InterruptedException e) {

        }

    }


}
