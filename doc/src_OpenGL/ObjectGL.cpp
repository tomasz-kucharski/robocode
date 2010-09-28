// ObjectGL.cpp: implementation of the ObjectGL class.
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

ObjectGL::ObjectGL()
{
	texture1 = NULL;
}

ObjectGL::~ObjectGL()
{
	if (texture1 != NULL)
	{
		delete[] texture1->data;
		delete texture1;
	}
}

GLvoid ObjectGL::loadGLTextures(char *file)
{
	texture1 = auxDIBImageLoad(file);
	glGenTextures(1,&texture[0]);
	glBindTexture(GL_TEXTURE_2D,texture[0]);
	glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MAG_FILTER,GL_LINEAR);
	glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MIN_FILTER,GL_LINEAR);
	glTexImage2D(GL_TEXTURE_2D,0,3,texture1->sizeX,texture1->sizeY,0,GL_RGB,GL_UNSIGNED_BYTE,texture1->data);	
}
