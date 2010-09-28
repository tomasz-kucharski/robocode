// FurnitureGL.h: interface for the FurnitureGL class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_FURNITUREGL_H__BE50ACE8_571B_11D6_A89A_0010A484ECEC__INCLUDED_)
#define AFX_FURNITUREGL_H__BE50ACE8_571B_11D6_A89A_0010A484ECEC__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include "ObjectGL.h"

class Furniture;
class WorldObject;

class FurnitureGL : public ObjectGL  
{
private:
	Furniture* furniture;
	GLuint list2,list1,list3;
public:
	void init();
	void draw(WorldObject* object);
	FurnitureGL();
	virtual ~FurnitureGL();

};

#endif // !defined(AFX_FURNITUREGL_H__BE50ACE8_571B_11D6_A89A_0010A484ECEC__INCLUDED_)
