package robotgame.loader.obj;

/**
 * @author tomekk
 * @since 2010-12-03, 00:05:26
 */
public enum FaceType {
    VERTEX(1), VERTEX_TEXTURE(2), VERTEX_NORMAL(2), VERTEX_TEXTURE_NORMAL(3);

    private int numberOfElements;

    FaceType(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }
}
