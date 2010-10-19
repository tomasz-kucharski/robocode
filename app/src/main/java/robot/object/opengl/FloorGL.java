package robot.object.opengl;

import robot.object.WorldObject;
import robot.object.opengl.ObjectGL;

import javax.media.opengl.GL2;
import java.io.File;
import java.nio.FloatBuffer;

public class FloorGL extends ObjectGL {

    public FloorGL() {
        list = 50;
    }


    public void draw(GL2 gl, WorldObject object) {
        gl.glCallList(list);
    }

    public void init(GL2 gl)
    {
        loadGLTextures(gl,new File("floor.bmp"));
        gl.glNewList(list,GL2.GL_COMPILE);


        FloatBuffer dechaAmb = FloatBuffer.wrap(new float[]{1.0f,1.0f,1.0f,1.0f});
        FloatBuffer dechaDif = FloatBuffer.wrap(new float[]{1.0f,1.0f,1.0f,1.0f});
        FloatBuffer dechaSpc = FloatBuffer.wrap(new float[]{1.0f,1.0f,1.0f,1.0f});
        FloatBuffer dechaShn = FloatBuffer.wrap(new float[]{128.000f});

        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT, dechaAmb);
        gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_DIFFUSE, dechaDif);
        gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, dechaSpc);
        gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SHININESS, dechaShn);

//	glBindTexture(GL2.GL_TEXTURE_2D,texture[0]);

        gl.glBegin(GL2.GL_QUADS);
        gl.glNormal3f(0.0f,0.0f,1.0f);
        gl.glTexCoord2f(0.0f,0.0f);	gl.glVertex3f(-0.48f,-0.48f,0.0f);
        gl.glTexCoord2f(0.0f,1.0f); 	gl.glVertex3f(-0.48f,0.48f,0.0f);
        gl.glTexCoord2f(1.0f,1.0f); 	gl.glVertex3f(0.48f,0.48f,0.0f);
        gl.glTexCoord2f(1.0f,0.0f); 	gl.glVertex3f(0.48f,-0.48f,0.0f);
        gl.glEnd();

        gl.glEndList();                                 
    }

}