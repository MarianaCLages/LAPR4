package eapli.base.persistence.impl.inmemory;

import eapli.base.binmanagement.domain.Bin;
import eapli.base.binmanagement.repositories.BinRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainAutoNumberRepository;

public class InMemoryBinRepository extends InMemoryDomainAutoNumberRepository<Bin> implements BinRepository {
    private static final String NOT_SUPPORTED_YET = "Not supported yet.";

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Bin findById(long id) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }

    @Override
    public void updateBin(Bin b) {
    }
}
