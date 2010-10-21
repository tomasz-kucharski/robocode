package robot.object.opengl;

import robot.TextureLoader;
import robot.object.Depot;
import robot.object.WorldObject;
import robot.object.opengl.ObjectGL;

import javax.media.opengl.GL;
import javax.media.opengl.GL;
import java.nio.FloatBuffer;

public class DepotGL extends ObjectGL {

    Depot depot;

    public DepotGL() {
        list = 60;
    }

    public void draw(GL gl, WorldObject object) {
        gl.glPushMatrix();
        gl.glBindTexture(GL.GL_TEXTURE_2D, TextureLoader.array[TextureLoader.START]);
        gl.glTranslatef(0f,0f,0.5f);
        gl.glCallList(list);
        gl.glPopMatrix();

    }

    public void init(GL gl) {
        gl.glPushMatrix();
        gl.glNewList(list,GL.GL_COMPILE);
        CubeGL.createCube(gl,0.8f,true);
        gl.glEndList();
        gl.glPopMatrix();
    }
}