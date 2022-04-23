package eapli.base.customermanagement.domain;

import eapli.framework.domain.model.DomainFactory;

import javax.persistence.Embedded;

public class CustomerBuilder implements DomainFactory<Customer> {

    private Customer theCustomer;

    private PhoneNumber customerPhoneNumber;
    private CustomerGender customerGender;
    private CustomerBirthDate customerBirthDate;
    private CustomerName customerName;
    private CustomerVAT customerVAT;
    private CustomerEmail customerEmail;


    public CustomerBuilder number(final PhoneNumber phoneNumber) {
        customerPhoneNumber = phoneNumber;
        return this;
    }


    public CustomerBuilder gender(final CustomerGender gender){
        customerGender = gender;
        return this;
    }


    public CustomerBuilder brithDate(final CustomerBirthDate birthDate){
        customerBirthDate = birthDate;
        return this;
    }


    public CustomerBuilder named(final CustomerName name){
        customerName = name;
        return this;
    }


    public CustomerBuilder vat(final CustomerVAT vat){
        customerVAT = vat;
        return this;
    }


    public CustomerBuilder email(final CustomerEmail email){
        customerEmail = email;
        return this;
    }

    private Customer buildOrThrow(){

        if(theCustomer != null){
            return theCustomer;
        }
        else if(customerPhoneNumber != null && customerName != null && customerVAT != null && customerEmail != null && customerGender != null && customerBirthDate != null){
            theCustomer = new Customer(customerPhoneNumber,customerBirthDate,customerName,customerGender,customerVAT,customerEmail);
            return theCustomer;
        }
        else{
            throw new IllegalStateException();
        }
    }

    @Override
    public Customer build() {
        final Customer ret = buildOrThrow();
        theCustomer = null;
        return ret;
    }
}
