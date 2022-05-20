package eapli.base.infrastructure.smoketests;

import eapli.base.productmanagement.application.RegisterProductController;
import eapli.base.productmanagement.domain.*;
import eapli.base.productmanagement.dto.ProductDTO;
import eapli.framework.actions.Action;

import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Money;
import eapli.framework.validations.Invariants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductManagementSmokeTest implements Action {


    private static final Logger LOGGER = LogManager.getLogger(ProductManagementSmokeTest.class);

    private static final RegisterProductController registerProductController = new RegisterProductController();

    @Override
    public boolean execute() {
        testGetProductDTO();
        return false;
    }

    private void testGetProductDTO() {
        Long CATEGORYID = 123456L;
        Code CODE = Code.valueOf("P0001");
        Description SHORT_DESCRIPTION = Description.valueOf("Test short description");
        Description EXTENDED_DESCRIPTION = Description.valueOf("Test extended description");
        Description TECHNICAL_DESCRIPTION = Description.valueOf("Test technical description");
        BrandName BRAND_NAME = BrandName.valueOf("Test brand name");
        Reference REFERENCE = Reference.valueOf("Teste reference");
        Barcode BARCODE = Barcode.valueOf(1L);
        Money PRICE = Money.valueOf("10 EUR");
        List<String> PHOTO_LIST = new ArrayList<>();
        final ProductionCode PRODUCTION_CODE = ProductionCode.valueOf("PC1");


        try {
            registerProductController.registerProductWithoutProductionCode(1, CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, PRICE, PHOTO_LIST);
        } catch (IOException e) {
            e.printStackTrace();
        }

        final ProductDTO productDTO = registerProductController.getProductDTO();


        Invariants.ensure(productDTO != null);

        LOGGER.info("»»» Product DTO", productDTO);

    }

}
