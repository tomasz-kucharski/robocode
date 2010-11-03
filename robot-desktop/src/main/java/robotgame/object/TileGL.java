package robotgame.object;

import javax.media.opengl.GL;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * @author tomekk
 * @since 2010-10-20, 17:09:38
 */
public class TileGL {

    /**
     * Vertex coordinates for cube, persisted as native array
     */
    private final FloatBuffer vertexBuffer;

    /**
     * Coordinates for textures, persisted as native array
     */
    private final FloatBuffer textureBuffer;

    /** The buffer holding the indices */
    private ByteBuffer indexBuffer;

    private byte[] indices;
    private FloatBuffer normalBuffer;


    private FloatBuffer dechaAmb;
    private FloatBuffer dechaDif;
    private FloatBuffer dechaSpc;
    private FloatBuffer dechaShn;


    public TileGL(float scaleFactor) {
        float scale = scaleFactor / 2;

        float vertices[] = {
                scale, -scale, -scale,
                -scale, -scale, -scale,
                scale, scale, -scale,
                -scale, scale, -scale,
        };

        float texture[] = {
                0.0f, 0.0f,
                0.0f, 1.0f,
                1.0f, 0.0f,
                1.0f, 1.0f,
        };

        float[] normals = new float[]{
                0.0f, 0.0f, scale,
                0.0f, 0.0f, -scale,
                0.0f, scale, 0.0f,
                0.0f, -scale, 0.0f,
        };

        vertexBuffer = loadToBuffer(vertices);
        textureBuffer = loadToBuffer(texture);
        normalBuffer = loadToBuffer(normals);

        indices = new byte[]{
                0,1,3, 0,3,2,
        };
        indexBuffer = ByteBuffer.allocateDirect(indices.length);
        indexBuffer.put(indices);
        indexBuffer.position(0);
    }

    public void draw(GL gl, int textureKey) {
        applyMaterial(gl);
        //Bind our only previously generated texture in this case
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureKey);

        //Point to our buffers
        gl.glEnableClientState(GL.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL.GL_TEXTURE_COORD_ARRAY);
        gl.glEnableClientState(GL.GL_NORMAL_ARRAY);

        //Set the face rotation
        gl.glFrontFace(GL.GL_CCW);

        //Enable the vertex and texture state
        gl.glVertexPointer(3, GL.GL_FLOAT, 0, vertexBuffer);
        gl.glTexCoordPointer(2, GL.GL_FLOAT, 0, textureBuffer);
        gl.glNormalPointer(GL.GL_FLOAT, 0, normalBuffer);


        //Draw the vertices as triangles, based on the Index Buffer information
        gl.glDrawElements(GL.GL_TRIANGLES, indices.length, GL.GL_UNSIGNED_BYTE, indexBuffer);

        //Disable the client state before leaving
        gl.glDisableClientState(GL.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL.GL_TEXTURE_COORD_ARRAY);
        gl.glDisableClientState(GL.GL_NORMAL_ARRAY);
//        gl.glPopMatrix();

    }

    private FloatBuffer loadToBuffer(float[] intArray) {
        ByteBuffer tbb = ByteBuffer.allocateDirect(intArray.length * Float.SIZE/8);
        tbb.order(ByteOrder.nativeOrder());
        FloatBuffer buffer = tbb.asFloatBuffer();

        buffer.put(intArray);
        buffer.position(0);
        return buffer;
    }

    public void applyMaterial(GL gl) {
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT, dechaAmb);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_DIFFUSE, dechaDif);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SPECULAR, dechaSpc);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS, dechaShn);
    }

    public void setDechaAmb(FloatBuffer dechaAmb) {
        this.dechaAmb = dechaAmb;
    }

    public void setDechaDif(FloatBuffer dechaDif) {
        this.dechaDif = dechaDif;
    }

    public void setDechaSpc(FloatBuffer dechaSpc) {
        this.dechaSpc = dechaSpc;
    }

    public void setDechaShn(FloatBuffer dechaShn) {
        this.dechaShn = dechaShn;
    }
}
