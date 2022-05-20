package eapli.base.clientordermanagement;
import static org.junit.Assert.*;

import eapli.base.categorymanagement.domain.CategoryBuilder;
import eapli.base.customermanagement.domain.CustomerBuilder;
import eapli.base.ordermanagement.domain.*;
import eapli.framework.general.domain.model.Money;
import org.junit.Test;

import java.util.Calendar;
import java.util.Currency;
import java.util.Date;


public class ClientOrderTest {

    final Payment payment = new Payment(PaymentMethod.PAYPAL);
    final Payment payment2 = new Payment(PaymentMethod.APPLEPAY);

    final ShippingPrice shippingPrice1 = ShippingPrice.valueOf(new Money(0, Currency.getInstance("EUR")));

    Shipping shipping = new Shipping(ShippingType.BLUE,shippingPrice1);

    final double weightPrice = 0;
    final Weight weight = Weight.valueOf(weightPrice);

    final Money money = new Money(0,Currency.getInstance("EUR"));



    @Test
    public void simpleOrderTest(){


        ClientOrder clientOrder = new OrderBuilder()
                .addOrderLine(null)
                .addShipping(shipping)
                .addPayment(payment)
                .addState(OrderState.REGISTERED)
                .addWeight(weight)
                .addCustomer(null)
                .addDate(Calendar.getInstance())
                .addPrice(money)
                .build();

        assertNotNull(clientOrder);
    }




}
