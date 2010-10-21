package robot.object.opengl;

import robot.object.WorldObject;
import robot.object.opengl.ObjectGL;

import javax.media.opengl.GL;
import javax.media.opengl.GL;
import java.io.File;

public class TableGL extends ObjectGL {

    private float x;
    private float y;

    public TableGL(int columns, int rows) {
        x = 0.5f*(float)columns+0.5f;
        y = 0.5f*(float)rows+0.5f;
        list = 10;
    }


    public void draw(GL gl, WorldObject object) {
        gl.glCallList(list);
    }

    public void init(GL gl) {
        loadGLTextures(gl, new File("table.bmp"));
        gl.glNewList(list,GL.GL_COMPILE);

        gl.glColor3f(1.0f,.7f,1.0f);
//	glBindTexture(GL.GL_TEXTURE_2D,texture[0]);
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