package eapli.base.customermanagement.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String billingAddress;
    private String deliveringPostalAddress;


    public Address(final String billingAddress, final String deliveringPostalAddress){

        this.billingAddress = billingAddress;
        this.deliveringPostalAddress = deliveringPostalAddress;
    }

    public Address(){}



}
