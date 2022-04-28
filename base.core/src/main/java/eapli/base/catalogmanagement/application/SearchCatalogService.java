package eapli.base.catalogmanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.dto.ProductDTO;
import eapli.base.productmanagement.repositories.ProductRepository;
import eapli.framework.application.ApplicationService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@ApplicationService
public class SearchCatalogService {

    private final ProductRepository productRepository = PersistenceContext.repositories().products();


    public Iterable<ProductDTO> searchAllProducts(HashMap<String, List<String>> options) {

        HashSet<Product> list = new HashSet<>();

        for (String s : options.keySet()) {
            switch (s) {
                case "Filter by brand":
                    list.addAll(productRepository.findByBrandName(options.get(s).get(0)));
                    break;

                case "Filter by reference":
                    list.addAll(productRepository.findByReference(options.get(s).get(0)));
                    break;

                case "Filter by barcode":
                    list.add(productRepository.findByBarcode(Long.valueOf(options.get(s).get(0))));
                    break;

                case "Filter by price":
                    int option = options.get(s).size();

                    if (option == 1) {
                        list.addAll(productRepository.findBySinglePrice(options.get(s).get(0)));
                    } else {

                        List<String> pricesLimit = new ArrayList<>();
                        pricesLimit.addAll(options.get(s));

                        list.addAll(productRepository.findByAllInsideLimitPrice(pricesLimit));
                    }
                    break;

                case "Filter by category":
                    list.addAll(productRepository.findByCategoryAlphaCode(options.get(s).get(0)));
                    break;

            }
        }

        //  Iterable<Product> list = productRepository.findAll();
        List<ProductDTO> listDto = new ArrayList<>();

        for (Product p : list) {
            p.toDTO();
            listDto.add(p.toDTO());
        }
        return listDto;
    }


}
