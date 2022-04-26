package eapli.base.warehousemanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.warehousemanagement.application.importservice.FileFormat;
import eapli.base.warehousemanagement.application.importservice.ImportWarehouseFromFile;
import eapli.base.warehousemanagement.application.importservice.ImportWarehouseServiceFactory;
import eapli.base.warehousemanagement.domain.Warehouse;
import eapli.base.warehousemanagement.repositories.WarehouseRepository;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import org.apache.commons.io.FilenameUtils;

import java.util.Optional;

@UseCaseController
public class ImportWarehouseController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ImportWarehouseServiceFactory factory = new ImportWarehouseServiceFactory();
    private final WarehouseRepository warehouseRepository = PersistenceContext.repositories().warehouseRepository();

    public Optional<Warehouse> importWarehouse(String filePath) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN, BaseRoles.WAREHOUSE_EMPLOYEE);

        FileFormat fileFormat = FileFormat.fromString(FilenameUtils.getExtension(filePath));

        final ImportWarehouseFromFile importService = factory.build(fileFormat);
        Optional<Warehouse> warehouse = importService.importWarehouse(filePath);
        System.out.println(warehouse.get().identity());

        if (warehouse.isPresent()) {
            warehouseRepository.save(warehouse.get());

            return warehouse;
        } else {
            return Optional.empty();
        }
    }
}