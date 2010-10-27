package robotgame.object.opengl;

import robotgame.loader.TextureLoader;
import robotgame.object.WorldObject;
import robotgame.object.WorldObjectRenderer;
import robotgame.object.robot.Robot;

import javax.media.opengl.GL;

public class RobotGL implements WorldObjectRenderer {

    private GL gl;
    private Robot robot;
    private int list = 30;

    int nogi0;
    int nogi1;
    int nogi2;
    int rece0;
    int rece1;
    int rece2;
    int czulki;
    int robotsprzata;

    @Override
    public void setGraphicsContext(Object context) {
        gl = (GL) context;
    }

    @Override
    public void setWorldObject(WorldObject object) {
        robot = (Robot)object;
    }

    @Override
    public void init() {
        gl.glPushMatrix();
        gl.glNewList(list,GL.GL_COMPILE);
        CubeGL.createCube(gl,0.9f,true);
        gl.glEndList();
        gl.glPopMatrix();
    }

    @Override
    public void draw() {

        float mx = robot.getDirection().getRotation();

        gl.glPushMatrix();
//        if (robotgame.getMoveState() != 0 ) {
//            float move = 1.0f - ((float)robotgame.getMoveState())/5.0f;
//            if (direction == Direction.EAST)
//                gl.glTranslatef(-move,0.0f,0.0f);
//            if (direction == Direction.WEST)
//                gl.glTranslatef(move,0.0f,0.0f);
//            if (direction == Direction.SOUTH)
//                gl.glTranslatef(0.0f,-move,0.0f);
//            if (direction == Direction.NORTH)
//                gl.glTranslatef(0.0f,move,0.0f);
//        }
        gl.glRotatef(mx,0.0f,0.0f,1.0f);

        gl.glBindTexture(GL.GL_TEXTURE_2D, TextureLoader.array[TextureLoader.ROBOT]);
        gl.glTranslatef(0f,0f,0.5f);
        gl.glCallList(list);

        gl.glPopMatrix();
    }
}