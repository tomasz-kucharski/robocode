package robotgame.opengl.loader.obj;

/**
 * @author tomekk
 * @since 2010-11-16, 22:22:23
 */
public abstract class LineParser {

    protected OBJModel model;

    public void setModel(OBJModel model) {
        this.model = model;
    }

    public abstract boolean canParseThisLine(String currentLine);

    public abstract void loadLine(String currentLine);
}
