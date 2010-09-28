// RobotPath.cpp: implementation of the RobotPath class.
//
//////////////////////////////////////////////////////////////////////

#include "stdafx.h"
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
const int RobotPath::LEFT = 1;
const int RobotPath::RIGHT = 2;
const int RobotPath::MOVE = 3;

RobotPath::RobotPath()
{

}

RobotPath::~RobotPath()
{
	clean();
}

void RobotPath::push(int value)
{
	PathObject* obj = new PathObject(value);
	this->add(obj,true);
	TRACE("PATH ADD : %d\n",value);
}

int RobotPath::pop()
{
	int value;
	PathObject* obj;
	if(!setToFirst()) 
		return -1;
	obj = (PathObject*)getObject();
	value = obj->value;
	remove(obj);
	delete obj;
	TRACE("POP : %d\n",value);
	return value;
}

void RobotPath::clean()
{
	PathObject* obj;
	setToFirst();
	while( obj = (PathObject*)getNext() )
		delete obj;
}
