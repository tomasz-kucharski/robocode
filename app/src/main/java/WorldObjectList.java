// WorldObjectList.cpp: implementation of the WorldObjectList class.
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

WorldObjectList::WorldObjectList()
{

}

WorldObjectList::~WorldObjectList()
{
	
}

WorldObject* WorldObjectList::getNext()
{
	return (WorldObject *)( List::getNext());
}


bool WorldObjectList::isObjectByName(int className)
{
	bool check = false;
	WorldObject* temp;
	if (!setToFirst()) return false;
	while  ( (temp = getNext() ) != NULL )
		if (temp->getClassName() == className)
			check = true;
	return check;
}

WorldObject* WorldObjectList::getObject()
{
	return (WorldObject*) List::getObject();
}

WorldObject* WorldObjectList::getObjectByName(int className)
{
	WorldObject* temp;
	if (!setToFirst()) return NULL;
	while  ( (temp = getNext() ) != NULL )
		if (temp->getClassName() == className) 
			return temp;
	return NULL;
}
