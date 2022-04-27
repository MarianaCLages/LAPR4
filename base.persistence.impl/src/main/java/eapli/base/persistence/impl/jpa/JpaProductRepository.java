package eapli.base.persistence.impl.jpa;

import eapli.base.productmanagement.domain.*;
import eapli.base.productmanagement.repositories.ProductRepository;

import javax.persistence.TypedQuery;

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
    public Product findByBrandName(BrandName brandName) {
        final TypedQuery<Product> q = createQuery("SELECT e FROM Product e WHERE  e.brandName = :m",
                Product.class);

        q.setParameter("m", brandName);
        return q.getSingleResult();
    }

    @Override
    public Product findByReference(Reference reference) {
        final TypedQuery<Product> q = createQuery("SELECT e FROM Product e WHERE  e.reference = :m",
                Product.class);

        q.setParameter("m", reference);
        return q.getSingleResult();
    }

    @Override
    public Product findByBarcode(Barcode barcode) {
        final TypedQuery<Product> q = createQuery("SELECT e FROM Product e WHERE  e.barcode = :m",
                Product.class);

        q.setParameter("m", barcode);
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
    public Iterable<Product> findAll(){
        final TypedQuery<Product> q = createQuery("SELECT * FROM Product",
                Product.class);

        return q.getResultList();
    }

}
