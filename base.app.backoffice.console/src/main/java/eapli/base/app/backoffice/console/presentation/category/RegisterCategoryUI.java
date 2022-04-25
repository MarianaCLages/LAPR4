
package eapli.base.app.backoffice.console.presentation.category;

import eapli.base.categorymanagement.application.RegisterCategoryController;
import eapli.base.categorymanagement.domain.AlphaNumericCode;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.general.domain.model.Description;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.validations.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegisterCategoryUI extends AbstractUI {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterCategoryUI.class);
    private final RegisterCategoryController controller = new RegisterCategoryController();

    @Override
    protected boolean doShow() {

        boolean verifyDescription = false;
        boolean verifyAlphaNumericCode = false;

        AlphaNumericCode code = null;
        Description description = null;

        try {

            do {
                try {
                    code = AlphaNumericCode.valueOf(Console.readLine("Please enter the alpha numeric code of the category"));

                    if (code.toString().isEmpty()) {
                        throw new IllegalArgumentException("Please don't enter a empty code!");
                    }

                    verifyAlphaNumericCode = true;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    LOGGER.error(e.getMessage());
                    verifyAlphaNumericCode = false;
                }
            } while (!verifyAlphaNumericCode);

            do {
                try {
                    description = Description.valueOf(Console.readLine("Please enter the desired description for the category"));

                    if (code.toString().isEmpty()) {
                        throw new IllegalArgumentException("Please don't enter a empty description!");
                    }

                    verifyDescription = true;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    LOGGER.error(e.getMessage());
                    verifyDescription = false;
                }
            } while (!verifyDescription);

            controller.registerCategory(description, code);

            System.out.println("\nCategory created :\n" + controller.getCategoryDTO().toString() + "\n\nOperation success!\n");

        } catch (final IntegrityViolationException ex) {
            LOGGER.error("Error performing the operation", ex);
            System.out.println("There is already a category with that kind of information!");

        }

        return false;
    }

    @Override
    public String headline() {
        return "Register Product Category";
    }

}
