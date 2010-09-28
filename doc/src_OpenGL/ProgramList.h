// ProgramList.h: interface for the ProgramList class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_PROGRAMLIST_H__16918733_BED7_4DC2_AEA6_17AAB0965DDE__INCLUDED_)
#define AFX_PROGRAMLIST_H__16918733_BED7_4DC2_AEA6_17AAB0965DDE__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include "List.h"

class Instruction;

class ProgramList : public List  
{
public:
	bool returnInstruction(int line);
	bool jump;
	ProgramList();
	virtual ~ProgramList();
	Instruction* getInstruction();
	bool gotoInstruction(int label);

};

#endif // !defined(AFX_PROGRAMLIST_H__16918733_BED7_4DC2_AEA6_17AAB0965DDE__INCLUDED_)
