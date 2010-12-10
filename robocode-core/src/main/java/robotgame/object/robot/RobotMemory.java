package robotgame.object.robot;

import org.apache.commons.lang.mutable.MutableInt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import robotgame.world.Direction;
import robotgame.world.Position;

import java.util.Stack;

public class RobotMemory {
    private static final Logger log = LoggerFactory.getLogger(RobotMemory.class);

    private final int columns;
    private final int rows;
    private Stack<RobotPath> path = new Stack<RobotPath>();
    private Robot owner;
    private Direction direction;
    private RobotMemoryObject[][] map;
    private Integer[][] pathMemory;

    public RobotMemory(Robot owner, int columns, int rows, Direction direction) {
        this.owner = owner;
        this.columns = columns;
        this.rows = rows;
        this.direction = direction;
        map = new RobotMemoryObject[columns][rows];
        pathMemory = new Integer[columns][rows];
        for(int i=0; i<columns; i++) {
            for (int j=0; j<rows; j++) {
                map[i][j] = RobotMemoryObject.UNKNOWN;
            }
        }
    }

    public void turnLeft(MutableInt usedPower)
    {
        usedPower.add(5);
        direction = direction.getLeft();
    }

    public void turnRight(MutableInt usedPower)
    {
        usedPower.add(5);
        direction = direction.getRight();
    }

    public Direction getDirection()
    {
        return direction;
    }

    private RobotMemoryObject getMemoryCell(Position p) {
//        if(checkPosition(p)) {
        return map[p.x][p.y];
    }

    public void setMemoryCell(Position p, RobotMemoryObject state) {
        if(checkPosition(p))
            if(map[p.x][p.y] != RobotMemoryObject.VISITED) {
                map[p.x][p.y] = state;
            }
    }

    public int lookAround(Position p, Direction direction) {
        Position p2 = direction.computeNextPosition(p);
        if (checkPosition(p2))
            return getMemoryCell(p2).ordinal();
        else
            return RobotMemoryObject.END.ordinal();
    }

    public boolean checkPosition(Position p) {
        return !((p.x < 0) || (p.x >= columns) || (p.y < 0) || (p.y >= rows));
    }

    public boolean findPath(int x, int y) {
        int value = 0;

        Position pathstart = owner.getPosition();
        Position pathstop = new Position(x,y);

        for (int i=0; i<columns; i++)
            for(int j=0; j<rows; j++)
                pathMemory[i][j] = null;

        setPathMemory(pathstart.x, pathstart.y,value);

        boolean endofmap = false;

        while(!endofmap)
        {
            endofmap = true;
            value++;
            for(int i=0; i<columns; i++)
            {
                for(int j=0; j<rows; j++)
                {
                    if(pathMemory[i][j] == value - 1)
                    {
                        endofmap = false;
                        if((i == pathstop.x) && (j == pathstop.y))
                        {
                            //writeMap();
                            setPath(i,j);
                            return true;
                        }
                        setPathMemory(i,j+1,value);
                        setPathMemory(i,j-1,value);
                        setPathMemory(i+1,j,value);
                        setPathMemory(i-1,j,value);
                    }
                }
            }

        }
        log.debug("Sciezka nie odnaleziona\n");
        return false;
    }

    private void setPathMemory(int x, int y, int value) {
        if (checkPosition(new Position(x,y))) {
            if (map[x][y] != RobotMemoryObject.UNMOVABLE) {
                if(pathMemory[x][y] == null ) {
                    pathMemory[x][y] = value;
                }
            }
        }
    }

    private void setPath(int x, int y) {

        Position p = new Position(x,y);
        path.push(RobotPath.FORWARD);
        Position p2 = getPathMemoryCellWithMinimalValueAroundSelectedPosition(p);
        Direction newdir = Direction.computeDirection(p2, p);

        p.x = p2.x;
        p.y = p2.y;

        Direction olddir;
        RobotPath robotPath;
        while(pathMemory[p.x][p.y] != 0) {
            p2 = getPathMemoryCellWithMinimalValueAroundSelectedPosition(p);

            olddir = newdir;
            newdir = Direction.computeDirection(p2,p);
            robotPath = computeRotation(olddir, newdir);

            if(robotPath != RobotPath.BACKWARD ) {
                path.push(robotPath);
            }
            path.push(RobotPath.FORWARD);
            p.x = p2.x;
            p.y = p2.y;
        }

        robotPath = computeRotation(direction, newdir);
        if (robotPath == RobotPath.FORWARD) {
            path.push(RobotPath.LEFT);
            path.push(RobotPath.LEFT);
        } else if (robotPath != RobotPath.BACKWARD) {
            path.push(robotPath);
        }
//	path.push(RobotPath.START);
    }

    private RobotPath computeRotation(Direction dirfrom, Direction dirto) {

        RobotPath result = RobotPath.FORWARD;
        if (dirfrom == dirto ) {
            return RobotPath.FORWARD;
        } else if (((dirfrom == Direction.NORTH) && (dirto == Direction.SOUTH)) ||
                ((dirfrom == Direction.SOUTH) && (dirto == Direction.NORTH)) ||
                ((dirfrom == Direction.WEST) && (dirto == Direction.EAST)) ||
                ((dirfrom == Direction.EAST) && (dirto == Direction.WEST))) {
            return RobotPath.BACKWARD;
        } else if (dirfrom == Direction.NORTH) {
            if(dirto == Direction.EAST)
                return RobotPath.LEFT;
            else
                return RobotPath.RIGHT;
        } else if (dirfrom == Direction.SOUTH) {
            if(dirto == Direction.WEST)
                return RobotPath.LEFT;
            else
                return RobotPath.RIGHT;
        } else if (dirfrom == Direction.WEST) {
            if(dirto == Direction.NORTH)
                return RobotPath.LEFT;
            else
                return RobotPath.RIGHT;
        } else if (dirfrom == Direction.EAST) {
            if(dirto == Direction.SOUTH)
                return RobotPath.LEFT;
            else
                return RobotPath.RIGHT;
        }
        return result;

    }

    private Position getPathMemoryCellWithMinimalValueAroundSelectedPosition(Position p) {
        Position pmin = new Position(p.x,p.y);

        Position temp = new Position(0,0);
        temp.x = p.x + 1;
        temp.y = p.y;
        if(checkPosition(temp))
            if ((pathMemory[temp.x][temp.y] < pathMemory[pmin.x][pmin.y]) && (pathMemory[temp.x][temp.y] != null))
            {
                pmin.x = temp.x;
                pmin.y = temp.y;
            }
        temp.x = p.x - 1;
        temp.y = p.y;
        if(checkPosition(temp))
            if ((pathMemory[temp.x][temp.y] < pathMemory[pmin.x][pmin.y]) && (pathMemory[temp.x][temp.y] != null))
            {
                pmin.x = temp.x;
                pmin.y = temp.y;
            }
        temp.x = p.x;
        temp.y = p.y + 1;
        if(checkPosition(temp))
            if ((pathMemory[temp.x][temp.y] < pathMemory[pmin.x][pmin.y]) && (pathMemory[temp.x][temp.y] != null))
            {
                pmin.x = temp.x;
                pmin.y = temp.y;
            }
        temp.x = p.x;
        temp.y = p.y - 1;
        if(checkPosition(temp))
            if ((pathMemory[temp.x][temp.y] < pathMemory[pmin.x][pmin.y]) && (pathMemory[temp.x][temp.y] != null))
            {
                pmin.x = temp.x;
                pmin.y = temp.y;
            }
        return pmin;
    }

    public int followPath()
    {
        return path.pop().ordinal();
    }

    public Position find(RobotMemoryObject type)
    {
        Position pmin = new Position(0,0);
        Position pmax = new Position(0,0);
        pmin.x = owner.getPosition().x;
        pmin.y = owner.getPosition().y;
        pmax.x = pmin.x;
        pmax.y = pmin.y;

        while((pmin.x > 0) || (pmin.y > 0) || (pmax.x < columns) || (pmax.y < rows))
        {
            pmin.x --;
            pmin.y --;
            pmax.x ++;
            pmax.y ++;
            for(int i=pmin.x; i<=pmax.x; i++)
                for(int j=pmin.y; j<=pmax.y; j++)
                    if (!((i < 0) || (i >= columns) || (j < 0) || (j >= rows)))
                        if(map[i][j] == type)
                        {
                            log.debug("ZNALEZIONO OBIEKT. XY = %d %d \n",i,j);
                            return new Position(i,j);
                        }
        }
        return new Position(-1,-1);
    }
}