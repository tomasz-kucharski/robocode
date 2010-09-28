// Instruction.cpp: implementation of the Instruction class.
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

Instruction::Instruction(int line, int label, int rozkaz, int operation, int value1, int value2)
{
	this->line = line;
	this->label = label;
	this->rozkaz = rozkaz;
	this->operation = operation;
	this->value1 = value1;
	this->value2 = value2;
}
Instruction::Instruction(int line, int label, int rozkaz, int operation,int value1)
{
	this->line = line;
	this->label = label;
	this->rozkaz = rozkaz;
	this->operation = operation;
	this->value1 = value1;
	value2 = -1;
}

Instruction::Instruction(int line, int label, int rozkaz, int value1)
{
	this->line = line;
	this->label = label;
	this->rozkaz = rozkaz;
	operation = -1;
	this->value1 = value1;
	value2 = -1;
}

Instruction::Instruction(int line, int label, int rozkaz)
{
	this->line = line;
	this->label = label;
	this->rozkaz = rozkaz;
	operation = -1;
	value1 = -1;
	value2 = -1;
}

Instruction::~Instruction()
{

}

int Instruction::getLine()
{
	return line;
}

int Instruction::getOperation()
{
	return operation;
}

int Instruction::getRozkaz()
{
	return rozkaz;
}

int Instruction::getValue1()
{
	return value1;
}

int Instruction::getValue2()
{
	return value2;
}

int Instruction::getLabel()
{
	return label;
}
