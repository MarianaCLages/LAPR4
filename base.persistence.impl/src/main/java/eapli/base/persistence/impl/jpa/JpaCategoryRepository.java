package eapli.base.persistence.impl.jpa;

import eapli.base.categorymanagement.domain.AlphaNumericCode;
import eapli.base.categorymanagement.domain.Category;
import eapli.base.categorymanagement.repositories.CategoryRepository;
import eapli.framework.general.domain.model.Description;

import javax.persistence.TypedQuery;
import java.util.List;

public class JpaCategoryRepository extends BasepaRepositoryBase<Category,Long,Long> implements CategoryRepository {

    public JpaCategoryRepository() { super("id");}

    @Override
    public Category findByCode(AlphaNumericCode code) {
        final TypedQuery<Category> q = createQuery("SELECT e FROM Category e WHERE  e.code = :m",
                Category.class);

        q.setParameter("m", code);
        return q.getSingleResult();
    }

    @Override
    public Category findById(long id) {
        final TypedQuery<Category> q = createQuery("SELECT e FROM Category e WHERE  e.categoryId = :m",
                Category.class);

        q.setParameter("m", id);
        return q.getSingleResult();
    }

    @Override
    public Category findByDescription(Description description) {
        final TypedQuery<Category> q = createQuery("SELECT e FROM Category e WHERE  e.description = :m",
                Category.class);

        q.setParameter("m", description.toString());
        return q.getSingleResult();
    }

    @Override
    public List<Category> findAll() {
        final TypedQuery<Category> q = createQuery("SELECT * FROM Category",
                Category.class);

        return q.getResultList();
    }
}
