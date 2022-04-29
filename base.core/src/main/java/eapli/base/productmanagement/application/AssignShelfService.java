package eapli.base.productmanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.warehousemanagement.DTO.ShelfDTO;
import eapli.base.warehousemanagement.domain.Shelf;
import eapli.base.warehousemanagement.domain.Warehouse;
import eapli.base.warehousemanagement.repositories.WarehouseRepository;
import eapli.framework.application.ApplicationService;

@ApplicationService
public class AssignShelfService {
    private final Warehouse warehouse;
    private final WarehouseRepository warehouseRepository = PersistenceContext.repositories().warehouseRepository();


    public AssignShelfService(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public ShelfDTO assignShelf() {
        Shelf shelf = this.warehouse.assignShelf();
        warehouseRepository.updateShelf(shelf);
        return shelf.toDTO();
    }
}
