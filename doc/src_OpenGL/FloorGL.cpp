// FloorGL.cpp: implementation of the FloorGL class.
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

FloorGL::FloorGL()
{
	list = 50;
}

FloorGL::~FloorGL()
{
}

void FloorGL::draw(WorldObject* object)
{
	glCallList(list);
}

void FloorGL::init()
{
	loadGLTextures("floor2.bmp");
	glNewList(list,GL_COMPILE);
	glEnable(GL_TEXTURE_2D);

	float dechaAmb[] = {1.0f,1.0f,1.0f,1.0f};
float dechaDif[] = {1.0f,1.0f,1.0f,1.0f};
float dechaSpc[] = {1.0f,1.0f,1.0f,1.0f};
float dechaShn[] = {128.000f};

glBegin(GL_TRIANGLES);
glMaterialfv(GL_FRONT_AND_BACK, GL_AMBIENT, dechaAmb);
glMaterialfv(GL_FRONT_AND_BACK, GL_DIFFUSE, dechaDif);
glMaterialfv(GL_FRONT_AND_BACK, GL_SPECULAR, dechaSpc);
glMaterialfv(GL_FRONT_AND_BACK, GL_SHININESS, dechaShn);

	glBindTexture(GL_TEXTURE_2D,texture[0]);

	glBegin(GL_QUADS); 	
	glNormal3f(0.0f,0.0f,1.0f);
		glTexCoord2f(1.0f,0.0f); 	glVertex3f(0.48f,-0.48f,0.0f);
		glTexCoord2f(1.0f,1.0f); 	glVertex3f(0.48f,0.48f,0.0f);
		glTexCoord2f(0.0f,1.0f); 	glVertex3f(-0.48f,0.48f,0.0f);
		glTexCoord2f(0.0f,0.0f);	glVertex3f(-0.48f,-0.48f,0.0f);
	glEnd();
	glDisable(GL_TEXTURE_2D);
	glEndList();
}
