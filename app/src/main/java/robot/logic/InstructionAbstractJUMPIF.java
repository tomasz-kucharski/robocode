package robot.logic;

import robot.Instruction;
import robot.InstructionOperator;
import robot.RobotProcessor;

/**
 * @author tomekk
 * @since 2010-10-24, 22:48:23
 */
public abstract class InstructionAbstractJUMPIF extends Instruction {

    @Override
    public final void process(RobotProcessor processor) throws InstructionExecutionException {
        int temp = this.getValue1();
        if(this.getOperation() == InstructionOperator.EQUAL) {
            if(compare(temp,processor.getRegistry()[0])) {
                gotoInstruction(processor);
            }
        }
        else {
            if((temp >= processor.getRegistry().length) || (temp < 0))
                throw new InstructionExecutionException("ERROR * - REGISTRY NOT PROPER",this);
            else {
                if(processor.getRegistry()[temp] == processor.getRegistry()[0]) {
                    gotoInstruction(processor);
                }
            }
        }
    }
    
    abstract protected boolean compare(int i,int j);

    private void gotoInstruction(RobotProcessor processor) throws InstructionExecutionException {
        if(!processor.getProgram().gotoInstruction(getValue2())) {
            throw new InstructionExecutionException("Line does not exist",this);
        }
    }


}
