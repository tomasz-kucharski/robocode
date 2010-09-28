// DepotGL.cpp: implementation of the DepotGL class.
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

DepotGL::DepotGL()
{
	list = 60;
}

DepotGL::~DepotGL()
{

}

void DepotGL::draw(WorldObject* object)
{
	glCallList(list);
}

void DepotGL::init()
{
	glNewList(list,GL_COMPILE);
		#include "depot"
	glEndList();
}