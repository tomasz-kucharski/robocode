package robot.logic;

import robot.Instruction;
import robot.Robot;
import robot.RobotScanner;

/**
 * @author tomekk
 * @since 2010-10-24, 22:48:23
 */
public class InstructionSCAN extends Instruction {

    public InstructionSCAN() {
        super(5);
    }

    @Override
    public void process(Robot robot) throws InstructionExecutionException {
        if (robot.getProcessor().getInstructionProgress() >= 100) {
            robot.getScanner().setProgress(RobotScanner.Progress.STOPPED);
        } else if (robot.getProcessor().getInstructionProgress() > 60) {
            robot.getScanner().scan();
        } else {
            robot.getScanner().setProgress(RobotScanner.Progress.STARTED);
        }
    }


}
