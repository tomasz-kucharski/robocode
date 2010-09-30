import java.util.Random;

public class Direction {

    public static int NORTH = 2;
    public static int EAST = 3;
    public static int SOUTH = 0;
    public static int WEST = 1;

    private static Random random = new Random();

    public static int getDirectionByName(String direction)
    {
        if (direction != null) {
            if("NORTH".equals(direction))
                return 0;
            if("EAST".equals(direction))
                return 1;
            if("SOUTH".equals(direction))
                return 2;
            if("WEST".equals(direction))
                return 3;
        }
        return -1;
    }

    public static void computePosition(Position position, int direction)
    {
        if (direction == NORTH)
            position.y--;
        else if (direction == EAST)
            position.x++;
        else if (direction == SOUTH)
            position.y++;
        else if (direction == WEST)
            position.x--;
    }

    static public float computeRotation(int direction)
    {
        if (direction == NORTH)
            return 0.0f;
        else if (direction == EAST)
            return 90.0f;
        else if (direction == SOUTH)
            return 180.0f;
        else if (direction == WEST)
            return 270.f;
        else {
            System.exit(997);
            return 0.0f;
        }
    }

    public static int randDirection()
    {
        return random.nextInt(4);
    }

    public static int getLeft(int direction)
    {
        return 	(direction + 1) % 4;
    }

    public static int getRight(int direction)
    {
        return (direction + 3) % 4;
    }

    public static int getBackward(int direction)
    {
        return direction % 4;
    }

    public int getForward(int direction)
    {
        return (direction + 2) % 4;
    }

    public static int computeDirection(Position from, Position to)
    {
        if(from.y > to.y )
            return NORTH;
        if(from.y < to.y )
            return SOUTH;
        if(from.x > to.x )
            return EAST;
        if(from.x < to.x )
            return WEST;
        return -1;
    }

}