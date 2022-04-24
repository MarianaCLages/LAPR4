package eapli.base.categorymanagement.application;

import eapli.base.categorymanagement.domain.AlphaNumericCode;
import eapli.base.categorymanagement.domain.Category;
import eapli.base.categorymanagement.repositories.CategoryRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.general.domain.model.Description;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

@UseCaseController
public class RegisterCategoryController {

    private final CategoryRepository categoryRepository = PersistenceContext.repositories().categories();
    private final AuthorizationService authorizationService = AuthzRegistry.authorizationService();

    public Category registerCategory(final Description description, final AlphaNumericCode code) {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.SALES_CLERK,BaseRoles.POWER_USER);

        return categoryRepository.save(new Category(description,code));
    }

}
