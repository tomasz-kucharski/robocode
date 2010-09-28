// RobotPath.h: interface for the RobotPath class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_ROBOTPATH_H__0D491814_B302_457B_B7AB_91F0FE3EB487__INCLUDED_)
#define AFX_ROBOTPATH_H__0D491814_B302_457B_B7AB_91F0FE3EB487__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include "List.h"


class RobotPath : public List  
{
public:
	void clean();
//	static const int START;
	static const int LEFT;
	static const int RIGHT;
	static const int MOVE;
//	static const int STOP;

	int pop();
	void push(int value);
	RobotPath();
	virtual ~RobotPath();

};

#endif // !defined(AFX_ROBOTPATH_H__0D491814_B302_457B_B7AB_91F0FE3EB487__INCLUDED_)
