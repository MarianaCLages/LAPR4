package eapli.base.customermanagement.domain;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Date;

public class CustomerBuild {

    private Email email1 = new Email("ola@gmail.com");
    private Email email2 = new Email("sadas@gmail.com");
    private Gender gender1 = new Gender("Male");
    private Gender gender2 = new Gender("Female");
    private BirthDate birthDate1 = new BirthDate(new Date("11/1/2001"));
    private BirthDate birthDate2 = new BirthDate(new Date("2/2/2002"));
    private Name name2 = new Name("Tiago Ferreira");
    private Name name3 = new Name("Ambrosio dos Brosios");
    private VAT VAT1 = new VAT(2);
    private VAT VAT2 = new VAT(3);
    private PhoneNumber phoneNumber1 = new PhoneNumber(123,123456789);
    private PhoneNumber phoneNumber2 = new PhoneNumber(333,111456789);

    private Customer customer1 = new Customer(phoneNumber1, birthDate1, name2, gender1, VAT1, email1);
    private Customer customer2 = new Customer(phoneNumber2, birthDate2, name3, gender2, VAT2, email2);

    private CustomerBuilder customerBuilder = new CustomerBuilder();

    @Test
    public void buildTest(){

        Customer customer = customerBuilder.number(phoneNumber1).brithDate(birthDate1).named(name2)
                .gender(gender1).vat(VAT1).email(email1).build();

        assertEquals(birthDate1,customer.birthDate());
        assertEquals(name2,customer.name());
        assertEquals(gender1,customer.gender());
        assertEquals(gender1,customer.gender());
        assertEquals(VAT1,customer.vat());
        assertEquals(email1,customer.email());
    }


}
