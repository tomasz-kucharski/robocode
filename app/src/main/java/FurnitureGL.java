// FurnitureGL.cpp: implementation of the FurnitureGL class.
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

FurnitureGL::FurnitureGL()
{
	list = 40;
	list1 = 41;
	list2 = 42;
	list3 = 43;
}

FurnitureGL::~FurnitureGL()
{

}

void FurnitureGL::draw(WorldObject* object)
{
	float mx;
	furniture = (Furniture*)object;
	glPushMatrix();
	mx = Direction::computeRotation(furniture->getDirection());
	glRotatef(mx,0.0f,0.0f,1.0f); 
	switch (furniture->getType()) {
	case 0: glCallList(list);
			break;
	case 1: glCallList(list1);
			break;
	case 2: glCallList(list2);
			break;
	case 3: glCallList(list3);
			break;
	}
	glPopMatrix();
}

void FurnitureGL::init()
{
	glNewList(list,GL_COMPILE);
		#include "furniture"
	glEndList();
	glNewList(list1,GL_COMPILE);
		#include "furniture2"
	glEndList();
	glNewList(list2,GL_COMPILE);
		#include "furniture3"
	glEndList();
	glNewList(list3,GL_COMPILE);
		#include "furniture4"
	glEndList();
}
