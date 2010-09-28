// WorldService.cpp: implementation of the WorldService class.
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

WorldService::WorldService(const char* file)
{
	DeployWorld* deploy = new DeployWorld(file);
	modelWorld = deploy->getWorld();
	robot = NULL;
	robot = (Robot*) deploy->getRobot();
	robotView = false;

	delete deploy;
	columns = modelWorld->getColumns();
	rows = modelWorld->getRows();

	viewWorld = new WorldGL(columns,rows);
}

WorldService::~WorldService()
{
	delete modelWorld;
	delete viewWorld;
}

void WorldService::onDraw()
{
	Position p(0,0);
	WorldObjectList* list;
	WorldObject* object;
	
	if (!robotView) 
		viewWorld->beginScene();
	else  {
		robotPosition = robot->getPosition();
		robotDirection = robot->getDirection();
		viewWorld->beginScene(robotPosition->x, robotPosition->y, robotDirection);
	}
	//viewWorld->renderObject(1,1,robot);
	for(p.x=0; p.x<columns; p.x++)
		for(p.y=0; p.y<rows; p.y++) {
			list = modelWorld->getCell(&p);
			list->setToFirst();
			while(object = list->getNext()) {
				if(!viewWorld->renderObject(p.x,p.y,object))
					exit(50);
			}
		}
	viewWorld->endScene();
}

void WorldService::evolve()
{
	Position p(0,0);
	WorldObjectList* list;
	WorldObject* object;

	for(p.x=0; p.x<columns; p.x++)
		for(p.y=0; p.y<rows; p.y++) {
			list = modelWorld->getCell(&p);
			list->setToFirst();
			while(object = list->getNext()) {
				if(!modelWorld->getMoved(object)) {
					object->evolve();
					modelWorld->setMoved(object);
				}
			}	
		}
	modelWorld->clearWorld();
}

void WorldService::onSetXYZRotate(float x, float y, float z )
{
	viewWorld->onSetXYZRotate(x,y,z);
}

void WorldService::onSetScale(float x)
{
	viewWorld->onSetScale(x);
}

void WorldService::onResize(GLsizei x, GLsizei y)
{
	viewWorld->ReSizeGLScene(x,y);
}

void WorldService::onAntialiasing(BOOL state)
{
	viewWorld->onAntialiasing(state);
}

void WorldService::onWireframe(BOOL state)
{
	viewWorld->onWireframe(state);
}

void WorldService::onSetXYZMove(float x, float y, float z)
{
	viewWorld->onSetXYZMove(x,y,z);
}

void WorldService::OnRobotview(bool robotView)
{
	this->robotView = robotView;
}

void WorldService::onInit()
{
	viewWorld->InitGL();
}
