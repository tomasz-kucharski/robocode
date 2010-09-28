

public class WorldService {

	private int columns;
	private int rows;
	private World modelWorld;
	private WorldGL viewWorld;
	private Robot robot;
	private Position robotPosition;
	private int robotDirection;
	private boolean robotView;


    public WorldService(const char* file) {
        DeployWorld deploy = new DeployWorld(file);
        modelWorld = deploy->getWorld();
        robot = NULL;
        robot = (Robot*) deploy->getRobot();
        robotView = false;

        delete deploy;
        columns = modelWorld->getColumns();
        rows = modelWorld->getRows();

        viewWorld = new WorldGL(columns,rows);
    }


    public void onDraw() {
        Position p(0,0);
        WorldObjectList* list;
        WorldObject* object;

        if (!robotView)
            viewWorld->beginScene();
        else  {
            robotPosition = robot->getPosition();
            robotDirection = robot->getDirection();
            viewWorld->beginScene(robotPosition.x, robotPosition.y, robotDirection);
        }
        //viewWorld->renderObject(1,1,robot);
        for(p.x=0; p.x<columns; p.x++)
            for(p.y=0; p.y<rows; p.y++) {
                list = modelWorld->getCell(&p);
                list->setToFirst();
                while(object = list->getNext()) {
                    if(!viewWorld->renderObject(p.x,p.y,object))
                        exit(50);
                }
            }
        viewWorld->endScene();
    }

    public void evolve() {
        Position p(0,0);
        WorldObjectList* list;
        WorldObject* object;

        for(p.x=0; p.x<columns; p.x++)
            for(p.y=0; p.y<rows; p.y++) {
                list = modelWorld->getCell(&p);
                list->setToFirst();
                while(object = list->getNext()) {
                    if(!modelWorld->getMoved(object)) {
                        object->evolve();
                        modelWorld->setMoved(object);
                    }
                }
            }
        modelWorld->clearWorld();
    }

    public void onSetXYZRotate(float x, float y, float z )
    {
        viewWorld->onSetXYZRotate(x,y,z);
    }

    public void onSetScale(float x)
    {
        viewWorld->onSetScale(x);
    }

    public void onResize(GLsizei x, GLsizei y)
    {
        viewWorld->ReSizeGLScene(x,y);
    }

    public void onAntialiasing(BOOL state)
    {
        viewWorld->onAntialiasing(state);
    }

    public void onWireframe(BOOL state)
    {
        viewWorld->onWireframe(state);
    }

    public void onSetXYZMove(float x, float y, float z)
    {
        viewWorld->onSetXYZMove(x,y,z);
    }

    public void OnRobotview(bool robotView)
    {
        this->robotView = robotView;
    }

    public void onInit()
    {
        viewWorld->InitGL();
    }
}