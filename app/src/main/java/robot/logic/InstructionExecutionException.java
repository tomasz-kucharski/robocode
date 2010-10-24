package robot.logic;

import robot.Instruction;

/**
 * @author tomekk
 * @since 2010-10-24, 23:03:17
 */
public class InstructionExecutionException extends Throwable {

    private Instruction instruction;

    public InstructionExecutionException(String s, Instruction instruction) {
        super(s);
        this.instruction = instruction;
    }

    public Instruction getInstruction() {
        return instruction;
    }
}
