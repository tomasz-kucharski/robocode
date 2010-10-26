package robotgame.object.robot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import robotgame.loader.RobotProgramLoader;
import robotgame.object.robot.logic.Instruction;
import robotgame.object.robot.logic.InstructionExecutionException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Stack;

public class RobotProcessor {
    private static final Logger log = LoggerFactory.getLogger(RobotProcessor.class);

    private static final int REGISTER_SIZE = 40;
    private static final int PROCESSOR_SPEED = 1;

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

    public void pushMethod() {
        methodStack.push(program.getInstructionIndex());
    }

    public void popMethod() {
        int line = methodStack.pop();
        program.setInstructionIndex(line);
    }

    public int getInstructionProgress() {
        return ((instruction.getExecutionTime() - instructionRemainingTime)*100)/instruction.getExecutionTime();
    }

    public boolean isProperRegisterAddress(int value) {
        return ((value >= robot.getProcessor().getRegistry().length) || (value < 0));
    }



}