package eapli.base.agvmanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.warehousemanagement.domain.AGVDock;
import eapli.base.warehousemanagement.repositories.WarehouseRepository;

public class SearchAGVDockService {


    WarehouseRepository warehouseRepository = PersistenceContext.repositories().warehouseRepository();


    public AGVDock searchAGVDockByIdSerivice(Integer id){
        return warehouseRepository.searchAGVDockById(id);
    }
}
