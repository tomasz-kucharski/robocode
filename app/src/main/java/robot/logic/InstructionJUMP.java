package robot.logic;

import robot.Instruction;
import robot.Robot;

/**
 * @author tomekk
 * @since 2010-10-24, 22:48:23
 */
public class InstructionJUMP extends Instruction {
    public InstructionJUMP() {
        super(1);
    }

    @Override
    public void process(Robot robot) throws InstructionExecutionException {
        boolean success = robot.getProcessor().getProgram().gotoInstruction(this.getValue1());
        if (!success) {
            throw new InstructionExecutionException("Label: '"+this.getValue1()+"' does not exist",this);
        }
    }


}
