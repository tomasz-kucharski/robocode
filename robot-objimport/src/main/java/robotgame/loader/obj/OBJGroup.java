package robotgame.loader.obj;

import java.nio.*;
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
    private List<Face> faces = new ArrayList<Face>();

    public void addVertex(Vertex vertex) {
        vertices.add(vertex);
    }

    public void addNormal(Vertex normal) {
        normals.add(normal);
    }

    public void addFaceVertex(Face face) {
        faces.add(face);
    }

    private FloatBuffer initFloatBuffer(int bufferSize) {
        ByteBuffer tbb = ByteBuffer.allocateDirect(bufferSize * Float.SIZE/8);
        tbb.order(ByteOrder.nativeOrder());
        return tbb.asFloatBuffer();
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public FloatBuffer getVertexBuffer() {
        FloatBuffer floatBuffer = initFloatBuffer(vertices.size() * Vertex.NUMBER_OF_FLOATS);
        int i=0;
        for (Vertex vertex : vertices) {
            floatBuffer.put(vertex.getX());
            floatBuffer.put(vertex.getY());
            floatBuffer.put(vertex.getZ());
        }
        floatBuffer.position(0);
        return floatBuffer;
    }

    public FloatBuffer getNormalBuffer() {
        FloatBuffer floatBuffer = initFloatBuffer(normals.size() * Vertex.NUMBER_OF_FLOATS);
        for (Vertex vertex : normals) {
            floatBuffer.put(vertex.getX());
            floatBuffer.put(vertex.getY());
            floatBuffer.put(vertex.getZ());
        }
        floatBuffer.position(0);
        return floatBuffer;
    }

    public ShortBuffer getFaceBuffer() {
        if (faces.size() ==0) {
            return null;
        }
        ByteBuffer tbb = ByteBuffer.allocateDirect(faces.size() * Face.NUMBER_OF_ELEMENTS * getFaceElementLengthInBytes());
        tbb.order(ByteOrder.nativeOrder());
        ShortBuffer buffer = tbb.asShortBuffer();
        for (Face face : faces) {
            buffer.put(face.getFaceX().toArray());
            buffer.put(face.getFaceY().toArray());
            buffer.put(face.getFaceZ().toArray());
        }
        buffer.position(0);
        return buffer;
    }

    private int getFaceElementLengthInBytes() {
        return faces.get(0).getFaceX().toArray().length * Short.SIZE/8;
    }
}
