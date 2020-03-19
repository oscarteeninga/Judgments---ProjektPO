package agh.cs.lab6;

public class Position {
    public final int x;
    public final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        int hash = 13;
        hash += this.x * 31;
        hash += this.y * 17;
        return hash;
    }

    public String toString() {
        return ("(" + this.x + "," + this.y + ")");
    }

    public boolean smaller(Position P) {
        if (P.x >= this.x && P.y >= this.y) return true;
        return false;
    }

    public boolean larger(Position P) {
        if (P.x <= this.x && P.y <= this.y) return true;
        return false;
    }

    public Position upperRight(Position P) {
        Position that = new Position(Math.max(P.x, this.x), Math.max(P.y, this.y));
        return that;
    }

    public Position lowerLef(Position P) {
        Position that = new Position(Math.min(P.x, this.x), Math.min(P.y, this.y));
        return that;
    }

    public Position add(Position P) {
        Position X = new Position(P.x + this.x, P.y + this.y);
        return X;
    }

    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Position)) return false;
        Position that = (Position) other;
        if (that.x == this.x && that.y == this.y) return true;
        return false;
    }
}