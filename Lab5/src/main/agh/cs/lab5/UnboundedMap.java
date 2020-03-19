package agh.cs.lab5;

import java.util.ArrayList;
import java.util.List;

public class UnboundedMap extends AbstractWorldMap {
    static int maxINT = 2147483647;
    static int minINT = -2147483648;

    public UnboundedMap (List<HayStack> hStack) {
        this.stacks = hStack;
    }

    public boolean canMoveTo(Position posit) {
        if(!this.isOccupied(posit)) return true;
        else return false;
    }

    public void setUpLow() {

    }

    public String toString() {
        upperRight = new Position(minINT, minINT);
        lowerLeft = new Position(maxINT, maxINT);
        for (int i = 0; i < this.cars.size(); i++) {
            Position posit = this.cars.get(i).getPosition();
            if (posit.x > upperRight.x) upperRight = new Position(posit.x, upperRight.y);
            if (posit.y > upperRight.y) upperRight = new Position (upperRight.x, posit.y);
            if (posit.x < lowerLeft.x) lowerLeft = new Position(posit.x, lowerLeft.y);
            if (posit.y < lowerLeft.y) lowerLeft = new Position(lowerLeft.x, posit.y );
        }
        for (int i = 0; i < this.stacks.size(); i++) {
            Position posit = this.stacks.get(i).getPosition();
            if (posit.x > upperRight.x) upperRight = new Position(posit.x, upperRight.y);
            if (posit.x < lowerLeft.x) lowerLeft = new Position(posit.x, lowerLeft.y);
            if (posit.y > upperRight.y) upperRight = new Position (upperRight.x, posit.y);
            if (posit.y < lowerLeft.y) lowerLeft = new Position(lowerLeft.x, posit.y);
        }
        return mapvis.draw(lowerLeft, upperRight);
    }
}
