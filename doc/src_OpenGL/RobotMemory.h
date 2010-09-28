// RobotMemory.h: interface for the RobotMemory class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_ROBOTMEMORY_H__976CF246_52EB_11D6_A89A_0010A484ECEC__INCLUDED_)
#define AFX_ROBOTMEMORY_H__976CF246_52EB_11D6_A89A_0010A484ECEC__INCLUDED_

#include "Position.h"	// Added by ClassView
#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

class Position;
class RobotPath;
class Robot;

class RobotMemory  
{
public:
	void setMemoryCell(Position* p, int state);
	int getMemoryCell(Position* p);
	int getRows();
	int getColumns();
	int getDirection();
	void turnRight(int& usedPower);
	void turnLeft(int& usedPower);
	RobotMemory(Robot* owner, int columns, int rows, int direction);
	virtual ~RobotMemory();

private:
	void setPath(int x, int y);
	bool setMem(int x, int y, int value);
	int getMem(int x, int y);
	int columns;
	int rows;
	Position* pathstart;
	Position pathstop;
	RobotPath* path;
	Robot* owner;
	int direction;
	int** map;
	int** map2;
	bool endofmap;
	int xmin;
	int ymin;
	int minvalue;
	int olddir;
	int newdir;
public:
	Position* find(int type);
	int followpath();
	Position minCell(Position p);
	int computeRotation(int dirfrom, int dirto);
	int findPath(int x, int y);
	void writeMap();
	bool checkPosition(Position p);
	int lookAround(Position *p, int direction);

};

#endif // !defined(AFX_ROBOTMEMORY_H__976CF246_52EB_11D6_A89A_0010A484ECEC__INCLUDED_)
