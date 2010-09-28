// Wall.cpp: implementation of the Wall class.
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

Wall::Wall(Position* p, int strength)
:WorldObject(WorldObjectVerifier::WALL,p,false, false, false, false)
{
	if (strength > 100) 
		this->strength = strength;
}

Wall::~Wall()
{

}

void Wall::evolve() 
{

}

bool Wall::conditionalMovement(WorldObject* const worldObject, 
		const int direction, const int maxPower, int& usedPower)
{
	usedPower = maxPower;
	return false;	// -1 
}