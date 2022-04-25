package eapli.base.customermanagement.domain;

import eapli.framework.domain.model.DomainFactory;

public class CustomerBuilder implements DomainFactory<Customer> {

    private Customer theCustomer;

    private PhoneNumber customerPhoneNumber;
    private Gender gender;
    private BirthDate birthDate;
    private Name name;
    private VAT VAT;
    private Email email;


    public CustomerBuilder number(final PhoneNumber phoneNumber) {
        customerPhoneNumber = phoneNumber;
        return this;
    }


    public CustomerBuilder gender(final Gender gender){
        this.gender = gender;
        return this;
    }


    public CustomerBuilder brithDate(final BirthDate birthDate){
        this.birthDate = birthDate;
        return this;
    }


    public CustomerBuilder named(final Name name){
        this.name = name;
        return this;
    }


    public CustomerBuilder vat(final VAT vat){
        VAT = vat;
        return this;
    }


    public CustomerBuilder email(final Email email){
        this.email = email;
        return this;
    }

    private Customer buildOrThrow(){

        if(theCustomer != null){
            return theCustomer;
        }
        else if(customerPhoneNumber != null && name != null && VAT != null && email != null && gender != null && birthDate != null){
            theCustomer = new Customer(customerPhoneNumber, birthDate, name, gender, VAT, email);
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
