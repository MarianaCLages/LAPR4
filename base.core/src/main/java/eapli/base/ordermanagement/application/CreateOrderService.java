package eapli.base.ordermanagement.application;

import eapli.base.customermanagement.domain.Customer;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.*;
import eapli.base.ordermanagement.repositories.OrderRepository;
import eapli.framework.application.ApplicationService;
import eapli.framework.general.domain.model.Money;
import org.hibernate.criterion.Order;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@ApplicationService
public class CreateOrderService {


    private final OrderRepository orderRepository = PersistenceContext.repositories().orders();


    public ClientOrder createOrder(Date date, Money money,
                             Weight weight, List<OrderLine> orderLineList,
                             Payment payment, Shipping shipping,
                             Customer customer){


        final ClientOrder clientOrder = new OrderBuilder().addDate(new OrderDate())
                .addDate(new OrderDate())
                .addPrice(money)
                .addDate(Calendar.getInstance())
                .addWeight(weight)
                .addCustomer(customer)
                .addOrderLine(orderLineList)
                .addState(OrderState.REGISTERED)
                .addPayment(payment)
                .addShipping(shipping)
                .build();

        orderRepository.save(clientOrder);

        return clientOrder;
    }



}
