package robotgame.opengl;

import android.opengl.GLU;
import robotgame.object.robot.Robot;
import robotgame.world.Position;
import robotgame.world.WorldRenderer;

import javax.microedition.khronos.opengles.GL10;

public class WorldGL extends WorldRenderer {

    protected GL10 gl;

    private float posX;
    private float posY;

    public void resize() {
        int screenWidth = worldConfiguration.getScreenWidth();
        int screenHeight = worldConfiguration.getScreenHeight();

        gl.glViewport(0, 0, screenWidth, screenHeight);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();

        GLU.gluPerspective(gl,45f, (float) screenWidth / screenHeight, 0.1f, 100f);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
    }


    @Override
    public Object getGraphicsContext() {
        return gl;
    }

    @Override
    public void setGraphicsContext(Object graphicsContext) {
        this.gl = (GL10) graphicsContext;
    }

    private void setUpLighting() {
        if(worldConfiguration.isLight()) {
            gl.glEnable(GL10.GL_LIGHTING);
            gl.glEnable(GL10.GL_LIGHT0);
            float[] lightAmbient = {0.3f, 0.3f, 0.3f, 1f};
            float[] lightDiffuse = {1.0f, 1.0f, 1.0f, 1.0f};
            float[] lightPosition = {1.0f, 1.0f, 10.0f,0f};
            gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT, lightAmbient, 0);
            gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, lightDiffuse, 0);
            gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, lightPosition, 0);
        } else {
            gl.glDisable(GL10.GL_LIGHTING);
        }

    }

    public void init() {
        setUpLighting();
        resize();

        posX = -0.5f*(worldMap.getColumns()-1);
        posY = -0.5f*(worldMap.getRows()-1);
        worldConfiguration.changeMoveZ(-Math.max(worldMap.getColumns(),worldMap.getRows())-3);

        gl.glEnable(GL10.GL_TEXTURE_2D);                              // Enable Texture Mapping
        gl.glShadeModel(GL10.GL_SMOOTH);                              // Enable Smooth Shading
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);                    // Black Background
        gl.glClearDepthf(1.0f);                                      // Depth Buffer Setup
        gl.glEnable(GL10.GL_DEPTH_TEST);                              // Enables Depth Testing
        gl.glDepthFunc(GL10.GL_LEQUAL);                               // The Type Of Depth Testing To Do

        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);  // Really Nice Perspective Calculations
        gl.glLoadIdentity();

    }


    public void beginScene() {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        setUpLighting();

        initRenderLocation();
//        drawWorldTable();
        gl.glColor4f(0f,1f,1f,0f);

        checkAntialiasing();
        checkWireframe();

    }

    public void beginScene(Robot robot) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();


        gl.glRotatef(90.0f,0.0f,1.0f,0.0f);
        gl.glRotatef(-90.0f,1.0f,0.0f,0.0f);
        gl.glRotatef(-35.0f,0.0f,1.0f,0.0f);
        gl.glRotatef(robot.getDirection().getRotation(),0.0f,0.0f,1.0f); //direction
        float X = -posX - (float)robot.getPosition().x;
        float Y = -posY - (float)robot.getPosition().y;
        gl.glTranslatef(X+2f ,Y ,-3.0f);

        gl.glRotatef(worldConfiguration.getRotateX(),0.0f,1.0f,0.0f);
        gl.glRotatef(worldConfiguration.getRotateY(),1.0f,0.0f,0.0f);
        gl.glRotatef(worldConfiguration.getRotateZ(),0.0f,0.0f,1.0f);

//        gl.glTranslatef(worldConfiguration.getMoveX(),worldConfiguration.getMoveY(),worldConfiguration.getMoveZ());



//        drawWorldTable();
        //---  USTAW WYSOKOSC PONAD STOLEM
//        gl.glTranslatef(0.0f,0.0f,0.3f);
//        gl.glColor3f(1f,1f,1f);
//
        checkAntialiasing();
        checkWireframe();
    }


    private void initRenderLocation() {
//        gl.glTranslatef(0.0f,0.0f,-8.0f);
        gl.glTranslatef(0.0f,0.0f,worldConfiguration.getMoveZ());
        float transX = worldConfiguration.getRotateX() / worldConfiguration.getScreenWidth()* 100;
        float transY = worldConfiguration.getRotateY() / worldConfiguration.getScreenHeight()* 100;
        gl.glRotatef(transX,0.0f,1.0f,0.0f);
        gl.glRotatef(transY,1.0f,0.0f,0.0f);
        gl.glRotatef(worldConfiguration.getRotateZ(),0.0f,0.0f,1.0f);

        gl.glTranslatef(worldConfiguration.getMoveX(),0.0f,0.0f);
        gl.glTranslatef(0.0f,worldConfiguration.getMoveY(),0.0f);
    }


    public void endScene()  {
        gl.glLoadIdentity();
    }

    public void beforeRenderObject(Position p) {
        gl.glTranslatef(posX+p.x,posY+p.y, 0.0f);
    }

    public void afterRenderObject(Position p) {
        gl.glTranslatef(-posX-p.x,-posY-p.y,0.0f);
    }

    public void checkAntialiasing() {
        if(worldConfiguration.isAntialiasing()) {
            gl.glEnable(GL10.GL_LINE_SMOOTH);
            gl.glEnable(GL10.GL_BLEND);
            gl.glBlendFunc(GL10.GL_SRC_ALPHA,GL10.GL_ONE_MINUS_SRC_ALPHA);
            gl.glHint(GL10.GL_LINE_SMOOTH_HINT,GL10.GL_NICEST);
            gl.glLineWidth(2.0f);
        }
        else {
            gl.glDisable(GL10.GL_LINE_SMOOTH);
            gl.glDisable(GL10.GL_BLEND);
            gl.glBlendFunc(GL10.GL_SRC_ALPHA,GL10.GL_ONE_MINUS_SRC_ALPHA);
            gl.glHint(GL10.GL_LINE_SMOOTH_HINT,GL10.GL_NICEST);
            gl.glLineWidth(2f);
        }
    }

    public void checkWireframe() {
    }
}