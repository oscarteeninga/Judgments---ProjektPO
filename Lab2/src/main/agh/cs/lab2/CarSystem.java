package agh.cs.lab2;

import static java.lang.System.out;

public class CarSystem {
    public static void main(String[] args) {
        Position position1 = new Position(1,20);
        out.println(position1);
        Position position2 = new Position(12,1);
        Position position3 = new Position(-2,1);
        out.println(position2);
        out.println(position1.add(position2));
        out.println(position2.equals(position3));
        MapDirection direction1 = MapDirection.North;
        direction1 = direction1.previous();
        out.println(position1.upperRight(position2));
    }
}
