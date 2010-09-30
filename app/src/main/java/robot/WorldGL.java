package robot;

import robot.object.WorldObject;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

public class WorldGL {
    GL gl;
    GLU glu = new GLU();

	TableGL tableGL;
	RobotGL robotGL;
	FloorGL floorGL;
	WallGL  wallGL;
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
	private float scale;

	private int columns;
	private int rows;

	private float posX;
	private float posY;

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

//	tableGL = new TableGL(columns,rows);
//	robotGL = new RobotGL();
//	floorGL = new FloorGL();
//	wallGL = new WallGL();
//	rubbishGL = new RubbishGL();
//	depotGL = new DepotGL();
//	furnitureGL = new FurnitureGL();
//
//	lightGL = new LightGL();
}

public void ReSizeGLScene(int  width, int height)
{
	if (height == 0)
		height = 1;
	gl.glViewport(0,0,width,height);
	gl.glMatrixMode(GL.GL_PROJECTION);
		gl.glLoadIdentity();
		glu.gluPerspective(45.0f,(float)width/(float)height,0.1f,100.0f);
	gl.glMatrixMode(GL.GL_MODELVIEW);
		gl.glLoadIdentity();
		gl.glDrawBuffer(GL.GL_BACK);
}

public void InitGL()
{
	gl.glClearColor(0.0f,0.0f,0.0f,0.0f);
	gl.glClearDepth(1.0f);
//	glDepthFunc(GL_LESS);
	gl.glEnable(GL.GL_DEPTH_TEST);
	gl.glShadeModel(GL.GL_SMOOTH);
	gl.glCullFace(GL.GL_FRONT);
//	glHint(GL_PERSPECTIVE_CORRECTION_HINT,GL_NICEST);
//	glMatrixMode(GL_PROJECTION);
//		glLoadIdentity();
//		gluPerspective(45.0f,(GLfloat)width/(GLfloat)height,0.1f,100.0f);
//	glMatrixMode(GL_MODELVIEW);

	gl.glEnable(GL.GL_DEPTH_TEST);
	gl.glEnable(GL.GL_TEXTURE_2D);
	gl.glEnable(GL.GL_LIGHTING);			// wlaczenie swiatla w ogole
//	glEnable(GL_COLOR_MATERIAL);  // wlaczenie materialu w proces tworzenia koloru
//ZALADOWANIE TEKSTUR
	gl.glLightfv(GL.GL_LIGHT0,GL.GL_AMBIENT,lightGL.LightAmbient);
	gl.glLightfv(GL.GL_LIGHT0,GL.GL_DIFFUSE,lightGL.LightDiffuse);
	gl.glLightfv(GL.GL_LIGHT0,GL.GL_POSITION,lightGL.LightPosition);
	gl.glLightfv(GL.GL_LIGHT0,GL.GL_SPECULAR, lightGL.LightSpecular);
	gl.glEnable(GL.GL_LIGHT0);			// wlaczenie oswietlenia LIGHT0

	floorGL.init(gl);
	wallGL.init(gl);
	furnitureGL.init(gl);
	tableGL.init(gl);
	depotGL.init(gl);
	rubbishGL.init(gl);
	robotGL.init(gl);

}

public void beginScene(int x, int y, int direction) {
	gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
 	gl.glLoadIdentity();


	gl.glRotatef(-90.0f,0.0f,1.0f,0.0f);
	gl.glRotatef(-90.0f,1.0f,0.0f,0.0f);
	gl.glRotatef(35.0f,0.0f,1.0f,0.0f);
	gl.glRotatef(-Direction.computeRotation(direction),0.0f,0.0f,1.0f); //direction
	float X = -posX - (float)x;//posX-(float)x;
	float Y = -posY - (float)y;//posY -(float)y;
	gl.glTranslatef(X ,Y ,-2.0f);

	gl.glRotatef(transx,0.0f,1.0f,0.0f);
	gl.glRotatef(transy,1.0f,0.0f,0.0f);
	gl.glRotatef(transz,0.0f,0.0f,1.0f);
	gl.glScalef(scale,scale,scale);

	gl.glTranslatef(movex,movey,movez);

		//---  RYSUJ PODKLAD 
 	tableGL.draw(gl,null);
		//---  USTAW WYSOKOSC PONAD STOLEM
	gl.glTranslatef(0.0f,0.0f,0.3f);
}

public void beginScene()
{
	gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
 	gl.glLoadIdentity();

		//---  WYKONAJ TRANSFORMACJE WYKONANE PRZEZ UZYTKOWNIKA
	gl.glTranslatef(0.0f,0.0f,-15.0f);
	gl.glTranslatef(0.0f,0.0f,movez);
	gl.glRotatef(transx,0.0f,1.0f,0.0f);
 	gl.glRotatef(transy,1.0f,0.0f,0.0f);
 	gl.glRotatef(transz,0.0f,0.0f,1.0f);
 	gl.glScalef(scale,scale,scale);


	gl.glTranslatef(movex,0.0f,0.0f);
	gl.glTranslatef(0.0f,movey,0.0f);
		//---  RYSUJ PODKLAD 
 	tableGL.draw(gl,null);
		//---  USTAW WYSOKOSC PONAD STOLEM
	gl.glTranslatef(0.0f,0.0f,0.3f);

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

public void onAntialiasing(boolean isAntialiasing) {
	if(isAntialiasing) {
		gl.glEnable(GL.GL_LINE_SMOOTH);
		gl.glEnable(GL.GL_BLEND);
		gl.glBlendFunc(GL.GL_SRC_ALPHA,GL.GL_ONE_MINUS_SRC_ALPHA);
		gl.glHint(GL.GL_LINE_SMOOTH_HINT,GL.GL_NICEST);
		gl.glLineWidth(1.0f);
	}
	else {
		gl.glDisable(GL.GL_LINE_SMOOTH);
		gl.glDisable(GL.GL_BLEND);
		gl.glBlendFunc(GL.GL_SRC_ALPHA,GL.GL_ONE_MINUS_SRC_ALPHA);
		gl.glHint(GL.GL_LINE_SMOOTH_HINT,GL.GL_NICEST);
		gl.glLineWidth(0.5f);
	}
}

public void onSetXYZRotate(float x, float y, float z) {
	transx += x;
	transy += y;
	transz += z;
}

public void onSetScale(float ile) {
	scale = ile;
}

public void onWireframe(boolean isWireframe) {
	if(isWireframe)
		gl.glPolygonMode(GL.GL_FRONT_AND_BACK,GL.GL_LINE);
	else
		gl.glPolygonMode(GL.GL_FRONT_AND_BACK,GL.GL_FILL);
}

public void onSetXYZMove(float x, float y, float z) {
	movex += x;
	movey += y;
	movez += z;
}

}