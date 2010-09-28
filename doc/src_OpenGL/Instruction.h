// Instruction.h: interface for the Instruction class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_INSTRUCTION_H__DDF86BD8_5EF6_4990_AD72_C5D2684A8916__INCLUDED_)
#define AFX_INSTRUCTION_H__DDF86BD8_5EF6_4990_AD72_C5D2684A8916__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include "Object.h"

class Instruction : public Object  
{
public:
	int getLabel();
	int getValue2();
	int getValue1();
	int getRozkaz();
	int getOperation();
	int getLine();

	Instruction(int line, int label, int rozkaz);
	Instruction(int line, int label, int rozkaz, int operation, int value1, int value2);
	Instruction(int line, int label, int rozkaz, int value1);
	Instruction(int line, int label, int rozkaz, int operation,int value1);
	virtual ~Instruction();
private:
	int label;
	int line;
	int rozkaz;
	int operation;
	int value1;
	int value2;

};

#endif // !defined(AFX_INSTRUCTION_H__DDF86BD8_5EF6_4990_AD72_C5D2684A8916__INCLUDED_)
