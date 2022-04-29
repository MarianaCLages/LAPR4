package eapli.base.persistence.impl.inmemory;

import eapli.base.productmanagement.domain.*;
import eapli.base.productmanagement.repositories.ProductRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainAutoNumberRepository;

import java.util.List;

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
    public List<Product> findByBrandName(String brandName) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }

    @Override
    public List<Product> findByReference(String reference) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }

    @Override
    public Product findByBarcode(Long barcode) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }

    @Override
    public Product findByProductionCode(ProductionCode productionCode) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }

    @Override
    public List<Product> findByCategoryAlphaCode(String code) {
        return null;
    }

    @Override
    public List<Product> findBySinglePrice(String price) {
        return null;
    }

    @Override
    public List<Product> findByAllInsideLimitPrice(List<String> price) {
        return null;
    }

    @Override
    public Product findByShortDescription(String productionCode) {
        return null;
    }

    @Override
    public Product findByExtendedDescription(String description) {
        return null;
    }

    @Override
    public Product findByTechnicalDescription(String description) {
        return null;
    }
}
