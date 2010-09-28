// WorldObjectList.h: interface for the WorldObjectList class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_WORLDOBJECTLIST_H__976CF244_52EB_11D6_A89A_0010A484ECEC__INCLUDED_)
#define AFX_WORLDOBJECTLIST_H__976CF244_52EB_11D6_A89A_0010A484ECEC__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include "List.h"

class WorldObject;

class WorldObjectList : public List  
{
public:
	WorldObject* getObjectByName(int className);
	WorldObject* getObject();
	bool isObjectByName(int className);
	WorldObject* getNext();
	WorldObjectList();
	virtual ~WorldObjectList();

};

#endif // !defined(AFX_WORLDOBJECTLIST_H__976CF244_52EB_11D6_A89A_0010A484ECEC__INCLUDED_)
