package eapli.base.productmanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.domain.*;
import eapli.base.productmanagement.dto.ProductDTO;
import eapli.base.productmanagement.repositories.ProductRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Money;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.io.IOException;

@UseCaseController
public class RegisterProductController {

    private Product product;
    private final AuthorizationService authorizationService = AuthzRegistry.authorizationService();
    private final RegisterProductService registerProductService = new RegisterProductService();

    public Product registerProductWithoutProductionCode(final Code code, final Description shortDescription, final Description extendedDescription, final Description technicalDescription, final BrandName brandName, final Reference reference, final Barcode barcode, final Money price, final String photo) throws IOException {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.SALES_CLERK, BaseRoles.POWER_USER);

        this.product = registerProductService.registerProductWithoutProductionCode(code, shortDescription, extendedDescription, technicalDescription, brandName, reference, barcode, price, photo);

        return product;
    }

    public Product registerProductWithProductionCode(final Code code, final Description shortDescription, final Description extendedDescription, final Description technicalDescription, final BrandName brandName, final Reference reference, final Barcode barcode, final Money price, final String photo, final ProductionCode productionCode) throws IOException {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.SALES_CLERK, BaseRoles.POWER_USER);

        this.product = registerProductService.registerProductWithProductionCode(code, shortDescription, extendedDescription, technicalDescription, brandName, reference, barcode, price, photo, productionCode);

        return product;
    }

    public ProductDTO getProductDTO() {
        return product.toDTO();
    }
}
