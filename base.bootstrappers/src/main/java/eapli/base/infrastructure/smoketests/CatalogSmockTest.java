package eapli.base.infrastructure.smoketests;

import eapli.base.catalogmanagement.application.SearchCatalogController;
import eapli.base.productmanagement.application.RegisterProductController;
import eapli.base.productmanagement.domain.*;
import eapli.base.productmanagement.dto.ProductDTO;
import eapli.framework.actions.Action;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Money;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CatalogSmockTest implements Action {
    Logger logger = org.slf4j.LoggerFactory.getLogger(CatalogSmockTest.class);


    @Override
    public boolean execute() {
        checkASimpleCatalog();
        return false;
    }

    private void checkASimpleCatalog() {
        RegisterProductController controller = new RegisterProductController();

        //create 2 simple products
        Code CODE1 = Code.valueOf("P0001");
        Description SHORT_DESCRIPTION1 = Description.valueOf("Test short description");
        Description EXTENDED_DESCRIPTION1 = Description.valueOf("Test extended description");
        Description TECHNICAL_DESCRIPTION1 = Description.valueOf("Test technical description");
        BrandName BRAND_NAME1 = BrandName.valueOf("Test brand name");
        Reference REFERENCE1 = Reference.valueOf("Teste reference");
        Barcode BARCODE1 = Barcode.valueOf(1L);
        Money PRICE1 = Money.valueOf("10 EUR");
        List<String> PHOTO_LIST1 = new ArrayList<>();
        PHOTO_LIST1.add("Docs/Extra/Photos/livro.jpg");
        final ProductionCode PRODUCTION_CODE1 = ProductionCode.valueOf("PC1");

        try {
            controller.registerProductWithProductionCode(2, CODE1, SHORT_DESCRIPTION1, EXTENDED_DESCRIPTION1, TECHNICAL_DESCRIPTION1, BRAND_NAME1, REFERENCE1, BARCODE1, PRICE1, PHOTO_LIST1, PRODUCTION_CODE1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //product 2
        Code CODE2 = Code.valueOf("P0002");
        Description SHORT_DESCRIPTION2 = Description.valueOf("Test short description");
        Description EXTENDED_DESCRIPTION2 = Description.valueOf("Test extended description");
        Description TECHNICAL_DESCRIPTION2 = Description.valueOf("Test technical description");
        BrandName BRAND_NAME2 = BrandName.valueOf("Test brand name");
        Reference REFERENCE2 = Reference.valueOf("Teste reference");
        Barcode BARCODE2 = Barcode.valueOf(2L);
        Money PRICE2 = Money.valueOf("10 EUR");
        List<String> PHOTO_LIST2 = new ArrayList<>();
        PHOTO_LIST2.add("Docs/Extra/Photos/livro.jpg");
        final ProductionCode PRODUCTION_CODE2 = ProductionCode.valueOf("PC2");

        try {
            controller.registerProductWithProductionCode(2, CODE2, SHORT_DESCRIPTION2, EXTENDED_DESCRIPTION2, TECHNICAL_DESCRIPTION2, BRAND_NAME2, REFERENCE2, BARCODE2, PRICE2, PHOTO_LIST2, PRODUCTION_CODE2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //get catalog
        SearchCatalogController searchCatalogController = new SearchCatalogController();
        Iterable<ProductDTO> p = searchCatalogController.searchAllProducts();
        // check if there is 2 products with this short description
        List<ProductDTO> products = new ArrayList<>();
        p.forEach(products::add);
        if (products.stream().filter(product -> product.getShortDescription().equals(SHORT_DESCRIPTION1.toString())).count() < 2) {
            throw new IllegalStateException("There is not 2 products with this short description");
        }
        logger.info(("»»» Product Catalog"));

    }
}
