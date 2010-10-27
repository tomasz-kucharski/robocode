package robotgame.world;

import robotgame.legacy.WorldObjectList;
import robotgame.object.WorldObject;

/**
 * @author tomekk
 * @since 2010-10-27, 23:09:13
 */
public class WorldMap {

    private WorldObjectList world[][];
    private int columns;
    private int rows;

    public WorldMap(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;

        world = new WorldObjectList[columns][rows];
        for (int i=0; i<columns; i++) {
            for (int j=0; j<rows; j++)
                world[i][j] = new WorldObjectList();
        }
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

    public WorldObjectList getCell(Position p) {
        if(!checkPosition(p)) {
            return null;
        } else {
            return world[p.x][p.y];
        }
    }

    public boolean checkPosition(Position p) {
        return !((p.x < 0) || (p.x >= columns) || (p.y < 0) || (p.y >= rows));
    }

    public boolean setCell(Position p, WorldObject object) {
        if (!checkPosition(p)) {
            return false;
        }
        else {
            world[p.x][p.y].add(object);
            object.position = p;
            return true;
        }
    }

    public void performActionOnWorldObjects(Command command) {
        Position position = new Position(0, 0);
        for(position.x=0; position.x<getColumns(); position.x++) {
            for(position.y=0; position.y<getRows(); position.y++) {
                for (WorldObject object : getCell(position)) {
                    command.performActionOnWorldObject(object);
                }
            }
        }

    }

    public static interface Command {
        void performActionOnWorldObject(WorldObject object);
    }

}
