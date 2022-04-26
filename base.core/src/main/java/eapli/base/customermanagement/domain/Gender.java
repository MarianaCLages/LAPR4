package eapli.base.customermanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class Gender implements ValueObject, Comparable<Gender> {


    private String customergender;

    public Gender(String customerGender) throws IllegalArgumentException {
        customerGender = checkGender(customerGender);
        this.customergender = customerGender;
    }


    public Gender() {

    }

    private String checkGender(String customerGender)  {

        List <String> gender = new ArrayList<>();
        gender.add("Male");
        gender.add("Female");
        gender.add("Other");
        gender.add("male");
        gender.add("female");
        gender.add("other");

        if(! gender.contains(customerGender))
            throw new IllegalArgumentException("Gender does not exist! Please input Male,Female or Other");

        if(customerGender.equals("male")){
            customerGender = "Male";
        }
        else if(customerGender.equals("female")){
            customerGender = "Female";
        }
        else if(customerGender.equals("other")){
            customerGender = "Other";
        }

        return customerGender;
    }

    public String gender(){
        return this.customergender;
    }

    @Override
    public int compareTo(Gender o) {
        if(this.customergender.equals(o.customergender))return 0;
        else return -1;
    }
}
