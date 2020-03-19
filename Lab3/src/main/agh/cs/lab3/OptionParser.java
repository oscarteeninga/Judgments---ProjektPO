package agh.cs.lab3;

public class OptionParser {
    public MoveDirection[] parse (String [] Moves) {
        MoveDirection []basicDir = new MoveDirection[Moves.length];
        int i = 0;
        for (String Move : Moves) {
            switch(Move) {
                case "f": basicDir[i] = MoveDirection.Forward; i++; break;
                case "forward": basicDir[i] = MoveDirection.Forward; i++; break;
                case "b": basicDir[i] = MoveDirection.Backward; i++; break;
                case "backward": basicDir[i] = MoveDirection.Backward; i++; break;
                case "r": basicDir[i] = MoveDirection.Right; i++; break;
                case "right": basicDir[i] = MoveDirection.Right; i++; break;
                case "l": basicDir[i] = MoveDirection.Left; i++; break;
                case "left": basicDir[i] = MoveDirection.Left; i++; break;
                default: break;
            }
        }
        MoveDirection []dir = new MoveDirection[i];
        for (int j = 0; j < i; j++) dir[j] = basicDir[j];
        return dir;
    }
}
