// LightGL.h: interface for the LightGL class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_LIGHTGL_H__74D912E4_3FFC_11D6_A899_0010A484ECEC__INCLUDED_)
#define AFX_LIGHTGL_H__74D912E4_3FFC_11D6_A899_0010A484ECEC__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

class LightGL  
{
public:
	GLfloat LightAmbient[4];
	GLfloat LightDiffuse[4];
	GLfloat LightPosition[4];
	GLfloat LightSpecular[4];
	GLfloat LightShininess[1];

	GLuint filter;
public:
	void init();
	LightGL();
	virtual ~LightGL();

};

#endif // !defined(AFX_LIGHTGL_H__74D912E4_3FFC_11D6_A899_0010A484ECEC__INCLUDED_)
