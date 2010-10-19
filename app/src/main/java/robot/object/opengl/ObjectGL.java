package robot.object.opengl;

import robot.object.WorldObject;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import java.io.File;
import java.nio.IntBuffer;

public abstract class ObjectGL {
          //todo all commented lines should be enabled
    protected IntBuffer texture;
//    protected AUX_RGBImageRec texture1 = null;
    protected int list;

    public abstract void draw(GL2 gl, WorldObject object);

    public abstract void init(GL2 gl);

    protected void loadGLTextures(GL gl, File file) {
//        texture1 = auxDIBImageLoad(file);
//        gl.glGenTextures(1,texture);
//        gl.glBindTexture(GL.GL_TEXTURE_2D,texture);
//        gl.glBindTexture(GL2.GL_TEXTURE_2D,list);
//        gl.glTexParameteri(GL2.GL_TEXTURE_2D,GL2.GL_TEXTURE_MAG_FILTER,GL2.GL_LINEAR);
//        gl.glTexParameteri(GL2.GL_TEXTURE_2D,GL2.GL_TEXTURE_MIN_FILTER,GL2.GL_LINEAR);
//        glTexImage2D(GL.GL_TEXTURE_2D,0,3,texture1.sizeX,texture1.sizeY,0,GL.GL_RGB,GL.GL_UNSIGNED_BYTE,texture1.data);
    }

}