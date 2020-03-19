package agh.cs.lab5;

import java.util.ArrayList;
import java.util.List;

abstract class AbstractWorldMap implements IWorldMap {
    protected List<Car> cars = new ArrayList<> ();
    protected List<HayStack> stacks = new ArrayList<> ();
    protected Position upperRight;
    protected Position lowerLeft;
    protected MapVisualizer mapvis = new MapVisualizer(this);

    public void run(MoveDirection[] move) {
        int i = 0;
        if (cars.isEmpty()) return;
        for (MoveDirection moving : move) {
            cars.get(i % cars.size()).move(moving);
            i++;
        }
    }

    public Object objectAT(Position pos) {
        for (int i = 0; i < this.cars.size(); i++) {
            if(pos.equals(this.cars.get(i).getPosition())) return cars.get(i);
        }
        for (int i = 0; i < this.stacks.size(); i++) {
            if(pos.equals(this.stacks.get(i).getPosition())) return stacks.get(i);
        }
        return null;
    }

    public boolean place(Car veh) {
        if(this.canMoveTo(veh.getPosition())) {
            this.cars.add(veh);
            return true;
        } else {
            return false;
        }
    }

    public boolean isOccupied(Position pos) {
        if (objectAT(pos) == null) return false;
        return true;
    }
}
