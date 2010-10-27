package robotgame.object.opengl;

import robotgame.loader.TextureLoader;
import robotgame.object.Depot;
import robotgame.object.WorldObject;
import robotgame.object.WorldObjectRenderer;

import javax.media.opengl.GL;

public class DepotGL implements WorldObjectRenderer {

    private GL gl;
    private int list = 60;

    @Override
    public void setGraphicsContext(Object context) {
        gl = (GL) context;
    }

    @Override
    public void init() {
        gl.glPushMatrix();
        gl.glNewList(list,GL.GL_COMPILE);
        CubeGL.createCube(gl,0.8f,true);
        gl.glEndList();
        gl.glPopMatrix();
    }

    @Override
    public void draw(WorldObject object) {
        gl.glPushMatrix();
        gl.glBindTexture(GL.GL_TEXTURE_2D, TextureLoader.array[TextureLoader.START]);
        gl.glTranslatef(0f,0f,0.5f);
        gl.glCallList(list);
        gl.glPopMatrix();

    }
}