package eapli.base.productmanagement.domain;

import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Money;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    private static final Long CATEGORYID = 123456L;
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
        return new ProductBuilder().withACategoryId(CATEGORYID).coded(CODE).withAShortDescription(SHORT_DESCRIPTION).withAnExtendedDescription(EXTENDED_DESCRIPTION).withATechnicalDescription(TECHNICAL_DESCRIPTION).withABrandName(BRAND_NAME).withAReference(REFERENCE).withABarcode(BARCODE).withAPrice(PRICE).withAPhoto(PHOTO).build();
    }

    private Product buildProductWithProductionCode() {
        return new ProductBuilder().withACategoryId(CATEGORYID).coded(CODE).withAShortDescription(SHORT_DESCRIPTION).withAnExtendedDescription(EXTENDED_DESCRIPTION).withATechnicalDescription(TECHNICAL_DESCRIPTION).withABrandName(BRAND_NAME).withAReference(REFERENCE).withABarcode(BARCODE).withAPrice(PRICE).withAPhoto(PHOTO).withAProductionCode(PRODUCTION_CODE).build();
    }

    @Test
    void ensureCanBuildProductWithoutOnlyProductionCode() {
        new Product(CATEGORYID, CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, PRICE, PHOTO);

        assertTrue(true);
    }

    @Test
    void ensureCanBuildProduct() {
        new Product(CATEGORYID, CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, PRICE, PHOTO, PRODUCTION_CODE);

        assertTrue(true);
    }

    @Test
    void ensureMustHaveCategoryId() {
        assertThrows(IllegalArgumentException.class, () -> new Product(null, CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, PRICE, PHOTO));
    }

    @Test
    void ensureMustHaveCode() {
        assertThrows(IllegalArgumentException.class, () -> new Product(CATEGORYID, null, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, PRICE, PHOTO));
    }

    @Test
    void ensureMustHaveShortDescription() {
        assertThrows(IllegalArgumentException.class, () -> new Product(CATEGORYID, CODE, null, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, PRICE, PHOTO));
    }

    @Test
    public void ensureMustHaveExtendedDescription() {
        assertThrows(IllegalArgumentException.class, () -> new Product(CATEGORYID, CODE, SHORT_DESCRIPTION, null, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, PRICE, PHOTO));
    }

    @Test
    void ensureMustHaveTechnicalDescription() {
        assertThrows(IllegalArgumentException.class, () -> new Product(CATEGORYID, CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, null, BRAND_NAME, REFERENCE, BARCODE, PRICE, PHOTO));
    }

    @Test
    public void ensureMustHaveBrandName() {
        assertThrows(IllegalArgumentException.class, () -> new Product(CATEGORYID, CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, null, REFERENCE, BARCODE, PRICE, PHOTO));
    }

    @Test
    public void ensureMustHaveReference() {
        assertThrows(IllegalArgumentException.class, () -> new Product(CATEGORYID, CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, null, BARCODE, PRICE, PHOTO));
    }

    @Test
    public void ensureMustHaveBarcode() {
        assertThrows(IllegalArgumentException.class, () -> new Product(CATEGORYID, CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, null, PRICE, PHOTO));
    }

    @Test
    void ensureMustHavePrice() {
        assertThrows(IllegalArgumentException.class, () -> new Product(CATEGORYID, CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, null, PHOTO));
    }

    @Test
    public void ensureMustHavePhoto() {
        assertThrows(IllegalArgumentException.class, () -> new Product(CATEGORYID, CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, PRICE, null));
    }

    @Test
    public void ensureCannotChangeCategoryIdToNull() {
        final Product subject = buildProductWithoutProductionCode();

        assertThrows(IllegalArgumentException.class, () -> subject.update(null, CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, PRICE, PHOTO, PRODUCTION_CODE));
    }

    @Test
    public void ensureCannotChangeCodeToNull() {
        final Product subject = buildProductWithoutProductionCode();

        assertThrows(IllegalArgumentException.class, () -> subject.update(CATEGORYID, null, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, PRICE, PHOTO, PRODUCTION_CODE));
    }

    @Test
    public void ensureCannotChangeShortDescriptionToNull() {
        final Product subject = buildProductWithoutProductionCode();

        assertThrows(IllegalArgumentException.class, () -> subject.update(CATEGORYID, CODE, null, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, PRICE, PHOTO, PRODUCTION_CODE));
    }

    @Test
    void ensureCannotChangeExtendedDescriptionToNull() {
        final Product subject = buildProductWithProductionCode();

        assertThrows(IllegalArgumentException.class, () -> subject.update(CATEGORYID, CODE, SHORT_DESCRIPTION, null, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, PRICE, PHOTO, PRODUCTION_CODE));
    }

    @Test
    void ensureCannotChangeTechnicalDescriptionToNull() {
        final Product subject = buildProductWithProductionCode();

        assertThrows(IllegalArgumentException.class, () -> subject.update(CATEGORYID, CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, null, BRAND_NAME, REFERENCE, BARCODE, PRICE, PHOTO, PRODUCTION_CODE));
    }

    @Test
    void ensureCannotChangeBrandNameToNull() {
        final Product subject = buildProductWithProductionCode();

        assertThrows(IllegalArgumentException.class, () -> subject.update(CATEGORYID, CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, null, REFERENCE, BARCODE, PRICE, PHOTO, PRODUCTION_CODE));
    }

    @Test
    void ensureCannotChangeReferenceToNull() {
        final Product subject = buildProductWithProductionCode();

        assertThrows(IllegalArgumentException.class, () -> subject.update(CATEGORYID, CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, null, BARCODE, PRICE, PHOTO, PRODUCTION_CODE));
    }

    @Test
    void ensureCannotChangeBarcodeToNull() {
        final Product subject = buildProductWithProductionCode();

        assertThrows(IllegalArgumentException.class, () -> subject.update(CATEGORYID, CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, null, PRICE, PHOTO, PRODUCTION_CODE));
    }

    @Test
    void ensureCannotChangePriceToNull() {
        final Product subject = buildProductWithProductionCode();

        assertThrows(IllegalArgumentException.class, () -> subject.update(CATEGORYID, CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, null, PHOTO, PRODUCTION_CODE));
    }

    @Test
    void ensureCannotChangePhotoToNull() {
        final Product subject = buildProductWithProductionCode();

        Exception e = assertThrows(IllegalArgumentException.class, () -> subject.update(CATEGORYID, CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, PRICE, null, PRODUCTION_CODE));
        assertEquals("At least one of the required method parameters is null", e.getMessage());
    }

    @Test
    void ensureCanChangeCategory() {
        final Product subject = buildProductWithProductionCode();

        final Long newInfo = 654321L;

        subject.update(newInfo, CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, PRICE, PHOTO, PRODUCTION_CODE);
    }

    @Test
    void ensureCanChangeCode() {
        final Product subject = buildProductWithProductionCode();

        final Code newInfo = Code.valueOf("P0002");

        subject.update(CATEGORYID, newInfo, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, PRICE, PHOTO, PRODUCTION_CODE);
    }

    @Test
    void ensureCanChangeShortDescription() {
        final Product subject = buildProductWithProductionCode();

        final Description newInfo = Description.valueOf("Another short description");

        subject.update(CATEGORYID, CODE, newInfo, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, PRICE, PHOTO, PRODUCTION_CODE);
    }

    @Test
    void ensureCanChangeExtendedDescription() {
        final Product subject = buildProductWithProductionCode();

        final Description newInfo = Description.valueOf("Another extended description");

        subject.update(CATEGORYID, CODE, SHORT_DESCRIPTION, newInfo, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, PRICE, PHOTO, PRODUCTION_CODE);
    }

    @Test
    void ensureCanChangeTechnicalDescription() {
        final Product subject = buildProductWithProductionCode();

        final Description newInfo = Description.valueOf("Another technicalt description");

        subject.update(CATEGORYID, CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, newInfo, BRAND_NAME, REFERENCE, BARCODE, PRICE, PHOTO, PRODUCTION_CODE);
    }

    @Test
    void ensureCanChangeBrandName() {
        final Product subject = buildProductWithProductionCode();

        final BrandName newInfo = BrandName.valueOf("Another brand name");

        subject.update(CATEGORYID, CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, newInfo, REFERENCE, BARCODE, PRICE, PHOTO, PRODUCTION_CODE);
    }

    @Test
    void ensureCanChangeReference() {
        final Product subject = buildProductWithProductionCode();

        final Reference newInfo = Reference.valueOf("Another reference");

        subject.update(CATEGORYID, CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, newInfo, BARCODE, PRICE, PHOTO, PRODUCTION_CODE);
    }

    @Test
    void ensureCanChangeBarcode() {
        final Product subject = buildProductWithProductionCode();

        final Barcode newInfo = Barcode.valueOf(2L);

        subject.update(CATEGORYID, CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, newInfo, PRICE, PHOTO, PRODUCTION_CODE);
    }

    @Test
    void ensureCanChangePrice() {
        final Product subject = buildProductWithProductionCode();

        final Money newInfo = Money.valueOf("20 EUR");

        subject.update(CATEGORYID, CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, newInfo, PHOTO, PRODUCTION_CODE);
    }

    @Test
    void ensureCanChangePhoto() {
        final Product subject = buildProductWithProductionCode();

        byte[] newPhoto = {2, 0, 1};
        final Photo newInfo = Photo.valueOf(newPhoto);

        subject.update(CATEGORYID, CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, PRICE, newInfo, PRODUCTION_CODE);
    }

    @Test
    void ensureCanChangeProductionCode() {
        final Product subject = buildProductWithProductionCode();

        final ProductionCode newInfo = ProductionCode.valueOf("Another production code");

        subject.update(CATEGORYID, CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, PRICE, PHOTO, newInfo);
    }
}