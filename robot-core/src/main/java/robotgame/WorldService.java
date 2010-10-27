package robotgame;

import robotgame.loader.TextureLoader;
import robotgame.object.WorldObject;
import robotgame.object.WorldObjectRenderer;
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

    public WorldConfiguration getConfiguration() {
        return configuration;
    }

    public void onResize(int width, int height) {
        configuration.setScreenWidth(width);
        configuration.setScreenHeight(height);
        worldRenderer.setWorldConfiguration(configuration);
        worldRenderer.onResize();
    }

    public void onInit(int width, int height) {
        textureLoader.loadTextures();
        configuration.setScreenWidth(width);
        configuration.setScreenHeight(height);
        worldRenderer.setWorldConfiguration(configuration);
        worldRenderer.init();
    }

    public boolean onMapLoad(WorldMap map) {
        world.setMap(map);
        boolean b = world.validateWorld();
        if (!b) {
            world.setMap(null);
        }
        return b;
    }

    public void onDraw() {
        worldRenderer.onDraw();
        evolveWorld();
    }

    private void evolveWorld() {
        if (configuration.isEvolve()) {
            world.evolve();
        }
    }
}