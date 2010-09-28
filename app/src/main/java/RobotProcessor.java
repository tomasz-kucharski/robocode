import java.io.File;
import java.util.Stack;

public class RobotProcessor {
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
    private Stack stack;
    private Robot r;
    private int PC;
    private int registry;
    private boolean done;
    private boolean external;
    private boolean error;
    private int rozkaz;
    private int cycle;

    public RobotProcessor(Robot r, File fileName)
    {
        registry = new int[REGISTRYSIZE];
        for (int i=0; i<REGISTRYSIZE; i++)
            registry[i] = 0;
        this->r = r;
        program = new ProgramList();
        RobotProgramLoader load = new RobotProgramLoader(fileName,program);
        delete load;
        instruction = NULL;
        stack = new Stack();

        done = true;
        external = false;
        error = false;
        rozkaz = -1;
    }

    RobotProcessor::~RobotProcessor()
    {
        delete[] registry;
        delete program;
        delete stack;
    }

    public static int getInstructionByName(String name)
    {
        if (name != NULL) {
            if(!strcmp(name,"SCAN"))
                return SCAN;
            if(!strcmp(name,"CLEAN"))
                return CLEAN;
            if(!strcmp(name,"MOVE"))
                return MOVE;
            if(!strcmp(name,"MEMLEFT"))
                return MEMLEFT;
            if(!strcmp(name,"MEMRIGHT"))
                return MEMRIGHT;
            if(!strcmp(name,"MEMBACK"))
                return MEMBACK;
            if(!strcmp(name,"MEMFRONT"))
                return MEMFRONT;
            if(!strcmp(name,"TURNLEFT"))
                return TURNLEFT;
            if(!strcmp(name,"TURNRIGHT"))
                return TURNRIGHT;
            if(!strcmp(name,"RECEIVE"))
                return RECEIVE;
            if(!strcmp(name,"FINDPATH"))
                return FINDPATH;
            if(!strcmp(name,"RAND"))
                return RAND;
            if(!strcmp(name,"STORE"))
                return STORE;
            if(!strcmp(name,"LOAD"))
                return LOAD;
            if(!strcmp(name,"READ"))
                return READ;
            if(!strcmp(name,"INC"))
                return INC;
            if(!strcmp(name,"DEC"))
                return DEC;
            if(!strcmp(name,"JUMP"))
                return JUMP;
            if(!strcmp(name,"JEQUAL"))
                return JEQUAL;
            if(!strcmp(name,"JNEQUAL"))
                return JNEQUAL;
            if(!strcmp(name,"JGT"))
                return JGT;
            if(!strcmp(name,"JLT"))
                return JLT;
            if(!strcmp(name,"JUMPF"))
                return JUMPF;
            if(!strcmp(name,"JEQUALF"))
                return JEQUALF;
            if(!strcmp(name,"JNEQUALF"))
                return JNEQUALF;
            if(!strcmp(name,"JGTF"))
                return JGTF;
            if(!strcmp(name,"JLTF"))
                return JLTF;
            if(!strcmp(name,"RETURN"))
                return RETURN;
        }
        return -1;
    }

    int RobotProcessor::getOperationByName(char *name)
    {
        if (name != NULL)
        {
            if(!strcmp(name,"="))
                return OPEQUAL;
            if(!strcmp(name,"*"))
                return OPADR;
        }
        return -1;
    }

    int RobotProcessor::getMemoryObjectByName(char *name)
    {
        if (name != NULL) {
            if(!strcmp(name,"UNKNOWN"))
                return UNKNOWN;
            if(!strcmp(name,"EMPTY"))
                return EMPTY;
            if(!strcmp(name,"RUBBISH"))
                return RUBBISH;
            if(!strcmp(name,"UNMOVABLE"))
                return UNMOVABLE;
            if(!strcmp(name,"VISITED"))
                return VISITED;
            if(!strcmp(name,"DEPOT"))
                return DEPOT;
            if(!strcmp(name,"ROBOT"))
                return ROBOT;
            if(!strcmp(name,"END"))
                return END;
        }
        return -1;
    }

    int RobotProcessor::getTypeOfInstruction(int rozkaz)
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
            break;
            case INC:
            case DEC:
            case STORE:
            case JUMP:
            case JUMPF:
                return 1; // 1 argument
            break;
            case RAND:
            case LOAD:
                return 2; // 1 argument + operator
            break;
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
            break;
            default:
                return -1;
            break;
        }
    }



    public boolean go()
    {
        if(error)
        {
            TRACE("ERROR\n");
            return false;;
        }

        if(!done)
            performExternal();
        else
        {
            done = false;
            cycle = 0; //ile cykli na takt - nie zaimplementowane
            while(!external)
            {
                instruction = program->getInstruction();
                if ( instruction == NULL )
                {
                    exception(instruction->getLine(), "ERROR - END OF PROGRAM\n");
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

    void RobotProcessor::perform()
    {
        int temp;

        switch(instruction->getRozkaz())
        {
            case MEMFRONT:
                registry[0] = r->memory->lookAround(r->getPosition(),r->memory->getDirection());
                break;
            case MEMLEFT:
                registry[0] = r->memory->lookAround(r->getPosition(),Direction::getLeft(r->memory->getDirection()));
                break;
            case MEMRIGHT:
                registry[0] = r->memory->lookAround(r->getPosition(),Direction::getRight(r->memory->getDirection()));
                break;
            case MEMBACK:
                registry[0] = r->memory->lookAround(r->getPosition(),Direction::getBackward(r->memory->getDirection()));
                break;
            case INC:
                temp = instruction->getValue1();
                if((temp >= REGISTRYSIZE) || ( temp < 0))
                    exception(instruction->getLine(),"ERROR INC- REGISTRY NOT PROPER\n");
                else
                    registry[temp]++;
                break;
            case DEC:
                temp = instruction->getValue1();
                if((temp >= REGISTRYSIZE) || ( temp < 0))
                    exception(instruction->getLine(), "ERROR DEC- REGISTRY NOT PROPER\n");
                else
                    registry[temp]--;
                break;
            case RAND:
                registry[0] = rand()%instruction->getValue1();
                break;
            case JUMP:
                if(!program->gotoInstruction(instruction->getValue1()))
                    exception(instruction->getLine(), "ERROR JUMP= - LINE NOT EXIST\n");
                break;
            case JEQUAL:
                temp = instruction->getValue1();
                if(instruction->getOperation() == OPEQUAL)
                {
                    if(temp == registry[0])
                    {
                        if(!program->gotoInstruction(instruction->getValue2()))
                            exception(instruction->getLine(), "ERROR JEQUAL= - LINE NOT EXIST\n");
                    }
                }
                else
                {
                    if((temp >= REGISTRYSIZE) || (temp < 0))
                        exception(instruction->getLine(), "ERROR JEQUAL* - REGISTRY NOT PROPER\n");
                    else
                    {
                        if(registry[temp] == registry[0])
                        {
                            if(!program->gotoInstruction(instruction->getValue2()))
                                exception(instruction->getLine(), "ERROR JEQUAL* - LINE NOT EXIST\n");
                        }
                    }
                }
                break;
            case JNEQUAL:
                temp = instruction->getValue1();
                if(instruction->getOperation() == OPEQUAL)
                {
                    if(temp != registry[0])
                    {
                        if(!program->gotoInstruction(instruction->getValue2()))
                            exception(instruction->getLine(), "ERROR JNEQUAL= - LINE NOT EXIST\n");
                    }
                }
                else
                {
                    if((temp >= REGISTRYSIZE) || (temp < 0))
                        exception(instruction->getLine(), "ERROR JNEQUAL* - REGISTRY NOT PROPER\n");
                    else
                    {
                        if(registry[temp] != registry[0])
                        {
                            if(!program->gotoInstruction(instruction->getValue2()))
                                exception(instruction->getLine(), "ERROR JNEQUAL* - LINE NOT EXIST\n");
                        }
                    }
                }
                break;
            case JGT:
                temp = instruction->getValue1();
                if(instruction->getOperation() == OPEQUAL)
                {
                    if(temp == registry[0])
                    {
                        if(!program->gotoInstruction(instruction->getValue2()))
                            exception(instruction->getLine(), "ERROR JGT= - LINE NOT EXIST\n");
                    }
                }
                else
                {
                    if((temp >= REGISTRYSIZE) || (temp < 0))
                        exception(instruction->getLine(), "ERROR JGT* - REGISTRY NOT PROPER\n");
                    else
                    {
                        if(registry[temp] == registry[0])
                        {
                            if(!program->gotoInstruction(instruction->getValue2()))
                                exception(instruction->getLine(), "ERROR JGT* - LINE NOT EXIST\n");
                        }
                    }
                }
                break;
            case JLT:
                temp = instruction->getValue1();
                if(instruction->getOperation() == OPEQUAL)
                {
                    if(temp == registry[0])
                    {
                        if(!program->gotoInstruction(instruction->getValue2()))
                            exception(instruction->getLine(), "ERROR JLT= - LINE NOT EXIST\n");
                    }
                }
                else
                {
                    if((temp >= REGISTRYSIZE) || (temp < 0))
                        exception(instruction->getLine(), "ERROR JLT* - REGISTRY NOT PROPER\n");
                    else
                    {
                        if(registry[temp] == registry[0])
                        {
                            if(!program->gotoInstruction(instruction->getValue2()))
                                exception(instruction->getLine(), "ERROR JLT* - LINE NOT EXIST\n");
                        }
                    }
                }
                break;
            case JUMPF:
                if(!program->gotoInstruction(instruction->getValue1()))
                    exception(instruction->getLine(), "ERROR JUMP= - LINE NOT EXIST\n");
                else
                    stack->push(instruction->getLine());
                break;
            case JEQUALF:
                temp = instruction->getValue1();
                if(instruction->getOperation() == OPEQUAL)
                {
                    if(temp == registry[0])
                    {
                        if(!program->gotoInstruction(instruction->getValue2()))
                            exception(instruction->getLine(), "ERROR JEQUAL= - LINE NOT EXIST\n");
                        else
                            stack->push(instruction->getLine());
                    }
                }
                else
                {
                    if((temp >= REGISTRYSIZE) || (temp < 0))
                        exception(instruction->getLine(), "ERROR JEQUAL* - REGISTRY NOT PROPER\n");
                    else
                    {
                        if(registry[temp] == registry[0])
                        {
                            if(!program->gotoInstruction(instruction->getValue2()))
                                exception(instruction->getLine(), "ERROR JEQUAL* - LINE NOT EXIST\n");
                            else
                                stack->push(instruction->getLine());
                        }
                    }
                }
                break;
            case JNEQUALF:
                temp = instruction->getValue1();
                if(instruction->getOperation() == OPEQUAL)
                {
                    if(temp != registry[0])
                    {
                        if(!program->gotoInstruction(instruction->getValue2()))
                            exception(instruction->getLine(), "ERROR JNEQUAL= - LINE NOT EXIST\n");
                        else
                            stack->push(instruction->getLine());
                    }
                }
                else
                {
                    if((temp >= REGISTRYSIZE) || (temp < 0))
                        exception(instruction->getLine(), "ERROR JNEQUAL* - REGISTRY NOT PROPER\n");
                    else
                    {
                        if(registry[temp] != registry[0])
                        {
                            if(!program->gotoInstruction(instruction->getValue2()))
                                exception(instruction->getLine(), "ERROR JNEQUAL* - LINE NOT EXIST\n");
                            else
                                stack->push(instruction->getLine());
                        }
                    }
                }
                break;
            case JGTF:
                temp = instruction->getValue1();
                if(instruction->getOperation() == OPEQUAL)
                {
                    if(temp == registry[0])
                    {
                        if(!program->gotoInstruction(instruction->getValue2()))
                            exception(instruction->getLine(), "ERROR JGT= - LINE NOT EXIST\n");
                        else
                            stack->push(instruction->getLine());
                    }
                }
                else
                {
                    if((temp >= REGISTRYSIZE) || (temp < 0))
                        exception(instruction->getLine(), "ERROR JGT* - REGISTRY NOT PROPER\n");
                    else
                    {
                        if(registry[temp] == registry[0])
                        {
                            if(!program->gotoInstruction(instruction->getValue2()))
                                exception(instruction->getLine(), "ERROR JGT* - LINE NOT EXIST\n");
                            else
                                stack->push(instruction->getLine());
                        }
                    }
                }
                break;
            case JLTF:
                temp = instruction->getValue1();
                if(instruction->getOperation() == OPEQUAL)
                {
                    if(temp == registry[0])
                    {
                        if(!program->gotoInstruction(instruction->getValue2()))
                            exception(instruction->getLine(), "ERROR JLT= - LINE NOT EXIST\n");
                        else
                            stack->push(instruction->getLine());
                    }
                }
                else
                {
                    if((temp >= REGISTRYSIZE) || (temp < 0))
                        exception(instruction->getLine(), "ERROR JLT* - REGISTRY NOT PROPER\n");
                    else
                    {
                        if(registry[temp] == registry[0])
                        {
                            if(!program->gotoInstruction(instruction->getValue2()))
                                exception(instruction->getLine(), "ERROR JLT* - LINE NOT EXIST\n");
                            else
                                stack->push(instruction->getLine());
                        }
                    }
                }
                break;
            case RETURN:
                temp = stack->pop();
                if (temp == -1)
                    exception(instruction->getLine(), "ERROR RETURN - STACK EMPTY\n");
                if(!program->returnInstruction(temp))
                    exception(instruction->getLine(), "ERROR RETURN - LINE NOT EXIST\n");
                break;
            case STORE:
                temp = instruction->getValue1();
                if((temp >= REGISTRYSIZE) || (temp < 0))
                    exception(instruction->getLine(), "ERROR STORE - REGISTRY NOT PROPER\n");
                else
                {
                    registry[temp] = registry[0];
                }
                break;
            case LOAD:
                temp = instruction->getValue1();
                if(instruction->getOperation() == OPEQUAL)
                {
                    registry[0] = temp;
                }
                else
                {
                    if((temp >= REGISTRYSIZE) || (temp < 0))
                        exception(instruction->getLine(), "ERROR LOAD* - REGISTRY NOT PROPER\n");
                    else
                    {
                        registry[0] = registry[temp];
                    }
                }
                break;
            case READ:
                int temp2;
                temp = instruction->getValue1();
                temp2 = instruction->getValue2();
                if((temp >= REGISTRYSIZE) || (temp < 0))
                    exception(instruction->getLine(), "ERROR READ= - REGISTRY NOT PROPER\n");
                else
                {
                    if(instruction->getOperation() == OPEQUAL)
                    {
                        registry[temp] = temp2;
                    }
                    else
                    {
                        if((temp2 >= REGISTRYSIZE) || (temp < 0))
                            exception(instruction->getLine(), "ERROR READ* - REGISTRY NOT PROPER\n");
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

    void RobotProcessor::performExternal()
    {
        external = false;
        switch(instruction->getRozkaz())
        {
            case SCAN:
                r->stateScaner++;
                r->stateScaner %= Robot::SCANERSTATE;
                if(r->stateScaner == 1)
                    r->scaner->scan();
                if(r->stateScaner == 0)
                    done = true;
                break;
            case TURNLEFT:
                r->memory->turnLeft(r->battery->plug());
                r->battery->unplug();
                done = true;
                break;
            case TURNRIGHT:
                r->memory->turnRight(r->battery->plug());
                r->battery->unplug();
                done = true;
                break;
            case MOVE:
                r->stateMove++;
                r->stateMove %= Robot::MOVESTATE;
                if(r->stateMove == 1)
                {
                    if(!r->getWorld()->move(r,r->memory->getDirection(),r->battery->getMaxCapacity(),r->battery->plug()))
                    {
                        TRACE("PRZESZKODA!\n");
                        r->stateMove = 0;
                        registry[0] = 1;
                    }
                }
                if(r->stateMove == 0 )
                {
                    r->memory->setMemoryCell(r->getPosition(),VISITED);
                    done = true;
                    registry[0] = 0;
                }
                break;
            case CLEAN:
                Rubbish* rubbish ;
                rubbish = NULL;
                if(r->scaner->scanMyCell(WorldObjectVerifier::RUBBISH) == NULL)
            {
                done = true;
                r->memory->setMemoryCell(r->getPosition(),VISITED);
            }
            else
            {
                if((rubbish = (Rubbish*)r->scaner->scanMyCell(WorldObjectVerifier::RUBBISH)) != NULL)
                {
                    rubbish->cleaning(r->battery->getMaxCapacity(),r->battery->plug());
                    r->battery->unplug();
                }
                else
                exception(instruction->getLine(), "WRONG CLEAN INSTRUCTION!!!!!!!!!!!!");
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

    void RobotProcessor::exception(int line, char* description)
    {
        TRACE("LINE %d : %s",line,description);
        error = true;
    }

}