package eapli.base.agvmanagement.application;

import eapli.base.agvmanagement.domain.*;
import eapli.base.agvmanagement.repositories.AGVRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.warehousemanagement.domain.AGVDock;

public class CreateAGVService {

    private AGVRepository agvRepository = PersistenceContext.repositories().agvRepository();

    public boolean createAGVService(String agvIdentifier, int agvAutonomy,
                                String agvDescription, String agvModel,
                                AGVStatus agvStatus, AGVDock agvDock) {

        AGV agv = new AGVBuilder()
                .identifier(agvIdentifier)
                .autonomy(agvAutonomy)
                .description(agvDescription)
                .model(agvModel)
                .status(agvStatus)
                .dock(agvDock)
                .build();


        agvRepository.save(agv);
        return true;
    }
}
