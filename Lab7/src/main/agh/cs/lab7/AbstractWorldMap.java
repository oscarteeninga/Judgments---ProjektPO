package agh.cs.lab7;

import java.util.*;

abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected Map<Position, Car> cars = new HashMap<>();
    protected Map<Position, HayStack> stacks = new HashMap<> ();
    protected Position upperRight;
    protected Position lowerLeft;
    protected MapVisualizer mapvis = new MapVisualizer(this);

    public void run(MoveDirection[] move) {
        if (this.cars.isEmpty()) return;
        for (int i = 0; i < move.length;) {
            var keys = new ArrayList<Position>();
            keys.addAll(this.cars.keySet());
            for (Position key : keys) {
                this.cars.get(key).move(move[i]);
                this.cars.get(key).positionChanged(key, this.cars.get(key).getPosition());
                i++;
                if (i >= move.length) break;
            }
        }
    }

    public Object objectAT(Position pos) {
        if (this.cars.get(pos) != null) return this.cars.get(pos);
        else if (this.stacks.get(pos) != null) return this.stacks.get(pos);
        else return null;
    }

    public boolean place(Car veh) {
        if(!this.canMoveTo(veh.getPosition())) {
            throw new IllegalArgumentException("You tried to place the Car on unplaceable position: (" + veh.getPosition().x + "," + veh.getPosition().y + ")");
        } else {
            this.cars.put(veh.getPosition(), veh);
            return true;
        }
    }

    public boolean isOccupied(Position pos) {
        if (objectAT(pos) == null) return false;
        else return true;
    }

    public void positionChanged (Position oldPosition, Position newPosition) {
        Car movedCar = this.cars.get(oldPosition);
        this.cars.remove(oldPosition);
        this.cars.put(newPosition, movedCar);
    }
}
