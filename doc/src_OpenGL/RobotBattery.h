// RobotBattery.h: interface for the RobotBattery class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_ROBOTBATTERY_H__976CF258_52EB_11D6_A89A_0010A484ECEC__INCLUDED_)
#define AFX_ROBOTBATTERY_H__976CF258_52EB_11D6_A89A_0010A484ECEC__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

class Depot;

class RobotBattery  
{
private:
	int capacity;
	const int maxCapacity;
	int actualDischarge;
	void discharge(int amount);
public:
	void unplug();
	int& plug();
	bool isEmpty();
	int getDischargeLevel();
	const int getMaxCapacity();
	void charge(Depot* depot);
	void setCapacity(int capacity);
	int getCapacity();
	RobotBattery(int capacity);
	virtual ~RobotBattery();

};

#endif // !defined(AFX_ROBOTBATTERY_H__976CF258_52EB_11D6_A89A_0010A484ECEC__INCLUDED_)
