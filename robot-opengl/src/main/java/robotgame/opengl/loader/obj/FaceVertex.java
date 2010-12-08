package robotgame.opengl.loader.obj;

/**
 * @author tomekk
 * @since 2010-12-05, 00:23:31
 */
public class FaceVertex {
    private Face faceX;
    private Face faceY;
    private Face faceZ;

    public Face getFaceX() {
        return faceX;
    }

    public void setFaceX(Face faceX) {
        this.faceX = faceX;
    }

    public Face getFaceY() {
        return faceY;
    }

    public void setFaceY(Face faceY) {
        this.faceY = faceY;
    }

    public Face getFaceZ() {
        return faceZ;
    }

    public void setFaceZ(Face faceZ) {
        this.faceZ = faceZ;
    }
}
