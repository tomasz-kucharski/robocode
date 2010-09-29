package robot.object.opengl;

import robot.object.WorldObject;

import java.io.File;

public abstract class ObjectGL {

    protected GLuint texture[1];
    protected AUX_RGBImageRec texture1 = null;
    protected GLuint list;

    abstract void draw(WorldObject object);

    public GLvoid loadGLTextures(File file) {
        texture1 = auxDIBImageLoad(file);
        glGenTextures(1,&texture[0]);
        glBindTexture(GL_TEXTURE_2D,texture[0]);
        glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MAG_FILTER,GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MIN_FILTER,GL_LINEAR);
        glTexImage2D(GL_TEXTURE_2D,0,3,texture1.sizeX,texture1.sizeY,0,GL_RGB,GL_UNSIGNED_BYTE,texture1.data);
    }

}