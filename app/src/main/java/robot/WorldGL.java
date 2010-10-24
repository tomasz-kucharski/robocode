package robot;

import robot.object.WorldObject;
import robot.object.opengl.*;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

public class WorldGL {
    GL gl;
    GLU glu = new GLU();

    TableGL tableGL;
    RobotGL robotGL;
    FloorGL floorGL;
    WallGL wallGL;
    RubbishGL rubbishGL;
    FurnitureGL furnitureGL;
    DepotGL depotGL;
    LightGL lightGL;

    private float movex;
    private float movey;
    private float movez;

    private float transx;
    private float transy;
    private float transz;
    private float scale = 1.0f;

    private int columns;
    private int rows;

    private float posX;
    private float posY;
    private boolean wireframe;
    private boolean antialiasing;
    private float rotateTest1;
    private int screenWidth;
    private int screenHeight;

    public WorldGL(int columns, int rows) {

        this.columns = columns;
        this.rows = rows;

        posX = -0.5f*(columns-1);
        posY = -0.5f*(rows-1);

        movex = 0.0f;
        movey = 0.0f;
        movez = 0.0f;

        transx = 0.0f;
        transy = 0.0f;
        transz = 0.0f;

        scale = 1.0f;

        tableGL = new TableGL(rows,columns);
        robotGL = new RobotGL();
        floorGL = new FloorGL();
        wallGL = new WallGL();
        rubbishGL = new RubbishGL();
        depotGL = new DepotGL();
        furnitureGL = new FurnitureGL();
        lightGL = new LightGL();
    }

    public void setGl(GL gl) {
        this.gl = gl;
    }

    public void ReSizeGLScene(int  width, int height) {
        screenWidth = width;
        screenHeight = height;


        gl.glViewport(0, 0, width, height);
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

    public void InitGL(int width, int height)
    {
//        ReSizeGLScene(width,height);

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


        floorGL.init(gl);
        wallGL.init(gl);
        rubbishGL.init(gl);
        depotGL.init(gl);
        robotGL.init(gl);
        furnitureGL.init(gl);
        tableGL.init(gl);



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
        new TriangleGL(1).draw(gl,null);
        //---  RYSUJ PODKLAD
        tableGL.draw(gl,null);
        //---  USTAW WYSOKOSC PONAD STOLEM
        gl.glTranslatef(0.0f,0.0f,0.3f);
        gl.glColor3f(1f,1f,1f);

        onAntialiasing();
        onWireframe();

    }

    private void initRenderLocation() {
        gl.glTranslatef(0.0f,0.0f,-8.0f);
        gl.glTranslatef(0.0f,0.0f,movez);
        gl.glRotatef(transx,0.0f,1.0f,0.0f);
        gl.glRotatef(transy,1.0f,0.0f,0.0f);
        gl.glRotatef(transz,0.0f,0.0f,1.0f);
        gl.glScalef(scale,scale,scale);


        gl.glTranslatef(movex,0.0f,0.0f);
        gl.glTranslatef(0.0f,movey,0.0f);
    }


    public void endScene()  {
        gl.glLoadIdentity();
    }

    public boolean renderObject(int x, int y, WorldObject object) {
        int className = object.getClassName();

        gl.glTranslatef(posX+x,posY+y, 0.0f);
        if (className == WorldObjectVerifier.FLOOR.getIntValue())
            floorGL.draw(gl,object);
        else if (className == WorldObjectVerifier.ROBOT.getIntValue())
            robotGL.draw(gl,object);
        else if (className == WorldObjectVerifier.WALL.getIntValue())
            wallGL.draw(gl,object);
        else if (className == WorldObjectVerifier.DEPOT.getIntValue())
            depotGL.draw(gl,object);
        else if (className == WorldObjectVerifier.RUBBISH.getIntValue())
            rubbishGL.draw(gl,object);
        else if (className == WorldObjectVerifier.FURNITURE.getIntValue())
            furnitureGL.draw(gl,object);
        else return false;

        gl.glTranslatef(-posX-x,-posY-y,0.0f);

        return true;
    }

    public void setWireframe(boolean wireframe) {
        this.wireframe = wireframe;
    }

    public void setAntialiasing(boolean antialiasing) {
        this.antialiasing = antialiasing;
    }

    public void onAntialiasing() {
        if(antialiasing) {
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

    public void onSetXRotate(float x) {
        transx += x/screenWidth * 100;
    }

    public void onSetYRotate(float y) {
        transy += y/screenHeight *100;
    }

    public void onSetScale(float ile) {
        scale = ile;
    }

    public void onWireframe() {
        if(wireframe)
            gl.glPolygonMode(GL.GL_FRONT_AND_BACK,GL.GL_LINE);
        else
            gl.glPolygonMode(GL.GL_FRONT_AND_BACK,GL.GL_FILL);
    }

    public void onSetXYZMove(float x, float y, float z) {
        movex += x;
        movey += y;
        movez += z;
    }

    public void onXMove(float x) {
        movex += x;
    }

    public void onYMove(float y) {
        movey += y;
    }

    public void onZMove(float z) {
        movez += z;
    }
}