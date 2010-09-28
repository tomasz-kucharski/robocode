// Furniture.cpp: implementation of the Furniture class.
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

Furniture::Furniture(Position* p, int weight)
: WorldObject(WorldObjectVerifier::FURNITURE,p,false,false,true,true)
{	
	if( weight < 0 )
		this->weight = 1;
	else 
		this->weight = weight;
	direction = Direction::randDirection();
	type = rand() % 4;
	
}

Furniture::~Furniture()
{

}
void Furniture::evolve()
{

}

bool Furniture::conditionalMovement(WorldObject* const worldObject, const int direction,
		const int MaxPower,	int& usedPower)
{
	usedPower += weight;
	return getWorld()->move(this,direction,MaxPower,usedPower);
}

int Furniture::getDirection()
{
	return direction;
}

int Furniture::getType()
{
	return type;
}
