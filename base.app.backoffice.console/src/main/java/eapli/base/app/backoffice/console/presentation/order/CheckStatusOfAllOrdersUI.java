package eapli.base.app.backoffice.console.presentation.order;

import eapli.base.ordermanagement.application.ViewAllOrdersService;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.List;

public class CheckStatusOfAllOrdersUI extends AbstractUI {

    private final ViewAllOrdersService viewAllOrdersService = new ViewAllOrdersService();

    @Override
    protected boolean doShow() {

        int index = 1;
        for (String s : viewAllOrdersService.getAllOrdersFromServer()) {
            System.out.println(">" + " - " + s);
            index++;
        }

        boolean invalidInputOptions = false;
        boolean changeOrderStatus = false;
        String addMoreOptions;

        do {
            try {
                addMoreOptions = Console.readLine("\nDo you wish to add any product to your shopping cart?");

                if (addMoreOptions.equals("No") | addMoreOptions.equals("NO") | addMoreOptions.equals("no") | addMoreOptions.equals("N") | addMoreOptions.equals("n")) {

                    invalidInputOptions = true;
                    changeOrderStatus = false;

                } else if (addMoreOptions.equals("Yes") | addMoreOptions.equals("YES") | addMoreOptions.equals("yes") | addMoreOptions.equals("Y") | addMoreOptions.equals("y")) {

                    invalidInputOptions = true;
                    changeOrderStatus = true;

                } else {
                    throw new IllegalArgumentException("Please enter a valid option!! (Yes or No)");
                }

                invalidInputOptions = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                invalidInputOptions = false;
            }

        } while (!invalidInputOptions);


        System.out.println("\n\nOperation Success!!\n");
        return true;
    }

    @Override
    public String headline() {
        return null;
    }
}
