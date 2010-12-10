package robotgame.loader.obj;

/**
 * @author tomekk
 * @since 2010-12-08, 14:55:47
 */
public class FaceElementVertexTextureNormal extends FaceElementVertexNormal {

    protected short texture;

    public void setTexture(short texture) {
        this.texture = texture;
    }

    @Override
    public short[] toArray() {
        return new short[]{vertex,texture,normal};
    }
}
