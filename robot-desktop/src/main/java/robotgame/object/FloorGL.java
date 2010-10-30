package robotgame.object;

import robotgame.loader.TextureLoader;

import javax.media.opengl.GL;
import java.nio.FloatBuffer;

public class FloorGL  implements WorldObjectRenderer {

    FloatBuffer dechaAmb = FloatBuffer.wrap(new float[]{1.0f,1.0f,1.0f,1.0f});
    FloatBuffer dechaDif = FloatBuffer.wrap(new float[]{1.0f,1.0f,1.0f,1.0f});
    FloatBuffer dechaSpc = FloatBuffer.wrap(new float[]{1.0f,1.0f,1.0f,1.0f});
    FloatBuffer dechaShn = FloatBuffer.wrap(new float[]{128.000f});

    private int list = 50;

    private GL gl;
    private static final String GRASS = "grass";
    private TextureLoader textureLoader;

    @Override
    public void setTextureLoader(TextureLoader textureLoader) {
        this.textureLoader = this.textureLoader;
        this.textureLoader = textureLoader;
    }

    @Override
    public void setGraphicsContext(Object context) {
        gl = (GL) context;
    }

    @Override
    public void init() {
        textureLoader.initTexture(GRASS);

        gl.glNewList(list,GL.GL_COMPILE);

        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT, dechaAmb);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_DIFFUSE, dechaDif);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SPECULAR, dechaSpc);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS, dechaShn);

        gl.glBegin(GL.GL_QUADS);
        gl.glNormal3f(0.0f,0.0f,1.0f);
        gl.glTexCoord2f(1.0f,0.0f); 	gl.glVertex3f(0.48f,-0.48f,0.0f);
        gl.glTexCoord2f(1.0f,1.0f); 	gl.glVertex3f(0.48f,0.48f,0.0f);
        gl.glTexCoord2f(0.0f,1.0f); 	gl.glVertex3f(-0.48f,0.48f,0.0f);
        gl.glTexCoord2f(0.0f,0.0f);	    gl.glVertex3f(-0.48f,-0.48f,0.0f);
        gl.glEnd();

        gl.glEndList();
    }

    @Override
    public void draw(WorldObject object) {
        gl.glBindTexture(GL.GL_TEXTURE_2D,textureLoader.getTexture(GRASS));
        gl.glCallList(list);
    }
}