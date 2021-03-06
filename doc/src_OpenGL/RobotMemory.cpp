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
: pathstop(0,0)
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
			map2[i][j] = -1;
		}
	}
	
	pathstart = NULL;
	path = new RobotPath();
	this->direction = direction;

	endofmap = false;
	xmin = 0;
	ymin=0;
	minvalue=0;
	olddir=0;
	newdir=0;
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
			TRACE("%d\t",map2[i][j]);
		TRACE("\n");
	}
	TRACE("\n");
}

int RobotMemory::findPath(int x, int y)
{
	int value = 0;

	pathstart = owner->getPosition();
	pathstop.x = x;
	pathstop.y = y;

	for (int i=0; i<columns; i++)
		for(int j=0; j<rows; j++)
			map2[i][j] = -1;

	setMem(pathstart->x,pathstart->y,value);
	writeMap();
	while(!endofmap)
	{
		endofmap = true;
		value++;
		for(int i=0; i<columns; i++)
		{
			for(int j=0; j<rows; j++)
			{
				if(map2[i][j] == value - 1)
				{
					endofmap = false;
					if((i == pathstop.x) && (j == pathstop.y))
					{	
						//writeMap();
						setPath(i,j);
						writeMap();
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
	TRACE("Sciezka nie odnaleziona\n");
	return 1;
}

bool RobotMemory::setMem(int x, int y, int value)
{
	if ((x < 0) || (x >= columns) || (y < 0) || (y >= rows))
		return false;
	else 
	{
		if (map[x][y] != RobotProcessor::UNMOVABLE)
			if(map2[x][y] == -1 )
				map2[x][y] = value;
		return true;
	}
}

int RobotMemory::getMem(int x, int y)
{
	if ((x < 0) || (x >= columns) || (y < 0) || (y >= rows))
		return -1;
	else 
		return map2[x][y];
}

void RobotMemory::setPath(int x, int y)
{
	Position p(x,y);
	Position p2(x,y);
	minvalue = map2[x][y];

//	path->push(RobotPath::STOP);
	path->push(RobotPath::MOVE);

	p2 = minCell(p);

	newdir = Direction::computeDirection(p2,p);
		
	p.x = p2.x;
	p.y = p2.y;
	do
	{
		p2 = minCell(p);

		olddir = newdir;
		newdir = Direction::computeDirection(p2,p);
		olddir = computeRotation(olddir,newdir);

		if(olddir != -1 )
			path->push(olddir);
		path->push(RobotPath::MOVE);
		p.x = p2.x;
		p.y = p2.y;
	}
	while(map2[p.x][p.y] != 0);
//	path->push(RobotPath::MOVE);
	TRACE("DIRECTION %d, NEWDIR %d\n",direction,newdir);
	olddir = computeRotation(direction,newdir);
	TRACE("DIRECTION %d\n",olddir);
	if (olddir == -2)
	{
		path->push(RobotPath::LEFT);
		path->push(RobotPath::LEFT);
	}
	else 
		if(olddir != -1)
			path->push(olddir);
//	path->push(RobotPath::START);
}

int RobotMemory::computeRotation(int dirfrom, int dirto)
{
	if (dirfrom == dirto )
		return -2;
	if (((dirfrom == Direction::NORTH) && (dirto == Direction::SOUTH)) || 
		((dirfrom == Direction::SOUTH) && (dirto == Direction::NORTH)) ||
		((dirfrom == Direction::WEST) && (dirto == Direction::EAST)) || 
		((dirfrom == Direction::EAST) && (dirto == Direction::WEST)))
		return -1;
	if (dirfrom == Direction::NORTH)
	{
		if(dirto == Direction::EAST)
			return RobotPath::LEFT;
		else
			return RobotPath::RIGHT;
	}
	if (dirfrom == Direction::SOUTH)
	{
		if(dirto == Direction::WEST)
			return RobotPath::LEFT;
		else
			return RobotPath::RIGHT;

	}
	if (dirfrom == Direction::WEST)
	{
		if(dirto == Direction::NORTH)
			return RobotPath::LEFT;
		else
			return RobotPath::RIGHT;

	}
	if (dirfrom == Direction::EAST)
	{
		if(dirto == Direction::SOUTH)
			return RobotPath::LEFT;
		else
			return RobotPath::RIGHT;

	}
	else return -1;
}

Position RobotMemory::minCell(Position p)
{
	Position temp(0,0);
	Position pmin(p.x,p.y);

	temp.x = p.x + 1;
	temp.y = p.y;
	if(checkPosition(temp))
		if ((map2[temp.x][temp.y] < map2[pmin.x][pmin.y]) && (map2[temp.x][temp.y] != -1))
		{
			pmin.x = temp.x;
			pmin.y = temp.y;
		}
	temp.x = p.x - 1;
	temp.y = p.y;
	if(checkPosition(temp))
		if ((map2[temp.x][temp.y] < map2[pmin.x][pmin.y]) && (map2[temp.x][temp.y] != -1))
		{
			pmin.x = temp.x;
			pmin.y = temp.y;
		}
	temp.x = p.x;
	temp.y = p.y + 1;
	if(checkPosition(temp))
		if ((map2[temp.x][temp.y] < map2[pmin.x][pmin.y]) && (map2[temp.x][temp.y] != -1))
		{
			pmin.x = temp.x;
			pmin.y = temp.y;
		}
	temp.x = p.x;
	temp.y = p.y - 1;
	if(checkPosition(temp))
		if ((map2[temp.x][temp.y] < map2[pmin.x][pmin.y]) && (map2[temp.x][temp.y] != -1))
		{
			pmin.x = temp.x;
			pmin.y = temp.y;
		}
	return pmin;
}

int RobotMemory::followpath()
{
	return path->pop();
}

Position* RobotMemory::find(int type)
{
	Position pmin(0,0);
	Position pmax(0,0);
	pmin.x = owner->getPosition()->x;
	pmin.y = owner->getPosition()->y;
	pmax.x = pmin.x;
	pmax.y = pmin.y;

	while((pmin.x > 0) || (pmin.y > 0) || (pmax.x < columns) || (pmax.y < rows))
	{
		pmin.x --;
		pmin.y --;
		pmax.x ++;
		pmax.y ++;
		for(int i=pmin.x; i<=pmax.x; i++)
			for(int j=pmin.y; j<=pmax.y; j++)
				if (!((i < 0) || (i >= columns) || (j < 0) || (j >= rows)))
					if(map[i][j] == type)
					{
						TRACE("ZNALEZIONO OBIEKT. XY = %d %d \n",i,j);
						return new Position(i,j);
					}
	}
	return new Position(-1,-1);
}	
