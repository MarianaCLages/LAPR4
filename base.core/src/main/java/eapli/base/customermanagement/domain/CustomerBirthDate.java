package eapli.base.customermanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;
import java.util.Date;

@Embeddable
public class CustomerBirthDate implements ValueObject,Comparable<CustomerBirthDate> {


    private Date customerDate;


    public CustomerBirthDate(final Date customerDate){
        this.customerDate = customerDate;
    }

    public CustomerBirthDate() {

    }


    @Override
    public int compareTo(final CustomerBirthDate o) {

        if(this.customerDate.equals(o.customerDate)) return 0;
        else return -1;
    }
}
