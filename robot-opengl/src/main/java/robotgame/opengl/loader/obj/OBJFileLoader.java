package robotgame.opengl.loader.obj;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author tomekk
 * @since 2010-11-16, 22:15:42
 */
public class OBJFileLoader {

    private LineParserFactory parserFactory = new LineParserFactory();
    {
        parserFactory.addNewLineParser(new VertexLineParser());
        parserFactory.addNewLineParser(new NormalLineParser());
        parserFactory.addNewLineParser(new FaceLineParser());
    }

    private OBJModel model;

    public void load(BufferedReader objFile) {
        initialize();
        try {
            parseFile(objFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initialize() {
        model = new OBJModel();
        parserFactory.initializeParsers(model);
    }

    private void parseFile(BufferedReader objFile) throws IOException {
        String currentLine = null;
        int lineNumber = 0;
        try {
            while((currentLine = objFile.readLine()) != null) {
                lineNumber++;
                parseLine(currentLine);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error in line: "+lineNumber+":'"+currentLine+"'. Message:'"+e.getMessage()+"'",e);
        } finally {
            objFile.close();
        }
    }

    private void parseLine(String currentLine) throws InvalidLineException {
        LineParser lineParser = parserFactory.chooseParserForTheLine(currentLine);
        lineParser.loadLine(currentLine);
    }

    public OBJModel getModel() {
        return model;
    }
}
