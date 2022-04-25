package eapli.base.customermanagement.domain;

import eapli.base.customermanagement.domain.BirthDate;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Date;


public class BirthDateTest {


    private BirthDate birthDate1 = new BirthDate(new Date("11/1/2001"));
    private BirthDate birthDate2 = new BirthDate(new Date("2/2/2002"));
    private BirthDate birthDate3 = new BirthDate(new Date("11/1/2001"));

    @Test
    public void compareTo(){


        assertEquals(0, birthDate1.compareTo(birthDate3));
        assertEquals(-1, birthDate1.compareTo(birthDate2));
    }
}
