package eapli.base.productmanagement.domain;

import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Money;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    private static final Code CODE = Code.valueOf("P0001");
    private static final Description SHORT_DESCRIPTION = Description.valueOf("Test short description");
    private static final Description EXTENDED_DESCRIPTION = Description.valueOf("Test extended description");
    private static final Description TECHNICAL_DESCRIPTION = Description.valueOf("Test technical description");
    private static final BrandName BRAND_NAME = BrandName.valueOf("Test brand name");
    private static final Reference REFERENCE = Reference.valueOf("Teste reference");
    private static final Barcode BARCODE = Barcode.valueOf(1L);
    private static final Money PRICE = Money.valueOf("10 EUR");
    private static final Photo PHOTO = new Photo();
    private static final ProductionCode PRODUCTION_CODE = ProductionCode.valueOf("PC1");

    private Product buildProductWithoutProductionCode() {
        return new ProductBuilder().coded(CODE).withAShortDescription(SHORT_DESCRIPTION).withAnExtendedDescription(EXTENDED_DESCRIPTION).withATechnicalDescription(TECHNICAL_DESCRIPTION).withABrandName(BRAND_NAME).withAReference(REFERENCE).withABarcode(BARCODE).withAPrice(PRICE).withAPhoto(PHOTO).build();
    }

    private Product buildProductWithProductionCode() {
        return new ProductBuilder().coded(CODE).withAShortDescription(SHORT_DESCRIPTION).withAnExtendedDescription(EXTENDED_DESCRIPTION).withATechnicalDescription(TECHNICAL_DESCRIPTION).withABrandName(BRAND_NAME).withAReference(REFERENCE).withABarcode(BARCODE).withAPrice(PRICE).withAPhoto(PHOTO).withAProductionCode(PRODUCTION_CODE).build();
    }

    @Test
    public void ensureCanBuildProductWithoutOnlyProductionCode() {
        new Product(CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, PRICE, PHOTO);

        assertTrue(true);
    }

    @Test
    public void ensureCanBuildProduct() {
        new Product(CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, PRICE, PHOTO, PRODUCTION_CODE);

        assertTrue(true);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void ensureMustHaveCode() {
        new Product(null, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, PRICE, PHOTO);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void ensureMustHaveShortDescription() {
        new Product(CODE, null, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, PRICE, PHOTO);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void ensureCannotChangeCodeToNull() {
        final Product subject = buildProductWithoutProductionCode();

        subject.update(null, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, PRICE, PHOTO, PRODUCTION_CODE);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void ensureCannotChangeShortDescriptionToNull() {
        final Product subject = buildProductWithoutProductionCode();

        subject.update(CODE, null, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, PRICE, PHOTO, PRODUCTION_CODE);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void ensureCannotChangeExtendedDescriptionToNull() {
        final Product subject = buildProductWithProductionCode();

        subject.update(CODE, SHORT_DESCRIPTION, null, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, PRICE, PHOTO, PRODUCTION_CODE);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void ensureCannotChangeTechnicalDescriptionToNull() {
        final Product subject = buildProductWithProductionCode();

        subject.update(CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, null, BRAND_NAME, REFERENCE, BARCODE, PRICE, PHOTO, PRODUCTION_CODE);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void ensureCannotChangeBrandNameToNull() {
        final Product subject = buildProductWithProductionCode();

        subject.update(CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, null, REFERENCE, BARCODE, PRICE, PHOTO, PRODUCTION_CODE);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void ensureCannotChangeReferenceToNull() {
        final Product subject = buildProductWithProductionCode();

        subject.update(CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, null, BARCODE, PRICE, PHOTO, PRODUCTION_CODE);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void ensureCannotChangeBarcodeToNull() {
        final Product subject = buildProductWithProductionCode();

        subject.update(CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, null, PRICE, PHOTO, PRODUCTION_CODE);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void ensureCannotChangePriceToNull() {
        final Product subject = buildProductWithProductionCode();

        subject.update(CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, null, PHOTO, PRODUCTION_CODE);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void ensureCannotChangePhotoToNull() {
        final Product subject = buildProductWithProductionCode();

        subject.update(CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, PRICE, null, PRODUCTION_CODE);
    }

    @Test
    public void ensureCanChangeCode() {
        final Product subject = buildProductWithProductionCode();

        final Code newInfo = Code.valueOf("P0002");

        subject.update(newInfo, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, PRICE, PHOTO, PRODUCTION_CODE);
    }

    @Test
    public void ensureCanChangeShortDescription() {
        final Product subject = buildProductWithProductionCode();

        final Description newInfo = Description.valueOf("Another short description");

        subject.update(CODE, newInfo, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, PRICE, PHOTO, PRODUCTION_CODE);
    }

    @Test
    public void ensureCanChangeExtendedDescription() {
        final Product subject = buildProductWithProductionCode();

        final Description newInfo = Description.valueOf("Another extended description");

        subject.update(CODE, SHORT_DESCRIPTION, newInfo, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, PRICE, PHOTO, PRODUCTION_CODE);
    }

    @Test
    public void ensureCanChangeTechnicalDescription() {
        final Product subject = buildProductWithProductionCode();

        final Description newInfo = Description.valueOf("Another technicalt description");

        subject.update(CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, newInfo, BRAND_NAME, REFERENCE, BARCODE, PRICE, PHOTO, PRODUCTION_CODE);
    }

    @Test
    public void ensureCanChangeBrandName() {
        final Product subject = buildProductWithProductionCode();

        final BrandName newInfo = BrandName.valueOf("Another brand name");

        subject.update(CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, newInfo, REFERENCE, BARCODE, PRICE, PHOTO, PRODUCTION_CODE);
    }

    @Test
    public void ensureCanChangeReference() {
        final Product subject = buildProductWithProductionCode();

        final Reference newInfo = Reference.valueOf("Another reference");

        subject.update(CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, newInfo, BARCODE, PRICE, PHOTO, PRODUCTION_CODE);
    }

    @Test
    public void ensureCanChangeBarcode() {
        final Product subject = buildProductWithProductionCode();

        final Barcode newInfo = Barcode.valueOf(2L);

        subject.update(CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, newInfo, PRICE, PHOTO, PRODUCTION_CODE);
    }

    @Test
    public void ensureCanChangePrice() {
        final Product subject = buildProductWithProductionCode();

        final Money newInfo = Money.valueOf("20 EUR");

        subject.update(CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, newInfo, PHOTO, PRODUCTION_CODE);
    }

    @Test
    public void ensureCanChangePhoto() {
        final Product subject = buildProductWithProductionCode();

        byte[] newPhoto = {2, 0, 1};
        final Photo newInfo = Photo.valueOf(newPhoto);

        subject.update(CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, PRICE, newInfo, PRODUCTION_CODE);
    }

    @Test
    public void ensureCanChangeProductionCode() {
        final Product subject = buildProductWithProductionCode();

        final ProductionCode newInfo = ProductionCode.valueOf("Another production code");

        subject.update(CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, PRICE, PHOTO, newInfo);
    }
}