package robotgame.loader.obj;

/**
 * @author tomekk
 * @since 2010-12-08, 14:55:47
 */
public class FaceElementVertexOnly extends FaceElement {

    protected short vertex;

    public void setVertex(short vertex) {
        this.vertex = vertex;
    }

    @Override
    public short[] toArray() {
        return new short[]{vertex};
    }
}
