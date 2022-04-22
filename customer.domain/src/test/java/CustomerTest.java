import model.*;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class CustomerTest {


    private CustomerEmail customerEmail1 = new CustomerEmail("ola@gmail.com");
    private CustomerEmail customerEmail2 = new CustomerEmail("sadas@gmail.com");
    private CustomerGender customerGender1 = new CustomerGender("Male");
    private CustomerGender customerGender2 = new CustomerGender("Female");
    private CustomerBirthDate customerBirthDate1 = new CustomerBirthDate(new Date("11/1/2001"));
    private CustomerBirthDate customerBirthDate2 = new CustomerBirthDate(new Date("2/2/2002"));
    private CustomerName customerName2 = new CustomerName("Tiago Ferreira","Tiago","Ferreira");
    private CustomerName customerName3 = new CustomerName("Ambrosio dos Brosios","Ambrosio","Brosio");
    private CustomerVAT customerVAT1 = new CustomerVAT(2);
    private CustomerVAT customerVAT2 = new CustomerVAT(3);
    private PhoneNumber phoneNumber1 = new PhoneNumber(123,123456789);
    private PhoneNumber phoneNumber2 = new PhoneNumber(333,111456789);

    private Customer customer1 = new Customer(phoneNumber1,customerBirthDate1,customerName2,customerGender1,customerVAT1,customerEmail1);
    private Customer customer2 = new Customer(phoneNumber2,customerBirthDate2,customerName3,customerGender2,customerVAT2,customerEmail2);


}
