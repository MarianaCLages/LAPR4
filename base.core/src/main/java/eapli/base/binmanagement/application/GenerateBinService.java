package eapli.base.binmanagement.application;

import eapli.base.binmanagement.domain.Bin;
import eapli.base.binmanagement.domain.BinLocation;
import eapli.base.binmanagement.repositories.BinRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.application.AssignShelfService;
import eapli.base.productmanagement.repositories.ProductRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.warehousemanagement.DTO.ShelfDTO;
import eapli.base.warehousemanagement.repositories.WarehouseRepository;
import eapli.framework.application.ApplicationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

@ApplicationService
public class GenerateBinService {
    private Bin bin;

    private final SearchWarehouseService service = new SearchWarehouseService();
    private final AssignShelfService assignShelfService = new AssignShelfService(service.getWarehouse());
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final BinRepository binRepository = PersistenceContext.repositories().bins();

    public ShelfDTO generateBin(final Long productId) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.SALES_CLERK);

        ShelfDTO shelfDTO = assignShelfService.assignShelf();
        Bin bin = new Bin(BinLocation.valueOf(shelfDTO.shelf(), shelfDTO.row(), shelfDTO.aisle()), productId);
        binRepository.save(bin);

        return shelfDTO;
    }

}
