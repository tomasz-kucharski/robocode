package javax.microedition.khronos.opengles;

import javax.media.opengl.GL;
import java.nio.Buffer;
import java.nio.FloatBuffer;

/**
 * @author tomekk
 * @since 2010-11-04, 00:58:26
 */
public class GL10Impl implements GL10 {

    GL gl;

    public GL10Impl(GL gl) {
        this.gl = gl;
    }

    public void glPolygonMode(int mode, int i) {
        gl.glPolygonMode(mode,i);
    }

    @Override
    public void glEnable(int mode) {
        gl.glEnable(mode);
    }

    @Override
    public void glLightfv(int glLight0, int glPosition, float[] lightPosition, int i) {
        gl.glLightfv(glLight0,glPosition,lightPosition,i);
    }

    @Override
    public void glShadeModel(int glSmooth) {
        gl.glShadeModel(glSmooth);
    }

    @Override
    public void glClearColor(float v, float v1, float v2, float v3) {
        gl.glClearColor(v,v1,v2,v3);
    }

    @Override
    public void glClearDepthf(float v) {
        gl.glClearDepth(v);
    }

    @Override
    public void glDepthFunc(int glLequal) {
        gl.glDepthFunc(glLequal);
    }

    @Override
    public void glHint(int glPerspectiveCorrectionHint, int glNicest) {
        gl.glHint(glPerspectiveCorrectionHint,glNicest);
    }

    @Override
    public void glLoadIdentity() {
        gl.glLoadIdentity();
    }

    @Override
    public void glClear(int i) {
        gl.glClear(i);
    }

    @Override
    public void glColor4f(float v, float v1, float v2, float v3) {
        gl.glColor4f(v,v1,v2,v3);
    }

    @Override
    public void glRotatef(float v, float v1, float v2, float v3) {
        gl.glRotatef(v,v1,v2,v3);
    }

    @Override
    public void glTranslatef(float v, float y, float v1) {
        gl.glTranslatef(v,y,v1);
    }

    @Override
    public void glBlendFunc(int glSrcAlpha, int glOneMinusSrcAlpha) {
        gl.glBlendFunc(glSrcAlpha,glOneMinusSrcAlpha);
    }

    @Override
    public void glLineWidth(float v) {
        gl.glLineWidth(v);
    }

    @Override
    public void glDisable(int glLineSmooth) {
        gl.glDisable(glLineSmooth);
    }

    @Override
    public void glViewport(int i, int i1, int screenWidth, int screenHeight) {
        gl.glViewport(i,i1,screenWidth,screenHeight);
    }

    @Override
    public void glMatrixMode(int glProjection) {
        gl.glMatrixMode(glProjection);
    }

    @Override
    public void glBindTexture(int glTexture2d, int textureKey) {
        gl.glBindTexture(glTexture2d,textureKey);
    }

    @Override
    public void glEnableClientState(int glVertexArray) {
        gl.glEnableClientState(glVertexArray);
    }

    @Override
    public void glFrontFace(int glCcw) {
        gl.glFrontFace(glCcw);
    }

    @Override
    public void glVertexPointer(int i, int glFloat, int i1, Buffer vertexBuffer) {
        gl.glVertexPointer(i,glFloat,i1,vertexBuffer);
    }

    @Override
    public void glTexCoordPointer(int i, int glFloat, int i1, Buffer textureBuffer) {
        gl.glTexCoordPointer(i,glFloat,i1,textureBuffer);
    }

    @Override
    public void glNormalPointer(int glFloat, int i, Buffer normalBuffer) {
        gl.glNormalPointer(glFloat,i,normalBuffer);
    }

    @Override
    public void glDrawElements(int glTriangles, int length, int glUnsignedByte, Buffer indexBuffer) {
        gl.glDrawElements(glTriangles,length,glUnsignedByte,indexBuffer);
    }

    @Override
    public void glDisableClientState(int glVertexArray) {
        gl.glDisableClientState(glVertexArray);
    }

    @Override
    public void glMaterialfv(int glFrontAndBack, int glAmbient, FloatBuffer dechaAmb) {
        gl.glMaterialfv(glFrontAndBack,glAmbient,dechaAmb);
    }

    @Override
    public void glPushMatrix() {
        gl.glPushMatrix();
    }

    @Override
    public void glPopMatrix() {
        gl.glPopMatrix();
    }

    @Override
    public void glScalef(float x, float y, float z) {
        gl.glScalef(x,y,z);
    }

    public GL getGl() {
        return gl;
    }
}
