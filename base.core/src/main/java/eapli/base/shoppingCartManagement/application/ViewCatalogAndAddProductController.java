package eapli.base.shoppingCartManagement.application;

import eapli.base.catalogmanagement.application.SearchCatalogService;
import eapli.base.productmanagement.dto.ProductDTO;
import eapli.base.servers.EstablishConnectionService;
import eapli.base.shoppingCartManagement.domain.ShoppingCart;
import eapli.base.shoppingCartManagement.dto.ShoppingCartDto;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserSession;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@UseCaseController
public class ViewCatalogAndAddProductController {
    private final AuthorizationService authorizationService = AuthzRegistry.authorizationService();
    private final SearchCatalogService searchCatalogService = new SearchCatalogService();
    private final VerifyUserIntegrityService verifyUserIntegrityService = new VerifyUserIntegrityService();
    private final EstablishConnectionService connectionService = new EstablishConnectionService();

    private Optional<SystemUser> user;


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

    public boolean handleCustomer() {
        try {
            authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.CLIENT_USER, BaseRoles.POWER_USER);
            this.user = authorizationService.session().filter(userSession -> userSession.authenticatedUser().identity().equals(userSession.authenticatedUser().identity())).map(UserSession::authenticatedUser);

            if (!verifyIfCustomerHasAShoppingCart()) {
                verifyUserIntegrityService.createShoppingCartForGivenCustomer(this.user.get().email().toString(), this.user.get().name().firstName(), this.user.get().name().lastName());
            }
            return true;

        } catch (Exception e) {
            //TEST PURPOSES
            return false;
        }

    }

    private boolean verifyIfCustomerHasAShoppingCart() {
        return searchCatalogService.verifyCustomer(this.user.get().email().toString(),
                this.user.get().name().firstName(), this.user.get().name().lastName());
    }

    public boolean addProductListToCart(Map<ProductDTO, Integer> productList) {
        return searchCatalogService.addProductsToCart(productList, this.user.get().email().toString(), this.user.get().name().firstName(), this.user.get().name().lastName());
    }

    public List<String> estabilishConnectionWithRequest(byte request) {
        return connectionService.createConnectionWithTheTcpOrderServer(request);
    }

    public ShoppingCartDto getShoppingCart() {
        return searchCatalogService.getShoppingCart((this.user.get().email().toString()), this.user.get().name().firstName(), this.user.get().name().lastName()).toDTO();
    }

}
