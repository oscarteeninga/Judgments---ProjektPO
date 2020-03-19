package agh.cs.lab5;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class RectangularMapTest {
    RectangularMap recmap = new RectangularMap(10, 213);
    Position notonmapposit = new Position(12, 213);
    @Test
    public void placeTest () { //analogiczne do place!!!
        assertEquals(recmap.canMoveTo(notonmapposit), false);
    }
    @Test
    public void moveTest () {
        Position start = new Position (5, 15);
        String[] arg = { "f", "f", "r", "r", "f", "f"};
        MoveDirection[] directions = new OptionsParser().parse(arg);
        Car testcar1 = new Car(recmap, start);
        Car testcar2 = new Car(recmap, notonmapposit);
        recmap.place(testcar1);
        assertEquals(recmap.place(testcar2), false);
        recmap.run(directions);
        assertEquals(testcar1.getPosition(), start);
        assertEquals(testcar1.toString(), "S");
    }
    @Test
    public void toStringTest() {
        Position lowerLeft = new Position( 0, 0);
        Position upperRight = new Position(10, 213);
        assertEquals(recmap.mapvis.draw(lowerLeft, upperRight), recmap.toString());
    }
}
