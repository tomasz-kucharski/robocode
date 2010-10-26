package robotgame.object.robot.logic;

import robotgame.object.robot.Robot;

/**
 * @author tomekk
 * @since 2010-10-24, 22:48:23
 */
public class InstructionTURNLEFT extends Instruction {

    public InstructionTURNLEFT() {
        super(5);
    }

    @Override
    public void process(Robot robot) throws InstructionExecutionException {
        if (robot.getProcessor().getInstructionProgress() >= 100) {
            robot.getMemory().turnLeft(robot.getBattery().plug());
            robot.getBattery().unplug();
        }
    }


}
