// Depot.cpp: implementation of the Depot class.
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

Depot::Depot(Position* p, int slippery)
: WorldObject(WorldObjectVerifier::DEPOT,p,true,false,false,true)
{	
	if( efficiency < 0 )
		this->efficiency = 100;
	else 
		this->efficiency = efficiency;
	lighted = false;
}

Depot::~Depot()
{

}

bool Depot::conditionalMovement(WorldObject* const worldObject, 
			const int direction,const int MaxPower, int& usedPower)
{
	return true;
}

void Depot::evolve()
{
	lighted = !lighted;
}

bool Depot::getLighted()
{
	return lighted;
}
