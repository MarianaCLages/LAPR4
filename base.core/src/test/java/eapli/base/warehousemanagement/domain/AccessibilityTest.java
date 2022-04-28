package eapli.base.warehousemanagement.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class AccessibilityTest {

    @Test
    void reverseLookupTest(){
        Optional<Accessibility> accessibility = Accessibility.get("w+");
        Assertions.assertTrue(accessibility.isPresent());

    }

    public static void main(String[] args) {
        Optional<Accessibility> accessibility = Accessibility.get("w+");
        System.out.println(accessibility.get());


    }

}