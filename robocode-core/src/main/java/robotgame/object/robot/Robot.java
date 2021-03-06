package robotgame.object.robot;

import org.apache.commons.lang.mutable.MutableInt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import robotgame.object.IntelligentObject;
import robotgame.object.WorldObject;
import robotgame.world.Direction;
import robotgame.world.MapObject;
import robotgame.world.Position;

import java.io.IOException;


public class Robot extends IntelligentObject {
    private static final Logger log = LoggerFactory.getLogger(Robot.class);

    private RobotMemory memory;
    private RobotScanner scanner;
    private RobotBattery battery;
    private RobotProcessor processor;

    public int stateMove;

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

    public Robot(Position p,int columns, int rows, String name, Direction direction, int capacity, int scannerRange, ProgramList program) throws IOException {
        super(MapObject.ROBOT,p,false,true,true,name);
        memory = new RobotMemory(this,columns,rows,direction);
        battery = new RobotBattery(capacity);
        scanner = new RobotScanner(this,scannerRange);
        processor = new RobotProcessor(this,program);
    }

    public Direction getDirection() {
        return memory.getDirection();
    }

    public void think() {
        processor.go();
    }

    public boolean conditionalMovement(WorldObject worldObject, Direction direction, int maxPower, MutableInt usedPower) {
        return false;
    }
}