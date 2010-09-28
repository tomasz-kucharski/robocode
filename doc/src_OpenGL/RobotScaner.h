// RobotScaner.h: interface for the RobotScaner class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_ROBOTSCANER_H__976CF247_52EB_11D6_A89A_0010A484ECEC__INCLUDED_)
#define AFX_ROBOTSCANER_H__976CF247_52EB_11D6_A89A_0010A484ECEC__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

class Position;
class Robot;
class RobotMemory;
class WorldObject;

class RobotScaner  
{
private:
	int verify(Position *p);
	Robot* owner;
	RobotMemory *memory;
	Position* p;
	int zakres;
public:
	int getScanerZakres();
	void scan();
	WorldObject* scanMyCell(int className);
	RobotScaner(Robot* owner, RobotMemory* memory, int zakres);
	virtual ~RobotScaner();

};

#endif // !defined(AFX_ROBOTSCANER_H__976CF247_52EB_11D6_A89A_0010A484ECEC__INCLUDED_)
