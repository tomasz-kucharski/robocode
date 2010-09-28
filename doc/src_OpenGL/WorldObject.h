// WorldObject.h: interface for the WorldObject class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_WORLDOBJECT_H__976CF229_52EB_11D6_A89A_0010A484ECEC__INCLUDED_)
#define AFX_WORLDOBJECT_H__976CF229_52EB_11D6_A89A_0010A484ECEC__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include "Object.h"

class Position;
class World;

class WorldObject : public Object  
{
public:
	Position* const getPosition();
	virtual void evolve() = 0; 
	virtual bool conditionalMovement(WorldObject* const worldObject, 
		const int direction, const int maxPower, int& usedPower) = 0; 
	int getClassName();

	bool isIntelligent();
	bool isMovable();
	bool isFlat();
	bool isSlide();
	World* getWorld();
	WorldObject(int className, Position* p, bool flat, bool intelligent,
				bool movable, bool slide);
	virtual ~WorldObject();

private:
	friend class World;
	friend class Eter;
	World* world;
	int className;
	Position* p;

	bool moved;
	bool deleteMe;
	bool flat;
	bool movable;
	bool intelligent;
	bool slide;
};

#endif // !defined(AFX_WORLDOBJECT_H__976CF229_52EB_11D6_A89A_0010A484ECEC__INCLUDED_)
