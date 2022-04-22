package customermanagemnentTest;


import eapli.base.customermanagement.domain.CustomerEmail;
import org.junit.Test;
import static org.junit.Assert.*;

public class CustomerEmailTest {


    private CustomerEmail customerEmail1 = new CustomerEmail("ola@gmail.com");
    private CustomerEmail customerEmail2 = new CustomerEmail("sadas@gmail.com");
    private CustomerEmail customerEmail3 = new CustomerEmail("ola@gmail.com");

    @Test
    public void checkEmail(){

        CustomerEmail customerEmail;
        String expectedMessage = "Incorrect Email Format!";
        String actualMessage = null;
        String actualMessage2 = null;

        try {
            customerEmail = new CustomerEmail("ASdassdasasad");
        }catch (IllegalArgumentException ex){
            actualMessage = ex.getMessage();
        }


        try {
            customerEmail = new CustomerEmail("ola@gmail.com");
        }catch (IllegalArgumentException ex){
            actualMessage2 = ex.getMessage();
        }

        assertEquals(expectedMessage,actualMessage);
        assertNotEquals(expectedMessage,actualMessage2);

    }

    @Test
    public void compareTo(){


        assertEquals(0,customerEmail1.compareTo(customerEmail3));
        assertEquals(-1,customerEmail1.compareTo(customerEmail2));
    }
}
