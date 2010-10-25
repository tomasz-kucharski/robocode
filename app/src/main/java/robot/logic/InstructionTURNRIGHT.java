package robot.logic;

import robot.Instruction;
import robot.Robot;

/**
 * @author tomekk
 * @since 2010-10-24, 22:48:23
 */
public class InstructionTURNRIGHT extends Instruction {

    public InstructionTURNRIGHT() {
        super(5);
    }

    @Override
    public void process(Robot robot) throws InstructionExecutionException {
        if (robot.getProcessor().getInstructionProgress() >= 100) {
            robot.getMemory().turnRight(robot.battery.plug());
            robot.battery.unplug();
        }
    }


}
