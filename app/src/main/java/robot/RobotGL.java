package robot;

import robot.object.WorldObject;
import robot.object.opengl.ObjectGL;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

public class RobotGL extends ObjectGL {

    Robot robot;
    int nogi0;
    int nogi1;
    int nogi2;
    int rece0;
    int rece1;
    int rece2;
    int czulki;
    int robotsprzata;

    public RobotGL() {
        list = 30;
    }

    public void draw(GL2 gl, WorldObject object) {
        int direction;
        float mx;
        float move;
        robot = (Robot)object;

        direction = robot.getDirection();
        mx = Direction.computeRotation(direction);

        gl.glPushMatrix();
        if (robot.getMoveState() != 0 )
        {
            move = 1.0f - ((float)robot.getMoveState())/5.0f;;
            if (direction == Direction.EAST)
                gl.glTranslatef(-move,0.0f,0.0f);
            if (direction == Direction.WEST)
                gl.glTranslatef(move,0.0f,0.0f);
            if (direction == Direction.SOUTH)
                gl.glTranslatef(0.0f,-move,0.0f);
            if (direction == Direction.NORTH)
                gl.glTranslatef(0.0f,move,0.0f);
        }
        gl.glRotatef(mx,0.0f,0.0f,1.0f);
        gl.glCallList(list);

        gl.glPopMatrix();
    }

    public void init(GL2 gl) {
//	loadGLTextures();
        gl.glNewList(list,GL2.GL_COMPILE);
//        #include "robot"
        gl.glEndList();
    }



}