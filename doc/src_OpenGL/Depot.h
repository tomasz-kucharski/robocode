// Depot.h: interface for the Depot class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_DEPOT_H__BE50ACE7_571B_11D6_A89A_0010A484ECEC__INCLUDED_)
#define AFX_DEPOT_H__BE50ACE7_571B_11D6_A89A_0010A484ECEC__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include "WorldObject.h"

class Position;

class Depot : public WorldObject  
{
private:
	int efficiency;
	bool lighted;
public:
	bool getLighted();
	void evolve();
	bool conditionalMovement(WorldObject* const worldObject, const int direction,
		const int MaxPower,	int& usedPower);
	Depot(Position* p, int efficiency);
	virtual ~Depot();

};

#endif // !defined(AFX_DEPOT_H__BE50ACE7_571B_11D6_A89A_0010A484ECEC__INCLUDED_)
