package eapli.base.app.backoffice.console.presentation.warehouse;

import eapli.base.categorymanagement.application.RegisterCategoryController;
import eapli.base.warehousemanagement.application.ImportWarehouseController;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImportWarehousePlantUI extends AbstractUI {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImportWarehousePlantUI.class);
    private final ImportWarehouseController controller = new ImportWarehouseController();

    @Override
    protected boolean doShow() {
        try {
            if (controller.isImported()) {
                boolean choice;

                choice = Console.readBoolean("There is already a warehouse plant imported. Do you want to import another one? (Y/N)");

                if (choice) {
                    return importWarehouse();

                } else {
                    return false;
                }

            } else {
                return importWarehouse();
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }

    private boolean importWarehouse() {

        String path = Console.readNonEmptyLine("Pls enter the file path", "The file path cannot be null");

        if (controller.importWarehouse(path).isPresent()) {
            System.out.printf("The warehouse plant was imported successfully\n");
            return true;
        } else {
            System.out.println("Impossible to load the file!!! :(((((");
            return false;
        }
    }

    @Override
    public String headline() {
        return "Import Warehouse plant from a file";
    }
}
