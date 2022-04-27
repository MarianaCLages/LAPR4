package eapli.base.catalogmanagement.application;

import eapli.base.productmanagement.dto.ProductDTO;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

@UseCaseController
public class SearchCatalogController {

    private final AuthorizationService authorizationService = AuthzRegistry.authorizationService();
    private final SearchCatalogService searchCatalogService = new SearchCatalogService();


    public Iterable<ProductDTO> searchAllProducts() {
        return searchCatalogService.searchAllProducts();
    }


}
