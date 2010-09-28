// OpenGL.h : main header file for the OPENGL application
//

#if !defined(AFX_OPENGL_H__8C53D285_3D2B_11D6_A899_0010A484ECEC__INCLUDED_)
#define AFX_OPENGL_H__8C53D285_3D2B_11D6_A899_0010A484ECEC__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#ifndef __AFXWIN_H__
	#error include 'stdafx.h' before including this file for PCH
#endif

#include "resource.h"		// main symbols

#include <gl\gl.h>
#include <gl\glu.h>
#include <gl\glaux.h>

/////////////////////////////////////////////////////////////////////////////
// COpenGLApp:
// See OpenGL.cpp for the implementation of this class
//

class COpenGLApp : public CWinApp
{
public:
	COpenGLApp();

// Overrides
	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(COpenGLApp)
	public:
	virtual BOOL InitInstance();
	//}}AFX_VIRTUAL

// Implementation

	//{{AFX_MSG(COpenGLApp)
		// NOTE - the ClassWizard will add and remove member functions here.
		//    DO NOT EDIT what you see in these blocks of generated code !
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
};


/////////////////////////////////////////////////////////////////////////////

//{{AFX_INSERT_LOCATION}}
// Microsoft Visual C++ will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_OPENGL_H__8C53D285_3D2B_11D6_A899_0010A484ECEC__INCLUDED_)
