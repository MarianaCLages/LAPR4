package model;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class CustomerName implements ValueObject, Comparable<CustomerName> {

    private String customerName;
    private String customerFinalName;
    private String customerFirstName;

    public CustomerName(final String customerName,final String customerFirstName,final String customerFinalName){
        this.customerFinalName = customerFinalName;
        this.customerFirstName = customerFirstName;
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
