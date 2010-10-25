package robot.logic;

import robot.Instruction;
import robot.Robot;

/**
 * @author tomekk
 * @since 2010-10-24, 22:48:23
 */
public class InstructionINC extends Instruction {

    public InstructionINC() {
        super(1);
    }

    @Override
    public void process(Robot robot) throws InstructionExecutionException {
        int temp = getValue1();
        if(robot.getProcessor().isProperRegisterAddress(getValue1()))
            throw new InstructionExecutionException("Instruction argument beyond registry index.",this);
        else
            robot.getProcessor().getRegistry()[temp]++;
    }


}
