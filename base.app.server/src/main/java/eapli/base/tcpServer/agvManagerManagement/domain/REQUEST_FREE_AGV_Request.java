package eapli.base.tcpServer.agvManagerManagement.domain;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.List;
import java.util.concurrent.Semaphore;

public class REQUEST_FREE_AGV_Request extends REQUESTS_API_Request {

    @Override
    public String execute(Semaphore agvSemaphore, Semaphore orderSemaphore, List<String> agvList, List<String> orderList, DataInputStream sIn, DataOutputStream sOut) {
        return null;
    }
}
