package eapli.base.warehousemanagement.DTO;


public class ShelfDTO {
    private final int aisleIdentifier;
    private final int rowId;
    private final int shelfIdentifier;

    public ShelfDTO(int aisleIdentifier, int rowId, int shelfIdentifier) {
        this.aisleIdentifier = aisleIdentifier;
        this.rowId = rowId;
        this.shelfIdentifier = shelfIdentifier;
    }

    @Override
    public String toString() {
        return "Shelf nº " +
                shelfIdentifier +
                " in row nº " +
                rowId +
                " in aisle nº " +
                aisleIdentifier;
    }

    public int aisle() {
        return aisleIdentifier;
    }

    public int row() {
        return rowId;
    }

    public int shelf() {
        return shelfIdentifier;
    }

}
