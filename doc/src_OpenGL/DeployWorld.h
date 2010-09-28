// DeployWorld.h: interface for the DeployWorld class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_DeployWorld_H__976CF248_52EB_11D6_A89A_0010A484ECEC__INCLUDED_)
#define AFX_DeployWorld_H__976CF248_52EB_11D6_A89A_0010A484ECEC__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include <fstream.h>

class World;
class Position;
class WorldObject;


class DeployWorld  
{
public:
	WorldObject* getRobot();
	World* getWorld();
	WorldObject* robot;
	DeployWorld(const char* file);
	virtual ~DeployWorld();

private:
	void eatAll();
	bool loadObject(char* type, Position* p, int data, int data2 = 0, 
				char* direction = NULL, char* name = NULL, char* fileName = NULL);
	void getToken();
	bool init();
	bool loadWorld();
	
	World* modelWorld;
	ifstream in;
	char* token;
	int size;

	int columns;
	int rows;
};

#endif // !defined(AFX_DeployWorld_H__976CF248_52EB_11D6_A89A_0010A484ECEC__INCLUDED_)
