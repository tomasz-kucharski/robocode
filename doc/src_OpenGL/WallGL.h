// WallGL.h: interface for the WallGL class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_WALLGL_H__74D912E2_3FFC_11D6_A899_0010A484ECEC__INCLUDED_)
#define AFX_WALLGL_H__74D912E2_3FFC_11D6_A899_0010A484ECEC__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include "ObjectGL.h"

class Wall;
class WorldObject;

class WallGL : public ObjectGL
{
private:
	Wall* wall;
public:
	void init();
	void draw(WorldObject* object);
	WallGL();
	virtual ~WallGL();

};

#endif // !defined(AFX_WALLGL_H__74D912E2_3FFC_11D6_A899_0010A484ECEC__INCLUDED_)
