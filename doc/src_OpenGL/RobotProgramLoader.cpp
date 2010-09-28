// RobotProgramLoader.cpp: implementation of the RobotProgramLoader class.
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

RobotProgramLoader::RobotProgramLoader(char* fileName, ProgramList* program)
{
	size = 10;
	in.open(fileName);
	token = new char[size];
	this->program = program;
	instruction = NULL;
	if(!loadProgram())
		exit(25);
//	program->setToFirst();
//	while (instruction = program->getInstruction())
//		TRACE("Instrukcja : %d %d %d %d %d\n",instruction->getLine(),instruction->getRozkaz(),instruction->getOperation(),
//			instruction->getValue1(),instruction->getValue2());
	program->setToFirst();
}

RobotProgramLoader::~RobotProgramLoader()
{
	in.close();
	delete[] token;
}

void RobotProgramLoader::getToken()
{
	char c;
	while(true) 
	{
		if(in.get(c)) 
		{	
			if(!isspace(c)) //BIALY ZNAK
				break;
		}
		else 
		{ //KONIEC PLIKU
			token[0] = 0;
			return;
		}
	}

	if( c == '#' ) 
	{
		eatAll();
		token[0] = '#';
		return;
	}
	in.putback(c); // ZWROC CO POBRALES

	for(int i=0; i<size; i++) 
	{
		if((in.get(c) == false) || isspace(c)) 
		{
			in.putback(c);
			token[i] = 0;					// KONIEC STRING-a
			break;
		}
		token[i] = c;
	}
}

void RobotProgramLoader::eatAll()
{
	char c;
	while(in.get(c))
		if(c=='\n')
			break;
}

bool RobotProgramLoader::loadProgram()
{
	int type;

	char c;

	int line = 0;
	int label = 0;
	int rozkaz = 0;
	int operation = 0;
	int value1 = 0;
	int value2 = 0;

	while(true)
	{	
		line++;
		label = 0;
		rozkaz = 0;
		operation = 0;
		value1 = 0;
		value2 = 0;
		if(in.get(c) == NULL)
			break;
		if ( c == '#' )
		{
			eatAll();
			continue;
		}
		else
			in.putback(c);

		if(!checkChar())
			in >> label;
		else
			label = 0;

		getToken();
		rozkaz = RobotProcessor::getInstructionByName(token);
		if (rozkaz == -1)
		{
			TRACE("LINE %d : ZLY ROZKAZ\n",line);
			return false;
		}
		
		type = RobotProcessor::getTypeOfInstruction(rozkaz);
		if ( type == -1)
		{
			TRACE("LINE %d : ZLY TYP ROZKAZU\n",line);
			return false;
		}

		switch(type) 
		{
		case 0:
			instruction = new Instruction(line,label,rozkaz);
		break;

		case 1:
			if (!checkChar())
			{
				in >> value1;
				instruction = new Instruction(line,label,rozkaz,value1);
			}
			else
			{
				TRACE("LINE %d : OPEARACJE NIEDOSTEPNE DLA ROZKAZU\n",line);
				return false;
			}
		break;
		case 2:
			getToken();
			operation = RobotProcessor::getOperationByName(token);
			if(operation == -1)
			{
				TRACE("LINE %d : ZLA OPERACJA\n",line);
				return false;
			}
			if(checkChar())
			{
				if (operation == RobotProcessor::OPADR)
				{
					TRACE("LINE %d : OPERACJA * NIEDOSTEPNA DLA OBIEKTOW\n",line);
					return false;
				}
				getToken();
				value1 = RobotProcessor::getMemoryObjectByName(token);
				if(value1 == -1)
				{
					TRACE("LINE %d : ZLY OBIEKT\n",line);
					return false;
				}
				
			}
			else
			{
				in >> value1;
			}
			instruction = new Instruction(line,label,rozkaz,operation,value1);
		break;
		case 3:
			getToken();
			operation = RobotProcessor::getOperationByName(token);
			if(operation == -1)
			{
				TRACE("LINE %d : ZLA OPERACJA\n",line);
				return false;
			}
			if(checkChar())
			{
				if (operation == RobotProcessor::OPADR)
				{
					TRACE("LINE %d : OPERACJA * NIEDOSTEPNA DLA OBIEKTOW\n",line);
					return false;
				}
				getToken();
				value1 = RobotProcessor::getMemoryObjectByName(token);
				if(value1 == -1)
				{
					TRACE("LINE %d : ZLY OBIEKT\n",line);
					return false;
				}
				
			}
			else
			{
				in >> value1;
			}
			in >> value2;
			instruction = new Instruction(line,label,rozkaz,operation,value1,value2);
		break;
		}
		eatAll();
		program->add(instruction,true);
//		TRACE("LINE:%d, LABEL:%d, ROZKAZ:%d, VALUE1:%d, VALUE2:%d\n",line,label,rozkaz,value1,value2);
	}
	return true;
}

bool RobotProgramLoader::checkChar()
{
	bool string = true;
	char c;
	while(in.get(c))
		if(!isspace(c))
			break;
	if (c == '0') string = false;
	if (c == '1') string = false;
	if (c == '2') string = false;
	if (c == '3') string = false;
	if (c == '4') string = false;
	if (c == '5') string = false;
	if (c == '6') string = false;
	if (c == '7') string = false;
	if (c == '8') string = false;
	if (c == '9') string = false;
	in.putback(c);
	return string;
}

