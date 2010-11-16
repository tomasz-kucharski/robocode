package robotgame.opengl.loader.obj;

/**
 * @author tomekk
 * @since 2010-11-16, 22:27:05
 */
public class VertexLineParser extends LineParser {

    @Override
    public boolean canParseThisLine(String currentLine) {
        return currentLine.startsWith("v");
    }

    @Override
    public void loadLine(String currentLine) {
        String[] fragments = currentLine.split(" ");
        float vertexX = Float.parseFloat(fragments[1]);
        float vertexY = Float.parseFloat(fragments[2]);
        float vertexZ = Float.parseFloat(fragments[3]);
        model.addVertex(vertexX, vertexY,vertexZ);
    }
}
