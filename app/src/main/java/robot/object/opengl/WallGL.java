package robot.object.opengl;

import robot.TextureLoader;
import robot.object.WorldObject;
import robot.object.opengl.ObjectGL;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

public class WallGL extends ObjectGL {
    public WallGL()
    {
        list = 70;
    }

    public void draw(GL2 gl, WorldObject object) {
        gl.glPushMatrix();
        gl.glBindTexture(GL.GL_TEXTURE_2D, TextureLoader.array[TextureLoader.BOX]);
        gl.glTranslatef(0f,0f,0.5f);
        gl.glCallList(list);
        gl.glPopMatrix();

    }

    public void init(GL2 gl) {
        gl.glPushMatrix();
        gl.glNewList(list,GL2.GL_COMPILE);
        CubeGL.createCube(gl,0.8f);
        gl.glEndList();
        gl.glPopMatrix();
    }
}