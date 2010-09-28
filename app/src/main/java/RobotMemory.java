// RobotMemory.cpp: implementation of the RobotMemory class.
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

RobotMemory::RobotMemory(Robot* owner, int columns, int rows, int direction) 
{
	this->owner = owner;
	this->columns = columns;
	this->rows = rows;

	map = new int* [columns];
	map2 = new int* [columns];
	for(int i=0; i<columns; i++) 
	{
		map[i] = new int[rows];
		map2[i] = new int[rows];
		for (int j=0; j<rows; j++) 
		{
			map[i][j] = RobotProcessor::UNKNOWN;
			map2[i][j] = 0;
		}
	}
	
	pathstart = NULL;
	pathstop = NULL;
	path = new RobotPath();
	this->direction = direction;
}

RobotMemory::~RobotMemory()
{
	for(int i=0; i<columns; i++)
	{
		delete[] map[i];
		delete[] map2[i];
	}
	delete[] map;
	delete[] map2;
	delete path;
}

void RobotMemory::turnLeft(int& usedPower)
{
	usedPower += 5;
	direction = Direction::getLeft(direction);
}

void RobotMemory::turnRight(int& usedPower)
{
	usedPower += 5;
	direction = Direction::getRight(direction);
}

int RobotMemory::getDirection()
{
	return direction;
}

int RobotMemory::getColumns()
{
	return columns;
}

int RobotMemory::getRows()
{
	return rows;
}

int RobotMemory::getMemoryCell(Position *p)
{
	if(checkPosition(*p))
		return map[p->x][p->y];
	else 
		exit(200);
}

void RobotMemory::setMemoryCell(Position *p, int state)
{
	if(checkPosition(*p))
		if(map[p->x][p->y] != RobotProcessor::VISITED)
			map[p->x][p->y] = state;
}

int RobotMemory::lookAround(Position *p,int direction)
{
	Position p2(p->x,p->y);

	Direction::computePosition(&p2,direction);
	if (checkPosition(p2))
		return getMemoryCell(&p2);
	else
		return RobotProcessor::END;
}

bool RobotMemory::checkPosition(Position p)
{
	if ((p.x < 0) || (p.x >= columns) || (p.y < 0) || (p.y >= rows))
		return false;
	else 
		return true;
}

void RobotMemory::writeMap()
{
	TRACE("Mapa\n");
	for(int i=0; i<columns; i++)
	{
		for(int j=0; j<rows; j++)
			TRACE("%d",map[i][j]);
		TRACE("\n");
	}
	TRACE("\n");
}

int RobotMemory::findPath(Position* p)
{
	pathstart = owner->getPosition();
	pathstop = p;

	int x,y;
	x = pathstart->x;
	y = pathstart->y;
	int value = 0;

	setMem(x,y,value);
	value++;
	for(int i=0; i<columns; i++)
	{
		for(int j=0; j<rows; j++)
		{
			if(map2[i][j] == value - 1)
			{
				if((x = pathstop->x) && (y = pathstop->y))
				{
					setPath(x,y);
					return 0;
				}
				setMem(i,j+1,value);
				setMem(i,j-1,value);
				setMem(i+1,j,value);
				setMem(i-1,j,value);
			}
		}
	}
}

bool RobotMemory::setMem(int x, int y, int value)
{
	if ((x < 0) || (x >= columns) || (y < 0) || (y >= rows))
		return false;
	else 
	{
		if (map2[x][y] != RobotProcessor::UNMOVABLE)
			map2[x][y] = value;
		return true;
	}
}

void RobotMemory::setPath(int x, int y)
{
	
}
