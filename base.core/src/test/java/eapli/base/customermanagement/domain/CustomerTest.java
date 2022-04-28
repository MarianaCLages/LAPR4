package eapli.base.customermanagement.domain;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Date;

public class CustomerTest {


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
    private Address address1 = new Address("aa",11,"aa","aa","aa");
    private Address address2 = new Address("aa",12,"bb","cc","dd");

    private Customer customer1 = new Customer(phoneNumber1, birthDate1, name2, gender1, VAT1, email1,address1);
    private Customer customer2 = new Customer(phoneNumber2, birthDate2, name3, gender2, VAT2, email2,address2);

    @Test
    public void simpleCustomerTest(){

        CustomerBuilder customerBuilder = new CustomerBuilder();

        customerBuilder.address(address1).brithDate(birthDate1)
                .number(phoneNumber1).named(name2).gender(gender1)
                .email(email1).vat(VAT1).build();

        Customer customer = customerBuilder.build();

        assertNotNull(customer);
        assertEquals(customer.email(),email1);
        assertEquals(customer.gender(),gender1);
        assertEquals(customer.birthDate(),birthDate1);
        assertEquals(customer.vat(),VAT1);
        assertEquals(customer.phoneNumber(),phoneNumber1);
    }
    @Test
    public void name(){
        assertEquals(name2,customer1.name());
    }

    @Test
    public void email(){
        assertEquals(email1,customer1.email());
    }

    @Test
    public void phoneNumber(){
        assertEquals(phoneNumber1, customer1.phoneNumber());
    }

    @Test
    public void birthDate(){
        assertEquals(birthDate1,customer1.birthDate());
    }

    @Test
    public void vat(){
        assertEquals(VAT1,customer1.vat());
    }

    @Test
    public void gender(){
        assertEquals(gender1,customer1.gender());
    }

    @Test
    public void sameAs(){

        CustomerBuilder customerBuilder = new CustomerBuilder();
        CustomerBuilder customerBuilder2 = new CustomerBuilder();

        customerBuilder.address(address1).brithDate(birthDate1)
                .number(phoneNumber1).named(name2).gender(gender1)
                .email(email1).vat(VAT1).build();

        customerBuilder2.address(address2).brithDate(birthDate2)
                .number(phoneNumber2).named(name3).gender(gender2)
                .email(email2).vat(VAT2).build();

        Customer customer1 = customerBuilder.build();
        Customer customer2 = customerBuilder2.build();

        assertEquals(false,customer1.equals(customer2));
        assertEquals(true,customer1.equals(customer1));
    }

    @Test
    public void hashcode(){
        assertEquals(1147,customer1.hashCode());
    }

}
