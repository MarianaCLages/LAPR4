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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterProductUI extends AbstractUI {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterProductUI.class);
    private final RegisterProductController controller = new RegisterProductController();

    @Override
    protected boolean doShow() {
        boolean verifyCategory;
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
        Optional<String> codeString = Optional.empty();
        Optional<String> shortDescriptionString = Optional.empty();
        Optional<String> extendedDescriptionString = Optional.empty();
        Optional<String> technicalDescriptionString = Optional.empty();
        Optional<String> brandNameString = Optional.empty();
        Optional<String> referenceString = Optional.empty();
        Optional<Long> barcodeLong = Optional.empty();
        Optional<String> moneyString = Optional.empty();
        List<String> pathPhotoList = new ArrayList<>();
        Optional<String> productionCodeString = Optional.empty();

        if (!controller.verifyWarehouseImported()) {
            System.out.println("WARNING! Before trying to create a product, there must be a warehouse imported in the system! Please import the warehouse first and try again!\n");
            return false;
        }

        try {
            // Category
            do {
                try {
                    int i = 0;
                    System.out.println("\n### Category List ###");
                    for (CategoryDTO category : controller.getCategoryDTOList()) {
                        System.out.println(i + 1 + ". " + category.toString());
                        i++;
                    }

                    categoryOption = Console.readInteger("\nPlease select the category of the product from the list above:");

                    if (categoryOption == 0) {
                        throw new IllegalArgumentException("\nPlease enter a valid option! Don't enter the value 0");
                    } else if (categoryOption < 0) {
                        throw new IllegalArgumentException("\nPlease enter a valid option! Don't enter negative values!");
                    }

                    try {
                        CategoryDTO categoryDTO = controller.getCategoryDTOList().get(categoryOption);

                        if (categoryDTO == null) {
                            throw new Exception();
                        }

                    } catch (Exception ex) {
                        throw new IllegalArgumentException("\nPlease enter a valid category! Please choose one valid option from the list\n");
                    }

                    verifyCategory = true;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    verifyCategory = false;
                }
            } while (!verifyCategory);

            // Internal code
            do {
                try {
                    codeString = Optional.ofNullable((Console.readLine("\nPlease enter the internal code of the product: (Format: [4 letters].[5 digits])")));

                    Pattern pattern = Pattern.compile("[a-zA-Z]{4}.[0-9]{5}", Pattern.CASE_INSENSITIVE);
                    Matcher matcher = pattern.matcher(codeString.get());
                    boolean matchFound = matcher.find();

                    if (codeString.get().isEmpty() || !matchFound) {
                        throw new IllegalArgumentException("Invalid internal code! It can't be empty and must follow the specified format. Please, try again.");
                    }

                    verifyCode = true;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } while (!verifyCode);

            // Short description
            do {
                try {
                    shortDescriptionString = Optional.ofNullable(Console.readLine("\nPlease enter the short description of the product: (Max = 30 chars)"));

                    if (shortDescriptionString.get().isEmpty() || shortDescriptionString.get().length() > 30) {
                        throw new IllegalArgumentException("Invalid short description! It can't be empty nor have more than 30 chars. Please, try again.");
                    }

                    verifyShortDescription = true;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } while (!verifyShortDescription);

            // Extended description
            do {
                try {
                    extendedDescriptionString = Optional.ofNullable(Console.readLine("\nPlease enter the extended description of the product: (Min = 20 chars | Max = 100 chars)"));

                    if (extendedDescriptionString.get().isEmpty() || extendedDescriptionString.get().length() < 20 || extendedDescriptionString.get().length() > 100) {
                        throw new IllegalArgumentException("Invalid extended description! It can't be empty nor have less than 20 chars nor have more than 100 chars. Please, try again.");
                    }

                    verifyExtendedDescription = true;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } while (!verifyExtendedDescription);

            // Technical description
            do {
                try {
                    technicalDescriptionString = Optional.ofNullable(Console.readLine("\nPlease enter the technical description of the product:"));

                    if (technicalDescriptionString.get().isEmpty()) {
                        throw new IllegalArgumentException("Invalid technical description! It can't be empty. Please, try again.");
                    }

                    verifyTechnicalDescription = true;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } while (!verifyTechnicalDescription);

            // Brand name
            do {
                try {
                    brandNameString = Optional.ofNullable(Console.readLine("\nPlease enter the brand name of the product: (Max = 50 chars)"));

                    if (brandNameString.get().isEmpty() || brandNameString.get().length() > 50) {
                        throw new IllegalArgumentException("Invalid brand name! It can't be empty nor have more than 50 chars. Please, try again.");
                    }

                    verifyBrandName = true;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } while (!verifyBrandName);

            // Reference
            do {
                try {
                    referenceString = Optional.ofNullable(Console.readLine("\nPlease enter the reference of the product: (Max = 23 chars)"));

                    if (referenceString.get().isEmpty() || referenceString.get().length() > 23) {
                        throw new IllegalArgumentException("Invalid reference! It can't be empty nor have more than 23 chars. Please, try again.");
                    }

                    verifyReference = true;

                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } while (!verifyReference);

            // Barcode
            do {
                try {
                    barcodeLong = Optional.of(Console.readLong("\nPlease enter the barcode of the product: (Length: 12 digits)"));

                    Pattern pattern = Pattern.compile("[0-9]+", Pattern.CASE_INSENSITIVE);
                    Matcher matcher = pattern.matcher(barcodeLong.get().toString());
                    boolean matchFound = matcher.find();

                    if (barcodeLong.get().toString().isEmpty() || !matchFound) {
                        throw new IllegalArgumentException("Invalid barcode! It can't be empty nor have any letters. Please, try again.");
                    } else if ((barcodeLong.get().toString().length() != 12)) {
                        throw new IllegalArgumentException("Invalid barcode! It must have exactly 12 digits. Please, try again.");
                    }

                    verifyBarcode = true;

                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } while (!verifyBarcode);

            // Price
            do {
                try {
                    moneyString = Optional.ofNullable(Console.readLine("\nPlease enter the price of the product: (Format: [price value] EUR/USD)"));

                    if (moneyString.get().isEmpty()) {
                        throw new IllegalArgumentException("Invalid price! It can't be empty and must follow the specified format. Please, try again.");
                    }

                    verifyPrice = true;

                } catch (IllegalArgumentException e) {
                    if (e.getMessage() == null) {
                        System.out.println("Invalid price! It can't be empty and must follow the specified format. Please, try again.");
                    }
                }
            } while (!verifyPrice);

            // Photos
            do {
                try {
                    String photoPath = Console.readLine("\nPlease enter the photo's path of the product: (Valid formats: PNG, JPG and SVG)");

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

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } while (!verifyPhoto);

            // Production code
            boolean invalidOption;
            do {
                try {
                    String productionCodeOption = Console.readLine("\nDo you want to enter the production code of the product?");

                    if (productionCodeOption.equals("No") | productionCodeOption.equals("NO") | productionCodeOption.equals("no") | productionCodeOption.equals("N") | productionCodeOption.equals("n")) {
                        verifyProductionCode = false;
                    } else if (productionCodeOption.equals("Yes") | productionCodeOption.equals("YES") | productionCodeOption.equals("yes") | productionCodeOption.equals("Y") | productionCodeOption.equals("y")) {
                        productionCodeString = Optional.ofNullable(Console.readLine("Please enter the production code of the product: (Format: [4 letters].[5 digits])"));

                        Pattern pattern = Pattern.compile("[a-zA-Z]{4}.[0-9]{5}", Pattern.CASE_INSENSITIVE);
                        Matcher matcher = pattern.matcher(productionCodeString.get());
                        boolean matchFound = matcher.find();

                        if (productionCodeString.get().isEmpty() || !matchFound) {
                            throw new IllegalArgumentException("Invalid production code! It can't be empty and must follow the specified format. Please, try again.");
                        }

                        verifyProductionCode = true;
                    } else {
                        throw new IllegalArgumentException("Please enter a valid option! (Yes or No)");
                    }
                    invalidOption = true;

                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    invalidOption = false;
                }
            } while (!invalidOption);

            try {
                if (!verifyProductionCode) {
                    controller.registerProductWithoutProductionCode(categoryOption, Code.valueOf(codeString.get()), Description.valueOf(shortDescriptionString.get()), Description.valueOf(extendedDescriptionString.get()), Description.valueOf(technicalDescriptionString.get()), BrandName.valueOf(brandNameString.get()), Reference.valueOf(referenceString.get()), Barcode.valueOf(barcodeLong.get()), Money.valueOf(moneyString.get()), pathPhotoList);
                } else {
                    controller.registerProductWithProductionCode(categoryOption, Code.valueOf(codeString.get()), Description.valueOf(shortDescriptionString.get()), Description.valueOf(extendedDescriptionString.get()), Description.valueOf(technicalDescriptionString.get()), BrandName.valueOf(brandNameString.get()), Reference.valueOf(referenceString.get()), Barcode.valueOf(barcodeLong.get()), Money.valueOf(moneyString.get()), pathPhotoList, ProductionCode.valueOf(productionCodeString.get()));
                }

                System.out.println("\n\n### Product Created ###\n" + controller.getProductDTO().toString() + "\n\n### Product Location ###\n" + controller.getBinLocation() + "\n\nOperation success!\n");

            } catch (Exception e) {
                System.out.println("An error occurred while trying to register the product. Please, try again.\n");
            }

        } catch (final IntegrityViolationException ex) {
            LOGGER.error("Error performing the operation!", ex);
            System.out.println("The product inserted already exists.");
        }

        return false;
    }


    @Override
    public String headline() {
        return "Register a new Product";
    }
}

