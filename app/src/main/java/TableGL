// TableGL.cpp: implementation of the TableGL class.
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

TableGL::TableGL(int columns, int rows)
{
	x = 0.5f*(float)columns+0.5f;;
	y = 0.5f*(float)rows+0.5f;
	list = 10;
}

TableGL::~TableGL()
{

}

void TableGL::draw(WorldObject* object)
{	
	glCallList(list);
}

void TableGL::init()
{
	loadGLTextures("table.bmp");
	glNewList(list,GL_COMPILE);

		glColor3f(1.0f,1.0f,1.0f);
	glBindTexture(GL_TEXTURE_2D,texture[0]);
	glBegin(GL_QUADS);
		glNormal3f(0.0f,0.0f,1.0f);
		glTexCoord2f(1.0f,1.0f);glVertex3f(x,y,0.0f);
		glTexCoord2f(1.0f,0.0f);glVertex3f(x,-y,0.0f);
		glTexCoord2f(0.0f,0.0f);glVertex3f(-x,-y,0.0f);
		glTexCoord2f(0.0f,1.0f);glVertex3f(-x,y,0.0f);
	glEnd();
	glEndList();
}
