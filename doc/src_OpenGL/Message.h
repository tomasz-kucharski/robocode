// Message.h: interface for the Message class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_MESSAGE_H__BE50ACEB_571B_11D6_A89A_0010A484ECEC__INCLUDED_)
#define AFX_MESSAGE_H__BE50ACEB_571B_11D6_A89A_0010A484ECEC__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include "Object.h"

class WorldObject;

class Message :public Object
{
public:
	const static int HELP;
	const static int CLEANED;
	const static int CHECKED;
	int information;
	WorldObject* worldObject;

	Message(WorldObject* worldObject, int information);
	~Message();
};

#endif // !defined(AFX_MESSAGE_H__BE50ACEB_571B_11D6_A89A_0010A484ECEC__INCLUDED_)
