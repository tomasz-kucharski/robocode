package robotgame.opengl.object;

import robotgame.object.WorldObject;
import robotgame.object.robot.Robot;

public class RobotGL extends DefaultWorldObjectGL {
    public static final String ROBOT = "Robot";

    public RobotGL() {
        super(ROBOT, 0.8f);
    }

    @Override
    public void draw(WorldObject object) {
        Robot robot = (Robot) object;
        float mx = robot.getDirection().getRotation();
        gl.glPushMatrix();
        gl.glRotatef(mx,0.0f,0.0f,1.0f);
        gl.glTranslatef(0f,0f,0.45f);
        cube.draw(gl,textureLoader.getTexture(textureKey));
        gl.glPopMatrix();
    }
}