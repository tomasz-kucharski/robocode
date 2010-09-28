// Rubbish.cpp: implementation of the Rubbish class.
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

Rubbish::Rubbish(Position* p, int quantity)
: WorldObject(WorldObjectVerifier::RUBBISH,p,true,false,false,true)
{
	if( quantity <= 0 )
		this->quantity = 1;
	else 
		this->quantity = quantity;
	type = 1;           //POPRAWIC
	direction = Direction::randDirection();
}

Rubbish::~Rubbish()
{

}

void Rubbish::evolve()
{

}

bool Rubbish::conditionalMovement(WorldObject* const worldObject, const int direction,
		const int MaxPower,	int& usedPower)
{
	return true;
}

void Rubbish::cleaning(const int maxPower, int &usedPower)
{
/*	int maxCleaned,needEnergy;
	if ( type == 1 ) 
		maxCleaned = 20;

	if (maxCleaned > quantity)	
		maxCleaned = quantity;
	
	needEnergy = 2 * maxCleaned;

	if (needEnergy > maxPower) {
		quantity -= maxPower/2;
		usedPower = maxPower;
	}
	else {
		usedPower = needEnergy;
		quantity -= maxCleaned;*/
	quantity -= 20;

		if(quantity <= 0)
			getWorld()->deleteMe(this);
}

int Rubbish::getDirection()
{
	return direction;
}
