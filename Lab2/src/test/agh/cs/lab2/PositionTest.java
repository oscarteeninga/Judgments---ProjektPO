package agh.cs.lab2;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PositionTest {
    @Test
    public void testToString() {
        assertEquals("(2,1)", new Position(2, 1).toString());
    }
    @Test
    public void testsmaller() {
        assertEquals(true, new Position(2, 1).smaller(new Position(3, 3)));
    }
    @Test
    public void testlarger() {
        assertEquals(false, new Position(2, 1).larger(new Position (3, 2)));
    }
    @Test
    public void testupperRight() {
        Position p1 = new Position(2, 1);
        Position p2 = new Position(1, 2);
        assertEquals("(2,2)", p1.upperRight(p2).toString());
    }
    @Test
    public void testlowerLeft() {
        Position p1 = new Position(2, 1);
        Position p2 = new Position(1, 2);
        assertEquals(Math.min(p1.x, p2.x), p1.lowerLeft(p2).x);
        assertEquals(Math.min(p1.y,p2.y), p1.lowerLeft(p2).y);
    }
    @Test
    public void testadd() {
        Position p1 = new Position(2, 1);
        Position p2 = new Position(1, 2);
        assertEquals("(3,3)", p1.add(p2).toString());
        assertEquals(p2.x + p1.x, p1.add(p2).x);
        assertEquals(p2.y + p1.y, p1.add(p2).y);
    }
}
