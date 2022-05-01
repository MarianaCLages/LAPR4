package eapli.base.infrastructure.smoketests;

import eapli.base.categorymanagement.application.RegisterCategoryController;
import eapli.base.categorymanagement.domain.AlphaNumericCode;
import eapli.base.categorymanagement.dto.CategoryDTO;
import eapli.base.productmanagement.application.ViewAllCategoriesService;
import eapli.framework.actions.Action;
import eapli.framework.general.domain.model.Description;
import org.slf4j.Logger;

import java.util.List;

public class CategorySmokeTester implements Action {

    Logger logger = org.slf4j.LoggerFactory.getLogger(CategorySmokeTester.class);
    RegisterCategoryController controller = new RegisterCategoryController();
    ViewAllCategoriesService viewAllCategoriesService = new ViewAllCategoriesService();

    @Override
    public boolean execute() {
        createASimpleCategory();
        return false;
    }

    private void createASimpleCategory() {

        Description description = Description.valueOf("School Supplies");
        AlphaNumericCode code = AlphaNumericCode.valueOf("SCHOOL");

        controller.registerCategory(description, code);

        List<CategoryDTO> list = viewAllCategoriesService.getAllCategories();

        if (list.stream().noneMatch(c -> c.getAlphaNumericCode().equals(code.toString()))) {
            throw new RuntimeException("Error on Category Test");
        }


        logger.info("»»» Category");

    }
}
