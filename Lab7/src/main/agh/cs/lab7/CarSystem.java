package agh.cs.lab7;

import java.util.*;

public class CarSystem {
    public static void main(String[] args) throws IllegalArgumentException {
        try {
            //Stworzenie listy stosów
            Map<Position, HayStack> stacks = new HashMap<>();
            System.out.println("HayStacks positions:");
            for (int i = 0; i < 7; i++) {
                Random generator = new Random();
                Position posit = new Position(generator.nextInt() % 5 + 5, generator.nextInt() % 5 + 5);
                HayStack hStack = new HayStack(posit);
                stacks.put(posit, hStack);
                System.out.println(posit.x + " " + posit.y);
            }
            UnboundedMap unMap = new UnboundedMap(stacks);

            //Dodanie do mapy samochodów
            System.out.println("");
            System.out.println("Car positions: ");
            for (int i = 0; i < 4; i++) {
                Random gen = new Random();
                Position posit = new Position(gen.nextInt() % 5 + 5, gen.nextInt() % 5 + 5);
                Car newCar = new Car(unMap, posit);
                newCar.addObserver(unMap);
                System.out.println(newCar.getPosition().x + " " + newCar.getPosition().y);
                unMap.place(newCar);
            }
            System.out.println("");
            String[] arg = { "f", "b", "r", "l"};

            MoveDirection[] moves = new OptionsParser().parse(arg);
            unMap.run(moves);
            System.out.println(unMap.toString());
        } catch (IllegalArgumentException ex) {
            System.out.println("=========EXCEPTION=========");
            System.out.println(ex.getMessage());
        }
    }
}
