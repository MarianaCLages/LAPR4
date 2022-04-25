package eapli.base.customermanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class VAT implements ValueObject, Comparable<VAT> {

    private int customerVAT;

    public VAT(final int customerVAT){
        this.customerVAT = customerVAT;
    }

    public VAT() {

    }


    @Override
    public int compareTo(VAT o) {

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
