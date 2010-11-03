package robotgame.object;

import robotgame.loader.TextureLoader;

import javax.media.opengl.GL;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * @author tomekk
 * @since 2010-10-20, 17:09:38
 */
public class CubeGL {

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


    public CubeGL(float scaleFactor) {
        float scale = scaleFactor / 2;

        float vertices[] = {
                //Vertices according to faces
                -scale, -scale, scale, //Vertex 0
                scale, -scale, scale,  //v1
                -scale, scale, scale,  //v2
                scale, scale, scale,   //v3

                scale, -scale, scale,	//...
                scale, -scale, -scale,
                scale, scale, scale,
                scale, scale, -scale,

                scale, -scale, -scale,
                -scale, -scale, -scale,
                scale, scale, -scale,
                -scale, scale, -scale,

                -scale, -scale, -scale,
                -scale, -scale, scale,
                -scale, scale, -scale            ,
                -scale, scale, scale,

                -scale, -scale, -scale             ,
                scale, -scale, -scale,
                -scale, -scale, scale,
                scale, -scale, scale,

                -scale, scale, scale,
                scale, scale, scale,
                -scale, scale, -scale,
                scale, scale, -scale,
        };

        /** The initial texture coordinates (u, v) */
        float texture[] = {
                //Mapping coordinates for the vertices
                0.0f, 0.0f,
                0.0f, 1.0f,
                1.0f, 0.0f,
                1.0f, 1.0f,

                0.0f, 0.0f,
                0.0f, 1.0f,
                1.0f, 0.0f,
                1.0f, 1.0f,

                0.0f, 0.0f,
                0.0f, 1.0f,
                1.0f, 0.0f,
                1.0f, 1.0f,

                0.0f, 0.0f,
                0.0f, 1.0f,
                1.0f, 0.0f,
                1.0f, 1.0f,

                0.0f, 0.0f,
                0.0f, 1.0f,
                1.0f, 0.0f,
                1.0f, 1.0f,

                0.0f, 0.0f,
                0.0f, 1.0f,
                1.0f, 0.0f,
                1.0f, 1.0f,
        };

        	/**
	 * The initial normals for the lighting calculations
	 *
	 * The normals are not necessarily correct from a
	 * real world perspective, as I am too lazy to write
	 * these all on my own. But you get the idea and see
	 * what I mean if you run the demo.
	 */
        float[] normals = new float[]{
                // Normals
                0.0f, 0.0f, scale,
                0.0f, 0.0f, -scale,
                0.0f, scale, 0.0f,
                0.0f, -scale, 0.0f,

                0.0f, 0.0f, scale,
                0.0f, 0.0f, -scale,
                0.0f, scale, 0.0f,
                0.0f, -scale, 0.0f,

                0.0f, 0.0f, scale,
                0.0f, 0.0f, -scale,
                0.0f, scale, 0.0f,
                0.0f, -scale, 0.0f,

                0.0f, 0.0f, scale,
                0.0f, 0.0f, -scale,
                0.0f, scale, 0.0f,
                0.0f, -scale, 0.0f,

                0.0f, 0.0f, scale,
                0.0f, 0.0f, -scale,
                0.0f, scale, 0.0f,
                0.0f, -scale, 0.0f,

                0.0f, 0.0f, scale,
                0.0f, 0.0f, -scale,
                0.0f, scale, 0.0f,
                0.0f, -scale, 0.0f,
        };

        vertexBuffer = loadToBuffer(vertices);
        textureBuffer = loadToBuffer(texture);
        normalBuffer = loadToBuffer(normals);

        /** The initial indices definition */
        indices = new byte[]{
                //Faces definition
                0,1,3, 0,3,2, 			//Face front
                4,5,7, 4,7,6, 			//Face right
                8,9,11, 8,11,10, 		//...
                12,13,15, 12,15,14,
                16,17,19, 16,19,18,
                20,21,23, 20,23,22,
        };
        indexBuffer = ByteBuffer.allocateDirect(indices.length);
        indexBuffer.put(indices);
        indexBuffer.position(0);


    }

    public void draw(GL gl, int textureKey) {

//        gl.glPushMatrix();
//        gl.glScalef(0.5f, .5f, .5f);

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

        FloatBuffer material03Amb  = FloatBuffer.wrap(new float[]{0.00784300f,0.0705880f,0.650980f,1.0f});
        FloatBuffer material03Dif  = FloatBuffer.wrap(new float[]{0.0117650f,0.109804f,0.560784f,1.0f});
        FloatBuffer material03Spc  = FloatBuffer.wrap(new float[]{0.00784300f,0.0705880f,0.650980f,1.0f});
        FloatBuffer material03Shn  = FloatBuffer.wrap(new float[]{128.000f});


        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT, material03Amb);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_DIFFUSE, material03Dif);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SPECULAR, material03Spc);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS, material03Shn);
    }
}
