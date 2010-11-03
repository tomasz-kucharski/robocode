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
    private int worldTableGLList = 100;
    private static final String TABLE = "table";

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
        float[] lightAmbient = {0.5f, 0.5f, 0.5f, 1.0f};
        float[] lightDiffuse = {1.0f, 1.0f, 1.0f, 1.0f};
        float[] lightPosition = {0.0f, 0.0f, 2.0f, 1.0f};

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

        gl.glEnable(GL.GL_TEXTURE_2D);                              // Enable Texture Mapping
        gl.glShadeModel(GL.GL_SMOOTH);                              // Enable Smooth Shading
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);                    // Black Background
        gl.glClearDepth(1.0f);                                      // Depth Buffer Setup
        gl.glEnable(GL.GL_DEPTH_TEST);                              // Enables Depth Testing
        gl.glDepthFunc(GL.GL_LEQUAL);                               // The Type Of Depth Testing To Do

        gl.glHint(GL.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);  // Really Nice Perspective Calculations
        gl.glLoadIdentity();

        gl.glTranslatef(0.0f,0.0f,0.3f);
        initializeWorldTable();
    }


    public void beginScene() {
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();

        initRenderLocation();
        drawWorldTable();
        gl.glColor3f(0f,1f,1f);

        checkAntialiasing();
        checkWireframe();

    }

    public void beginScene(Robot robot) {
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();


        gl.glRotatef(-90.0f,0.0f,1.0f,0.0f);
        gl.glRotatef(-90.0f,1.0f,0.0f,0.0f);
        gl.glRotatef(35.0f,0.0f,1.0f,0.0f);
        gl.glRotatef(robot.getDirection().getRotation(),0.0f,0.0f,1.0f); //direction
        float X = -posX - (float)robot.getPosition().x;
        float Y = -posY - (float)robot.getPosition().y;
        gl.glTranslatef(X+1 ,Y ,-3.0f);

        gl.glRotatef(worldConfiguration.getRotateX(),0.0f,1.0f,0.0f);
        gl.glRotatef(worldConfiguration.getRotateY(),1.0f,0.0f,0.0f);
        gl.glRotatef(worldConfiguration.getRotateZ(),0.0f,0.0f,1.0f);

        gl.glTranslatef(worldConfiguration.getMoveX(),worldConfiguration.getMoveY(),worldConfiguration.getMoveZ());



        drawWorldTable();
        //---  USTAW WYSOKOSC PONAD STOLEM
        gl.glTranslatef(0.0f,0.0f,0.3f);
        gl.glColor3f(1f,1f,1f);

        checkAntialiasing();
        checkWireframe();
    }


    private void initRenderLocation() {
        gl.glTranslatef(0.0f,0.0f,-8.0f);
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
            gl.glLineWidth(5.0f);
        }
        else {
            gl.glDisable(GL.GL_LINE_SMOOTH);
            gl.glDisable(GL.GL_BLEND);
            gl.glBlendFunc(GL.GL_SRC_ALPHA,GL.GL_ONE_MINUS_SRC_ALPHA);
            gl.glHint(GL.GL_LINE_SMOOTH_HINT,GL.GL_NICEST);
            gl.glLineWidth(5f);
        }
    }

    public void checkWireframe() {
        if(worldConfiguration.isWireframe()) {
            gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL.GL_LINE);
        } else {
            gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL.GL_FILL);
        }
    }

    private void initializeWorldTable() {
        textureLoader.initTexture(TABLE);

        float x = 0.5f*(float)worldMap.getColumns()+0.5f;
        float y = 0.5f*(float)worldMap.getRows()+0.5f;

        gl.glNewList(worldTableGLList,GL.GL_COMPILE);


        gl.glColor4f(1.0f,.7f,1.0f,1f);
        gl.glBegin(GL.GL_QUADS);
        gl.glNormal3f(0.0f,0.0f,1.0f);
        gl.glTexCoord2f(1.0f,1.0f);gl.glVertex3f(x,y,0.0f);
        gl.glTexCoord2f(0.0f,1.0f);gl.glVertex3f(-x,y,0.0f);
        gl.glTexCoord2f(0.0f,0.0f);gl.glVertex3f(-x,-y,0.0f);
        gl.glTexCoord2f(1.0f,0.0f);gl.glVertex3f(x,-y,0.0f);
        gl.glEnd();

        gl.glEndList();
    }

    private void drawWorldTable() {
        gl.glTranslatef(0.0f,0.0f,-0.3f);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureLoader.getTexture(TABLE));
        gl.glCallList(worldTableGLList);
        gl.glTranslatef(0.0f,0.0f,0.3f);
    }
}