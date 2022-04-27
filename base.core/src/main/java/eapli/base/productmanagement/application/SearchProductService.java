package eapli.base.productmanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.repositories.ProductRepository;

public class SearchProductService {

    private final ProductRepository productRepository = PersistenceContext.repositories().products();


    public Product searchProduct(Long id){
        return productRepository.findById(id);
    }
}
