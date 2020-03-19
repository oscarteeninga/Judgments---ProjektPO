package agh.cs.lab7;

import java.util.*;

public class UnboundedMap extends AbstractWorldMap {
    static int maxINT = 2147483647;
    static int minINT = -2147483648;

    public UnboundedMap (Map<Position, HayStack> hStack) {
        this.stacks = hStack;
    }

    public boolean canMoveTo(Position posit) {
        if(!this.isOccupied(posit)) return true;
        else return false;
    }

    public String toString() {
        upperRight = new Position(minINT, minINT);
        lowerLeft = new Position(maxINT, maxINT);
        for (Position posit : this.cars.keySet()) {
            if (this.cars.get(posit).getPosition().x > upperRight.x) upperRight = new Position(posit.x, upperRight.y);
            if (this.cars.get(posit).getPosition().y > upperRight.y) upperRight = new Position (upperRight.x, posit.y);
            if (this.cars.get(posit).getPosition().x < lowerLeft.x) lowerLeft = new Position(posit.x, lowerLeft.y);
            if (this.cars.get(posit).getPosition().y < lowerLeft.y) lowerLeft = new Position(lowerLeft.x, posit.y );
        }
        for (Position posit : this.stacks.keySet()) {
            if (this.stacks.get(posit).getPosition().x > upperRight.x) upperRight = new Position(posit.x, upperRight.y);
            if (this.stacks.get(posit).getPosition().x < lowerLeft.x) lowerLeft = new Position(posit.x, lowerLeft.y);
            if (this.stacks.get(posit).getPosition().y > upperRight.y) upperRight = new Position (upperRight.x, posit.y);
            if (this.stacks.get(posit).getPosition().y < lowerLeft.y) lowerLeft = new Position(lowerLeft.x, posit.y);
        }
        return mapvis.draw(lowerLeft, upperRight);
    }
}
