package eapli.base.customermanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class CustomerVAT implements ValueObject, Comparable<CustomerVAT> {

    private int customerVAT;

    public CustomerVAT(final int customerVAT){
        this.customerVAT = customerVAT;
    }

    public CustomerVAT() {

    }


    @Override
    public int compareTo(CustomerVAT o) {

        if(o.customerVAT == customerVAT){
            return 0;
        }
        else if(customerVAT > o.customerVAT){
            return 1;
        }
        else{
            return -1;
        }
    }
}
