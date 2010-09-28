// RubbishGL.h: interface for the RubbishGL class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_RUBBISHGL_H__BE50ACE1_571B_11D6_A89A_0010A484ECEC__INCLUDED_)
#define AFX_RUBBISHGL_H__BE50ACE1_571B_11D6_A89A_0010A484ECEC__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include "ObjectGL.h"

class Rubbish;
class WorldObject;

class RubbishGL  :public ObjectGL
{
private:
	Rubbish* rubbish;
public:
	void init();
	void draw(WorldObject* object);
	RubbishGL();
	virtual ~RubbishGL();

};

#endif // !defined(AFX_RUBBISHGL_H__BE50ACE1_571B_11D6_A89A_0010A484ECEC__INCLUDED_)
