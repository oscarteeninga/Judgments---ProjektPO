package agh.cs.lab7;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class UnboundedMapTest {
    Map<Position, HayStack> stack = new HashMap<>();
    //HayStack hay1 = new HayStack (new Position (10, 10));
    //HayStack hay2 = new HayStack (new Position (-20, -10));
    UnboundedMap umap = new UnboundedMap(stack);
    @Test
    public void placeTest () {
        Car car = new Car (umap, new Position(5, -20));
        assertEquals(umap.place(car), true);
    }
    @Test
    public void moveTest () {
        Position start = new Position (9, 10);
        String[] arg = { "f", "f", "r", "r", "f", "f"};
        MoveDirection[] directions = new OptionsParser().parse(arg);
        Car testcar1 = new Car(umap, start);
        testcar1.addObserver(umap);
        umap.place(testcar1);
        umap.run(directions);
        assertEquals(testcar1.getPosition().equals(start), true);
        assertEquals(testcar1.toString(), "S");
    }
    @Test
    public void toStringTest() {
        Car car1 = new Car (umap, new Position (10, -20));
        Car car2 = new Car (umap, new Position (-20, 10));
        umap.place(car1);
        umap.place(car2);
        Position upperRight = new Position (10, 10);
        Position lowerLeft = new Position (-20, -20);
        assertEquals(umap.mapvis.draw(lowerLeft, upperRight), umap.toString());
    }
}
