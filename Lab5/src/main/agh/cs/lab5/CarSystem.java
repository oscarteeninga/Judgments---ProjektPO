package agh.cs.lab5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CarSystem {
    public static void main(String[] args) {

        //Stworzenie listy stosów
        List<HayStack> stacks = new ArrayList<> ();
        System.out.println ("HayStacks positions:");
        for (int i = 0; i < 7; i++) {
            Random generator = new Random();
            Position posit = new Position(generator.nextInt() % 5 + 5, generator.nextInt() % 5 + 5);
            HayStack hStack = new HayStack(posit);
            stacks.add(hStack);
            System.out.println(posit.x + " " + posit.y);
        }
        UnboundedMap unMap = new UnboundedMap (stacks);

        //Dodanie do mapy samochodów
        System.out.println("");
        System.out.println("Car positions: ");
        for (int i = 0; i < 1; i++) {
            Random gen = new Random();
            Position posit = new Position (gen.nextInt() % 5 + 5, gen.nextInt() % 5 + 5);
            Car newCar = new Car(unMap, posit);
            unMap.place(newCar);
            System.out.println(newCar.getPosition().x + " " + newCar.getPosition().y);
        }
        System.out.println("");

        System.out.println(unMap.toString());
    }
}
