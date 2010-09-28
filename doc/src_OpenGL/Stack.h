// Stack.h: interface for the Stack class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_STACK_H__370AEED4_4031_48DD_9099_042C50DFE084__INCLUDED_)
#define AFX_STACK_H__370AEED4_4031_48DD_9099_042C50DFE084__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include "List.h"

class Stack : public List  
{
public:
	int pop();
	void push(int value);
	Stack();
	virtual ~Stack();

};

#endif // !defined(AFX_STACK_H__370AEED4_4031_48DD_9099_042C50DFE084__INCLUDED_)
