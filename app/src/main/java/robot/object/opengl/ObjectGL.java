package robot.object.opengl;

import robot.object.WorldObject;

import javax.media.opengl.GL;
import java.io.File;
import java.nio.IntBuffer;

public abstract class ObjectGL {

    protected IntBuffer texture;
    protected AUX_RGBImageRec texture1 = null;
    protected int list;

    public abstract void draw(WorldObject object);

    public abstract void init();

    protected void loadGLTextures(GL gl, File file) {
        texture1 = auxDIBImageLoad(file);
        gl.glGenTextures(1,texture);
        gl.glBindTexture(GL.GL_TEXTURE_2D,texture);
        gl.glTexParameteri(GL.GL_TEXTURE_2D,GL.GL_TEXTURE_MAG_FILTER,GL.GL_LINEAR);
        gl.glTexParameteri(GL.GL_TEXTURE_2D,GL.GL_TEXTURE_MIN_FILTER,GL.GL_LINEAR);
        glTexImage2D(GL.GL_TEXTURE_2D,0,3,texture1.sizeX,texture1.sizeY,0,GL.GL_RGB,GL.GL_UNSIGNED_BYTE,texture1.data);
    }

}