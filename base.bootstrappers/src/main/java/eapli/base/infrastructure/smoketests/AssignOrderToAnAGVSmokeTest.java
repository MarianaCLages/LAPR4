package eapli.base.infrastructure.smoketests;

import eapli.base.agvmanagement.domain.AGV;
import eapli.base.agvmanagement.domain.AGVBuilder;
import eapli.base.agvmanagement.domain.AGVStatus;
import eapli.base.customermanagement.domain.*;
import eapli.base.ordermanagement.application.AssignOrderToAGVController;
import eapli.base.ordermanagement.domain.*;
import eapli.framework.actions.Action;
import eapli.framework.general.domain.model.Money;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class AssignOrderToAnAGVSmokeTest implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(AssignOrderToAnAGVSmokeTest.class);
    private final AssignOrderToAGVController assignOrderToAGVController = new AssignOrderToAGVController();

    @Override
    public boolean execute() {

        Date date = new Date("12/01/2020");
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date);

        final Customer customer = new CustomerBuilder().brithDate(new BirthDate(new Date("12/12/2002"))).vat(new VAT(12)).number(new PhoneNumber(123, 123456789)).named(new Name("customer1 customer")).gender(new Gender("Male")).email(new Email("email@email.com")).address(new Address("Billing Address", 11, "postal code", "city", "country")).build();

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

        final String agvIdentifier = "BX001201";
        final int agvAutonomy = 33;
        final String agvDescription = new String("DescriptionABV1");
        final String agvModel = "ModelZZ";
        final AGVStatus agvStatus = AGVStatus.AVAILABLE;

        AGV agv = new AGVBuilder()
                .autonomy(agvAutonomy)
                .capacity(20)
                .description(agvDescription)
                .dock(null)
                .identifier(agvIdentifier)
                .status(AGVStatus.AVAILABLE)
                .build();

        try {
            if (clientOrder.state().equals(OrderState.TO_BE_PREPARED) || agv.agvStatus().equals(AGVStatus.AVAILABLE)) {
                assignOrderToAGVController.assignOrderToAGV(clientOrder.toDTO(), agv.toDTO());
            }

            LOGGER.info("»»» Order Creation SUCCESS!");
            return true;

        } catch (Exception e) {
            LOGGER.info("»»» Order Creation ERROR!");
            return false;
        }

    }

}
