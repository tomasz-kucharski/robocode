// List.cpp: implementation of the List class.
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

List::List()
{
	first = NULL;
	actual = NULL;
	last = NULL;
}

List::~List()
{
		ListNode* temp;
		actual = first;

		while(actual != NULL) {
			temp = actual->getNext();
			delete actual;
			actual = temp;
		}
}

Object* List::getNext()
{
	if(actual != NULL ) {
		ListNode* temp = actual;
		actual = actual->getNext();
		return temp->getObject();
	}
	else
		return NULL;
}

bool List::setToFirst()
{
	actual = first;
	if (actual != NULL)
		return true;
	else 
		return false;
}

void List::add(Object* const object, bool end)
{
	if (end)
	{	
		if (first == NULL)
		{
			ListNode* temp = new ListNode(object, last);
			first = temp;
			last = temp;
			actual = first;
		}
		else
		{
			ListNode* temp = new ListNode(object, NULL);
			last->setNext(temp);
			last = temp;
		}
	}
	else
	{
		ListNode* temp = new ListNode(object, first);
		first = temp;
		if (last == NULL)
		{
			last = temp;
			actual = first;
		}
	}
}

bool List::remove(Object* const object)
{
	bool objectFound = false;

	ListNode* temp = this->first; 
	ListNode* prev = NULL;
	ListNode* next = NULL;

	while( temp != NULL ) {
		if (object == temp->getObject()) {
			next = temp->getNext();
			delete temp;
			if(next == NULL)
				last = NULL;
			if(prev == NULL)
				first = next;
			else
				prev->setNext(next);
			objectFound = true;
			break;
		}
		prev = temp;
		temp = temp->getNext();
	}
	return objectFound;
}

bool List::isEmpty()
{
	if (first != NULL)
		return false;
	return true;
}


Object* List::getObject()
{
	return actual->getObject();
}

Object* List::next()
{
	if ( actual->getNext() != NULL )
	{
		actual = actual->getNext();
		return actual->getObject();
	}
	else
		return NULL;
}

bool List::removeFirst()
{
	ListNode* temp;
	if (first == NULL)
		return false;
	temp = first->getNext();
	delete first;
	first = temp;
	return true;
}
