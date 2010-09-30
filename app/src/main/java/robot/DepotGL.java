package robot;

import robot.object.Depot;
import robot.object.WorldObject;
import robot.object.opengl.ObjectGL;

import javax.media.opengl.GL;
import java.nio.FloatBuffer;

public class DepotGL extends ObjectGL {

    Depot depot;

    public DepotGL() {
        list = 60;
    }


    public void draw(GL gl, WorldObject object) {
        gl.glCallList(list);
    }

    public void init(GL gl) {
        gl.glNewList(list, GL.GL_COMPILE);
        draw(gl);
        gl.glEndList();
    }

    public void draw(GL gl) {
        gl.glPushMatrix();
        gl.glRotatef(90.0f,1.0f,0.0f,0.0f);
//glScalef(0.1f, 0.1f,0.1f);
        FloatBuffer material02Amb = FloatBuffer.wrap(new float[]{0.992157f,0.945098f,0.0745100f,1.0f});
        FloatBuffer material02Dif = FloatBuffer.wrap(new float[]{1.00000f,0.933333f,0.125490f,1.0f});
        FloatBuffer material02Spc = FloatBuffer.wrap(new float[]{0.992157f,0.945098f,0.0745100f,1.0f});
        FloatBuffer material02Shn = FloatBuffer.wrap(new float[]{128.000f});

        FloatBuffer material03Amb = FloatBuffer.wrap(new float[]{0.00784300f,0.0705880f,0.650980f,1.0f});
        FloatBuffer material03Dif = FloatBuffer.wrap(new float[]{0.0117650f,0.109804f,0.560784f,1.0f});
        FloatBuffer material03Spc = FloatBuffer.wrap(new float[]{0.00784300f,0.0705880f,0.650980f,1.0f});
        FloatBuffer material03Shn = FloatBuffer.wrap(new float[]{128.000f});

        FloatBuffer material04Amb = FloatBuffer.wrap(new float[]{0.0196080f,0.0352940f,0.552941f,1.0f});
        FloatBuffer material04Dif = FloatBuffer.wrap(new float[]{0.470588f,0.384314f,0.874510f,1.0f});
        FloatBuffer material04Spc = FloatBuffer.wrap(new float[]{0.0196080f,0.0352940f,0.552941f,1.0f});
        FloatBuffer material04Shn = FloatBuffer.wrap(new float[]{128.000f});

        gl.glBegin(GL.GL_TRIANGLES);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT, material03Amb);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_DIFFUSE, material03Dif);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SPECULAR, material03Spc);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS, material03Shn);
        gl.glNormal3f( 0.532634f, -0.718714f, -0.446936f );

        //todo needs to load depot file
    }
}