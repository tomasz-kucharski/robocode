package robotgame.object.robot.logic;

import robotgame.object.robot.Robot;

import java.util.EmptyStackException;

/**
 * @author tomekk
 * @since 2010-10-24, 22:48:23
 */
public class InstructionRETURN extends Instruction {
    public InstructionRETURN() {
        super(1);
    }

    @Override
    public void process(Robot robot) throws InstructionExecutionException {
        try {
            robot.getProcessor().popMethod();
        } catch (EmptyStackException e) {
            throw new InstructionExecutionException("Stack is empty",this);
        }
        if(robot.getProcessor().getProgram().get() == null) {
            throw new InstructionExecutionException("Stack is inconsistent",this);            
        }
    }


}
