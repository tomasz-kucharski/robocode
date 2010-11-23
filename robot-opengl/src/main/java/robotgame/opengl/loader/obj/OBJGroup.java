package robotgame.opengl.loader.obj;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tomekk
 * @since 2010-11-24, 00:26:40
 */
public class OBJGroup {

    private Material material;
    private List<Float> vertices = new ArrayList<Float>();

    public void addVertex(float vertexX, float vertexY, float vertexZ) {
        vertices.add(vertexX);
        vertices.add(vertexY);
        vertices.add(vertexZ);
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}
