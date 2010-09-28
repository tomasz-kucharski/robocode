// Direction.cpp: implementation of the Direction class.
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



int Direction::NORTH = 2;
int Direction::EAST = 3;
int Direction::SOUTH = 0;
int Direction::WEST = 1;
//////////////////////////////////////////////////////////////////////
// Construction/Destruction
//////////////////////////////////////////////////////////////////////

//DEL int Direction::turnLeft(int direction)
//DEL {
//DEL 	return 	(direction + 1) % 4;
//DEL }

//DEL int Direction::turnRight(int direction)
//DEL {
//DEL 	return (direction + 3) % 4;
//DEL }

int Direction::getDirectionByName(char *direction)
{
 	if (direction != NULL) {
 		if(!strcmp(direction,"NORTH"))
 			return NORTH;
 		if(!strcmp(direction,"EAST"))
 			return EAST;
		if(!strcmp(direction,"SOUTH"))
 			return SOUTH;
 		if(!strcmp(direction,"WEST"))
 			return WEST;
	}
 	return -1;
}

void Direction::computePosition(Position* p, int direction)
{
	if (direction == NORTH)
		p->y--;
	else if (direction == EAST)
		p->x++;
	else if (direction == SOUTH)
		p->y++;
	else if (direction == WEST)
		p->x--;
}

Position Direction::computePosition(Position p, int direction)
{
	if (direction == NORTH)
		p.y--;
	else if (direction == EAST)
		p.x++;
	else if (direction == SOUTH)
		p.y++;
	else if (direction == WEST)
		p.x--;
	return p;
}

float Direction::computeRotation(int direction)
{
	if (direction == NORTH)
		return 0.0f;
	else if (direction == EAST)
		return 90.0f;
	else if (direction == SOUTH)
		return 180.0f;
	else if (direction == WEST)
		return 270.f;
	else 
		exit(997);
}

int Direction::randDirection()
{
	int	choice = rand() % 4;
	return choice;
}

int Direction::getLeft(int direction)
{
	return 	(direction + 1) % 4;
}

int Direction::getRight(int direction)
{
	return (direction + 3) % 4;
}

int Direction::getBackward(int direction)
{
	return direction % 4;
}

int Direction::getForward(int direction)
{
	return (direction + 2) % 4;
}

int Direction::computeDirection(Position from, Position to)
{
	if(from.y > to.y )
		return NORTH;
	if(from.y < to.y )
		return SOUTH;
	if(from.x > to.x )
		return EAST;
	if(from.x < to.x )
		return WEST;
	return -1;
}
