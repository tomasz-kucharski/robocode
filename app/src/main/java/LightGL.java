// LightGL.cpp: implementation of the LightGL class.
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

LightGL::LightGL()
{
	LightAmbient[0]= 0.4f;
	LightAmbient[1]= 0.4f;
	LightAmbient[2]= 0.4f;
	LightAmbient[3]= 1.0f;

	LightDiffuse[0] = 1.0f;
	LightDiffuse[1] = 1.0f;
	LightDiffuse[2] = 1.0f;
	LightDiffuse[3] = 1.0f;

	LightPosition[0] = 10.0f;
	LightPosition[1] = 10.0f;
	LightPosition[2] = -10.0f;
	LightPosition[3] = 5.0f;

	LightSpecular[0] = 0.8f;
	LightSpecular[1] = 0.8f;
	LightSpecular[2] = 0.8f;
	LightSpecular[3] = 1.0f;
}

LightGL::~LightGL()
{

}

void LightGL::init()
{

}
