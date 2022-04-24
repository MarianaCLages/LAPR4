package customermanagemnentTest.domain;

import eapli.base.customermanagement.domain.CustomerName;
import org.junit.Test;
import static org.junit.Assert.*;


public class CustomerNameTest {


    private CustomerName customerName1 = new CustomerName("Tiago Ferreira");
    private CustomerName customerName2 = new CustomerName("Tiago Ferreira");
    private CustomerName customerName3 = new CustomerName("Ambrosio dos Brosios");


    @Test
    public void compareTo(){

        assertEquals(0,customerName1.compareTo(customerName2));
        assertEquals(-1,customerName1.compareTo(customerName3));
    }
}
