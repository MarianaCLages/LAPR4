package eapli.base.app.backoffice.console.presentation.order;

import eapli.base.customermanagement.domain.Customer;
import eapli.base.ordermanagement.application.CreateOrderController;
import eapli.base.ordermanagement.domain.*;
import eapli.base.ordermanagement.dto.OrderDto;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.dto.ProductDTO;
import eapli.framework.general.domain.model.Money;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import javax.persistence.NoResultException;
import java.util.*;


public class CreateOrderUI extends AbstractUI {
    private CreateOrderController createOrderController = new CreateOrderController();

    @Override
    protected boolean doShow() {

        //booleans
        boolean customerConfirmation = false;
        boolean productConfirmation = false;
        boolean verifyShippingType = false;

        Iterable<ProductDTO> productIterable = createOrderController.findAllProducts();

        Optional<Integer> priceInteger;
        Optional<String> shippingTypeString = Optional.empty();


        Customer customer = null;
        Weight weight = new Weight();
        Payment payment;
        //Money
        Money money;
        String paymentMethod;
        //Shipping
        Optional<Shipping> shipping = Optional.empty();
        ShippingPrice shippingPrice;
        ShippingType shippingType = null;

        //Order Line info
        Product product = null;
        String productCode;
        int quantity;
        List<OrderLine> orderLineList = new ArrayList<>();
        int orderLineOption = 1;

        do {
            try {
                customer = createOrderController.searchIfCustomerExists(Console.readLine("Enter the customer email: "));
                customerConfirmation = true;
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            } catch (NoResultException ex) {
                System.out.println("Email does not exist! Please input an existing email.");
            }

        } while (!customerConfirmation);

        do {
            try {
                do {
                    productConfirmation = false;
                    for (ProductDTO p : productIterable) {
                        System.out.println(p + "\n\n");
                    }

                    boolean productExists = false;

                    do {
                        try {

                            productCode = Console.readLine("Please enter the product code: ");

                            if (productCode.isEmpty()) {
                                throw new IllegalArgumentException("Please enter a valid product code! (Notice you must enter a valid input, no empty inputs are allowed)!");
                            }

                            product = createOrderController.searchIfProductExists(productCode);

                            if (product == null) {
                                throw new IllegalArgumentException("Please enter a valid product code! (Notice the product code is the code, in the representation, of a product!");
                            }

                            productExists = true;

                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                            productExists = false;
                        }

                    } while (!productExists);

                    boolean quantityValid = false;
                    quantity = 0;

                    do {
                        try {
                            quantity = Console.readInteger("Please enter the quantity of the product: (Notice: A VALID NUMBER must be introduced!)");

                            if (quantity < 0) {
                                throw new NumberFormatException("Please enter a positive number! You can not enter a negative quantity!");
                            }

                            quantityValid = true;

                        } catch (NumberFormatException e) {
                            System.out.println(e.getMessage());
                            quantityValid = false;
                        }

                    } while (!quantityValid);

                    orderLineList.add(createOrderController.generateOrderLine(product, quantity));
                    productConfirmation = true;

                    orderLineOption = Console.readInteger("Do you want to keep adding products?\n 1 > Yes   0 > No\n");
                } while (orderLineOption != 0);

            } catch (NoResultException | IllegalArgumentException ex) {
                System.out.println("The product doesn't exist! Please input an existing product!");
            }

        } while (!productConfirmation);
        money = createOrderController.calculateMoney(orderLineList);

        boolean paymentMethodBool = false;
        paymentMethod = null;

        do {
            try {
                paymentMethod = Console.readLine("Payment Method? PAYPAL or APPLEPAY");

                if (paymentMethod.equals("applepay") || paymentMethod.equals("APPLEPAY") || paymentMethod.equals("apple pay") || paymentMethod.equals("APPLE PAY") || paymentMethod.equals("paypal") || paymentMethod.equals("PAYPAL") || paymentMethod.equals("PAY PAL") || paymentMethod.equals("pay pal") || paymentMethod.equals("Paypal") || paymentMethod.equals("Applepay")) {
                    paymentMethod = paymentMethod.toUpperCase(Locale.ROOT);
                } else {
                    throw new IllegalArgumentException("Please enter a valid option between Paypal or Applepay!");
                }

                paymentMethodBool = true;

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                paymentMethodBool = false;
            }

        } while (!paymentMethodBool);

        if (paymentMethod.equalsIgnoreCase("APPLEPAY")) {
            payment = new Payment(PaymentMethod.APPLEPAY);
        } else {
            payment = new Payment(PaymentMethod.PAYPAL);
        }

        boolean moneyBool = false;
        priceInteger = Optional.of(0);

        do {
            try {
                priceInteger = Optional.ofNullable(Console.readInteger("Please enter the shipping price: (Notice the only currency accepted for now is EUR)"));
                Money auxMoney = Money.valueOf(priceInteger.get(), String.valueOf(Currency.getInstance("EUR")));

                moneyBool = true;
            } catch (Exception e) {
                System.out.println();
                moneyBool = false;
            }
        } while (!moneyBool);

        Optional<String> shippingTypeStrForOrder = Optional.empty();

        do {
            try {
                shippingTypeString = Optional.ofNullable(Console.readLine("What is the shipping type? (AVAILABLE: Standard, Blue, Green)"));

                if (shippingTypeString.get().equals("Standard") || shippingTypeString.get().equals("standard") || shippingTypeString.get().equals("STANDARD") || shippingTypeString.get().equals("Blue") || shippingTypeString.get().equals("blue") || shippingTypeString.get().equals("BLUE") || shippingTypeString.get().equals("Green") || shippingTypeString.get().equals("GREEN") || shippingTypeString.get().equals("green")) {
                    shippingTypeStrForOrder = Optional.of(shippingTypeString.get().toUpperCase(Locale.ROOT));
                } else {
                    throw new IllegalArgumentException("Wrong Shipping Type! (Please enter a valid Shipping type!)");
                }

                verifyShippingType = true;
            } catch (IllegalArgumentException ex) {
                System.out.println("Wrong Shipping Type! (Please enter a valid Shipping type!)");
            }
        } while (!verifyShippingType);


        shipping = Optional.of(Shipping.valueOf(ShippingPrice.valueOf(Money.valueOf(priceInteger.get(), String.valueOf(Currency.getInstance("EUR")))), ShippingType.valueOf(shippingTypeStrForOrder.get())));

        try {
            OrderDto order = createOrderController.createOrderController(new Date(), money, weight, orderLineList, payment, shipping.get(), customer).toDTO();
            System.out.println("\n### ORDER CREATED ###\n\n" + order + "\nCustomerEmail : " + customer.email() + "\nProducts : " + orderLineList.toString() + "\n\nOperation success!!\n");

            createOrderController.warnServer(order.getId());
        } catch (Exception e) {
            //EMPTY
            System.out.println("Operation failed!");
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public String headline() {
        return "Create New Order";
    }
}
