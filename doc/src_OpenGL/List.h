// ListOfBitmaps.h: interface for the ListOfBitmaps class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_LISTOFBITMAPS_H__C4581A24_3B7C_11D6_A899_0010A484ECEC__INCLUDED_)
#define AFX_LISTOFBITMAPS_H__C4581A24_3B7C_11D6_A899_0010A484ECEC__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

class ListNode;
class Object;

class List  
{
private:
	ListNode* actual;
	ListNode* first;
	ListNode* last;
public:
	bool removeFirst();
	bool isEmpty();
	bool remove(Object* const object);
	bool setToFirst();
	void add(Object* const object, bool end = false);
	virtual ~List();
protected:
	Object* next();
	Object* getObject();
	Object* getNext();
	List();
};

#endif // !defined(AFX_LISTOFBITMAPS_H__C4581A24_3B7C_11D6_A899_0010A484ECEC__INCLUDED_)
