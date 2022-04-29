package eapli.base.catalogmanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.dto.ProductDTO;
import eapli.base.productmanagement.repositories.ProductRepository;
import eapli.framework.application.ApplicationService;

import javax.persistence.NoResultException;
import java.util.*;

@ApplicationService
public class SearchCatalogService {

    private final ProductRepository productRepository = PersistenceContext.repositories().products();

    public Iterable<ProductDTO> searchAllProducts(HashMap<String, List<String>> options) {

        HashSet<Product> list = new HashSet<>();

        List<Product> brandList = new ArrayList<>();
        List<Product> referenceList = new ArrayList<>();
        List<Product> barcodeList = new ArrayList<>();
        List<Product> priceList = new ArrayList<>();
        List<Product> categoryList = new ArrayList<>();

        for (String s : options.keySet()) {
            switch (s) {
                case "Filter by brand":
                    list.addAll(productRepository.findByBrandName(options.get(s).get(0)));
                    //brandList.addAll(productRepository.findByBrandName(options.get(s).get(0)));
                    break;

                case "Filter by reference":
                    list.addAll(productRepository.findByReference(options.get(s).get(0)));
                    //referenceList.addAll(productRepository.findByReference(options.get(s).get(0)));
                    break;

                case "Filter by barcode":
                    list.add(productRepository.findByBarcode(Long.parseLong(options.get(s).get(0))));
                    //barcodeList.add(productRepository.findByBarcode(Long.parseLong(options.get(s).get(0))));
                    break;

                case "Filter by description":
                    Product product = null;

                    try {
                        product = productRepository.findByShortDescription(options.get(s).get(0));
                    } catch (NoResultException ex1) {
                        try {
                            product = productRepository.findByExtendedDescription(options.get(s).get(0));
                        } catch (NoResultException ex2) {
                            product = productRepository.findByTechnicalDescription(options.get(s).get(0));
                        }
                    }

                    list.add(product);
                    break;

                case "Filter by category":
                    list.addAll(productRepository.findByCategoryAlphaCode(options.get(s).get(0)));
                    //categoryList.addAll(productRepository.findByCategoryAlphaCode(options.get(s).get(0)));

                    break;

            }
        }

        //Filter the list

     /*   for (String s : options.keySet()) {
            switch (s) {
                case "Filter by brand":
                    if(!referenceList.isEmpty()) {
                        brandList.co
                    }
                    break;

                case "Filter by reference":

                    break;

                case "Filter by barcode":

                    break;

                case "Filter by price":

                    break;

                case "Filter by category":

                    break;
            }
        }*/

        List<ProductDTO> listDto = new ArrayList<>();

        for (Product p : list) {
            p.toDTO();
            listDto.add(p.toDTO());
        }
        return listDto;
    }

    public Iterable<ProductDTO> searchAllProducts() {

        Iterable<Product> list = productRepository.findAll();
        List<ProductDTO> listDto = new ArrayList<>();

        for (Product p : list) {
            p.toDTO();
            listDto.add(p.toDTO());
        }
        return listDto;

    }

    public List<ProductDTO> prepareToBeRepresented(List<ProductDTO> productDTOS, int option) {
        switch (option) {
            case 1:
                Collections.sort(productDTOS, new Comparator<ProductDTO>() {
                    @Override
                    public int compare(ProductDTO p1, ProductDTO p2) {
                        return p1.getBrandName().compareTo(p2.getBrandName());
                    }
                });

                break;

            case 2:
                Collections.sort(productDTOS, new Comparator<ProductDTO>() {
                    @Override
                    public int compare(ProductDTO p1, ProductDTO p2) {
                        return p2.getBrandName().compareTo(p1.getBrandName());
                    }
                });

                break;


            case 3:
                Collections.sort(productDTOS, new Comparator<ProductDTO>() {
                    @Override
                    public int compare(ProductDTO p1, ProductDTO p2) {
                        return p1.getReference().compareTo(p2.getReference());
                    }
                });

                break;

            case 4:
                Collections.sort(productDTOS, new Comparator<ProductDTO>() {
                    @Override
                    public int compare(ProductDTO p1, ProductDTO p2) {
                        return p2.getReference().compareTo(p1.getReference());
                    }
                });

                break;

            case 5:
                Collections.sort(productDTOS, new Comparator<ProductDTO>() {
                    @Override
                    public int compare(ProductDTO p1, ProductDTO p2) {
                        return p1.getCode().compareTo(p2.getCode());
                    }
                });

                break;

            case 6:
                Collections.sort(productDTOS, new Comparator<ProductDTO>() {
                    @Override
                    public int compare(ProductDTO p1, ProductDTO p2) {
                        return p2.getCode().compareTo(p1.getCode());
                    }
                });

                break;

            case 7:
                Collections.sort(productDTOS, new Comparator<ProductDTO>() {
                    @Override
                    public int compare(ProductDTO p1, ProductDTO p2) {
                        return p1.getShortDescription().compareTo(p2.getShortDescription());
                    }
                });

                break;

            case 8:
                Collections.sort(productDTOS, new Comparator<ProductDTO>() {
                    @Override
                    public int compare(ProductDTO p1, ProductDTO p2) {
                        return p2.getShortDescription().compareTo(p1.getShortDescription());
                    }
                });

                break;

        }

        return productDTOS;

    }

}