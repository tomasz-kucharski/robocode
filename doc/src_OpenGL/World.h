// World.h: interface for the World class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_WORLD_H__EFB1FB42_3C74_11D6_A899_0010A484ECEC__INCLUDED_)
#define AFX_WORLD_H__EFB1FB42_3C74_11D6_A899_0010A484ECEC__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

class WorldObjectList;
class WorldObject;
class Position;

class World  
{
private:
	WorldObjectList ***world;
	int columns;
	int rows;
	bool moveObject(WorldObject* object, int direction);
public:
	bool getMoved(WorldObject* object);
	void setMoved(WorldObject* object);
	WorldObject* getObject(Position*p, int className);
	void clearWorld();
	bool deleteMe(WorldObject* const worldObject);
	bool move(WorldObject* const object, const int direction, const int maxPower,
		int& usedPower);
	bool checkPosition(Position* p);
	bool checkValidate();
	bool setCell(Position* p, WorldObject* object);
	WorldObjectList* getCell(Position* p);
	int getRows();
	int getColumns();
	World(int columns, int rows);
	virtual ~World();

};

#endif // !defined(AFX_WORLD_H__EFB1FB42_3C74_11D6_A899_0010A484ECEC__INCLUDED_)
