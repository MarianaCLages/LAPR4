package eapli.base.productmanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.repositories.ProductRepository;

public class FindAllProductsService {

    private final ProductRepository productRepository = PersistenceContext.repositories().products();

    public Iterable<Product> findAllProductsService(){
        return productRepository.findAll();
    }
}
