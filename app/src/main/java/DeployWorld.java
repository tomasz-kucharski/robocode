// DeployWorld.cpp: implementation of the DeployWorld class.
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

DeployWorld::DeployWorld(const char* file)
{
	size = 40;
	in.open(file);
	token = new char[size];

	rows = 0;
	columns = 0;

	if(init())
		modelWorld = new World(columns,rows);
	else 
		exit(10);
	if (!loadWorld())
		exit(20);
	if (!modelWorld->checkValidate())
		exit(30);
}

DeployWorld::~DeployWorld()
{
	in.close();
	delete[] token;
}

bool DeployWorld::init()
{	
	do
	{	
		getToken();
		if(token[0] == 0) 
			return false;
		if(token[0] == '#') 
			continue;
		if(!strcmp(token,"columns")) {
			in >> columns;
			eatAll();
		}
		if(!strcmp(token,"rows")) {
			in >> rows;
			eatAll();
		}
		if((columns != 0) && (rows != 0))
			return true;
	} while (true);

	return false;
}

void DeployWorld::getToken()
{
	
	char c;
	while(true) {
		if(in.get(c) != NULL ) {	
			if(!isspace(c)) //BIALY ZNAK
				break;
		}
		else { //KONIEC PLIKU
			token[0] = 0;
			return;
		}
	}
	
	if( c == '#' ) 	{
		eatAll();
		token[0] = '#';
		return;
	}

	in.putback(c); // ZWROC CO POBRALES

	for(int i=0; i<size; i++) {
		if(!(in.get(c)) || isspace(c)) {
			token[i] = 0;					// KONIEC STRING-a
			break;
		}
		token[i] = c;
	}
}

bool DeployWorld::loadWorld()
{

	Position* p = new Position(0,0);

	char* type = new char[size];	
	char* name = new char[size];	
	char* direction = new char[size];
	char* fileName = new char[size];

	int data;
	int data2;

	//x y TYPE data data2 DIRECTION NAME
	do
	{	
		in >> p->x >> p->y;
		getToken();
		strcpy(type,token);
		in >> data;

		if(type[0] == 0) //przeniesc pod gettoken
			break;
		if(!strcmp(type,"ROBOT")) {
			in >> data2;
			getToken();
			strcpy(direction,token);
			getToken();
			strcpy(name,token);
			getToken();
			strcpy(fileName,"Intelligence\\");
			strcat(fileName,token);
			if(!loadObject(type,p,data,data2,direction,name,fileName))
				return false;
		}
		else {
			eatAll();
			loadObject(type,p,data);
		}

	} while (true);

	delete p;
	delete[] type;
	delete[] name;
	delete[] direction;
	delete[] fileName;
	return true;
}


bool DeployWorld::loadObject(char* type, Position *p, int data, int data2, 
							 char* direction, char* name, char* fileName)
{
	WorldObject* worldObject;

	int typeOfWorldObject = WorldObjectVerifier::getWorldObjectByName(type);
	
	//CREATE ROBOT
	if ( typeOfWorldObject == WorldObjectVerifier::ROBOT) {
		worldObject = new Robot(p,columns,rows,name,Direction::getDirectionByName(direction),data,data2,fileName);
		robot = worldObject;
	}
	//CREATE FLOOR
	else if( typeOfWorldObject == WorldObjectVerifier::FLOOR) 
		worldObject = new Floor(p,data);
	//CREATE WALL
	else if( typeOfWorldObject == WorldObjectVerifier::WALL)
		worldObject = new Wall(p,data);
	//CREATE RUBBISH
	else if( typeOfWorldObject == WorldObjectVerifier::RUBBISH)
		worldObject = new Rubbish(p,data);
	//CREATE DEPOT
	else if( typeOfWorldObject == WorldObjectVerifier::DEPOT)
		worldObject = new Depot(p,data);
	//CREATE FURNITURE
	else if( typeOfWorldObject == WorldObjectVerifier::FURNITURE)
		worldObject = new Furniture(p,data);
	//ADD TO WORLD 
	if (!modelWorld->setCell(p,worldObject))
		return false;
	else
		return true;
}

void DeployWorld::eatAll()
{
	char c;
	while(in.get(c))
		if(c=='\n')
			break;
}

World* DeployWorld::getWorld()
{
	return modelWorld;
}

WorldObject* DeployWorld::getRobot()
{
	return robot;
}
