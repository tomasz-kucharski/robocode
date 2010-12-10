package robotgame.loader.obj;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author tomekk
 * @since 2010-11-16, 22:20:52
 */
public class LineParserFactory {
    private Set<LineParser> parsers = new LinkedHashSet<LineParser>();

    public void addNewLineParser(LineParser parser) {
        this.parsers.add(parser);
    }

    public void initializeParsers(OBJModel model) {
        for (LineParser parser : parsers) {
            parser.setModel(model);
        }
    }

    public LineParser chooseParserForTheLine(String currentLine) {
        for (LineParser parser : parsers) {
            if (parser.canParseThisLine(currentLine)) {
                return parser;
            }
        }
        return null;
    }
}
