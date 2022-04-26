package eapli.base.warehousemanagement.application.importservice;

public class ImportWarehouseServiceFactory {

    public ImportWarehouseFromFile build(FileFormat format) {
        switch (format) {
            case CSV:
                throw new IllegalArgumentException("CSV not implemented yet");
            case JSON:
                return new ImportWarehouseFromJSON();
            //return new ImportWarehouseFromJSON();
            case XML:
                throw new IllegalArgumentException("XML not supported yet");
                //return new ImportWarehouseFromXML();
            default:
                throw new IllegalArgumentException("Unknown file format");
        }
    }

}