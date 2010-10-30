package robotgame.object;

import robotgame.loader.TextureLoader;

import javax.media.opengl.GL;

public class WallGL implements WorldObjectRenderer {

    private int list = 70;
    private GL gl;
    private TextureLoader textureLoader;
    private static final String WALL = "wall";

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
        textureLoader.initTexture(WALL);
        gl.glPushMatrix();
        gl.glNewList(list,GL.GL_COMPILE);
        CubeGL.createCube(gl,0.9f,true);
        gl.glEndList();
        gl.glPopMatrix();
    }

    @Override
    public void draw(WorldObject object) {
        gl.glPushMatrix();
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureLoader.getTexture(WALL));
        gl.glTranslatef(0f,0f,0.5f);
        gl.glCallList(list);
        gl.glPopMatrix();

    }
}