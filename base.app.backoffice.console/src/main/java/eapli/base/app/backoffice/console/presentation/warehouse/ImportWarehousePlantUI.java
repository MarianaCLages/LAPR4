package eapli.base.app.backoffice.console.presentation.warehouse;

import eapli.base.warehousemanagement.application.ImportWarehouseController;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ImportWarehousePlantUI extends AbstractUI {

    private static final Logger logger = LoggerFactory.getLogger(ImportWarehousePlantUI.class);
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
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    private boolean importWarehouse() {

        String path = Console.readNonEmptyLine("Please enter the file path:", "The file path cannot be null");

        try {
            if (controller.importWarehouse(path).isPresent()) {
                System.out.println("The warehouse plant was imported successfully");
                return true;
            } else {
                System.out.println("Impossible to load the file!!! :(((((");
                return false;
            }
        } catch (IOException e) {
            System.out.println("WARNING!!! Error while loading the file, please check the file location");
            return false;
        } catch (ParseException e) {
            System.out.println("WARNING!!! Error reading the warehouse plant, please check the file");
            return false;
        }
    }

    @Override
    public String headline() {
        return "Import Warehouse plant from a file";
    }
}
