// Floor.h: interface for the Floor class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_FLOOR_H__976CF243_52EB_11D6_A89A_0010A484ECEC__INCLUDED_)
#define AFX_FLOOR_H__976CF243_52EB_11D6_A89A_0010A484ECEC__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include "WorldObject.h"

class Position;

class Floor : public WorldObject  
{
public:
	void evolve();
	bool conditionalMovement(WorldObject* const worldObject, const int direction,
		const int MaxPower,	int& usedPower);
	Floor(Position* p,int slippery);
	virtual ~Floor();

private:
	int slippery; //od 1 do 10
};

#endif // !defined(AFX_FLOOR_H__976CF243_52EB_11D6_A89A_0010A484ECEC__INCLUDED_)
