package eapli.base.productmanagement.domain;

import eapli.framework.domain.model.DomainFactory;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Money;


public class ProductBuilder implements DomainFactory<Product> {

    private Product theProduct;

    private Long categoryId;
    private Code code;
    private Description shortDescription;
    private Description extendedDescription;
    private Description technicalDescription;
    private BrandName brandName;
    private Reference reference;
    private Barcode barcode;
    private Money price;
    private Photo photo;
    private ProductionCode productionCode;

    public ProductBuilder withACategoryId(final Long categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public ProductBuilder withACategoryId(final String categoryId) {
        return withACategoryId(Long.valueOf(categoryId));
    }

    public ProductBuilder coded(final Code code) {
        this.code = code;
        return this;
    }

    public ProductBuilder coded(final String code) {
        return coded(Code.valueOf(code));
    }

    public ProductBuilder withAShortDescription(final Description shortDescription) {
        this.shortDescription = shortDescription;
        return this;
    }

    public ProductBuilder withAShortDescription(final String shortDescription) {
        return withAShortDescription(Description.valueOf(shortDescription));
    }

    public ProductBuilder withAnExtendedDescription(final Description extendedDescription) {
        this.extendedDescription = extendedDescription;
        return this;
    }

    public ProductBuilder withAnExtendedDescription(final String extendedDescription) {
        return withAnExtendedDescription(Description.valueOf(extendedDescription));
    }

    public ProductBuilder withATechnicalDescription(final Description technicalDescription) {
        this.technicalDescription = technicalDescription;
        return this;
    }

    public ProductBuilder withATechnicalDescription(final String technicalDescription) {
        return withATechnicalDescription(Description.valueOf(technicalDescription));
    }

    public ProductBuilder withABrandName(final BrandName brandName) {
        this.brandName = brandName;
        return this;
    }

    public ProductBuilder withABrandName(final String brandName) {
        return withABrandName(BrandName.valueOf(brandName));
    }

    public ProductBuilder withAReference(final Reference reference) {
        this.reference = reference;
        return this;
    }

    public ProductBuilder withAReference(final String reference) {
        return withAReference(Reference.valueOf(reference));
    }

    public ProductBuilder withABarcode(final Barcode barcode) {
        this.barcode = barcode;
        return this;
    }

    public ProductBuilder withABarcode(final Long barcode) {
        return withABarcode(Barcode.valueOf(barcode));
    }

    public ProductBuilder withAPrice(final Money price) {
        this.price = price;
        return this;
    }

    public ProductBuilder withAPrice(final String price) {
        return withAPrice(Money.valueOf(price));
    }

    public ProductBuilder withAPhoto(Photo photo) {
        this.photo = photo;
        return this;
    }

    public ProductBuilder withAPhoto(final byte[] photo) {
        return withAPhoto(Photo.valueOf(photo));
    }

    public ProductBuilder withAProductionCode(final ProductionCode productionCode) {
        this.productionCode = productionCode;
        return this;
    }

    public ProductBuilder withAProductionCode(final String productionCode) {
        return withAProductionCode(ProductionCode.valueOf(productionCode));
    }

    private Product buildOrThrow() {
        if (theProduct != null) {
            return theProduct;
        } else if (categoryId != null && code != null && shortDescription != null && extendedDescription != null && technicalDescription != null && brandName != null && reference != null && barcode != null && price != null && photo != null) {
            theProduct = new Product(categoryId, code, shortDescription, extendedDescription, technicalDescription, brandName, reference, barcode, price, photo);
            return theProduct;
        } else if (categoryId != null && code != null && shortDescription != null && extendedDescription != null && technicalDescription != null && brandName != null && reference != null && barcode != null && price != null && photo != null && productionCode != null) {
            theProduct = new Product(categoryId, code, shortDescription, extendedDescription, technicalDescription, brandName, reference, barcode, price, photo, productionCode);
            return theProduct;
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public Product build() {
        final Product ret = buildOrThrow();
        // make sure we will create a new instance if someone reuses this builder and do not change
        // the previously built product.
        theProduct = null;
        return ret;
    }
}
