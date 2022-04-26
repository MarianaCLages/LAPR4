package eapli.base.customermanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class Email implements ValueObject,Comparable<Email> {

    private String customerEmail;

    public Email(final String customerEmail) throws IllegalArgumentException {
        checkEmail(customerEmail);
        this.customerEmail = customerEmail;
    }

    public Email() {

    }

    public void checkEmail(String customerEmail)  {

        if(!customerEmail.contains("@")  || customerEmail.charAt(0) == '@'){
            throw new IllegalArgumentException("Incorrect Email Format!");
        }
    }


    @Override
    public int compareTo(Email o) {

        if(this.customerEmail.equals(o.customerEmail)) return 0;
        else return -1;
    }
}
