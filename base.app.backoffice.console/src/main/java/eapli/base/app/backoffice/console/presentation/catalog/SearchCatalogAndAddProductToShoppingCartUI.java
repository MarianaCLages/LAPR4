package eapli.base.app.backoffice.console.presentation.catalog;

import eapli.base.categorymanagement.domain.AlphaNumericCode;
import eapli.base.productmanagement.dto.ProductDTO;
import eapli.base.shoppingCartManagement.application.ViewCatalogAndAddProductController;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchCatalogAndAddProductToShoppingCartUI extends AbstractUI {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchCatalogUI.class);
    private final ViewCatalogAndAddProductController controller = new ViewCatalogAndAddProductController();

    @Override
    protected boolean doShow() {
        List<String> list = new ArrayList<>();

        Iterable<ProductDTO> productDTOS = new ArrayList<>();

        boolean filterOption = false;

        List<String> filterOptions = new ArrayList<>();
        filterOptions.add("Filter by brand");
        filterOptions.add("Filter by reference");
        filterOptions.add("Filter by barcode");
        filterOptions.add("Filter by category");
        filterOptions.add("Filter by description");

        int option = 0;
        int index = 1;
        String addMoreOptions;
        boolean addMoreOptionsBool = false;
        boolean filterOrNot = false;

        int filterOrNotInt = 0;

        HashMap<String, List<String>> optionsChosen = new HashMap<>();

        do {
            try {
                filterOrNotInt = Console.readInteger("\n> 1 - Filter the catalog\n> 2 - See all products without filter");

                if (filterOrNotInt == 2) {
                    filterOrNot = false;
                } else if (filterOrNotInt == 1) {
                    filterOrNot = true;
                } else {
                    throw new IllegalArgumentException("\nPlease enter a valid option! (1/2)");
                }

                addMoreOptionsBool = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                addMoreOptionsBool = false;
            }

        } while (!addMoreOptionsBool);

        boolean toBeRepresented = true;

        if (filterOrNot) {

            do {
                try {
                    do {
                        if (!filterOptions.isEmpty()) {
                            System.out.println("\n\n## Filter options ##");
                            for (String s : filterOptions) {
                                System.out.println("> " + index + " - " + s);
                                index++;
                            }

                            boolean optionFailed = false;
                            option = 0;

                            do {
                                try {
                                    option = Console.readInteger("\nPlease enter one of the valid options!");

                                    if (option < 0 || option > (index - 1)) {
                                        throw new IllegalArgumentException("\nPlease enter a valid option! (Neither can the option be negative or have a higher value than the represented in the options!");
                                    }

                                    optionFailed = true;

                                } catch (IllegalArgumentException e) {
                                    System.out.println(e.getMessage());
                                    optionFailed = false;
                                }

                            } while (!optionFailed);

                            boolean invalidInputOptions = false;

                            do {
                                try {
                                    addMoreOptions = Console.readLine("\nDo you wish to add more options?");

                                    if (addMoreOptions.equals("No") | addMoreOptions.equals("NO") | addMoreOptions.equals("no") | addMoreOptions.equals("N") | addMoreOptions.equals("n")) {
                                        addMoreOptionsBool = true;
                                        optionsChosen.put(filterOptions.get(option - 1), new ArrayList<>());
                                        filterOptions.remove(option - 1);
                                        index = 1;
                                    } else if (addMoreOptions.equals("Yes") | addMoreOptions.equals("YES") | addMoreOptions.equals("yes") | addMoreOptions.equals("Y") | addMoreOptions.equals("y")) {
                                        addMoreOptionsBool = false;
                                        optionsChosen.put(filterOptions.get(option - 1), new ArrayList<>());
                                        filterOptions.remove(option - 1);
                                        index = 1;
                                    } else {
                                        throw new IllegalArgumentException("Please enter a valid option!! (Yes or No)");
                                    }

                                    invalidInputOptions = true;
                                } catch (IllegalArgumentException e) {
                                    System.out.println(e.getMessage());
                                    invalidInputOptions = false;
                                }

                            } while (!invalidInputOptions);

                        } else {
                            System.out.println("\nThere is no more options to add!\n");
                            addMoreOptionsBool = true;
                        }

                    } while (!addMoreOptionsBool);

                    filterOption = true;

                } catch (Exception e) {
                    filterOption = false;
                    filterOptions = new ArrayList<>();
                    filterOptions.add("Filter by brand");
                    filterOptions.add("Filter by reference");
                    filterOptions.add("Filter by barcode");
                    filterOptions.add("Filter by category");
                    filterOptions.add("Filter by description");
                    index = 1;
                    optionsChosen = new HashMap<>();
                    LOGGER.error(e.getMessage());
                }
            } while (!filterOption);

            boolean addOption = false;

            for (String s : optionsChosen.keySet()) {
                switch (s) {
                    case "Filter by brand":
                        do {
                            try {
                                String brandInfo = Console.readLine("\nPlease enter a brand name:");

                                if (brandInfo.isEmpty())
                                    throw new IllegalArgumentException("Please enter a brand name!");

                                optionsChosen.get("Filter by brand").add(brandInfo);
                                addOption = true;
                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                                addOption = false;
                            }

                        } while (!addOption);

                        break;
                    case "Filter by reference":
                        do {
                            try {
                                String referenceInfo = Console.readLine("\nPlease enter a reference: ");

                                if (referenceInfo.isEmpty())
                                    throw new IllegalArgumentException("Please enter a valid input (No empty characters are allowed!");

                                optionsChosen.get("Filter by reference").add(referenceInfo);
                                addOption = true;
                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                                addOption = false;
                            }

                        } while (!addOption);

                        break;
                    case "Filter by barcode":
                        do {
                            try {
                                int barcodeInfoLong = 0;
                                barcodeInfoLong = Console.readInteger("\nPlease enter a barcode: (NOTICE: A barcode must only contain numbers!) ");

                                if (barcodeInfoLong == 0 || barcodeInfoLong < 0 || barcodeInfoLong > 999999999)
                                    throw new IllegalArgumentException("Please enter a valid barcode (Don't leave the input empty, enter a )!");

                                optionsChosen.get("Filter by barcode").add(String.valueOf(barcodeInfoLong));
                                addOption = true;
                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                                addOption = false;
                            }

                        } while (!addOption);
                        break;

                    case "Filter by category":
                        do {
                            try {
                                String alphaCode = Console.readLine("\nPlease enter the AlphaNumeric code of the category: ");
                                AlphaNumericCode code = AlphaNumericCode.valueOf(alphaCode);

                                if (code == null || alphaCode.isEmpty())
                                    throw new IllegalArgumentException("Please enter a valid alpha numeric code!!");

                                optionsChosen.get("Filter by category").add(alphaCode);
                                addOption = true;
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                                addOption = false;
                            }

                        } while (!addOption);
                        break;
                    case "Filter by description":

                        option = 0;

                        do {
                            try {
                                System.out.println("\n### Description types ###");
                                filterOrNotInt = Console.readInteger(">1 - Short Description\n>2 - Extended Description\n>3 - Technical Description");

                                if (filterOrNotInt == 2) {
                                    option = 2;
                                } else if (filterOrNotInt == 1) {
                                    option = 1;
                                } else if (filterOrNotInt == 3) {
                                    option = 3;
                                } else {
                                    throw new IllegalArgumentException("Please enter a valid option! (1/2/3)");
                                }

                                addMoreOptionsBool = true;
                            } catch (Exception e) {
                                System.out.println("Please enter a valid option! (1/2/3)");
                                addMoreOptionsBool = false;
                            }

                        } while (!addMoreOptionsBool);

                        String typeDescription;

                        if (option == 1) typeDescription = "short";
                        else if (option == 2) typeDescription = "extended";
                        else typeDescription = "technical";

                        do {
                            try {
                                String description = Console.readLine("\nPlease enter the " + typeDescription + " description: ");

                                if (description.isEmpty())
                                    throw new IllegalArgumentException("Please enter a valid description!");

                                optionsChosen.get("Filter by description").add(description);
                                addOption = true;
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                                addOption = false;
                            }

                        } while (!addOption);

                        break;
                }
            }

            try {
                productDTOS = controller.searchAllProducts(optionsChosen);
            } catch (Exception e) {
                System.out.println("\nThere is no product available fitting your desired options!");
                toBeRepresented = false;
            }


        } else {
            try {

                //list = controller.estabilishConnectionWithRequest((byte) 3);
                //list.forEach(x -> System.out.println(x));

                productDTOS = controller.searchAllProducts();
            } catch (Exception e) {
                System.out.println("\nThere is no product available fitting your desired options!");
                toBeRepresented = false;
            }

        }

        if (((List<ProductDTO>) productDTOS).isEmpty()) {
            System.out.println("\nPlease run the UI again and choose other options!\n");
            return false;
        }

        if (toBeRepresented) {
            boolean presentationOrderOrNot = false;

            do {
                try {
                    filterOrNotInt = Console.readInteger("\n>1 - Change the presentation order of the products catalog\n>2 - Show the products catalog without a presentation order");

                    if (filterOrNotInt == 2) {
                        presentationOrderOrNot = false;
                    } else if (filterOrNotInt == 1) {
                        presentationOrderOrNot = true;
                    } else {
                        throw new IllegalArgumentException("Please enter a valid option! (1/2)");
                    }

                    addMoreOptionsBool = true;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    addMoreOptionsBool = false;
                }

            } while (!addMoreOptionsBool);

            if (presentationOrderOrNot) {
                List<String> presentationOrderOptions = new ArrayList<>();
                presentationOrderOptions.add("Sort the brand name in ascending order");
                presentationOrderOptions.add("Sort the brand name in descending order");
                presentationOrderOptions.add("Sort the reference in ascending order");
                presentationOrderOptions.add("Sort the reference in descending order");
                presentationOrderOptions.add("Sort the category in ascending order");
                presentationOrderOptions.add("Sort the category in descending order");
                presentationOrderOptions.add("Sort the short description in ascending order");
                presentationOrderOptions.add("Sort the short description in descending order");

                index = 1;
                option = 0;

                do {
                    try {
                        do {
                            if (!presentationOrderOptions.isEmpty()) {
                                System.out.println("\n\n## Presentation order ##");
                                for (String s : presentationOrderOptions) {
                                    System.out.println("> " + index + " - " + s);
                                    index++;
                                }

                                boolean optionFailed = false;

                                do {
                                    try {
                                        option = Console.readInteger("\nPlease enter one of the valid options!");

                                        if (option < 0 || option > (index - 1)) {
                                            throw new IllegalArgumentException("Please enter a valid option! (Neither can the option be negative or have a higher value than the represented in the options!");
                                        }

                                        optionFailed = true;

                                    } catch (IllegalArgumentException e) {
                                        System.out.println(e.getMessage());
                                        optionFailed = false;
                                    }

                                } while (!optionFailed);

                            } else {
                                throw new IllegalArgumentException("Some error occur! Restart the UI please!");
                            }

                        } while (!addMoreOptionsBool);

                        filterOption = true;

                    } catch (Exception e) {
                        filterOption = false;
                        index = 1;
                        System.out.println(e.getMessage());
                    }
                } while (!filterOption);

                productDTOS = controller.prepareListToBeRepresented((List<ProductDTO>) productDTOS, option);

                index = 1;

                System.out.println("\n\n\n### Products Catalog ###");
                for (ProductDTO pd : productDTOS) {
                    System.out.println("> " + index + " - " + pd + "\n");
                    index++;
                }

            } else {

                index = 1;

                System.out.println("\n\n\n### Products Catalog ###");
                for (ProductDTO pd : productDTOS) {
                    System.out.println("> " + index + " - " + pd + "\n");
                    index++;
                }

            }

            Map<ProductDTO, Integer> selectedProdsWithQuantity = new HashMap<>();

            if (!((List<ProductDTO>) productDTOS).isEmpty()) {

                //Opção de adicionar algum produto ao shopping cart

                boolean invalidInputOptions = false;
                boolean addToSCart = false;

                do {
                    try {
                        addMoreOptions = Console.readLine("\nDo you wish to add any product to your shopping cart?");

                        if (addMoreOptions.equals("No") | addMoreOptions.equals("NO") | addMoreOptions.equals("no") | addMoreOptions.equals("N") | addMoreOptions.equals("n")) {

                            invalidInputOptions = true;
                            addToSCart = false;

                        } else if (addMoreOptions.equals("Yes") | addMoreOptions.equals("YES") | addMoreOptions.equals("yes") | addMoreOptions.equals("Y") | addMoreOptions.equals("y")) {

                            invalidInputOptions = true;
                            addToSCart = true;

                        } else {
                            throw new IllegalArgumentException("Please enter a valid option!! (Yes or No)");
                        }

                        invalidInputOptions = true;
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        invalidInputOptions = false;
                    }

                } while (!invalidInputOptions);

                boolean addProd = false;
                invalidInputOptions = false;
                boolean optionFailed = false;
                boolean clientVerification = false;

                try {
                    if (addToSCart) {

                        clientVerification = controller.handleCustomer();

                        if (clientVerification) {
                            do {
                                do {
                                    try {
                                        index = 1;

                                        System.out.println("\n\n\n### Products Catalog ###");
                                        for (ProductDTO pd : productDTOS) {
                                            System.out.println("> " + index + " - " + pd + "\n");
                                            index++;
                                        }

                                        option = Console.readInteger("\nWhich product/products do you wish to add to your shopping cart? (Please enter the product index shown above!)");

                                        if (option < 0 || option > (index - 1)) {
                                            throw new IllegalArgumentException("\nPlease enter a valid option! (Neither can the option be negative or have a higher value than the represented in the options!");
                                        }

                                        optionFailed = true;

                                    } catch (IllegalArgumentException e) {
                                        System.out.println(e.getMessage());
                                        optionFailed = false;
                                    }

                                } while (!optionFailed);


                                int quantity = 0;

                                do {

                                    try {

                                        quantity = Console.readInteger("\nHow many of this product do you wish to add to your shopping cart?");

                                        if (quantity < 0) {
                                            throw new IllegalArgumentException("\nPlease enter a valid option! (Don't type a negative number!)");
                                        }

                                        optionFailed = true;

                                    } catch (IllegalArgumentException e) {
                                        System.out.println(e.getMessage());
                                        optionFailed = false;
                                    } catch (Exception e) {
                                        System.out.println("Please enter a valid number!");
                                        optionFailed = false;
                                    }

                                } while (!optionFailed);

                                selectedProdsWithQuantity.put(((List<ProductDTO>) productDTOS).get(option - 1), quantity);
                                ((List<ProductDTO>) productDTOS).remove(option - 1);


                                do {
                                    try {
                                        addMoreOptions = Console.readLine("\nDo you wish to add more products to your shopping cart?");

                                        if (addMoreOptions.equals("No") | addMoreOptions.equals("NO") | addMoreOptions.equals("no") | addMoreOptions.equals("N") | addMoreOptions.equals("n")) {

                                            invalidInputOptions = true;
                                            addProd = true;

                                        } else if (addMoreOptions.equals("Yes") | addMoreOptions.equals("YES") | addMoreOptions.equals("yes") | addMoreOptions.equals("Y") | addMoreOptions.equals("y")) {

                                            invalidInputOptions = true;
                                            addProd = false;

                                        } else {
                                            throw new IllegalArgumentException("Please enter a valid option!! (Yes or No)");
                                        }

                                        invalidInputOptions = true;
                                    } catch (IllegalArgumentException e) {
                                        System.out.println(e.getMessage());
                                        invalidInputOptions = false;
                                    }

                                } while (!invalidInputOptions);


                            } while (!addProd);

                        } else {
                            throw new IllegalArgumentException("Failed to verify the customer information!\nPlease contact a admin!");
                        }

                    }

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            }

            try {
                if (controller.addProductListToCart(selectedProdsWithQuantity)) {
                    System.out.println("\nOperation success!");
                } else {
                    System.out.println("There was an error while trying to add the products to the cart!\nPlease contact a admin!\nPerhaps the product that you tried to add already exists in your shopping cart!\nOperation Failed!");
                }

            } catch (Exception e) {
                System.out.println("The product that you wanted to add to your shopping cart already it's there! Please make an order with the current shopping cart or add other products!");
            }

        } else {
            System.out.println("\nPlease run the UI again and specify other filter/options!\n");
        }

        return false;
    }

    @Override
    public String headline() {
        return "Search Product Catalog >";
    }
}