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
import java.util.ArrayList;
import java.util.List;

public class ProductBootstrapper implements Action {

    private static final Logger LOGGER = LogManager.getLogger(ProductBootstrapper.class);

    private final RegisterProductController controller = new RegisterProductController();

    @Override
    public boolean execute() {

        final int categoryId1 = 5;
        final int categoryId2 = 4;
        final int categoryId3 = 3;
        final int categoryId4 = 6;
        final int categoryId5 = 7;
        final int categoryId6 = 8;
        final int categoryId7 = 9;
        final int categoryId8 = 10;
        final int categoryId9 = 11;
        final int categoryId10 = 13;
        final int categoryId11 = 14;
        final int categoryId12 = 15;
        final int categoryId13 = 16;
        final int categoryId14 = 17;


        final Code code1 = Code.valueOf("P0001");
        final Code code2 = Code.valueOf("P0002");
        final Code code3 = Code.valueOf("P0003");
        final Code code4 = Code.valueOf("P0004");
        final Code code5 = Code.valueOf("P0005");
        final Code code6 = Code.valueOf("P0006");
        final Code code7 = Code.valueOf("P0007");
        final Code code8 = Code.valueOf("P0008");
        final Code code9 = Code.valueOf("P0009");
        final Code code10 = Code.valueOf("P0010");
        final Code code11 = Code.valueOf("P0011");
        final Code code12 = Code.valueOf("P0012");
        final Code code13 = Code.valueOf("P0013");
        final Code code14 = Code.valueOf("P0014");


        final Description shortDescription1 = Description.valueOf("Short description about product one");
        final Description shortDescription2 = Description.valueOf("Short description about product two");
        final Description shortDescription3 = Description.valueOf("Short description about product three");
        final Description shortDescription4 = Description.valueOf("Short description about product four");
        final Description shortDescription5 = Description.valueOf("Short description about product five");
        final Description shortDescription6 = Description.valueOf("Short description about product six");
        final Description shortDescription7 = Description.valueOf("Short description about product seven");
        final Description shortDescription8 = Description.valueOf("Short description about product eight");
        final Description shortDescription9 = Description.valueOf("Short description about product nine");
        final Description shortDescription10 = Description.valueOf("Short description about product ten");
        final Description shortDescription11 = Description.valueOf("Short description about product eleven");
        final Description shortDescription12 = Description.valueOf("Short description about product twelve");
        final Description shortDescription13 = Description.valueOf("Short description about product thirteen");
        final Description shortDescription14 = Description.valueOf("Short description about product fourteen");


        final Description extendedDescription1 = Description.valueOf("Extended description about product one");
        final Description extendedDescription2 = Description.valueOf("Extended description about product two");
        final Description extendedDescription3 = Description.valueOf("Extended description about product three");
        final Description extendedDescription4 = Description.valueOf("Extended description about product four");
        final Description extendedDescription5 = Description.valueOf("Extended description about product five");
        final Description extendedDescription6 = Description.valueOf("Extended description about product six");
        final Description extendedDescription7 = Description.valueOf("Extended description about product seven");
        final Description extendedDescription8 = Description.valueOf("Extended description about product eight");
        final Description extendedDescription9 = Description.valueOf("Extended description about product nine");
        final Description extendedDescription10 = Description.valueOf("Extended description about product ten");
        final Description extendedDescription11 = Description.valueOf("Extended description about product eleven");
        final Description extendedDescription12 = Description.valueOf("Extended description about product twelve");
        final Description extendedDescription13 = Description.valueOf("Extended description about product thirteen");
        final Description extendedDescription14 = Description.valueOf("Extended description about product fourteen");


        final Description technicalDescription1 = Description.valueOf("Technical description about product one");
        final Description technicalDescription2 = Description.valueOf("Technical description about product two");
        final Description technicalDescription3 = Description.valueOf("Technical description about product three");
        final Description technicalDescription4 = Description.valueOf("Technical description about product four");
        final Description technicalDescription5 = Description.valueOf("Technical description about product five");
        final Description technicalDescription6 = Description.valueOf("Technical description about product six");
        final Description technicalDescription7 = Description.valueOf("Technical description about product seven");
        final Description technicalDescription8 = Description.valueOf("Technical description about product eight");
        final Description technicalDescription9 = Description.valueOf("Technical description about product nine");
        final Description technicalDescription10 = Description.valueOf("Technical description about product ten");
        final Description technicalDescription11 = Description.valueOf("Technical description about product eleven");
        final Description technicalDescription12 = Description.valueOf("Technical description about product twelve");
        final Description technicalDescription13 = Description.valueOf("Technical description about product thirteen");
        final Description technicalDescription14 = Description.valueOf("Technical description about product fourteen");


        final BrandName brandName1 = BrandName.valueOf("Star Wars");
        final BrandName brandName2 = BrandName.valueOf("Tramontina");
        final BrandName brandName3 = BrandName.valueOf("LeYa");
        final BrandName brandName4 = BrandName.valueOf("Nintendo");
        final BrandName brandName5 = BrandName.valueOf("Stardew");
        final BrandName brandName6 = BrandName.valueOf("Colgate");
        final BrandName brandName7 = BrandName.valueOf("Asus");
        final BrandName brandName8 = BrandName.valueOf("Backpack");
        final BrandName brandName9 = BrandName.valueOf("Sony");
        final BrandName brandName10 = BrandName.valueOf("IKEA");
        final BrandName brandName11 = BrandName.valueOf("Doritos");
        final BrandName brandName12 = BrandName.valueOf("Police");
        final BrandName brandName13 = BrandName.valueOf("Bike");
        final BrandName brandName14 = BrandName.valueOf("Petmate");


        final Reference reference1 = Reference.valueOf("REF1");
        final Reference reference2 = Reference.valueOf("REF2");
        final Reference reference3 = Reference.valueOf("REF3");
        final Reference reference4 = Reference.valueOf("REF4");
        final Reference reference5 = Reference.valueOf("REF5");
        final Reference reference6 = Reference.valueOf("REF6");
        final Reference reference7 = Reference.valueOf("REF7");
        final Reference reference8 = Reference.valueOf("REF8");
        final Reference reference9 = Reference.valueOf("REF9");
        final Reference reference10 = Reference.valueOf("REF10");
        final Reference reference11 = Reference.valueOf("REF11");
        final Reference reference12 = Reference.valueOf("REF12");
        final Reference reference13 = Reference.valueOf("REF13");
        final Reference reference14 = Reference.valueOf("REF14");


        final Barcode barcode1 = Barcode.valueOf(1L);
        final Barcode barcode2 = Barcode.valueOf(2L);
        final Barcode barcode3 = Barcode.valueOf(3L);
        final Barcode barcode4 = Barcode.valueOf(4L);
        final Barcode barcode5 = Barcode.valueOf(5L);
        final Barcode barcode6 = Barcode.valueOf(6L);
        final Barcode barcode7 = Barcode.valueOf(7L);
        final Barcode barcode8 = Barcode.valueOf(8L);
        final Barcode barcode9 = Barcode.valueOf(9L);
        final Barcode barcode10 = Barcode.valueOf(10L);
        final Barcode barcode11 = Barcode.valueOf(11L);
        final Barcode barcode12 = Barcode.valueOf(12L);
        final Barcode barcode13 = Barcode.valueOf(12L);
        final Barcode barcode14 = Barcode.valueOf(14L);


        final Money price1 = Money.valueOf("30 EUR");
        final Money price2 = Money.valueOf("100 EUR");
        final Money price3 = Money.valueOf("8 EUR");
        final Money price4 = Money.valueOf("45 EUR");
        final Money price5 = Money.valueOf("40 USD");
        final Money price6 = Money.valueOf("2 EUR");
        final Money price7 = Money.valueOf("250 EUR");
        final Money price8 = Money.valueOf("15 EUR");
        final Money price9 = Money.valueOf("26 EUR");
        final Money price10 = Money.valueOf("500 EUR");
        final Money price11 = Money.valueOf("2 USD");
        final Money price12 = Money.valueOf("280 EUR");
        final Money price13 = Money.valueOf("310 EUR");
        final Money price14 = Money.valueOf("4 USD");


        final List<String> photoList1 = new ArrayList<>();
        photoList1.add("docs/Extra/Photos/yoda.jpg");
        photoList1.add("docs/Extra/Photos/yoda2.jpg");

        final List<String> photoList2 = new ArrayList<>();
        photoList2.add("docs/Extra/Photos/panelas.jpg");

        final List<String> photoList3 = new ArrayList<>();
        photoList3.add("docs/Extra/Photos/livro.jpg");

        final List<String> photoList4 = new ArrayList<>();
        photoList4.add("docs/Extra/Photos/videogame.jpg");

        final List<String> photoList5 = new ArrayList<>();
        photoList5.add("docs/Extra/Photos/boardgame.png");
        photoList5.add("docs/Extra/Photos/boardgame2.jpg");

        final List<String> photoList6 = new ArrayList<>();
        photoList6.add("docs/Extra/Photos/hygiene.jpg");

        final List<String> photoList7 = new ArrayList<>();
        photoList7.add("docs/Extra/Photos/computer.jpg");

        final List<String> photoList8 = new ArrayList<>();
        photoList8.add("docs/Extra/Photos/school.jpg");

        final List<String> photoList9 = new ArrayList<>();
        photoList9.add("docs/Extra/Photos/hardware.jpg");

        final List<String> photoList10 = new ArrayList<>();
        photoList10.add("docs/Extra/Photos/furniture.jpg");

        final List<String> photoList11 = new ArrayList<>();
        photoList11.add("docs/Extra/Photos/food.jpg");

        final List<String> photoList12 = new ArrayList<>();
        photoList12.add("docs/Extra/Photos/accessories.jpg");

        final List<String> photoList13 = new ArrayList<>();
        photoList13.add("docs/Extra/Photos/vehicle.jpg");

        final List<String> photoList14 = new ArrayList<>();
        photoList14.add("docs/Extra/Photos/pets.jpg");


        final ProductionCode productionCode1 = ProductionCode.valueOf("PC1");
        final ProductionCode productionCode2 = ProductionCode.valueOf("PC3");
        final ProductionCode productionCode3 = ProductionCode.valueOf("PC4");
        final ProductionCode productionCode4 = ProductionCode.valueOf("PC7");
        final ProductionCode productionCode5 = ProductionCode.valueOf("PC9");
        final ProductionCode productionCode6 = ProductionCode.valueOf("PC13");


        registerWithProductionCode(categoryId1, code1, shortDescription1, extendedDescription1, technicalDescription1, brandName1, reference1, barcode1, price1, photoList1, productionCode1);
        registerWithoutProductionCode(categoryId2, code2, shortDescription2, extendedDescription2, technicalDescription2, brandName2, reference2, barcode2, price2, photoList2);
        registerWithProductionCode(categoryId3, code3, shortDescription3, extendedDescription3, technicalDescription3, brandName3, reference3, barcode3, price3, photoList3, productionCode2);
        registerWithProductionCode(categoryId4, code4, shortDescription4, extendedDescription4, technicalDescription4, brandName4, reference4, barcode4, price4, photoList4, productionCode3);
        registerWithoutProductionCode(categoryId5, code5, shortDescription5, extendedDescription5, technicalDescription5, brandName5, reference5, barcode5, price5, photoList5);
        registerWithoutProductionCode(categoryId6, code6, shortDescription6, extendedDescription6, technicalDescription6, brandName6, reference6, barcode6, price6, photoList6);
        registerWithProductionCode(categoryId7, code7, shortDescription7, extendedDescription7, technicalDescription7, brandName7, reference7, barcode7, price7, photoList7, productionCode4);
        registerWithoutProductionCode(categoryId8, code8, shortDescription8, extendedDescription8, technicalDescription8, brandName8, reference8, barcode8, price8, photoList8);
        registerWithProductionCode(categoryId9, code9, shortDescription9, extendedDescription9, technicalDescription9, brandName9, reference9, barcode9, price9, photoList9, productionCode5);
        registerWithoutProductionCode(categoryId10, code10, shortDescription10, extendedDescription10, technicalDescription10, brandName10, reference10, barcode10, price10, photoList10);
        registerWithoutProductionCode(categoryId11, code11, shortDescription11, extendedDescription11, technicalDescription11, brandName11, reference11, barcode11, price11, photoList11);
        registerWithoutProductionCode(categoryId12, code12, shortDescription12, extendedDescription12, technicalDescription12, brandName12, reference12, barcode12, price12, photoList12);
        registerWithProductionCode(categoryId13, code13, shortDescription13, extendedDescription13, technicalDescription13, brandName13, reference13, barcode13, price13, photoList13, productionCode6);
        registerWithoutProductionCode(categoryId14, code14, shortDescription14, extendedDescription14, technicalDescription14, brandName14, reference14, barcode14, price14, photoList14);

        return true;
    }

    private void registerWithProductionCode(int categoryId, final Code code, Description shortDescription, Description extendedDescription, Description technicalDescription, BrandName brandName, Reference reference, Barcode barcode, Money price, List<String> photoList, ProductionCode productionCode) {
        try {
            Product product = controller.registerProductWithProductionCode(categoryId, code, shortDescription, extendedDescription, technicalDescription, brandName, reference, barcode, price, photoList, productionCode);
            LOGGER.debug("»»» {}", product);
        } catch (final IntegrityViolationException | ConcurrencyException | IOException exception) {
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", "product");
            LOGGER.trace("Assuming existing record", exception);
        }
    }

    private void registerWithoutProductionCode(int categoryId, final Code code, Description shortDescription, Description extendedDescription, Description technicalDescription, BrandName brandName, Reference reference, Barcode barcode, Money price, List<String> photoList) {
        try {
            Product product = controller.registerProductWithoutProductionCode(categoryId, code, shortDescription, extendedDescription, technicalDescription, brandName, reference, barcode, price, photoList);
            LOGGER.debug("»»» {}", product);
        } catch (final IntegrityViolationException | ConcurrencyException | IOException exception) {
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", "product");
            LOGGER.trace("Assuming existing record", exception);
        }
    }
}
