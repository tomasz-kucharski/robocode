package robot.logic;

import robot.*;

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
        if (robot.getProcessor().getInstructionProgress() >= 80) {
            robot.getMemory().setMemoryCell(robot.getPosition(), RobotMemoryObject.VISITED);
            if(!robot.getWorld().move(robot, robot.getMemory().getDirection(), robot.getBattery().getMaxCapacity(), robot.getBattery().plug())) {
                robot.stateMove = 0;
            }
        }
        if (robot.getProcessor().getInstructionProgress() >= 80) {
            robot.stateMove ++;
        }
        robot.getBattery().unplug();
    }
}
