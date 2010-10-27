package robotgame.object.opengl;

import robotgame.loader.TextureLoader;
import robotgame.object.WorldObject;
import robotgame.object.WorldObjectRenderer;
import robotgame.object.robot.Robot;

import javax.media.opengl.GL;
import java.io.File;

public class TableGL implements WorldObjectRenderer {

    private float x;
    private float y;
    private GL gl;
    private int list = 20;

    @Override
    public void setGraphicsContext(Object context) {
        gl = (GL) context;
    }

    public TableGL(int columns, int rows) {
        x = 0.5f*(float)columns+0.5f;
        y = 0.5f*(float)rows+0.5f;
        list = 10;
    }

    @Override
    public void draw(WorldObject object) {
        gl.glBindTexture(GL.GL_TEXTURE_2D, TextureLoader.array[TextureLoader.TABLE]);
        gl.glCallList(list);
    }

    @Override
    public void init() {
        gl.glNewList(list,GL.GL_COMPILE);

        gl.glColor3f(1.0f,.7f,1.0f);
        gl.glBegin(GL.GL_QUADS);
        gl.glNormal3f(0.0f,0.0f,1.0f);
        gl.glTexCoord2f(1.0f,1.0f);gl.glVertex3f(x,y,0.0f);
        gl.glTexCoord2f(0.0f,1.0f);gl.glVertex3f(-x,y,0.0f);
        gl.glTexCoord2f(0.0f,0.0f);gl.glVertex3f(-x,-y,0.0f);
        gl.glTexCoord2f(1.0f,0.0f);gl.glVertex3f(x,-y,0.0f);
        gl.glEnd();

        gl.glEndList();
    }

}