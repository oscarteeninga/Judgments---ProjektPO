package agh.cs.lab7;

public class RectangularMap extends AbstractWorldMap {
    private int width;
    private int height;

    public RectangularMap(int wdh, int hgt) {
        this.width = Math.abs(wdh);
        this.height = Math.abs(hgt);
        upperRight = new Position(width, height);
        lowerLeft = new Position(0, 0);
    }
    public boolean canMoveTo(Position posit) {
        if(posit.x >= 0 && posit.y >= 0 && posit.x <= this.width && posit.y <= this.height && !this.isOccupied(posit)) return true;
        else return false;
    }

    public String toString() {
        return mapvis.draw(lowerLeft, upperRight);
    }
}
