// WorldObjectVerifier.h: interface for the WorldObjectVerifier class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_WORLDOBJECTVERIFIER_H__976CF25A_52EB_11D6_A89A_0010A484ECEC__INCLUDED_)
#define AFX_WORLDOBJECTVERIFIER_H__976CF25A_52EB_11D6_A89A_0010A484ECEC__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

class WorldObjectVerifier  
{
public:
	static int getWorldObjectByName(char* name);
	const static int ROBOT;
	const static int WALL;
	const static int FURNITURE;
	const static int RUBBISH;
	const static int FLOOR;
	const static int DEPOT;

};

#endif // !defined(AFX_WORLDOBJECTVERIFIER_H__976CF25A_52EB_11D6_A89A_0010A484ECEC__INCLUDED_)
