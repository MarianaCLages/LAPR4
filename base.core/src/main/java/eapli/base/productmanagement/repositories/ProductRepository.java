package eapli.base.productmanagement.repositories;

import eapli.base.productmanagement.domain.*;
import eapli.framework.domain.repositories.DomainRepository;

public interface ProductRepository extends DomainRepository<Long, Product> {

    Product findById(long id);
    Product findByCode(Code code);
    Product findByBrandName(BrandName brandName);
    Product findByReference(Reference reference);
    Product findByBarcode(Barcode barcode);
    Product findByProductionCode(ProductionCode productionCode);

    Iterable<Product> findAll();

}
