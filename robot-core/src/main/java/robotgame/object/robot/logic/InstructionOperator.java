package robotgame.object.robot.logic;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tomekk
 * @since 2010-10-24, 21:21:53
 */
public enum InstructionOperator {

    EQUAL("="),
    ADDRESS("*");

    private static Map<String,InstructionOperator> notationMapping = new HashMap<String, InstructionOperator>();

    private String notation;

    InstructionOperator(String notation) {
        this.notation = notation;
    }

    public String getNotation() {
        return notation;
    }

    public static InstructionOperator getInstructionOperatorByNotation(String notation) {
        if (notationMapping.isEmpty()) {
            for (InstructionOperator operator : InstructionOperator.values()) {
                notationMapping.put(operator.getNotation(),operator);
            }
        }
        return notationMapping.get(notation);
    }
}
