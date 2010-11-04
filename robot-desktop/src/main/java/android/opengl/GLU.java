package android.opengl;

import javax.microedition.khronos.opengles.GL10;

/**
 * @author tomekk
 * @since 2010-11-04, 01:11:12
 */
public class GLU {

    static  javax.media.opengl.glu.GLU glu;

    public static void gluPerspective(GL10 gl, float i, float v, float v1, float v2) {
        if (glu == null) {
            glu = new javax.media.opengl.glu.GLU();
        }
        glu.gluPerspective(i,v,v1,v2);
    }
}
