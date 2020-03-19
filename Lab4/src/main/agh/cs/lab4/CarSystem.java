package agh.cs.lab4;


public class CarSystem {
    public static void main(String[] args) {
        String[] arg = { "f", "b", "f"};
        MoveDirection[] directions = new OptionsParser().parse(arg);
        IWorldMap map = new RectangularMap(10, 5);

        Position initialPosition = new Position (3, 4);

        map.place(new Car(map));
        map.place(new Car(map,initialPosition));
        map.run(directions);
        System.out.println(map);
    }
}
