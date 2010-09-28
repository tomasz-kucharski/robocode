// WorldGL.h: interface for the OpenGL class.
//
//////////////////////////////////////////////////////////////////////
#include <gl\gl.h>
#include <gl\glu.h>
#include <gl\glaux.h>

#if !defined(AFX_WORLDGL_H__8C53D2A5_3D2B_11D6_A899_0010A484ECEC__INCLUDED_)
#define AFX_WORLDGL_H__8C53D2A5_3D2B_11D6_A899_0010A484ECEC__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

class TableGL;
class RobotGL;
class FloorGL;
class RubbishGL;
class FurnitureGL;
class DepotGL;
class WallGL;
class WorldObject;
class LightGL;

class WorldGL  
{
private:
//	GLuint texture[1];
//	AUX_RGBImageRec *texture1;

//	int pixelFormat;
//	HGLRC hRC;

	TableGL* tableGL;
	RobotGL* robotGL;
 	FloorGL* floorGL;
	WallGL*  wallGL;
	RubbishGL* rubbishGL;
	FurnitureGL* furnitureGL;
	DepotGL* depotGL;
	LightGL* lightGL;

	float movex;
	float movey;
	float movez;

	float transx;
	float transy;
	float transz;
	float scale;
	
	int columns;
	int rows;

	float posX;
	float posY;

public:
	void onSetXYZMove(float x, float y, float z);
	void onSetXYZRotate(float x, float y, float z);
	void onSetScale(float ile);
	void onWireframe(BOOL isWireframe);
	void onAntialiasing(BOOL isAntialiasing);

	void beginScene();
	void beginScene(int x, int y, int direction);
	void endScene();
	bool renderObject(int x, int y, WorldObject* object);

	GLvoid InitGL();
	GLvoid ReSizeGLScene(GLsizei width, GLsizei height);
	WorldGL(int columns, int rows);
	virtual ~WorldGL();

};

#endif // !defined(AFX_WORLDGL_H__8C53D2A5_3D2B_11D6_A899_0010A484ECEC__INCLUDED_)
