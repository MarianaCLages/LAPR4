package eapli.base.customermanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class CustomerName implements ValueObject, Comparable<CustomerName> {

    private String customerName;


    public CustomerName(final String customerName){

        this.customerName = customerName;
    }

    public CustomerName() {

    }


    @Override
    public int compareTo(CustomerName o) {

        if(this.customerName.equals(o.customerName)){return  0;}
        else return -1;
    }
}
