package eapli.base.customermanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;
import java.util.Date;

@Embeddable
public class BirthDate implements ValueObject,Comparable<BirthDate> {


    private Date customerDate;


    public BirthDate(final Date customerDate){
        this.customerDate = customerDate;
    }

    public BirthDate() {

    }


    @Override
    public int compareTo(final BirthDate o) {

        if(this.customerDate.equals(o.customerDate)) return 0;
        else return -1;
    }
}
