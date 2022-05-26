package eapli.base.warehousemanagement.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class WarehouseTest {

    @Test
    void simpleWarehouseTest() {
        WarehouseBuilder warehouseBuilder = new WarehouseBuilder()
                .withLength(20)
                .withWidth(30)
                .withSquare(1)
                .withUnit("m")
                .addAgvDock(String.valueOf(1), new Location(5, 4), new Location(5, 5), new Location(6, 6), Accessibility.LENGHT_PLUS)
                .addAgvDock(String.valueOf(2), new Location(10, 4), new Location(10, 5), new Location(10, 6), Accessibility.WIDTH_MINUS)
                .addAisle(1, new Location(0, 1), new Location(0, 6), new Location(3, 3), Accessibility.LENGHT_PLUS)
                .addAisle(2, new Location(10, 15), new Location(10, 20), new Location(15, 15), Accessibility.WIDTH_MINUS)
                .addRow(1, 1, new Location(0, 1), new Location(0, 2), 5)
                .addRow(1, 2, new Location(0, 2), new Location(0, 3), 10)
                .addRow(2, 1, new Location(10, 15), new Location(10, 16), 5)
                .withName("A Simple Warehouse");

        Warehouse warehouse = warehouseBuilder.build();
        assertNotNull(warehouse);
        //aisles
        assertEquals(2, warehouse.aisles().size());
    }

    @Test
    void impossibleWarehouseWithoutRows() {

        WarehouseBuilder warehouseBuilder = new WarehouseBuilder()
                .addAgvDock(String.valueOf(1), new Location(5, 4), new Location(5, 5), new Location(6, 6), Accessibility.LENGHT_PLUS)
                .addAgvDock(String.valueOf(2), new Location(10, 4), new Location(10, 5), new Location(10, 6), Accessibility.WIDTH_MINUS)
                .addAisle(1, new Location(0, 1), new Location(0, 6), new Location(3, 3), Accessibility.LENGHT_PLUS)
                .addAisle(2, new Location(10, 15), new Location(10, 20), new Location(15, 15), Accessibility.WIDTH_MINUS)
                .withName("No Rows :(");

        Exception e = Assertions.assertThrows(IllegalArgumentException.class, warehouseBuilder::build);
        assertEquals("Aisles must have rows", e.getMessage());
    }

    @Test
    void impossibleWarehouseWithoutAgvDocs() {

        WarehouseBuilder warehouseBuilder = new WarehouseBuilder()
                .addAisle(1, new Location(0, 1), new Location(0, 6), new Location(3, 3), Accessibility.LENGHT_PLUS)
                .addAisle(2, new Location(10, 15), new Location(10, 20), new Location(15, 15), Accessibility.WIDTH_MINUS)
                .addRow(1, 1, new Location(0, 1), new Location(0, 2), 5)
                .addRow(1, 2, new Location(0, 2), new Location(0, 3), 10)
                .withName("No AgvDocks :(");

        Exception e = Assertions.assertThrows(java.lang.IllegalArgumentException.class, warehouseBuilder::build);
        assertEquals("There must be at least one AGVDock", e.getMessage());

    }

    @Test
    void tryToAddRowsToAislesThatDontExist() {
        WarehouseBuilder warehouseBuilder = new WarehouseBuilder()
                .addAgvDock(String.valueOf(1), new Location(5, 4), new Location(5, 5), new Location(6, 6), Accessibility.LENGHT_PLUS)
                .addAgvDock(String.valueOf(2), new Location(10, 4), new Location(10, 5), new Location(10, 6), Accessibility.WIDTH_MINUS)
                .addAisle(1, new Location(0, 1), new Location(0, 6), new Location(3, 3), Accessibility.LENGHT_PLUS)
                .addAisle(2, new Location(10, 15), new Location(10, 20), new Location(15, 15), Accessibility.WIDTH_MINUS)
                .addRow(1, 1, new Location(0, 1), new Location(0, 2), 5)
                .addRow(1, 2, new Location(0, 2), new Location(0, 3), 10)
                .addRow(2, 1, new Location(10, 15), new Location(10, 16), 5)
                .withName("No Rows :(");


        Location l1 = new Location(20, 20);
        Location l2 = new Location(20, 21);

        Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> warehouseBuilder.addRow(3, 2, l1, l2, 10));
        assertEquals("Aisle with id 3 does not exist", e.getMessage());
    }

    @Test
    void impossibleEmptyName() {
        WarehouseBuilder warehouseBuilder = new WarehouseBuilder()
                .addAgvDock(String.valueOf(1), new Location(5, 4), new Location(5, 5), new Location(6, 6), Accessibility.LENGHT_PLUS)
                .addAgvDock(String.valueOf(2), new Location(10, 4), new Location(10, 5), new Location(10, 6), Accessibility.WIDTH_MINUS)
                .addAisle(1, new Location(0, 1), new Location(0, 6), new Location(3, 3), Accessibility.LENGHT_PLUS)
                .addAisle(2, new Location(10, 15), new Location(10, 20), new Location(15, 15), Accessibility.WIDTH_MINUS)
                .addRow(1, 1, new Location(0, 1), new Location(0, 2), 5)
                .addRow(1, 2, new Location(0, 2), new Location(0, 3), 10)
                .addRow(2, 1, new Location(10, 15), new Location(10, 16), 5);


        Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> warehouseBuilder.withName(""));
        assertEquals("Name should neither be null nor empty nor have starting blank spaces", e.getMessage());
    }

    @Test
    void impossibleNoUnit() {
        WarehouseBuilder warehouseBuilder = new WarehouseBuilder()
                .addAgvDock(String.valueOf(1), new Location(5, 4), new Location(5, 5), new Location(6, 6), Accessibility.LENGHT_PLUS)
                .addAgvDock(String.valueOf(2), new Location(10, 4), new Location(10, 5), new Location(10, 6), Accessibility.WIDTH_MINUS)
                .addAisle(1, new Location(0, 1), new Location(0, 6), new Location(3, 3), Accessibility.LENGHT_PLUS)
                .addAisle(2, new Location(10, 15), new Location(10, 20), new Location(15, 15), Accessibility.WIDTH_MINUS)
                .addRow(1, 1, new Location(0, 1), new Location(0, 2), 5)
                .addRow(1, 2, new Location(0, 2), new Location(0, 3), 10)
                .addRow(2, 1, new Location(10, 15), new Location(10, 16), 5)
                .withName("No Unit :(")
                .withLength(20)
                .withWidth(20)
                .withSquare(1);

        Exception e = Assertions.assertThrows(IllegalArgumentException.class, warehouseBuilder::build);
        assertEquals("No unit was set", e.getMessage());
    }

    @Test
    void impossibleLenght() {
        WarehouseBuilder warehouseBuilder = new WarehouseBuilder()
                .addAgvDock(String.valueOf(1), new Location(5, 4), new Location(5, 5), new Location(6, 6), Accessibility.LENGHT_PLUS)
                .addAgvDock(String.valueOf(2), new Location(10, 4), new Location(10, 5), new Location(10, 6), Accessibility.WIDTH_MINUS)
                .addAisle(1, new Location(0, 1), new Location(0, 6), new Location(3, 3), Accessibility.LENGHT_PLUS)
                .addAisle(2, new Location(10, 15), new Location(10, 20), new Location(15, 15), Accessibility.WIDTH_MINUS)
                .addRow(1, 1, new Location(0, 1), new Location(0, 2), 5)
                .addRow(1, 2, new Location(0, 2), new Location(0, 3), 10)
                .addRow(2, 1, new Location(10, 15), new Location(10, 16), 5)
                .withName("No Unit :(")
                .withLength(0)
                .withWidth(20)
                .withSquare(1);

        Exception e = Assertions.assertThrows(IllegalArgumentException.class, warehouseBuilder::build);
        assertEquals("Length must be positive", e.getMessage());
    }


    @Test
    void testPlantGeneration() {

        WarehouseBuilder warehouseBuilder = new WarehouseBuilder()
                .addAgvDock(String.valueOf(1), new Location(5, 4), new Location(5, 5), new Location(6, 6), Accessibility.LENGHT_PLUS)
                .addAgvDock(String.valueOf(2), new Location(10, 4), new Location(10, 5), new Location(10, 6), Accessibility.WIDTH_MINUS)
                .addAisle(1, new Location(5, 1), new Location(16, 1), new Location(16, 1), Accessibility.LENGHT_PLUS)
                .addRow(1, 1, new Location(5, 1), new Location(7, 1), 3)
                .withName("No Unit :(")
                .withLength(20)
                .withWidth(20)
                .withSquare(1)
                .withUnit("m");


        Warehouse warehouse = warehouseBuilder.build();

        String plantExpected =
                "|  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  |\n" +
                "|  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  |\n" +
                "|  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  |\n" +
                "|  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  |\n" +
                "|  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  |\n" +
                "|  ||R1||  ||  ||D1||D1||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  |\n" +
                "|  ||A1||  ||  ||  ||  ||D1||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  |\n" +
                "|  ||R1||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  |\n" +
                "|  ||A1||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  |\n" +
                "|  ||A1||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  |\n" +
                "|  ||A1||  ||  ||D2||D2||D2||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  |\n" +
                "|  ||A1||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  |\n" +
                "|  ||A1||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  |\n" +
                "|  ||A1||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  |\n" +
                "|  ||A1||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  |\n" +
                "|  ||A1||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  |\n" +
                "|  ||A1||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  |\n" +
                "|  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  |\n" +
                "|  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  |\n" +
                "|  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  ||  |\n";


        String[][] plant = warehouse.generatePlant();
        StringBuilder plantString = new StringBuilder();
        //transforms the plant into a string
        for (int i = 0; i < plant.length - 1; i++) {
            for (int j = 0; j < plant[i].length - 1; j++) {
                plantString.append(plant[i][j]);
            }
            plantString.append("\n");
        }

        assertEquals(plantExpected, plantString.toString());

    }
}