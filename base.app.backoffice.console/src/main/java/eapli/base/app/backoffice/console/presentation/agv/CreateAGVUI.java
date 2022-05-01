package eapli.base.app.backoffice.console.presentation.agv;


import eapli.base.agvmanagement.AGVDto;
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

        String identification = null;
        AGVDock agvDock = null;
        Integer id;
        Iterable<AGVDock> agvDocks = null;

        int autonomy;
        String shortDescription = null;
        String agvModel = null;
        AGVStatus agvStatus = AGVStatus.AVAILABLE;

        //verify
        boolean autonomyVerification = false;
        boolean agvDockVerification = false;
        boolean agvIdentification = false;

        try {
            do {
                try {
                    identification = Console.readLine("\nAGV identification? Must be 8 character long");
                    if (identification.length() != 8) {
                        throw new IllegalArgumentException("\nThe AGV identification MUST have a size of 8 characters!");
                    }

                    agvIdentification = true;
                } catch (IllegalArgumentException e) {
                    agvIdentification = false;
                    System.out.println(e.getMessage());
                }

            } while (!agvIdentification);

            try {
                agvDocks = agvController.searchAllAGVDock();
            } catch (Exception e) {
                System.out.println("\nThere was an error while trying to get all AGV Docks! Verify if the warehouse was imported correctly!");
                return false;
            }

            do {
                try {
                    System.out.println("\nAvailable AGV Docks: \n");
                    for (AGVDock a : agvDocks) {
                        System.out.println("> AGV ID -> " + a.identification());
                    }

                    id = Console.readInteger("\nPlease enter the id of the AGV Dock: \n");

                    agvDock = agvController.searchAGVDock(id);
                    agvDockVerification = true;
                } catch (IllegalArgumentException | NoResultException ex) {
                    System.out.println("\nThere is no AGV Dock with that ID!");
                }
            } while (!agvDockVerification);

            agvIdentification = false;
            autonomy = 0;

            do {
                try {
                    autonomy = Console.readInteger("\nAGV autonomy? (NOTICE: The autonomy of an AGV can only have values between 500-2000 minutes)");

                    if (autonomy < 0) {
                        throw new IllegalArgumentException("\nPlease enter a positive number");
                    } else if (autonomy > 2000) {
                        throw new IllegalArgumentException("\nPlease don't enter a value above 2000!");
                    } else if (autonomy < 500) {
                        throw new IllegalArgumentException("\nPlease don't enter a value below 500!");
                    }

                    agvIdentification = true;

                } catch (IllegalArgumentException e) {
                    agvIdentification = false;
                    System.out.println(e.getMessage());
                }
            } while (!agvIdentification);


            agvIdentification = false;

            do {
                try {
                    agvModel = Console.readLine("\nPlease specify the AGV Model: (NOTICE: The maximum size of input is 50 chars)!");

                    if (agvModel.length() > 50) {
                        throw new IllegalArgumentException("\nPlease enter a model that has a length below 50 chars!");
                    }

                    agvIdentification = true;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    agvIdentification = false;
                }

            } while (!agvIdentification);


            agvIdentification = false;

            do {
                try {
                    shortDescription = Console.readLine("\nPlease specify the short description of the AGV: (NOTICE: The maximum size of input is 30 chars)!");

                    if (shortDescription.length() > 30) {
                        throw new IllegalArgumentException("\nPlease enter a short description that has a length below 30 chars!");
                    }

                    agvIdentification = true;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    agvIdentification = false;
                }

            } while (!agvIdentification);


            try {
                AGVDto agvDto = agvController.createAGV(identification, autonomy, shortDescription, agvModel, agvStatus, agvDock).toDTO();
                System.out.println("\n### AGV Created ### \n" + agvDto + "\n\nOperation Success!");
                return true;
            } catch (IllegalStateException ex) {
                System.out.println("\nUnable to Create AGV!\n\nOperation Failed!");
            }
        }catch (Exception e){
            System.out.println("Some error occurred in the program!");
            return false;
        }
        return false;
    }


    @Override
    public String headline() {
        return "Configure AGV";
    }
}
