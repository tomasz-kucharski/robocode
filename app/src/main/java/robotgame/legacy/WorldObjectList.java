package robotgame.legacy;

import robotgame.legacy.List;
import robotgame.object.WorldObject;
import robotgame.world.MapObject;

import java.util.*;

public class WorldObjectList  {
    private Stack<WorldObject> stack = new Stack<WorldObject>();
    private Map<MapObject,WorldObject> objectTypes = new HashMap<MapObject,WorldObject>();

    public boolean isObjectByName(MapObject mapObject) {
        return objectTypes.containsKey(mapObject);
    }

    public WorldObject getObjectByName(MapObject mapObject) {
        return objectTypes.get(mapObject);
    }

    public void add(WorldObject object) {
        stack.push(object);
        objectTypes.put(object.getClassName(),object);
    }

    public boolean remove(WorldObject object) {
        objectTypes.remove(object.getClassName());
        return stack.remove(object);
    }
}
