package eapli.base.ordermanagement.application;


import eapli.base.customermanagement.application.SearchCustomerService;
import eapli.base.customermanagement.domain.*;
import eapli.base.ordermanagement.domain.*;
import eapli.base.ordermanagement.dto.OrderDto;
import eapli.base.productmanagement.application.FindAllProductsService;
import eapli.base.productmanagement.application.SearchProductService;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.dto.ProductDTO;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.general.domain.model.Money;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.io.IOException;
import java.util.*;

public class CreateOrderController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final SearchCustomerService searchCustomerService = new SearchCustomerService();
    private final CreateOrderService createOrderService = new CreateOrderService();
    private final SearchProductService searchProductService = new SearchProductService();
    private final CalculateOrderLineService calculateOrderLineService = new CalculateOrderLineService();
    private final FindAllProductsService findAllProductsService = new FindAllProductsService();
    private final CallNewOrderToTheServerService callNewOrderToTheServerService = new CallNewOrderToTheServerService();

    public ClientOrder createOrderController(Date date, Money money,
                                             Weight weight, List<OrderLine> orderLineList,
                                             Payment payment, Shipping shipping,
                                             Customer customer) {

        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.SALES_CLERK, BaseRoles.POWER_USER);

       return createOrderService.createOrder(date, money, weight, orderLineList, payment, shipping, customer);
    }

    public Customer searchIfCustomerExists(String email) {
        return searchCustomerService.searchCustomerServiceByEmail(email);
    }


    public OrderLine generateOrderLine(Product product, int quantity) {
        return calculateOrderLineService.calculateOrderLine(product, quantity);
    }

    public Product searchIfProductExists(String productCOde) {
        return searchProductService.searchByCode(productCOde);
    }

    public Money calculateMoney(List<OrderLine> orderLineList) {

        double moneyLong = 0;

        for (OrderLine orderLine : orderLineList) {
            moneyLong = moneyLong + Double.valueOf(orderLine.price());
        }

        return new Money(moneyLong, Currency.getInstance("EUR"));
    }

    public Iterable<ProductDTO> findAllProducts() {
        Iterable<Product> productList = findAllProductsService.findAllProductsService();
        List<ProductDTO> productDTOList = new ArrayList<>();


        for (Product p : productList) {
            productDTOList.add(p.toDTO());
        }

        return productDTOList;

    }

    public boolean  warnServer(Long clientOrder) throws IOException {
        //callNewOrderToTheServerService.CallNewOrderToTheServerService(clientOrder);

        return true;
    }
}
