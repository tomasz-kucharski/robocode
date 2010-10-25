package robot.logic;

import robot.Direction;
import robot.Instruction;
import robot.Robot;

/**
 * @author tomekk
 * @since 2010-10-24, 22:48:23
 */
public class InstructionMEMRIGHT extends Instruction {
    public InstructionMEMRIGHT() {
        super(1);
    }

    @Override
    public void process(Robot robot) {
        robot.getProcessor().getRegistry()[0] = robot.getMemory().lookAround(
                robot.getPosition(),
                robot.getMemory().getDirection().getRight());

    }
}
