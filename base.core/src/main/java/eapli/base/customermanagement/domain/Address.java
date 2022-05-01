package eapli.base.customermanagement.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String streeName;
    private int doorNumber;
    private String postalCode;
    private String city;
    private String country;


    public Address(final String streeName,final int doorNumber, final String postalCode,
    final String city, final String country){

       this.streeName = streeName;
       this.doorNumber = doorNumber;
       this.postalCode = postalCode;
       this.city = city;
       this.country = country;
    }

    public Address(){}


    @Override
    public String toString() {
        return "Address -> [" +
                "Street name : " + streeName +
                ", Door number : " + doorNumber +
                ", Postal code : " + postalCode +
                ", City : " + city +
                ", Country : " + country  +
                ']';
    }
}
