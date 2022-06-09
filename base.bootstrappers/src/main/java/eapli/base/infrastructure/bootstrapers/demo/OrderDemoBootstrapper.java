package eapli.base.infrastructure.bootstrapers.demo;

import ch.qos.logback.core.net.server.Client;
import eapli.base.customermanagement.domain.Customer;
import eapli.base.customermanagement.repositories.ClientRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.*;
import eapli.base.ordermanagement.repositories.OrderRepository;
import eapli.framework.actions.Action;
import eapli.base.ordermanagement.application.CreateOrderController;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.general.domain.model.Money;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.*;

public class OrderDemoBootstrapper implements Action {

    private static final Logger LOGGER = LogManager.getLogger(ProductBootstrapper.class);
    private static final CreateOrderController createOrderController = new CreateOrderController();
    private static final ClientRepository crepo = PersistenceContext.repositories().client();
    private static final OrderRepository orepo = PersistenceContext.repositories().orders();

    @Override
    public boolean execute() {

        final Payment payment = new Payment(PaymentMethod.PAYPAL);
        final Payment payment2 = new Payment(PaymentMethod.APPLEPAY);

        final ShippingPrice shippingPrice1 = ShippingPrice.valueOf(new Money(15, Currency.getInstance("EUR")));
        final ShippingPrice shippingPrice2 = ShippingPrice.valueOf(new Money(30, Currency.getInstance("EUR")));

        Shipping shipping = new Shipping(ShippingType.BLUE, shippingPrice1);
        Shipping shipping1 = new Shipping(ShippingType.GREEN, shippingPrice2);
        Shipping shipping2 = new Shipping(ShippingType.STANDARD, shippingPrice2);

        double weightPrice = 20;
        final Weight weight1 = Weight.valueOf(weightPrice);

        weightPrice = 40;
        final Weight weight2 = Weight.valueOf(weightPrice);

        weightPrice = 44;
        final Weight weight3 = Weight.valueOf(weightPrice);

        weightPrice = 21;
        final Weight weight4 = Weight.valueOf(weightPrice);

        weightPrice = 51;
        final Weight weight5 = Weight.valueOf(weightPrice);

        weightPrice = 100;
        final Weight weight6 = Weight.valueOf(weightPrice);

        final Money money1 = new Money(60, Currency.getInstance("EUR"));
        final Money money2 = new Money(41, Currency.getInstance("EUR"));
        final Money money3 = new Money(40, Currency.getInstance("EUR"));
        final Money money4 = new Money(32, Currency.getInstance("EUR"));
        final Money money5 = new Money(53, Currency.getInstance("EUR"));
        final Money money6 = new Money(48, Currency.getInstance("EUR"));

        Customer customer = crepo.findById(82);
        Customer customer2 = crepo.findById(76);

        List<OrderLine> orderLineList = new ArrayList<>();
        orderLineList.add(new OrderLine(46L, 3, "12"));
        orderLineList.add(new OrderLine(49L, 2, "30"));
        orderLineList.add(new OrderLine(51L, 4, "50"));
        orderLineList.add(new OrderLine(53L, 5, "70"));
        orderLineList.add(new OrderLine(55L, 1, "32"));

        ClientOrder order;

        order = registerOrder(money1, payment2, shipping, weight1, orderLineList, customer2);

        order.chanceState(OrderState.READY_FOR_CARRIER_DISPATCHING);
        orepo.save(order);

        order = registerOrder(money2, payment, shipping, weight2, orderLineList, customer);

        order.chanceState(OrderState.READY_FOR_CARRIER_DISPATCHING);
        orepo.save(order);

        order = registerOrder(money3, payment2, shipping2, weight3, orderLineList, customer2);

        order.chanceState(OrderState.DELIVERED_BY_CARRIED);
        orepo.save(order);

        order = registerOrder(money4, payment2, shipping2, weight4, orderLineList, customer);

        order.chanceState(OrderState.READY_FOR_CARRIER_DISPATCHING);
        orepo.save(order);

        order = registerOrder(money5, payment, shipping1, weight5, orderLineList, customer2);

        order.chanceState(OrderState.READY_FOR_CARRIER_DISPATCHING);
        orepo.save(order);

        order = registerOrder(money6, payment2, shipping2, weight6, orderLineList, customer);

        order.chanceState(OrderState.TO_BE_PREPARED);
        orepo.save(order);

        order = registerOrder(money1, payment, shipping1, weight1, orderLineList, customer2);

        order.chanceState(OrderState.TO_BE_PREPARED);
        orepo.save(order);

        order = registerOrder(money2, payment2, shipping2, weight2, orderLineList, customer2);

        order.chanceState(OrderState.TO_BE_PREPARED);
        orepo.save(order);

        order = registerOrder(money3, payment, shipping, weight3, orderLineList, customer);

        order.chanceState(OrderState.TO_BE_PREPARED);
        orepo.save(order);

        order = registerOrder(money4, payment, shipping, weight4, orderLineList, customer2);

        order.chanceState(OrderState.TO_BE_PREPARED);
        orepo.save(order);

        order = registerOrder(money5, payment, shipping1, weight5, orderLineList, customer);

        order.chanceState(OrderState.TO_BE_PREPARED);
        orepo.save(order);

        order = registerOrder(money6, payment2, shipping1, weight6, orderLineList, customer);

        order.chanceState(OrderState.TO_BE_PREPARED);
        orepo.save(order);

        order = registerOrder(money1, payment2, shipping1, weight1, orderLineList, customer2);

        order.chanceState(OrderState.TO_BE_PREPARED);
        orepo.save(order);

        order = registerOrder(money2, payment, shipping, weight2, orderLineList, customer2);

        order.chanceState(OrderState.TO_BE_PREPARED);
        orepo.save(order);

        order = registerOrder(money3, payment2, shipping2, weight3, orderLineList, customer);

        order.chanceState(OrderState.TO_BE_PREPARED);
        orepo.save(order);

        order = registerOrder(money4, payment2, shipping, weight4, orderLineList, customer);

        order.chanceState(OrderState.TO_BE_PREPARED);
        orepo.save(order);

        order = registerOrder(money5, payment, shipping1, weight5, orderLineList, customer2);

        order.chanceState(OrderState.TO_BE_PREPARED);
        orepo.save(order);

        order = registerOrder(money6, payment, shipping2, weight6, orderLineList, customer2);

        order.chanceState(OrderState.TO_BE_PREPARED);
        orepo.save(order);

        order = registerOrder(money1, payment2, shipping1, weight1, orderLineList, customer);

        order.chanceState(OrderState.TO_BE_PREPARED);
        orepo.save(order);

        order = registerOrder(money2, payment, shipping2, weight2, orderLineList, customer2);

        order.chanceState(OrderState.TO_BE_PREPARED);
        orepo.save(order);

        order = registerOrder(money3, payment2, shipping2, weight3, orderLineList, customer);

        order.chanceState(OrderState.TO_BE_PREPARED);
        orepo.save(order);

        order = registerOrder(money4, payment, shipping, weight4, orderLineList, customer2);

        order.chanceState(OrderState.TO_BE_PREPARED);
        orepo.save(order);

        order = registerOrder(money5, payment, shipping1, weight5, orderLineList, customer2);

        order.chanceState(OrderState.READY_FOR_CARRIER_DISPATCHING);
        orepo.save(order);

        return true;
    }

    private ClientOrder registerOrder(Money money, Payment payment, Shipping shipping, Weight weigh, List<OrderLine> list, Customer customer) {

        try {
            return createOrderController.createOrderController(new Date(), money, weigh, list, payment, shipping, customer);


        } catch (final IntegrityViolationException | ConcurrencyException exception) {
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", "product");
            LOGGER.trace("Assuming existing record", exception);
            return null;
        }
    }

}

