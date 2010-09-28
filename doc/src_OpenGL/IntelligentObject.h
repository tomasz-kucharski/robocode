// IntelligentObject.h: interface for the IntelligentObject class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_INTELLIGENTOBJECT_H__976CF241_52EB_11D6_A89A_0010A484ECEC__INCLUDED_)
#define AFX_INTELLIGENTOBJECT_H__976CF241_52EB_11D6_A89A_0010A484ECEC__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include "WorldObject.h"

class Position;

class IntelligentObject : public WorldObject  
{
public:
	void evolve();
	virtual void think() = 0;
	virtual bool conditionalMovement(WorldObject* const worldObject, 
		const int direction, const int maxPower, int& usedPower) = 0; 
	IntelligentObject(int className, Position* p, bool flat, bool movable, 
						bool slide, char* name);
	virtual ~IntelligentObject();

protected:
	char* name;
};

#endif // !defined(AFX_INTELLIGENTOBJECT_H__976CF241_52EB_11D6_A89A_0010A484ECEC__INCLUDED_)
