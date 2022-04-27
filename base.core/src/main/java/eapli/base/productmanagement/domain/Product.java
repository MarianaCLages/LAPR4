package eapli.base.productmanagement.domain;

import eapli.base.productmanagement.dto.ProductDTO;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Money;
import eapli.framework.representations.RepresentationBuilder;
import eapli.framework.representations.Representationable;
import eapli.framework.representations.dto.DTOable;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class Product implements AggregateRoot<Long>, DTOable<ProductDTO>, Representationable {

    private static final long serialVersionUID = 210702L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;

    @Version
    private Long version;

    @Column(nullable = false)
    private Code code;

    @AttributeOverride(name = "value", column = @Column)
    @Column(nullable = false)
    private Description shortDescription;

    @AttributeOverride(name = "value", column = @Column(name = "extended_description"))
    @Column(nullable = false)
    private Description extendedDescription;

    @AttributeOverride(name = "value", column = @Column(name = "technical_description"))
    @Column(nullable = false)
    private Description technicalDescription;

    @Column(nullable = false)
    private BrandName brandName;

    @Column(nullable = false)
    private Reference reference;

    @Column(nullable = false)
    private Barcode barcode;

    @Column(nullable = false)
    private Money price;

    @Column(nullable = false, length = 99999)
    private Photo photo;

    @Column
    private ProductionCode productionCode;

    protected Product() {
        // For ORM only
    }

    public Product(final Code code, final Description shortDescription, final Description extendedDescription, final Description technicalDescription, final BrandName brandName, final Reference reference, final Barcode barcode, final Money price, final Photo photo) {
        Preconditions.noneNull(code, shortDescription, extendedDescription, technicalDescription, brandName, reference, barcode, price, photo);

        this.code = code;
        this.shortDescription = shortDescription;
        this.extendedDescription = extendedDescription;
        this.technicalDescription = technicalDescription;
        this.brandName = brandName;
        this.reference = reference;
        this.barcode = barcode;
        this.price = price;
        this.photo = photo;
    }

    public Product(final Code code, final Description shortDescription, final Description extendedDescription, final Description technicalDescription, final BrandName brandName, final Reference reference, final Barcode barcode, final Money price, final Photo photo, final  ProductionCode productionCode) {
        Preconditions.noneNull(code, shortDescription, extendedDescription, technicalDescription, brandName, reference, barcode, price, photo);

        this.code = code;
        this.shortDescription = shortDescription;
        this.extendedDescription = extendedDescription;
        this.technicalDescription = technicalDescription;
        this.brandName = brandName;
        this.reference = reference;
        this.barcode = barcode;
        this.price = price;
        this.photo = photo;
        this.productionCode = productionCode;
    }

    @Override
    public Long identity() {
        return this.productId;
    }

    @Override
    public boolean sameAs(final Object other) {
        if (!(other instanceof Product)) {
            return false;
        }

        final Product that = (Product) other;
        if (this == that) {
            return true;
        }

        return identity().equals(that.identity()) && code.equals(that.code)
                && shortDescription.equals(that.shortDescription)
                && extendedDescription.equals(that.extendedDescription)
                && technicalDescription.equals(that.technicalDescription)
                && brandName.equals(that.brandName)
                && reference.equals(that.reference)
                && barcode.equals(that.barcode)
                && price.equals(that.price)
                && photo.equals(that.photo);
    }

    @Override
    public <R> R buildRepresentation(RepresentationBuilder<R> builder) {
        builder.startObject("Product").withProperty("code", String.valueOf(code))
                .withProperty("short description", shortDescription)
                .withProperty("extended description", extendedDescription)
                .withProperty("technical description", technicalDescription)
                .withProperty("brand name", String.valueOf(brandName))
                .withProperty("reference", String.valueOf(reference))
                .withProperty("barcode", String.valueOf(barcode))
                .withProperty("price", price)
                .withProperty("photo", String.valueOf(photo))
                .withProperty("production code", String.valueOf(productionCode));

        return builder.build();
    }

    @Override
    public boolean equals(Object o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public ProductDTO toDTO() {
        return new ProductDTO(code.toString(), shortDescription.toString(), extendedDescription.toString(), brandName.toString(), reference.toString(), price);
    }

    private void changeCode(final Code code) {
        if (code == null) {
            throw new IllegalArgumentException();
        }
        this.code = code;
    }

    private void changeShortDescription(final Description shortDescription) {
        if (shortDescription == null) {
            throw new IllegalArgumentException();
        }
        this.shortDescription = shortDescription;
    }

    private void changeExtendedDescription(final Description extendedDescription) {
        if (extendedDescription == null) {
            throw new IllegalArgumentException();
        }
        this.extendedDescription = extendedDescription;
    }

    private void changeTechnicalDescription(final Description technicalDescription) {
        if (technicalDescription == null) {
            throw new IllegalArgumentException();
        }
        this.technicalDescription = technicalDescription;
    }

    private void changeBrandName(final BrandName brandName) {
        if (brandName == null) {
            throw new IllegalArgumentException();
        }
        this.brandName = brandName;
    }

    private void changeReference(final Reference reference) {
        if (reference == null) {
            throw new IllegalArgumentException();
        }
        this.reference = reference;
    }

    private void changeBarcode(final Barcode barcode) {
        if (barcode == null) {
            throw new IllegalArgumentException();
        }
        this.barcode = barcode;
    }

    private void changePrice(final Money price) {
        if (price == null) {
            throw new IllegalArgumentException();
        }
        this.price = price;
    }

    private void changePhoto(final Photo photo) {
        if (photo == null) {
            throw new IllegalArgumentException();
        }
        this.photo = photo;
    }

    private void changeProductionCode(final ProductionCode productionCode) {
        if (productionCode == null) {
            throw new IllegalArgumentException();
        }
        this.productionCode = productionCode;
    }

    public void update(final Code code, final Description shortDescription, final Description extendedDescription, final Description technicalDescription, final BrandName brandName, final Reference reference, final Barcode barcode, final Money price, final Photo photo, final ProductionCode productionCode) {
        Preconditions.noneNull(code, shortDescription, extendedDescription, technicalDescription, brandName, reference, barcode, price, photo);

        changeCode(code);
        changeShortDescription(shortDescription);
        changeExtendedDescription(extendedDescription);
        changeBrandName(brandName);
        changeReference(reference);
        changeBarcode(barcode);
        changePrice(price);
        changePhoto(photo);
        changeProductionCode(productionCode);
    }
}
