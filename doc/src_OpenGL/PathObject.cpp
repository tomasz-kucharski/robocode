// PathObject.cpp: implementation of the PathObject class.
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

PathObject::PathObject(int value)
{
	this->value = value;
}

PathObject::~PathObject()
{

}
