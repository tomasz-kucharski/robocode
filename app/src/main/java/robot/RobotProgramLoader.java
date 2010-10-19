package robot;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RobotProgramLoader {

    public static final int FIRST_ATTRIBUTE = 0;
    public static final int SECOND_ATTRIBUTE = 1;
    public static final int THIRD_ATTRIBUTE = 2;
    public static final int FOURTH_ATTRIBUTE = 3;
    private static final int FIFTH_ATTRIBUTE = 4;

    private BufferedReader reader;
    private ProgramList program;
    private static final String COMMENT_MARK = "#";
    private static final String INSTRUCTION_PARAMS_SEPARATOR = "\t";


    private int lineNumber;

    public RobotProgramLoader(BufferedReader programFile, ProgramList program) throws IOException {
        this.program = program;
        this.reader = programFile;
        loadProgram();
        program.setToFirst();
    }

    public void loadProgram() throws IOException {
        String lineOfFile;
        do {
            lineOfFile = reader.readLine();
            parseLineOfFile(lineOfFile);
            lineNumber++;
        } while (lineOfFile != null);
    }

    private void parseLineOfFile(String lineOfFile) {
        if (isLineCommented(lineOfFile)) {
            Instruction instruction = parseInstructionLine(lineOfFile);
            program.add(instruction);
        }
    }

    public boolean isLineCommented(String lineOfFile) {
        return !lineOfFile.startsWith(COMMENT_MARK);
    }

    private Instruction parseInstructionLine(String lineOfFile) {
        String instructionParams[] = lineOfFile.split(INSTRUCTION_PARAMS_SEPARATOR);
        int order = RobotProcessor.getInstructionByName(instructionParams[SECOND_ATTRIBUTE]);
        if (order == -1) {
            throw new IllegalArgumentException("Unknown order. Line:"+lineNumber);
        } else {
            InstructionLoader loader = instructionLoaders.get(order);
            return loader.getInstruction(order,instructionParams);
        }
    }

    private  Map<Integer,InstructionLoader> instructionLoaders = new HashMap<Integer, InstructionLoader>();

    {
        instructionLoaders.put(0,new ZeroArgumentInstructionLoader());
        instructionLoaders.put(1,new OneArgumentInstructionLoader());
        instructionLoaders.put(2,new TwoArgumentsInstructionLoader());
        instructionLoaders.put(2,new ThreeArgumentsInstructionLoader());
    }


    private abstract class InstructionLoader {
        protected Instruction instruction = new Instruction();

        public Instruction getInstruction(int order, String[] parameters) {
            instruction.setLabel(parseNumber(parameters[FIRST_ATTRIBUTE]));
            instruction.setLine(lineNumber);
            instruction.setRozkaz(order);
            loadParameters(parameters);
            return instruction;
        }

        public abstract void loadParameters(String[] parameters);

        protected int parseNumber(String s) {
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(buildExceptionMessage("Excepted number, was:'"+s+"'"),e);
            }
        }
        protected String buildExceptionMessage(String message) {
            return "Wrong line:"+instruction.getLine()+", order:"+instruction.getRozkaz()+". Message:"+message;
        }


    }

    private class ZeroArgumentInstructionLoader extends InstructionLoader {

        @Override
        public void loadParameters(String[] parameters) {
        }
    }

    private class OneArgumentInstructionLoader extends InstructionLoader {

        @Override
        public void loadParameters(String[] parameters) {
            String value1 = parameters[THIRD_ATTRIBUTE];
            instruction.setValue1(parseNumber(value1));
        }
    }

    private class TwoArgumentsInstructionLoader extends InstructionLoader {

        @Override
        public void loadParameters(String[] parameters) {
            String operationString = parameters[THIRD_ATTRIBUTE];
            int operation = RobotProcessor.getOperationByName(operationString);
            if (operation == -1) {
                throw new IllegalArgumentException(buildExceptionMessage("Excepted operation = or *"));
            }
            String value1String = parameters[FOURTH_ATTRIBUTE];
            int value1 = RobotProcessor.getMemoryObjectByName(value1String);

            instruction.setOperation(operation);
            instruction.setValue1(value1);
        }
    }

    private class ThreeArgumentsInstructionLoader extends TwoArgumentsInstructionLoader {

        @Override
        public void loadParameters(String[] parameters) {
            super.loadParameters(parameters);
            String value2String = parameters[FIFTH_ATTRIBUTE];
            int value2 = RobotProcessor.getMemoryObjectByName(value2String);
            instruction.setValue2(value2);

        }
    }
}