package eapli.base.tcpServer.agvManager.domain;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.List;
import java.util.concurrent.Semaphore;

public class REQUEST_AGV_POSITION_Request extends REQUESTS_API_Request {

    @Override
    public String execute(Semaphore agvSemaphore, Semaphore orderSemaphore, List<String> agvList, List<String> orderList, DataInputStream sIn, DataOutputStream sOut) {
        return null;
    }
}
