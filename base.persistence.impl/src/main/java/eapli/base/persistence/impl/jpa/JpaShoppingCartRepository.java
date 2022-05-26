package eapli.base.persistence.impl.jpa;

import eapli.base.customermanagement.domain.Customer;
import eapli.base.customermanagement.domain.Email;
import eapli.base.customermanagement.domain.Name;
import eapli.base.shoppingCartManagement.domain.ShoppingCart;
import eapli.base.shoppingCartManagement.repositories.ShoppingCartRepository;

import javax.persistence.TypedQuery;

public class JpaShoppingCartRepository extends BasepaRepositoryBase<ShoppingCart, Long, Long> implements ShoppingCartRepository {

    public JpaShoppingCartRepository() {
        super("id");
    }

    @Override
    public ShoppingCart findShoppingCartByCustomer(Customer customer, Name customerName) {

        final TypedQuery<ShoppingCart> q2 = createQuery("SELECT e FROM ShoppingCart e WHERE e.customer = :m",
                ShoppingCart.class);

        q2.setParameter("m", customer);


        return q2.getSingleResult();
    }

    @Override
    public ShoppingCart findShoppingCartByCustomerID(long id) {
        final TypedQuery<ShoppingCart> q = createQuery("SELECT e FROM ShoppingCart e WHERE  e.shoppingCartId = :m",
                ShoppingCart.class);

        q.setParameter("m", id);
        return q.getSingleResult();
    }


    @Override
    public boolean verifyIfACustomerAlreadyHasAShoppingCart(Email customerEmail, Name customerName) {
        try {
            final TypedQuery<Customer> q1 = createQuery("SELECT e FROM Customer e WHERE  e.email = :m and e.name = :s",
                    Customer.class);

            q1.setParameter("m", customerEmail);
            q1.setParameter("s", customerName);

            ShoppingCart shoppingCart;

            if (!q1.getResultList().isEmpty()) {
                Customer c1 = q1.getResultList().get(0);

                if (c1 != null) {

                    shoppingCart = findShoppingCartByCustomer(c1, customerName);

                    if (shoppingCart != null) return true;
                    else return false;


                } else {
                    Customer c2 = findAndReturnCustomer(customerEmail);

                    if (c2 != null) {

                        shoppingCart = findShoppingCartByCustomer(c2, customerName);

                        if (shoppingCart != null) return true;
                        else return false;

                    }

                }
            } else {
                Customer c3 = findAndReturnCustomer(customerEmail);

                shoppingCart = findShoppingCartByCustomer(c3, customerName);

                if (shoppingCart != null) return true;
                else return false;

            }

        } catch (Exception e) {
            return false;
        }

        return false;

    }

    private boolean findCustomerShoppingCart(Email customerEmail) {

        try {
            final TypedQuery<Customer> q1 = createQuery("SELECT e FROM Customer e WHERE  e.email = :m",
                    Customer.class);

            q1.setParameter("m", customerEmail);

            Customer c = q1.getResultList().get(0);

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    private Customer findAndReturnCustomer(Email customerEmail) {

        try {
            final TypedQuery<Customer> q1 = createQuery("SELECT e FROM Customer e WHERE  e.email = :m",
                    Customer.class);

            q1.setParameter("m", customerEmail);

            Customer c = q1.getResultList().get(0);

            return c;

        } catch (Exception e) {
            return null;
        }


    }

}
