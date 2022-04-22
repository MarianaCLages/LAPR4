import org.junit.Test;
import model.CustomerGender;

import static org.junit.Assert.*;

public class CustomerGenderTest {

    private CustomerGender customerGender1 = new CustomerGender("Male");
    private CustomerGender customerGender2 = new CustomerGender("Female");
    private CustomerGender customerGender3 = new CustomerGender("Male");

    @Test
    public void checkGender(){

        String expectedMessage = "Gender does not exist!";
        String actualMessage = null;
        String actualMessage2 = null;

        try{

            CustomerGender customerGender = new CustomerGender("AAAA");
        }catch (IllegalArgumentException ex){
            actualMessage = ex.getMessage();
        }

        try{

            CustomerGender customerGender = new CustomerGender("Male");
        }catch (IllegalArgumentException ex){
            actualMessage2 = ex.getMessage();
        }

        assertEquals(expectedMessage,actualMessage);
        assertNotEquals(expectedMessage,actualMessage2);
    }

    @Test
    public void compareTo(){

        assertEquals(0,customerGender1.compareTo(customerGender3));
        assertEquals(-1,customerGender1.compareTo(customerGender2));
    }
}
