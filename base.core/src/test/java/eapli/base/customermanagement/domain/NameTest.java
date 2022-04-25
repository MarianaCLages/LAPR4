package eapli.base.customermanagement.domain;

import eapli.base.customermanagement.domain.Name;
import org.junit.Test;
import static org.junit.Assert.*;


public class NameTest {


    private Name name1 = new Name("Tiago Ferreira");
    private Name name2 = new Name("Tiago Ferreira");
    private Name name3 = new Name("Ambrosio dos Brosios");


    @Test
    public void compareTo(){

        assertEquals(0, name1.compareTo(name2));
        assertEquals(-1, name1.compareTo(name3));
    }
}
