package robotgame.opengl.loader.obj;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tomekk
 * @since 2010-11-16, 22:30:24
 */
public class OBJModel {

    private List<Float> vertices = new ArrayList<Float>();

    public void addVertex(float vertexX, float vertexY, float vertexZ) {
        vertices.add(vertexX);
        vertices.add(vertexY);
        vertices.add(vertexZ);
    }
}
