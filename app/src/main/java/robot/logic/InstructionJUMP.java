package robot.logic;

import robot.Instruction;
import robot.RobotProcessor;

/**
 * @author tomekk
 * @since 2010-10-24, 22:48:23
 */
public class InstructionJUMP extends Instruction {

    @Override
    public void process(RobotProcessor processor) throws InstructionExecutionException {
        boolean success = processor.getProgram().gotoInstruction(this.getValue1());
        if (!success) {
            throw new InstructionExecutionException("Label: '"+this.getValue1()+"' does not exist",this);
        }
    }


}
