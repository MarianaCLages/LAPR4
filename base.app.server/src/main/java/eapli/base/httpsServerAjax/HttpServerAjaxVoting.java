package eapli.base.httpsServerAjax;

import eapli.base.agvmanagement.domain.AGV;
import eapli.base.agvmanagement.dto.AGVDto;
import eapli.base.agvmanagement.repositories.AGVRepository;
import eapli.base.httpsServerAjax.domain.HttpAjaxVotingRequest;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.warehousemanagement.domain.Warehouse;
import eapli.base.warehousemanagement.repositories.WarehouseRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServerAjaxVoting {
    static private final String BASE_FOLDER = "base.app.server/src/main/java/eapli/base/httpsServerAjax/www";
    static private ServerSocket sock;
    static private String SERVER_SOCKET = "2228";

    private static final Logger LOGGER = LogManager.getLogger(HttpServerAjaxVoting.class);

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
            HttpAjaxVotingRequest req = new HttpAjaxVotingRequest(cliSock, BASE_FOLDER);
            req.start();

        }
    }


    // DATA ACCESSED BY THREADS - LOCKING REQUIRED

    public static AGVRepository agvRepository = PersistenceContext.repositories().agvRepository();
    public static WarehouseRepository warehouseRepository = PersistenceContext.repositories().warehouseRepository();

    public static synchronized String getInfo() {
        try {
            StringBuilder s = new StringBuilder();
            Warehouse warehouse = warehouseRepository.findWarehouse();

            s.append("<br>");
            s.append("<br>");
            s.append("### WAREHOUSE PLANT ###");
            s.append("<br>");
            s.append("<br>");

            String[][] plant = warehouse.generatePlant();

            s.append("<table style=\"width:60%\">");
            s.append("<tr>");

            for (int i = 0; i < plant.length; i++) {

                s.append("<tr>");

                for (int j = 0; j < plant[i].length; j++) {

                    s.append("<td>" + plant[i][j] + "</td>");

                }
                s.append("</tr>");
            }

            s.append("<br>");
            s.append("<br>");
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
            s.append("<br>");

            s.append("</p>");

            return s.toString();

        } catch (NullPointerException ne) {
            return " ";
        }
    }

}
