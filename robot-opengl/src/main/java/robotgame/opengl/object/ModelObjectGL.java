package robotgame.opengl.object;

import javax.microedition.khronos.opengles.GL10;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

/**
 * @author tomekk
 * @since 2010-12-08, 20:41:17
 */
public class ModelObjectGL {

    private FloatBuffer vertexBuffer;
    private FloatBuffer textureBuffer;
    private FloatBuffer normalBuffer;
    private ShortBuffer indexBuffer;

    public void draw(GL10 gl, int textureKey) {
        gl.glPushMatrix();
        gl.glScalef(.25f, .25f, .25f);

        // Bind our only previously generated texture in this case
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textureKey);

        // Point to our buffers
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
//        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);

        // Set the face rotation
        gl.glFrontFace(GL10.GL_CCW);

        // Enable the vertex and texture state
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
//        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBuffer);
        gl.glNormalPointer(GL10.GL_FLOAT, 0, normalBuffer);

        // Draw the vertices as triangles, based on the Index Buffer information
        gl.glDrawElements(GL10.GL_POINTS, indexBuffer.capacity(), GL10.GL_UNSIGNED_SHORT, indexBuffer);

        // Disable the client state before leaving
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
        gl.glPopMatrix();
    }
}
