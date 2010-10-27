package robotgame;

import robotgame.object.WorldObject;
import robotgame.object.WorldObjectRenderer;
import robotgame.object.robot.Robot;
import robotgame.world.*;

public class WorldService {

    private World world = new World();
    private Robot viewerRobot;
    private WorldRenderer worldRenderer;
    private WorldConfiguration worldConfiguration;

    public void setViewerRobot(Robot viewerRobot) {
        this.viewerRobot = viewerRobot;
    }

    public void setWorldRenderer(WorldRenderer worldRenderer) {
        this.worldRenderer = worldRenderer;
    }

    public void setWorldConfiguration(WorldConfiguration worldConfiguration) {
        this.worldConfiguration = worldConfiguration;
    }

    public void onResize(int width, int height) {
        worldConfiguration.setScreenWidth(width);
        worldConfiguration.setScreenHeight(height);
        worldRenderer.setWorldConfiguration(worldConfiguration);
        worldRenderer.onResize();
    }

    public void onInit(int width, int height) {
        worldConfiguration.setScreenWidth(width);
        worldConfiguration.setScreenHeight(height);
        worldRenderer.setWorldConfiguration(worldConfiguration);
        worldRenderer.init();
    }

    public void onMapLoad(WorldMap map) {
        world.setMap(map);
    }

    public void onDraw(WorldConfiguration worldConfiguration) {

        drawBegin(worldConfiguration);
        drawMap();
        drawEnd();
        evolveWorld(worldConfiguration);
    }

    private void drawBegin(WorldConfiguration worldConfiguration) {
        if (!worldConfiguration.isRobotView())
            worldRenderer.beginScene();
        else  {
            worldRenderer.beginScene(viewerRobot);
        }
    }

    private void drawMap() {
        world.getMap().performActionOnWorldObjects(new WorldMap.Command() {
            @Override
            public void performActionOnWorldObject(WorldObject object) {
                WorldObjectRenderer worldObjectRenderer = worldRenderer.getWorldObjectRenderer(object.getClassName());
                worldRenderer.beforeRenderObject(object.getPosition());
                worldObjectRenderer.draw(object);
                worldRenderer.afterRenderObject(object.getPosition());
            }
        });
    }

    private void drawEnd() {
        worldRenderer.endScene();
    }

    private void evolveWorld(WorldConfiguration worldConfiguration) {
        if (worldConfiguration.isEvolve()) {
            world.evolve();
        }
    }
}