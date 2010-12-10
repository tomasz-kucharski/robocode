package robotgame.object.robot.logic;

import robotgame.object.robot.Robot;

/**
 * @author tomekk
 * @since 2010-10-24, 22:48:23
 */
public class InstructionMEMFRONT extends Instruction {
    public InstructionMEMFRONT() {
        super(1);
    }

    @Override
    public void process(Robot robot) {
        robot.getProcessor().getRegistry()[0] = robot.getMemory().lookAround(robot.getPosition(), robot.getMemory().getDirection());

    }
}
