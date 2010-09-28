// World.cpp: implementation of the World class.
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

World::World(int columns, int rows)
{
	this->columns = columns;
	this->rows = rows;

	world = new WorldObjectList** [columns];
	for (int i=0; i<columns; i++) {
		world[i] = new WorldObjectList*[rows];
		for (int j=0; j<rows; j++)
			world[i][j] = new WorldObjectList();
	}
	srand( (unsigned)time( NULL ) );
}

World::~World()
{
	WorldObject* object;

	for (int i=0; i<columns; i++) {
		for(int j=0; j<rows; j++)
			if (world[i][j] != NULL) {
				world[i][j]->setToFirst();
				while( object = world[i][j]->getNext() )
					delete object;
				delete world[i][j];
			}
		if(world [i] != NULL) delete[] world[i];
	}
	if(world != NULL) delete[] world;
}

int World::getColumns()
{
	return columns;
}

int World::getRows()
{
	return rows;
}

WorldObjectList* World::getCell(Position* p)
{
	if(!checkPosition(p)) return NULL;
	else 
		return world[p->x][p->y];
}

bool World::checkPosition(Position* p)
{
	if ((p->x < 0) || (p->x >= columns) || (p->y < 0) || (p->y >= rows))
		return false;
	else 
		return true;
}

bool World::setCell(Position* p, WorldObject *object)
{
	if (!checkPosition(p))
		return false;
	else {
		world[p->x][p->y]->add(object);
		object->world = this;
		return true;
	}
}

bool World::checkValidate()
{
	for(int i=0; i<columns; i++)
		for(int j=0; j<rows; j++)
			if(!world[i][j]->isObjectByName(WorldObjectVerifier::FLOOR))
				return false;
	return true;
}

bool World::moveObject(WorldObject* object, int direction)
{
	Position p(0,0);
	p.x = object->p->x;
	p.y = object->p->y;

	WorldObjectList* listFrom;
	WorldObjectList* listTo;
	listFrom = world[p.x][p.y];
	Direction::computePosition(&p,direction);
	if (!checkPosition(&p))
		return false;
	listTo = world[p.x][p.y];
	if (!listFrom->remove(object))
		exit(60);
	object->p->x = p.x;
	object->p->y = p.y;
	listTo->add(object);
	
	return true;
}

bool World::move(WorldObject* const worldObject, const int direction,
				 const int maxPower, int& usedPower)
{
	Position p(0,0);
	WorldObject *object;

	WorldObjectList* list;
	if ( maxPower < usedPower)
		return false;
	p.x = worldObject->p->x;
	p.y = worldObject->p->y;

	Direction::computePosition(&p,direction);
	if (checkPosition(&p) == false ) 
		return false;

	list = world[p.x][p.y];
	list->setToFirst();
	while(object = list->getNext())
		if(!object->conditionalMovement(worldObject,direction,maxPower,usedPower))
			return false;
		if(!moveObject(worldObject,direction))
			return false;
	return true;
}

bool World::deleteMe(WorldObject *const worldObject)
{
	worldObject->deleteMe = true;
	return true;
}

void World::clearWorld()
{
	WorldObject* object;
	for (int i=0; i<columns; i++) 
		for(int j=0; j<rows; j++) {
			world[i][j]->setToFirst();
			while( object = world[i][j]->getNext() ) {
				object->moved = false;
				if (object->deleteMe) {
					if(!world[i][j]->remove(object))
						exit(200);
					delete object;
				}
			}

		}
}

WorldObject* World::getObject(Position *p, int className)
{
	if(!checkPosition(p))
		return NULL;
	else
		return world[p->x][p->y]->getObjectByName(className);
}

void World::setMoved(WorldObject *object)
{
	object->moved = true;
}

bool World::getMoved(WorldObject *object)
{
	return object->moved;
}
