package robotgame.opengl.loader.obj;

/**
 * @author tomekk
 * @since 2010-11-16, 22:27:05
 */
public class VertexLineParser extends LineParser {

    @Override
    protected String getLinePrefix() {
        return "v";
    }

    @Override
    public void loadLine(String currentLine) {
        String[] fragments = currentLine.split(" ");
        Vertex vertex = new Vertex();
        vertex.setX(Float.parseFloat(fragments[1]));
        vertex.setY(Float.parseFloat(fragments[2]));
        vertex.setZ(Float.parseFloat(fragments[3]));
        model.getCurrentGroup().addVertex(vertex);
    }
}
