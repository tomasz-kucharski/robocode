import org.apache.commons.lang.mutable.MutableInt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import robot.object.WorldObject;

import java.io.File;


public class Robot extends IntelligentObject {
    private static final Logger log = LoggerFactory.getLogger(Robot.class);

    public static int MOVESTATE = 5;
    public static int CLEANSTATE = 3;
    public static int SCANERSTATE = 4;
    public static int TURNSTATE = 2;


    public RobotMemory  memory;
    public RobotScanner  scaner;
    public RobotBattery battery;
    private RobotProcessor processor;

    public int stateMove;
    private int stateClean;
    public int stateScaner;
    private int stateTurn;
    private int stateProgram;

//    friend class RobotProcessor;


    public Robot(Position p,int columns, int rows, String name, int direction, int capacity, int zakres, File fileName) {

        super(WorldObjectVerifier.ROBOT.getIntValue(),p,false,true,true,name);
        memory = new RobotMemory(this,columns,rows,direction);
        battery = new RobotBattery(capacity);
        scaner = new RobotScanner(this,memory,zakres);
        processor = new RobotProcessor(this,fileName);

        stateMove = 0;
        stateClean = 0;
        stateScaner = 0;
        stateTurn = 0;
        stateProgram = 0;

        log.debug("{} : PositionXY:{},{}.\n",new Object[]{name,getPosition().x, getPosition().y});
    }

    public int getDirection()
    {
        return memory.getDirection();
    }

    public int getCleanState()
    {
        return stateClean;
    }

    public int getScanerState()
    {
        return stateScaner;
    }

    public int getMoveState()
    {
        return stateMove;
    }

    public int getTurnState()
    {
        return stateTurn;
    }

    public int getStateProgram()
    {
        return stateProgram;
    }

    public void think()
    {
        if (stateProgram == 0)
            if (!processor.go())
                stateProgram = 1;
    }

    public boolean conditionalMovement(WorldObject worldObject, int direction, int maxPower, MutableInt usedPower) {
        return false; 
    }
}