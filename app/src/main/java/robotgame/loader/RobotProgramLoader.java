package robotgame.loader;

import robotgame.object.robot.ProgramList;
import robotgame.object.robot.RobotMemoryObject;
import robotgame.object.robot.logic.Instruction;
import robotgame.object.robot.logic.InstructionFactory;
import robotgame.object.robot.logic.InstructionOperator;
import robotgame.object.robot.logic.Order;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class
        RobotProgramLoader {

    public static final int FIRST_ATTRIBUTE = 0;
    public static final int SECOND_ATTRIBUTE = 1;
    public static final int THIRD_ATTRIBUTE = 2;
    public static final int FOURTH_ATTRIBUTE = 3;
    private static final int FIFTH_ATTRIBUTE = 4;

    private BufferedReader reader;
    private ProgramList program;
    private static final String COMMENT_MARK = "#";
    private static final String INSTRUCTION_PARAMS_SEPARATOR = "\t";


    private int lineNumber=0;

    private InstructionFactory factory = new InstructionFactory();

    public RobotProgramLoader(BufferedReader programReader) throws IOException {
        this(programReader,new ProgramList());
    }

    public RobotProgramLoader(BufferedReader programFile, ProgramList program) throws IOException {
        this.program = program;
        this.reader = programFile;
    }

    public ProgramList getProgram() {
        return program;
    }

    public void loadProgram() throws IOException {
        String lineOfFile = reader.readLine();
        while (lineOfFile != null) {
            parseLineOfFile(lineOfFile);

            lineNumber++;
            lineOfFile = reader.readLine();
        }
    }

    private void parseLineOfFile(String lineOfFile) {
        if (!isLineCommented(lineOfFile)) {
            Instruction instruction = parseInstructionLine(lineOfFile);
            program.addInstruction(instruction);
        }
    }

    public boolean isLineCommented(String lineOfFile) {
        return lineOfFile.startsWith(COMMENT_MARK);
    }

    private Instruction parseInstructionLine(String lineOfFile) {
        String instructionParams[] = lineOfFile.split(INSTRUCTION_PARAMS_SEPARATOR);
        Order order = Order.valueOf(instructionParams[SECOND_ATTRIBUTE]);
        try {
            InstructionLoader loader = instructionLoaders.get(order.getNumberOfArguments());
            return loader.getInstruction(order,instructionParams);
        } catch (RuntimeException e) {
            System.out.println("WRONG LINE:"+lineNumber+"LINE:'"+lineOfFile+"'");
            System.out.println(lineNumber);
            throw e;
        }
    }

    private  Map<Integer,InstructionLoader> instructionLoaders = new HashMap<Integer, InstructionLoader>();

    {
        instructionLoaders.put(0,new ZeroArgumentInstructionLoader());
        instructionLoaders.put(1,new OneArgumentInstructionLoader());
        instructionLoaders.put(2,new TwoArgumentsInstructionLoader());
        instructionLoaders.put(3,new ThreeArgumentsInstructionLoader());
    }


    private abstract class InstructionLoader {
        protected Instruction instruction;

        public Instruction getInstruction(Order order, String[] parameters) {
            instruction = factory.createInstruction(order);

            instruction.setLabel(parseNumber(parameters[FIRST_ATTRIBUTE]));
            instruction.setLine(lineNumber);
            instruction.setOrder(order);
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
            return "Wrong line:"+instruction.getLine()+", order:"+instruction.getOrder()+". Message:"+message;
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
            InstructionOperator operation = InstructionOperator.getInstructionOperatorByNotation(operationString);
            if (operation == null) {
                throw new IllegalArgumentException(buildExceptionMessage("Excepted operations: "+ Arrays.toString(InstructionOperator.values())));
            }
            instruction.setOperation(operation);


            String value1String = parameters[FOURTH_ATTRIBUTE];
            if (instruction.getOrder() == Order.RAND) {
                instruction.setValue1(parseNumber(value1String));
            } else {
                instruction.setValue1(RobotMemoryObject.valueOf(value1String).ordinal());
            }
        }
    }

    private class ThreeArgumentsInstructionLoader extends TwoArgumentsInstructionLoader {

        @Override
        public void loadParameters(String[] parameters) {
            super.loadParameters(parameters);
            String value2String = parameters[FIFTH_ATTRIBUTE];
            int value2 = parseNumber(value2String);
            instruction.setValue2(value2);

        }
    }
}