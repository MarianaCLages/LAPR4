import org.junit.Test;
import model.CustomerName;

import static org.junit.Assert.*;


public class CustomerNameTest {


    private CustomerName customerName1 = new CustomerName("Tiago Ferreira","Tiago","Ferreira");
    private CustomerName customerName2 = new CustomerName("Tiago Ferreira","Tiago","Ferreira");
    private CustomerName customerName3 = new CustomerName("Ambrosio dos Brosios","Ambrosio","Brosio");


    @Test
    public void compareTo(){

        assertEquals(0,customerName1.compareTo(customerName2));
        assertEquals(-1,customerName1.compareTo(customerName3));
    }
}
