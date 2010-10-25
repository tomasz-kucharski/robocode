package robot.logic;

import robot.Instruction;
import robot.Robot;
import robot.RobotScanner;

/**
 * @author tomekk
 * @since 2010-10-24, 22:48:23
 */
public class InstructionFINDPATH extends Instruction {

    public InstructionFINDPATH() {
        super(100);
    }

    @Override
    public void process(Robot robot) throws InstructionExecutionException {
        throw new UnsupportedOperationException();
    }


}
