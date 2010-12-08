package robotgame.opengl.loader.obj;

/**
 * @author tomekk
 * @since 2010-12-08, 14:55:47
 */
public class FaceElementVertexTextureNormal extends FaceElementVertexNormal {

    protected int texture;

    public void setTexture(int texture) {
        this.texture = texture;
    }

    @Override
    public int[] toArray() {
        return new int[]{vertex,texture,normal};
    }
}
