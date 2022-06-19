package eapli.base.app.backoffice.console.presentation.order;

import eapli.base.ordermanagement.application.UpdateOrderController;
import eapli.base.ordermanagement.domain.ClientOrder;
import eapli.base.ordermanagement.dto.OrderDto;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

public class UpdateOrderAsBeingDeliveredUI extends AbstractUI {


    private final UpdateOrderController controller = new UpdateOrderController();

    @Override
    protected boolean doShow() {


        List<OrderDto> orderList = controller.getOrdersToBeDispatchedList();
        int id = -1;
        int exist = 0;
        boolean validId = false;
        OrderDto order = null;

        if (orderList.isEmpty()) {
            System.out.println("\nThere are no orders to be dispatched for customer delivery!");
            return true;
        }

        System.out.println(showOrder(orderList));

        while (id != 0) {

            id = Console.readInteger("\nPlease, select which order you wish to update as being delivered. Press 0, to cancel the operation\n");

            if (id != 0) {
                for (OrderDto clientOrder : orderList) {

                    if (clientOrder.getId() == Long.valueOf(id)) {
                        exist++;
                        order = clientOrder;
                    }
                }

                if (exist == 1) {
                    System.out.println("\nOrder with the ID: " + id + " recognized!\nUpdating to as being delivered...\n");
                    controller.updateOrderStatusAsBeingDelivered(order);
                    orderList = controller.getOrdersToBeDispatchedList();
                    System.out.println(showOrder(orderList));
                } else {
                    System.out.println("There is not a order with that ID!\n");
                    System.out.println(showOrder(orderList));
                }

                exist = 0;
            }
        }


        return true;
    }

    @Override
    public String headline() {
        return "Update Order as being delivered.";
    }

    public String showOrder(List<OrderDto> orderDtoList) {

        StringBuilder sb = new StringBuilder();

        for (OrderDto dto : orderDtoList) {
            sb.append(dto + "\nOrder ID: " + dto.getId() + "\n\n");
        }

        return sb.toString();
    }
}
