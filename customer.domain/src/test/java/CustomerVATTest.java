import model.CustomerVAT;
import org.junit.Test;
import static org.junit.Assert.*;

public class CustomerVATTest {


    private CustomerVAT customerVAT1 = new CustomerVAT(2);
    private CustomerVAT customerVAT2 = new CustomerVAT(3);
    private CustomerVAT customerVAT3 = new CustomerVAT(2);
    private CustomerVAT customerVAT4 = new CustomerVAT(1);


    @Test
    public void compareTo(){

        int equals = customerVAT1.compareTo(customerVAT3);
        int greater = customerVAT1.compareTo(customerVAT4);
        int less = customerVAT1.compareTo(customerVAT2);

        assertEquals(0,equals);
        assertEquals(1,greater);
        assertEquals(-1,less);

    }
}
