// Bitmapa.h: interface for the Bitmapa class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_LISTNODE_H__C4581A21_3B7C_11D6_A899_0010A484ECEC__INCLUDED_)
#define AFX_LISTNODE_H__C4581A21_3B7C_11D6_A899_0010A484ECEC__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

class Object;

class ListNode
{
private:
	Object* object;
	ListNode* next;
public:
	void setNext(ListNode *next);
	Object* getObject();
	ListNode* getNext();

	ListNode(Object* object, ListNode* next);
	virtual ~ListNode();

};

#endif 
