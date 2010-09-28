// PathObject.h: interface for the PathObject class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_PATHOBJECT_H__C1D81185_DCB4_4A8E_B8E5_A8503F691552__INCLUDED_)
#define AFX_PATHOBJECT_H__C1D81185_DCB4_4A8E_B8E5_A8503F691552__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include "Object.h"

class PathObject : public Object  
{
public:
	PathObject(int value);
	virtual ~PathObject();
	int value;

};

#endif // !defined(AFX_PATHOBJECT_H__C1D81185_DCB4_4A8E_B8E5_A8503F691552__INCLUDED_)
