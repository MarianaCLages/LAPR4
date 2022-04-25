package eapli.base.customermanagement.domain;

import eapli.base.customermanagement.domain.PhoneNumber;
import org.junit.Test;


import static org.junit.Assert.*;
public class PhoneNumberTest {


    private PhoneNumber phoneNumber1 = new PhoneNumber(123,123456789);
    private PhoneNumber phoneNumber2 = new PhoneNumber(333,111456789);
    private PhoneNumber phoneNumber3 = new PhoneNumber(123,123456789);

    @Test
    public void checkIndicatives(){

        String message = "Incorrect indicative format!";
        String actualMessage = null;

        try{
            PhoneNumber phoneNumber = new PhoneNumber(1,123456789);
        }catch (IllegalArgumentException ex){
            actualMessage = ex.getMessage();
        }
        
        assertEquals(message,actualMessage);
    }

    @Test
    public void checkPhoneNumber(){

        String message = "Incorrect Phone Number format!";
        String actualMessage = null;

        try{
            PhoneNumber phoneNumber = new PhoneNumber(123,16789);
        }catch (IllegalArgumentException ex){
            actualMessage = ex.getMessage();
        }

        assertEquals(message,actualMessage);
    }

    @Test
    public void compareTo(){

        assertEquals(-1,phoneNumber1.compareTo(phoneNumber2));
        assertEquals(0,phoneNumber1.compareTo(phoneNumber3));
    }



}
