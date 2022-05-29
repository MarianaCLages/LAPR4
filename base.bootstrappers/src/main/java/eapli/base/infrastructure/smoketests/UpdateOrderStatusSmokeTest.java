package eapli.base.infrastructure.smoketests;

import eapli.base.agvmanagement.domain.AGV;
import eapli.base.agvmanagement.domain.AGVBuilder;
import eapli.base.agvmanagement.domain.AGVStatus;
import eapli.base.customermanagement.domain.*;
import eapli.base.ordermanagement.application.UpdateOrderController;
import eapli.base.ordermanagement.domain.*;
import eapli.base.ordermanagement.dto.OrderDto;
import eapli.framework.actions.Action;
import eapli.framework.general.domain.model.Money;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.List;

public class UpdateOrderStatusSmokeTest implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateOrderStatusSmokeTest.class);

    private final UpdateOrderController controller = new UpdateOrderController();

    @Override
    public boolean execute() {
        runSmokeTestDispatchOrderWithoutFilter();

        if (runSmokeTestDispatchOrderWithoutFilter() && runSmokeTestDispatchOrderWithFilter()) {
            LOGGER.info("»»» Order status update SUCCESS!");
            return true;
        }

        LOGGER.info("»»» Order status update ERROR!");
        return false;
    }

    private boolean runSmokeTestDispatchOrderWithoutFilter() {
        Date date = new Date("13/01/2020");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        final Customer customer = new CustomerBuilder().brithDate(new BirthDate(new Date("12/12/2002"))).vat(new VAT(12)).number(new PhoneNumber(123, 123456789)).named(new Name("customer1 customer")).gender(new Gender("Male")).email(new Email("email@email.com")).address(new Address("Billing Address", 11, "postal code", "city", "country")).build();

        ClientOrder clientOrder = new OrderBuilder()
                .addDate(new OrderDate())
                .addDate(calendar)
                .addWeight(12)
                .addPrice(new Money(12, Currency.getInstance("EUR")))
                .addCustomer(customer)
                .addOrderLine(null)
                .addState(OrderState.READY_FOR_CARRIER_DISPATCHING)
                .addPayment(new Payment(PaymentMethod.PAYPAL))
                .addShipping(new Shipping())
                .build();


        try {
            controller.updateOrderStatus(clientOrder.toDTO());

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    private boolean runSmokeTestDispatchOrderWithFilter() {
        Date date = new Date("13/01/2021");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        final Customer customer = new CustomerBuilder().brithDate(new BirthDate(new Date("12/12/2002"))).vat(new VAT(12)).number(new PhoneNumber(123, 123456789)).named(new Name("customer1 customer")).gender(new Gender("Male")).email(new Email("email@email.com")).address(new Address("Billing Address", 11, "postal code", "city", "country")).build();

        ClientOrder clientOrder = new OrderBuilder()
                .addDate(new OrderDate())
                .addDate(calendar)
                .addWeight(12)
                .addPrice(new Money(12, Currency.getInstance("EUR")))
                .addCustomer(customer)
                .addOrderLine(null)
                .addState(OrderState.READY_FOR_CARRIER_DISPATCHING)
                .addPayment(new Payment(PaymentMethod.PAYPAL))
                .addShipping(new Shipping())
                .build();

        final String agvIdentifier = "BX001202";
        final int agvAutonomy = 34;
        final String agvDescription = "DescriptionABV1";
        final String agvModel = "ModelZZ";
        final AGVStatus agvStatus = AGVStatus.AVAILABLE;

        AGV agv = new AGVBuilder()
                .autonomy(agvAutonomy)
                .capacity(20)
                .description(agvDescription)
                .model(agvModel)
                .dock(null)
                .identifier(agvIdentifier)
                .status(agvStatus)
                .build();

        try {
            agv.addOrderToAGV(clientOrder);
            List<OrderDto> orderList = controller.getOrdersToBeDispatchedFromAGV(agv.toDTO());
            controller.updateOrderStatus(orderList.get(0));

            return true;

        } catch (Exception e) {
            return false;
        }
    }
}
