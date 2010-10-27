package robotgame.world.opengl;

import robotgame.loader.TextureLoader;
import robotgame.object.opengl.*;
import robotgame.object.robot.Robot;
import robotgame.world.WorldRenderer;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

public class WorldGL extends WorldRenderer {
    GL gl;
    GLU glu = new GLU();


    private float movex;
    private float movey;
    private float movez;

    private float transx;
    private float transy;
    private float transz;
    private float scale = 1.0f;

    private float posX;
    private float posY;
    private int screenWidth;
    private int screenHeight;

    public WorldGL(int columns, int rows) {

        posX = -0.5f*(columns-1);
        posY = -0.5f*(rows-1);

        movex = 0.0f;
        movey = 0.0f;
        movez = 0.0f;

        transx = 0.0f;
        transy = 0.0f;
        transz = 0.0f;

        scale = 1.0f;
    }


    public void setGl(GL gl) {
        this.gl = gl;
    }

    public void onResize() {
        screenWidth = worldConfiguration.getScreenWidth();
        screenHeight = worldConfiguration.getScreenHeight();


        gl.glViewport(0, 0, screenWidth, screenHeight);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();

        glu.gluPerspective(45, (float) width / height, 1, 1000);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();

//        gl.glViewport(0,0, screenWidth, screenHeight);
//        gl.glMatrixMode(GL.GL_PROJECTION);
//        gl.glLoadIdentity();
//        glu.gluPerspective(45.0f,(float)width/(float)height,0.1f,100.0f);
//        gl.glHint(GL.GL_PERSPECTIVE_CORRECTION_HINT,GL.GL_NICEST);
//        gl.glMatrixMode(GL.GL_MODELVIEW);
//        gl.glLoadIdentity();
//        gl.glEnable(GL.GL_NORMALIZE);

    }

//    public void InitGL()
//    {
//        gl.glClearColor(0.0f,0.0f,0.0f,0.0f);
//        gl.glClearDepth(1.0f);
////	glDepthFunc(GL_LESS);
//        gl.glEnable(GL.GL_DEPTH_TEST);
//        gl.glShadeModel(GL.GL_SMOOTH);
//        gl.glCullFace(GL.GL_FRONT);
////	glHint(GL_PERSPECTIVE_CORRECTION_HINT,GL_NICEST);
////	glMatrixMode(GL_PROJECTION);
////		glLoadIdentity();
////		gluPerspective(45.0f,(GLfloat)width/(GLfloat)height,0.1f,100.0f);
////	glMatrixMode(GL_MODELVIEW);
//
//        gl.glEnable(GL.GL_DEPTH_TEST);
//        gl.glEnable(GL.GL_TEXTURE_2D);
//        gl.glEnable(GL.GL_LIGHTING);			// wlaczenie swiatla w ogole
//	      // wlaczenie materialu w proces tworzenia koloru
////ZALADOWANIE TEKSTUR
//        gl.glLightfv(GL.GL_LIGHT0,GL.GL_AMBIENT,lightGL.LightAmbient);
//        gl.glLightfv(GL.GL_LIGHT0,GL.GL_DIFFUSE,lightGL.LightDiffuse);
//        gl.glLightfv(GL.GL_LIGHT0,GL.GL_POSITION,lightGL.LightPosition);
//        gl.glLightfv(GL.GL_LIGHT0,GL.GL_SPECULAR, lightGL.LightSpecular);
//        gl.glEnable(GL.GL_LIGHT0);			// wlaczenie oswietlenia LIGHT0
//
//        floorGL.init(gl);
//        wallGL.init(gl);
//        furnitureGL.init(gl);
//        tableGL.init(gl);
//        depotGL.init(gl);
//        rubbishGL.init(gl);
//        robotGL.init(gl);
//
//    }

    public void init() {
        onResize();

        new TextureLoader().loadTextures(gl);

        gl.glEnable(GL.GL_TEXTURE_2D);                              // Enable Texture Mapping
        gl.glShadeModel(GL.GL_SMOOTH);                              // Enable Smooth Shading
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);                    // Black Background
        gl.glClearDepth(1.0f);                                      // Depth Buffer Setup
        gl.glEnable(GL.GL_DEPTH_TEST);                              // Enables Depth Testing
        gl.glDepthFunc(GL.GL_LEQUAL);                               // The Type Of Depth Testing To Do
//        gl.glEnable(GL.GL_LIGHT0);                                  // Quick And Dirty Lighting (Assumes Light0 Is Set Up)
        gl.glEnable(GL.GL_LIGHTING);                                // Enable Lighting
        gl.glEnable(GL.GL_COLOR_MATERIAL);                          // Enable Material Coloring
        gl.glHint(GL.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);  // Really Nice Perspective Calculations
        gl.glLoadIdentity();

//        gl.glColor4f(1.0f,1.0f,1.0f,0.5f);			// Full Brightness, 50% Alpha ( NEW )
//        gl.glBlendFunc(GL.GL_SRC_ALPHA,GL.GL_ONE);		// Blending Function For Translucency Based On Source Alpha Value ( NEW )
//        gl.glEnable(GL.GL_BLEND);

//        gl.glLightfv(GL.GL_LIGHT1, GL.GL_AMBIENT, LightGL.LightAmbient);
//        gl.glLightfv(GL.GL_LIGHT1, GL.GL_DIFFUSE, LightGL.LightDiffuse);
//        gl.glLightfv(GL.GL_LIGHT1, GL.GL_POSITION,LightGL.LightPosition);
//        gl.glLightfv(GL.GL_LIGHT1, GL.GL_SHININESS,LightGL.LightShininess);
//        gl.glEnable(GL.GL_LIGHT1);
        gl.glEnable(GL.GL_LIGHT0);
//        gl.glEnable(GL.GL_LIGHTING);								// Enable Lighting
//        gl.glEnable(GL.GL_COLOR_MATERIAL);							// Enable Material Coloring




//        gl.glHint(GL.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);			// Really Nice Perspective Calculations


//	glDepthFunc(GL_LESS);
//        gl.glEnable(GL.GL_DEPTH_TEST);
//        gl.glShadeModel(GL.GL_SMOOTH);
//        gl.glCullFace(GL.GL_FRONT);
////	glHint(GL_PERSPECTIVE_CORRECTION_HINT,GL_NICEST);
////	glMatrixMode(GL_PROJECTION);
////		glLoadIdentity();
////		gluPerspective(45.0f,(GLfloat)width/(GLfloat)height,0.1f,100.0f);
//	gl.glMatrixMode(GL.GL_MODELVIEW);
//
//        gl.glEnable(GL.GL_DEPTH_TEST);
//        gl.glEnable(GL.GL_TEXTURE_2D);
//        gl.glEnable(GL.GL_LIGHTING);			// wlaczenie swiatla w ogole
//	      // wlaczenie materialu w proces tworzenia koloru
////ZALADOWANIE TEKSTUR
//        gl.glLightfv(GL.GL_LIGHT0,GL.GL_AMBIENT,lightGL.LightAmbient);
//        gl.glLightfv(GL.GL_LIGHT0,GL.GL_DIFFUSE,lightGL.LightDiffuse);
//        gl.glLightfv(GL.GL_LIGHT0,GL.GL_POSITION,lightGL.LightPosition);
//        gl.glLightfv(GL.GL_LIGHT0,GL.GL_SPECULAR, lightGL.LightSpecular);
//        gl.glEnable(GL.GL_LIGHT0);			// wlaczenie oswietlenia LIGHT0


    }


    public void beginScene() {
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();

        initRenderLocation();

        //---  RYSUJ PODKLAD
        gl.glTranslatef(0.0f,0.0f,-0.3f);
        tableGL.draw();
        gl.glTranslatef(0.0f,0.0f,0.3f);
        //---  USTAW WYSOKOSC PONAD STOLEM
        gl.glColor3f(1f,1f,1f);

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

        gl.glRotatef(transx,0.0f,1.0f,0.0f);
        gl.glRotatef(transy,1.0f,0.0f,0.0f);
        gl.glRotatef(transz,0.0f,0.0f,1.0f);
        gl.glScalef(scale,scale,scale);

        gl.glTranslatef(movex,movey,movez);



        drawWorldTable();
        //---  USTAW WYSOKOSC PONAD STOLEM
        gl.glTranslatef(0.0f,0.0f,0.3f);
        gl.glColor3f(1f,1f,1f);

        checkAntialiasing();
        checkWireframe();
    }


    private void initRenderLocation() {
        gl.glTranslatef(0.0f,0.0f,-8.0f);
        gl.glTranslatef(0.0f,0.0f,movez);
        gl.glRotatef(transx/screenWidth*100,0.0f,1.0f,0.0f);
        gl.glRotatef(transy/screenHeight*100,1.0f,0.0f,0.0f);
        gl.glRotatef(transz,0.0f,0.0f,1.0f);
        gl.glScalef(scale,scale,scale);


        gl.glTranslatef(movex,0.0f,0.0f);
        gl.glTranslatef(0.0f,movey,0.0f);
    }


    public void endScene()  {
        gl.glLoadIdentity();
    }

    public void beforeRenderObject(int x, int y) {
        gl.glTranslatef(posX+x,posY+y, 0.0f);
    }

    public void afterRenderObject(int x, int y) {
        gl.glTranslatef(-posX-x,-posY-y,0.0f);
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
        float x = 0.5f*(float)columns+0.5f;
        float y = 0.5f*(float)rows+0.5f;
    }

    private void drawWorldTable() {
    }
}