package robotgame.opengl.loader.obj;

/**
 * @author tomekk
 * @since 2010-11-16, 22:27:05
 */
public class FaceLineParser extends LineParser {

    @Override
    public boolean canParseThisLine(String currentLine) {
        return currentLine.startsWith("f");
    }

    @Override
    public void loadLine(String currentLine) {
        String[] fragments = currentLine.split(" ");

        Face[] faces = new Face[fragments.length-1];
        for (int i=1; i<fragments.length; i++) {
            Face face = new Face();
            String[] faceParameters = fragments[i].split("/");
            face.setVertexIndex(Integer.parseInt(faceParameters[0]));
            if (!"".equals(faceParameters[1])) {
                face.setTextureCoordinateIndex(Integer.parseInt(faceParameters[1]));
            }
            faces[i-1] = face;
        }
        float vertexX = Float.parseFloat(fragments[1]);
        float vertexY = Float.parseFloat(fragments[2]);
        float vertexZ = Float.parseFloat(fragments[3]);
        model.addVertex(vertexX, vertexY,vertexZ);
    }
}
