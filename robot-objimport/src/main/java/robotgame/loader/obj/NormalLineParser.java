package robotgame.loader.obj;

/**
 * @author tomekk
 * @since 2010-12-02, 21:59:05
 */
public class NormalLineParser extends LineParser {

    @Override
    protected String getLinePrefix() {
        return "vn";
    }

    @Override
    public void loadLine(String currentLine) {
        String[] fragments = currentLine.split(" ");
        Vertex vertex = new Vertex();
        vertex.setX(Float.parseFloat(fragments[1]));
        vertex.setY(Float.parseFloat(fragments[2]));
        vertex.setZ(Float.parseFloat(fragments[3]));
        model.getCurrentGroup().addNormal(vertex);
    }
}
