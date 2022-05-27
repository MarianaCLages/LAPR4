package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.agvmanagement.application.CreateAGVController;
import eapli.base.agvmanagement.domain.AGV;
import eapli.base.agvmanagement.domain.AGVStatus;
import eapli.base.productmanagement.domain.Product;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class AGVBootStrapper implements Action {


    final CreateAGVController controller = new CreateAGVController();

    private static final Logger LOGGER = LogManager.getLogger(ProductBootstrapper.class);


    @Override
    public boolean execute() {


        final String agvIdentifier = "BX000001";
        final String agvIdentifier2 = "BX000002";

        final String agvIdentifier3 = "BX000003";
        final String agvIdentifier4 = "BX000004";
        final String agvIdentifier5 = "BX000005";
        final String agvIdentifier6 = "BX000006";
        final String agvIdentifier7 = "BX000007";
        final String agvIdentifier8 = "BX000008";
        final String agvIdentifier9 = "BX000009";
        final String agvIdentifier10 = "BX000010";

        final int agvAutonomy = 12;
        final int agvAutonomy2 = 13;

        final int agvAutonomy3 = 40;
        final int agvAutonomy4 = 20;
        final int agvAutonomy5 = 20;
        final int agvAutonomy6 = 20;
        final int agvAutonomy7 = 40;
        final int agvAutonomy8 = 40;
        final int agvAutonomy9 = 20;
        final int agvAutonomy10 = 30;

        final String agvDescription = new String("DescriptionAGV1");
        final String agvDescription2 = new String("DescriptionAGV2");

        final String agvDescription3 = "DescriptionAGV3";
        final String agvDescription4 = "DescriptionAGV4";
        final String agvDescription5 = "DescriptionAGV5";
        final String agvDescription6 = "DescriptionAGV6";
        final String agvDescription7 = "DescriptionAGV7";
        final String agvDescription8 = "DescriptionAGV8";
        final String agvDescription9 = "DescriptionAGV9";
        final String agvDescription10 = "DescriptionAGV10";

        final String agvModel = "ModelX";
        final String agvModel2 = "ModelXS";

        final String agvModel3 = "ModelXL";
        final String agvModel4 = "ModelZ";
        final String agvModel5 = "ModelY";
        final String agvModel6 = "ModelG";
        final String agvModel7 = "ModelH";
        final String agvModel8 = "ModelOP";
        final String agvModel9 = "ModelH";
        final String agvModel10 = "ModelG";

        final AGVStatus agvStatus = AGVStatus.AVAILABLE;


        registerAGVWithoutADockAttached(agvIdentifier, agvAutonomy, agvDescription, agvModel, agvStatus);
        registerAGVWithoutADockAttached(agvIdentifier2, agvAutonomy2, agvDescription2, agvModel2, agvStatus);

        registerAGVWithoutADockAttached(agvIdentifier3, agvAutonomy3, agvDescription2, agvModel3, agvStatus);
        registerAGVWithoutADockAttached(agvIdentifier4, agvAutonomy4, agvDescription2, agvModel4, agvStatus);
        registerAGVWithoutADockAttached(agvIdentifier5, agvAutonomy5, agvDescription2, agvModel5, agvStatus);
        registerAGVWithoutADockAttached(agvIdentifier6, agvAutonomy6, agvDescription2, agvModel6, agvStatus);
        registerAGVWithoutADockAttached(agvIdentifier7, agvAutonomy7, agvDescription2, agvModel7, agvStatus);
        registerAGVWithoutADockAttached(agvIdentifier8, agvAutonomy8, agvDescription2, agvModel8, agvStatus);
        registerAGVWithoutADockAttached(agvIdentifier9, agvAutonomy9, agvDescription2, agvModel9, agvStatus);
        registerAGVWithoutADockAttached(agvIdentifier10, agvAutonomy10, agvDescription2, agvModel10, agvStatus);

        return false;
    }

    private void registerAGVWithoutADockAttached(String agvIdentifier, int agvAutonomy, String agvDescription, String agvModel, AGVStatus agvStatus) {

        try {
            controller.createAGV(agvIdentifier, agvAutonomy, agvDescription, agvModel, agvStatus, null);
        } catch (final IntegrityViolationException | ConcurrencyException exception) {
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", "AGV");
            LOGGER.trace("Assuming existing record", exception);
        }
    }
}

