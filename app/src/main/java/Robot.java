// Robot.cpp: implementation of the Robot class.
//
//////////////////////////////////////////////////////////////////////
#include "Stdafx.h"
#include "OpenGL.h"

#include "Inkludy.h"

#ifdef _DEBUG
#undef THIS_FILE
static char THIS_FILE[]=__FILE__;
#define new DEBUG_NEW
#endif

const int Robot::MOVESTATE = 5;
const int Robot::CLEANSTATE = 3;
const int Robot::SCANERSTATE = 4;
const int Robot::TURNSTATE = 2;

//////////////////////////////////////////////////////////////////////
// Construction/Destruction
//////////////////////////////////////////////////////////////////////

Robot::Robot(Position* p,int columns, int rows, char* name, int direction,
			 int capacity, int zakres, char* fileName)
: IntelligentObject(WorldObjectVerifier::ROBOT,p,false,true,true,name)
{
	memory = new RobotMemory(this,columns,rows,direction);
	battery = new RobotBattery(capacity);
	scaner = new RobotScaner(this,memory,zakres);
	processor = new RobotProcessor(this,fileName);

	stateMove = 0;
	stateClean = 0;
	stateScaner = 0;
	stateTurn = 0;
	stateProgram = 0;

	TRACE("%s : PositionXY:%d,%d.\n",name,getPosition()->x, getPosition()->y);
}

Robot::~Robot()
{
	delete memory;
	delete battery;
	delete scaner;
	delete processor;
}

int Robot::getDirection()
{
	return memory->getDirection();
}

int Robot::getCleanState()
{
	return stateClean;
}

int Robot::getScanerState()
{
	return stateScaner;
}

int Robot::getMoveState()
{
	return stateMove;
}

int Robot::getTurnState()
{
	return stateTurn;
}

int Robot::getStateProgram()
{
	return stateProgram;
}

bool Robot::conditionalMovement(WorldObject* const worldObject, const int direction,
								const int maxPower, int& usedPower)
{
	return false;
}

void Robot::think()
{
	if (stateProgram == 0)
		if (!processor->go())
			stateProgram = 1;
}
