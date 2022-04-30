package eapli.base.agvmanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.warehousemanagement.domain.AGVDock;
import eapli.base.warehousemanagement.repositories.WarehouseRepository;

public class SearchAllAGVDocksService {

    WarehouseRepository warehouseRepository = PersistenceContext.repositories().warehouseRepository();

    public Iterable<AGVDock> searchAllAGVDocksService(){
        return warehouseRepository.findAllAGVDock();
    }
}
