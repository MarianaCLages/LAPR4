package eapli.base.infrastructure.bootstrapers.demo;

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

        final String photo1 = "Docs/Extra/Photos/yoda.jpg";
        final String photo2 = "Docs/Extra/Photos/yoda.jpg";

        final ProductionCode productionCode1 = ProductionCode.valueOf("PC1");

        registerWithProductionCode(code1, shortDescription1, extendedDescription1, technicalDescription1, brandName1, reference1, barcode1, price1, photo1, productionCode1);
        registerWithoutProductionCode(code2, shortDescription2, extendedDescription2, technicalDescription2, brandName2, reference2, barcode2, price2, photo2);

        return true;
    }

    private void registerWithoutProductionCode(final Code code, Description shortDescription, Description extendedDescription, Description technicalDescription, BrandName brandName, Reference reference, Barcode barcode, Money price, String photo) {
        try {
            Product product = controller.registerProductWithoutProductionCode(code, shortDescription, extendedDescription, technicalDescription, brandName, reference, barcode, price, photo);
            LOGGER.debug("»»» {}", product);
        } catch (final IntegrityViolationException | ConcurrencyException | IOException exception) {
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", "product");
            LOGGER.trace("Assuming existing record", exception);
        }
    }

    private void registerWithProductionCode(final Code code, Description shortDescription, Description extendedDescription, Description technicalDescription, BrandName brandName, Reference reference, Barcode barcode, Money price, String photo, ProductionCode productionCode) {
        try {
            Product product = controller.registerProductWithProductionCode(code, shortDescription, extendedDescription, technicalDescription, brandName, reference, barcode, price, photo, productionCode);
            LOGGER.debug("»»» {}", product);
        } catch (final IntegrityViolationException | ConcurrencyException | IOException exception) {
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", "product");
            LOGGER.trace("Assuming existing record", exception);
        }
    }
}
