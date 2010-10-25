package robot.logic;

import robot.Instruction;
import robot.Robot;

/**
 * @author tomekk
 * @since 2010-10-24, 22:48:23
 */
public class InstructionSTORE extends Instruction {
    public InstructionSTORE() {
        super(1);
    }

    @Override
    public void process(Robot robot) throws InstructionExecutionException {
        if(robot.getProcessor().isProperRegisterAddress(getValue1()))
            throw new InstructionExecutionException("Instruction argument beyond registry index.",this);
        else {
            robot.getProcessor().getRegistry()[getValue1()] = robot.getProcessor().getRegistry()[0];
        }
    }


}
