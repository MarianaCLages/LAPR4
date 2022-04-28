package eapli.base.persistence.impl.jpa;

import eapli.base.categorymanagement.domain.AlphaNumericCode;
import eapli.base.productmanagement.domain.*;
import eapli.base.productmanagement.repositories.ProductRepository;

import javax.persistence.TypedQuery;
import java.util.List;

public class JpaProductRepository extends BasepaRepositoryBase<Product, Long, Long> implements ProductRepository {

    public JpaProductRepository() {
        super("id");
    }

    @Override
    public Product findById(long id) {
        final TypedQuery<Product> q = createQuery("SELECT e FROM Product e WHERE  e.productId = :m",
                Product.class);

        q.setParameter("m", id);
        return q.getSingleResult();
    }

    @Override
    public Product findByCode(Code code) {
        final TypedQuery<Product> q = createQuery("SELECT e FROM Product e WHERE  e.code = :m",
                Product.class);

        q.setParameter("m", code);
        return q.getSingleResult();
    }

    @Override
    public List<Product> findByBrandName(String brandName) {
        final TypedQuery<Product> q = createQuery("SELECT e FROM Product e WHERE  e.brandName = :m",
                Product.class);

        q.setParameter("m", BrandName.valueOf(brandName));
        return q.getResultList();
    }

    @Override
    public List<Product> findByReference(String reference) {
        final TypedQuery<Product> q = createQuery("SELECT e FROM Product e WHERE  e.reference = :m",
                Product.class);

        q.setParameter("m", Reference.valueOf(reference));
        return q.getResultList();
    }

    @Override
    public Product findByBarcode(Long barcode) {
        final TypedQuery<Product> q = createQuery("SELECT e FROM Product e WHERE  e.barcode = :m",
                Product.class);

        q.setParameter("m", Barcode.valueOf(barcode));
        return q.getSingleResult();
    }

    @Override
    public Product findByProductionCode(ProductionCode productionCode) {
        final TypedQuery<Product> q = createQuery("SELECT e FROM Product e WHERE  e.productionCode = :m",
                Product.class);

        q.setParameter("m", productionCode);
        return q.getSingleResult();
    }

    @Override
    public Iterable<Product> findAll() {
        final TypedQuery<Product> q = createQuery("select p from Product p",
                Product.class);

        return q.getResultList();
    }

    @Override
    public List<Product> findByCategoryAlphaCode(String code) {
        final TypedQuery<Product> q = createQuery("select p from Product p inner join Category c on p.categoryId = c.categoryId where c.categoryId = :m",
                Product.class);

        q.setParameter("m", AlphaNumericCode.valueOf(code));
        return q.getResultList();
    }

    @Override
    public List<Product> findBySinglePrice(String price) {
        return null;
    }

    @Override
    public List<Product> findByAllInsideLimitPrice(List<String> price) {
        return null;
    }

}
