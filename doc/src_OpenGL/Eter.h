// Eter.h: interface for the Eter class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_ETER_H__BE50ACEA_571B_11D6_A89A_0010A484ECEC__INCLUDED_)
#define AFX_ETER_H__BE50ACEA_571B_11D6_A89A_0010A484ECEC__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include "List.h"

class Message;

class Eter : public List  
{
public:
	void clearMessages();
	Message* retrieveMessage();
	bool sendMessage(Message* message);
	Eter();
	virtual ~Eter();

};

#endif // !defined(AFX_ETER_H__BE50ACEA_571B_11D6_A89A_0010A484ECEC__INCLUDED_)
