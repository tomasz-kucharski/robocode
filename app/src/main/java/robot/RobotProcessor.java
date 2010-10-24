package robot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import robot.logic.InstructionExecutionException;
import robot.object.Rubbish;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Stack;

public class RobotProcessor {
    private static final Logger log = LoggerFactory.getLogger(RobotProcessor.class);

    public static final int REGISTRYSIZE = 40;

    //STANY OBIEKTOW
    public static final int UNKNOWN = 100;
    public static final int EMPTY = 101;
    public static final int RUBBISH = 102;
    public static final int MOVABLE = 103;
    public static final int UNMOVABLE = 104;
    public static final int VISITED = 105;
    public static final int DEPOT = 106;
    public static final int ROBOT = 107;
    public static final int END = 108;

    private ProgramList program;
    private Instruction instruction;
    private Stack<Integer> methodStack = new Stack<Integer>();
    private Robot r;
    private int[] registry;
    private boolean done;
    private boolean external;

    public RobotProcessor(Robot r, BufferedReader program) throws IOException {
        registry = new int[REGISTRYSIZE];
        for (int i=0; i<REGISTRYSIZE; i++)
            registry[i] = 0;
        this.r = r;
        this.program = new ProgramList();
        RobotProgramLoader programLoader = new RobotProgramLoader(program, this.program);
        programLoader.loadProgram();

        done = true;
        external = false;
    }

    public int[] getRegistry() {
        return registry;
    }

    public RobotMemory getMemory() {
        return r.memory;
    }

    public Position getRobotPosition() {
        return r.position;
    }

    public ProgramList getProgram() {
        return program;
    }

    public static int getMemoryObjectByName(String name)
    {
        if (name != null) {
            if(name.equals("UNKNOWN"))
                return UNKNOWN;
            if(name.equals("EMPTY"))
                return EMPTY;
            if(name.equals("RUBBISH"))
                return RUBBISH;
            if(name.equals("UNMOVABLE"))
                return UNMOVABLE;
            if(name.equals("VISITED"))
                return VISITED;
            if(name.equals("DEPOT"))
                return DEPOT;
            if(name.equals("ROBOT"))
                return ROBOT;
            if(name.equals("END"))
                return END;
        }
        return -1;
    }

    public boolean go() {
        try {
            if(!done)
                performExternal();
            else
            {
                done = false;
                while(!external)
                {
                    instruction = program.next();
                    if ( instruction == null )
                    {
                        exception(program.size(),"ERROR - END OF PROGRAM\n");
                        break;
                    }
                    instruction.process(this);
                }
                performExternal();
            }
            return true;
        } catch (InstructionExecutionException e) {
            log.info("Wrong execution:"+e.getInstruction(),e);
            return false;
        }
    }

    void performExternal()
    {
        external = false;
        switch(instruction.getOrder())
        {
            case SCAN:
                r.stateScaner++;
                r.stateScaner %= Robot.SCANERSTATE;
                if(r.stateScaner == 1)
                    r.scaner.scan();
                if(r.stateScaner == 0)
                    done = true;
                break;
            case TURNLEFT:
                r.memory.turnLeft(r.battery.plug());
                r.battery.unplug();
                done = true;
                break;
            case TURNRIGHT:
                r.memory.turnRight(r.battery.plug());
                r.battery.unplug();
                done = true;
                break;
            case MOVE:
                r.stateMove++;
                r.stateMove %= Robot.MOVESTATE;
                if(r.stateMove == 1)
                {
                    if(!r.getWorld().move(r,r.memory.getDirection(),r.battery.getMaxCapacity(),r.battery.plug()))
                    {
                        log.debug("PRZESZKODA!");
                        r.stateMove = 0;
                        registry[0] = 1;
                    }
                }
                if(r.stateMove == 0 )
                {
                    r.memory.setMemoryCell(r.getPosition(),VISITED);
                    done = true;
                    registry[0] = 0;
                }
                r.battery.unplug();
                break;
            case CLEAN:
                Rubbish rubbish;
                if(r.scaner.scanMyCell(WorldObjectVerifier.RUBBISH.getIntValue()) == null)
                {
                    done = true;
                    r.memory.setMemoryCell(r.getPosition(),VISITED);
                }
                else
                {
                    if((rubbish = (Rubbish)r.scaner.scanMyCell(WorldObjectVerifier.RUBBISH.getIntValue())) != null)
                    {
                        rubbish.cleaning(r.battery.getMaxCapacity(),r.battery.plug());
                        r.battery.unplug();
                    }
                    else
                        exception(instruction.getLine(), "WRONG CLEAN INSTRUCTION!!!!!!!!!!!!");
                }
                break;
            case RECEIVE:
                done = true;
                break;
            case FINDPATH:
                done = true;
                break;
        }
    }

    void exception(int line, String description)
    {
        log.error("LINE %d : %s",line,description);
    }

    public void pushMethod() {
        methodStack.push(program.getInstructionIndex());
    }

    public void popMethod() {
        int line = methodStack.pop();
        program.setInstructionIndex(line);
    }
}