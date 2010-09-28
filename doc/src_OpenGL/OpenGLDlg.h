// OpenGLDlg.h : header file
//

#if !defined(AFX_OPENGLDLG_H__8C53D287_3D2B_11D6_A899_0010A484ECEC__INCLUDED_)
#define AFX_OPENGLDLG_H__8C53D287_3D2B_11D6_A899_0010A484ECEC__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

/////////////////////////////////////////////////////////////////////////////
// COpenGLDlg dialog
#include "WorldService.h"

class CController;

class COpenGLDlg : public CDialog
{
private:
	WorldService* world;
	bool mouseButton;
	CPoint mousePoint;
	int pixelFormat;
	HGLRC hRC;
public:
	BOOL COpenGLDlg::CreateViewGLContext(HDC hDC);
	BOOL COpenGLDlg::SetWindowPixelFormat(HDC hDC);
	void OnRobotView(bool robotView);
	CController *controller;
	COpenGLDlg(CWnd* pParent = NULL);	// standard constructor
	void OnAntialiasing(BOOL antialiasing);
	void OnWireframe(BOOL wireframe);
	void OnScale(int scale);
	void OnTimer();

// Dialog Data
	//{{AFX_DATA(COpenGLDlg)
	enum { IDD = IDD_OPENGL_DIALOG };
	//}}AFX_DATA

	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(COpenGLDlg)
	protected:
	virtual void DoDataExchange(CDataExchange* pDX);	// DDX/DDV support
	//}}AFX_VIRTUAL

// Implementation
protected:
	HICON m_hIcon;

	// Generated message map functions
	//{{AFX_MSG(COpenGLDlg)
	virtual BOOL OnInitDialog();
	afx_msg void OnSysCommand(UINT nID, LPARAM lParam);
	afx_msg void OnPaint();
	afx_msg HCURSOR OnQueryDragIcon();
	afx_msg int OnCreate(LPCREATESTRUCT lpCreateStruct);
	afx_msg void OnSize(UINT nType, int cx, int cy);
	afx_msg void OnClose();
	afx_msg void OnLButtonDown(UINT nFlags, CPoint point);
	afx_msg void OnLButtonUp(UINT nFlags, CPoint point);
	afx_msg void OnMouseMove(UINT nFlags, CPoint point);
	afx_msg BOOL OnMouseWheel(UINT nFlags, short zDelta, CPoint pt);
	afx_msg void OnDestroy();
	afx_msg void OnKeyDown(UINT nChar, UINT nRepCnt, UINT nFlags);
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
};

//{{AFX_INSERT_LOCATION}}
// Microsoft Visual C++ will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_OPENGLDLG_H__8C53D287_3D2B_11D6_A899_0010A484ECEC__INCLUDED_)
