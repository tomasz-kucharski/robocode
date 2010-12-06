package robotgame.opengl.loader.obj;

/**
 * @author tomekk
 * @since 2010-11-16, 22:27:05
 */
public class FaceLineParser extends LineParser {
    public static final int VERTEX_INDEX = 0;
    public static final int TEXTURE_INDEX = 1;
    public static final int NORMAL_INDEX = 2;

    @Override
    public boolean canParseThisLine(String currentLine) {
        return currentLine.startsWith("f");
    }

    @Override
    public void loadLine(String currentLine) {
        String[] mainFragments = currentLine.split(" ");
        for (int i= TEXTURE_INDEX; i<mainFragments.length; i++) {
            parseSingleIndexItem(mainFragments[i]);
            if (i == TEXTURE_INDEX) {
                setFaceType();
            }
        }
    }

    private void setFaceType() {
        //To change body of created methods use File | Settings | File Templates.
    }

    private void parseSingleIndexItem(String mainFragment) {
        String[] indexValues = mainFragment.split("/");
        OBJIndex objIndex = new OBJIndex();
        objIndex.setVertex(parseNumber(indexValues[VERTEX_INDEX]));
        objIndex.setVertex(parseNumber(indexValues[TEXTURE_INDEX]));
        objIndex.setNormal(parseNumber(indexValues[NORMAL_INDEX]));
    }

    private Integer parseNumber(String value) {
        if (value == null) {
            return null;
        } else {
            return Integer.parseInt(value);
        }
    }
}
