package eapli.base.app.backoffice.console.presentation.order;

import eapli.base.customermanagement.domain.Customer;
import eapli.base.ordermanagement.application.CreateOrderController;
import eapli.base.ordermanagement.domain.*;
import eapli.base.productmanagement.domain.Product;
import eapli.framework.general.domain.model.Money;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class CreateOrderUI extends AbstractUI {


    private CreateOrderController createOrderController = new CreateOrderController();

    @Override
    protected boolean doShow() {

        Customer customer;
        Weight weight = new Weight();
        Payment payment;
        //Money
        Money money;
        String paymentMethod;
        Shipping shipping;

        //Order Line info
        Product product;
        String productCode;
        int quantity;
        List<OrderLine> orderLineList = new ArrayList<>();
        int orderLineOption = 1;

        customer = createOrderController.searchIfCustomerExists(Console.readLine("Customer Email?"));

        do{
            productCode = Console.readLine("What is the product Code?");
            product = createOrderController.searchIfProductExists(productCode);
            quantity = Console.readInteger("What is the quantity?");


            orderLineList.add(createOrderController.generateOrderLine(product,quantity));



            orderLineOption = Console.readInteger("Do you want to keep adding products? 1.Yes   0.No");
        }while (orderLineOption != 0);

        money = createOrderController.calculateMoney(orderLineList);

        do{
            paymentMethod = Console.readLine("Payment Method?");

        }while(! paymentMethod.equals("APPLEPAY") && ! paymentMethod.equals("PAYPAL"));

        if(paymentMethod.equals("APPLEPAY")){ payment = new Payment(PaymentMethod.APPLEPAY);}
        else{payment = new Payment(PaymentMethod.PAYPAL);}

        shipping = new Shipping();

        createOrderController.createOrderController(new Date(),money,weight,
                orderLineList,payment,shipping,customer);

        return true;
    }

    @Override
    public String headline() {
        return "Create New Order";
    }
}
