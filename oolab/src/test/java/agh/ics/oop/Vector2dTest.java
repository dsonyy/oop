package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {

    @Test
    public void equalsTest() {
        Vector2d v = new Vector2d(1, 2);

        assertEquals(new Vector2d(1, 2), new Vector2d(1, 2));
        assertEquals(v, v);
        assertNotEquals(v, null);
        assertNotEquals(new Vector2d(1, 2), new Vector2d(2, 1));
    }

    @Test
    public void toStringTest() {
        assertEquals(new Vector2d(1, 2).toString(), "(1,2)");
        assertEquals(new Vector2d(2, -3).toString(), "(2,-3)");
        assertEquals(new Vector2d(-100, 0).toString(), "(-100,0)");
        assertEquals(new Vector2d(-42, -9999).toString(), "(-42,-9999)");
    }

    @Test
    public void precedesTest() {
        assertTrue(new Vector2d(1, 1).precedes(new Vector2d(1, 1)));
        assertFalse(new Vector2d(1, 1).precedes(new Vector2d(1, -3)));
        assertFalse(new Vector2d(1, 1).precedes(new Vector2d(-3, 1)));
        assertTrue(new Vector2d(1, 1).precedes(new Vector2d(1, 3)));
        assertTrue(new Vector2d(1, 1).precedes(new Vector2d(3, 1)));
        assertTrue(new Vector2d(1, 1).precedes(new Vector2d(2, 2)));
        assertFalse(new Vector2d(1, 1).precedes(new Vector2d(2, -2)));
        assertFalse(new Vector2d(1, 1).precedes(new Vector2d(-2, 2)));
        assertFalse(new Vector2d(1, 1).precedes(new Vector2d(-2, -2)));
    }

    @Test
    public void followsTest() {
        assertTrue(new Vector2d(1, 1).follows(new Vector2d(1, 1)));
        assertTrue(new Vector2d(1, 1).follows(new Vector2d(1, -3)));
        assertTrue(new Vector2d(1, 1).follows(new Vector2d(-3, 1)));
        assertFalse(new Vector2d(1, 1).follows(new Vector2d(1, 3)));
        assertFalse(new Vector2d(1, 1).follows(new Vector2d(3, 1)));
        assertFalse(new Vector2d(1, 1).follows(new Vector2d(2, 2)));
        assertFalse(new Vector2d(1, 1).follows(new Vector2d(2, -2)));
        assertFalse(new Vector2d(1, 1).follows(new Vector2d(-2, 2)));
        assertTrue(new Vector2d(1, 1).follows(new Vector2d(-2, -2)));
    }

    @Test
    public void upperRightTest() {
        assertEquals(new Vector2d(1, 2).upperRight(new Vector2d(-1, -3)), new Vector2d(1, 2));
        assertEquals(new Vector2d(1, 2).upperRight(new Vector2d(-1, 3)), new Vector2d(1, 3));
        assertEquals(new Vector2d(1, 2).upperRight(new Vector2d(2, -3)), new Vector2d(2, 2));
        assertEquals(new Vector2d(1, 2).upperRight(new Vector2d(2, 3)), new Vector2d(2, 3));
    }

    @Test
    public void lowerLeftTest() {
        assertEquals(new Vector2d(1, 2).lowerLeft(new Vector2d(-1, -3)), new Vector2d(-1, -3));
        assertEquals(new Vector2d(1, 2).lowerLeft(new Vector2d(-1, 3)), new Vector2d(-1, 2));
        assertEquals(new Vector2d(1, 2).lowerLeft(new Vector2d(2, -3)), new Vector2d(1, -3));
        assertEquals(new Vector2d(1, 2).lowerLeft(new Vector2d(2, 3)), new Vector2d(1, 2));
    }

    @Test
    public void addTest() {
        assertEquals(new Vector2d(2, 1).add(new Vector2d(2, 1)), new Vector2d(4,2));
        assertEquals(new Vector2d(2, 1).add(new Vector2d(-1, -3)), new Vector2d(1, -2));
        assertEquals(new Vector2d(0, 0).add(new Vector2d(-999, 888)), new Vector2d(-999, 888));
    }

    @Test
    public void subtractTest() {
        assertEquals(new Vector2d(2, 1).subtract(new Vector2d(2, 1)), new Vector2d(0,0));
        assertEquals(new Vector2d(2, 1).subtract(new Vector2d(-1, -3)), new Vector2d(3, 4));
        assertEquals(new Vector2d(0, 0).subtract(new Vector2d(-999, 888)), new Vector2d(999, -888));
    }

    @Test
    public void oppositeTest() {
        assertEquals(new Vector2d(1, 2).opposite(), new Vector2d(-1, -2));
        assertEquals(new Vector2d(-1, 2).opposite(), new Vector2d(1, -2));
        assertEquals(new Vector2d(1, -2).opposite(), new Vector2d(-1, 2));
        assertEquals(new Vector2d(-1, -2).opposite(), new Vector2d(1, 2));
    }

}
