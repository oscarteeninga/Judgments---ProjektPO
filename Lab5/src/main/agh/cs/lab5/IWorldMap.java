package agh.cs.lab5;

public interface IWorldMap {
    boolean canMoveTo (Position pos);
    boolean place (Car veh);
    void run(MoveDirection[] direction);
    boolean isOccupied(Position pos);
    Object objectAT(Position pos);
}
