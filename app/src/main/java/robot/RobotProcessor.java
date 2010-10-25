package robot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import robot.logic.InstructionExecutionException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Stack;

public class RobotProcessor {
    private static final Logger log = LoggerFactory.getLogger(RobotProcessor.class);

    private static final int REGISTER_SIZE = 40;
    private static final int PROCESSOR_SPEED = 1;

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

    private Robot robot;

    private int[] registry;
    private ProgramList program;
    private Stack<Integer> methodStack = new Stack<Integer>();

    private Instruction instruction;
    private int instructionRemainingTime;

    public RobotProcessor(Robot robot, BufferedReader program) throws IOException {
        registry = new int[REGISTER_SIZE];
        this.robot = robot;
        this.program = new ProgramList();
        RobotProgramLoader programLoader = new RobotProgramLoader(program, this.program);
        programLoader.loadProgram();
    }

    public int[] getRegistry() {
        return registry;
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
            if (instructionRemainingTime == 0) {
                instruction = program.next();
                instructionRemainingTime = instruction.getExecutionTime();
                if ( instruction == null ) {
                    throw new InstructionExecutionException("END OF PROGRAM",null);
                }
            }
            decreaseRemainingTime();
            instruction.process(robot);
            return true;
        } catch (InstructionExecutionException e) {
            log.info("Wrong execution:"+e.getInstruction(),e);
            return false;
        }
    }

    private void decreaseRemainingTime() {
        instructionRemainingTime -= PROCESSOR_SPEED;
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

    public int getInstructionProgress() {
        return (instruction.getExecutionTime() - instructionRemainingTime)/instruction.getExecutionTime();
    }

    public boolean isProperRegisterAddress(int value) {
        return ((value >= robot.getProcessor().getRegistry().length) || (value < 0));
    }



}