package eapli.base.binmanagement.domain;

import eapli.framework.domain.model.DomainFactory;

public class BinBuilder implements DomainFactory<Bin> {

    private Bin theBin;

    private BinLocation binLocation;
    private Long productId;

    public BinBuilder withABinLocation(final BinLocation binLocation) {
        this.binLocation = binLocation;
        return this;
    }

    public BinBuilder withABinLocation(final int shelfId,final int rowId, final int aisleId) {
        return withABinLocation(BinLocation.valueOf(shelfId,rowId,aisleId));
    }

    public BinBuilder withAProductId(final Long productId) {
        this.productId = productId;
        return this;
    }

    public BinBuilder withAProductId(final String productId) {
        return withAProductId(Long.valueOf(productId));
    }

    private Bin buildOrThrow() {
        if (theBin != null) {
            return theBin;
        } else if (binLocation != null && productId != null) {
            theBin = new Bin(binLocation, productId);
            return theBin;
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public Bin build() {
        final Bin ret = buildOrThrow();
        // make sure we will create a new instance if someone reuses this builder and do not change
        // the previously built bin.
        theBin = null;
        return ret;
    }
}
