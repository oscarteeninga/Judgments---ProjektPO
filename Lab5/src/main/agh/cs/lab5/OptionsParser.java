package agh.cs.lab5;

public class OptionsParser {
    public MoveDirection[] parse (String [] Moves) {
        MoveDirection []Dir = new MoveDirection[Moves.length];
        int i = 0;
        for (String Move : Moves) {
            switch(Move) {
                case "f": Dir[i] = MoveDirection.Forward; i++; break;
                case "forward": Dir[i] = MoveDirection.Forward; i++; break;
                case "b": Dir[i] = MoveDirection.Backward; i++; break;
                case "backward": Dir[i] = MoveDirection.Backward; i++; break;
                case "r": Dir[i] = MoveDirection.Right; i++; break;
                case "right": Dir[i] = MoveDirection.Right; i++; break;
                case "l": Dir[i] = MoveDirection.Left; i++; break;
                case "left": Dir[i] = MoveDirection.Left; i++; break;
                default: break;
            }
        }
        MoveDirection []dir = new MoveDirection[i];
        for (int j = 0; j < i; j++) dir[j] = Dir[j];
        return dir;
    }
}
