package robotgame.object;

import robotgame.object.WorldObject;

import javax.media.opengl.GL;
import java.io.File;
import java.nio.IntBuffer;

public abstract class ObjectGL {

    protected int list;

    public abstract void draw(GL gl, WorldObject object);

    public abstract void init(GL gl);

}