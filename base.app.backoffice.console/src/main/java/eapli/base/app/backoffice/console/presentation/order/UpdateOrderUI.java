package eapli.base.app.backoffice.console.presentation.order;

import eapli.base.agvmanagement.dto.AGVDto;
import eapli.base.ordermanagement.application.UpdateOrderController;
import eapli.base.ordermanagement.dto.OrderDto;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.ArrayList;
import java.util.List;

public class UpdateOrderUI extends AbstractUI {

    private final UpdateOrderController controller = new UpdateOrderController();

    @Override
    protected boolean doShow() {

        int filterOrNot;
        boolean filterByAGV = false;
        boolean invalidOption = false;

        int agvOption = 0;
        int orderOption = 0;

        List<AGVDto> agvDtoList;
        List<OrderDto> orderFromAgvList = new ArrayList<>();
        List<OrderDto> allOrdersList;

        try {
            do {
                filterOrNot = Console.readInteger("> 1 - Filter by AGV\n> 2 - See all orders without filter");

                if (filterOrNot == 2) {
                    filterByAGV = false;
                } else if (filterOrNot == 1) {
                    filterByAGV = true;
                } else {
                    throw new IllegalArgumentException("\nPlease enter a valid option! (1/2)");
                }

                invalidOption = true;

            } while (!invalidOption);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            invalidOption = false;
        }

        agvDtoList = controller.getAgvDTOList();
        allOrdersList = controller.getOrdersToBeDispatchedList();
        invalidOption = false;

        if (!agvDtoList.isEmpty()) {
            if (filterByAGV) {
                // Choose AGV
                try {
                    do {
                        int i = 0;
                        System.out.println("\n### AGV LIST ###\n");
                        for (AGVDto agv : agvDtoList) {
                            System.out.println(i + 1 + ". " + agv.toString() + '\n');
                            i++;
                        }

                        agvOption = Console.readInteger("\nPlease select the AGV from the list above:");

                        if (agvOption == 0 || agvOption < 0) {
                            throw new IllegalArgumentException("\nInvalid option! It must be above 0. Please, try again.");
                        }

                        invalidOption = true;

                        AGVDto agvDto = agvDtoList.get(agvOption - 1);
                        orderFromAgvList = controller.getOrdersToBeDispatchedFromAGV(agvDto);

                    } while (!invalidOption);

                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    invalidOption = false;
                } catch (Exception e) {
                    System.out.println("There was an error during the process. Please, try again.");
                    invalidOption = false;
                }

                invalidOption = false;

                if (!orderFromAgvList.isEmpty()) {
                    try {
                        do {
                            int j = 0;
                            if (!orderFromAgvList.isEmpty()) {
                                System.out.println("\n ### Orders to be dispatched of the selected AGV ###");
                                for (OrderDto order : orderFromAgvList) {
                                    System.out.println(j + 1 + ". " + order.toString() + '\n');
                                    j++;
                                }

                                orderOption = Console.readInteger("\nPlease select the order to update from the list above:");

                                if (orderOption == 0 || orderOption < 0) {
                                    throw new IllegalArgumentException("\nInvalid option! It must be above 0. Please, try again.");
                                }

                                OrderDto orderDto = orderFromAgvList.get(orderOption - 1);
                                controller.updateOrderStatusAsDispatched(orderDto);

                                invalidOption = true;
                            }
                        } while (!invalidOption);

                        System.out.println("\nThe order status was changed.\nOperation success!");

                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        invalidOption = false;
                    } catch (Exception e) {
                        System.out.println("\nThere was an error during the process. Please, try again.");
                        invalidOption = false;
                    }
                } else {
                    System.out.println("\nThe AGV selected has no orders to be dispatched!");
                }

            } else {
                try {
                    do {
                        int m = 0;
                        System.out.println("\n### ORDER LIST ###\n");
                        for (OrderDto order : allOrdersList) {
                            System.out.println(m + 1 + ". " + order.toString() + '\n');
                            m++;
                        }

                        orderOption = Console.readInteger("\nPlease select the order to update from the list above:");

                        if (orderOption == 0 || orderOption < 0) {
                            throw new IllegalArgumentException("\nInvalid option! It must be above 0. Please, try again.");
                        }

                        OrderDto orderDto = allOrdersList.get(orderOption - 1);
                        controller.updateOrderStatusAsDispatched(orderDto);

                        invalidOption = true;

                    } while (!invalidOption);

                    System.out.println("\nThe order status was changed.\nOperation success!");

                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    invalidOption = false;
                } catch (Exception e) {
                    System.out.println("\nThere was an error during the process. Please, try again.");
                    invalidOption = false;
                }
            }

        } else {
            System.out.println("\nThere are no AGVs in the system!");
        }

        return false;
    }

    @Override
    public String headline() {
        return "Update order status";
    }
}
