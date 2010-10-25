package robot.logic;

import robot.Instruction;
import robot.Robot;
import robot.RobotProcessor;
import robot.RobotScanner;

/**
 * @author tomekk
 * @since 2010-10-24, 22:48:23
 */
public class InstructionMOVE extends Instruction {

    public InstructionMOVE() {
        super(5);
    }

    @Override
    public void process(Robot robot) throws InstructionExecutionException {
        if (robot.getProcessor().getInstructionProgress() >= 100) {
            robot.memory.setMemoryCell(robot.getPosition(), RobotProcessor.VISITED);
        } else if (robot.getProcessor().getInstructionProgress() > 80) {
            if(!robot.getWorld().move(robot, robot.memory.getDirection(), robot.battery.getMaxCapacity(), robot.battery.plug())) {
                robot.stateMove = 0;
            }
        } else {
            robot.stateMove ++;
        }
        robot.battery.unplug();
    }


}
