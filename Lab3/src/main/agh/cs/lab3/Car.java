package agh.cs.lab3;

public class Car {
    private MapDirection mapdir;
    private Position posit;

    public Car() {
        this.mapdir = MapDirection.North;
        this.posit = new Position (2,2);
    }

    public static void main(String []args) {
        System.out.println();
        String []StringMoves = { "r", "asda", "f", "f", "f" };
        OptionParser that = new OptionParser();
        MoveDirection []Moves = that.parse(StringMoves);
        Car vehicule = new Car();
        for (MoveDirection Move : Moves) {
            vehicule.move(Move);
        }
        System.out.println(vehicule.toString());
    }

    public String toString() {
        return ("(" + posit.x + "," + posit.y + ")" + " : " + mapdir.toString());
    }

    public void move (MoveDirection direction) {
        switch(direction) {
            case Right: {
                this.mapdir = this.mapdir.next();
                break;
            }
            case Left: {
                this.mapdir = this.mapdir.previous();
                break;
            }
            case Forward: {
                switch(this.mapdir) {
                    case North: {
                        if (this.posit.y < 4) this.posit = new Position(this.posit.x, this.posit.y + 1);
                        break;
                    }
                    case South: {
                        if (this.posit.y > 0) this.posit = new Position(this.posit.x, this.posit.y - 1);
                        break;
                    }
                    case East: {
                        if(this.posit.x < 4) this.posit = new Position(this.posit.x + 1, this.posit.y);
                        break;
                    }
                    case West: {
                        if(this.posit.x > 0) this.posit = new Position(this.posit.x - 1, this.posit.y);
                        break;
                    }
                    default:;
                }
                break;
            }
            case Backward: {
                switch(this.mapdir) {
                    case North: {
                        if (this.posit.y > 0) this.posit = new Position(this.posit.x, this.posit.y - 1);
                        break;
                    }
                    case South: {
                        if (this.posit.y < 4) this.posit = new Position(this.posit.x, this.posit.y + 1);
                        break;
                    }
                    case East: {
                        if(this.posit.x > 0) this.posit = new Position(this.posit.x - 1, this.posit.y);
                        break;
                    }
                    case West: {
                        if(this.posit.x < 4) this.posit = new Position(this.posit.x + 1, this.posit.y);
                        break;
                    }
                    default:;
                }
                break;
            }
            default: ;
        }

    }
}
