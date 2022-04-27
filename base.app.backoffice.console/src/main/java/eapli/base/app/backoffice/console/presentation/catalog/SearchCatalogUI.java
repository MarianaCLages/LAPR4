package eapli.base.app.backoffice.console.presentation.catalog;

import eapli.base.app.backoffice.console.presentation.product.RegisterProductUI;
import eapli.base.productmanagement.application.RegisterProductController;
import eapli.framework.presentation.console.AbstractUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SearchCatalogUI extends AbstractUI {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchCatalogUI.class);
    private final RegisterProductController controller = new RegisterProductController();

    @Override
    protected boolean doShow() {






        return false;
    }

    @Override
    public String headline() {
        return "Search Product Catalog >";
    }
}
