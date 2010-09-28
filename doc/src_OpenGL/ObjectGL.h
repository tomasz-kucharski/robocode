// ObjectGL.h: interface for the ObjectGL class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_OBJECTGL_H__45EB5E61_522C_11D6_A89A_0010A484ECEC__INCLUDED_)
#define AFX_OBJECTGL_H__45EB5E61_522C_11D6_A89A_0010A484ECEC__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

class WorldObject;

class ObjectGL  
{
public:
	virtual void init() = 0 ;
	ObjectGL();
	virtual void draw(WorldObject* object) =0 ;
	virtual ~ObjectGL();
protected:
	GLvoid loadGLTextures(char* file);
	GLuint texture[1];
	AUX_RGBImageRec *texture1;
	GLuint list;

};

#endif // !defined(AFX_OBJECTGL_H__45EB5E61_522C_11D6_A89A_0010A484ECEC__INCLUDED_)
