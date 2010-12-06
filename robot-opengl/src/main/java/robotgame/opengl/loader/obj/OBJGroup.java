package robotgame.opengl.loader.obj;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tomekk
 * @since 2010-11-24, 00:26:40
 */
public class OBJGroup {

    private Material material;
                                                                                                                                             
    private List<Vertex> vertices = new ArrayList<Vertex>();
    private List<Vertex> normals = new ArrayList<Vertex>();
    private List<IndexVertex> indices = new ArrayList<IndexVertex>();

    private OBJIndexType indexType;

    public void setIndexType(OBJIndexType indexType) {
        this.indexType = indexType;
    }

    public void addVertex(Vertex vertex) {
        vertices.add(vertex);
    }

    public void addNormal(Vertex normal) {
        normals.add(normal);
    }
    
    public void addIndexf3G4HRN(IndexVertex index) {
        indices.add(index);
    }


    private FloatBuffer loadToBuffer(float[] intArray) {
        ByteBuffer tbb = ByteBuffer.allocateDirect(intArray.length * Float.SIZE/8);
        tbb.order(ByteOrder.nativeOrder());
        FloatBuffer buffer = tbb.asFloatBuffer();

        buffer.put(intArray);
        buffer.position(0);
        return buffer;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

}
