package eapli.base.customermanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class Gender implements ValueObject, Comparable<Gender> {


    private String customergender;

    public Gender(String customerGender) throws IllegalArgumentException {
        checkGender(customerGender);
        this.customergender = customerGender;
    }


    public Gender() {

    }

    public void checkGender(String customerGender)  {

        List <String> gender = new ArrayList<>();
        gender.add("Male");
        gender.add("Female");
        gender.add("Other");

        if(! gender.contains(customerGender))
            throw new IllegalArgumentException("Gender does not exist!");
    }


    @Override
    public int compareTo(Gender o) {
        if(this.customergender.equals(o.customergender))return 0;
        else return -1;
    }
}
