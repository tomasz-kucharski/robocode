package robotgame.opengl.loader.obj;

/**
 * @author tomekk
 * @since 2010-12-08, 14:55:47
 */
public class FaceElementVertexNormal extends FaceElementVertexOnly {

    protected int normal;

    public void setNormal(int normal) {
        this.normal = normal;
    }

    @Override
    public int[] toArray() {
        return new int[]{vertex,normal};
    }
}
