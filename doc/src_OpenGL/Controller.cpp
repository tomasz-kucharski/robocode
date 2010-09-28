// Controller.cpp : implementation file
//

#include "stdafx.h"
#include "OpenGL.h"
#include "Controller.h"
#include "OpenGLDlg.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif

/////////////////////////////////////////////////////////////////////////////
// CController dialog


CController::CController(COpenGLDlg* dlg)
	: CDialog(CController::IDD, NULL)
{
	this->dlg = dlg;
	//{{AFX_DATA_INIT(CController)
	m_Antialiasing = FALSE;
	m_Wireframe = FALSE;
	m_Start = FALSE;
	m_RobotView = FALSE;
	m_FPS = _T("");
	//}}AFX_DATA_INIT
}


void CController::DoDataExchange(CDataExchange* pDX)
{
	CDialog::DoDataExchange(pDX);
	//{{AFX_DATA_MAP(CController)
	DDX_Control(pDX, IDC_SCALE, m_Scale);
	DDX_Check(pDX, IDC_ANTIALIASING, m_Antialiasing);
	DDX_Check(pDX, IDC_WIREFRAME, m_Wireframe);
	DDX_Check(pDX, IDC_GO, m_Start);
	DDX_Check(pDX, IDC_ROBOTVIEW, m_RobotView);
	DDX_Text(pDX, IDC_FPS, m_FPS);
	DDV_MaxChars(pDX, m_FPS, 100);
	//}}AFX_DATA_MAP
}


BEGIN_MESSAGE_MAP(CController, CDialog)
	//{{AFX_MSG_MAP(CController)
	ON_BN_CLICKED(IDC_ANTIALIASING, OnAntialiasing)
	ON_BN_CLICKED(IDC_WIREFRAME, OnWireframe)
	ON_WM_HSCROLL()
	ON_BN_CLICKED(IDC_GO, OnStart)
	ON_WM_TIMER()
	ON_BN_CLICKED(IDC_ROBOTVIEW, OnRobotview)
	ON_BN_CLICKED(IDC_BUTTONUP, OnButtonup)
	//}}AFX_MSG_MAP
END_MESSAGE_MAP()

/////////////////////////////////////////////////////////////////////////////
// CController message handlers


void CController::OnAntialiasing() 
{
	m_Antialiasing = !m_Antialiasing;
	dlg->OnAntialiasing(m_Antialiasing);
}

void CController::OnWireframe() 
{
	m_Wireframe = !m_Wireframe;
	dlg->OnWireframe(m_Wireframe);
}

BOOL CController::OnInitDialog() 
{
	CDialog::OnInitDialog();
	
	m_Scale.SetRange(1,100,TRUE);
	m_Scale.SetPos(50);
	
	return TRUE;  // return TRUE unless you set the focus to a control
	              // EXCEPTION: OCX Property Pages should return FALSE
}

void CController::OnHScroll(UINT nSBCode, UINT nPos, CScrollBar* pScrollBar) 
{
	dlg->OnScale(m_Scale.GetPos());
	CDialog::OnHScroll(nSBCode, nPos, pScrollBar);
}

void CController::OnStart() 
{
	m_Start = !m_Start;
	if(m_Start)
		SetTimer(1,100,NULL);
	else
		KillTimer(1);
}

void CController::OnTimer(UINT nIDEvent) 
{
	dlg->OnTimer();	
}

void CController::OnRobotview() 
{
	// TODO: Add your control notification handler code here
	m_RobotView = !m_RobotView;
	if (m_RobotView) 
		dlg->OnRobotView(true);
	else 
		dlg->OnRobotView(false);
}

void CController::OnButtonup() 
{
	// TODO: Add your control notification handler code here
	
}

void CController::addToFPS(double duration)
{
	char buffer[50];
	_gcvt( duration, 15, buffer );
//	m_FPS.Insert(1,"cos");
}
