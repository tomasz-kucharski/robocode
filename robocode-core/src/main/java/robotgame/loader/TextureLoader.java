package robotgame.loader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tomekk
 * @since 2010-10-20, 18:01:51
 */
public abstract class TextureLoader {

    protected List<String> textures = new ArrayList<String>();
    protected int[] array;

    public void initTexture(String textureKey) {
        if (!textures.contains(textureKey)) {
            textures.add(textureKey);
        }
    }

    public void init() {
        array = new int[textures.size()];
        loadTextures();
    }

    public int getTexture(String textureKey) {
        return array[textures.indexOf(textureKey)];
    }

    protected abstract void loadTextures();

    public abstract void setGraphicsContext(Object gl);


}
