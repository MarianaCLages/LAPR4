package eapli.base.tcpServer.agvManagerManagement.domain;

import eapli.base.agvmanagement.domain.AGV;
import eapli.base.agvmanagement.domain.AGVStatus;
import eapli.base.agvmanagement.repositories.AGVRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.ClientOrder;
import eapli.base.ordermanagement.domain.OrderState;
import eapli.base.ordermanagement.repositories.OrderRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.Collections;
import java.util.List;

public class AssignOrderToAnAGVThread implements Runnable {

    private final AGVRepository agvRepository = PersistenceContext.repositories().agvRepository();
    private final OrderRepository orderRepository = PersistenceContext.repositories().orders();
    //private final AuthorizationService authz = AuthzRegistry.authorizationService();

    @Override
    public void run() {
        List<ClientOrder> clientOrders = orderRepository.findAllToBePreparedOrders();
        List<AGV> agvList = agvRepository.findFreeAGVS();

        if (clientOrders == null) {
            return;
        }
        if (agvList == null) {
            return;
        }


        ClientOrder clientOrder = null;

        for (AGV agv : agvList) {
            if (!clientOrders.isEmpty()) {

                clientOrder = fifo(clientOrders);
                clientOrders.remove(clientOrder);


                clientOrder.chanceState(OrderState.BEING_PREPARED);
                agv.changeStatus(AGVStatus.OCCUPIED);
                agv.changeClientOrder(clientOrder);

                agvRepository.updateAGV(agv);
                orderRepository.updateOrder(clientOrder);


                System.out.println("The AGV " + agv.identity() + " is now working on the order " + clientOrder.identity() + "!\n");
            }
        }


    }

    private ClientOrder fifo(List<ClientOrder> clientOrders) {

        ClientOrder first = null;

        for (ClientOrder c : clientOrders) {

            if(first == null) first = c;

            if(first.orderDate().date().before(c.orderDate().date())){
                first = c;
            }

        }

        return first;
    }
}