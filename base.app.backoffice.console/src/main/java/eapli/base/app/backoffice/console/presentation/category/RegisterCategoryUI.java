
package eapli.base.app.backoffice.console.presentation.category;

import eapli.base.categorymanagement.application.RegisterCategoryController;
import eapli.base.categorymanagement.domain.AlphaNumericCode;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.general.domain.model.Description;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class RegisterCategoryUI extends AbstractUI {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterCategoryUI.class);
    private final RegisterCategoryController controller = new RegisterCategoryController();

    @Override
    protected boolean doShow() {

        boolean verifyDescription;
        boolean verifyAlphaNumericCode;

        Optional<String> alphaCode = Optional.empty();
        Optional<String> description = Optional.empty();

        try {
            do {
                try {
                    alphaCode = Optional.ofNullable(Console.readLine("Please enter the alpha numeric code of the category (10 chars maximum)"));

                    if (alphaCode.toString().isEmpty() || !alphaCode.isPresent()) {
                        throw new IllegalArgumentException("Please don't enter an empty code!");
                    } else if (alphaCode.get().length() > 10) {
                        throw new IllegalArgumentException("Notice, the maximum length for the category code is 10 characters!");
                    }

                    verifyAlphaNumericCode = true;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    verifyAlphaNumericCode = false;
                }
            } while (!verifyAlphaNumericCode);

            do {
                try {
                    description = Optional.ofNullable(Console.readLine("Please enter the desired description for the category (20 chars minimum | 50 chars maximum)"));

                    if (description.toString().isEmpty() || !description.isPresent()) {
                        throw new IllegalArgumentException("Please don't enter an empty description!");
                    } else if (description.get().length() > 50) {
                        throw new IllegalArgumentException("Notice, the maximum length for the category description is 50 characters!");
                    } else if (description.get().length() < 20) {
                        throw new IllegalArgumentException("Notice, the minimum length for the category description is 20 characters!");
                    }

                    verifyDescription = true;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    verifyDescription = false;
                }
            } while (!verifyDescription);

            controller.registerCategory(Description.valueOf(description.get()), AlphaNumericCode.valueOf(alphaCode.get()));

            System.out.println("\nCategory created :\n" + controller.getCategoryDTO().toString() + "\n\nOperation success!\n");

        } catch (final IntegrityViolationException ex) {
            System.out.println("There is already a category with that kind of information!");

        }

        return false;
    }

    @Override
    public String headline() {
        return "Register Product Category";
    }

}
