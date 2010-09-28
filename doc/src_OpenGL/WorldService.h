// WorldService.h: interface for the WorldService class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_WORLDSERVICE_H__976CF259_52EB_11D6_A89A_0010A484ECEC__INCLUDED_)
#define AFX_WORLDSERVICE_H__976CF259_52EB_11D6_A89A_0010A484ECEC__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

class World;
class WorldGL;
class Robot;
class Position;

class WorldService  
{
public:
	void onInit();
	void OnRobotview(bool robotView);
	void onSetXYZMove(float x, float y, float z);
	void evolve();
	void onWireframe(BOOL state);
	void onAntialiasing(BOOL state);
	void onResize(GLsizei x, GLsizei y);
	void onSetScale(float x);
	void onDraw();
	void onSetXYZRotate(float x, float y, float z);
	WorldService(const char* file);
	virtual ~WorldService();
private:
	int columns;
	int rows;
	World* modelWorld;
	WorldGL* viewWorld;
	Robot* robot;
	Position* robotPosition;
	int robotDirection;
	bool robotView;



};

#endif // !defined(AFX_WORLDSERVICE_H__976CF259_52EB_11D6_A89A_0010A484ECEC__INCLUDED_)
