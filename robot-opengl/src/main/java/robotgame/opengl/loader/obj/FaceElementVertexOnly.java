package robotgame.opengl.loader.obj;

/**
 * @author tomekk
 * @since 2010-12-08, 14:55:47
 */
public class FaceElementVertexOnly extends FaceElement {

    protected int vertex;

    public void setVertex(int vertex) {
        this.vertex = vertex;
    }

    @Override
    public int[] toArray() {
        return new int[]{vertex};
    }
}
