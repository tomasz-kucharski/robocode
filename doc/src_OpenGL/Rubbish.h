// Rubbish.h: interface for the Rubbish class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_RUBBISH_H__BE50ACE2_571B_11D6_A89A_0010A484ECEC__INCLUDED_)
#define AFX_RUBBISH_H__BE50ACE2_571B_11D6_A89A_0010A484ECEC__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include "WorldObject.h"

class Position;

class Rubbish : public WorldObject  
{
private:
	int type;
	int quantity;
	int direction;
public:
	int getDirection();
	void cleaning(const int maxPower, int& usedPower);
	void evolve();
	bool conditionalMovement(WorldObject* const worldObject, const int direction,
		const int MaxPower,	int& usedPower);
	Rubbish(Position* p, int quantity);
	virtual ~Rubbish();

};

#endif // !defined(AFX_RUBBISH_H__BE50ACE2_571B_11D6_A89A_0010A484ECEC__INCLUDED_)
