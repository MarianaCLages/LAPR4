package eapli.base.shoppingCartManagement.application;

import eapli.base.catalogmanagement.application.SearchCatalogService;
import eapli.base.productmanagement.dto.ProductDTO;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.HashMap;
import java.util.List;

@UseCaseController
public class ViewCatalogAndAddProductController {
    private final AuthorizationService authorizationService = AuthzRegistry.authorizationService();
    private final SearchCatalogService searchCatalogService = new SearchCatalogService();


    public Iterable<ProductDTO> searchAllProducts(HashMap<String, List<String>> options) {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.SALES_CLERK,BaseRoles.POWER_USER);

        return searchCatalogService.searchAllProducts(options);
    }

    public Iterable<ProductDTO> searchAllProducts() {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.SALES_CLERK,BaseRoles.POWER_USER);

        return searchCatalogService.searchAllProducts();
    }

    public List<ProductDTO> prepareListToBeRepresented(List<ProductDTO> productDTOS, int option) {
        return searchCatalogService.prepareToBeRepresented(productDTOS,option);
    }




}
