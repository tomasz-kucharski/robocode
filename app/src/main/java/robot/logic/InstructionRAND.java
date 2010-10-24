package robot.logic;

import robot.Instruction;
import robot.RobotProcessor;

import java.util.Random;

/**
 * @author tomekk
 * @since 2010-10-24, 22:32:36
 */
public class InstructionRAND extends Instruction {

    public void process(RobotProcessor processor) {
        processor.getRegistry()[0] = new Random().nextInt(getValue1());
    }
}
