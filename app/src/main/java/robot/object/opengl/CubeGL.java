package robot.object.opengl;

import robot.TextureLoader;
import robot.object.WorldObject;

import javax.media.opengl.GL;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

/**
 * @author tomekk
 * @since 2010-10-20, 17:09:38
 */
public class CubeGL extends ObjectGL{

    IntBuffer buffer = IntBuffer.allocate(10);

    @Override
    public void draw(GL gl, WorldObject object) {


//        gl.glBindTexture(GL.GL_TEXTURE_2D, TextureLoader.array[0]);
        createCube(gl,1,true);



    }

    public static void createCube(GL gl, float size, boolean texture) {
        float x = size/2;


        FloatBuffer material02Amb = FloatBuffer.wrap(new float[]{0.992157f,0.945098f,0.0745100f,1.0f});
        FloatBuffer material02Dif = FloatBuffer.wrap(new float[]{1.00000f,0.933333f,0.125490f,1.0f});
        FloatBuffer material02Spc = FloatBuffer.wrap(new float[]{0.992157f,0.945098f,0.0745100f,1.0f});
        FloatBuffer material02Shn = FloatBuffer.wrap(new float[]{128.000f});

        FloatBuffer material03Amb  = FloatBuffer.wrap(new float[]{0.00784300f,0.0705880f,0.650980f,1.0f});
        FloatBuffer material03Dif  = FloatBuffer.wrap(new float[]{0.0117650f,0.109804f,0.560784f,1.0f});
        FloatBuffer material03Spc  = FloatBuffer.wrap(new float[]{0.00784300f,0.0705880f,0.650980f,1.0f});
        FloatBuffer material03Shn  = FloatBuffer.wrap(new float[]{128.000f});

        FloatBuffer material04Amb = FloatBuffer.wrap(new float[]{0.0196080f,0.0352940f,0.552941f,1.0f});
        FloatBuffer material04Dif = FloatBuffer.wrap(new float[]{0.470588f,0.384314f,0.874510f,1.0f});
        FloatBuffer material04Spc = FloatBuffer.wrap(new float[]{0.0196080f,0.0352940f,0.552941f,1.0f});
        FloatBuffer material04Shn = FloatBuffer.wrap(new float[]{128.000f});

//        gl.glBegin(GL.GL_TRIANGLES);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT, material03Amb);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_DIFFUSE, material03Dif);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SPECULAR, material03Spc);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS, material03Shn);


        gl.glBegin(GL.GL_QUADS);
        // Front Face
//            gl.glColor3f(1f,0f,0f);
        if (texture) gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-x, -x,  x);	// Bottom Left Of The Texture and Quad
        if (texture) gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( x, -x,  x);	// Bottom Right Of The Texture and Quad
        if (texture) gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( x,  x,  x);	// Top Right Of The Texture and Quad
        if (texture) gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-x,  x,  x);	// Top Left Of The Texture and Quad
        // Back Face
        if (texture) gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-x, -x, -x);	// Bottom Right Of The Texture and Quad
        if (texture) gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-x,  x, -x);	// Top Right Of The Texture and Quad
        if (texture) gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f( x,  x, -x);	// Top Left Of The Texture and Quad
        if (texture) gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f( x, -x, -x);	// Bottom Left Of The Texture and Quad
        // Top Face
        if (texture) gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-x,  x, -x);	// Top Left Of The Texture and Quad
        if (texture) gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-x,  x,  x);	// Bottom Left Of The Texture and Quad
        if (texture) gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( x,  x,  x);	// Bottom Right Of The Texture and Quad
        if (texture) gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( x,  x, -x);	// Top Right Of The Texture and Quad
//        gl.glEnd();
//        gl.glBindTexture(GL.GL_TEXTURE_2D, TextureLoader.array[1]);
//        gl.glBegin(GL.GL_QUADS);
        // Bottom Face
        if (texture) gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-x, -x, -x);	// Top Right Of The Texture and Quad
        if (texture) gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f( x, -x, -x);	// Top Left Of The Texture and Quad
        if (texture) gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f( x, -x,  x);	// Bottom Left Of The Texture and Quad
        if (texture) gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-x, -x,  x);	// Bottom Right Of The Texture and Quad
        // Right face
        if (texture) gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( x, -x, -x);	// Bottom Right Of The Texture and Quad
        if (texture) gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( x,  x, -x);	// Top Right Of The Texture and Quad
        if (texture) gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f( x,  x,  x);	// Top Left Of The Texture and Quad
        if (texture) gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f( x, -x,  x);	// Bottom Left Of The Texture and Quad
        // Left Face
        if (texture) gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-x, -x, -x);	// Bottom Left Of The Texture and Quad
        if (texture) gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-x, -x,  x);	// Bottom Right Of The Texture and Quad
        if (texture) gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-x,  x,  x);	// Top Right Of The Texture and Quad
        if (texture) gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-x,  x, -x);	// Top Left Of The Texture and Quad
        gl.glEnd();
    }

    @Override
    public void init(GL gl) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
