package eapli.base.productmanagement.domain;

import eapli.base.productmanagement.application.AssignShelfService;
import eapli.base.warehousemanagement.DTO.ShelfDTO;
import eapli.base.warehousemanagement.domain.Accessibility;
import eapli.base.warehousemanagement.domain.Location;
import eapli.base.warehousemanagement.domain.Warehouse;
import eapli.base.warehousemanagement.domain.WarehouseBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssignShelfTest {
    private final WarehouseBuilder warehouseBuilder = new WarehouseBuilder();

    @Test
    void ensureCorrectAssignment() {
        //uses the builder create a warehouse
        Warehouse warehouse = warehouseBuilder.withName("TestWarehouse")
                .withUnit("m")
                .withSquare(10)
                .withLength(100)
                .withWidth(100)
                .addAisle(1, new Location(1, 1), new Location(1, 10), new Location(1, 20), Accessibility.LENGHT_PLUS)
                .addRow(1, 1, new Location(1, 10), new Location(1, 20), 1)
                .addAgvDock("1", new Location(1, 10), new Location(1, 20), new Location(1, 5), Accessibility.LENGHT_PLUS)
                .build();

        AssignShelfService assignShelfService = new AssignShelfService(warehouse);

        ShelfDTO shelfDTO = assignShelfService.assignShelf();

        assertEquals(1, shelfDTO.aisle());
        assertEquals(1, shelfDTO.row());
        assertEquals(1, shelfDTO.shelf());
        assertNotNull(shelfDTO);

    }

    @Test
    void allShelfsAssigned() {
        //uses the builder create a warehouse
        Warehouse warehouse = warehouseBuilder.withName("TestWarehouse")
                .withUnit("m")
                .withSquare(10)
                .withLength(100)
                .withWidth(100)
                .addAisle(1, new Location(1, 1), new Location(1, 10), new Location(1, 20), Accessibility.LENGHT_PLUS)
                .addRow(1, 1, new Location(1, 10), new Location(1, 20), 1)
                .addAgvDock("1", new Location(1, 10), new Location(1, 20), new Location(1, 5), Accessibility.LENGHT_PLUS)
                .build();

        AssignShelfService assignShelfService = new AssignShelfService(warehouse);

        ShelfDTO shelfDTO = assignShelfService.assignShelf();

        Exception e = assertThrows(IllegalStateException.class, assignShelfService::assignShelf);
        assertEquals("There are no available shelves", e.getMessage());
    }


}
