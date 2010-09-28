// RobotProcessor.h: interface for the RobotProcessor class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_ROBOTPROCESSOR_H__CB2436FD_AF9D_4A77_B969_05A96DF84568__INCLUDED_)
#define AFX_ROBOTPROCESSOR_H__CB2436FD_AF9D_4A77_B969_05A96DF84568__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

class ProgramList;
class Instruction;
class Robot;
class Stack;


class RobotProcessor  
{
public:
	static int getOperationByName(char* name);
	static int getInstructionByName(char* name);
	static int getMemoryObjectByName(char* name);
	static int getTypeOfInstruction(int rozkaz);

	static const int REGISTRYSIZE;
	static const int CYCLE;
//ROZKAZY
	static const int SCAN;
	static const int CLEAN;
	static const int MOVE;
	static const int MEMLEFT;
	static const int MEMRIGHT;
	static const int MEMFRONT;
	static const int MEMBACK;
	static const int TURNLEFT;
	static const int TURNRIGHT;
	static const int RECEIVE;
	static const int FINDPATH;
	static const int FIND;
	static const int FOLLOW;
	static const int WORLD;
	static const int POS;
	static const int RAND;
	static const int STORE;
	static const int LOAD;
	static const int READ;
	static const int INC;
	static const int DEC;
	static const int JUMP;
	static const int JEQUAL;
	static const int JNEQUAL;
	static const int JGT;
	static const int JLT;
	static const int JUMPF;
	static const int JEQUALF;
	static const int JNEQUALF;
	static const int JGTF;
	static const int JLTF;
	static const int RETURN;

//OPERATORY
	static const int OPEQUAL;
	static const int OPADR;
//STANY OBIEKTOW
	static const int UNKNOWN;  //nieznany
	static const int EMPTY;		// pusty, czyli sama podloga
	static const int MOVABLE;   // czyli np. meble
	static const int UNMOVABLE; // np. sciana
	static const int RUBBISH; // smiecie
	static const int VISITED;  // puste i sprzatniete;
	static const int DEPOT; // miejsce parkingowe
	static const int ROBOT; // roboty
	static const int END;


	bool go();

	RobotProcessor(Robot* r, char* fileName);
	virtual ~RobotProcessor();
private:
	void performExternal();
	void perform();
	void exception(int line, char* description);
	ProgramList *program;
	Instruction *instruction;
	Stack *stack;
	Robot* r;

	int PC;
	int* registry;
	bool done;
	bool external;
	bool error;
	int rozkaz;
	int cycle;

};

#endif // !defined(AFX_ROBOTPROCESSOR_H__CB2436FD_AF9D_4A77_B969_05A96DF84568__INCLUDED_)
