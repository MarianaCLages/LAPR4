package eapli.base.categorymanagement.dto;

import eapli.framework.representations.dto.DTO;

@DTO
public class CategoryDTO {

    private String alphaNumericCode;
    private String description;

    public CategoryDTO(final String alphaNumericCode, final String description) {
        this.alphaNumericCode = alphaNumericCode;
        this.description = description;
    }

    public String getAlphaNumericCode() {
        return alphaNumericCode;
    }

    @Override
    public String toString() {
        return "Category ->" +
                " [Code='" + alphaNumericCode.toString() + '\'' +
                ", Description='" + description.toString() + '\'' + "]";
    }
}
