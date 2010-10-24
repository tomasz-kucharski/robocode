package robot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import robot.object.Rubbish;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Random;
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
    private boolean error;

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
        error = false;
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

    public boolean go()
    {
        if(error)
        {
            log.info("ERROR");
            return false;
        }

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
                    error = true;
                    break;
                }
                log.debug(instruction.toString());
                perform();
                if (error)
                    break;
            }
            if(!error)
                performExternal();
        }
        return true;
    }

    void perform()
    {
        int temp;

        switch(instruction.getRozkaz())
        {
            case MEMFRONT:
                registry[0] = r.memory.lookAround(r.getPosition(),r.memory.getDirection());
                break;
            case MEMLEFT:
                registry[0] = r.memory.lookAround(r.getPosition(),Direction.getLeft(r.memory.getDirection()));
                break;
            case MEMRIGHT:
                registry[0] = r.memory.lookAround(r.getPosition(),Direction.getRight(r.memory.getDirection()));
                break;
            case MEMBACK:
                registry[0] = r.memory.lookAround(r.getPosition(),Direction.getBackward(r.memory.getDirection()));
                break;
            case INC:
                temp = instruction.getValue1();
                if((temp >= REGISTRYSIZE) || ( temp < 0))
                    exception(instruction.getLine(),"ERROR INC- REGISTRY NOT PROPER\n");
                else
                    registry[temp]++;
                break;
            case DEC:
                temp = instruction.getValue1();
                if((temp >= REGISTRYSIZE) || ( temp < 0))
                    exception(instruction.getLine(), "ERROR DEC- REGISTRY NOT PROPER\n");
                else
                    registry[temp]--;
                break;
            case RAND:
                registry[0] = new Random().nextInt(instruction.getValue1());
                break;
            case JUMP:
                if(!program.gotoInstruction(instruction.getValue1()))
                    exception(instruction.getLine(), "ERROR JUMP= - LINE NOT EXIST\n");
                break;
            case JEQUAL:
                temp = instruction.getValue1();
                if(instruction.getOperation() == InstructionOperator.EQUAL)
                {
                    if(temp == registry[0])
                    {
                        if(!program.gotoInstruction(instruction.getValue2()))
                            exception(instruction.getLine(), "ERROR JEQUAL= - LINE NOT EXIST\n");
                    }
                }
                else
                {
                    if((temp >= REGISTRYSIZE) || (temp < 0))
                        exception(instruction.getLine(), "ERROR JEQUAL* - REGISTRY NOT PROPER\n");
                    else
                    {
                        if(registry[temp] == registry[0])
                        {
                            if(!program.gotoInstruction(instruction.getValue2()))
                                exception(instruction.getLine(), "ERROR JEQUAL* - LINE NOT EXIST\n");
                        }
                    }
                }
                break;
            case JNEQUAL:
                temp = instruction.getValue1();
                if(instruction.getOperation() == InstructionOperator.EQUAL)
                {
                    if(temp != registry[0])
                    {
                        if(!program.gotoInstruction(instruction.getValue2()))
                            exception(instruction.getLine(), "ERROR JNEQUAL= - LINE NOT EXIST\n");
                    }
                }
                else
                {
                    if((temp >= REGISTRYSIZE) || (temp < 0))
                        exception(instruction.getLine(), "ERROR JNEQUAL* - REGISTRY NOT PROPER\n");
                    else
                    {
                        if(registry[temp] != registry[0])
                        {
                            if(!program.gotoInstruction(instruction.getValue2()))
                                exception(instruction.getLine(), "ERROR JNEQUAL* - LINE NOT EXIST\n");
                        }
                    }
                }
                break;
            case JGT:
                temp = instruction.getValue1();
                if(instruction.getOperation() == InstructionOperator.EQUAL)
                {
                    if(temp == registry[0])
                    {
                        if(!program.gotoInstruction(instruction.getValue2()))
                            exception(instruction.getLine(), "ERROR JGT= - LINE NOT EXIST\n");
                    }
                }
                else
                {
                    if((temp >= REGISTRYSIZE) || (temp < 0))
                        exception(instruction.getLine(), "ERROR JGT* - REGISTRY NOT PROPER\n");
                    else
                    {
                        if(registry[temp] == registry[0])
                        {
                            if(!program.gotoInstruction(instruction.getValue2()))
                                exception(instruction.getLine(), "ERROR JGT* - LINE NOT EXIST\n");
                        }
                    }
                }
                break;
            case JLT:
                temp = instruction.getValue1();
                if(instruction.getOperation() == InstructionOperator.EQUAL)
                {
                    if(temp == registry[0])
                    {
                        if(!program.gotoInstruction(instruction.getValue2()))
                            exception(instruction.getLine(), "ERROR JLT= - LINE NOT EXIST\n");
                    }
                }
                else
                {
                    if((temp >= REGISTRYSIZE) || (temp < 0))
                        exception(instruction.getLine(), "ERROR JLT* - REGISTRY NOT PROPER\n");
                    else
                    {
                        if(registry[temp] == registry[0])
                        {
                            if(!program.gotoInstruction(instruction.getValue2()))
                                exception(instruction.getLine(), "ERROR JLT* - LINE NOT EXIST\n");
                        }
                    }
                }
                break;
            case JUMPF:
                if(!program.gotoInstruction(instruction.getValue1()))
                    exception(instruction.getLine(), "ERROR JUMP= - LINE NOT EXIST\n");
                else
                    methodStack.push(instruction.getLine());
                break;
            case JEQUALF:
                temp = instruction.getValue1();
                if(instruction.getOperation() == InstructionOperator.EQUAL)
                {
                    if(temp == registry[0])
                    {
                        if(!program.gotoInstruction(instruction.getValue2()))
                            exception(instruction.getLine(), "ERROR JEQUAL= - LINE NOT EXIST\n");
                        else
                            methodStack.push(instruction.getLine());
                    }
                }
                else
                {
                    if((temp >= REGISTRYSIZE) || (temp < 0))
                        exception(instruction.getLine(), "ERROR JEQUAL* - REGISTRY NOT PROPER\n");
                    else
                    {
                        if(registry[temp] == registry[0])
                        {
                            if(!program.gotoInstruction(instruction.getValue2()))
                                exception(instruction.getLine(), "ERROR JEQUAL* - LINE NOT EXIST\n");
                            else
                                methodStack.push(instruction.getLine());
                        }
                    }
                }
                break;
            case JNEQUALF:
                temp = instruction.getValue1();
                if(instruction.getOperation() == InstructionOperator.EQUAL)
                {
                    if(temp != registry[0])
                    {
                        if(!program.gotoInstruction(instruction.getValue2()))
                            exception(instruction.getLine(), "ERROR JNEQUAL= - LINE NOT EXIST\n");
                        else
                            methodStack.push(instruction.getLine());
                    }
                }
                else
                {
                    if((temp >= REGISTRYSIZE) || (temp < 0))
                        exception(instruction.getLine(), "ERROR JNEQUAL* - REGISTRY NOT PROPER\n");
                    else
                    {
                        if(registry[temp] != registry[0])
                        {
                            if(!program.gotoInstruction(instruction.getValue2()))
                                exception(instruction.getLine(), "ERROR JNEQUAL* - LINE NOT EXIST\n");
                            else
                                methodStack.push(instruction.getLine());
                        }
                    }
                }
                break;
            case JGTF:
                temp = instruction.getValue1();
                if(instruction.getOperation() == InstructionOperator.EQUAL)
                {
                    if(temp == registry[0])
                    {
                        if(!program.gotoInstruction(instruction.getValue2()))
                            exception(instruction.getLine(), "ERROR JGT= - LINE NOT EXIST\n");
                        else
                            methodStack.push(instruction.getLine());
                    }
                }
                else
                {
                    if((temp >= REGISTRYSIZE) || (temp < 0))
                        exception(instruction.getLine(), "ERROR JGT* - REGISTRY NOT PROPER\n");
                    else
                    {
                        if(registry[temp] == registry[0])
                        {
                            if(!program.gotoInstruction(instruction.getValue2()))
                                exception(instruction.getLine(), "ERROR JGT* - LINE NOT EXIST\n");
                            else
                                methodStack.push(instruction.getLine());
                        }
                    }
                }
                break;
            case JLTF:
                temp = instruction.getValue1();
                if(instruction.getOperation() == InstructionOperator.EQUAL)
                {
                    if(temp == registry[0])
                    {
                        if(!program.gotoInstruction(instruction.getValue2()))
                            exception(instruction.getLine(), "ERROR JLT= - LINE NOT EXIST\n");
                        else
                            methodStack.push(instruction.getLine());
                    }
                }
                else
                {
                    if((temp >= REGISTRYSIZE) || (temp < 0))
                        exception(instruction.getLine(), "ERROR JLT* - REGISTRY NOT PROPER\n");
                    else
                    {
                        if(registry[temp] == registry[0])
                        {
                            if(!program.gotoInstruction(instruction.getValue2()))
                                exception(instruction.getLine(), "ERROR JLT* - LINE NOT EXIST\n");
                            else
                                methodStack.push(instruction.getLine());
                        }
                    }
                }
                break;
            case RETURN:
                temp = methodStack.pop();
                if (temp == -1)
                    exception(instruction.getLine(), "ERROR RETURN - STACK EMPTY\n");
                if(program.getInstructionByLine(temp) != null)
                    exception(instruction.getLine(), "ERROR RETURN - LINE NOT EXIST\n");
                break;
            case STORE:
                temp = instruction.getValue1();
                if((temp >= REGISTRYSIZE) || (temp < 0))
                    exception(instruction.getLine(), "ERROR STORE - REGISTRY NOT PROPER\n");
                else
                {
                    registry[temp] = registry[0];
                }
                break;
            case LOAD:
                temp = instruction.getValue1();
                if(instruction.getOperation() == InstructionOperator.EQUAL)
                {
                    registry[0] = temp;
                }
                else
                {
                    if((temp >= REGISTRYSIZE) || (temp < 0))
                        exception(instruction.getLine(), "ERROR LOAD* - REGISTRY NOT PROPER\n");
                    else
                    {
                        registry[0] = registry[temp];
                    }
                }
                break;
            case READ:
                int temp2;
                temp = instruction.getValue1();
                temp2 = instruction.getValue2();
                if((temp >= REGISTRYSIZE) || (temp < 0))
                    exception(instruction.getLine(), "ERROR READ= - REGISTRY NOT PROPER\n");
                else
                {
                    if(instruction.getOperation() == InstructionOperator.EQUAL)
                    {
                        registry[temp] = temp2;
                    }
                    else
                    {
                        if((temp2 >= REGISTRYSIZE) || (temp < 0))
                            exception(instruction.getLine(), "ERROR READ* - REGISTRY NOT PROPER\n");
                        else
                        {
                            registry[temp] = registry[temp2];
                        }
                    }
                }
                break;
            default:
                external = true;
                done = false;
                break;
        }
    }

    void performExternal()
    {
        external = false;
        switch(instruction.getRozkaz())
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
                Rubbish rubbish = null;
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
        error = true;
    }

}