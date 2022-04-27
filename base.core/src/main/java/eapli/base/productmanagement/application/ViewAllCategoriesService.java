package eapli.base.productmanagement.application;

import eapli.base.categorymanagement.domain.Category;
import eapli.base.categorymanagement.dto.CategoryDTO;
import eapli.base.categorymanagement.repositories.CategoryRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.application.ApplicationService;

import java.util.ArrayList;
import java.util.List;

@ApplicationService
public class ViewAllCategoriesService {

    private final CategoryRepository categoryRepository = PersistenceContext.repositories().categories();

    public List<CategoryDTO> getAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();

        List<CategoryDTO> categoryDTOS = new ArrayList<>();

        for(Category category : categoryList) {
            categoryDTOS.add(category.toDTO());
        }

        return categoryDTOS;
    }

    public Long getCategoryId(CategoryDTO categoryDTO) {
        return categoryRepository.findByCodeAndReturnId(categoryDTO.getAlphaNumericCode());
    }
}
