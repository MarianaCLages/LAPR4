package customermanagemnentTest;

import eapli.base.customermanagement.domain.CustomerBirthDate;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Date;


public class CustomerBirthDateTest {


    private CustomerBirthDate customerBirthDate1 = new CustomerBirthDate(new Date("11/1/2001"));
    private CustomerBirthDate customerBirthDate2 = new CustomerBirthDate(new Date("2/2/2002"));
    private CustomerBirthDate customerBirthDate3 = new CustomerBirthDate(new Date("11/1/2001"));

    @Test
    public void compareTo(){


        assertEquals(0,customerBirthDate1.compareTo(customerBirthDate3));
        assertEquals(-1,customerBirthDate1.compareTo(customerBirthDate2));
    }
}
