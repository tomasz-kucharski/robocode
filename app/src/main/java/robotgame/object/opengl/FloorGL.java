package robotgame.object.opengl;

import robotgame.loader.TextureLoader;
import robotgame.object.WorldObject;

import javax.media.opengl.GL;
import java.nio.FloatBuffer;

public class FloorGL extends ObjectGL {

    FloatBuffer dechaAmb = FloatBuffer.wrap(new float[]{1.0f,1.0f,1.0f,1.0f});
    FloatBuffer dechaDif = FloatBuffer.wrap(new float[]{1.0f,1.0f,1.0f,1.0f});
    FloatBuffer dechaSpc = FloatBuffer.wrap(new float[]{1.0f,1.0f,1.0f,1.0f});
    FloatBuffer dechaShn = FloatBuffer.wrap(new float[]{128.000f});

    public FloorGL() {
        list = 50;
    }


    public void draw(GL gl, WorldObject object) {
        gl.glBindTexture(GL.GL_TEXTURE_2D,TextureLoader.array[TextureLoader.GRASS]);
        gl.glCallList(list);
    }

    public void init(GL gl)
    {
        gl.glNewList(list,GL.GL_COMPILE);

        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT, dechaAmb);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_DIFFUSE, dechaDif);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SPECULAR, dechaSpc);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS, dechaShn);

        gl.glBegin(GL.GL_QUADS);
        gl.glNormal3f(0.0f,0.0f,1.0f);
        gl.glTexCoord2f(1.0f,0.0f); 	gl.glVertex3f(0.48f,-0.48f,0.0f);
        gl.glTexCoord2f(1.0f,1.0f); 	gl.glVertex3f(0.48f,0.48f,0.0f);
        gl.glTexCoord2f(0.0f,1.0f); 	gl.glVertex3f(-0.48f,0.48f,0.0f);
        gl.glTexCoord2f(0.0f,0.0f);	    gl.glVertex3f(-0.48f,-0.48f,0.0f);
        gl.glEnd();

        gl.glEndList();
    }

}