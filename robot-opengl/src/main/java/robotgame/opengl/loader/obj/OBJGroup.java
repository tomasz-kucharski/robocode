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
    private List<FaceVertex> faces = new ArrayList<FaceVertex>();

    private FaceType faceType;
    public static final int VERTEX_ELEMENTS = 3;

    public void setFaceType(FaceType faceType) {
        this.faceType = faceType;
    }

    public void addVertex(Vertex vertex) {
        vertices.add(vertex);
    }

    public void addNormal(Vertex normal) {
        normals.add(normal);
    }
    
    public void addFaceVertex(FaceVertex face) {
        setUnifiedFaceType(face);
        faces.add(face);
    }

    private void setUnifiedFaceType(FaceVertex face) {
        if (faces.size() == 0) {
            this.faceType = face.getFaceX().getType();
        }
    }

    private FloatBuffer initFloatBuffer(int bufferSize) {
        ByteBuffer tbb = ByteBuffer.allocateDirect(bufferSize * Float.SIZE/8);
        tbb.order(ByteOrder.nativeOrder());
        FloatBuffer buffer = tbb.asFloatBuffer();
        return buffer;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public FloatBuffer getVertexBuffer() {
        FloatBuffer floatBuffer = initFloatBuffer(vertices.size() * VERTEX_ELEMENTS);
        for (Vertex vertex : vertices) {
            floatBuffer.put(vertex.getX());
            floatBuffer.put(vertex.getY());
            floatBuffer.put(vertex.getZ());
        }
        floatBuffer.position(0);
        return floatBuffer;
    }

    public FloatBuffer getNormalBuffer() {
        FloatBuffer floatBuffer = initFloatBuffer(normals.size() * VERTEX_ELEMENTS);
        for (Vertex vertex : normals) {
            floatBuffer.put(vertex.getX());
            floatBuffer.put(vertex.getY());
            floatBuffer.put(vertex.getZ());
        }
        floatBuffer.position(0);
        return floatBuffer;
    }

    public ByteBuffer getFacesBuffer() {
        ByteBuffer buffer = ByteBuffer.allocateDirect(faces.size() * VERTEX_ELEMENTS * faceType.getNumberOfElements());
        for (FaceVertex faceVertex : faces) {
        }
        buffer.position(0);
        return buffer;
    }
}
