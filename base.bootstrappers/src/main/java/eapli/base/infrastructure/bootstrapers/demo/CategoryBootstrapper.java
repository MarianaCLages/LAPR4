package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.categorymanagement.application.RegisterCategoryController;
import eapli.base.categorymanagement.domain.AlphaNumericCode;
import eapli.base.categorymanagement.domain.Category;
import eapli.framework.actions.Action;

import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.general.domain.model.Description;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CategoryBootstrapper implements Action {
    private static final Logger LOGGER = LogManager.getLogger(CategoryBootstrapper.class);

    private final RegisterCategoryController controller = new RegisterCategoryController();

    @Override
    public boolean execute() {

        final Description technologyDescription = Description.valueOf("Technology");
        final Description booksDescription = Description.valueOf("Books");
        final Description culinaryDescription = Description.valueOf("Culinary");
        final Description toysDescription = Description.valueOf("Toys");

        final AlphaNumericCode technologyCode = AlphaNumericCode.valueOf("T0001");
        final AlphaNumericCode booksCode = AlphaNumericCode.valueOf("B0002");
        final AlphaNumericCode culinaryCode = AlphaNumericCode.valueOf("C0003");
        final AlphaNumericCode toysCode = AlphaNumericCode.valueOf("T0004");

        register(technologyDescription,technologyCode);
        register(booksDescription,booksCode);
        register(culinaryDescription,culinaryCode);
        register(toysDescription,toysCode);

        return true;
    }

    private void register(final Description description,final AlphaNumericCode code) {
        try{
            //final var c = controller.registerCategory(description,code);
            Category c = controller.registerCategory(description,code);
            LOGGER.debug("»»» {}",c);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated category
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", "category");
            LOGGER.trace("Assuming existing record", e);
        }
    }

}
