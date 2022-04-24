package eapli.base.warehousemanagement.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShelfIdentifierTest {

    @Test
    void aisleIdentifierCorrect() {
        ShelfIdentifier shelfIdentifier = new ShelfIdentifier(5486, 48, 1);
        assertEquals(5486, shelfIdentifier.aisleIdentifier());
    }

    @Test
    void rowIdentifierCorrect() {
        ShelfIdentifier shelfIdentifier = new ShelfIdentifier(5486, 48, 1);
        assertEquals(48, shelfIdentifier.rowIdentifier());
    }

    @Test
    void shelfIdentifierCorrect() {
        ShelfIdentifier shelfIdentifier = new ShelfIdentifier(5486, 48, 1);
        assertEquals(1, shelfIdentifier.shelfIdentifier());
    }

    @Test
    void testEqualsAndHashCode() {
        ShelfIdentifier shelfIdentifier = new ShelfIdentifier(5486, 48, 1);
        ShelfIdentifier shelfIdentifier2 = new ShelfIdentifier(5486, 48, 1);
        assertEquals(shelfIdentifier, shelfIdentifier2);
        assertEquals(shelfIdentifier.hashCode(), shelfIdentifier2.hashCode());
    }


    @Test
    void compareTo() {
        //are equal
        ShelfIdentifier shelfIdentifier = new ShelfIdentifier(5486, 48, 1);
        ShelfIdentifier shelfIdentifier2 = new ShelfIdentifier(5486, 48, 1);
        assertEquals(0, shelfIdentifier.compareTo(shelfIdentifier2));

        //shelfIdentifier is greater
        ShelfIdentifier shelfIdentifier3 = new ShelfIdentifier(5486, 48, 2);
        assertEquals(-1, shelfIdentifier.compareTo(shelfIdentifier3));

        //rowIdentifier is greater
        ShelfIdentifier shelfIdentifier4 = new ShelfIdentifier(5486, 49, 1);
        assertEquals(-1, shelfIdentifier.compareTo(shelfIdentifier4));

        //aisleIdentifier is greater
        ShelfIdentifier shelfIdentifier5 = new ShelfIdentifier(5487, 48, 1);
        assertEquals(-1, shelfIdentifier.compareTo(shelfIdentifier5));

        //shelfIdentifier is smaller
        ShelfIdentifier shelfIdentifier6 = new ShelfIdentifier(5485, 48, 2);
        assertEquals(1, shelfIdentifier.compareTo(shelfIdentifier6));

        //rowIdentifier is smaller
        ShelfIdentifier shelfIdentifier7 = new ShelfIdentifier(5486, 47, 1);
        assertEquals(1, shelfIdentifier.compareTo(shelfIdentifier7));

        //aisleIdentifier is smaller
        ShelfIdentifier shelfIdentifier8 = new ShelfIdentifier(5485, 48, 1);
        assertEquals(1, shelfIdentifier.compareTo(shelfIdentifier8));

        //shelfIdentifier is smaller
        ShelfIdentifier shelfIdentifier9 = new ShelfIdentifier(5486, 48, 0);
        assertEquals(1, shelfIdentifier.compareTo(shelfIdentifier9));

    }
}