package eapli.base.categorymanagement.application;

import eapli.base.categorymanagement.domain.AlphaNumericCode;
import eapli.base.categorymanagement.domain.Category;
import eapli.base.categorymanagement.dto.CategoryDTO;
import eapli.base.categorymanagement.repositories.CategoryRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.general.domain.model.Description;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

@UseCaseController
public class RegisterCategoryController {

    private final AuthorizationService authorizationService = AuthzRegistry.authorizationService();
    private final RegisterCategoryService registerCategoryService = new RegisterCategoryService();
    private Category category;

    public Category registerCategory(final Description description, final AlphaNumericCode code) {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.SALES_CLERK, BaseRoles.POWER_USER);

        this.category = registerCategoryService.registerCategory(description, code);

        return category;
    }

    public CategoryDTO getCategoryDTO() {
        return category.toDTO();
    }

}
