// RobotGL.h: interface for the RobotGL class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_ROBOTGL_H__1DF77AA1_3FEF_11D6_A899_0010A484ECEC__INCLUDED_)
#define AFX_ROBOTGL_H__1DF77AA1_3FEF_11D6_A899_0010A484ECEC__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include "ObjectGL.h"

class Robot;
class WorldObject;

class RobotGL : public  ObjectGL
{
private:
	Robot* robot;
	GLuint nogi0;
	GLuint nogi1;
	GLuint nogi2;
	GLuint rece0;
	GLuint rece1;
	GLuint rece2;
	GLuint czulki;
	GLuint robotsprzata;
public:
	void init();
	void draw(WorldObject* object);
	RobotGL();
	virtual ~RobotGL();

};

#endif // !defined(AFX_ROBOTGL_H__1DF77AA1_3FEF_11D6_A899_0010A484ECEC__INCLUDED_)



