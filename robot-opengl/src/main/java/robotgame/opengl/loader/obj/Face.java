package robotgame.opengl.loader.obj;

/**
 * @author tomekk
 * @since 2010-12-05, 00:23:31
 */
public class Face {

    public static final int NUMBER_OF_ELEMENTS = 3;

    private FaceElement faceElementX;
    private FaceElement faceElementY;
    private FaceElement faceElementZ;

    public FaceElement getFaceX() {
        return faceElementX;
    }

    public void setFaceX(FaceElement faceElementX) {
        this.faceElementX = faceElementX;
    }

    public FaceElement getFaceY() {
        return faceElementY;
    }

    public void setFaceY(FaceElement faceElementY) {
        this.faceElementY = faceElementY;
    }

    public FaceElement getFaceZ() {
        return faceElementZ;
    }

    public void setFaceZ(FaceElement faceElementZ) {
        this.faceElementZ = faceElementZ;
    }
}
