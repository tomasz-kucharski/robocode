package robotgame.object;

import robotgame.loader.TextureLoader;

import javax.media.opengl.GL;
import java.nio.FloatBuffer;

public class FloorGL  implements WorldObjectRenderer {

    private int list = 50;

    private GL gl;
    private static final String GRASS = "grass";
    private TextureLoader textureLoader;
    private TileGL tile;

    @Override
    public void setTextureLoader(TextureLoader textureLoader) {
        this.textureLoader = textureLoader;
    }

    @Override
    public void setGraphicsContext(Object context) {
        gl = (GL) context;
    }

    @Override
    public void init() {
        textureLoader.initTexture(GRASS);

        tile = new TileGL(0.95f);

        FloatBuffer dechaAmb = FloatBuffer.wrap(new float[]{1.0f,1.0f,1.0f,1.0f});
        FloatBuffer dechaDif = FloatBuffer.wrap(new float[]{1.0f,1.0f,1.0f,1.0f});
        FloatBuffer dechaSpc = FloatBuffer.wrap(new float[]{1.0f,1.0f,1.0f,1.0f});
        FloatBuffer dechaShn = FloatBuffer.wrap(new float[]{128.000f});

        tile.setDechaAmb(dechaAmb);
        tile.setDechaDif(dechaDif);
        tile.setDechaShn(dechaShn);
        tile.setDechaSpc(dechaSpc);

    }

    @Override
    public void draw(WorldObject object) {
        gl.glTranslatef(0,0,.4f);
        tile.draw(gl,textureLoader.getTexture(GRASS));
        gl.glTranslatef(0,0,-.4f);
    }
}