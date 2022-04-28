package eapli.base.productmanagement.domain;

import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductBuilderTest {

    private static final Long CATEGORY = 123456L;
    private static final Code CODE = Code.valueOf("P0001");
    private static final Description SHORT_DESCRIPTION = Description.valueOf("Test short description");
    private static final Description EXTENDED_DESCRIPTION = Description.valueOf("Test extended description");
    private static final Description TECHNICAL_DESCRIPTION = Description.valueOf("Test technical description");
    private static final BrandName BRAND_NAME = BrandName.valueOf("Test brand name");
    private static final Reference REFERENCE = Reference.valueOf("Teste reference");
    private static final Barcode BARCODE = Barcode.valueOf(1L);
    private static final Money PRICE = Money.valueOf("10 EUR");
    private static final byte[] PHOTO = new byte[0];
    private static final ProductionCode PRODUCTION_CODE = ProductionCode.valueOf("PC1");


    private Product buildProductWithoutProductionCode() {
        return new ProductBuilder().withACategoryId(CATEGORY).coded(CODE).withAShortDescription(SHORT_DESCRIPTION).withAnExtendedDescription(EXTENDED_DESCRIPTION).withATechnicalDescription(TECHNICAL_DESCRIPTION).withABrandName(BRAND_NAME).withAReference(REFERENCE).withABarcode(BARCODE).withAPrice(PRICE).withAPhoto(PHOTO).build();
    }

    private Product buildProductWithProductionCode() {
        return new ProductBuilder().withACategoryId(CATEGORY).coded(CODE).withAShortDescription(SHORT_DESCRIPTION).withAnExtendedDescription(EXTENDED_DESCRIPTION).withATechnicalDescription(TECHNICAL_DESCRIPTION).withABrandName(BRAND_NAME).withAReference(REFERENCE).withABarcode(BARCODE).withAPrice(PRICE).withAPhoto(PHOTO).withAProductionCode(PRODUCTION_CODE).build();
    }

    @Test
    public void ensureCanBuildProductWithoutOnlyProductionCode() {
        final Product subject = new ProductBuilder().withACategoryId(CATEGORY).coded(CODE).withAShortDescription(SHORT_DESCRIPTION).withAnExtendedDescription(EXTENDED_DESCRIPTION).withATechnicalDescription(TECHNICAL_DESCRIPTION).withABrandName(BRAND_NAME).withAReference(REFERENCE).withABarcode(BARCODE).withAPrice(PRICE).withAPhoto(PHOTO).build();

        assertNotNull(subject);
    }

    @Test
    public void ensureCanBuildProduct() {
        final Product subject = new ProductBuilder().withACategoryId(CATEGORY).coded(CODE).withAShortDescription(SHORT_DESCRIPTION).withAnExtendedDescription(EXTENDED_DESCRIPTION).withATechnicalDescription(TECHNICAL_DESCRIPTION).withABrandName(BRAND_NAME).withAReference(REFERENCE).withABarcode(BARCODE).withAPrice(PRICE).withAPhoto(PHOTO).withAProductionCode(PRODUCTION_CODE).build();

        assertNotNull(subject);
    }

    @Test
    void ensureCannotBuildProductWithCategoryNull() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new ProductBuilder().withACategoryId(Long.valueOf("")).coded(CODE).withAShortDescription(SHORT_DESCRIPTION).withAnExtendedDescription(EXTENDED_DESCRIPTION).withATechnicalDescription(TECHNICAL_DESCRIPTION).withABrandName(BRAND_NAME).withAReference(REFERENCE).withABarcode(BARCODE).withAPrice(PRICE).withAPhoto(PHOTO).build());
        assertEquals("For input string: \"\"", exception.getMessage());
    }

    @Test
    void ensureCannotBuildProductWithCodeNull() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new ProductBuilder().withACategoryId(CATEGORY).coded(Code.valueOf("")).withAShortDescription(SHORT_DESCRIPTION).withAnExtendedDescription(EXTENDED_DESCRIPTION).withATechnicalDescription(TECHNICAL_DESCRIPTION).withABrandName(BRAND_NAME).withAReference(REFERENCE).withABarcode(BARCODE).withAPrice(PRICE).withAPhoto(PHOTO).build());
        assertEquals("Code should neither be null nor empty", exception.getMessage());
    }

    @Test
    void ensureCannotBuildProductWithShortDescriptionNull() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new ProductBuilder().withACategoryId(CATEGORY).coded(CODE).withAShortDescription(Description.valueOf("")).withAnExtendedDescription(EXTENDED_DESCRIPTION).withATechnicalDescription(TECHNICAL_DESCRIPTION).withABrandName(BRAND_NAME).withAReference(REFERENCE).withABarcode(BARCODE).withAPrice(PRICE).withAPhoto(PHOTO).build());
        assertEquals("Description should neither be null nor empty", exception.getMessage());
    }

    @Test
    void ensureCannotBuildProductWithExtendedDescriptionNull() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new ProductBuilder().withACategoryId(CATEGORY).coded(CODE).withAShortDescription(SHORT_DESCRIPTION).withAnExtendedDescription(Description.valueOf("")).withATechnicalDescription(TECHNICAL_DESCRIPTION).withABrandName(BRAND_NAME).withAReference(REFERENCE).withABarcode(BARCODE).withAPrice(PRICE).withAPhoto(PHOTO).build());
        assertEquals("Description should neither be null nor empty", exception.getMessage());
    }

    @Test
    void ensureCannotBuildProductWithTechicalDescriptionNull() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new ProductBuilder().withACategoryId(CATEGORY).coded(CODE).withAShortDescription(SHORT_DESCRIPTION).withAnExtendedDescription(EXTENDED_DESCRIPTION).withATechnicalDescription(Description.valueOf("")).withABrandName(BRAND_NAME).withAReference(REFERENCE).withABarcode(BARCODE).withAPrice(PRICE).withAPhoto(PHOTO).build());
        assertEquals("Description should neither be null nor empty", exception.getMessage());
    }

    @Test
    void ensureCannotBuildProductWithBrandNameNull() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new ProductBuilder().withACategoryId(CATEGORY).coded(CODE).withAShortDescription(SHORT_DESCRIPTION).withAnExtendedDescription(EXTENDED_DESCRIPTION).withATechnicalDescription(TECHNICAL_DESCRIPTION).withABrandName(BrandName.valueOf("")).withAReference(REFERENCE).withABarcode(BARCODE).withAPrice(PRICE).withAPhoto(PHOTO).build());
        assertEquals("Brand name should neither be null nor empty", exception.getMessage());
    }

    @Test
    void ensureCannotBuildProductWithReferenceNull() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new ProductBuilder().withACategoryId(CATEGORY).coded(CODE).withAShortDescription(SHORT_DESCRIPTION).withAnExtendedDescription(EXTENDED_DESCRIPTION).withATechnicalDescription(TECHNICAL_DESCRIPTION).withABrandName(BRAND_NAME).withAReference(Reference.valueOf("")).withABarcode(BARCODE).withAPrice(PRICE).withAPhoto(PHOTO).build());
        assertEquals("Reference should neither be null nor empty", exception.getMessage());
    }

    @Test
    void ensureCannotBuildProductWithBarcodeNull() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new ProductBuilder().withACategoryId(CATEGORY).coded(CODE).withAShortDescription(SHORT_DESCRIPTION).withAnExtendedDescription(EXTENDED_DESCRIPTION).withATechnicalDescription(TECHNICAL_DESCRIPTION).withABrandName(BRAND_NAME).withAReference(REFERENCE).withABarcode(Barcode.valueOf(Long.valueOf(""))).withAPrice(PRICE).withAPhoto(PHOTO).build());
        assertEquals("For input string: \"\"", exception.getMessage());
    }

    @Test
    void ensureCannotBuildProductWithPriceNull() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new ProductBuilder().withACategoryId(CATEGORY).coded(CODE).withAShortDescription(SHORT_DESCRIPTION).withAnExtendedDescription(EXTENDED_DESCRIPTION).withATechnicalDescription(TECHNICAL_DESCRIPTION).withABrandName(BRAND_NAME).withAReference(REFERENCE).withABarcode(BARCODE).withAPrice(Money.valueOf("")).withAPhoto(PHOTO).build());
        assertNull(exception.getMessage());
    }
}