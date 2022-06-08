package eapli.base.app.backoffice.console.presentation.order;

import eapli.base.ordermanagement.application.ViewOrderStatusController;
import eapli.base.servers.EstablishConnectionService;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.List;

public class ViewOrderStatusUI extends AbstractUI {

    private final EstablishConnectionService establishConnectionService = new EstablishConnectionService();
    private final ViewOrderStatusController viewOrderStatusController = new ViewOrderStatusController();

    @Override
    protected boolean doShow() {
        System.out.println("###Your Orders: ###\n\n");

        int index = 1;
        for (String s : viewOrderStatusController.getAllOrdersFromLoggedCustomer()) {
            System.out.println(">" + index + " - " + s);
            index++;
        }


        String addMoreOptions;
        boolean invalidInputOptions = false;
        boolean changeOrder = false;

        do {
            try {
                addMoreOptions = Console.readLine("\nDo you wish to change the state of any order which state is “delivered by carrier“?\n");

                if (addMoreOptions.equals("No") | addMoreOptions.equals("NO") | addMoreOptions.equals("no") | addMoreOptions.equals("N") | addMoreOptions.equals("n")) {

                    invalidInputOptions = true;
                    changeOrder = true;

                } else if (addMoreOptions.equals("Yes") | addMoreOptions.equals("YES") | addMoreOptions.equals("yes") | addMoreOptions.equals("Y") | addMoreOptions.equals("y")) {

                    invalidInputOptions = true;
                    changeOrder = false;

                } else {
                    throw new IllegalArgumentException("Please enter a valid option!! (Yes or No)");
                }

                invalidInputOptions = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                invalidInputOptions = false;
            }

        } while (!invalidInputOptions);

        if (changeOrder) {

            List<String> orderList = viewOrderStatusController.getAllOrdersFromLoggedCustomerInDeliveredState();

            int option = 0;

            if (!orderList.isEmpty()) {
                invalidInputOptions = false;

                do {
                    try {
                        option = Console.readInteger("Which order from the list you wish to change the status?\n");

                        if (option < 0 || option > index) {
                            throw new IllegalArgumentException("Please enter a valid option!! (Yes or No)");
                        }
                        invalidInputOptions = true;
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        invalidInputOptions = false;
                    }

                } while (!invalidInputOptions);

                try {
                    viewOrderStatusController.changeOrderState(index - 1);
                } catch (Exception e) {
                    System.out.println("There was an error while trying to change the order status!\nPlease contact an admin!\n");
                    return false;
                }

            } else {
                System.out.println("You don't have any order in the delivered by carrier status!!\n");
            }


        }

        System.out.println("Operation Success!\n\n");
        return true;
    }

    @Override
    public String headline() {
        return "View Order Status";
    }
}
