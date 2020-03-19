package agh.cs.lab4;

import java.util.ArrayList;
import java.util.List;

public class RectangularMap implements IWorldMap {
    private int width;
    private int height;

    Position lowerLeft = new Position (0, 0);
    Position upperRight = new Position( 0, 0);

    MapVisualizer map = new MapVisualizer(this);
    List<Car> cars = new ArrayList<>();

    public RectangularMap(int wdh, int hgt) {
        this.width = Math.abs(wdh);
        this.height = Math.abs(hgt);
        upperRight = new Position(this.width, this.height);
    }
    public boolean place(Car veh) {
        if(!this.isOccupied(veh.getPosition())) {
            cars.add(veh);
            return true;
        } else {
            return false;
        }
    }
    public boolean canMoveTo(Position posit) {
        if(posit.x >= 0 && posit.y >= 0 && posit.x <= this.width && posit.y <= this.height && !this.isOccupied(posit)) return true;
        else return false;
    }
    public void run(MoveDirection[] move) {
        int i = 0;
        if (cars.isEmpty()) return;
        for (MoveDirection moving : move) {
            cars.get(i % cars.size()).move(moving);
            i++;
        }
    }
    public boolean isOccupied(Position pos) {
        if (objectAT(pos) == null) return false;
        return true;
    }
    public Object objectAT(Position pos) {
        for (int i = 0; i < this.cars.size(); i++) {
            if(pos.equals(cars.get(i).getPosition())) return cars.get(i);
        }
        return null;
    }
    public String toString() {
        return new MapVisualizer(this).draw(new Position(0, 0), new Position(4, 4));
    }

}
