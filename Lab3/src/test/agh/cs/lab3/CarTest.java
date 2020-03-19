package agh.cs.lab3;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CarTest {
    @Test
    public void moveTest () {
        Car veh = new Car();
        veh.move(MoveDirection.Right);
        veh.move(MoveDirection.Forward);
        veh.move(MoveDirection.Forward);
        veh.move(MoveDirection.Forward);
        veh.toString();
        assertEquals(veh.toString(), "(4,2) : Wschód");
        assertEquals(veh.toString().equals("(0,1) : Południe"), false);
    }
    @Test
    public void mapTestOne () {
        Car veh = new Car();
        veh.move(MoveDirection.Forward);
        veh.move(MoveDirection.Forward);
        veh.move(MoveDirection.Forward);
        assertEquals(veh.toString(), "(2,4) : Północ");
        assertEquals(veh.toString().equals("(0,1) : Południe"), false);
    }
    @Test
    public void mapTestTwo () {
        Car veh = new Car();
        veh.move(MoveDirection.Right);
        veh.move(MoveDirection.Right);
        veh.move(MoveDirection.Backward);
        assertEquals(veh.toString(), "(2,3) : Południe");
        veh.move(MoveDirection.Backward);
        assertEquals(veh.toString(), "(2,4) : Południe");
        veh.move(MoveDirection.Backward);
        assertEquals(veh.toString(), "(2,4) : Południe");
        veh.move(MoveDirection.Backward);
        assertEquals(veh.toString(), "(2,4) : Południe");
        assertEquals(veh.toString().equals("(0,1) : Południe"), false);
    }
    @Test
    public void mapTestThree () {
        Car veh = new Car();
        veh.move(MoveDirection.Left);
        veh.move(MoveDirection.Forward);
        assertEquals(veh.toString(),"(1,2) : Zachód");
        veh.move(MoveDirection.Forward);
        assertEquals(veh.toString(),"(0,2) : Zachód");
        veh.move(MoveDirection.Forward);
        assertEquals(veh.toString(),"(0,2) : Zachód");
        veh.move(MoveDirection.Forward);
        assertEquals(veh.toString(),"(0,2) : Zachód");
        assertEquals(veh.toString().equals("(0,1) : Południe"), false);
    }
    @Test
    public void parserTest () {
        String []StringMoves = { "r", "f", "ratatata", "left", "backward", "f", "f"};
        Car veh = new Car();
        OptionParser that = new OptionParser();
        MoveDirection []Moves = that.parse(StringMoves);
        for(MoveDirection moving: Moves) {
            veh.move(moving);
        }
        assertEquals(veh.toString(), "(3,3) : Północ");
        assertEquals(veh.toString().equals("(0,1) : Południe"), false);
    }
}
