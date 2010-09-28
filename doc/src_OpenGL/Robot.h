// Robot.h: interface for the Robot class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_ROBOT_H__976CF245_52EB_11D6_A89A_0010A484ECEC__INCLUDED_)
#define AFX_ROBOT_H__976CF245_52EB_11D6_A89A_0010A484ECEC__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include "IntelligentObject.h"

class RobotMemory;
class RobotScaner;
class RobotBattery;
class Position;
class RobotProcessor;

class Robot : public IntelligentObject  
{
public:
	int getStateProgram();
	const static int MOVESTATE;
	const static int CLEANSTATE;
	const static int SCANERSTATE;
	const static int TURNSTATE;

	int getTurnState();
	int getCleanState();
	int getMoveState();
	int getScanerState();

	virtual bool conditionalMovement(WorldObject* const worldObject, 
			const int direction, const int maxPower, int& usedPower);
	int getDirection();
	void think();
	Robot(Position* p,int columns, int rows, char* name, int direction, 
			int capacity, int zakres, char* fileName);
	virtual ~Robot();

private:
	RobotMemory*  memory;
	RobotScaner*  scaner;
	RobotBattery* battery;
	RobotProcessor* processor;

	int stateMove;
	int stateClean;
	int stateScaner;
	int stateTurn;
	int stateProgram;

	friend class RobotProcessor;
	

};

#endif // !defined(AFX_ROBOT_H__976CF245_52EB_11D6_A89A_0010A484ECEC__INCLUDED_)
