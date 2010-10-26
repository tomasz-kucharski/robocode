package robotgame.object.opengl;

import robotgame.loader.TextureLoader;
import robotgame.object.WorldObject;

import javax.media.opengl.GL;

/**
 * @author tomekk
 * @since 2010-10-20, 17:15:34
 */
public class TriangleGL extends ObjectGL{

    private int anInt;

    public TriangleGL(int anInt) {
        this.anInt = anInt;
    }

    @Override
    public void draw(GL gl, WorldObject object) {

        gl.glBindTexture(GL.GL_TEXTURE_2D, TextureLoader.array[anInt]);
        gl.glBegin(GL.GL_TRIANGLES);
                gl.glColor3f(1.0f,0.0f,0.0f);			// Red
                gl.glVertex3f( 0.0f, 1.0f, 0.0f);			// Top Of Triangle (Front)
                gl.glColor3f(0.0f,1.0f,0.0f);			// Green
                gl.glVertex3f(-1.0f,-1.0f, 1.0f);			// Left Of Triangle (Front)
                gl.glColor3f(0.0f,0.0f,1.0f);			// Blue
                gl.glVertex3f( 1.0f,-1.0f, 1.0f);			// Right Of Triangle (Front)
                gl.glColor3f(1.0f,0.0f,0.0f);			// Red
                gl.glVertex3f( 0.0f, 1.0f, 0.0f);			// Top Of Triangle (Right)
                gl.glColor3f(0.0f,0.0f,1.0f);			// Blue
                gl.glVertex3f( 1.0f,-1.0f, 1.0f);			// Left Of Triangle (Right)
                gl.glColor3f(0.0f,1.0f,0.0f);			// Green
                gl.glVertex3f( 1.0f,-1.0f, -1.0f);			// Right Of Triangle (Right)
                gl.glColor3f(1.0f,0.0f,0.0f);			// Red
                gl.glVertex3f( 0.0f, 1.0f, 0.0f);			// Top Of Triangle (Back)
                gl.glColor3f(0.0f,1.0f,0.0f);			// Green
                gl.glVertex3f( 1.0f,-1.0f, -1.0f);			// Left Of Triangle (Back)
                gl.glColor3f(0.0f,0.0f,1.0f);			// Blue
                gl.glVertex3f(-1.0f,-1.0f, -1.0f);			// Right Of Triangle (Back)
                gl.glColor3f(1.0f,0.0f,0.0f);			// Red
                gl.glVertex3f( 0.0f, 1.0f, 0.0f);			// Top Of Triangle (Left)
                gl.glColor3f(0.0f,0.0f,1.0f);			// Blue
                gl.glVertex3f(-1.0f,-1.0f,-1.0f);			// Left Of Triangle (Left)
                gl.glColor3f(0.0f,1.0f,0.0f);			// Green
                gl.glVertex3f(-1.0f,-1.0f, 1.0f);			// Right Of Triangle (Left)
            gl.glEnd();						// Done Drawing The Pyramid

        
    }

    @Override
    public void init(GL gl) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
