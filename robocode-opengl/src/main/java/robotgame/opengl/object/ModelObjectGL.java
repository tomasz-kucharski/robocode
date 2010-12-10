package robotgame.opengl.object;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import robotgame.loader.TextureLoader;
import robotgame.loader.obj.OBJFileLoader;
import robotgame.loader.obj.OBJGroup;
import robotgame.object.WorldObject;
import robotgame.object.WorldObjectRenderer;

import javax.microedition.khronos.opengles.GL10;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tomekk
 * @since 2010-12-08, 20:41:17
 */
public class ModelObjectGL implements WorldObjectRenderer {
    private static final Logger log = LoggerFactory.getLogger(ModelObjectGL.class);

    protected GL10 gl;

    private Map<String, ModelGroupGL> groups = new HashMap<String, ModelGroupGL>();
	
	private BufferedReader modelFile;

    @Override
    public void setTextureLoader(TextureLoader textureLoader) {

    }

    @Override
    public void setGraphicsContext(Object context) {
        this.gl = (GL10) context;
    }

    @Override
    public void init() {
        OBJFileLoader fileLoader = new OBJFileLoader();
        try {
            fileLoader.load(modelFile);
            for (Map.Entry<String, OBJGroup> group : fileLoader.getModel().getGroups().entrySet()) {
                ModelGroupGL modelGroup = new ModelGroupGL();
                modelGroup.setVertexBuffer(group.getValue().getVertexBuffer());
                modelGroup.setNormalBuffer(group.getValue().getNormalBuffer());
                modelGroup.setIndexBuffer(group.getValue().getFaceBuffer());
                groups.put(group.getKey(),modelGroup);
            }
        } catch (FileNotFoundException e) {
            log.warn("Robot model not found",e);
        }
    }

    @Override
    public void draw(WorldObject object) {
        gl.glPushMatrix();
//        gl.glTranslatef(.5f, .5f, 2f);
//        gl.glColor4f(0.7f,0.7f,0.7f,1);
//        Bind our only previously generated texture in this case
//        gl.glBindTexture(GL10.GL_TEXTURE_2D, textureKey);
        for (ModelGroupGL group : groups.values()) {
            // Point to our buffers
            gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
//        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
            gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);

            // Set the face rotation
            gl.glFrontFace(GL10.GL_CW);
            // Enable the vertex and texture state
            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, group.getVertexBuffer());
//        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBuffer);
            gl.glNormalPointer(GL10.GL_FLOAT, 0, group.getNormalBuffer());

            // Draw the vertices as triangles, based on the Index Buffer information
            gl.glDrawElements(GL10.GL_TRIANGLES, group.getIndexBuffer().capacity(), GL10.GL_UNSIGNED_SHORT, group.getIndexBuffer());

            // Disable the client state before leaving
            gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
//        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
            gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
        }
        gl.glPopMatrix();
    }
}
