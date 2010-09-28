// Floor.cpp: implementation of the Floor class.
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

Floor::Floor(Position* p, int slippery)
: WorldObject(WorldObjectVerifier::FLOOR,p,true,false,false,true)
{	
	if( (slippery > 0 ) && (slippery <= 10 ) )
		this->slippery = slippery;
	else this->slippery = 1;
}

Floor::~Floor()
{

}

bool Floor::conditionalMovement(WorldObject* const worldObject, 
			const int direction,const int MaxPower, int& usedPower)
{
	usedPower += slippery;
	return true;
}

void Floor::evolve()
{

}
