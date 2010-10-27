package robotgame;

import robotgame.legacy.WorldObjectList;
import robotgame.object.WorldObject;
import robotgame.object.robot.Robot;
import robotgame.loader.DeployWorld;
import robotgame.world.Direction;
import robotgame.world.Position;
import robotgame.world.World;
import robotgame.world.opengl.WorldGL;

import javax.media.opengl.GL;
import java.io.BufferedReader;
import java.io.IOException;

public class WorldService {

    private int columns;
    private int rows;
    private World modelWorld;
    private WorldGL viewWorld;
    private Robot robot;
    private boolean robotView;
    private boolean evolve;




    public void onSetScale(float x)
    {
        viewWorld.onSetScale(x);
    }

    public void onResize(int x, int y)
    {
        viewWorld.ReSizeGLScene(x,y);
    }

    public void onAntialiasing(boolean state)
    {
        viewWorld.setAntialiasing(state);
    }

    public void onWireframe(boolean state)
    {
        viewWorld.setWireframe(state);
    }

    public void onSetXYZMove(float x, float y, float z)
    {
        viewWorld.onSetXYZMove(x,y,z);
    }

    public void onXMove(float x) {
        viewWorld.onXMove(x);
    }

    public void onYMove(float y) {
        viewWorld.onYMove(y);
    }

    public void onZMove(float z) {
        viewWorld.onZMove(z);
    }

    public void onXRotate(float x) {
        viewWorld.onSetXRotate(x);
    }

    public void onYRotate(float x) {
        viewWorld.onSetYRotate(x);
    }

    public void OnRobotview(boolean robotView)
    {
        this.robotView = robotView;
    }

    public void onInit(int width, int height)
    {
        viewWorld.InitGL(width,height);
    }

    public void setEvolve(boolean selected) {
        this.evolve = selected;
    }
}