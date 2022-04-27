package eapli.base.persistence.impl.inmemory;

import eapli.base.categorymanagement.domain.AlphaNumericCode;
import eapli.base.categorymanagement.domain.Category;
import eapli.base.categorymanagement.repositories.CategoryRepository;
import eapli.framework.general.domain.model.Description;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainAutoNumberRepository;

import java.util.List;

public class InMemoryCategoryRepository extends InMemoryDomainAutoNumberRepository<Category> implements CategoryRepository {

    private static final String NOT_SUPPORTED_YET = "Not supported yet.";

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Category findByCode(AlphaNumericCode code) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }

    @Override
    public Category findById(long id) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }

    @Override
    public Category findByDescription(Description description) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }

    @Override
    public List<Category> findAll() {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }

    @Override
    public Long findByCodeAndReturnId(String code) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }
}
