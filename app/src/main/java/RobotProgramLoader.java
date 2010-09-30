import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class RobotProgramLoader {
    private static final Logger log = LoggerFactory.getLogger(RobotProgramLoader.class);

    private  int size;
    private BufferedReader in;
    private String token;
    private ProgramList program;
    private Instruction instruction;
    private static final String COMMENT_MARK = "#";
    private static final String INSTRUCTION_PARAMS_SEPARATOR = "\t";

    RobotProgramLoader(File fileName, ProgramList program) throws FileNotFoundException {
        size = 10;
        in = new BufferedReader(new FileReader(fileName));
        this.program = program;
        instruction = null;
//        loadProgram();
        program.setToFirst();
        while ((instruction = program.getInstruction()) != null)
            log.debug("Instrukcja : {} {} {} {} {}",new Object[]{instruction.getLine(),instruction.getRozkaz(),instruction.getOperation(),instruction.getValue1(),instruction.getValue2()});
        program.setToFirst();
    }

//    void getToken()
//    {
//        char c;
//        while(true)
//        {
//            if(in.get(c))
//            {
//                if(!isspace(c)) //BIALY ZNAK
//                    break;
//            }
//            else
//            { //KONIEC PLIKU
//                token[0] = 0;
//                return;
//            }
//        }
//
//        if( c == '#' )
//        {
//            eatAll();
//            token[0] = '#';
//            return;
//        }
//        in.putback(c); // ZWROC CO POBRALES
//
//        for(int i=0; i<size; i++)
//        {
//            if((in.get(c) == false) || isspace(c))
//            {
//                in.putback(c);
//                token[i] = 0;					// KONIEC STRING-a
//                break;
//            }
//            token[i] = c;
//        }
//    }

//    void eatAll()
//    {
//        char c;
//        while(in.get(c))
//            if(c=='\n')
//                break;
//    }

    public void loadFile() throws IOException {
        String lineOfFile = null;
        do {
            lineOfFile = in.readLine();
            parseLineOfFile(lineOfFile);
        } while (lineOfFile != null);
    }

    private void parseLineOfFile(String lineOfFile) {
        if (!lineOfFile.startsWith(COMMENT_MARK)) {
            parseInstructionLine(lineOfFile);
        }
    }

    private void parseInstructionLine(String lineOfFile) {
        Instruction instruction = new Instruction();
        String instructionParams[] = lineOfFile.split(INSTRUCTION_PARAMS_SEPARATOR);
        instruction.setLabel(Integer.parseInt(instructionParams[0]));

    }
//
//    boolean loadProgram()
//    {
//        int type;
//
//        char c;
//
//        int line = 0;
//        int label;
//        int rozkaz;
//        int operation;
//        int value1;
//        int value2;
//
//        while(true)
//        {
//            line++;
//            if(in.get(c) == null)
//                break;
//            if ( c == '#' )
//            {
//                eatAll();
//                continue;
//            }
//            else
//                in.putback(c);
//
//            if(!checkChar())
//                in >> label;
//            else
//                label = 0;
//
//            getToken();
//            rozkaz = RobotProcessor.getInstructionByName(String.valueOf(token));
//            if (rozkaz == -1)
//            {
//                log.debug("LINE %d : ZLY ROZKAZ\n",line);
//                return false;
//            }
//
//            type = RobotProcessor.getTypeOfInstruction(rozkaz);
//            if ( type == -1)
//            {
//                log.debug("LINE %d : ZLY TYP ROZKAZU\n",line);
//                return false;
//            }
//
//            switch(type)
//            {
//                case 0:
//                    instruction = new Instruction(line,label,rozkaz);
//                    break;
//
//                case 1:
//                    if (!checkChar())
//                    {
//                        in.putback(c);
//                        in >> value1;
//                        instruction = new Instruction(line,label,rozkaz,value1);
//                    }
//                    else
//                    {
//                        log.debug("LINE %d : OPEARACJE NIEDOSTEPNE DLA ROZKAZU\n",line);
//                        return false;
//                    }
//                    break;
//                case 2:
//                    getToken();
//                    operation = RobotProcessor.getOperationByName(token);
//                    if(operation == -1)
//                    {
//                        log.debug("LINE %d : ZLA OPERACJA\n",line);
//                        return false;
//                    }
//                    if(checkChar())
//                    {
//                        if (operation == RobotProcessor.OPADR)
//                        {
//                            log.debug("LINE %d : OPERACJA * NIEDOSTEPNA DLA OBIEKTOW\n",line);
//                            return false;
//                        }
//                        getToken();
//                        value1 = RobotProcessor.getMemoryObjectByName(token);
//                        if(value1 == -1)
//                        {
//                            log.debug("LINE %d : ZLY OBIEKT\n",line);
//                            return false;
//                        }
//
//                    }
//                    else
//                    {
//                        in >> value1;
//                    }
//                    instruction = new Instruction(line,label,rozkaz,operation,value1);
//                    break;
//                case 3:
//                    getToken();
//                    operation = RobotProcessor.getOperationByName(token);
//                    if(operation == -1)
//                    {
//                        log.debug("LINE %d : ZLA OPERACJA\n",line);
//                        return false;
//                    }
//                    if(checkChar())
//                    {
//                        if (operation == RobotProcessor.OPADR)
//                        {
//                            log.debug("LINE %d : OPERACJA * NIEDOSTEPNA DLA OBIEKTOW\n",line);
//                            return false;
//                        }
//                        getToken();
//                        value1 = RobotProcessor.getMemoryObjectByName(token);
//                        if(value1 == -1)
//                        {
//                            log.debug("LINE %d : ZLY OBIEKT\n",line);
//                            return false;
//                        }
//
//                    }
//                    else
//                    {
//                        in >> value1;
//                    }
//                    in >> value2;
//                    instruction = new Instruction(line,label,rozkaz,operation,value1,value2);
//                    break;
//            }
//            eatAll();
//            program.add(instruction,true);
//            log.debug("LINE:%d, LABEL:%d, ROZKAZ:%d, VALUE1:%d, VALUE2:%d\n",line,label,rozkaz,value1,value2);
//        }
//        return true;
//    }
}