package eapli.base.app.backoffice.console.presentation.product;

import eapli.base.categorymanagement.dto.CategoryDTO;
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
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterProductUI extends AbstractUI {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterProductUI.class);
    private final RegisterProductController controller = new RegisterProductController();

    @Override
    protected boolean doShow() {
        boolean verifyCategory = false;
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

        int categoryOption = 0;
        Code code = null;
        Description shortDescription = null;
        Description extendedDescription = null;
        Description technicalDescription = null;
        BrandName brandName = null;
        Reference reference = null;
        Barcode barcode = null;
        Money price = null;
        List<String> pathPhotoList = new ArrayList<>();
        ProductionCode productionCode = null;

        try {
            // Category
            do {
                try {
                    int i = 0;
                    for (CategoryDTO category : controller.getCategoryDTOList()) {
                        System.out.println(i + 1 + ". " + category.toString());
                        i++;
                    }

                    System.out.println();

                    categoryOption = Console.readInteger("Please select the category of the product from the list above:");

                    verifyCategory = true;

                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } while (!verifyCategory);

            System.out.println();

            // Internal code
            do {
                try {
                    code = Code.valueOf(Console.readLine("Please enter the internal code of the product: (Max = 23 chars | Format: [4 letters].[5 digits])"));

                    Pattern pattern = Pattern.compile("[a-zA-Z]{4}.[0-9]{5}", Pattern.CASE_INSENSITIVE);
                    Matcher matcher = pattern.matcher(code.toString());
                    boolean matchFound = matcher.find();

                    if (code.toString().isEmpty() || code.toString().length() > 23 || !matchFound) {
                        throw new IllegalArgumentException("Invalid internal code! It can't be empty nor have more than 23 chars and must follow the specified format. Please, try again.");
                    }

                    verifyCode = true;

                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } while (!verifyCode);

            System.out.println();

            // Short description
            do {
                try {
                    shortDescription = Description.valueOf(Console.readLine("Please enter the short description of the product: (Max = 30 chars)"));

                    if (shortDescription.toString().isEmpty() || shortDescription.length() > 30) {
                        throw new IllegalArgumentException("Invalid short description! It can't be empty nor have more than 30 chars. Please, try again.");
                    }

                    verifyShortDescription = true;

                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } while (!verifyShortDescription);

            System.out.println();

            // Extended description
            do {
                try {
                    extendedDescription = Description.valueOf(Console.readLine("Please enter the extended description of the product: (Min = 20 chars | Max = 100 chars)"));

                    if (extendedDescription.toString().isEmpty() || extendedDescription.length() < 20 || extendedDescription.length() > 100) {
                        throw new IllegalArgumentException("Invalid extended description! It can't be empty nor have less than 20 chars nor have more than 100 chars. Please, try again.");
                    }

                    verifyExtendedDescription = true;

                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } while (!verifyExtendedDescription);

            System.out.println();

            // Technical description
            do {
                try {
                    technicalDescription = Description.valueOf(Console.readLine("Please enter the technical description of the product:"));

                    if (technicalDescription.toString().isEmpty()) {
                        throw new IllegalArgumentException("Invalid technical description! It can't be empty. Please, try again.");
                    }

                    verifyTechnicalDescription = true;

                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } while (!verifyTechnicalDescription);

            System.out.println();

            // Brand name
            do {
                try {
                    brandName = BrandName.valueOf(Console.readLine("Please enter the brand name of the product: (Max = 50 chars)"));

                    if (brandName.toString().isEmpty() || brandName.toString().length() > 50)  {
                        throw new IllegalArgumentException("Invalid brand name! It can't be empty nor have more than 50 chars. Please, try again.");
                    }

                    verifyBrandName = true;

                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } while (!verifyBrandName);

            System.out.println();

            // Reference
            do {
                try {
                    reference = Reference.valueOf(Console.readLine("Please enter the reference of the product: (Max = 23 chars)"));

                    if (reference.toString().isEmpty() || reference.toString().length() > 23) {
                        throw new IllegalArgumentException("Invalid reference! It can't be empty nor have more than 23 chars. Please, try again.");
                    }

                    verifyReference = true;

                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } while (!verifyReference);

            System.out.println();

            // Barcode
            do {
                try {
                    barcode = Barcode.valueOf(Console.readLong("Please enter the barcode of the product:"));

                    if (barcode.toString().isEmpty()) {
                        throw new IllegalArgumentException("Invalid barcode! It can't be empty nor have any letters. Please, try again.");
                    }

                    verifyBarcode = true;

                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } while (!verifyBarcode);

            System.out.println();

            // Price
            do {
                try {
                    price = Money.valueOf(Console.readLine("Please enter the price of the product: (Format: [price value] EUR/USD)"));

                    if (price.toString().isEmpty()) {
                        throw new IllegalArgumentException("Invalid price! It can't be empty and must follow the specified format. Please, try again.");
                    }

                    verifyPrice = true;

                } catch (IllegalArgumentException e) {
                    if (e.getMessage() == null) {
                        System.out.println("Invalid price! It can't be empty and must follow the specified format. Please, try again.");
                    }
                }
            } while (!verifyPrice);

            System.out.println();

            // Photos
            do {
                try {
                    String photoPath = Console.readLine("Please enter the photo's path of the product: (Valid formats: PNG, JPG and SVG)");

                    if (controller.validateAndVerifyPath(photoPath)) {

                        boolean invalidOption;

                        do {
                            try {
                                String addPhoto = Console.readLine("Do you wish to enter another photo?");

                                if (addPhoto.equals("No") | addPhoto.equals("NO") | addPhoto.equals("no") | addPhoto.equals("N") | addPhoto.equals("n")) {
                                    verifyPhoto = true;
                                    pathPhotoList.add(photoPath);
                                } else if (addPhoto.equals("Yes") | addPhoto.equals("YES") | addPhoto.equals("yes") | addPhoto.equals("Y") | addPhoto.equals("y")) {
                                    verifyPhoto = false;
                                    pathPhotoList.add(photoPath);
                                } else {
                                    throw new IllegalArgumentException("Please enter a valid option! (Yes or No)");
                                }

                                invalidOption = true;
                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                                invalidOption = false;
                            }
                        } while (!invalidOption);
                    }

                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } while (!verifyPhoto);

            System.out.println();

            // Production code
            try {
                String productionCodeOption = Console.readLine("Do you want to enter the production code of the product?");

                if (productionCodeOption.equals("No") | productionCodeOption.equals("NO") | productionCodeOption.equals("no") | productionCodeOption.equals("N") | productionCodeOption.equals("n")) {
                    verifyProductionCode = false;
                } else if (productionCodeOption.equals("Yes") | productionCodeOption.equals("YES") | productionCodeOption.equals("yes") | productionCodeOption.equals("Y") | productionCodeOption.equals("y")) {
                    productionCode = ProductionCode.valueOf(Console.readLine("Please enter the production code of the product: (Max = 23 chars | Format: [4 letters].[5 digits])"));

                    Pattern pattern = Pattern.compile("[a-zA-Z]{4}.[0-9]{5}", Pattern.CASE_INSENSITIVE);
                    Matcher matcher = pattern.matcher(productionCode.toString());
                    boolean matchFound = matcher.find();

                    if (code.toString().isEmpty() || code.toString().length() > 23 || !matchFound) {
                        throw new IllegalArgumentException("Invalid production code! It can't be empty nor have more than 23 chars and must follow the specified format. Please, try again.");
                    }

                    verifyProductionCode = true;
                } else {
                    throw new IllegalArgumentException("Please enter a valid option! (Yes or No)");
                }

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

            if (!verifyProductionCode) {
                controller.registerProductWithoutProductionCode(categoryOption, code, shortDescription, extendedDescription, technicalDescription, brandName, reference, barcode, price, pathPhotoList);
            } else {
                controller.registerProductWithProductionCode(categoryOption, code, shortDescription, extendedDescription, technicalDescription, brandName, reference, barcode, price, pathPhotoList, productionCode);
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
