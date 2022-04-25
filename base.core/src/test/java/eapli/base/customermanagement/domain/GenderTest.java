package eapli.base.customermanagement.domain;

import eapli.base.customermanagement.domain.Gender;
import org.junit.Test;


import static org.junit.Assert.*;

public class GenderTest {

    private Gender gender1 = new Gender("Male");
    private Gender gender2 = new Gender("Female");
    private Gender gender3 = new Gender("Male");

    @Test
    public void checkGender(){

        String expectedMessage = "Gender does not exist!";
        String actualMessage = null;
        String actualMessage2 = null;

        try{

            Gender gender = new Gender("AAAA");
        }catch (IllegalArgumentException ex){
            actualMessage = ex.getMessage();
        }

        try{

            Gender gender = new Gender("Male");
        }catch (IllegalArgumentException ex){
            actualMessage2 = ex.getMessage();
        }

        assertEquals(expectedMessage,actualMessage);
        assertNotEquals(expectedMessage,actualMessage2);
    }

    @Test
    public void compareTo(){

        assertEquals(0, gender1.compareTo(gender3));
        assertEquals(-1, gender1.compareTo(gender2));
    }
}
