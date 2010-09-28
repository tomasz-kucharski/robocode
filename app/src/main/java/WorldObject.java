// WorldObject.cpp: implementation of the WorldObject class.
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

WorldObject::WorldObject(int className, Position* p, bool flat, bool intelligent, 
						 bool movable, bool slide)
{
	this->flat = flat;
	this->intelligent = intelligent;
	this->movable = movable;
	this->slide = slide;
	this->className = className;

	this->p = new Position(p->x, p->y);
	deleteMe = false;
	moved = false;
	world = NULL;
}

WorldObject::~WorldObject()
{
	delete p;
}

bool WorldObject::isSlide()
{
	return slide;
}

bool WorldObject::isFlat()
{
	return flat;
}

bool WorldObject::isMovable()
{
	return movable;
}

bool WorldObject::isIntelligent()
{
	return intelligent;
}

int WorldObject::getClassName()
{
	return className;
}

World* WorldObject::getWorld()
{
	return world;
}

Position* const WorldObject::getPosition()
{
	return this->p;
}
