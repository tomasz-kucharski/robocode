// Wall.h: interface for the Wall class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_WALL_H__976CF242_52EB_11D6_A89A_0010A484ECEC__INCLUDED_)
#define AFX_WALL_H__976CF242_52EB_11D6_A89A_0010A484ECEC__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include  "WorldObject.h"

class Position;

class Wall : public WorldObject  
{
public:
	void evolve();
	bool conditionalMovement(WorldObject* const worldObject, 
		const int direction, const int maxPower, int& usedPower);//-1 

	Wall(Position* p, int strength);
	virtual ~Wall();
private:
	int strength;

};

#endif // !defined(AFX_WALL_H__976CF242_52EB_11D6_A89A_0010A484ECEC__INCLUDED_)
