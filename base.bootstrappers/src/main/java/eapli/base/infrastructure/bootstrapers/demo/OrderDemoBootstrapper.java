package eapli.base.infrastructure.bootstrapers.demo;
import eapli.base.ordermanagement.domain.*;
import eapli.framework.actions.Action;
import eapli.base.ordermanagement.application.CreateOrderController;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.general.domain.model.Money;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;

public class OrderDemoBootstrapper implements Action {

    private static final Logger LOGGER = LogManager.getLogger(ProductBootstrapper.class);
    private static final CreateOrderController createOrderController = new CreateOrderController();



    @Override
    public boolean execute() {

        final Payment payment = new Payment(PaymentMethod.PAYPAL);
        final Payment payment2 = new Payment(PaymentMethod.APPLEPAY);

        final ShippingPrice shippingPrice1 = ShippingPrice.valueOf(new Money(0,Currency.getInstance("EUR")));

        Shipping shipping = new Shipping(ShippingType.BLUE,shippingPrice1);

        final double weightPrice = 0;
        final Weight weight = Weight.valueOf(weightPrice);

        final Money money = new Money(0,Currency.getInstance("EUR"));

        registerOrder(money,payment2,shipping,weight);
        registerOrder(money,payment,shipping,weight);

        return true;
    }

    private void registerOrder(Money money,Payment payment,Shipping shipping,Weight weigh){

        try {
            createOrderController.createOrderController(new Date(),money,weigh,null,payment,shipping,null);


        }catch (final IntegrityViolationException | ConcurrencyException  exception){
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", "product");
            LOGGER.trace("Assuming existing record", exception);
        }
    }

}

