package robotgame.world;

import robotgame.object.WorldObject;
import robotgame.world.MapObject;

import java.util.*;

public class WorldObjectList {

    private WorldObject[] height = new WorldObject[MapObject.getMaxLevel()+1];

    public boolean isObjectByName(MapObject mapObject) {
        WorldObject worldObject = height[mapObject.getLevel()];
        return worldObject != null && worldObject.getClassName() == mapObject;
    }

    public WorldObject getObjectByName(MapObject mapObject) {
        return height[mapObject.getLevel()];
    }

    public void add(WorldObject object) {
        height[object.getClassName().getLevel()] = object;
    }

    public boolean remove(WorldObject object) {
        if (height[object.getClassName().getLevel()] != null) {
            height[object.getClassName().getLevel()] = null;
            return true;
        } else {
            return false;
        }
    }

    public WorldObject[] getStack() {
        return height;
    }
}
