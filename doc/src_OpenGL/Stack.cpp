// Stack.cpp: implementation of the Stack class.
//
//////////////////////////////////////////////////////////////////////

#include "stdafx.h"
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

Stack::Stack()
{

}

Stack::~Stack()
{
	StackObject* obj;
	setToFirst();
	while( obj = (StackObject*)getNext() )
		delete obj;
}

void Stack::push(int value)
{
	StackObject* obj = new StackObject(value);
	this->add(obj);
	TRACE("PUSH : %d\n",value);
}

int Stack::pop()
{
	int value;
	StackObject* obj;
	if(!setToFirst()) 
		return -1;
	obj = (StackObject*)getObject();
	value = obj->value;
	remove(obj);
	delete obj;
	TRACE("POP : %d\n",value);
	return value;
}
