package agh.cs.lab3;

public enum MapDirection {
    North, South, West, East;
    public String toString () {
        switch(this) {
            case North: return "Północ";
            case East: return "Wschód";
            case South: return "Południe";
            case West: return "Zachód";
            default: return "Unknown direction";
        }
    }
    public MapDirection next () {
        switch(this) {
            case North: return East;
            case East: return South;
            case South: return West;
            case West: return North;
            default: return null;
        }
    }
    public MapDirection previous () {
        switch(this) {
            case East: return North;
            case South: return East;
            case West: return South;
            case North: return West;
            default: return null;
        }
    }
}