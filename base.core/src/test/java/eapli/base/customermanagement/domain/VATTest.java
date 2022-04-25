package eapli.base.customermanagement.domain;

import eapli.base.customermanagement.domain.VAT;
import org.junit.Test;
import static org.junit.Assert.*;

public class VATTest {


    private VAT VAT1 = new VAT(2);
    private VAT VAT2 = new VAT(3);
    private VAT VAT3 = new VAT(2);
    private VAT VAT4 = new VAT(1);


    @Test
    public void compareTo(){

        int equals = VAT1.compareTo(VAT3);
        int greater = VAT1.compareTo(VAT4);
        int less = VAT1.compareTo(VAT2);

        assertEquals(0,equals);
        assertEquals(1,greater);
        assertEquals(-1,less);

    }
}
