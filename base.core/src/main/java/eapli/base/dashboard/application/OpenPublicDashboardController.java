package eapli.base.dashboard.application;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class OpenPublicDashboardController {

    public void openDashboard() throws URISyntaxException, IOException {
        Desktop desk = Desktop.getDesktop();
        desk.browse(new URI("https://vs-gate.dei.isep.ipp.pt:30639/"));
    }

}