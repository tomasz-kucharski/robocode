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

    protected WorldMap worldMap;
    protected WorldConfiguration worldConfiguration;
    private Robot viewerRobot;

    private Map<MapObject, WorldObjectRenderer> objectRendererMap = new HashMap<MapObject, WorldObjectRenderer>();


    public void setObjectRendererMap(Map<MapObject, WorldObjectRenderer> objectRendererMap) {
        this.objectRendererMap = objectRendererMap;
    }

    public WorldObjectRenderer getWorldObjectRenderer(MapObject mapObject) {
        return objectRendererMap.get(mapObject);
    }


    public void setWorldConfiguration(WorldConfiguration worldConfiguration) {
        this.worldConfiguration = worldConfiguration;
    }

    public void setViewerRobot(Robot viewerRobot) {
        this.viewerRobot = viewerRobot;
    }


    public void setWorldMap(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    public void onDraw() {

        drawBegin();
        drawMap();
        endScene();
    }

    private void drawBegin() {
        if (!worldConfiguration.isRobotView())
            beginScene();
        else  {
            beginScene(viewerRobot);
        }
    }

    private void drawMap() {
        worldMap.performActionOnWorldObjects(new WorldMap.Command() {
            @Override
            public void performActionOnWorldObject(WorldObject object) {
                WorldObjectRenderer worldObjectRenderer = getWorldObjectRenderer(object.getClassName());
                beforeRenderObject(object.getPosition());
                worldObjectRenderer.draw(object);
                afterRenderObject(object.getPosition());
            }
        });
    }

    public abstract void init();

    public abstract void onResize();

    public abstract void beginScene();

    public abstract void beginScene(Robot viewerRobot);

    public abstract void beforeRenderObject(Position position);

    public abstract void afterRenderObject(Position position);    

    public abstract void endScene();

}
