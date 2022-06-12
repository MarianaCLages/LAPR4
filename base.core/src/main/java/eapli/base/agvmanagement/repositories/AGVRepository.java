package eapli.base.agvmanagement.repositories;

import eapli.base.agvmanagement.domain.AGV;
import eapli.base.agvmanagement.domain.AGVDescription;
import eapli.base.agvmanagement.domain.AGVModel;
import eapli.base.warehousemanagement.domain.AGVDock;
import eapli.base.warehousemanagement.domain.Warehouse;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.List;

public interface AGVRepository extends DomainRepository<Long, AGV> {
    AGV findByID(long id);

    AGV findByDock(AGVDock dock);

    List<AGV> findFreeAGVS();

    void updateAGV(AGV agv);

    List<AGV> findAllAGVS();

    AGV findByModelAndDescription(AGVModel model, AGVDescription description);


}
