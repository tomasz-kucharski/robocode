package robotgame;

import robotgame.loader.TextureLoader;
import robotgame.object.robot.Robot;
import robotgame.world.*;

public class WorldService {

    private World world = new World();
    private WorldRenderer worldRenderer;
    private WorldConfiguration configuration = new WorldConfiguration();
    private TextureLoader textureLoader;

    public void setWorldRenderer(WorldRenderer worldRenderer) {
        this.worldRenderer = worldRenderer;
    }

    public void setGraphicsContext(Object graphicsContext) {
        worldRenderer.setGraphicsContext(graphicsContext);
    }

    public void setMainRobot(Robot robot) {
        worldRenderer.setViewerRobot(robot);
    }

    public void setTextureLoader(TextureLoader textureLoader) {
        this.textureLoader = textureLoader;
    }

    public WorldConfiguration getConfiguration() {
        return configuration;
    }

    public void onResize(int width, int height) {
        configuration.setScreenWidth(width);
        configuration.setScreenHeight(height);
        worldRenderer.setWorldConfiguration(configuration);
        worldRenderer.resize();
    }

    public void onInit(int width, int height) {
        configuration.setScreenWidth(width);
        configuration.setScreenHeight(height);

        textureLoader.setGraphicsContext(worldRenderer.getGraphicsContext());
        textureLoader.loadTextures();

        worldRenderer.setWorldConfiguration(configuration);
        worldRenderer.onInit();
    }

    public boolean onMapLoad(WorldMap map) {
        boolean b = world.validateMap(map);
        if (b) {
            worldRenderer.setWorldMap(map);
            world.setMap(map);
        }
        return b;
    }

    public void onDraw() {
        worldRenderer.onDraw();
        if (configuration.isEvolve()) {
            world.evolve();
        }
    }
}