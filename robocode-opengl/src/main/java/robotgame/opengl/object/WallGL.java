package robotgame.opengl.object;

import robotgame.object.WorldObjectRenderer;

/**
 * @author tomekk
 * @since 2010-11-04, 00:34:48
 */
public class WallGL extends DefaultWorldObjectGL {
    public static final String WALL = "Wall";

    public WallGL() {
        super(WALL, 0.8f);
    }
}
