package robotgame.object.robot;

import robotgame.world.MapObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tomekk
 * @since 2010-10-25, 22:26:51
 */
public enum RobotMemoryObject {
    UNKNOWN,
    EMPTY,
    RUBBISH,
    MOVABLE,
    UNMOVABLE,
    VISITED,
    DEPOT,
    ROBOT,
    END;

    private static Map<MapObject,RobotMemoryObject> map = new HashMap<MapObject, RobotMemoryObject>();
    static {
        map.put(MapObject.DEPOT,DEPOT);
        map.put(MapObject.FURNITURE,MOVABLE);
        map.put(MapObject.RUBBISH,RUBBISH);
        map.put(MapObject.WALL,UNMOVABLE);
        map.put(MapObject.ROBOT,ROBOT);
    }


    public static RobotMemoryObject getRobotMemoryObject(MapObject object) {
        RobotMemoryObject object1 = map.get(object);
        if (object1 == null) {
            object1 = EMPTY;
        }
        return object1;
    }

}
