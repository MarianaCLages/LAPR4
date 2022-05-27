package eapli.base.tcpServer.agvManagerManagement.domain;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public abstract class REQUESTS_API_Request {

    public abstract String execute(Semaphore agvSemaphore, Semaphore orderSemaphore, LinkedList<String> agvList, LinkedList<String> orderList, DataInputStream sIn, DataOutputStream sOut);

}
