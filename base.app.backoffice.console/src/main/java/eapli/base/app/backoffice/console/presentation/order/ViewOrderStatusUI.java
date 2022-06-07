package eapli.base.app.backoffice.console.presentation.order;

import eapli.base.servers.EstablishConnectionService;
import eapli.framework.presentation.console.AbstractUI;

public class ViewOrderStatusUI extends AbstractUI {

    private final EstablishConnectionService establishConnectionService = new EstablishConnectionService();

    @Override
    protected boolean doShow() {
        System.out.println("###Your Orders: ###\n\n");
        establishConnectionService.createConnectionWithTheTcpOrderServer((byte) 10);


        return true;
    }

    @Override
    public String headline() {
        return "View Order Status";
    }
}
