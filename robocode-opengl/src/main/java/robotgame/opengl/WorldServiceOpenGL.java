package robotgame.opengl;

import robotgame.WorldService;
import robotgame.loader.TextureLoader;
import robotgame.object.WorldObjectRenderer;
import robotgame.opengl.object.*;
import robotgame.world.MapObject;
import robotgame.world.WorldRenderer;

import java.util.HashMap;

/**
 * @author tomekk
 * @since 2010-11-04, 00:28:08
 */
public class WorldServiceOpenGL extends WorldService {

    public WorldServiceOpenGL(WorldRenderer worldRenderer) {

        HashMap<MapObject, WorldObjectRenderer> rendererHashMap = new HashMap<MapObject, WorldObjectRenderer>();
        rendererHashMap.put(MapObject.DEPOT,new DepotGL());
        rendererHashMap.put(MapObject.FURNITURE,new FurnitureGL());
        rendererHashMap.put(MapObject.RUBBISH,new RubbishGL());
        rendererHashMap.put(MapObject.ROBOT,new RobotGL());
        rendererHashMap.put(MapObject.FLOOR,new FloorGL());
        rendererHashMap.put(MapObject.WALL,new WallGL());

        worldRenderer.setObjectRendererMap(rendererHashMap);
        setWorldRenderer(worldRenderer);
    }

    public WorldServiceOpenGL() {
        this(new WorldGL());
    }
}
