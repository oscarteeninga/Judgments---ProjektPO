package agh.cs.lab2;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MapDirectionTest {
    @Test
    public void testGetX() {
        assertEquals(2, new Position(2, 1).x);
    }
    @Test
    public void testNext() {
        MapDirection dir = MapDirection.East;
        assertEquals(MapDirection.South, dir.next());
        dir = dir.next();
        assertEquals(MapDirection.West, dir.next());
        dir = dir.next();
        assertEquals(MapDirection.North, dir.next());
        dir = dir.next();
        assertEquals(MapDirection.East, dir.next());
    }
    @Test
    public void testPrevious() {
        MapDirection dir = MapDirection.East;
        assertEquals(MapDirection.North, dir.previous());
        dir = dir.previous();
        assertEquals(MapDirection.West, dir.previous());
        dir = dir.previous();
        assertEquals(MapDirection.South, dir.previous());
        dir = dir.previous();
        assertEquals(MapDirection.East, dir.previous());
    }
}
