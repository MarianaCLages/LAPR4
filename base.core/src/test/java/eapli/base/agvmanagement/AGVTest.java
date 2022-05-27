package eapli.base.agvmanagement;

import eapli.base.agvmanagement.domain.AGV;
import eapli.base.agvmanagement.domain.AGVBuilder;
import eapli.base.agvmanagement.domain.AGVCapacity;
import eapli.base.agvmanagement.domain.AGVStatus;
import eapli.base.customermanagement.domain.*;
import eapli.base.ordermanagement.domain.*;
import eapli.base.productmanagement.domain.Code;
import eapli.base.productmanagement.domain.Product;
import eapli.base.warehousemanagement.domain.AGVDock;
import eapli.base.warehousemanagement.domain.Accessibility;
import eapli.base.warehousemanagement.domain.Location;
import eapli.framework.general.domain.model.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

class AGVTest {
    @Test
    void ensureAGVCanBeCreated() {
        //dock for the agv
        AGVDock dock = new AGVDock("Dock1", new Location(1, 1), new Location(2, 2), new Location(3, 3), Accessibility.LENGHT_PLUS);
        //agv
        AGV agv = new AGV("D452FDD1", 5000, "First AGV", "AutomatedSolutions", AGVStatus.AVAILABLE, dock, new AGVCapacity(10));
        Assertions.assertEquals("D452FDD1", agv.identifier().toString());
    }

    @Test
    void ensureAGVOrderListIsWorking() {

        ;

        Date date = new Date("12/01/2020");
        Date date2 = new Date("15/03/2021");
        Date date3 = new Date("20/01/2021");
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        Calendar calendar3 = Calendar.getInstance();
        calendar1.setTime(date);
        calendar2.setTime(date2);
        calendar3.setTime(date3);
        final Customer customer = new CustomerBuilder().brithDate(new BirthDate(new Date("12/12/2002"))).vat(new VAT(12)).number(new PhoneNumber(123, 123456789)).named(new Name("customer1 customer")).gender(new Gender("Male")).email(new Email("email@email.com")).address(new Address("Billing Address", 11, "postal code", "city", "country")).build();
        final Customer customer2 = new CustomerBuilder().brithDate(new BirthDate(new Date("12/12/2002"))).vat(new VAT(12)).number(new PhoneNumber(123, 123416789)).named(new Name("customer2 customer")).gender(new Gender("Male")).email(new Email("email@email.com")).address(new Address("Billing Address", 11, "postal code", "city", "country")).build();
        final Customer customer3 = new CustomerBuilder().brithDate(new BirthDate(new Date("12/12/2002"))).vat(new VAT(12)).number(new PhoneNumber(123, 123456789)).named(new Name("customer3 customer")).gender(new Gender("Male")).email(new Email("email@email.com")).address(new Address("Billing Address", 11, "postal code", "city", "country")).build();


        ClientOrder clientOrder = new OrderBuilder()
                .addDate(new OrderDate())
                .addDate(calendar1)
                .addWeight(12)
                .addPrice(new Money(12, Currency.getInstance("EUR")))
                .addCustomer(customer)
                .addOrderLine(null)
                .addState(OrderState.TO_BE_PREPARED)
                .addPayment(new Payment(PaymentMethod.PAYPAL))
                .addShipping(new Shipping())
                .build();

        ClientOrder clientOrder1 = new OrderBuilder()
                .addDate(calendar2)
                .addWeight(12)
                .addPrice(new Money(12, Currency.getInstance("EUR")))
                .addCustomer(customer2)
                .addState(OrderState.TO_BE_PREPARED)
                .addPayment(new Payment(PaymentMethod.PAYPAL))
                .addShipping(new Shipping())
                .build();

        ClientOrder clientOrder2 = new OrderBuilder()
                .addDate(calendar3)
                .addWeight(12)
                .addPrice(new Money(12, Currency.getInstance("EUR")))
                .addCustomer(customer3)
                .addState(OrderState.TO_BE_PREPARED)
                .addPayment(new Payment(PaymentMethod.PAYPAL))
                .addShipping(new Shipping())
                .build();

        AGV agv = new AGVBuilder()
                .identifier("aaaaaaaa")
                .autonomy(20)
                .description("aaaaaa")
                .dock(null)
                .model("ModelX")
                .status(AGVStatus.AVAILABLE)
                .capacity(100)
                .build();

        agv.addAlreadyProcessedOrdersToList(clientOrder);
        agv.addAlreadyProcessedOrdersToList(clientOrder1);
        agv.addAlreadyProcessedOrdersToList(clientOrder2);
        System.out.println(agv.AllProcessedOrders());
        //Assertions.assertEquals();
    }
}