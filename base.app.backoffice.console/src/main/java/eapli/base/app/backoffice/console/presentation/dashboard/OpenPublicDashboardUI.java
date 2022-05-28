package eapli.base.app.backoffice.console.presentation.dashboard;

import eapli.base.dashboard.application.OpenPublicDashboardController;
import eapli.framework.presentation.console.AbstractUI;

import java.io.IOException;
import java.net.URISyntaxException;


public class OpenPublicDashboardUI extends AbstractUI {
    private OpenPublicDashboardController controller = new OpenPublicDashboardController();

    @Override
    protected boolean doShow() {
        try {
            controller.openDashboard();
            System.out.println("\n\nOperation success!\n");
        } catch (IOException | URISyntaxException e) {
            System.out.println("The HTTPS Server is offline! Please contact an admin!");
            System.out.println("\n\nOperation failed!\n");
        }


        return true;
    }

    @Override
    public String headline() {
        return "Show Dashboard >";
    }
}
