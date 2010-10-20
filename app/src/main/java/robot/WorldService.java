package robot;

import robot.object.WorldObject;

import javax.media.opengl.GL2;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class WorldService {

	private int columns;
	private int rows;
	private World modelWorld;
	private WorldGL viewWorld;
	private Robot robot;
    private boolean robotView;


    public WorldService(BufferedReader map) throws IOException {
        DeployWorld deploy = new DeployWorld(map);
        modelWorld = deploy.getWorld();
        robot = null;
        robot = (Robot) deploy.getRobot();
        robotView = false;

        columns = modelWorld.getColumns();
        rows = modelWorld.getRows();

        viewWorld = new WorldGL(columns,rows);
    }

    public void setGL(GL2 gl) {
        viewWorld.setGl(gl);
    }

    public void onDraw() {
        Position p = new Position(0,0);
        WorldObjectList list;
        WorldObject object;

//        if (!robotView)
            viewWorld.beginScene();
//        else  {
//            Position robotPosition = robot.getPosition();
//            int robotDirection = robot.getDirection();
//            viewWorld.beginScene(robotPosition.x, robotPosition.y, robotDirection);
//        }
        for(p.x=0; p.x<columns; p.x++)
            for(p.y=0; p.y<rows; p.y++) {
                list = modelWorld.getCell(p);
                list.setToFirst();
                while((object = list.getNext()) != null) {
                    if(!viewWorld.renderObject(p.x,p.y,object))
                        System.exit(50);
                }
            }
        viewWorld.endScene();
    }

    public void evolve() {
        Position p = new Position(0,0);
        WorldObjectList list;
        WorldObject object;

        for(p.x=0; p.x<columns; p.x++)
            for(p.y=0; p.y<rows; p.y++) {
                list = modelWorld.getCell(p);
                list.setToFirst();
                while((object = list.getNext()) != null) {
                    if(!modelWorld.getMoved(object)) {
                        object.evolve();
                        modelWorld.setMoved(object);
                    }
                }
            }
        modelWorld.clearWorld();
    }

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
}