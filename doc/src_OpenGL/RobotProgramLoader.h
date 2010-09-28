// RobotProgramLoader.h: interface for the RobotProgramLoader class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_ROBOTPROGRAMLOADER_H__DC329956_B0AE_4130_AF65_F1427AE80A03__INCLUDED_)
#define AFX_ROBOTPROGRAMLOADER_H__DC329956_B0AE_4130_AF65_F1427AE80A03__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

class ProgramList;
class Instruction;

class RobotProgramLoader  
{
public:
	bool checkChar();
	RobotProgramLoader(char* fileName,ProgramList* program);
	virtual ~RobotProgramLoader();
	bool loadProgram();
	void eatAll();
	void getToken();
private:
	int size;
	ifstream in;
	char* token;
	ProgramList* program;
	Instruction* instruction;
};

#endif // !defined(AFX_ROBOTPROGRAMLOADER_H__DC329956_B0AE_4130_AF65_F1427AE80A03__INCLUDED_)
