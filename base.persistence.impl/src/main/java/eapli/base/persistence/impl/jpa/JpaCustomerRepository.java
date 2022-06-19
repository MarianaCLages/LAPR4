package eapli.base.persistence.impl.jpa;

import eapli.base.customermanagement.domain.Customer;
import eapli.base.customermanagement.domain.Email;
import eapli.base.customermanagement.domain.Name;
import eapli.base.customermanagement.repositories.ClientRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.ClientOrder;
import eapli.base.ordermanagement.domain.OrderLine;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.repositories.ProductRepository;

import javax.persistence.TypedQuery;
import java.util.List;

public class JpaCustomerRepository extends BasepaRepositoryBase<Customer, Long, Long> implements ClientRepository {

    ProductRepository productRepository = PersistenceContext.repositories().products();


    public JpaCustomerRepository() {
        super("id");
    }

    @Override
    public Customer findById(long id) {
        final TypedQuery<Customer> q = createQuery("SELECT e FROM Customer e WHERE  e.customerId = :m",
                Customer.class);

        q.setParameter("m", id);
        return q.getSingleResult();
    }

    @Override
    public Customer findByEmail(Email email, Name name) {
        final TypedQuery<Customer> q = createQuery("SELECT e FROM Customer e WHERE  e.email = :m and e.name = :s",
                Customer.class);

        q.setParameter("m", email);
        q.setParameter("s", name);
        return q.getResultList().get(0);
    }

    @Override
    public Customer findByEmailOnly(Email email) {
        final TypedQuery<Customer> q = createQuery("SELECT e FROM Customer e WHERE  e.email = :m",
                Customer.class);

        q.setParameter("m", email);
        return q.getResultList().get(0);
    }

    @Override
    public boolean orderedTheBrand(String email, String name, String ruleValue) {
        final TypedQuery<Customer> q1 = createQuery("SELECT e FROM Customer e WHERE  e.email = :m and e.name = :s",
                Customer.class);

        q1.setParameter("m", Email.valueOf(email));
        q1.setParameter("s", Name.valueOf(name));

        List<Customer> customers = q1.getResultList();

        final TypedQuery<ClientOrder> q = createQuery("SELECT e FROM ClientOrder e WHERE  e.customer = :m",
                ClientOrder.class);

        q.setParameter("m", customers.get(0));
        List<ClientOrder> orders = q.getResultList();
        for (ClientOrder order : orders) {
            for (OrderLine line : order.orderLine()) {
                long productId = line.productId();
                //find the producto
                Product product = productRepository.findById(productId);
                if (product.brandName().toString().equals(ruleValue)) {
                    return true;
                }
            }
        }

        return false;

    }

    @Override
    public boolean orderedTheProducts(String email, String name, String ruleValue) {
        final TypedQuery<Customer> q1 = createQuery("SELECT e FROM Customer e WHERE  e.email = :m and e.name = :s",
                Customer.class);

        q1.setParameter("m", Email.valueOf(email));
        q1.setParameter("s", Name.valueOf(name));

        List<Customer> customers = q1.getResultList();
        final TypedQuery<ClientOrder> q = createQuery("SELECT e FROM ClientOrder e WHERE  e.customer = :m",
                ClientOrder.class);

        q.setParameter("m", customers.get(0));

        List<ClientOrder> orders = q.getResultList();

        for (ClientOrder order : orders) {

            for (OrderLine orderLine : order.orderLine()) {
                //find all the products from the orderline
                final TypedQuery<Product> q3 = createQuery("SELECT e FROM Product e WHERE e.productId = :m",
                        Product.class);

                q3.setParameter("m", orderLine.productId());
                List<Product> products = q3.getResultList();
                for (Product product : products) {
                    if (product.toDTO().getCode().equals(ruleValue)) {
                        return true;
                    }
                }
            }

        }

        return false;
    }

}
