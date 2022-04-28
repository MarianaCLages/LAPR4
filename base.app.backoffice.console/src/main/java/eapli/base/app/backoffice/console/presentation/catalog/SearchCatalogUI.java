package eapli.base.app.backoffice.console.presentation.catalog;

import eapli.base.catalogmanagement.application.SearchCatalogController;
import eapli.base.categorymanagement.domain.AlphaNumericCode;
import eapli.base.productmanagement.dto.ProductDTO;
import eapli.framework.general.domain.model.Money;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchCatalogUI extends AbstractUI {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchCatalogUI.class);
    private final SearchCatalogController controller = new SearchCatalogController();

    @Override
    protected boolean doShow() {

        boolean filterOption = false;

        List<String> filterOptions = new ArrayList<>();
        filterOptions.add("Filter by brand");
        filterOptions.add("Filter by reference");
        filterOptions.add("Filter by barcode");
        filterOptions.add("Filter by price");
        filterOptions.add("Filter by category");

        int option;
        int index = 1;
        String addMoreOptions;
        boolean addMoreOptionsBool = false;

        HashMap<String, List<String>> optionsChosen = new HashMap<>();

        do {
            try {
                do {
                    if (!filterOptions.isEmpty()) {
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
                                    throw new IllegalArgumentException("Please enter a valid option! (Neither can the option be negative or have a higher value than the represented in the options!");
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
                                addMoreOptions = Console.readLine("Do you wish to add more options?");

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
                filterOptions.add("Filter by price");
                filterOptions.add("Filter by category");
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
                            String brandInfo = Console.readLine("Please enter a brand name:");

                            if (brandInfo.isEmpty()) throw new IllegalArgumentException("Please enter a brand name!");

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
                            String referenceInfo = Console.readLine("Please enter a reference: ");

                            if (referenceInfo.isEmpty())
                                throw new IllegalArgumentException("Please enter a reference!");

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
                            barcodeInfoLong = Console.readInteger("Please enter a barcode: (NOTICE: A barcode must only contain numbers!) ");

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
                case "Filter by price":

                    boolean optionFailed = false;
                    int optionPrice = 0;

                    do {
                        try {
                            System.out.println("\n> 1 - Enter a price only\n> 2 - Enter a interval of prices (Low Price - High Price)\n");
                            optionPrice = Console.readInteger("Please choose one of the valid options:");

                            if (optionPrice < 0 || optionPrice > 2) {
                                throw new IllegalArgumentException("Please enter a valid option! (Neither can the option be negative or have a higher value than the represented in the options!");
                            }

                            optionFailed = true;

                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                            optionFailed = false;
                        }
                    } while (!optionFailed);

                    if (optionPrice == 1) {
                        do {
                            try {

                                String priceInfo = Console.readLine("Please enter a price (EUR/USD): ");

                                Money price = Money.valueOf(priceInfo);

                                if (priceInfo.isEmpty())
                                    throw new IllegalArgumentException("Please enter a valid price (AMOUNT EUR/USD)!");

                                optionsChosen.get("Filter by price").add(priceInfo);
                                addOption = true;
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                                addOption = false;
                            }
                        } while (!addOption);

                    } else if (optionPrice == 2) {
                        do {
                            try {

                                String stringHighestPrice = Console.readLine("\nPlease enter the highest price (EUR/USD): ");

                                if (stringHighestPrice.isEmpty())
                                    throw new IllegalArgumentException("Please enter a valid price (AMOUNT EUR/USD)!");

                                Money highestPriceInfo = Money.valueOf(stringHighestPrice);

                                String stringLowestPrice = Console.readLine("Please enter the lowest price (EUR/USD): ");

                                if (stringLowestPrice.isEmpty())
                                    throw new IllegalArgumentException("Please enter a valid price (AMOUNT EUR/USD)!");

                                Money lowestPriceInfo = Money.valueOf(stringLowestPrice);

                                if (lowestPriceInfo.isGreaterThan(highestPriceInfo)) {
                                    throw new IllegalArgumentException("Please enter valid prices! (The lowest price cannot be higher than the highest price!)");
                                }

                                optionsChosen.get("Filter by price").add(stringHighestPrice);
                                optionsChosen.get("Filter by price").add(stringLowestPrice);
                                addOption = true;
                            } catch (Exception e) {
                                if (e.getMessage() == null)
                                    LOGGER.info("\nPlease specify a correct amount! (AMOUNT (EUR/USD))");
                                else LOGGER.info("\n" + e.getMessage());

                                addOption = false;
                            }
                        } while (!addOption);


                    } else {
                        throw new IllegalArgumentException("An error occur while trying to parse the price!");
                    }

                    break;
                case "Filter by category":
                    do {
                        try {
                            Long alphaCode = Long.valueOf(Console.readInteger("\nPlease enter the AlphaNumeric code of the category: "));
                            AlphaNumericCode code = AlphaNumericCode.valueOf(String.valueOf(alphaCode));

                            if (code == null || alphaCode < 0L)
                                throw new IllegalArgumentException("Please enter a barcode!");

                            optionsChosen.get("Filter by category").add(String.valueOf(alphaCode));
                            addOption = true;
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            addOption = false;
                        }

                    } while (!addOption);
                    break;
            }
        }


        for (ProductDTO pd : controller.searchAllProducts(optionsChosen)) {
            System.out.println(pd + "\n");
        }

        System.out.println("Operation success!");

        return false;
    }

    @Override
    public String headline() {
        return "Search Product Catalog >";
    }
}
