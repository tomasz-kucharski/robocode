package robotgame.opengl.loader.obj;

/**
 * @author tomekk
 * @since 2010-11-16, 22:27:05
 */
public class FaceLineParser extends LineParser {
    public static final int VERTEX_INDEX = 0;
    public static final int TEXTURE_INDEX = 1;
    public static final int NORMAL_INDEX = 2;
    public static final int NUMBER_OF_FACES_PLUS_LINE_PREFIX = 4;

    @Override
    protected String getLinePrefix() {
        return "f";
    }

    @Override
    public void loadLine(String currentLine) throws InvalidLineException {
        String[] mainFragments = currentLine.split(" ");
        if (mainFragments.length != NUMBER_OF_FACES_PLUS_LINE_PREFIX) {
            throw new InvalidLineException("Expected number of faces:"+ NUMBER_OF_FACES_PLUS_LINE_PREFIX +", was: "+mainFragments.length);
        }
        Face face = new Face();
        face.setFaceX(parseSingleIndexItem(mainFragments[1]));
        face.setFaceY(parseSingleIndexItem(mainFragments[2]));
        face.setFaceZ(parseSingleIndexItem(mainFragments[3]));
        model.getCurrentGroup().addFaceVertex(face);
    }

    public FaceElement parseSingleIndexItem(String mainFragment) {
        String[] indexValues = mainFragment.split("/");
        if (isParameterProvided(indexValues,NORMAL_INDEX)) {
            if (isParameterProvided(indexValues,TEXTURE_INDEX)) {
                FaceElementVertexTextureNormal faceElement = new FaceElementVertexTextureNormal();
                faceElement.setVertex(decodeIndexValue(indexValues[VERTEX_INDEX]));
                faceElement.setNormal(decodeIndexValue(indexValues[NORMAL_INDEX]));
                faceElement.setTexture(decodeIndexValue(indexValues[TEXTURE_INDEX]));
                return faceElement;
            } else {
                FaceElementVertexNormal faceElement = new FaceElementVertexNormal();
                faceElement.setVertex(decodeIndexValue(indexValues[VERTEX_INDEX]));
                faceElement.setNormal(decodeIndexValue(indexValues[NORMAL_INDEX]));
                return faceElement;
            }
        } else {
            FaceElementVertexOnly faceElement = new FaceElementVertexOnly();
            faceElement.setVertex(decodeIndexValue(indexValues[VERTEX_INDEX]));
            return faceElement;
        }
    }

    private int decodeIndexValue(String indexValue) {
        // values in obj files start from 1 instead of 0
        return Integer.parseInt(indexValue) - 1;
    }

    private boolean isParameterProvided(String[] indexValue, int index) {
        return indexValue.length > index && indexValue[index] != null && !indexValue[index].isEmpty();
    }
}
