package eapli.base.app.backoffice.console.presentation.order;

import eapli.base.customermanagement.domain.Customer;
import eapli.base.ordermanagement.application.CreateOrderController;
import eapli.base.ordermanagement.domain.*;
import eapli.base.productmanagement.domain.Product;
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


        Customer customer = null;
        Weight weight = new Weight();
        Payment payment;
        //Money
        Money money;
        String paymentMethod;
        //Shipping
        Shipping shipping = null;
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
                customer = createOrderController.searchIfCustomerExists(Console.readLine("Customer Email?"));
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
                    productCode = Console.readLine("What is the product Code?");
                    product = createOrderController.searchIfProductExists(productCode);
                    quantity = Console.readInteger("What is the quantity?");


                    orderLineList.add(createOrderController.generateOrderLine(product, quantity));
                    productConfirmation = true;


                    orderLineOption = Console.readInteger("Do you want to keep adding products? 1.Yes   0.No");
                } while (orderLineOption != 0);
            } catch (NoResultException | IllegalArgumentException ex) {
                System.out.println("The product doesn't exist! Please input an existing product!");
            }
        } while (!productConfirmation);
        money = createOrderController.calculateMoney(orderLineList);

        do {
            paymentMethod = Console.readLine("Payment Method? PAYPAL or APPLEPAY");

        } while (!paymentMethod.equalsIgnoreCase("applepay") && !paymentMethod.equalsIgnoreCase("paypal"));

        if (paymentMethod.equalsIgnoreCase("APPLEPAY")) {
            payment = new Payment(PaymentMethod.APPLEPAY);
        } else {
            payment = new Payment(PaymentMethod.PAYPAL);
        }




        shippingPrice = ShippingPrice.valueOf(new Money(Console.readInteger("Shipping price?"), Currency.getInstance("EUR")));

        do {
            try {
                shippingType = ShippingType.valueOf(Console.readLine("What is the shipping type?"));
                verifyShippingType = true;
            } catch (IllegalArgumentException ex) {
                System.out.println("Wrong Shipping Type!");
            }
        }while (!verifyShippingType);

        shipping = Shipping.valueOf(shippingPrice,shippingType);

        createOrderController.createOrderController(new Date(), money, weight,
                orderLineList, payment, shipping, customer);

        return true;
    }

    @Override
    public String headline() {
        return "Create New Order";
    }
}
