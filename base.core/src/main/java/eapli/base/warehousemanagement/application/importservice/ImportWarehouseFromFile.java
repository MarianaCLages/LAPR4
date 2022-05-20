package eapli.base.warehousemanagement.application.importservice;


import eapli.base.warehousemanagement.domain.Warehouse;
import eapli.framework.application.ApplicationService;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Optional;

@ApplicationService
public interface ImportWarehouseFromFile {

    Optional<Warehouse> importWarehouse(String path) throws IOException, ParseException;

}
