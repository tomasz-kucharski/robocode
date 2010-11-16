package robotgame.opengl.loader.obj;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author tomekk
 * @since 2010-11-16, 22:15:42
 */
public class OBJFileLoader {

    private LineParserFactory parserFactory;

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
        String currentLine;
        try {
            while((currentLine = objFile.readLine()) != null) {
                parseLine(currentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            objFile.close();
        }
    }

    private void parseLine(String currentLine) {
        LineParser lineParser = parserFactory.chooseParserForTheLine(currentLine);
        lineParser.loadLine(currentLine);
    }
}
