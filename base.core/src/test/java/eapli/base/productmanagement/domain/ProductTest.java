package eapli.base.productmanagement.domain;

import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Money;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    private static final Long CATEGORY_ID = 123456L;
    private static final Code CODE = Code.valueOf("P0001");
    private static final Description SHORT_DESCRIPTION = Description.valueOf("Test short description");
    private static final Description EXTENDED_DESCRIPTION = Description.valueOf("Test extended description");
    private static final Description TECHNICAL_DESCRIPTION = Description.valueOf("Test technical description");
    private static final BrandName BRAND_NAME = BrandName.valueOf("Test brand name");
    private static final Reference REFERENCE = Reference.valueOf("Teste reference");
    private static final Barcode BARCODE = Barcode.valueOf(1L);
    private static final Money PRICE = Money.valueOf("10 EUR");
    private static final List<Photo> PHOTO_LIST = new ArrayList<>();
    private static final ProductionCode PRODUCTION_CODE = ProductionCode.valueOf("PC1");

    private Product buildProductWithoutProductionCode() {
        return new ProductBuilder().withACategoryId(CATEGORY_ID).coded(CODE).withAShortDescription(SHORT_DESCRIPTION).withAnExtendedDescription(EXTENDED_DESCRIPTION).withATechnicalDescription(TECHNICAL_DESCRIPTION).withABrandName(BRAND_NAME).withAReference(REFERENCE).withABarcode(BARCODE).withAPrice(PRICE).withASetOfPhotos(PHOTO_LIST).build();
    }

    private Product buildProductWithProductionCode() {
        return new ProductBuilder().withACategoryId(CATEGORY_ID).coded(CODE).withAShortDescription(SHORT_DESCRIPTION).withAnExtendedDescription(EXTENDED_DESCRIPTION).withATechnicalDescription(TECHNICAL_DESCRIPTION).withABrandName(BRAND_NAME).withAReference(REFERENCE).withABarcode(BARCODE).withAPrice(PRICE).withASetOfPhotos(PHOTO_LIST).withAProductionCode(PRODUCTION_CODE).build();
    }

    @Test
    void ensureCanBuildProductWithoutOnlyProductionCode() {
        new Product(CATEGORY_ID, CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, PRICE, PHOTO_LIST);

        assertTrue(true);
    }

    @Test
    void ensureCanBuildProduct() {
        new Product(CATEGORY_ID, CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, PRICE, PHOTO_LIST, PRODUCTION_CODE);

        assertTrue(true);
    }

    @Test
    void ensureMustHaveCategoryId() {
        assertThrows(IllegalArgumentException.class, () -> new Product(null, CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, PRICE, PHOTO_LIST));
    }

    @Test
    void ensureMustHaveCode() {
        assertThrows(IllegalArgumentException.class, () -> new Product(CATEGORY_ID, null, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, PRICE, PHOTO_LIST));
    }

    @Test
    void ensureMustHaveShortDescription() {
        assertThrows(IllegalArgumentException.class, () -> new Product(CATEGORY_ID, CODE, null, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, PRICE, PHOTO_LIST));
    }

    @Test
    public void ensureMustHaveExtendedDescription() {
        assertThrows(IllegalArgumentException.class, () -> new Product(CATEGORY_ID, CODE, SHORT_DESCRIPTION, null, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, PRICE, PHOTO_LIST));
    }

    @Test
    void ensureMustHaveTechnicalDescription() {
        assertThrows(IllegalArgumentException.class, () -> new Product(CATEGORY_ID, CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, null, BRAND_NAME, REFERENCE, BARCODE, PRICE, PHOTO_LIST));
    }

    @Test
    public void ensureMustHaveBrandName() {
        assertThrows(IllegalArgumentException.class, () -> new Product(CATEGORY_ID, CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, null, REFERENCE, BARCODE, PRICE, PHOTO_LIST));
    }

    @Test
    public void ensureMustHaveReference() {
        assertThrows(IllegalArgumentException.class, () -> new Product(CATEGORY_ID, CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, null, BARCODE, PRICE, PHOTO_LIST));
    }

    @Test
    public void ensureMustHaveBarcode() {
        assertThrows(IllegalArgumentException.class, () -> new Product(CATEGORY_ID, CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, null, PRICE, PHOTO_LIST));
    }

    @Test
    void ensureMustHavePrice() {
        assertThrows(IllegalArgumentException.class, () -> new Product(CATEGORY_ID, CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, null, PHOTO_LIST));
    }

    @Test
    public void ensureMustHavePhoto() {
        assertThrows(IllegalArgumentException.class, () -> new Product(CATEGORY_ID, CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, PRICE, null));
    }

    @Test
    public void ensureCannotChangeCategoryIdToNull() {
        final Product subject = buildProductWithoutProductionCode();

        assertThrows(IllegalArgumentException.class, () -> subject.update(null, CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, PRICE, PHOTO_LIST, PRODUCTION_CODE));
    }

    @Test
    public void ensureCannotChangeCodeToNull() {
        final Product subject = buildProductWithoutProductionCode();

        assertThrows(IllegalArgumentException.class, () -> subject.update(CATEGORY_ID, null, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, PRICE, PHOTO_LIST, PRODUCTION_CODE));
    }

    @Test
    public void ensureCannotChangeShortDescriptionToNull() {
        final Product subject = buildProductWithoutProductionCode();

        assertThrows(IllegalArgumentException.class, () -> subject.update(CATEGORY_ID, CODE, null, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, PRICE, PHOTO_LIST, PRODUCTION_CODE));
    }

    @Test
    void ensureCannotChangeExtendedDescriptionToNull() {
        final Product subject = buildProductWithProductionCode();

        assertThrows(IllegalArgumentException.class, () -> subject.update(CATEGORY_ID, CODE, SHORT_DESCRIPTION, null, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, PRICE, PHOTO_LIST, PRODUCTION_CODE));
    }

    @Test
    void ensureCannotChangeTechnicalDescriptionToNull() {
        final Product subject = buildProductWithProductionCode();

        assertThrows(IllegalArgumentException.class, () -> subject.update(CATEGORY_ID, CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, null, BRAND_NAME, REFERENCE, BARCODE, PRICE, PHOTO_LIST, PRODUCTION_CODE));
    }

    @Test
    void ensureCannotChangeBrandNameToNull() {
        final Product subject = buildProductWithProductionCode();

        assertThrows(IllegalArgumentException.class, () -> subject.update(CATEGORY_ID, CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, null, REFERENCE, BARCODE, PRICE, PHOTO_LIST, PRODUCTION_CODE));
    }

    @Test
    void ensureCannotChangeReferenceToNull() {
        final Product subject = buildProductWithProductionCode();

        assertThrows(IllegalArgumentException.class, () -> subject.update(CATEGORY_ID, CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, null, BARCODE, PRICE, PHOTO_LIST, PRODUCTION_CODE));
    }

    @Test
    void ensureCannotChangeBarcodeToNull() {
        final Product subject = buildProductWithProductionCode();

        assertThrows(IllegalArgumentException.class, () -> subject.update(CATEGORY_ID, CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, null, PRICE, PHOTO_LIST, PRODUCTION_CODE));
    }

    @Test
    void ensureCannotChangePriceToNull() {
        final Product subject = buildProductWithProductionCode();

        assertThrows(IllegalArgumentException.class, () -> subject.update(CATEGORY_ID, CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, null, PHOTO_LIST, PRODUCTION_CODE));
    }

    @Test
    void ensureCannotChangePhotoToNull() {
        final Product subject = buildProductWithProductionCode();

        Exception e = assertThrows(IllegalArgumentException.class, () -> subject.update(CATEGORY_ID, CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, PRICE, null, PRODUCTION_CODE));
        assertEquals("At least one of the required method parameters is null", e.getMessage());
    }

    @Test
    void ensureCanChangeCategory() {
        final Product subject = buildProductWithProductionCode();

        final Long newInfo = 654321L;

        subject.update(newInfo, CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, PRICE, PHOTO_LIST, PRODUCTION_CODE);
    }

    @Test
    void ensureCanChangeCode() {
        final Product subject = buildProductWithProductionCode();

        final Code newInfo = Code.valueOf("P0002");

        subject.update(CATEGORY_ID, newInfo, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, PRICE, PHOTO_LIST, PRODUCTION_CODE);
    }

    @Test
    void ensureCanChangeShortDescription() {
        final Product subject = buildProductWithProductionCode();

        final Description newInfo = Description.valueOf("Another short description");

        subject.update(CATEGORY_ID, CODE, newInfo, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, PRICE, PHOTO_LIST, PRODUCTION_CODE);
    }

    @Test
    void ensureCanChangeExtendedDescription() {
        final Product subject = buildProductWithProductionCode();

        final Description newInfo = Description.valueOf("Another extended description");

        subject.update(CATEGORY_ID, CODE, SHORT_DESCRIPTION, newInfo, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, PRICE, PHOTO_LIST, PRODUCTION_CODE);
    }

    @Test
    void ensureCanChangeTechnicalDescription() {
        final Product subject = buildProductWithProductionCode();

        final Description newInfo = Description.valueOf("Another technicalt description");

        subject.update(CATEGORY_ID, CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, newInfo, BRAND_NAME, REFERENCE, BARCODE, PRICE, PHOTO_LIST, PRODUCTION_CODE);
    }

    @Test
    void ensureCanChangeBrandName() {
        final Product subject = buildProductWithProductionCode();

        final BrandName newInfo = BrandName.valueOf("Another brand name");

        subject.update(CATEGORY_ID, CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, newInfo, REFERENCE, BARCODE, PRICE, PHOTO_LIST, PRODUCTION_CODE);
    }

    @Test
    void ensureCanChangeReference() {
        final Product subject = buildProductWithProductionCode();

        final Reference newInfo = Reference.valueOf("Another reference");

        subject.update(CATEGORY_ID, CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, newInfo, BARCODE, PRICE, PHOTO_LIST, PRODUCTION_CODE);
    }

    @Test
    void ensureCanChangeBarcode() {
        final Product subject = buildProductWithProductionCode();

        final Barcode newInfo = Barcode.valueOf(2L);

        subject.update(CATEGORY_ID, CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, newInfo, PRICE, PHOTO_LIST, PRODUCTION_CODE);
    }

    @Test
    void ensureCanChangePrice() {
        final Product subject = buildProductWithProductionCode();

        final Money newInfo = Money.valueOf("20 EUR");

        subject.update(CATEGORY_ID, CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, newInfo, PHOTO_LIST, PRODUCTION_CODE);
    }

    @Test
    void ensureCanChangePhoto() {
        final Product subject = buildProductWithProductionCode();

        final  List<Photo> newPhotoList = new ArrayList<>();

        subject.update(CATEGORY_ID, CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, PRICE, newPhotoList, PRODUCTION_CODE);
    }

    @Test
    void ensureCanChangeProductionCode() {
        final Product subject = buildProductWithProductionCode();

        final ProductionCode newInfo = ProductionCode.valueOf("Another production code");

        subject.update(CATEGORY_ID, CODE, SHORT_DESCRIPTION, EXTENDED_DESCRIPTION, TECHNICAL_DESCRIPTION, BRAND_NAME, REFERENCE, BARCODE, PRICE, PHOTO_LIST, newInfo);
    }
}