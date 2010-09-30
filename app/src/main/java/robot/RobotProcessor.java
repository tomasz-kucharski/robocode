package robot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Stack;

public class RobotProcessor {
    private static final Logger log = LoggerFactory.getLogger(RobotProcessor.class);

    public static final int REGISTRYSIZE = 40;
    public static final int CYCLE = 5;
    //ROZKAZY
    public static final int SCAN = 1;
    public static final int CLEAN = 2;
    public static final int MOVE = 3;
    public static final int MEMLEFT = 4;
    public static final int MEMRIGHT = 5;
    public static final int MEMFRONT = 6;
    public static final int MEMBACK = 7;
    public static final int TURNLEFT = 8;
    public static final int TURNRIGHT = 9;
    public static final int RECEIVE = 10;
    public static final int FINDPATH = 11;
    public static final int RAND = 12;
    public static final int STORE = 13;
    public static final int LOAD = 14;
    public static final int READ = 15;
    public static final int INC = 16;
    public static final int DEC = 17;
    public static final int JUMP = 18;
    public static final int JEQUAL = 19;
    public static final int JNEQUAL = 20;
    public static final int JGT = 21;
    public static final int JLT = 22;
    public static final int JUMPF = 23;
    public static final int JEQUALF = 24;
    public static final int JNEQUALF = 25;
    public static final int JGTF = 26;
    public static final int JLTF = 27;
    public static final int	RETURN = 28;

    //OPERATORY
    public static final int OPEQUAL = 50;
    public static final int OPADR = 51;
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
    private int PC;
    private int[] registry;
    private boolean done;
    private boolean external;
    private boolean error;
    private int rozkaz;
    private int cycle;

    public RobotProcessor(Robot r, File fileName) throws FileNotFoundException {
        registry = new int[REGISTRYSIZE];
        for (int i=0; i<REGISTRYSIZE; i++)
            registry[i] = 0;
        this.r = r;
        program = new ProgramList();
        RobotProgramLoader load = new RobotProgramLoader(fileName,program);

        done = true;
        external = false;
        error = false;
        rozkaz = -1;
    }

    public static int getInstructionByName(String name)
    {
        if (name != null) {
            if(name.equals("SCAN"))
                return SCAN;
            if(name.equals("CLEAN"))
                return CLEAN;
            if(name.equals("MOVE"))
                return MOVE;
            if(name.equals("MEMLEFT"))
                return MEMLEFT;
            if(name.equals("MEMRIGHT"))
                return MEMRIGHT;
            if(name.equals("MEMBACK"))
                return MEMBACK;
            if(name.equals("MEMFRONT"))
                return MEMFRONT;
            if(name.equals("TURNLEFT"))
                return TURNLEFT;
            if(name.equals("TURNRIGHT"))
                return TURNRIGHT;
            if(name.equals("RECEIVE"))
                return RECEIVE;
            if(name.equals("FINDPATH"))
                return FINDPATH;
            if(name.equals("RAND"))
                return RAND;
            if(name.equals("STORE"))
                return STORE;
            if(name.equals("LOAD"))
                return LOAD;
            if(name.equals("READ"))
                return READ;
            if(name.equals("INC"))
                return INC;
            if(name.equals("DEC"))
                return DEC;
            if(name.equals("JUMP"))
                return JUMP;
            if(name.equals("JEQUAL"))
                return JEQUAL;
            if(name.equals("JNEQUAL"))
                return JNEQUAL;
            if(name.equals("JGT"))
                return JGT;
            if(name.equals("JLT"))
                return JLT;
            if(name.equals("JUMPF"))
                return JUMPF;
            if(name.equals("JEQUALF"))
                return JEQUALF;
            if(name.equals("JNEQUALF"))
                return JNEQUALF;
            if(name.equals("JGTF"))
                return JGTF;
            if(name.equals("JLTF"))
                return JLTF;
            if(name.equals("RETURN"))
                return RETURN;
        }
        return -1;
    }

    public static int getOperationByName(String name)
    {
        if (name != null)
        {
            if(name.equals("="))
                return OPEQUAL;
            if(name.equals("*"))
                return OPADR;
        }
        return -1;
    }

    int getMemoryObjectByName(String name)
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

    public static int getTypeOfInstruction(int rozkaz)
    {
        switch(rozkaz)
        {
            case SCAN:
            case CLEAN:
            case MOVE:
            case MEMFRONT:
            case MEMRIGHT:
            case MEMBACK:
            case MEMLEFT:
            case TURNLEFT:
            case TURNRIGHT:
            case RECEIVE:
            case FINDPATH:
            case RETURN:
                return 0;  // brak argumentow

            case INC:
            case DEC:
            case STORE:
            case JUMP:
            case JUMPF:
                return 1; // 1 argument
            case RAND:
            case LOAD:
                return 2; // 1 argument + operator
            case JEQUAL:
            case JNEQUAL:
            case JLT:
            case JGT:
            case JEQUALF:
            case JNEQUALF:
            case JLTF:
            case JGTF:
            case READ:
                return 3; // 2 argumenty + operator
            default:
                return -1;
        }
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
            cycle = 0; //ile cykli na takt - nie zaimplementowane
            while(!external)
            {
                instruction = program.getInstruction();
                if ( instruction == null )
                {
                    exception(instruction.getLine(), "ERROR - END OF PROGRAM\n");
                    error = true;
                    break;
                }
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
                if(instruction.getOperation() == OPEQUAL)
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
                if(instruction.getOperation() == OPEQUAL)
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
                if(instruction.getOperation() == OPEQUAL)
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
                if(instruction.getOperation() == OPEQUAL)
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
                if(instruction.getOperation() == OPEQUAL)
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
                if(instruction.getOperation() == OPEQUAL)
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
                if(instruction.getOperation() == OPEQUAL)
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
                if(instruction.getOperation() == OPEQUAL)
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
                if(!program.returnInstruction(temp))
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
                if(instruction.getOperation() == OPEQUAL)
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
                    if(instruction.getOperation() == OPEQUAL)
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