// Eter.cpp: implementation of the Eter class.
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

Eter::Eter() : List()
{

}

Eter::~Eter()
{
	
}

bool Eter::sendMessage(Message *message)
{
	List::add(message);
	return true;
}

Message* Eter::retrieveMessage()
{
	return (Message*) List::getNext();
}

void Eter::clearMessages()
{
	Message* message;
	this->setToFirst();
	while(message = retrieveMessage() )
		delete (Message*) this->remove(message); 
}
