package eapli.base.productmanagement.dto;

import eapli.framework.general.domain.model.Money;
import eapli.framework.representations.dto.DTO;

@DTO
public class ProductDTO {

    private String code;
    private String shortDescription;
    private String extendedDescription;
    private String brandName;
    private String reference;
    private Money price;

    public ProductDTO(final String code, final String shortDescription, final String extendedDescription, final String brandName, final String reference, final Money price) {
        this.code = code;
        this.shortDescription = shortDescription;
        this.extendedDescription = extendedDescription;
        this.brandName = brandName;
        this.reference = reference;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Code: " + code + '\n' +
                "Short description: " + shortDescription + '\n' +
                "Extended description: " + extendedDescription + '\n' +
                "Brand name: " + brandName + '\n' +
                "Reference: " + reference + '\n' +
                "Price: " + price.toSimpleString();
    }
}
