package robotgame.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.opengl.GLSurfaceView;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MotionEvent;
import robotgame.WorldService;
import robotgame.android.loader.AndroidTextureLoader;
import robotgame.loader.DeployWorld;
import robotgame.opengl.WorldServiceOpenGL;
import robotgame.world.WorldConfiguration;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author tomekk
 * @since 2010-11-01, 12:51:18
 */
public class RobotGameView extends GLSurfaceView {
    private static final String LOG_TAG = RobotGameView.class.getSimpleName();

    private WorldService worldService = new WorldServiceOpenGL();
    private float oldX;
    private float oldY;

    public RobotGameView(Context context) {
        super(context);
        setRenderer(new GLRenderer());
        AndroidTextureLoader androidTextureLoader = new AndroidTextureLoader();
        androidTextureLoader.setResources(context.getResources());
        worldService.setTextureLoader(androidTextureLoader);
        try {
            SharedPreferences myPreference = PreferenceManager.getDefaultSharedPreferences(context);
            String mapsLocation = myPreference.getString("mapsLocation",null);
            String programsLocation = myPreference.getString("programsLocation",null);
            String map = myPreference.getString("map",null);

            DeployWorld deployWorld = new DeployWorld(new BufferedReader(new FileReader(mapsLocation+"/"+map)));
            deployWorld.setContextPath(programsLocation);
            worldService.onMapLoad(deployWorld.loadWorld());
            worldService.setMainRobot(deployWorld.getRobot());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //
        float x = event.getX();
        float y = event.getY();

        //If a touch is moved on the screen
        if(event.getAction() == MotionEvent.ACTION_MOVE) {
            //Calculate the change
            float dx = x - oldX;
            float dy = y - oldY;
            //Define an upper area of 10% on the screen
            int upperArea = this.getHeight() / 10;

            //Zoom in/out if the touch move has been made in the upper
            if(y < upperArea) {
                worldService.getConfiguration().changeMoveZ(-dx * 0.1f);

                //Rotate around the axis otherwise
            } else {
                worldService.getConfiguration().changeRotateX(dx);
                worldService.getConfiguration().changeRotateY(dy);
            }

            //A press on the screen
        } else if(event.getAction() == MotionEvent.ACTION_UP) {
            //Define an upper area of 10% to define a lower area
            int upperArea = this.getHeight() / 10;
            int lowerArea = this.getHeight() - upperArea;

            //Change the light setting if the lower area has been pressed
            if(y > lowerArea) {
                worldService.getConfiguration().changeLightEnabled();
            }
        }

        //Remember the values
        oldX = x;
        oldY = y;

        //We handled the event
        return true;
    }

    public WorldConfiguration getConfiguration() {
        return worldService.getConfiguration();
    }

    class GLRenderer implements GLSurfaceView.Renderer {
        private long numFrames;
        private long fpsStartTime;

        public GLRenderer() {
            fpsStartTime = System.currentTimeMillis();
        }

        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            worldService.setGraphicsContext(gl);
            worldService.onInit(0,0);
        }

        public void onSurfaceChanged(GL10 gl, int width, int height) {
            worldService.setGraphicsContext(gl);
            worldService.onResize(width, height);
        }

        public void onDrawFrame(GL10 gl) {
            worldService.onDraw();
            numFrames++;
            logTime();
        }

        private void logTime() {
            long fpsElapsed = System.currentTimeMillis() - fpsStartTime;
            if (fpsElapsed > 5 * 1000) { // every 5 seconds
                float fps = (numFrames * 1000.0F) / fpsElapsed;
                Log.d(LOG_TAG, "Frames per second: " + fps + " (" + numFrames + " frames in " + fpsElapsed + " ms)");
                fpsStartTime = System.currentTimeMillis();
                numFrames = 0;
            }
        }
    }
}
