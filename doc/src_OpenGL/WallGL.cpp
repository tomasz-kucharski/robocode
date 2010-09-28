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
glNewList(list,GL_COMPILE);

#include "slup"
glEndList();
}
