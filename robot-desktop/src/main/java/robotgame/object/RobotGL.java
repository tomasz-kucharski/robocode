package robotgame.object;

import robotgame.loader.TextureLoader;
import robotgame.object.robot.Robot;

import javax.media.opengl.GL;

public class RobotGL implements WorldObjectRenderer {

    private GL gl;
    private int list = 30;

    int nogi0;
    int nogi1;
    int nogi2;
    int rece0;
    int rece1;
    int rece2;
    int czulki;
    int robotsprzata;
    private TextureLoader textureLoader;
    private static final String ROBOT = "robot";
    private CubeGL cube;

    @Override
    public void setTextureLoader(TextureLoader textureLoader) {
        this.textureLoader = textureLoader;
    }

    @Override
    public void setGraphicsContext(Object context) {
        gl = (GL) context;
    }

    @Override
    public void init() {
        textureLoader.initTexture(ROBOT);
        gl.glPushMatrix();
//        gl.glNewList(list,GL.GL_COMPILE);
        cube = new CubeGL(0.9f);
//        gl.glEndList();
        gl.glPopMatrix();
    }

    @Override
    public void draw(WorldObject object) {
        Robot robot = (Robot) object;

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
        gl.glTranslatef(0f,0f,0.45f);

//        gl.glBindTexture(GL.GL_TEXTURE_2D, textureLoader.getTexture(ROBOT));
        cube.draw(gl,textureLoader.getTexture(ROBOT));
//        gl.glCallList(list);

        gl.glPopMatrix();
    }
}