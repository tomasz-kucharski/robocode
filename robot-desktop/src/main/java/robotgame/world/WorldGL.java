package robotgame.world;

import robotgame.loader.TextureLoader;
import robotgame.object.CubeGL;
import robotgame.object.robot.Robot;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

public class WorldGL extends WorldRenderer {

    private GL gl;
    private GLU glu;

    private float posX;
    private float posY;
//    private int worldTableGLList = 100;
//    private static final String TABLE = "table";

    public void resize() {
        int screenWidth = worldConfiguration.getScreenWidth();
        int screenHeight = worldConfiguration.getScreenHeight();

        gl.glViewport(0, 0, screenWidth, screenHeight);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();

        glu.gluPerspective(45, (float) screenWidth / screenHeight, 0.1f, 100f);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }


    @Override
    public Object getGraphicsContext() {
        return gl;
    }

    @Override
    public void setGraphicsContext(Object graphicsContext) {
        this.gl = (GL) graphicsContext;
        glu = new GLU();
    }

    private void setUpLighting() {
        float[] lightAmbient = {0.1f, 0.1f, 0.1f, 1f};
        float[] lightDiffuse = {1.0f, 1.0f, 1.0f, 1.0f};
        float[] lightPosition = {1.0f, 1.0f, 10.0f,0f};

        gl.glEnable(GL.GL_LIGHTING);
        gl.glEnable(GL.GL_LIGHT0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_AMBIENT, lightAmbient, 0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_DIFFUSE, lightDiffuse, 0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, lightPosition, 0);
    }

    public void init() {
        setUpLighting();
        resize();

        posX = -0.5f*(worldMap.getColumns()-1);
        posY = -0.5f*(worldMap.getRows()-1);
        worldConfiguration.changeMoveZ(-Math.max(worldMap.getColumns(),worldMap.getRows())-3);

        gl.glEnable(GL.GL_TEXTURE_2D);                              // Enable Texture Mapping
        gl.glShadeModel(GL.GL_SMOOTH);                              // Enable Smooth Shading
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);                    // Black Background
        gl.glClearDepth(1.0f);                                      // Depth Buffer Setup
        gl.glEnable(GL.GL_DEPTH_TEST);                              // Enables Depth Testing
        gl.glDepthFunc(GL.GL_LEQUAL);                               // The Type Of Depth Testing To Do

        gl.glHint(GL.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);  // Really Nice Perspective Calculations
        gl.glLoadIdentity();

    }


    public void beginScene() {
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        setUpLighting();
        
        initRenderLocation();
//        drawWorldTable();
        gl.glColor3f(0f,1f,1f);

        checkAntialiasing();
        checkWireframe();

    }

    public void beginScene(Robot robot) {
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
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
            gl.glEnable(GL.GL_LINE_SMOOTH);
            gl.glEnable(GL.GL_BLEND);
            gl.glBlendFunc(GL.GL_SRC_ALPHA,GL.GL_ONE_MINUS_SRC_ALPHA);
            gl.glHint(GL.GL_LINE_SMOOTH_HINT,GL.GL_NICEST);
            gl.glLineWidth(2.0f);
        }
        else {
            gl.glDisable(GL.GL_LINE_SMOOTH);
            gl.glDisable(GL.GL_BLEND);
            gl.glBlendFunc(GL.GL_SRC_ALPHA,GL.GL_ONE_MINUS_SRC_ALPHA);
            gl.glHint(GL.GL_LINE_SMOOTH_HINT,GL.GL_NICEST);
            gl.glLineWidth(2f);
        }
    }

    public void checkWireframe() {
        if(worldConfiguration.isWireframe()) {
//            GL.GL_LINE = 6913
            gl.glPolygonMode(GL.GL_FRONT_AND_BACK, 6913);
        } else {
//            GL.GL_FILL = 6914
            gl.glPolygonMode(GL.GL_FRONT_AND_BACK, 6914);
        }
    }
}