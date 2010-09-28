// RobotProcessor.cpp: implementation of the RobotProcessor class.
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
const int RobotProcessor::REGISTRYSIZE = 40;
const int RobotProcessor::CYCLE = 5;
//ROZKAZY
	  const int RobotProcessor::SCAN = 1;
	  const int RobotProcessor::CLEAN = 2;
	  const int RobotProcessor::MOVE = 3;
	  const int RobotProcessor::MEMLEFT = 4;
	  const int RobotProcessor::MEMRIGHT = 5;
	  const int RobotProcessor::MEMFRONT = 6;
	  const int RobotProcessor::MEMBACK = 7;
	  const int RobotProcessor::TURNLEFT = 8;
	  const int RobotProcessor::TURNRIGHT = 9;
	  const int RobotProcessor::RECEIVE = 10;
	  const int RobotProcessor::FINDPATH = 11;
	  const int RobotProcessor::FIND = 12;
	  const int RobotProcessor::FOLLOW = 13;
	  const int RobotProcessor::POS = 14;
	  const int RobotProcessor::WORLD = 15;
	  const int RobotProcessor::RAND = 16;
	  const int RobotProcessor::STORE = 17;
	  const int RobotProcessor::LOAD = 18;
	  const int RobotProcessor::READ = 19;
	  const int RobotProcessor::INC = 20;
	  const int RobotProcessor::DEC = 21;
	  const int RobotProcessor::JUMP = 22;
	  const int RobotProcessor::JEQUAL = 23;
	  const int RobotProcessor::JNEQUAL = 24;
	  const int RobotProcessor::JGT = 25;
	  const int RobotProcessor::JLT = 26;
	  const int RobotProcessor::JUMPF = 27;
	  const int RobotProcessor::JEQUALF = 28;
	  const int RobotProcessor::JNEQUALF = 29;
	  const int RobotProcessor::JGTF = 30;
	  const int RobotProcessor::JLTF = 31;
	  const int	RobotProcessor::RETURN = 32;

//OPERATORY
	  const int RobotProcessor::OPEQUAL = 50;
	  const int RobotProcessor::OPADR = 51;
//STANY OBIEKTOW
		const int RobotProcessor::UNKNOWN = 100;
		const int RobotProcessor::EMPTY = 101;
		const int RobotProcessor::RUBBISH = 102;
		const int RobotProcessor::MOVABLE = 103;
		const int RobotProcessor::UNMOVABLE = 104;
		const int RobotProcessor::VISITED = 105;
		const int RobotProcessor::DEPOT = 106;
		const int RobotProcessor::ROBOT = 107;
		const int RobotProcessor::END = 108;


//////////////////////////////////////////////////////////////////////
// Construction/Destruction
//////////////////////////////////////////////////////////////////////

RobotProcessor::RobotProcessor(Robot* r,char* fileName)
{
	registry = new int[REGISTRYSIZE];
	for (int i=0; i<REGISTRYSIZE; i++)
		registry[i] = 0;
	this->r = r;
	program = new ProgramList();
	RobotProgramLoader* load = new RobotProgramLoader(fileName,program);
	delete load;
	instruction = NULL;
	stack = new Stack();

	done = true;
	external = false;
	error = false;
	rozkaz = -1;
}

RobotProcessor::~RobotProcessor()
{
	delete[] registry;
	delete program;
	delete stack;
}

int RobotProcessor::getInstructionByName(char *name)
{
	 if (name != NULL) {
 		if(!strcmp(name,"SCAN"))
 			return SCAN;
 		if(!strcmp(name,"CLEAN"))
 			return CLEAN;
		if(!strcmp(name,"MOVE"))
 			return MOVE;
 		if(!strcmp(name,"MEMLEFT"))
 			return MEMLEFT;
 		if(!strcmp(name,"MEMRIGHT"))
 			return MEMRIGHT;
 		if(!strcmp(name,"MEMBACK"))
 			return MEMBACK;
 		if(!strcmp(name,"MEMFRONT"))
 			return MEMFRONT;
 		if(!strcmp(name,"TURNLEFT"))
 			return TURNLEFT;
 		if(!strcmp(name,"TURNRIGHT"))
 			return TURNRIGHT;
 		if(!strcmp(name,"RECEIVE"))
 			return RECEIVE;
 		if(!strcmp(name,"FINDPATH"))
 			return FINDPATH;
 		if(!strcmp(name,"FIND"))
 			return FIND;
 		if(!strcmp(name,"FOLLOW"))
 			return FOLLOW;
 		if(!strcmp(name,"WORLD"))
 			return WORLD;
 		if(!strcmp(name,"POS"))
 			return POS;
 		if(!strcmp(name,"RAND"))
 			return RAND;
 		if(!strcmp(name,"STORE"))
 			return STORE;
 		if(!strcmp(name,"LOAD"))
 			return LOAD;
 		if(!strcmp(name,"READ"))
 			return READ;
 		if(!strcmp(name,"INC"))
 			return INC;
 		if(!strcmp(name,"DEC"))
 			return DEC;
 		if(!strcmp(name,"JUMP"))
 			return JUMP;
 		if(!strcmp(name,"JEQUAL"))
 			return JEQUAL;
 		if(!strcmp(name,"JNEQUAL"))
 			return JNEQUAL;
 		if(!strcmp(name,"JGT"))
 			return JGT;
 		if(!strcmp(name,"JLT"))
 			return JLT;
 		if(!strcmp(name,"JUMPF"))
 			return JUMPF;
 		if(!strcmp(name,"JEQUALF"))
 			return JEQUALF;
 		if(!strcmp(name,"JNEQUALF"))
 			return JNEQUALF;
 		if(!strcmp(name,"JGTF"))
 			return JGTF;
 		if(!strcmp(name,"JLTF"))
 			return JLTF;
 		if(!strcmp(name,"RETURN"))
 			return RETURN;
	}
 	return -1;
}

int RobotProcessor::getOperationByName(char *name)
{
	if (name != NULL)
	{
 		if(!strcmp(name,"="))
 			return OPEQUAL;
 		if(!strcmp(name,"*"))
 			return OPADR;
	}
 	return -1;
}

int RobotProcessor::getMemoryObjectByName(char *name)
{
	 if (name != NULL) {
 		if(!strcmp(name,"UNKNOWN"))
 			return UNKNOWN;
 		if(!strcmp(name,"EMPTY"))
 			return EMPTY;
		if(!strcmp(name,"RUBBISH"))
 			return RUBBISH;
 		if(!strcmp(name,"UNMOVABLE"))
 			return UNMOVABLE;
 		if(!strcmp(name,"VISITED"))
 			return VISITED;
 		if(!strcmp(name,"DEPOT"))
 			return DEPOT;
 		if(!strcmp(name,"ROBOT"))
 			return ROBOT;
 		if(!strcmp(name,"END"))
 			return END;
	}
 	return -1;
}

int RobotProcessor::getTypeOfInstruction(int rozkaz)
{
	switch(rozkaz)
	{
	case SCAN:
	case CLEAN:
	case MOVE:
	case MEMFRONT:
	case MEMRIGHT:
	case MEMBACK:
	case MEMLEFT:
	case TURNLEFT:
	case TURNRIGHT:
	case RECEIVE:
	case RETURN:
	case FOLLOW:
	case WORLD:
	case POS:
		return 0;  // brak argumentow
	break;
	case INC:
	case DEC:
	case STORE:
	case JUMP:
	case JUMPF:
	case FIND:
		return 1; // 1 argument
	break;
	case RAND:
	case LOAD:
		return 2; // 1 argument + operator
	break;
	case JEQUAL:
	case JNEQUAL:
	case JLT:
	case JGT:
	case JEQUALF:
	case JNEQUALF:
	case JLTF:
	case JGTF:
	case READ:	
	case FINDPATH:
		return 3; // 2 argumenty + operator
	break;
	default:
		return -1;
	break;
	}
}



bool RobotProcessor::go()
{
	if(error)
	{
		TRACE("ERROR\n");
		return false;;
	}

	if(!done) 
		performExternal();
	else
	{
		done = false;
		cycle = 0; //ile cykli na takt - nie zaimplementowane
		while(!external)
		{
			instruction = program->getInstruction();
			if ( instruction == NULL )
			{
				exception(instruction->getLine(), "ERROR - END OF PROGRAM\n");
				error = true;
				break;
			}
			perform();	
			if (error)
				break;
		}
		if(!error)
			performExternal();
	}
	return true;
}

void RobotProcessor::perform()
{
	int temp;

	switch(instruction->getRozkaz())
	{
	case MEMFRONT:
		registry[0] = r->memory->lookAround(r->getPosition(),r->memory->getDirection());	
	break;
	case MEMLEFT:
		registry[0] = r->memory->lookAround(r->getPosition(),Direction::getLeft(r->memory->getDirection()));	
	break;
	case MEMRIGHT:
		registry[0] = r->memory->lookAround(r->getPosition(),Direction::getRight(r->memory->getDirection()));	
	break;
	case MEMBACK:
		registry[0] = r->memory->lookAround(r->getPosition(),Direction::getBackward(r->memory->getDirection()));	
	break;
	case INC:
		temp = instruction->getValue1();
		if((temp >= REGISTRYSIZE) || ( temp < 0))
			exception(instruction->getLine(),"ERROR INC- REGISTRY NOT PROPER\n");
		else
			registry[temp]++;
	break;
	case DEC:
		temp = instruction->getValue1();
		if((temp >= REGISTRYSIZE) || ( temp < 0))
			exception(instruction->getLine(), "ERROR DEC- REGISTRY NOT PROPER\n");
		else
			registry[temp]--;
	break;
	case RAND:
		registry[0] = rand()%instruction->getValue1();
	break;
	case JUMP:
		if(!program->gotoInstruction(instruction->getValue1()))
			exception(instruction->getLine(), "ERROR JUMP - LINE NOT EXIST\n");
	break;
	case JEQUAL:
		temp = instruction->getValue1();
		if(instruction->getOperation() == OPEQUAL)
		{
			if(temp == registry[0])
			{
				if(!program->gotoInstruction(instruction->getValue2()))
					exception(instruction->getLine(), "ERROR JEQUAL= - LINE NOT EXIST\n");
			}
		}
		else
		{
			if((temp >= REGISTRYSIZE) || (temp < 0))
				exception(instruction->getLine(), "ERROR JEQUAL* - REGISTRY NOT PROPER\n");
			else
			{
				if(registry[temp] == registry[0])
				{
					if(!program->gotoInstruction(instruction->getValue2()))
						exception(instruction->getLine(), "ERROR JEQUAL* - LINE NOT EXIST\n");
				}
			}
		}
	break;
	case JNEQUAL:
		temp = instruction->getValue1();
		if(instruction->getOperation() == OPEQUAL)
		{
			if(temp != registry[0])
			{
				if(!program->gotoInstruction(instruction->getValue2()))
					exception(instruction->getLine(), "ERROR JNEQUAL= - LINE NOT EXIST\n");
			}
		}
		else
		{
			if((temp >= REGISTRYSIZE) || (temp < 0))
				exception(instruction->getLine(), "ERROR JNEQUAL* - REGISTRY NOT PROPER\n");
			else
			{
				if(registry[temp] != registry[0])
				{
					if(!program->gotoInstruction(instruction->getValue2()))
						exception(instruction->getLine(), "ERROR JNEQUAL* - LINE NOT EXIST\n");
				}
			}
		}
	break;
	case JGT:
		temp = instruction->getValue1();
		if(instruction->getOperation() == OPEQUAL)
		{
			if(temp == registry[0])
			{
				if(!program->gotoInstruction(instruction->getValue2()))
					exception(instruction->getLine(), "ERROR JGT= - LINE NOT EXIST\n");
			}
		}
		else
		{
			if((temp >= REGISTRYSIZE) || (temp < 0))
				exception(instruction->getLine(), "ERROR JGT* - REGISTRY NOT PROPER\n");
			else
			{
				if(registry[temp] == registry[0])
				{
					if(!program->gotoInstruction(instruction->getValue2()))
						exception(instruction->getLine(), "ERROR JGT* - LINE NOT EXIST\n");
				}
			}
		}
	break;
	case JLT:
		temp = instruction->getValue1();
		if(instruction->getOperation() == OPEQUAL)
		{
			if(temp == registry[0])
			{
				if(!program->gotoInstruction(instruction->getValue2()))
					exception(instruction->getLine(), "ERROR JLT= - LINE NOT EXIST\n");
			}
		}
		else
		{
			if((temp >= REGISTRYSIZE) || (temp < 0))
				exception(instruction->getLine(), "ERROR JLT* - REGISTRY NOT PROPER\n");
			else
			{
				if(registry[temp] == registry[0])
				{
					if(!program->gotoInstruction(instruction->getValue2()))
						exception(instruction->getLine(), "ERROR JLT* - LINE NOT EXIST\n");
				}
			}
		}
	break;
	case JUMPF:
		if(!program->gotoInstruction(instruction->getValue1()))
			exception(instruction->getLine(), "ERROR JUMP= - LINE NOT EXIST\n");
		else	
			stack->push(instruction->getLine());
	break;
	case JEQUALF:
		temp = instruction->getValue1();
		if(instruction->getOperation() == OPEQUAL)
		{
			if(temp == registry[0])
			{
				if(!program->gotoInstruction(instruction->getValue2()))
					exception(instruction->getLine(), "ERROR JEQUAL= - LINE NOT EXIST\n");
				else	
					stack->push(instruction->getLine());
			}
		}
		else
		{
			if((temp >= REGISTRYSIZE) || (temp < 0))
				exception(instruction->getLine(), "ERROR JEQUAL* - REGISTRY NOT PROPER\n");
			else
			{
				if(registry[temp] == registry[0])
				{
					if(!program->gotoInstruction(instruction->getValue2()))
						exception(instruction->getLine(), "ERROR JEQUAL* - LINE NOT EXIST\n");
					else	
						stack->push(instruction->getLine());
				}
			}
		}
	break;
	case JNEQUALF:
		temp = instruction->getValue1();
		if(instruction->getOperation() == OPEQUAL)
		{
			if(temp != registry[0])
			{
				if(!program->gotoInstruction(instruction->getValue2()))
					exception(instruction->getLine(), "ERROR JNEQUAL= - LINE NOT EXIST\n");
				else	
					stack->push(instruction->getLine());
			}
		}
		else
		{
			if((temp >= REGISTRYSIZE) || (temp < 0))
				exception(instruction->getLine(), "ERROR JNEQUAL* - REGISTRY NOT PROPER\n");
			else
			{
				if(registry[temp] != registry[0])
				{
					if(!program->gotoInstruction(instruction->getValue2()))
						exception(instruction->getLine(), "ERROR JNEQUAL* - LINE NOT EXIST\n");
					else	
						stack->push(instruction->getLine());
				}
			}
		}
	break;
	case JGTF:
		temp = instruction->getValue1();
		if(instruction->getOperation() == OPEQUAL)
		{
			if(temp == registry[0])
			{
				if(!program->gotoInstruction(instruction->getValue2()))
					exception(instruction->getLine(), "ERROR JGT= - LINE NOT EXIST\n");
				else	
					stack->push(instruction->getLine());
			}
		}
		else
		{
			if((temp >= REGISTRYSIZE) || (temp < 0))
				exception(instruction->getLine(), "ERROR JGT* - REGISTRY NOT PROPER\n");
			else
			{
				if(registry[temp] == registry[0])
				{
					if(!program->gotoInstruction(instruction->getValue2()))
						exception(instruction->getLine(), "ERROR JGT* - LINE NOT EXIST\n");
					else	
						stack->push(instruction->getLine());
				}
			}
		}
	break;
	case JLTF:
		temp = instruction->getValue1();
		if(instruction->getOperation() == OPEQUAL)
		{
			if(temp == registry[0])
			{
				if(!program->gotoInstruction(instruction->getValue2()))
					exception(instruction->getLine(), "ERROR JLT= - LINE NOT EXIST\n");
				else	
					stack->push(instruction->getLine());
			}
		}
		else
		{
			if((temp >= REGISTRYSIZE) || (temp < 0))
				exception(instruction->getLine(), "ERROR JLT* - REGISTRY NOT PROPER\n");
			else
			{
				if(registry[temp] == registry[0])
				{
					if(!program->gotoInstruction(instruction->getValue2()))
						exception(instruction->getLine(), "ERROR JLT* - LINE NOT EXIST\n");
					else	
						stack->push(instruction->getLine());
				}
			}
		}
	break;
	case RETURN:
		temp = stack->pop();
		if (temp == -1)
			exception(instruction->getLine(), "ERROR RETURN - STACK EMPTY\n");
		if(!program->returnInstruction(temp))
			exception(instruction->getLine(), "ERROR RETURN - LINE NOT EXIST\n");
	break;
	case STORE:
		temp = instruction->getValue1();
		if((temp >= REGISTRYSIZE) || (temp < 0))
			exception(instruction->getLine(), "ERROR STORE - REGISTRY NOT PROPER\n");
		else
		{
			registry[temp] = registry[0];
		}
	break;
	case LOAD:
		temp = instruction->getValue1();
		if(instruction->getOperation() == OPEQUAL)
		{
			registry[0] = temp;
		}
		else
		{
			if((temp >= REGISTRYSIZE) || (temp < 0))
				exception(instruction->getLine(), "ERROR LOAD* - REGISTRY NOT PROPER\n");
			else
			{
				registry[0] = registry[temp];
			}
		}		
	break;
	case READ:
		int temp2;
		temp = instruction->getValue1();
		temp2 = instruction->getValue2();
		if((temp >= REGISTRYSIZE) || (temp < 0))
			exception(instruction->getLine(), "ERROR READ= - REGISTRY NOT PROPER\n");
		else
		{
			if(instruction->getOperation() == OPEQUAL)
			{
				registry[temp] = temp2;
			}
			else
			{
				if((temp2 >= REGISTRYSIZE) || (temp < 0))
					exception(instruction->getLine(), "ERROR READ* - REGISTRY NOT PROPER\n");
				else
				{
					registry[temp] = registry[temp2];
				}
			}		
		}
	break;
	default:
		external = true;
		done = false;
	break;
	}
}

void RobotProcessor::performExternal()
{
	int temp;
	int temp2;

	external = false;
	switch(instruction->getRozkaz())
	{
	case SCAN: 
		r->stateScaner++;
		r->stateScaner %= Robot::SCANERSTATE;
		if(r->stateScaner == 1)
			r->scaner->scan();
		if(r->stateScaner == 0)
			done = true;
	break;
	case TURNLEFT: 
		r->memory->turnLeft(r->battery->plug());
		r->battery->unplug();
		done = true;
	break;
	case TURNRIGHT:
		r->memory->turnRight(r->battery->plug());
		r->battery->unplug();
		done = true;
	break;
	case MOVE:
		r->stateMove++;
		r->stateMove %= Robot::MOVESTATE;
		if(r->stateMove == 1)
		{
		 	if(!r->getWorld()->move(r,r->memory->getDirection(),r->battery->getMaxCapacity(),r->battery->plug()))
		 	{	
				TRACE("PRZESZKODA!\n");
				r->stateMove = 0;
				registry[0] = 1;
 			}
			r->battery->unplug();
		}
		if(r->stateMove == 0 )
		{
			r->memory->setMemoryCell(r->getPosition(),VISITED);
			done = true;
			registry[0] = 0;
		}
	break;
	case CLEAN:
		Rubbish* rubbish ;
		rubbish = NULL;
		r->stateClean++;
		if(r->scaner->scanMyCell(WorldObjectVerifier::RUBBISH) == NULL)
		{
				done = true;
				r->stateClean = 0;
				r->memory->setMemoryCell(r->getPosition(),VISITED);
		}
		else
		{
			if((rubbish = (Rubbish*)r->scaner->scanMyCell(WorldObjectVerifier::RUBBISH)) != NULL)
			{
				rubbish->cleaning(r->battery->getMaxCapacity(),r->battery->plug());
				r->battery->unplug();
			}
			else	
				exception(instruction->getLine(), "WRONG CLEAN INSTRUCTION!!!!!!!!!!!!");
		}
	break;
	case RECEIVE:
		done = true;
	break;
	case FINDPATH:
		temp = instruction->getValue1();
		temp2 = instruction->getValue2();
		if(instruction->getOperation() == OPEQUAL)
		{
				registry[0] = r->memory->findPath(temp,temp2);
		}
		else
		{
			if((temp >= REGISTRYSIZE) || (temp < 0) || (temp2 >= REGISTRYSIZE) || (temp2 < 0)) 
				exception(instruction->getLine(), "ERROR FINDPATH* - REGISTRY NOT PROPER\n");
			else
			{
				registry[0] = r->memory->findPath(temp,temp2);
			}
		}
		done = true;
	break;
	case FIND:
		Position* p;
		p = r->memory->find(instruction->getValue1());
		registry[0] = p->x;
		registry[1] = p->y;
		delete p;
		done = true;
	break;
	case FOLLOW:
		temp = r->memory->followpath();
		if (temp == -1)
			registry[0] = 2;
		else if (temp == RobotPath::LEFT)
		{
			r->memory->turnLeft(r->battery->plug());
			r->battery->unplug();
		}
		else if (temp == RobotPath::RIGHT)
		{
			r->memory->turnRight(r->battery->plug());
			r->battery->unplug();
		}
		else if (temp == RobotPath::MOVE)
		{
			if(!r->getWorld()->move(r,r->memory->getDirection(),r->battery->getMaxCapacity(),r->battery->plug()))
			{	
				TRACE("PRZESZKODA NA SZLAKU!\n");
				registry[0] = 1;
 			}
			else
				registry[0] = 0;
			r->battery->unplug();
		}
	break;
	case POS:
		registry[0]	= r->getPosition()->x;
		registry[1] = r->getPosition()->y;
	break;
	case WORLD:
		registry[0] = r->memory->getColumns() - 1;
		registry[1] = r->memory->getRows() - 1;
	break;
	}
}

void RobotProcessor::exception(int line, char* description)
{
	TRACE("LINE %d : %s",line,description);
	error = true;
}
