// RobotBattery.cpp: implementation of the RobotBattery class.
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

//////////////////////////////////////////////////////////////////////
// Construction/Destruction
//////////////////////////////////////////////////////////////////////

RobotBattery::RobotBattery(int capacity)
: maxCapacity(capacity)
{
	if(capacity < 100 )
		this->capacity = 100;
	else 
		this->capacity = capacity;
	actualDischarge = 0;
}

RobotBattery::~RobotBattery()
{

}

int RobotBattery::getCapacity()
{
	return capacity;
}


void RobotBattery::discharge(int amount)
{
	capacity -= amount;
	if (capacity < 0 ) 
		capacity = 0;
}

void RobotBattery::charge(Depot *depot)
{

}

const int RobotBattery::getMaxCapacity()
{
	return maxCapacity;
}

int RobotBattery::getDischargeLevel()
{
	return (maxCapacity*100)/capacity;
}


bool RobotBattery::isEmpty()
{
	return (capacity == 0) ? true : false;
}

int& RobotBattery::plug()
{
	return  actualDischarge;
}

void RobotBattery::unplug()
{
	//discharge(actualDischarge);
	actualDischarge = 0;
}


