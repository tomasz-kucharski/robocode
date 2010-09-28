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
	nogi0 = 31;
	nogi1 = 32;
	nogi2 = 33;
	rece0 = 34;
	rece1 = 35;
	rece2 = 36;
	czulki = 37;
	robotsprzata = 38;
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
		move = 1.0f - ((float)robot->getMoveState())/Robot::MOVESTATE;
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
	if(robot->getCleanState() != 0)
	{
		glCallList(robotsprzata);
	}
	else
	{
		glCallList(list);
		if(robot->getMoveState() != 0)
		{
			if(robot->getMoveState()%2 == 0)
			{
				glCallList(nogi1);
				glCallList(rece1);
			}
			else
			{
				glCallList(nogi2);
				glCallList(rece2);
			}
		}
		else
		{
			glCallList(nogi0);
			glCallList(rece0);
		}
		if(robot->getScanerState() != 0)
			glCallList(czulki);
	}
	glPopMatrix();
}

void RobotGL::init()
{
//	loadGLTextures();
	glNewList(list,GL_COMPILE);
	{	
#include "robotbody"
	}
	glEndList();
	glNewList(nogi0,GL_COMPILE);
	{
#include "nogi0"
	}
	glEndList();
	glNewList(nogi1,GL_COMPILE);
	{
#include "nogi1"
	}
	glEndList();
	glNewList(nogi2,GL_COMPILE);
	{
#include "nogi2"
	}
	glEndList();
	glNewList(rece2,GL_COMPILE);
	{
#include "rece2"
	}
	glEndList();
	glNewList(rece0,GL_COMPILE);
	{
#include "rece0"
	}
	glEndList();
	glNewList(rece1,GL_COMPILE);
	{
#include "rece1"
	}
	glEndList();
	glNewList(czulki,GL_COMPILE);
	{
#include "czulki"
	}
	glEndList();
	glNewList(robotsprzata,GL_COMPILE);
	{
#include "robotsprzata"
	}
	glEndList();
}


