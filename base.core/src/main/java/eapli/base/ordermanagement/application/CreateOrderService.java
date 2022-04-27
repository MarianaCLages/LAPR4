package eapli.base.ordermanagement.application;

import eapli.base.customermanagement.domain.Customer;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.*;
import eapli.base.ordermanagement.repositories.OrderRepository;
import eapli.framework.application.ApplicationService;
import eapli.framework.general.domain.model.Money;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@ApplicationService
public class CreateOrderService {


    private final OrderRepository orderRepository = PersistenceContext.repositories().orders();


    public boolean createOrder(Date date, Money money,
                               Weight weight, List<OrderLine> orderLineList,
                               Payment payment, Shipping shipping,
                               Customer customer){


        final ClientOrder clientOrder = new OrderBuilder().addDate(new OrderDate())
                .addDate(Calendar.getInstance())
                .addPrice(money)
                .addWeight(weight)
                .addOrderLine(orderLineList)
                .addCustomer(customer)
                .addState(OrderState.REGISTERED)
                .addPayment(payment)
                .addShipping(shipping)
                .build();

        orderRepository.save(clientOrder);

        return true;
    }



}
