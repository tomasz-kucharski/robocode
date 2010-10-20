package robot.object.opengl;

import robot.TextureLoader;
import robot.object.WorldObject;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import java.nio.IntBuffer;

/**
 * @author tomekk
 * @since 2010-10-20, 17:09:38
 */
public class CubeGL extends ObjectGL{

    IntBuffer buffer = IntBuffer.allocate(10);

    @Override
    public void draw(GL2 gl, WorldObject object) {


//        gl.glBindTexture(GL.GL_TEXTURE_2D, TextureLoader.array[0]);
        createCube(gl,1);



    }

    public static void createCube(GL2 gl, float size) {
        float x = size/2;
        gl.glBegin(GL2.GL_QUADS);
        // Front Face
//            gl.glColor3f(1f,0f,0f);
        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-x, -x,  x);	// Bottom Left Of The Texture and Quad
        gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( x, -x,  x);	// Bottom Right Of The Texture and Quad
        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( x,  x,  x);	// Top Right Of The Texture and Quad
        gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-x,  x,  x);	// Top Left Of The Texture and Quad
        // Back Face
        gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-x, -x, -x);	// Bottom Right Of The Texture and Quad
        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-x,  x, -x);	// Top Right Of The Texture and Quad
        gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f( x,  x, -x);	// Top Left Of The Texture and Quad
        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f( x, -x, -x);	// Bottom Left Of The Texture and Quad
        gl.glColor3f(1f,0f,1f);
        // Top Face
        gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-x,  x, -x);	// Top Left Of The Texture and Quad
        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-x,  x,  x);	// Bottom Left Of The Texture and Quad
        gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( x,  x,  x);	// Bottom Right Of The Texture and Quad
        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( x,  x, -x);	// Top Right Of The Texture and Quad
//        gl.glEnd();
//        gl.glBindTexture(GL.GL_TEXTURE_2D, TextureLoader.array[1]);
//        gl.glBegin(GL2.GL_QUADS);
        // Bottom Face
        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-x, -x, -x);	// Top Right Of The Texture and Quad
        gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f( x, -x, -x);	// Top Left Of The Texture and Quad
        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f( x, -x,  x);	// Bottom Left Of The Texture and Quad
        gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-x, -x,  x);	// Bottom Right Of The Texture and Quad
        // Right face
        gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( x, -x, -x);	// Bottom Right Of The Texture and Quad
        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( x,  x, -x);	// Top Right Of The Texture and Quad
        gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f( x,  x,  x);	// Top Left Of The Texture and Quad
        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f( x, -x,  x);	// Bottom Left Of The Texture and Quad
        // Left Face
        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-x, -x, -x);	// Bottom Left Of The Texture and Quad
        gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-x, -x,  x);	// Bottom Right Of The Texture and Quad
        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-x,  x,  x);	// Top Right Of The Texture and Quad
        gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-x,  x, -x);	// Top Left Of The Texture and Quad
        gl.glEnd();
    }

    @Override
    public void init(GL2 gl) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
