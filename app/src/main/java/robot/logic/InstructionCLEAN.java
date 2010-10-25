package robot.logic;

import robot.*;
import robot.object.Rubbish;

/**
 * @author tomekk
 * @since 2010-10-24, 22:48:23
 */
public class InstructionCLEAN extends Instruction {

    public InstructionCLEAN() {
        super(5);
    }

    @Override
    public void process(Robot robot) throws InstructionExecutionException {
        Rubbish rubbish = (Rubbish) robot.getScanner().scanMyCell(WorldObjectVerifier.RUBBISH.getIntValue());
        if(rubbish == null) {
            robot.memory.setMemoryCell(robot.getPosition(), RobotProcessor.VISITED);
        }
        else {
            rubbish.cleaning(robot.battery.getMaxCapacity(), robot.battery.plug());
            robot.battery.unplug();
        }
    }
}
