// Message.cpp: implementation of the Message class.
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
const int Message::CHECKED = 1;
const int Message::CLEANED = 2;
const int Message::HELP = 3;

Message::Message(WorldObject* worldObject, int information)
{
	this->worldObject = worldObject;
	this->information = information;
}

Message::~Message()
{

}
