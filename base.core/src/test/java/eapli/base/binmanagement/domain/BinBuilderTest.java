package eapli.base.binmanagement.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinBuilderTest {

    private static final BinLocation BIN_LOCATION = BinLocation.valueOf(1,1,1);
    private static final Long PRODUCT_ID = 1L;


    private Bin buildBin() {
        return new BinBuilder().withABinLocation(BIN_LOCATION).withAProductId(PRODUCT_ID).build();
    }

    @Test
    public void ensureCanBuildBin() {
        final Bin subject = new BinBuilder().withABinLocation(BIN_LOCATION).withAProductId(PRODUCT_ID).build();
    }

    @Test
    void ensureCannotBuildBinWithProductIdNull() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new BinBuilder().withABinLocation(BIN_LOCATION).withAProductId(Long.valueOf("")));
        assertEquals("For input string: \"\"", exception.getMessage());
    }
}