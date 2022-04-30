package eapli.base.app.backoffice.console.presentation.agv;


import eapli.base.agvmanagement.application.CreateAGVController;
import eapli.base.agvmanagement.domain.AGVStatus;
import eapli.base.warehousemanagement.domain.AGVDock;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import javax.persistence.NoResultException;

public class CreateAGVUI extends AbstractUI {

    CreateAGVController agvController = new CreateAGVController();


    @Override
    protected boolean doShow() {

        String identification;
        AGVDock agvDock = null;
        Integer id;
        Iterable<AGVDock> agvDocks = null;

        int autonomy;
        String shortDescription;
        String agvModel;
        AGVStatus agvStatus = AGVStatus.AVAILABLE;
        //verify
        boolean autonomyVerification = false;
        boolean agvDockVerification = false;
        boolean agvIdentification = false;

        do {
            identification = Console.readLine("AGV identification? Must be 8 character long");
            if(identification.length() == 8) agvIdentification = true;
        }while (!agvIdentification);
        agvDocks = agvController.searchAllAGVDock();


        do {
            try {
                System.out.println("Available AGV Docks:\n");
                for (AGVDock a : agvDocks) {
                    System.out.println("AGV ID:" + a.identification());
                }

                id = Console.readInteger("What is the AGV Dock Id?");

                agvDock = agvController.searchAGVDock(id);
                agvDockVerification = true;
            } catch (IllegalArgumentException | NoResultException ex) {
                System.out.println("There is no AGV Dock with that ID!");
            }
        } while (!agvDockVerification);

        do {
            autonomy = Console.readInteger("AGV autonomy?");
        }while (autonomy <= 0);
        agvModel = Console.readLine("What is the AGV Model?");
        shortDescription = Console.readLine("Insert a short description of the AGV:");

        try {
            agvController.createAGV(identification, autonomy, shortDescription, agvModel, agvStatus, agvDock);
            System.out.println("AGV Created!");
            return true;
        } catch (IllegalStateException ex) {
            System.out.println("Unable to Create AGV!");
        }

        return false;
    }


    @Override
    public String headline() {
        return "Return new AGV";
    }
}
