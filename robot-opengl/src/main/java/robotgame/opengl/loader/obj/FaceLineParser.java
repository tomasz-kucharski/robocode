package robotgame.opengl.loader.obj;

/**
 * @author tomekk
 * @since 2010-11-16, 22:27:05
 */
public class FaceLineParser extends LineParser {
    public static final int VERTEX_INDEX = 0;
    public static final int TEXTURE_INDEX = 1;
    public static final int NORMAL_INDEX = 2;
    public static final int NUMBER_OF_FACES = 3;

    @Override
    public boolean canParseThisLine(String currentLine) {
        return currentLine.startsWith("f");
    }

    @Override
    public void loadLine(String currentLine) throws InvalidLineException {
        String[] mainFragments = currentLine.split(" ");
        if (mainFragments.length != NUMBER_OF_FACES) {
            throw new InvalidLineException("Expected number of faces:"+NUMBER_OF_FACES+", was: "+mainFragments.length);
        }
        FaceVertex faceVertex = new FaceVertex();
        faceVertex.setFaceX(parseSingleIndexItem(mainFragments[0]));
        faceVertex.setFaceY(parseSingleIndexItem(mainFragments[1]));
        faceVertex.setFaceZ(parseSingleIndexItem(mainFragments[2]));
        model.getCurrentGroup().addFaceVertex(faceVertex);
    }

    private Face parseSingleIndexItem(String mainFragment) {
        String[] indexValues = mainFragment.split("/");
        Face face = new Face();
        face.setVertex(parseNumber(indexValues[VERTEX_INDEX]));
        face.setVertex(parseNumber(indexValues[TEXTURE_INDEX]));
        face.setNormal(parseNumber(indexValues[NORMAL_INDEX]));
        return face;
    }

    private Integer parseNumber(String value) {
        if (value == null) {
            return null;
        } else {
            return Integer.parseInt(value);
        }
    }
}
