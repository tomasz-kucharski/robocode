package javax.microedition.khronos.opengles;

import javax.media.opengl.GL;
import java.nio.Buffer;
import java.nio.FloatBuffer;

/**
 * @author tomekk
 * @since 2010-11-04, 00:43:11
 */
public interface GL10 {
    int GL_LIGHTING = GL.GL_LIGHTING;
    int GL_LIGHT0 = GL.GL_LIGHT0;
    int GL_AMBIENT = GL.GL_AMBIENT;
    int GL_DIFFUSE = GL.GL_DIFFUSE;
    int GL_POSITION = GL.GL_POSITION;
    int GL_TEXTURE_2D = GL.GL_TEXTURE_2D;
    int GL_SMOOTH = GL.GL_SMOOTH;
    int GL_DEPTH_TEST = GL.GL_DEPTH_TEST;
    int GL_LEQUAL = GL.GL_LEQUAL;
    int GL_PERSPECTIVE_CORRECTION_HINT = GL.GL_PERSPECTIVE_CORRECTION_HINT;
    int GL_NICEST = GL.GL_NICEST;
    int GL_COLOR_BUFFER_BIT = GL.GL_COLOR_BUFFER_BIT;
    int GL_DEPTH_BUFFER_BIT = GL.GL_DEPTH_BUFFER_BIT;
    int GL_LINE_SMOOTH = GL.GL_LINE_SMOOTH;
    int GL_BLEND = GL.GL_BLEND;
    int GL_SRC_ALPHA = GL.GL_SRC_ALPHA;
    int GL_ONE_MINUS_SRC_ALPHA = GL.GL_ONE_MINUS_SRC_ALPHA;
    int GL_LINE_SMOOTH_HINT = GL.GL_LINE_SMOOTH_HINT;
    int GL_PROJECTION = GL.GL_PROJECTION;
    int GL_MODELVIEW = GL.GL_MODELVIEW;
    int GL_VERTEX_ARRAY = GL.GL_VERTEX_ARRAY;
    int GL_TEXTURE_COORD_ARRAY = GL.GL_TEXTURE_COORD_ARRAY;
    int GL_NORMAL_ARRAY = GL.GL_NORMAL_ARRAY;
    int GL_CCW = GL.GL_CCW;
    int GL_FLOAT = GL.GL_FLOAT;
    int GL_TRIANGLES = GL.GL_TRIANGLES;
    int GL_UNSIGNED_BYTE = GL.GL_UNSIGNED_BYTE;
    int GL_FRONT_AND_BACK = GL.GL_FRONT_AND_BACK;
    int GL_SPECULAR = GL.GL_SPECULAR;
    int GL_SHININESS = GL.GL_SHININESS;

    public void glPolygonMode(int mode, int i);

    void glEnable(int mode);

    void glLightfv(int glLight0, int glPosition, float[] lightPosition, int i);

    void glShadeModel(int glSmooth);

    void glClearColor(float v, float v1, float v2, float v3);

    void glClearDepthf(float v);

    void glDepthFunc(int glLequal);

    void glHint(int glPerspectiveCorrectionHint, int glNicest);

    void glLoadIdentity();

    void glClear(int i);

    void glColor4f(float v, float v1, float v2, float v3);

    void glRotatef(float v, float v1, float v2, float v3);

    void glTranslatef(float v, float y, float v1);

    void glBlendFunc(int glSrcAlpha, int glOneMinusSrcAlpha);

    void glLineWidth(float v);

    void glDisable(int glLineSmooth);

    void glViewport(int i, int i1, int screenWidth, int screenHeight);

    void glMatrixMode(int glProjection);

    void glBindTexture(int glTexture2d, int textureKey);

    void glEnableClientState(int glVertexArray);

    void glFrontFace(int glCcw);

    void glVertexPointer(int i, int glFloat, int i1, Buffer vertexBuffer);

    void glTexCoordPointer(int i, int glFloat, int i1, Buffer textureBuffer);

    void glNormalPointer(int glFloat, int i, Buffer normalBuffer);

    void glDrawElements(int glTriangles, int length, int glUnsignedByte, Buffer indexBuffer);

    void glDisableClientState(int glVertexArray);

    void glMaterialfv(int glFrontAndBack, int glAmbient, FloatBuffer dechaAmb);

    void glPushMatrix();

    void glPopMatrix();

    void glScalef(float x, float y, float z);
}
