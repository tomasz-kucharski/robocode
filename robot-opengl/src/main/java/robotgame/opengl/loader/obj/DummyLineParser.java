package robotgame.opengl.loader.obj;

/**
 * @author tomekk
 * @since 2010-11-16, 22:27:05
 */
public class DummyLineParser extends LineParser {

    @Override
    public boolean canParseThisLine(String currentLine) {
        return true;
    }

    @Override
    public void loadLine(String currentLine) {
        System.out.println("Unknown line:'"+currentLine+"'");
    }
}
