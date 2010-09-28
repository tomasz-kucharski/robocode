#if !defined(AFX_CONTROLLER_H__74D912E3_3FFC_11D6_A899_0010A484ECEC__INCLUDED_)
#define AFX_CONTROLLER_H__74D912E3_3FFC_11D6_A899_0010A484ECEC__INCLUDED_

#include "OpenGLDlg.h"	// Added by ClassView
#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000
// Controller.h : header file
//

/////////////////////////////////////////////////////////////////////////////
// CController dialog

class COpenGLDlg;

class CController : public CDialog
{
// Construction
public:
	void addToFPS(double duration);
	COpenGLDlg * dlg;
	CController(COpenGLDlg* dlg);   // standard constructor

// Dialog Data
	//{{AFX_DATA(CController)
	enum { IDD = IDD_CONTROLLER };
	CSliderCtrl	m_Scale;
	BOOL	m_Antialiasing;
	BOOL	m_Wireframe;
	BOOL	m_Start;
	BOOL	m_RobotView;
private:
	CString	m_FPS;
public:
	//}}AFX_DATA


// Overrides
	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CController)
	protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV support
	//}}AFX_VIRTUAL

// Implementation
protected:

	// Generated message map functions
	//{{AFX_MSG(CController)
	afx_msg void OnAntialiasing();
	afx_msg void OnWireframe();
	virtual BOOL OnInitDialog();
	afx_msg void OnHScroll(UINT nSBCode, UINT nPos, CScrollBar* pScrollBar);
	afx_msg void OnStart();
	afx_msg void OnTimer(UINT nIDEvent);
	afx_msg void OnRobotview();
	afx_msg void OnButtonup();
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
};

//{{AFX_INSERT_LOCATION}}
// Microsoft Visual C++ will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_CONTROLLER_H__74D912E3_3FFC_11D6_A899_0010A484ECEC__INCLUDED_)
