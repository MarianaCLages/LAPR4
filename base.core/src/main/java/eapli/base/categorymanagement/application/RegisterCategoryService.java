package eapli.base.categorymanagement.application;

import eapli.base.categorymanagement.domain.AlphaNumericCode;
import eapli.base.categorymanagement.domain.Category;
import eapli.base.categorymanagement.repositories.CategoryRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.application.ApplicationService;
import eapli.framework.general.domain.model.Description;

@ApplicationService
public class RegisterCategoryService {

    private final CategoryRepository categoryRepository = PersistenceContext.repositories().categories();

    public Category registerCategory(final Description description, final AlphaNumericCode code) {
        return categoryRepository.save(new Category(description,code));
    }

}
