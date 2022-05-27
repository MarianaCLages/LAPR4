package eapli.base.persistence.impl.jpa;

import eapli.base.ordermanagement.domain.ClientOrder;
import eapli.base.ordermanagement.domain.OrderDate;
import eapli.base.ordermanagement.domain.OrderState;
import eapli.base.ordermanagement.repositories.OrderRepository;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JpaOrderRepository extends BasepaRepositoryBase<ClientOrder, Long, Long> implements OrderRepository {


    public JpaOrderRepository() {
        super("id");
    }


    @Override
    public List<ClientOrder> findAllToBePreparedOrders() {

        List<ClientOrder> clientOrderList = new ArrayList<>();
        ClientOrder clientOrder = null;

        final TypedQuery<ClientOrder> q = createQuery("SELECT e FROM ClientOrder e", ClientOrder.class);

        Iterator<ClientOrder> clientOrderIterator = q.getResultList().iterator();

        while (clientOrderIterator.hasNext()) {
            clientOrder = clientOrderIterator.next();

            if (clientOrder.state().equals(OrderState.TO_BE_PREPARED)) clientOrderList.add(clientOrder);
        }

        return clientOrderList;
    }

    @Override
    public ClientOrder findById(long id) {
        final TypedQuery<ClientOrder> q = createQuery("SELECT e FROM ClientOrder e WHERE  e.orderId = :m", ClientOrder.class);

        q.setParameter("m", id);

        return q.getSingleResult();

    }

    @Override
    public List<ClientOrder> findAll() {
        final TypedQuery<ClientOrder> q = createQuery("SELECT e FROM ClientOrder e", ClientOrder.class);

        return q.getResultList();
    }

    @Override
    public void updateOrder(ClientOrder clientOrder) {
        entityManager().getTransaction().begin();
        entityManager().merge(clientOrder);
        entityManager().getTransaction().commit();

    }

    @Override
    public ClientOrder findByCalendar(OrderDate orderDate) {
        final TypedQuery<ClientOrder> q = createQuery("SELECT e FROM ClientOrder e WHERE  e.date = :m", ClientOrder.class);

        q.setParameter("m", orderDate);

        return q.getSingleResult();
    }

}
