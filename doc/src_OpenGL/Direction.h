// Direction.h: interface for the Direction class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_DIRECTION_H__976CF240_52EB_11D6_A89A_0010A484ECEC__INCLUDED_)
#define AFX_DIRECTION_H__976CF240_52EB_11D6_A89A_0010A484ECEC__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

class Position;

class Direction  
{
public:
	static int computeDirection(Position from,Position to);
	static int getForward(int direction);
	static int getBackward(int direction);
	static int getRight(int direction);
	static int getLeft(int direction);
	static int randDirection();
	static float computeRotation(int direction);
	static void computePosition (Position* p, int direction);
	static Position computePosition(Position p, int direction);
	static int getDirectionByName(char* direction);
	static int NORTH;
	static int EAST;
	static int SOUTH;
	static int WEST;
};

#endif // !defined(AFX_DIRECTION_H__976CF240_52EB_11D6_A89A_0010A484ECEC__INCLUDED_)
