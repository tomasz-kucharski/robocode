package robotgame.world;

import javax.media.opengl.GL;
import javax.microedition.khronos.opengles.GL10;


public class WorldGL extends robotgame.opengl.WorldGL {

    public void checkWireframe() {
        if(worldConfiguration.isWireframe()) {
            ((GL10)getGraphicsContext()).glPolygonMode(GL.GL_FRONT_AND_BACK, GL.GL_LINE);
        } else {
            ((GL10)getGraphicsContext()).glPolygonMode(GL.GL_FRONT_AND_BACK, GL.GL_FILL);
        }
    }
}