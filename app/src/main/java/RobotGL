// RobotGL.cpp: implementation of the RobotGL class.
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

RobotGL::RobotGL()
{
	list = 30;
}

RobotGL::~RobotGL()
{

}

void RobotGL::draw(WorldObject* object)
{
	int direction;
	float mx;
	float move;
	robot = (Robot*)object;
	
	direction = robot->getDirection();
	mx = Direction::computeRotation(direction);

	glPushMatrix();
	if (robot->getMoveState() != 0 )
	{
		move = 1.0f - ((float)robot->getMoveState())/5.0f;;
		if (direction == Direction::EAST)
			glTranslatef(-move,0.0f,0.0f);
		if (direction == Direction::WEST)
			glTranslatef(move,0.0f,0.0f);
		if (direction == Direction::SOUTH)
			glTranslatef(0.0f,-move,0.0f);
		if (direction == Direction::NORTH)
			glTranslatef(0.0f,move,0.0f);
	}	
	glRotatef(mx,0.0f,0.0f,1.0f); 
	glCallList(list);
	
	glPopMatrix();
}

void RobotGL::init()
{
//	loadGLTextures();
	glNewList(list,GL_COMPILE);
	#include "robot"
	glEndList();
}


