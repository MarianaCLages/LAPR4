package eapli.base.app.backoffice.console.presentation.catalog;

import eapli.base.catalogmanagement.application.SearchCatalogController;
import eapli.base.productmanagement.dto.ProductDTO;
import eapli.framework.presentation.console.AbstractUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
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
        filterOptions.add("Filter by ");
        filterOptions.add("Filter by brand");

        do {


        } while (!filterOption);


        for (ProductDTO pd : controller.searchAllProducts()) {
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
