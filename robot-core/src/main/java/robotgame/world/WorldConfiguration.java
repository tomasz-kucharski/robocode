package robotgame.world;

/**
 * @author tomekk
 * @since 2010-10-27, 23:25:59
 */
public class WorldConfiguration {

    private boolean robotView;

    private boolean evolve;

    private boolean wireframe;

    private boolean antialiasing;

    private int screenWidth;
    private int screenHeight;

    private float moveX;
    private float moveY;
    private float moveZ;

    private float rotateX;
    private float rotateY;
    private float rotateZ;


    public boolean isRobotView() {
        return robotView;
    }

    public void setRobotView(boolean robotView) {
        this.robotView = robotView;
    }

    public boolean isEvolve() {
        return evolve;
    }

    public void setEvolve(boolean evolve) {
        this.evolve = evolve;
    }

    public boolean isWireframe() {
        return wireframe;
    }

    public void setWireframe(boolean wireframe) {
        this.wireframe = wireframe;
    }

    public boolean isAntialiasing() {
        return antialiasing;
    }

    public void setAntialiasing(boolean antialiasing) {
        this.antialiasing = antialiasing;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public void changeMoveX(float moveX) {
        this.moveX += moveX;
    }

    public void changeMoveY(float moveY) {
        this.moveY += moveY;
    }

    public void changeMoveZ(float moveZ) {
        this.moveZ += moveZ;
    }

    public void changeRotateX(float rotateX) {
        this.rotateX += rotateX;
    }

    public void changeRotateY(float rotateY) {
        this.rotateY += rotateY;
    }

    public void changeRotateZ(float rotateZ) {
        this.rotateZ += rotateZ;
    }

    public float getMoveX() {
        return moveX;
    }

    public float getMoveY() {
        return moveY;
    }

    public float getMoveZ() {
        return moveZ;
    }

    public float getRotateX() {
        return rotateX;
    }

    public float getRotateY() {
        return rotateY;
    }

    public float getRotateZ() {
        return rotateZ;
    }
}
