package robotgame.android;

import android.content.Context;
import android.opengl.GLSurfaceView;
import robotgame.WorldService;
import robotgame.android.loader.TextureLoader;
import robotgame.opengl.WorldServiceOpenGL;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * @author tomekk
 * @since 2010-11-01, 12:51:18
 */
public class RobotGameView extends GLSurfaceView {

    private WorldService worldService = new WorldServiceOpenGL();

    public RobotGameView(Context context) {
        super(context);
        setRenderer(new GLRenderer());
        worldService.setTextureLoader(new TextureLoader());
    }

        class GLRenderer implements GLSurfaceView.Renderer {

        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            worldService.setGraphicsContext(gl);
        }

        public void onSurfaceChanged(GL10 gl, int width, int height) {
            worldService.setGraphicsContext(gl);
            worldService.onResize(width, height);
        }

        public void onDrawFrame(GL10 gl) {
            worldService.onDraw();
        }
    }
}
