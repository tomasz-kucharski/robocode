package robot;

import robot.object.WorldObject;
import robot.object.opengl.ObjectGL;

import javax.media.opengl.GL;

public class RubbishGL extends ObjectGL {

    Rubbish rubbish;
    public RubbishGL() {
        list = 20;
    }

    public void draw(GL gl, WorldObject object)
    {
        float mx;
        rubbish = (Rubbish)object;
        gl.glPushMatrix();
        mx = Direction.computeRotation(rubbish.getDirection());
        gl.glRotatef(mx,0.0f,0.0f,1.0f);
        gl.glCallList(list);
        gl.glPopMatrix();
    }

    public void init(GL gl)
    {
        gl.glNewList(list, GL.GL_COMPILE);
//#include "rubbish"
        gl.glEndList();
    }

}