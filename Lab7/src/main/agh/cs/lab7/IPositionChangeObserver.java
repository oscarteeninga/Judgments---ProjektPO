package agh.cs.lab7;

public interface IPositionChangeObserver {
    public void positionChanged(Position oldPosition, Position newPosition);
}
