// Furniture.h: interface for the Furniture class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_FURNITURE_H__BE50ACE6_571B_11D6_A89A_0010A484ECEC__INCLUDED_)
#define AFX_FURNITURE_H__BE50ACE6_571B_11D6_A89A_0010A484ECEC__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include "WorldObject.h"

class Position;

class Furniture : public WorldObject  
{
private:
	int weight;
	int direction;
	int type;
public:
	int getType();
	int getDirection();
	void evolve();
	bool conditionalMovement(WorldObject* const worldObject, const int direction,
		const int MaxPower,	int& usedPower);
	Furniture(Position* p, int weight);
	virtual ~Furniture();

};

#endif // !defined(AFX_FURNITURE_H__BE50ACE6_571B_11D6_A89A_0010A484ECEC__INCLUDED_)
