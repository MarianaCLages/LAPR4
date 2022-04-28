package eapli.base.productmanagement.repositories;

import eapli.base.productmanagement.domain.*;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.List;

public interface ProductRepository extends DomainRepository<Long, Product> {

    Product findById(long id);

    Product findByCode(Code code);

    List<Product> findByBrandName(String brandName);

    List<Product> findByReference(String reference);

    Product findByBarcode(Long barcode);

    Product findByProductionCode(ProductionCode productionCode);

    Iterable<Product> findAll();

    List<Product> findByCategoryAlphaCode(String code);

    List<Product> findBySinglePrice(String price);

    List<Product> findByAllInsideLimitPrice(List<String> price);

}
