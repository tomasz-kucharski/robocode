package robot.logic;

import robot.Direction;
import robot.Instruction;
import robot.RobotProcessor;

/**
 * @author tomekk
 * @since 2010-10-24, 22:48:23
 */
public class InstructionINC extends Instruction {

    @Override
    public void process(RobotProcessor processor) throws InstructionExecutionException {
        int temp = getValue1();
        if((temp >= processor.getRegistry().length) || ( temp < 0))
            throw new InstructionExecutionException("Instruction argument beyond registry index.",this);
        else
            processor.getRegistry()[temp]++;
    }


}
