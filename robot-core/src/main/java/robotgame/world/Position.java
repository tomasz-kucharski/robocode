package robotgame.world;

public class Position {
    public int x;
    public int y;

    public Position() {
    }

    public Position(Position p) {
        this.x = p.x;
        this.y = p.y;
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
