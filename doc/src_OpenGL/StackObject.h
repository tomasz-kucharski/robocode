// StackObject.h: interface for the StackObject class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_STACKOBJECT_H__5AA5694F_7162_49F1_B179_B0FB31EB0095__INCLUDED_)
#define AFX_STACKOBJECT_H__5AA5694F_7162_49F1_B179_B0FB31EB0095__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include "Object.h"

class StackObject : public Object  
{
public:
	int value;
	StackObject(int value);
	virtual ~StackObject();

};

#endif // !defined(AFX_STACKOBJECT_H__5AA5694F_7162_49F1_B179_B0FB31EB0095__INCLUDED_)
