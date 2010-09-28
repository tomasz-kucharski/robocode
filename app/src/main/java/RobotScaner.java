// RobotScaner.cpp: implementation of the RobotScaner class.
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

RobotScaner::RobotScaner(Robot* owner, RobotMemory* memory, int zakres)
: p(0,0)
{
	if( (zakres <= 0)  )
		this->zakres  = 1;
	else 
		this->zakres = zakres;

	this->owner = owner;
	this->memory = memory;

	p = new Position(0,0);
}

RobotScaner::~RobotScaner()
{
	delete p;
}

WorldObject* RobotScaner::scanMyCell(int className)
{
	return owner->getWorld()->getObject(owner->getPosition(),className);
}

void RobotScaner::scan()
{
	int direction = memory->getDirection();
	int i = 0;
	p->x = owner->getPosition()->x;
	p->y = owner->getPosition()->y;

	Direction::computePosition(p,Direction::getLeft(direction));
	if (memory->checkPosition(*p))
	{
		for(i=0; i<zakres; i++)
		{
			Direction::computePosition(p,direction);
			if(memory->checkPosition(*p))
				memory->setMemoryCell(p,verify(p));
		}
	}

	p->x = owner->getPosition()->x;
	p->y = owner->getPosition()->y;

	if (memory->checkPosition(*p))
	{
		for(i=0; i<zakres; i++)
		{
			Direction::computePosition(p,direction);
			if(memory->checkPosition(*p))
				memory->setMemoryCell(p,verify(p));
		}
	}
	
	p->x = owner->getPosition()->x;
	p->y = owner->getPosition()->y;

	Direction::computePosition(p,Direction::getRight(direction));
	if (memory->checkPosition(*p))
	{
		for(i=0; i<zakres; i++)
		{
			Direction::computePosition(p,direction);
			if(memory->checkPosition(*p))
				memory->setMemoryCell(p,verify(p));
		}
	}
}

int RobotScaner::verify(Position *p)
{
	if(owner->getWorld()->getObject(p,WorldObjectVerifier::DEPOT) != NULL)
		return RobotProcessor::DEPOT;
	if(owner->getWorld()->getObject(p,WorldObjectVerifier::FURNITURE) != NULL)
		return RobotProcessor::MOVABLE;
	if(owner->getWorld()->getObject(p,WorldObjectVerifier::RUBBISH) != NULL)
		return RobotProcessor::RUBBISH;
	if(owner->getWorld()->getObject(p,WorldObjectVerifier::WALL) != NULL)
		return RobotProcessor::UNMOVABLE;
	if(owner->getWorld()->getObject(p,WorldObjectVerifier::ROBOT) != NULL)
		return RobotProcessor::ROBOT;
	return RobotProcessor::EMPTY;
}


int RobotScaner::getScanerZakres()
{	
	return zakres;
}
