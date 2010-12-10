package robotgame.object.robot.logic;

import robotgame.object.Rubbish;
import robotgame.object.robot.Robot;
import robotgame.object.robot.RobotMemoryObject;
import robotgame.world.MapObject;

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
        Rubbish rubbish = (Rubbish) robot.getScanner().scanMyCell(MapObject.RUBBISH);
        if(rubbish == null) {
            robot.getMemory().setMemoryCell(robot.getPosition(), RobotMemoryObject.VISITED);
        }
        else {
            rubbish.cleaning(robot.getBattery().getMaxCapacity(), robot.getBattery().plug());
            robot.getBattery().unplug();
        }
    }
}
