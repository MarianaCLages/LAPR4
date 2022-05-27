package eapli.base.app.backoffice.console.presentation.catalog;

import eapli.base.shoppingCartManagement.application.ViewCatalogAndAddProductController;
import eapli.framework.presentation.console.AbstractUI;

public class ViewShoppingCartUI extends AbstractUI {

    private final ViewCatalogAndAddProductController controller = new ViewCatalogAndAddProductController();

    @Override
    protected boolean doShow() {
        controller.handleCustomer();

        System.out.println("\n### Customer Shopping Cart ###\n");
        System.out.println(controller.getShoppingCart());

        System.out.println("\nOperation Success!\n");

        return false;
    }

    @Override
    public String headline() {
        return "View your shopping cart >";
    }
}
