package eapli.base.binmanagement.dto;

import eapli.framework.representations.dto.DTO;

@DTO
public class BinDTO {

    private String shelfId;
    private String productId;

    public BinDTO(final String shelfId, String productId) {
        this.shelfId = shelfId;
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "Shelf Id: " + shelfId + '\n' +
                "Product Id: " + productId;
    }
}
