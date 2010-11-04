package robotgame.opengl.object;

import robotgame.loader.TextureLoader;
import robotgame.object.WorldObject;
import robotgame.object.WorldObjectRenderer;

import javax.microedition.khronos.opengles.GL10;
import java.nio.FloatBuffer;

public class FloorGL implements WorldObjectRenderer {

    private GL10 gl;
    private static final String floor = "Floor";
    private TextureLoader textureLoader;
    private TileGL tile;

    @Override
    public void setTextureLoader(TextureLoader textureLoader) {
        this.textureLoader = textureLoader;
    }

    @Override
    public void setGraphicsContext(Object context) {
        gl = (GL10) context;
    }

    @Override
    public void init() {
        textureLoader.initTexture(floor);

        tile = new TileGL(0.95f);

        FloatBuffer dechaAmb = FloatBuffer.wrap(new float[]{.5f,.5f,.5f,.5f});
        FloatBuffer dechaDif = FloatBuffer.wrap(new float[]{.5f,.5f,.5f,.5f});
        FloatBuffer dechaSpc = FloatBuffer.wrap(new float[]{.5f,.5f,.5f,.5f});
        FloatBuffer dechaShn = FloatBuffer.wrap(new float[]{128.000f});

        tile.setDechaAmb(dechaAmb);
        tile.setDechaDif(dechaDif);
        tile.setDechaShn(dechaShn);
        tile.setDechaSpc(dechaSpc);

    }

    @Override
    public void draw(WorldObject object) {
        gl.glTranslatef(0,0,.4f);
        tile.draw(gl,textureLoader.getTexture(floor));
        gl.glTranslatef(0,0,-.4f);
    }
}