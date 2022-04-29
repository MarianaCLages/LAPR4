package eapli.base.binmanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.application.AssignShelfService;
import eapli.base.warehousemanagement.domain.Warehouse;
import eapli.base.warehousemanagement.repositories.WarehouseRepository;
import eapli.framework.application.ApplicationService;

@ApplicationService
public class SearchWarehouseService {

    private final WarehouseRepository warehouseRepository = PersistenceContext.repositories().warehouseRepository();

    public Warehouse getWarehouse(){
       return warehouseRepository.findWarehouse();


    }


}
