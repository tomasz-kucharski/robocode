package robotgame.loader.obj;

/**
 * @author tomekk
 * @since 2010-11-16, 22:22:23
 */
public abstract class LineParser {

    protected OBJModel model;

    public void setModel(OBJModel model) {
        this.model = model;
    }

    public boolean canParseThisLine(String currentLine) {
        String[] fragments = currentLine.split(" ");
        return getLinePrefix().equals(fragments[0]);
    }

    protected abstract String getLinePrefix();

    public abstract void loadLine(String currentLine) throws InvalidLineException;
}
