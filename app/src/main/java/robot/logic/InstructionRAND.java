package robot.logic;

import robot.Instruction;
import robot.Robot;

import java.util.Random;

/**
 * @author tomekk
 * @since 2010-10-24, 22:32:36
 */
public class InstructionRAND extends Instruction {
    public InstructionRAND() {
        super(2);
    }

    public void process(Robot robot) {
        robot.getProcessor().getRegistry()[0] = new Random().nextInt(getValue1());
    }
}
