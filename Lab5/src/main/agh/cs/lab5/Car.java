package agh.cs.lab5;

public class Car implements IMapElement {
    private MapDirection mapdir;
    private Position posit;
    private IWorldMap map;

    public Car(IWorldMap map) {
         this.mapdir = MapDirection.North;
         this.posit = new Position(2, 2);
         this.map = map;
    }

    public Car(IWorldMap map, Position initialPosition) {
        this.mapdir = MapDirection.North;
        this.posit = initialPosition;
        this.map = map;
    }

    public Position getPosition() {
        return this.posit;
    }

    public String toString() {
        switch(this.mapdir) {
            case North: return "N";
            case South: return "S";
            case West: return "W";
            case East: return "E";
            default: return "Error";
        }
    }

    public void move (MoveDirection direction) {
        Position newposit = new Position(posit.x, posit.y);
        switch(direction) {
            case Right: { this.mapdir = this.mapdir.next(); break; }
            case Left: { this.mapdir = this.mapdir.previous(); break; }
            case Forward: {
                switch(this.mapdir) {
                    case North: { newposit = new Position(this.posit.x, this.posit.y + 1); break; }
                    case South: { newposit = new Position(this.posit.x, this.posit.y -1); break; }
                    case East: { newposit = new Position(this.posit.x + 1, this.posit.y ); break; }
                    case West: { newposit = new Position(this.posit.x - 1, this.posit.y); break; }
                    default:;
                }
                break;
            }
            case Backward: {
                switch(this.mapdir) {
                    case North: { newposit = new Position(this.posit.x, this.posit.y -1); break; }
                    case South: { newposit = new Position(this.posit.x, this.posit.y + 1); break; }
                    case East: { newposit = new Position(this.posit.x - 1, this.posit.y); break; }
                    case West: { newposit = new Position(this.posit.x + 1, this.posit.y); break; }
                    default: ;
                }
                break;
            }
            default:;
        }
        if(this.map.canMoveTo(newposit)) this.posit = newposit;
    }
}
