package eapli.base.app.backoffice.console.presentation.product;

import eapli.base.productmanagement.application.RegisterProductController;
import eapli.base.productmanagement.domain.*;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Money;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class RegisterProductUI extends AbstractUI {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterProductUI.class);
    private final RegisterProductController controller = new RegisterProductController();

    @Override
    protected boolean doShow() {
        boolean verifyCode = false;
        boolean verifyShortDescription = false;
        boolean verifyExtendedDescription = false;
        boolean verifyTechnicalDescription = false;
        boolean verifyBrandName = false;
        boolean verifyReference = false;
        boolean verifyBarcode = false;
        boolean verifyPrice = false;
        boolean verifyPhoto = false;
        boolean verifyProductionCode = false;

        Code code = null;
        Description shortDescription = null;
        Description extendedDescription = null;
        Description technicalDescription = null;
        BrandName brandName = null;
        Reference reference = null;
        Barcode barcode = null;
        Money price = null;
        String photo = null;
        ProductionCode productionCode = null;

        try {
            do {
                try {
                    code = Code.valueOf(Console.readLine("Please enter the internal code of the product:"));

                    if (code.toString().isEmpty()) {
                        throw new IllegalArgumentException("Please don't enter an empty code!");
                    }

                    verifyCode = true;

                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    LOGGER.error(e.getMessage());
                }
            } while (!verifyCode);

            System.out.println();

            do {
                try {
                    shortDescription = Description.valueOf(Console.readLine("Please enter the short description of the product:"));

                    if (code.toString().isEmpty()) {
                        throw new IllegalArgumentException("Please don't enter an empty description!");
                    }

                    verifyShortDescription = true;

                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    LOGGER.error(e.getMessage());
                }
            } while (!verifyShortDescription);

            System.out.println();

            do {
                try {
                    extendedDescription = Description.valueOf(Console.readLine("Please enter the extended description of the product:"));

                    if (code.toString().isEmpty()) {
                        throw new IllegalArgumentException("Please don't enter an empty description!");
                    }

                    verifyExtendedDescription = true;

                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    LOGGER.error(e.getMessage());
                }
            } while (!verifyExtendedDescription);

            System.out.println();

            do {
                try {
                    technicalDescription = Description.valueOf(Console.readLine("Please enter the technical description of the product:"));

                    if (code.toString().isEmpty()) {
                        throw new IllegalArgumentException("Please don't enter an empty description!");
                    }

                    verifyTechnicalDescription = true;

                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    LOGGER.error(e.getMessage());
                }
            } while (!verifyTechnicalDescription);

            System.out.println();

            do {
                try {
                    brandName = BrandName.valueOf(Console.readLine("Please enter the brand name of the product:"));

                    if (code.toString().isEmpty()) {
                        throw new IllegalArgumentException("Please don't enter an empty brand name!");
                    }

                    verifyBrandName = true;

                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    LOGGER.error(e.getMessage());
                }
            } while (!verifyBrandName);

            System.out.println();

            do {
                try {
                    reference = Reference.valueOf(Console.readLine("Please enter the reference of the product:"));

                    if (code.toString().isEmpty()) {
                        throw new IllegalArgumentException("Please don't enter an empty reference!");
                    }

                    verifyReference = true;

                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    LOGGER.error(e.getMessage());
                }
            } while (!verifyReference);

            System.out.println();

            do {
                try {
                    barcode = Barcode.valueOf(Console.readLong("Please enter the barcode of the product:"));

                    if (code.toString().isEmpty()) {
                        throw new IllegalArgumentException("Please don't enter an empty barcode!");
                    }

                    verifyBarcode = true;

                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    LOGGER.error(e.getMessage());
                }
            } while (!verifyBarcode);

            System.out.println();

            do {
                try {
                    price = Money.valueOf(Console.readLine("Please enter the price of the product:"));

                    if (code.toString().isEmpty()) {
                        throw new IllegalArgumentException("Please don't enter an empty price!");
                    }

                    verifyPrice = true;

                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    LOGGER.error(e.getMessage());
                }
            } while (!verifyPrice);

            System.out.println();

            do {
                try {
                    photo = Console.readLine("Please enter the photo(s) path of the product: (Valid formats: PNG and JPG)");

                    if (code.toString().isEmpty()) {
                        throw new IllegalArgumentException("Please don't enter an empty path!");
                    }

                    verifyPhoto = true;

                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    LOGGER.error(e.getMessage());
                }
            } while (!verifyPhoto);

            System.out.println();

            try {
                productionCode = ProductionCode.valueOf(Console.readLine("Please enter the production code of the product:"));

                verifyProductionCode = true;

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                LOGGER.error(e.getMessage());
            }

            if (!verifyProductionCode) {
                controller.registerProductWithoutProductionCode(code, shortDescription, extendedDescription, technicalDescription, brandName, reference, barcode, price, photo);
            } else {
                controller.registerProductWithProductionCode(code, shortDescription, extendedDescription, technicalDescription, brandName, reference, barcode, price, photo, productionCode);
            }

            System.out.println("\nProduct created:\n" + controller.getProductDTO().toString() + "\n\nOperation success!\n");

        } catch (final IntegrityViolationException ex) {
            LOGGER.error("Error performing the operation!", ex);
            System.out.println("The product inserted already exists.");
        } catch (IOException ex) {
            LOGGER.error("Error performing the operation!", ex);
            System.out.println("There was an error with the photo path introduced.");
        }

        return false;
    }

    @Override
    public String headline() {
        return "Register a new Product";
    }
}
