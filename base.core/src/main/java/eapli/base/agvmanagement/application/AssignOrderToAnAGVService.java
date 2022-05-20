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

        Queue<ClientOrder> clientOrderQueue = orderByFIFO(clientOrders);
        ClientOrder clientOrder = null;

        for (AGV agv : agvList){
            if(!clientOrderQueue.isEmpty()){

                clientOrder = clientOrderQueue.poll();

                agvRepository.remove(agv);
                orderRepository.remove(clientOrder);

                clientOrder.chanceState(OrderState.BEING_PREPARED);
                agv.changeStatus(AGVStatus.OCCUPIED);

                agvRepository.save(agv);
                orderRepository.save(clientOrder);
                agv.changeClientOrder(clientOrder);

                if(authz.isAuthenticatedUserAuthorizedTo(BaseRoles.WAREHOUSE_EMPLOYEE))
                System.out.println("The AGV "+agv.identity()+" is now working on the order "+clientOrder.identity()+"!\n");
            }
        }

        return true;

    }


    private Queue<ClientOrder> orderByFIFO(List<ClientOrder> clientOrders) {

        Queue<ClientOrder> clientOrderQueue = new LinkedList<>();

        for(ClientOrder clientOrder : clientOrders){
            clientOrderQueue.add(clientOrder);
        }

        return clientOrderQueue;
    }
}
