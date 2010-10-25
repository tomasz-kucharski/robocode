package robot.logic;

import robot.Instruction;
import robot.InstructionOperator;
import robot.Robot;
import robot.RobotProcessor;

/**
 * @author tomekk
 * @since 2010-10-24, 22:48:23
 */
public abstract class InstructionAbstractJUMPIF extends Instruction {
    public InstructionAbstractJUMPIF() {
        super(1);
    }

    @Override
    public final void process(Robot robot) throws InstructionExecutionException {
        int temp = this.getValue1();
        if(this.getOperation() == InstructionOperator.EQUAL) {
            if(compare(temp, robot.getProcessor().getRegistry()[0])) {
                gotoInstruction(robot.getProcessor());
            }
        }
        else {
            if(robot.getProcessor().isProperRegisterAddress(getValue1()))
                throw new InstructionExecutionException("Instruction argument beyond registry index.",this);
            else {
                if(robot.getProcessor().getRegistry()[temp] == robot.getProcessor().getRegistry()[0]) {
                    gotoInstruction(robot.getProcessor());
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
