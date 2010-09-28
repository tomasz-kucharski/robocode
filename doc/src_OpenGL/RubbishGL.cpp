// RubbishGL.cpp: implementation of the RubbishGL class.
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

RubbishGL::RubbishGL()
{
	list = 20;
}

RubbishGL::~RubbishGL()
{

}

void RubbishGL::draw(WorldObject* object)
{
	float mx;
	rubbish = (Rubbish*)object;
	glPushMatrix();
	mx = Direction::computeRotation(rubbish->getDirection());
	glRotatef(mx,0.0f,0.0f,1.0f); 
	glCallList(list);
	glPopMatrix();
}

void RubbishGL::init()
{
	glNewList(list, GL_COMPILE);
#include "rubbish"
	glEndList();
}
