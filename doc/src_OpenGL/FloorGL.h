// FloorGL.h: interface for the FloorGL class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_FLOORGL_H__74D912E1_3FFC_11D6_A899_0010A484ECEC__INCLUDED_)
#define AFX_FLOORGL_H__74D912E1_3FFC_11D6_A899_0010A484ECEC__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include "ObjectGL.h"

class Floor;
class WorldObject;

class FloorGL : public ObjectGL
{
private:
	Floor* floor;
public:
	void init();
	void draw(WorldObject* object);
	FloorGL();
	virtual ~FloorGL();

};

#endif // !defined(AFX_FLOORGL_H__74D912E1_3FFC_11D6_A899_0010A484ECEC__INCLUDED_)
