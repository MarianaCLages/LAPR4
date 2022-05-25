package eapli.base.agvmanagement.application;

import ch.qos.logback.core.net.server.Client;
import eapli.base.agvmanagement.domain.AGV;
import eapli.base.agvmanagement.domain.AGVStatus;
import eapli.base.agvmanagement.repositories.AGVRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.ClientOrder;
import eapli.base.ordermanagement.domain.OrderState;
import eapli.base.ordermanagement.repositories.OrderRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AssignOrderToAnAGVService {

    private final AGVRepository agvRepository = PersistenceContext.repositories().agvRepository();
    private final OrderRepository orderRepository = PersistenceContext.repositories().orders();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    public boolean assignOrderToAnAGVService() {

        List<ClientOrder> clientOrders = orderRepository.findAllToBePreparedOrders();
        List<AGV> agvList = agvRepository.findFreeAGVS();

        if (clientOrders == null) {
            return false;
        }
        if (agvList == null) {
            return false;
        }


        Collections.sort(clientOrders, Collections.reverseOrder());

        ClientOrder clientOrder = null;

        for (AGV agv : agvList) {
            if (!clientOrders.isEmpty()) {

                clientOrder = clientOrders.get(0);
                clientOrders.remove(0);


                clientOrder.chanceState(OrderState.BEING_PREPARED);
                agv.changeStatus(AGVStatus.OCCUPIED);
                agv.changeClientOrder(clientOrder);

                agvRepository.updateAGV(agv);
                orderRepository.updateOrder(clientOrder);

/*
                if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.WAREHOUSE_EMPLOYEE))
                    System.out.println("The AGV " + agv.identity() + " is now working on the order " + clientOrder.identity() + "!\n");*/
            }
        }

        return true;

    }
}
