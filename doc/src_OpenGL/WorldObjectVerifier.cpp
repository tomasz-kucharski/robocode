// WorldObjectVerifier.cpp: implementation of the WorldObjectVerifier class.
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

const int WorldObjectVerifier::FLOOR = 0;
const int WorldObjectVerifier::FURNITURE = 5;
const int WorldObjectVerifier::ROBOT = 2;
const int WorldObjectVerifier::RUBBISH = 1;
const int WorldObjectVerifier::WALL = 3;
const int WorldObjectVerifier::DEPOT = 4;

int WorldObjectVerifier::getWorldObjectByName(char *name)
{
 	if (name != NULL) {
 		if(!strcmp(name,"FLOOR"))
 			return 0;
 		if(!strcmp(name,"FURNITURE"))
 			return 5;
		if(!strcmp(name,"ROBOT"))
 			return 2;
 		if(!strcmp(name,"RUBBISH"))
 			return 1;
		if(!strcmp(name,"WALL"))
			return 3;
		if(!strcmp(name,"DEPOT"))
			return 4;
	}
 	return -1;
}
