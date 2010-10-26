package robotgame.world;

import java.util.Random;

public enum Direction {

    NORTH(0,0f),
    EAST(1,90f),
    SOUTH(2,180f),
    WEST(3,270f);

    private static Random random = new Random();

    private int direction;
    private float rotation;

    Direction(int direction, float rotation) {
        this.direction = direction;
        this.rotation = rotation;
    }

    public float getRotation() {
        return rotation;
    }

    public Position computePosition(Position position) {
        Position newPosition = new Position(position.x,position.y);
        if (this == NORTH)
            newPosition.y--;
        else if (this == EAST)
            newPosition.x++;
        else if (this == SOUTH)
            newPosition.y++;
        else if (this == WEST)
            newPosition.x--;
        return newPosition;
    }

    public Direction getLeft() {
        return Direction.values()[(this.ordinal()+ 3) % 4];
    }

    public Direction getRight() {
        return Direction.values()[(this.ordinal()+ 1) % 4];
    }

    public Direction getBackward() {
        return Direction.values()[(this.ordinal()+ 2) % 4];
    }

    public static Direction computeDirection(Position from, Position to) {
        if(from.y > to.y )
            return NORTH;
        if(from.y < to.y )
            return SOUTH;
        if(from.x > to.x )
            return EAST;
        if(from.x < to.x )
            return WEST;
        throw new UnsupportedOperationException("");
    }

    public static Direction randDirection() {
        return Direction.values()[random.nextInt(4)];
    }
}