package robotgame.object;

import robotgame.loader.TextureLoader;

import javax.media.opengl.GL;

public class DepotGL implements WorldObjectRenderer {

    private GL gl;
    private int list = 60;
    private TextureLoader textureLoader;
    private static final String START = "start";
    private CubeGL cube;

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
        textureLoader.initTexture(START);
        gl.glPushMatrix();
//        gl.glNewList(list,GL.GL_COMPILE);
        cube = new CubeGL(0.8f);
//        gl.glEndList();
        gl.glPopMatrix();
    }

    @Override
    public void draw(WorldObject object) {
        gl.glPushMatrix();
//        gl.glBindTexture(GL.GL_TEXTURE_2D, textureLoader.getTexture(START));
        gl.glTranslatef(0f,0f,0.5f);
//        gl.glCallList(list);
        cube.draw(gl,textureLoader.getTexture(START));
        gl.glPopMatrix();

    }
}