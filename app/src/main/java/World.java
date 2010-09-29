import org.apache.commons.lang.mutable.MutableInt;
import robot.object.WorldObject;

import java.util.Random;

public class World {
    WorldObjectList world[][];
    private int columns;
    private int rows;

    Random random;

    public World(int columns, int rows)
    {
        this.columns = columns;
        this.rows = rows;

        world = new WorldObjectList [columns][rows];
        for (int i=0; i<columns; i++) {
            for (int j=0; j<rows; j++)
                world[i][j] = new WorldObjectList();
        }
        random = new Random();
    }

    public int getColumns()
    {
        return columns;
    }

    public int getRows()
    {
        return rows;
    }

    public WorldObjectList getCell(Position p)
    {
        if(!checkPosition(p)) return null;
        else
            return world[p.x][p.y];
    }

    public boolean checkPosition(Position p)
    {
        if ((p.x < 0) || (p.x >= columns) || (p.y < 0) || (p.y >= rows))
            return false;
        else
            return true;
    }

    public boolean setCell(Position p, WorldObject object)
    {
        if (!checkPosition(p))
            return false;
        else {
            world[p.x][p.y].add(object);
            object.world = this;
            return true;
        }
    }

    public boolean checkValidate()
    {
        for(int i=0; i<columns; i++)
            for(int j=0; j<rows; j++)
                if(!world[i][j].isObjectByName(WorldObjectVerifier.FLOOR.getIntValue()))
        return false;
        return true;
    }

    private boolean moveObject(WorldObject object, int direction)
    {
        Position p = new Position(0,0);
        p.x = object.position.x;
        p.y = object.position.y;

        WorldObjectList listFrom;
        WorldObjectList listTo;
        listFrom = world[p.x][p.y];
        Direction.computePosition(p,direction);
        if (!checkPosition(p)) {
            return false;
        }
        listTo = world[p.x][p.y];
        if (!listFrom.remove(object)) {
            System.exit(60);
        }
        object.position.x = p.x;
        object.position.y = p.y;
        listTo.add(object);

        return true;
    }

    public boolean move(final WorldObject worldObject, final int direction, final int maxPower, MutableInt usedPower)
    {
        Position p = new Position(0,0);
        WorldObject object;

        WorldObjectList list;
        if ( maxPower < usedPower.intValue())
            return false;
        p.x = worldObject.position.x;
        p.y = worldObject.position.y;

        Direction.computePosition(p,direction);
        if (!checkPosition(p)){
            return false;
        }

        list = world[p.x][p.y];
        list.setToFirst();
        while((object = list.getNext()) != null ) {
            if(!object.conditionalMovement(worldObject,direction,maxPower,usedPower)) {
                return false;
            }
        }
        if(!moveObject(worldObject,direction)) {
            return false;
        }
        return true;
    }

    public boolean deleteMe(final WorldObject worldObject)
    {
        worldObject.deleteMe = true;
        return true;
    }

    public void clearWorld()
    {
        WorldObject object;
        for (int i=0; i<columns; i++)
            for(int j=0; j<rows; j++) {
                world[i][j].setToFirst();
                while((object = world[i][j].getNext()) != null) {
                    object.moved = false;
                    if (object.deleteMe) {
//                        if(!world[i][j].remove(object))
//                            exit(200);
//                        delete object;
                    }
                }

            }
    }

    public WorldObject getObject(Position p, int className)
    {
        if(!checkPosition(p))
            return null;
        else
            return world[p.x][p.y].getObjectByName(className);
    }

    public void setMoved(WorldObject object)
    {
        object.moved = true;
    }

    public boolean getMoved(WorldObject object)
    {
        return object.moved;
    }
}
