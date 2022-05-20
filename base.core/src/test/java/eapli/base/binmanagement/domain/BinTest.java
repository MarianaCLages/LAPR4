package eapli.base.binmanagement.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinTest {

    private static final BinLocation BIN_LOCATION = BinLocation.valueOf(1,1,1);
    private static final Long PRODUCT_ID = 1L;

    private Bin buildBin() {
        return new BinBuilder().withABinLocation(BIN_LOCATION).withAProductId(PRODUCT_ID).build();
    }

    @Test
    void ensureCanBuildBin() {
        new Bin(BIN_LOCATION, PRODUCT_ID);
        assertTrue(true);
    }

    @Test
    void ensureMustHaveLocation() {
        assertThrows(IllegalArgumentException.class, () -> new Bin(null, PRODUCT_ID));
    }

    @Test
    void ensureMustHaveProductId() {
        assertThrows(IllegalArgumentException.class, () -> new Bin(BIN_LOCATION, null));
    }

    @Test
    void ensureCannotChangeLocationToNull() {
        final Bin subject = buildBin();

        assertThrows(IllegalArgumentException.class, () -> subject.update(null, PRODUCT_ID));
    }

    @Test
    void ensureCannotChangeProductIdToNull() {
        final Bin subject = buildBin();

        assertThrows(IllegalArgumentException.class, () -> subject.update(BIN_LOCATION, null));
    }

    @Test
    void ensureCanChangeLocation() {
        final Bin subject = buildBin();

        final BinLocation newInfo = BinLocation.valueOf(2,2,2);

        subject.update(newInfo, PRODUCT_ID);
    }
}