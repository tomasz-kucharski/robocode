package robotgame.loader.obj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author tomekk
 * @since 2010-11-16, 22:15:42
 */
public class OBJFileLoader {
    private static final Logger log = LoggerFactory.getLogger(OBJFileLoader.class);

    private LineParserFactory parserFactory = new LineParserFactory();

    private OBJModel model;

    public OBJFileLoader() {
        parserFactory.addNewLineParser(new VertexLineParser());
        parserFactory.addNewLineParser(new NormalLineParser());
        parserFactory.addNewLineParser(new FaceLineParser());
        parserFactory.addNewLineParser(new CommentLineParser());
    }

    public void load(BufferedReader objFile) {
        initialize();
        parseFile(objFile);
    }

    private void initialize() {
        model = new OBJModel();
        parserFactory.initializeParsers(model);
    }

    private void parseFile(BufferedReader objFile) {
        String currentLine = null;
        int lineNumber = 0;
        try {
            while((currentLine = objFile.readLine()) != null) {
                lineNumber++;
                LineParser lineParser = parserFactory.chooseParserForTheLine(currentLine);
                if (lineParser == null) {
                    log.info("Wrong line: "+lineNumber+":'"+currentLine+"'");
                } else {
                    lineParser.loadLine(currentLine);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error in line: "+lineNumber+":'"+currentLine+"'. Message:'"+e.getMessage()+"'",e);
        } finally {
            try {
                objFile.close();
            } catch (IOException e) {
                throw new RuntimeException("Cannot close source reader");
            }
        }
    }

    public OBJModel getModel() {
        return model;
    }
}
