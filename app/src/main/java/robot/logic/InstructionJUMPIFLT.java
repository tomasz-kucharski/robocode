package robot.logic;

/**
 * @author tomekk
 * @since 2010-10-24, 22:48:23
 */
public class InstructionJUMPIFLT extends InstructionAbstractJUMPIF {
    
    protected boolean compare(int i,int j) {
        return i < j;
    }
}
