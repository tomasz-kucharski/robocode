// ListNode.cpp: implementation of the Bitmapa class.
//
//////////////////////////////////////////////////////////////////////
#include "Stdafx.h"
#include "OpenGL.h"

#include "Inkludy.h"
#ifdef _DEBUG
#undef THIS_FILE
static char THIS_FILE[]=__FILE__;
#define new DEBUG_NEW
#endif

//////////////////////////////////////////////////////////////////////
// Construction/Destruction
//////////////////////////////////////////////////////////////////////

ListNode::ListNode(Object* object, ListNode* next)
{
	this->object = object;
	this->next = next;
}

ListNode::~ListNode()
{
	
}

Object* ListNode::getObject()
{
	return object;
}

ListNode* ListNode::getNext()
{
	return next;
}


void ListNode::setNext(ListNode *next)
{
 	this->next = next;
}
