package robotgame.object;

import robotgame.loader.TextureLoader;

/**
 * @author tomekk
 * @since 2010-10-27, 21:41:44
 */
public interface WorldObjectRenderer {

    void setTextureLoader(TextureLoader textureLoader);

    void setGraphicsContext(Object context);

    void init();

    void draw(WorldObject object);
}
