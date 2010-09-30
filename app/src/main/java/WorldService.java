import robot.object.WorldObject;

import java.io.File;
import java.io.FileNotFoundException;

public class WorldService {

	private int columns;
	private int rows;
	private World modelWorld;
	private WorldGL viewWorld;
	private Robot robot;
	private Position robotPosition;
	private int robotDirection;
	private boolean robotView;


    public WorldService(final File file) throws FileNotFoundException {
        DeployWorld deploy = new DeployWorld(file);
        modelWorld = deploy.getWorld();
        robot = null;
        robot = (Robot) deploy.getRobot();
        robotView = false;

        columns = modelWorld.getColumns();
        rows = modelWorld.getRows();

        viewWorld = new WorldGL(columns,rows);
    }


    public void onDraw() {
        Position p = new Position(0,0);
        WorldObjectList list;
        WorldObject object;

        if (!robotView)
            viewWorld.beginScene();
        else  {
            robotPosition = robot.getPosition();
            robotDirection = robot.getDirection();
            viewWorld.beginScene(robotPosition.x, robotPosition.y, robotDirection);
        }
        //viewWorld.renderObject(1,1,robot);
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

    public void onSetXYZRotate(float x, float y, float z )
    {
//        viewWorld.onSetXYZRotate(x,y,z);
    }

    public void onSetScale(float x)
    {
//        viewWorld.onSetScale(x);
    }

    public void onResize(int x, int y)
    {
//        viewWorld.ReSizeGLScene(x,y);
    }

    public void onAntialiasing(boolean state)
    {
//        viewWorld.onAntialiasing(state);
    }

    public void onWireframe(boolean state)
    {
//        viewWorld.onWireframe(state);
    }

    public void onSetXYZMove(float x, float y, float z)
    {
//        viewWorld.onSetXYZMove(x,y,z);
    }

    public void OnRobotview(boolean robotView)
    {
        this.robotView = robotView;
    }

    public void onInit()
    {
//        viewWorld.InitGL();
    }
}