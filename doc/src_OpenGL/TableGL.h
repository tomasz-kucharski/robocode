// TableGL.h: interface for the TableGL class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_TABLEGL_H__E8B47781_3ECC_11D6_A899_0010A484ECEC__INCLUDED_)
#define AFX_TABLEGL_H__E8B47781_3ECC_11D6_A899_0010A484ECEC__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include "ObjectGL.h"

class WorldObject;

class TableGL : public ObjectGL
{
public:
	void init();
	void draw(WorldObject* object);
	TableGL(int columns, int rows);
	virtual ~TableGL();
private:
	float x;
	float y;
};

#endif // !defined(AFX_TABLEGL_H__E8B47781_3ECC_11D6_A899_0010A484ECEC__INCLUDED_)
