package robotgame.world;

import robotgame.object.WorldObject;
import robotgame.object.WorldObjectRenderer;
import robotgame.object.robot.Robot;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tomekk
 * @since 2010-10-27, 23:31:28
 */
public class WorldRenderer {

    private Map<MapObject, WorldObjectRenderer> objectRendererMap = new HashMap<MapObject, WorldObjectRenderer>();

    public void setObjectRendererMap(Map<MapObject, WorldObjectRenderer> objectRendererMap) {
        this.objectRendererMap = objectRendererMap;
    }

    public WorldObjectRenderer getWorldObjectRenderer(MapObject mapObject) {
        return objectRendererMap.get(mapObject);
    }

    public void init() {

    }

    public void onResize() {
        
    }

    public void beginScene() {
        //To change body of created methods use File | Settings | File Templates.
    }

    public void beginScene(Robot viewerRobot) {
        //To change body of created methods use File | Settings | File Templates.
    }

    public void endScene() {
        //To change body of created methods use File | Settings | File Templates.
    }
}
