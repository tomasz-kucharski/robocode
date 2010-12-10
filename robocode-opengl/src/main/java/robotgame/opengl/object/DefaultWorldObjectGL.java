package robotgame.opengl.object;

import robotgame.loader.TextureLoader;
import robotgame.object.WorldObject;
import robotgame.object.WorldObjectRenderer;

import javax.microedition.khronos.opengles.GL10;

public class DefaultWorldObjectGL implements WorldObjectRenderer {

    protected GL10 gl;
    protected TextureLoader textureLoader;

    protected CubeGL cube;
    protected String textureKey;


    public DefaultWorldObjectGL(String textureKey, float scale) {
        this.textureKey = textureKey;
        cube = new CubeGL(scale);
    }

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
        textureLoader.initTexture(textureKey);
        cube = new CubeGL(0.8f);
    }

    @Override
    public void draw(WorldObject object) {
        gl.glPushMatrix();
        gl.glTranslatef(0f,0f,0.4f);
        cube.draw(gl,textureLoader.getTexture(textureKey));
        gl.glPopMatrix();

    }
}