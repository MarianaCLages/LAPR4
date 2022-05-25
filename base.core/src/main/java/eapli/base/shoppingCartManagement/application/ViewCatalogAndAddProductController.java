package eapli.base.shoppingCartManagement.application;

import eapli.base.catalogmanagement.application.SearchCatalogService;
import eapli.base.productmanagement.dto.ProductDTO;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserSession;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@UseCaseController
public class ViewCatalogAndAddProductController {
    private final AuthorizationService authorizationService = AuthzRegistry.authorizationService();
    private final SearchCatalogService searchCatalogService = new SearchCatalogService();


    public Iterable<ProductDTO> searchAllProducts(HashMap<String, List<String>> options) {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.CLIENT_USER, BaseRoles.POWER_USER);

        return searchCatalogService.searchAllProducts(options);
    }

    public Iterable<ProductDTO> searchAllProducts() {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.CLIENT_USER, BaseRoles.POWER_USER);

        return searchCatalogService.searchAllProducts();
    }

    public List<ProductDTO> prepareListToBeRepresented(List<ProductDTO> productDTOS, int option) {
        return searchCatalogService.prepareToBeRepresented(productDTOS, option);
    }

    public boolean verifyIfCustomerHasAShoppingCart() {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.CLIENT_USER, BaseRoles.POWER_USER);
        Optional<SystemUser> userName = authorizationService.session().filter(userSession -> userSession.authenticatedUser().identity().equals(userSession.authenticatedUser().identity())).map(UserSession::authenticatedUser);

        Optional<String> userEmail;

        if (userName.isPresent()) {
            userEmail = Optional.ofNullable(String.valueOf(userName.get().email()));
        } else {
            return false;
        }

        searchCatalogService.verifiyShoppingCart(userEmail.get());
        return false;

    }


}
