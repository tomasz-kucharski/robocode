// ProgramList.cpp: implementation of the ProgramList class.
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

ProgramList::ProgramList()
{
	jump = true;
}

ProgramList::~ProgramList()
{	
	Instruction* instruction;
	setToFirst();
	while( instruction = (Instruction*)getNext() )
		delete instruction;
}

Instruction* ProgramList::getInstruction()
{
	if (jump)
	{
		return (Instruction *)getNext();
		jump = false;
	}
	else
		return (Instruction *)next();
}

bool ProgramList::gotoInstruction(int label)
{
	jump = true;
	Instruction* temp;
	if (!setToFirst()) 
		return false;
	temp = (Instruction*)getObject();
	if( temp->getLabel() == label)
		return true;
	while  ( (temp = (Instruction*)next()) != NULL )
	{
		if ( temp->getLabel() == label) 
			return true;
	}
	return false;
}


bool ProgramList::returnInstruction(int line)
{
	Instruction* temp;
	if (!setToFirst()) 
		return false;
	temp = (Instruction*)getObject();
	if( temp->getLine() == line)
		return true;
	while  ( (temp = (Instruction*)next()) != NULL )
	{
		if ( temp->getLine() == line) 
			return true;
	}
	return false;
}
