package robot;

import org.apache.commons.lang.mutable.MutableInt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import robot.object.IntelligentObject;
import robot.object.WorldObject;

import java.io.*;


public class Robot extends IntelligentObject {
    private static final Logger log = LoggerFactory.getLogger(Robot.class);

    public RobotMemory  memory;
    public RobotScanner scanner;
    public RobotBattery battery;
    private RobotProcessor processor;

    public int stateMove;
    private int stateProgram;

    public RobotMemory getMemory() {
        return memory;
    }

    public RobotScanner getScanner() {
        return scanner;
    }

    public RobotBattery getBattery() {
        return battery;
    }

    public RobotProcessor getProcessor() {
        return processor;
    }

    public int getMoveState() {
        return stateMove;
    }

    public Robot(Position p,int columns, int rows, String name, int direction, int capacity, int zakres, File fileName) throws IOException {

        super(WorldObjectVerifier.ROBOT.getIntValue(),p,false,true,true,name);
        memory = new RobotMemory(this,columns,rows,direction);
        battery = new RobotBattery(capacity);
        scanner = new RobotScanner(this,memory,zakres);
        processor = new RobotProcessor(this,new BufferedReader(new FileReader(fileName)));

        log.debug("{} : PositionXY:{},{}.\n",new Object[]{name,getPosition().x, getPosition().y});
    }

    public int getDirection()
    {
        return memory.getDirection();
    }

    public void think() {
        if (!processor.go()) {
            stateProgram = 1;
        }
    }

    public boolean conditionalMovement(WorldObject worldObject, int direction, int maxPower, MutableInt usedPower) {
        return false;
    }
}