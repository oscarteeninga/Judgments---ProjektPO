package agh.cs.lab7;

public class HayStack implements IMapElement {
    private final Position posit;

    public HayStack (Position setPlace) {
        this.posit = setPlace;
    }

    public Position getPosition() {
        return this.posit;
    }

    public String toString() {
        return "s";
    }

}
