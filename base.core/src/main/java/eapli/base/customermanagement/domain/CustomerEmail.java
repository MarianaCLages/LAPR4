package eapli.base.customermanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class CustomerEmail implements ValueObject,Comparable<CustomerEmail> {

    private String customerEmail;

    public  CustomerEmail(final String customerEmail) throws IllegalArgumentException {
        checkEmail(customerEmail);
        this.customerEmail = customerEmail;
    }

    public CustomerEmail() {

    }

    public void checkEmail(String customerEmail)  {

        if(!customerEmail.contains("@") && !customerEmail.endsWith(".com") || customerEmail.charAt(0) == '@'){
            throw new IllegalArgumentException("Incorrect Email Format!");
        }
    }

    @Override
    public int compareTo(CustomerEmail o) {

        if(this.customerEmail.equals(o.customerEmail)) return 0;
        else return -1;
    }
}
