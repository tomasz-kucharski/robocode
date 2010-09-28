// WorldGL.cpp: implementation of the OpenGL class.
//
//////////////////////////////////////////////////////////////////////
#include "Stdafx.h"
#include "OpenGL.h"

#include "Inkludy.h"

#ifdef _DEBUG
#undef THIS_FILE
static char THIS_FILE[]=__FILE__;
#define new DEBUG_NEW
#endif

WorldGL::WorldGL(int columns, int rows)
{
	this->columns = columns;
	this->rows = rows;

	posX = -0.5f*(columns-1);
	posY = -0.5f*(rows-1);

	movex = 0.0f;
	movey = 0.0f;
	movez = 0.0f;
	
	transx = 0.0f;
	transy = 0.0f;
	transz = 0.0f;

	scale = 1.0f;

	tableGL = new TableGL(columns,rows);
	robotGL = new RobotGL();
	floorGL = new FloorGL();
	wallGL = new WallGL();
	rubbishGL = new RubbishGL();
	depotGL = new DepotGL();
	furnitureGL = new FurnitureGL();

	lightGL = new LightGL();
}

WorldGL::~WorldGL()
{
	delete tableGL;
	delete robotGL;
	delete floorGL;
	delete wallGL;
	delete rubbishGL;
	delete furnitureGL;
	delete depotGL;
	delete lightGL;
}


GLvoid WorldGL::ReSizeGLScene(GLsizei width, GLsizei height)
{
	if (height == 0)
		height = 1;
	glViewport(0,0,width,height); 
	glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluPerspective(45.0f,(GLfloat)width/(GLfloat)height,0.1f,100.0f);
	glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glDrawBuffer(GL_BACK);
}

GLvoid WorldGL::InitGL()
{
	glClearColor(0.0f,0.0f,0.0f,0.0f);
	glClearDepth(1.0f);
	glEnable(GL_DEPTH_TEST);		
	glShadeModel(GL_SMOOTH);
	glEnable(GL_CULL_FACE);
	glCullFace(GL_BACK);

	glEnable(GL_LIGHTING);			// wlaczenie swiatla w ogole
	glLightfv(GL_LIGHT0,GL_AMBIENT,lightGL->LightAmbient);
	glLightfv(GL_LIGHT0,GL_DIFFUSE,lightGL->LightDiffuse);
	glLightfv(GL_LIGHT0,GL_POSITION,lightGL->LightPosition);
	glLightfv(GL_LIGHT0,GL_SPECULAR, lightGL->LightSpecular);
//	glLightfv(GL_LIGHT0,GL_SHININESS, lightGL->LightShininess);
	glEnable(GL_LIGHT0);			// wlaczenie oswietlenia LIGHT0

	floorGL->init();
	wallGL->init();
	furnitureGL->init();
	tableGL->init();
	depotGL->init();
	rubbishGL->init();
	robotGL->init();

}

void WorldGL::beginScene(int x, int y, int direction)
{
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
 	glLoadIdentity();


	glRotatef(-90.0f,0.0f,1.0f,0.0f);
	glRotatef(-90.0f,1.0f,0.0f,0.0f);
	glRotatef(35.0f,0.0f,1.0f,0.0f);
	glRotatef(-Direction::computeRotation(direction),0.0f,0.0f,1.0f); //direction
	float X = -posX - (float)x;//posX-(float)x;
	float Y = -posY - (float)y;//posY -(float)y;
	TRACE("X:%f, Y:%f \n",X,Y); 
	glTranslatef(X ,Y ,-2.0f);

	glRotatef(transx,0.0f,1.0f,0.0f);
	glRotatef(transy,1.0f,0.0f,0.0f);
	glRotatef(transz,0.0f,0.0f,1.0f);
	glScalef(scale,scale,scale);

	glTranslatef(movex,movey,movez);

		//---  RYSUJ PODKLAD 
 	tableGL->draw(NULL);
		//---  USTAW WYSOKOSC PONAD STOLEM
	glTranslatef(0.0f,0.0f,0.3f);
}

void WorldGL::beginScene()
{
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
 	glLoadIdentity();

		//---  WYKONAJ TRANSFORMACJE WYKONANE PRZEZ UZYTKOWNIKA
	glTranslatef(0.0f,0.0f,-15.0f);
	glTranslatef(0.0f,0.0f,movez);
	glRotatef(transx,0.0f,1.0f,0.0f);
 	glRotatef(transy,1.0f,0.0f,0.0f);
 	glRotatef(transz,0.0f,0.0f,1.0f);
 	glScalef(scale,scale,scale);


	glTranslatef(movex,0.0f,0.0f);
	glTranslatef(0.0f,movey,0.0f);
		//---  RYSUJ PODKLAD 
 	tableGL->draw(NULL);
		//---  USTAW WYSOKOSC PONAD STOLEM
	glTranslatef(0.0f,0.0f,0.3f);

}

void WorldGL::endScene() 
{
 	glLoadIdentity();
}

bool WorldGL::renderObject(int x, int y, WorldObject* object)
{
	int className = object->getClassName();

	glTranslatef(posX+x,posY+y, 0.0f);
	if (className == WorldObjectVerifier::FLOOR)
		floorGL->draw(object);
	else if (className == WorldObjectVerifier::ROBOT)
		robotGL->draw(object);
	else if (className == WorldObjectVerifier::WALL)
		wallGL->draw(object);
	else if (className == WorldObjectVerifier::DEPOT)
		depotGL->draw(object);
	else if (className == WorldObjectVerifier::RUBBISH)
		rubbishGL->draw(object);
	else if (className == WorldObjectVerifier::FURNITURE)
		furnitureGL->draw(object);
	else return false;
	
	glTranslatef(-posX-x,-posY-y,0.0f); 

	return true;
}

void WorldGL::onAntialiasing(BOOL isAntialiasing)
{	
	if(isAntialiasing) {
		glEnable(GL_LINE_SMOOTH);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA,GL_ONE_MINUS_SRC_ALPHA);
		glHint(GL_LINE_SMOOTH_HINT,GL_NICEST);
		glLineWidth(1.0);
	}
	else {
		glDisable(GL_LINE_SMOOTH);
		glDisable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA,GL_ONE_MINUS_SRC_ALPHA);
		glHint(GL_LINE_SMOOTH_HINT,GL_NICEST);
		glLineWidth(0.5);
	};
}

void WorldGL::onSetXYZRotate(float x, float y, float z)
{
	transx += x;
	transy += y;
	transz += z;
}

void WorldGL::onSetScale(float ile)
{
	scale = ile;
}

void WorldGL::onWireframe(BOOL isWireframe)
{
	if(isWireframe)
		glPolygonMode(GL_FRONT_AND_BACK,GL_LINE);
	else
		glPolygonMode(GL_FRONT,GL_FILL);
}

void WorldGL::onSetXYZMove(float x, float y, float z)
{
	movex += x;
	movey += y;
	movez += z;
}
