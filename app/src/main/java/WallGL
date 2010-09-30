// WallGL.cpp: implementation of the WallGL class.
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

//////////////////////////////////////////////////////////////////////
// Construction/Destruction
//////////////////////////////////////////////////////////////////////

WallGL::WallGL()
{
	list = 70;
}

WallGL::~WallGL()
{

}

void WallGL::draw(WorldObject* object)
{
	glCallList(list);	
}

void WallGL::init()
{
//this->loadGLTextures("wall.bmp");
glNewList(list,GL_COMPILE);

/*		glColor3f(0.5f,0.5f,0.5f);
	glBindTexture(GL_TEXTURE_2D,texture[0]);
	glBegin(GL_QUADS);
	//gora				
		glNormal3f(0.0f,0.0f,1.0f);
		//	glColor3f(0.8f,0.1f,0.1f);
			glTexCoord2f(0.0f,0.0f);	glVertex3f(-0.5f,-0.5f,1.0f);
		//	glColor3f(0.8f,0.05f,0.05f);
			glTexCoord2f(0.0f,1.0f);	glVertex3f(-0.5f,0.5f,1.0f);
		//	glColor3f(0.75f,0.15f,0.15f);
			glTexCoord2f(1.0f,1.0f);	glVertex3f(0.5f,0.5f,1.0f);
		//	glColor3f(0.6f,0.0f,0.0f);	
			glTexCoord2f(1.0f,0.0f);	glVertex3f(0.5f,-0.5f,1.0f);
	//przod
		glNormal3f(0.0f,1.0f,0.0f);
		//	glColor3f(0.5f,0.1f,0.1f);	
			glTexCoord2f(0.0f,0.0f);	glVertex3f(-0.5f,0.5f,0.0f);
		//	glColor3f(0.5f,0.1f,0.1f);	
			glTexCoord2f(1.0f,0.0f);	glVertex3f(0.5f,0.5f,0.0f);
		//	glColor3f(0.9f,0.2f,0.2f);	
			glTexCoord2f(1.0f,1.0f);	glVertex3f(0.5f,0.5f,1.0f);
		//	glColor3f(0.9f,0.2f,0.2f);	
			glTexCoord2f(0.0f,1.0f);	glVertex3f(-0.5f,0.5f,1.0f);
	//tyl
		glNormal3f(0.0f,-1.0f,0.0f);
		//	glColor3f(0.5f,0.1f,0.1f);	
			glTexCoord2f(0.0f,0.0f);	glVertex3f(-0.5f,-0.5f,0.0f);
		//	glColor3f(0.5f,0.1f,0.1f);	
			glTexCoord2f(1.0f,0.0f);	glVertex3f(0.5f,-0.5f,0.0f);
		//	glColor3f(0.9f,0.2f,0.2f);	
			glTexCoord2f(1.0f,1.0f);	glVertex3f(0.5f,-0.5f,1.0f);
		//	glColor3f(0.9f,0.2f,0.2f);	
			glTexCoord2f(0.0f,1.0f);	glVertex3f(-0.5f,-0.5f,1.0f);
	//lewy
		glNormal3f(-1.0f,0.0f,0.0f);
		//	glColor3f(0.5f,0.1f,0.1f);	
			glTexCoord2f(0.0f,0.0f);	glVertex3f(-0.5f,-0.5f,0.0f);
		//	glColor3f(0.9f,0.2f,0.2f);	
			glTexCoord2f(0.0f,1.0f);	glVertex3f(-0.5f,-0.5f,1.0f);
		//	glColor3f(0.9f,0.2f,0.2f);	
			glTexCoord2f(1.0f,1.0f);	glVertex3f(-0.5f,0.5f,1.0f);
		//	glColor3f(0.5f,0.1f,0.1f);	
			glTexCoord2f(1.0f,0.0f);	glVertex3f(-0.5f,0.5f,0.0f);
	//prawy
		glNormal3f(1.0f,0.0f,0.0f);
		//	glColor3f(0.5f,0.1f,0.1f);	
			glTexCoord2f(0.0f,0.0f);	glVertex3f(0.5f,-0.5f,0.0f);
		//	glColor3f(0.9f,0.2f,0.2f);	
			glTexCoord2f(0.0f,1.0f);	glVertex3f(0.5f,-0.5f,1.0f);
		//	glColor3f(0.9f,0.2f,0.2f);	
			glTexCoord2f(1.0f,1.0f);	glVertex3f(0.5f,0.5f,1.0f);
		//	glColor3f(0.5f,0.1f,0.1f);	
			glTexCoord2f(1.0f,0.0f);	glVertex3f(0.5f,0.5f,0.0f);
	glEnd();
*/
#include "wall"
glEndList();
}
