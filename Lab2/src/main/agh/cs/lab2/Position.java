package agh.cs.lab2;

public class Position {
    public final int x;
    public final int y;
    public Position (int x, int y) {
        this.x = x;
        this.y = y;
    }
    public String toString() {
       return ("(" + this.x + "," + this.y + ")");
    }
    public boolean smaller (Position that) {
        if (that.x >= this.x && that.y >= this.y) return true;
        return false;
    }
    public boolean larger (Position that) {
        if (that.x <= this.x && that.y <= this.y) return true;
        return false;
    }
    public Position upperRight (Position other) {
        Position that = new Position(Math.max(other.x,this.x), Math.max(other.y,this.y));
        return that;
    }
    public Position lowerLeft(Position other) {
        Position that = new Position(Math.min(other.x,this.x), Math.min(other.y,this.y));
        return that;
    }
    public Position add (Position other) {
        Position that = new Position(other.x + this.x, other.y + this.y);
        return that;
    }
    public boolean equals (Object other) {
        if (this == other) return true;
        if (!(other instanceof Position)) return false;
        Position that = (Position) other;
        if (that.x == this.x && that.y == this.y) return true;
        return false;
    }
}
