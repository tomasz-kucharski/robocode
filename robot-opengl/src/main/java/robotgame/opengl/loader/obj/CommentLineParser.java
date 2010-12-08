package robotgame.opengl.loader.obj;

/**
 * @author tomekk
 * @since 2010-11-16, 22:27:05
 */
public class CommentLineParser extends LineParser {

    @Override
    protected String getLinePrefix() {
        return "#";
    }

    @Override
    public void loadLine(String currentLine) {
    }
}
