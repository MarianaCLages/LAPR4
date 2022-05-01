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

import java.io.IOException;


public class AGVBootStrapper implements Action {


    final CreateAGVController controller = new CreateAGVController();

    private static final Logger LOGGER = LogManager.getLogger(ProductBootstrapper.class);


    @Override
    public boolean execute() {


        final String agvIdentifier = new String("11111111");
        final String agvIdentifier2 = new String("11111111");

        final int agvAutonomy = 12;
        final int agvAutonomy2 = 13;

        final String agvDescription = new String("description");
        final String agvDescription2 = new String("description23");

        final String agvModel = new String("AGVMODEL");
        final String agvModel2 = new String("AGVMODEL12");

        final AGVStatus agvStatus = AGVStatus.AVAILABLE;


        registerAGVWithoutADockAttached(agvIdentifier,agvAutonomy,agvDescription,agvModel,agvStatus);
        registerAGVWithoutADockAttached(agvIdentifier2,agvAutonomy2,agvDescription2,agvModel2,agvStatus);

        return false;
    }

    private void registerAGVWithoutADockAttached(String agvIdentifier,int agvAutonomy,String agvDescription,String agvModel,AGVStatus agvStatus){

        try {
            controller.createAGV(agvIdentifier,agvAutonomy,agvDescription,agvModel,agvStatus,null);
        } catch (final IntegrityViolationException | ConcurrencyException  exception) {
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", "AGV");
            LOGGER.trace("Assuming existing record", exception);
        }
    }
}
