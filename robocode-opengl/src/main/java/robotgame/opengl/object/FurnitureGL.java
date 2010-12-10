package robotgame.opengl.object;

import robotgame.object.WorldObjectRenderer;

/**
 * @author tomekk
 * @since 2010-11-04, 00:37:21
 */
public class FurnitureGL extends DefaultWorldObjectGL {
    public static final String FURNITURE = "Furniture";

    public FurnitureGL() {
        super(FURNITURE, 0.8f);
    }
}
