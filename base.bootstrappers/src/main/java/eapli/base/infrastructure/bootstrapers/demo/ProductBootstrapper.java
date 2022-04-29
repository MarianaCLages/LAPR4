package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.categorymanagement.domain.Category;
import eapli.base.productmanagement.application.RegisterProductController;
import eapli.base.productmanagement.domain.*;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Money;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductBootstrapper implements Action {

    private static final Logger LOGGER = LogManager.getLogger(ProductBootstrapper.class);

    private final RegisterProductController controller = new RegisterProductController();

    @Override
    public boolean execute() {

        final Code code1 = Code.valueOf("P0001");
        final Code code2 = Code.valueOf("P0002");

        final Description shortDescription1 = Description.valueOf("Short description about product one");
        final Description shortDescription2 = Description.valueOf("Short description about product two");

        final Description extendedDescription1 = Description.valueOf("Extended description about product one");
        final Description extendedDescription2 = Description.valueOf("Extended description about product two");

        final Description technicalDescription1 = Description.valueOf("Technical description about product one");
        final Description technicalDescription2 = Description.valueOf("Technical description about product two");

        final BrandName brandName1 = BrandName.valueOf("FILA");
        final BrandName brandName2 = BrandName.valueOf("REGINA");

        final Reference reference1 = Reference.valueOf("REF1");
        final Reference reference2 = Reference.valueOf("REF2");

        final Barcode barcode1 = Barcode.valueOf(1L);
        final Barcode barcode2 = Barcode.valueOf(2L);

        final Money price1 = Money.valueOf("10 EUR");
        final Money price2 = Money.valueOf("20 EUR");

        final List<String> photoList1 = new ArrayList<>();
        photoList1.add("Docs/Extra/Photos/yoda.jpg");
        photoList1.add("Docs/Extra/Photos/yoda2.jpg");

        final List<String> photoList2 = new ArrayList<>();
        photoList2.add("Docs/Extra/Photos/panelas.jpg");

        final ProductionCode productionCode1 = ProductionCode.valueOf("PC1");

        registerWithProductionCode(code1, shortDescription1, extendedDescription1, technicalDescription1, brandName1, reference1, barcode1, price1, photoList1, productionCode1);
        registerWithoutProductionCode(code2, shortDescription2, extendedDescription2, technicalDescription2, brandName2, reference2, barcode2, price2, photoList2);

        return true;
    }

    private void registerWithProductionCode(final Code code, Description shortDescription, Description extendedDescription, Description technicalDescription, BrandName brandName, Reference reference, Barcode barcode, Money price, List<String> photoList, ProductionCode productionCode) {
        try {
            Product product = controller.registerProductWithProductionCode(6, code, shortDescription, extendedDescription, technicalDescription, brandName, reference, barcode, price, photoList, productionCode);
            LOGGER.debug("»»» {}", product);
        } catch (final IntegrityViolationException | ConcurrencyException | IOException exception) {
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", "product");
            LOGGER.trace("Assuming existing record", exception);
        }
    }

    private void registerWithoutProductionCode(final Code code, Description shortDescription, Description extendedDescription, Description technicalDescription, BrandName brandName, Reference reference, Barcode barcode, Money price, List<String> photoList) {
        try {
            Product product = controller.registerProductWithoutProductionCode(6, code, shortDescription, extendedDescription, technicalDescription, brandName, reference, barcode, price, photoList);
            LOGGER.debug("»»» {}", product);
        } catch (final IntegrityViolationException | ConcurrencyException | IOException exception) {
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", "product");
            LOGGER.trace("Assuming existing record", exception);
        }
    }
}
