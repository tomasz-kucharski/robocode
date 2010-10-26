package robotgame.object.robot;

import robotgame.object.robot.logic.Instruction;

import java.util.*;

public class ProgramList {

    private List<Instruction> instructions = new ArrayList<Instruction>();
    private Map<Integer, Instruction> instructionsToLines = new HashMap<Integer, Instruction>();
    private int instructionIndex;
    
    public void addInstruction(Instruction instruction) {
        instructions.add(instruction);
        instructionsToLines.put(instruction.getLine(),instruction);
    }

    public Instruction get() {
        return instructions.get(instructionIndex);
    }
    public Instruction next() {
        return instructions.get(instructionIndex++);
    }

    public boolean gotoInstruction(int label) {
        for (int i=0; i<instructions.size(); i++) {
            Instruction instruction = instructions.get(i);
            if (instruction.getLabel() == label) {
                instructionIndex = i;
                return true;
            }
        }
        return false;
    }


    public Instruction getInstructionByLine(int line) {
        return instructionsToLines.get(line);
    }

    public int size() {
        return instructions.size();
    }

    public int getInstructionIndex() {
        return instructionIndex;
    }

    public void setInstructionIndex(int instructionIndex) {
        this.instructionIndex = instructionIndex;
    }
}
