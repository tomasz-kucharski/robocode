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
//const int RobotPath::START = 4;
//const int RobotPath::STOP = 5;

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
	this->add(obj,false);
	switch(value)
	{
	case LEFT:
			TRACE("Path LEFT add\n");
		break;
	case RIGHT:
			TRACE("Path RIGHT add\n");
		break;
	case MOVE:
			TRACE("Path MOVE add\n");
		break;
//	case STOP:
//			TRACE("Path STOP add\n");
//		break;
///	case START:
//			TRACE("Path START add\n");
//		break;
	}
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

	switch(value)
	{
	case LEFT:
			TRACE("Path LEFT pop\n");
		break;
	case RIGHT:
			TRACE("Path RIGHT pop\n");
		break;
	case MOVE:
			TRACE("Path MOVE pop\n");
		break;
//	case STOP:
//			TRACE("Path STOP pop\n");
//		break;
//	case START:
//			TRACE("Path START pop\n");
//		break;
	}
	return value;
}

void RobotPath::clean()
{
	PathObject* obj;
	setToFirst();
	while( obj = (PathObject*)getNext() )
		delete obj;
}
