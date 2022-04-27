package eapli.base.catalogmanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.dto.ProductDTO;
import eapli.base.productmanagement.repositories.ProductRepository;
import eapli.framework.application.ApplicationService;

import java.util.ArrayList;
import java.util.List;

@ApplicationService
public class SearchCatalogService {

    private final ProductRepository productRepository = PersistenceContext.repositories().products();


    public Iterable<ProductDTO> searchAllProducts() {
        List<Product> list = (List<Product>) productRepository.findAll();
        List<ProductDTO> listDto = new ArrayList<>();

        for (Product p : list) {
            p.toDTO();
            listDto.add(p.toDTO());
        }
        return listDto;
    }


}
