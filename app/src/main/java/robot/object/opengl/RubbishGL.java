package robot.object.opengl;

import robot.Direction;
import robot.object.Rubbish;
import robot.object.WorldObject;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

public class RubbishGL extends ObjectGL {

    Rubbish rubbish;
    public RubbishGL() {
        list = 20;
    }

    public void draw(GL2 gl, WorldObject object)
    {
        float mx;
        rubbish = (Rubbish)object;
        gl.glPushMatrix();
        mx = Direction.computeRotation(rubbish.getDirection());
        gl.glRotatef(mx,0.0f,0.0f,1.0f);
        gl.glCallList(list);
        gl.glPopMatrix();
    }

    public void init(GL2 gl)
    {
        gl.glNewList(list, GL2.GL_COMPILE);
//#include "rubbish"
        gl.glEndList();
    }

}