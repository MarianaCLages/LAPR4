package eapli.base.productmanagement.application;

import eapli.base.categorymanagement.domain.Category;
import eapli.base.categorymanagement.repositories.CategoryRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.application.ApplicationService;

import java.util.List;

@ApplicationService
public class ViewAllCategoriesService {

    private final CategoryRepository categoryRepository = PersistenceContext.repositories().categories();

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
