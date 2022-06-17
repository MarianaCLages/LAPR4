package eapli.base.httpsServerAjax;

import eapli.base.agvmanagement.domain.AGV;
import eapli.base.agvmanagement.dto.AGVDto;
import eapli.base.agvmanagement.repositories.AGVRepository;
import eapli.base.httpsServerAjax.domain.HttpAjaxRequest;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.warehousemanagement.domain.AGVLocation;
import eapli.base.warehousemanagement.domain.Warehouse;
import eapli.base.warehousemanagement.repositories.AGVLocationRepository;
import eapli.base.warehousemanagement.repositories.WarehouseRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class HttpServerAjaxVoting {
    static private final String BASE_FOLDER = "base.app.server/src/main/java/eapli/base/httpsServerAjax/www";
    static private ServerSocket sock;
    static private String SERVER_SOCKET = "2228";

    private static final Logger LOGGER = LogManager.getLogger(HttpServerAjaxVoting.class);

    public static AGVRepository agvRepository = PersistenceContext.repositories().agvRepository();
    public static WarehouseRepository warehouseRepository = PersistenceContext.repositories().warehouseRepository();
    public static final AGVLocationRepository agvLocationRepository = PersistenceContext.repositories().agvLocations();

    public static void main(String args[]) throws Exception {
        Socket cliSock;

        try {
            sock = new ServerSocket(Integer.parseInt(SERVER_SOCKET));
        } catch (IOException ex) {
            LOGGER.error("Server failed to open local port " + SERVER_SOCKET);
            System.exit(1);
        }
        while (true) {
            cliSock = sock.accept();
            HttpAjaxRequest req = new HttpAjaxRequest(cliSock, BASE_FOLDER);
            req.start();

        }
    }

    public static synchronized String getInfo() {
        try {
            Warehouse warehouse = warehouseRepository.findWarehouse();
            StringBuilder s = new StringBuilder();

            s.append("<p>");
            /*s.append("<br>");
            s.append("<br>");
            s.append("### CURRENT AGV STATUS ###");
            s.append("<br>");
            s.append("<br>");

            for (AGV atv : agvRepository.findAll()) {

                AGVDto agvDto = atv.toDTO();
                s.append("<tr class=\"active-row\">" +
                        "<td>" + agvDto.toString() + "</td>" +
                        "</tr>");

                s.append("<br>");

            }

            s.append("<br>");
            s.append("<br>");*/

            s.append("<h3>Warehouse Plant</h3>");
            s.append("<br>");
            s.append("<br>");

            String[][] plant = warehouse.generatePlant();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {

            }

            try {
                List<AGVLocation> agvLocations = (List<AGVLocation>) agvLocationRepository.findAll();

                if (!agvLocations.isEmpty()) {

                    for (AGVLocation agvLocation : agvLocations) {

                        plant[agvLocation.getyPos()][agvLocation.getxPos()] = "|AGV|";

                        //TESTAR ISTO DPS
                        agvLocationRepository.delete(agvLocation);

                    }


                }

            } catch (Exception e) {
                System.out.println("Deu erro!");

            }

            s.append("<table style=\"width:60%\" summary=\"LAPR4_LEI_2DJ_G01 DASHBOARD\" cellpadding=\"6\" cellspacing=\"6\" border=\"10\" bordercolor=\"000000\" bgcolor=\"F0FFFF\"> <tbody>");

            for (int i = 0; i < plant.length; i++) {

                s.append("<tr>");

                for (int j = 0; j < plant[i].length; j++) {

                    if (plant[i][j].equals("") || plant[i][j].isEmpty() || plant[i][j].equals("||") || plant[i][j].equals("|  |"))
                        s.append("<td style=\"text-align:center\">" + "&emsp;" + "</td>");

                    else s.append("<td style=\"text-align:center\">" + plant[i][j] + "</td>");

                }
                s.append("</tr>");
            }

            s.append("</table>");
            s.append("</tbody>");
            s.append("<br>");
            s.append("<br>");
            s.append("<br>");
            s.append("<h3>Warehouse Plant subtitle</h3>");
            s.append("<br>");
            s.append("<br>");
            s.append("D - Dock | A - Aisle | R - Row | | &emsp | - Free Space");
            s.append("<br>");
            s.append("<br>");
            s.append("<br>");
            s.append("</p>");

            return s.toString();

        } catch (NullPointerException ne) {
            return " ";
        }
    }

}
