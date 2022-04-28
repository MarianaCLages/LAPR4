package eapli.base.productmanagement.application;

import eapli.base.warehousemanagement.DTO.ShelfDTO;
import eapli.base.warehousemanagement.domain.Shelf;
import eapli.base.warehousemanagement.domain.Warehouse;

public class AssignShelfService {
    private final Warehouse warehouse;

    public AssignShelfService(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public ShelfDTO assignShelf() {
        Shelf shelf = this.warehouse.assignShelf();
        return shelf.toDTO();
    }
}
