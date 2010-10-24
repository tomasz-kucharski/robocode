package robot.logic;

import robot.Instruction;
import robot.RobotProcessor;

/**
 * @author tomekk
 * @since 2010-10-24, 22:48:23
 */
public class InstructionMEMFRONT extends Instruction {

    @Override
    public void process(RobotProcessor processor) {
        processor.getRegistry()[0] = processor.getMemory().lookAround(
                processor.getRobotPosition(),processor.getMemory().getDirection());

    }
}
