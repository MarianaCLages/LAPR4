package eapli.base.clientordermanagement;

import eapli.base.customermanagement.domain.Customer;
import eapli.base.customermanagement.domain.Email;
import eapli.base.customermanagement.repositories.ClientRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.*;
import eapli.base.productmanagement.domain.Code;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.repositories.ProductRepository;
import eapli.framework.general.domain.model.Money;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import java.util.*;

public class SortCompareDateTest {



    @Test
    void compare() {



        List<OrderLine> orderLineList = new ArrayList<>();
        Product product = null;
        Customer customer = null;
        OrderLine orderLine = null;
        orderLineList.add(orderLine);

        List<OrderLine> orderLineList1 = new ArrayList<>();
        Product product1 = null;
        OrderLine orderLine1 = null;
        orderLineList1.add(orderLine1);

        Date date = new Date("12/01/2020");
        Date date2 = new Date("15/03/2021");
        Date date3 = new Date("20/01/2021");
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        Calendar calendar3 = Calendar.getInstance();
        calendar1.setTime(date);
        calendar2.setTime(date2);
        calendar3.setTime(date3);


        final ClientOrder clientOrder = new OrderBuilder()
                .addDate(new OrderDate())
                .addDate(calendar1)
                .addWeight(12)
                .addPrice(new Money(12, Currency.getInstance("EUR")))
                .addCustomer(customer)
                .addOrderLine(orderLineList)
                .addState(OrderState.TO_BE_PREPARED)
                .addPayment(new Payment(PaymentMethod.PAYPAL))
                .addShipping(new Shipping())
                .build();

        final ClientOrder clientOrder1 = new OrderBuilder()
                .addDate(calendar2)
                .addWeight(12)
                .addPrice(new Money(12, Currency.getInstance("EUR")))
                .addCustomer(customer)
                .addOrderLine(orderLineList1)
                .addState(OrderState.TO_BE_PREPARED)
                .addPayment(new Payment(PaymentMethod.PAYPAL))
                .addShipping(new Shipping())
                .build();

        final ClientOrder clientOrder2 = new OrderBuilder()
                .addDate(calendar3)
                .addWeight(12)
                .addPrice(new Money(12, Currency.getInstance("EUR")))
                .addCustomer(customer)
                .addOrderLine(orderLineList1)
                .addState(OrderState.TO_BE_PREPARED)
                .addPayment(new Payment(PaymentMethod.PAYPAL))
                .addShipping(new Shipping())
                .build();

        List<ClientOrder> expected = new ArrayList<>();
        expected.add(clientOrder);
        expected.add(clientOrder2);
        expected.add(clientOrder1);

        List <ClientOrder> actual = new ArrayList<>();
        actual.add(clientOrder);
        actual.add(clientOrder1);
        actual.add(clientOrder2);


        Collections.sort(actual, new SortCompareDate());

        assertEquals(expected.get(0),actual.get(0));
        assertEquals(expected.get(1),actual.get(1));
        assertEquals(expected.get(2),actual.get(2));
    }
}
