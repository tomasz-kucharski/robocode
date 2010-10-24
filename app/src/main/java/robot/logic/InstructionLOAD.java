package robot.logic;

import robot.Instruction;
import robot.InstructionOperator;
import robot.RobotProcessor;

/**
 * @author tomekk
 * @since 2010-10-24, 22:48:23
 */
public class InstructionLOAD extends Instruction {

    @Override
    public void process(RobotProcessor processor) throws InstructionExecutionException {
        if(getOperation() == InstructionOperator.EQUAL) {
            processor.getRegistry()[0] = getValue1();
        }
        else
        {
            if((getValue1() >= processor.getRegistry().length) || (getValue1() < 0))
                throw new InstructionExecutionException("Instruction argument beyond registry index.",this);
            else {
                processor.getRegistry()[0] = processor.getRegistry()[getValue1()];
            }
        }
    }


}
