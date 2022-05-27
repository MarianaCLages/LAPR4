package eapli.base.tcpServer.agvManagerManagement.domain;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class ASSIGN_ORDER_Request extends REQUESTS_API_Request {

    @Override
    public String execute(Semaphore agvSemaphore, Semaphore orderSemaphore, LinkedList<String> agvList, LinkedList<String> orderList, DataInputStream sIn, DataOutputStream sOut) {
        return null;
    }
}
