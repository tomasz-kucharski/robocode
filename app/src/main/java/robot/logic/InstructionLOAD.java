package robot.logic;

import robot.Instruction;
import robot.InstructionOperator;
import robot.Robot;

/**
 * @author tomekk
 * @since 2010-10-24, 22:48:23
 */
public class InstructionLOAD extends Instruction {
    public InstructionLOAD() {
        super(1);
    }

    @Override
    public void process(Robot robot) throws InstructionExecutionException {
        if(getOperation() == InstructionOperator.EQUAL) {
            robot.getProcessor().getRegistry()[0] = getValue1();
        }
        else
        {
            if(robot.getProcessor().isProperRegisterAddress(getValue1()))
                throw new InstructionExecutionException("Instruction argument beyond registry index.",this);
            else {
                robot.getProcessor().getRegistry()[0] = robot.getProcessor().getRegistry()[getValue1()];
            }
        }
    }


}
