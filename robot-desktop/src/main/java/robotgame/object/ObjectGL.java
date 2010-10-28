package robotgame.object;

import javax.media.opengl.GL;

public abstract class ObjectGL {

    protected int list;

    public abstract void draw(GL gl, WorldObject object);

    public abstract void init(GL gl);

}