package robot.logic;

import robot.Instruction;
import robot.RobotProcessor;

import java.util.EmptyStackException;

/**
 * @author tomekk
 * @since 2010-10-24, 22:48:23
 */
public class InstructionRETURN extends Instruction {

    @Override
    public void process(RobotProcessor processor) throws InstructionExecutionException {
        try {
            processor.popMethod();
        } catch (EmptyStackException e) {
            throw new InstructionExecutionException("Stack is empty",this);
        }
        if(processor.getProgram().get() == null) {
            throw new InstructionExecutionException("Stack is inconsistent",this);            
        }
    }


}
