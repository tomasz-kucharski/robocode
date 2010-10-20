package robot.object.opengl;

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


        gl.glBindTexture(GL.GL_TEXTURE_2D, 1);
        gl.glBegin(GL2.GL_QUADS);
            // Front Face
            gl.glColor3f(1f,0f,0f);
            gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-1.0f, -1.0f,  1.0f);	// Bottom Left Of The Texture and Quad
            gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 1.0f, -1.0f,  1.0f);	// Bottom Right Of The Texture and Quad
            gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 1.0f,  1.0f,  1.0f);	// Top Right Of The Texture and Quad
            gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-1.0f,  1.0f,  1.0f);	// Top Left Of The Texture and Quad
            // Back Face
            gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-1.0f, -1.0f, -1.0f);	// Bottom Right Of The Texture and Quad
            gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-1.0f,  1.0f, -1.0f);	// Top Right Of The Texture and Quad
            gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f( 1.0f,  1.0f, -1.0f);	// Top Left Of The Texture and Quad
            gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f( 1.0f, -1.0f, -1.0f);	// Bottom Left Of The Texture and Quad
            gl.glColor3f(1f,0f,1f);
            // Top Face
            gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-1.0f,  1.0f, -1.0f);	// Top Left Of The Texture and Quad
            gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-1.0f,  1.0f,  1.0f);	// Bottom Left Of The Texture and Quad
            gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 1.0f,  1.0f,  1.0f);	// Bottom Right Of The Texture and Quad
            gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 1.0f,  1.0f, -1.0f);	// Top Right Of The Texture and Quad
            // Bottom Face
            gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-1.0f, -1.0f, -1.0f);	// Top Right Of The Texture and Quad
            gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f( 1.0f, -1.0f, -1.0f);	// Top Left Of The Texture and Quad
            gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f( 1.0f, -1.0f,  1.0f);	// Bottom Left Of The Texture and Quad
            gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-1.0f, -1.0f,  1.0f);	// Bottom Right Of The Texture and Quad
            // Right face
            gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 1.0f, -1.0f, -1.0f);	// Bottom Right Of The Texture and Quad
            gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 1.0f,  1.0f, -1.0f);	// Top Right Of The Texture and Quad
            gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f( 1.0f,  1.0f,  1.0f);	// Top Left Of The Texture and Quad
            gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f( 1.0f, -1.0f,  1.0f);	// Bottom Left Of The Texture and Quad
            // Left Face
            gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-1.0f, -1.0f, -1.0f);	// Bottom Left Of The Texture and Quad
            gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-1.0f, -1.0f,  1.0f);	// Bottom Right Of The Texture and Quad
            gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-1.0f,  1.0f,  1.0f);	// Top Right Of The Texture and Quad
            gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-1.0f,  1.0f, -1.0f);	// Top Left Of The Texture and Quad
        gl.glEnd();



    }

    @Override
    public void init(GL2 gl) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
