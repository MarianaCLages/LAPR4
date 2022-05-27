package eapli.base.productmanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.domain.Code;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.dto.ProductDTO;
import eapli.base.productmanagement.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class SearchProductService {

    private final ProductRepository productRepository = PersistenceContext.repositories().products();


    public Product searchProduct(Long id) {
        return productRepository.findById(id);
    }

    public Product searchByCode(String code) {
        List<Product> product = productRepository.findByCode(new Code(code));

        if (product.isEmpty()) {
            return null;
        }

        return product.get(0);
    }

    public List<ProductDTO> getAll() {


        List<Product> products = (List<Product>) productRepository.findAll();
        List<ProductDTO> productDTOS = new ArrayList<>();

        for (Product p : products) {
            productDTOS.add(p.toDTO());
        }

        return productDTOS;

    }

}
