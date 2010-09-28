// DepotGL.h: interface for the DepotGL class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_DEPOTGL_H__BE50ACE9_571B_11D6_A89A_0010A484ECEC__INCLUDED_)
#define AFX_DEPOTGL_H__BE50ACE9_571B_11D6_A89A_0010A484ECEC__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include "ObjectGL.h"

class Depot;

class DepotGL : public ObjectGL  
{
private:
	Depot* depot;
public:
	void init();
	void draw(WorldObject* object);
	DepotGL();
	virtual ~DepotGL();

};

#endif // !defined(AFX_DEPOTGL_H__BE50ACE9_571B_11D6_A89A_0010A484ECEC__INCLUDED_)
