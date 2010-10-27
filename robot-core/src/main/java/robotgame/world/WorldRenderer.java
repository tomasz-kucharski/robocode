package robotgame.world;

import robotgame.loader.TextureLoader;
import robotgame.object.WorldObject;
import robotgame.object.WorldObjectRenderer;
import robotgame.object.robot.Robot;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tomekk
 * @since 2010-10-27, 23:31:28
 */
public abstract class WorldRenderer {

    private Map<MapObject, WorldObjectRenderer> objectRendererMap = new HashMap<MapObject, WorldObjectRenderer>();

    public void setObjectRendererMap(Map<MapObject, WorldObjectRenderer> objectRendererMap) {
        this.objectRendererMap = objectRendererMap;
    }

    public WorldObjectRenderer getWorldObjectRenderer(MapObject mapObject) {
        return objectRendererMap.get(mapObject);
    }

    protected WorldConfiguration worldConfiguration;

    public void setWorldConfiguration(WorldConfiguration worldConfiguration) {
        this.worldConfiguration = worldConfiguration;
    }

    public abstract void init() {
        new TextureLoader().loadTextures(gl);
    }

    public abstract void onResize();

    public abstract void beginScene();

    public abstract void beginScene(Robot viewerRobot);

    public abstract void beforeRenderObject(Position position);

    public abstract void afterRenderObject(Position position);    

    public abstract void endScene();

}
