package robot.object.opengl;

import robot.object.WorldObject;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

/**
 * @author tomekk
 * @since 2010-10-20, 17:15:34
 */
public class TriangleGL extends ObjectGL{
    @Override
    public void draw(GL2 gl, WorldObject object) {
        gl.glBegin(GL.GL_TRIANGLES);
        gl.glColor3f(1, 0, 0);
        gl.glVertex3f(0f, 1f, 0);
        gl.glColor3f(0, 1, 0);
        gl.glVertex3f(-1f, -1f, 0);
        gl.glColor3f(0, 0, 1);
        gl.glVertex3f(1f, -1f, 0);
        gl.glEnd();
    }

    @Override
    public void init(GL2 gl) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
