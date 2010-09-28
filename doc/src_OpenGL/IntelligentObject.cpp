// IntelligentObject.cpp: implementation of the IntelligentObject class.
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

IntelligentObject::IntelligentObject(int className, Position* p, bool flat, 
									 bool movable,bool slide, char* name)
:WorldObject(className,p,flat,true,movable,slide)
{
	this->name = new char [ strlen(name) + 1 ];
	strcpy(this->name, name);
}

IntelligentObject::~IntelligentObject()
{
	delete name;
}

void IntelligentObject::evolve()
{
	think();
}
