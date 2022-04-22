package model;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class CustomerGender implements ValueObject, Comparable<CustomerGender> {


    private String customergender;

    public CustomerGender(String customerGender) throws IllegalArgumentException {
        checkGender(customerGender);
        this.customergender = customerGender;
    }


    public CustomerGender() {

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
    public int compareTo(CustomerGender o) {
        if(this.customergender.equals(o.customergender))return 0;
        else return -1;
    }
}
