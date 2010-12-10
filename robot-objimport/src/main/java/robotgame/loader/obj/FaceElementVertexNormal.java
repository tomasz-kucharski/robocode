package robotgame.loader.obj;

/**
 * @author tomekk
 * @since 2010-12-08, 14:55:47
 */
public class FaceElementVertexNormal extends FaceElementVertexOnly {

    protected short normal;

    public void setNormal(short normal) {
        this.normal = normal;
    }

    @Override
    public short[] toArray() {
//        return new short[]{vertex,normal};
        return new short[]{vertex};
    }

    public short getNormal() {
        return normal;
    }
}
