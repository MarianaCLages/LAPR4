package eapli.base.app.backoffice.console.presentation.order;

import eapli.base.agvmanagement.dto.AGVDto;
import eapli.base.app.backoffice.console.presentation.catalog.SearchCatalogUI;
import eapli.base.ordermanagement.application.AssignOrderToAGVController;
import eapli.base.ordermanagement.dto.OrderDto;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AssignOrderToAGVUI extends AbstractUI {

    private final AssignOrderToAGVController assignOrderToAGVController = new AssignOrderToAGVController();
    private static final Logger LOGGER = LoggerFactory.getLogger(AssignOrderToAGVUI.class);


    @Override
    protected boolean doShow() {

        AtomicInteger index = new AtomicInteger(1);
        AtomicInteger index_2 = new AtomicInteger(1);

        List<OrderDto> clientOrderList;
        List<AGVDto> agvDtoList;

        clientOrderList = assignOrderToAGVController.viewAllOrdersToBePrepared();

        if (!clientOrderList.isEmpty()) {

            System.out.println("### Orders to be prepared ###\n");
            clientOrderList.forEach(s -> {
                System.out.println("> " + index + " - " + s);
                index.getAndIncrement();

            });

            int currentIndex = index.get();

            boolean invalidOption;
            int option_order = 0;

            try {
                do {

                    option_order = Console.readInteger("\nPlease choose one of the orders available: ");

                    if (option_order < 0) {
                        throw new IllegalArgumentException("Please don't enter a negative value!");
                    } else if (option_order > (currentIndex - 1)) {
                        throw new IllegalArgumentException("Please enter a valid option_order! Enter a option_order between 1 and " + currentIndex);
                    }

                    invalidOption = true;

                } while (!invalidOption);

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                invalidOption = false;
            } catch (Exception e) {
                System.out.println("Invalid?");
                invalidOption = false;
            }

            OrderDto chosenOrder = clientOrderList.get(option_order - 1);

            agvDtoList = assignOrderToAGVController.viewAllAGVs(chosenOrder.getWeight());


            if (!agvDtoList.isEmpty()) {

                System.out.println("\n### All available AGVs at the moment, that can conduct that order: ###\n");
                agvDtoList.forEach(s -> {
                    System.out.println("> " + index_2 + " - " + s);
                    index_2.getAndIncrement();

                });

                int option_agv = 0;
                currentIndex = index_2.get();

                try {
                    do {

                        option_agv = Console.readInteger("\nPlease choose one of the agvs available: ");

                        if (option_agv < 0) {
                            throw new IllegalArgumentException("Please don't enter a negative value!");
                        } else if (option_agv > (currentIndex - 1)) {
                            throw new IllegalArgumentException("Please enter a valid option_order! Enter a option_order between 1 and " + currentIndex);
                        }

                        invalidOption = true;

                    } while (!invalidOption);

                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    invalidOption = false;
                } catch (Exception e) {
                    System.out.println("Invalid?");
                    invalidOption = false;
                }

                AGVDto choosenAGV = agvDtoList.get(option_agv - 1);

                try {
                    assignOrderToAGVController.assignOrderToAGV(chosenOrder, choosenAGV);

                    System.out.println("\nOrder assigned to AGV with success!\nOperation Success!\n");

                } catch (Exception e) {
                    System.out.println("\nOperation failed\nPlease contact an admin!");
                    LOGGER.error(e.getMessage());

                }

            } else {
                System.out.println("\nThere is no AGV to prepare the order right now!\n");
            }


        } else {
            System.out.println("\nThere is no order to be prepared right now!\n");
        }


        return false;
    }

    @Override
    public String headline() {
        return "Assign Order to an AGV >";
    }
}
