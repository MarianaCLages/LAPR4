package eapli.base.persistence.impl.inmemory;

import eapli.base.productmanagement.domain.*;
import eapli.base.productmanagement.repositories.ProductRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainAutoNumberRepository;

public class InMemoryProductRepository extends InMemoryDomainAutoNumberRepository<Product> implements ProductRepository {
    private static final String NOT_SUPPORTED_YET = "Not supported yet.";

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Product findById(long id) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }

    @Override
    public Product findByCode(Code code) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }

    @Override
    public Product findByBrandName(BrandName brandName) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }

    @Override
    public Product findByReference(Reference reference) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }

    @Override
    public Product findByBarcode(Barcode barcode) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }

    @Override
    public Product findByProductionCode(ProductionCode productionCode) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }
}
