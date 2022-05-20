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
        final Description videoGamesDescription = Description.valueOf("Video Games");
        final Description boardGamesDescription = Description.valueOf("Board Games");
        final Description hygieneDescription = Description.valueOf("Hygiene");
        final Description computersDescription = Description.valueOf("Computers");
        final Description schoolDescription = Description.valueOf("School");
        final Description hardwareDescription = Description.valueOf("Hardware");
        final Description softwareDescription = Description.valueOf("Software");
        final Description furnitureDescription = Description.valueOf("Furniture");
        final Description foodDescription = Description.valueOf("Food");
        final Description accessoriesDescription = Description.valueOf("Accessories");
        final Description vehiclesDescription = Description.valueOf("Vehicles");
        final Description petsDescription = Description.valueOf("Pets");


        final AlphaNumericCode technologyCode = AlphaNumericCode.valueOf("T0001");
        final AlphaNumericCode booksCode = AlphaNumericCode.valueOf("B0002");
        final AlphaNumericCode culinaryCode = AlphaNumericCode.valueOf("C0003");
        final AlphaNumericCode toysCode = AlphaNumericCode.valueOf("T0004");
        final AlphaNumericCode videoGamesCode = AlphaNumericCode.valueOf("V0005");
        final AlphaNumericCode boardGamesCode = AlphaNumericCode.valueOf("B0006");
        final AlphaNumericCode hygieneCode = AlphaNumericCode.valueOf("H0007");
        final AlphaNumericCode computerCode = AlphaNumericCode.valueOf("C0008");
        final AlphaNumericCode schoolCode = AlphaNumericCode.valueOf("S0009");
        final AlphaNumericCode hardwareCode = AlphaNumericCode.valueOf("H0010");
        final AlphaNumericCode softwareCode = AlphaNumericCode.valueOf("S0011");
        final AlphaNumericCode furnitureCode = AlphaNumericCode.valueOf("F0012");
        final AlphaNumericCode foodCode = AlphaNumericCode.valueOf("F0013");
        final AlphaNumericCode vehiclesCode = AlphaNumericCode.valueOf("V0014");
        final AlphaNumericCode accessoriesCode = AlphaNumericCode.valueOf("A0015");
        final AlphaNumericCode petsCode = AlphaNumericCode.valueOf("P0016");

        register(technologyDescription, technologyCode);
        register(booksDescription, booksCode);
        register(culinaryDescription, culinaryCode);
        register(toysDescription, toysCode);
        register(videoGamesDescription, videoGamesCode);
        register(boardGamesDescription, boardGamesCode);
        register(hygieneDescription, hygieneCode);
        register(computersDescription, computerCode);
        register(schoolDescription, schoolCode);
        register(hardwareDescription, hardwareCode);
        register(softwareDescription, softwareCode);
        register(furnitureDescription, furnitureCode);
        register(foodDescription, foodCode);
        register(vehiclesDescription, vehiclesCode);
        register(accessoriesDescription, accessoriesCode);
        register(petsDescription, petsCode);


        return true;
    }

    private void register(final Description description, final AlphaNumericCode code) {
        try {
            //final var c = controller.registerCategory(description,code);
            Category c = controller.registerCategory(description, code);
            LOGGER.debug("»»» {}", c);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated category
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", "category");
            LOGGER.trace("Assuming existing record", e);
        }
    }

}
