// OpenGLDlg.cpp : implementation file
//
#include <time.h>

#include "stdafx.h"
#include "OpenGL.h"
#include "OpenGLDlg.h"
#include "WorldService.h"
#include "Controller.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif

/////////////////////////////////////////////////////////////////////////////
// CAboutDlg dialog used for App About

class CAboutDlg : public CDialog
{
public:
	CAboutDlg();

// Dialog Data
	//{{AFX_DATA(CAboutDlg)
	enum { IDD = IDD_ABOUTBOX };
	//}}AFX_DATA

	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CAboutDlg)
	protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV support
	//}}AFX_VIRTUAL

// Implementation
protected:
	//{{AFX_MSG(CAboutDlg)
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
};

CAboutDlg::CAboutDlg() : CDialog(CAboutDlg::IDD)
{
	//{{AFX_DATA_INIT(CAboutDlg)
	//}}AFX_DATA_INIT
}

void CAboutDlg::DoDataExchange(CDataExchange* pDX)
{
	CDialog::DoDataExchange(pDX);
	//{{AFX_DATA_MAP(CAboutDlg)
	//}}AFX_DATA_MAP
}

BEGIN_MESSAGE_MAP(CAboutDlg, CDialog)
	//{{AFX_MSG_MAP(CAboutDlg)
		// No message handlers
	//}}AFX_MSG_MAP
END_MESSAGE_MAP()

/////////////////////////////////////////////////////////////////////////////
// COpenGLDlg dialog

COpenGLDlg::COpenGLDlg(CWnd* pParent /*=NULL*/)
	: CDialog(COpenGLDlg::IDD, pParent)
{
	//{{AFX_DATA_INIT(COpenGLDlg)
	//}}AFX_DATA_INIT
	// Note that LoadIcon does not require a subsequent DestroyIcon in Win32
	m_hIcon = AfxGetApp()->LoadIcon(IDR_MAINFRAME);
}

void COpenGLDlg::DoDataExchange(CDataExchange* pDX)
{
	CDialog::DoDataExchange(pDX);
	//{{AFX_DATA_MAP(COpenGLDlg)
	//}}AFX_DATA_MAP
}

BEGIN_MESSAGE_MAP(COpenGLDlg, CDialog)
	//{{AFX_MSG_MAP(COpenGLDlg)
	ON_WM_SYSCOMMAND()
	ON_WM_PAINT()
	ON_WM_QUERYDRAGICON()
	ON_WM_CREATE()
	ON_WM_SIZE()
	ON_WM_CLOSE()
	ON_WM_LBUTTONDOWN()
	ON_WM_LBUTTONUP()
	ON_WM_MOUSEMOVE()
	ON_WM_MOUSEWHEEL()
	ON_WM_DESTROY()
	ON_BN_CLICKED(IDC_ANTIALIASING, OnAntialiasing)
	ON_WM_HSCROLL()
	ON_BN_CLICKED(IDC_WIREFRAME, OnWireframe)
	ON_WM_TIMER()
	ON_WM_KEYDOWN()
	//}}AFX_MSG_MAP
END_MESSAGE_MAP()

/////////////////////////////////////////////////////////////////////////////
// COpenGLDlg message handlers

BOOL COpenGLDlg::OnInitDialog()
{
	CDialog::OnInitDialog();

	// Add "About..." menu item to system menu.

	// IDM_ABOUTBOX must be in the system command range.
	ASSERT((IDM_ABOUTBOX & 0xFFF0) == IDM_ABOUTBOX);
	ASSERT(IDM_ABOUTBOX < 0xF000);

	CMenu* pSysMenu = GetSystemMenu(FALSE);
	if (pSysMenu != NULL)
	{
		CString strAboutMenu;
		strAboutMenu.LoadString(IDS_ABOUTBOX);
		if (!strAboutMenu.IsEmpty())
		{
			pSysMenu->AppendMenu(MF_SEPARATOR);
			pSysMenu->AppendMenu(MF_STRING, IDM_ABOUTBOX, strAboutMenu);
		}
	}

	// Set the icon for this dialog.  The framework does this automatically
	//  when the application's main window is not a dialog
	SetIcon(m_hIcon, TRUE);			// Set big icon
	SetIcon(m_hIcon, FALSE);		// Set small icon
	
	// TODO: Add extra initialization here
	controller = new CController(this);
	controller->Create(IDD_CONTROLLER);
	controller->ShowWindow(SW_SHOW);
	mouseButton = false;
	return true;  // return TRUE  unless you set the focus to a control
}

void COpenGLDlg::OnSysCommand(UINT nID, LPARAM lParam)
{
	if ((nID & 0xFFF0) == IDM_ABOUTBOX)
	{
		CAboutDlg dlgAbout;
		dlgAbout.DoModal();
	}
	else
	{
		CDialog::OnSysCommand(nID, lParam);
	}
}

// If you add a minimize button to your dialog, you will need the code below
//  to draw the icon.  For MFC applications using the document/view model,
//  this is automatically done for you by the framework.

void COpenGLDlg::OnPaint() 
{
   clock_t start, finish;
   double duration;
   static double allDuration = 0.0f;
   static long counter = 0;
   start = clock();

	CPaintDC dc(this); // device context for painting
	if (IsIconic())
	{
		SendMessage(WM_ICONERASEBKGND, (WPARAM) dc.GetSafeHdc(), 0);

		// Center icon in client rectangle
		int cxIcon = GetSystemMetrics(SM_CXICON);
		int cyIcon = GetSystemMetrics(SM_CYICON);
		CRect rect;
		GetClientRect(&rect);
		int x = (rect.Width() - cxIcon + 1) / 2;
		int y = (rect.Height() - cyIcon + 1) / 2;
		dc.DrawIcon(x, y, m_hIcon);
	}
	else
	{
		world->onDraw();
		SwapBuffers(dc.m_ps.hdc);
	}
   finish = clock();
   duration = 1.0f/((double)(finish - start) / CLOCKS_PER_SEC);
   counter++;
   allDuration += duration;

//   TRACE("Real: %f fps ",duration);
//   TRACE("Average: %f fps \n", allDuration/counter);

   if (counter == 20) {
	   allDuration = 0.0f;
	   counter = 0;
   }
//	controller->addToFPS(duration);
}

// The system calls this to obtain the cursor to display while the user drags
//  the minimized window.
HCURSOR COpenGLDlg::OnQueryDragIcon()
{
	return (HCURSOR) m_hIcon;
}

int COpenGLDlg::OnCreate(LPCREATESTRUCT lpCreateStruct) 
{	

	if (CDialog::OnCreate(lpCreateStruct) == -1)
		return -1;
	
	pixelFormat = 0;
	hRC = NULL;

	HWND hWnd = GetSafeHwnd();
	HDC hDC = ::GetDC(hWnd);
	
	RECT rect;
	::GetClientRect(hWnd,&rect);

	world = new WorldService("Maps\\robot.txt");

	if(!SetWindowPixelFormat(hDC))
		exit(1);	
	if(!CreateViewGLContext(hDC))
		exit(1);

	world->onInit(); 
	world->onResize(rect.right,rect.bottom);

	return 0;
}

void COpenGLDlg::OnSize(UINT nType, int cx, int cy) 
{
	CDialog::OnSize(nType, cx, cy);
	
	GLsizei width,height;

	width = (GLsizei)cx;
	height = (GLsizei)cy;

	world->onResize(width,height);
	InvalidateRect(NULL,FALSE);
}

void COpenGLDlg::OnClose() 
{
	CDialog::OnClose();
	if(wglGetCurrentContext() != NULL)
		wglMakeCurrent(NULL,NULL);
	if(hRC != NULL)
	{
		wglDeleteContext(hRC);
		hRC = NULL;
	};
}

void COpenGLDlg::OnLButtonDown(UINT nFlags, CPoint point) 
{
	mouseButton = true;
	mousePoint = point;
	CDialog::OnLButtonDown(nFlags, point);
}

void COpenGLDlg::OnLButtonUp(UINT nFlags, CPoint point) 
{
	mouseButton = false;
	CDialog::OnLButtonUp(nFlags, point);
}

void COpenGLDlg::OnMouseMove(UINT nFlags, CPoint point) 
{
	if(mouseButton)
	{
		float iX = -(float)(mousePoint.x - point.x)/3.0f;
		float iY = -(float)(mousePoint.y - point.y)/3.0f;
		world->onSetXYZRotate(iX,iY,0.0f);
		mousePoint = point;
		InvalidateRect(NULL,FALSE);
	}
	CDialog::OnMouseMove(nFlags, point);
}

BOOL COpenGLDlg::OnMouseWheel(UINT nFlags, short zDelta, CPoint pt) 
{
	world->onSetXYZRotate(0.0f,0.0f,(float)zDelta/10.0f);
	InvalidateRect(NULL,FALSE);
	return CDialog::OnMouseWheel(nFlags, zDelta, pt);
}

void COpenGLDlg::OnScale(int scale) 
{
	world->onSetScale(scale/50.0f);	
	InvalidateRect(NULL,FALSE);
}

void COpenGLDlg::OnWireframe(BOOL wireframe) 
{
	world->onWireframe(wireframe);
	InvalidateRect(NULL,FALSE);
}

void COpenGLDlg::OnAntialiasing(BOOL antialiasing) 
{
	world->onAntialiasing(antialiasing);
	InvalidateRect(NULL,FALSE);
}

void COpenGLDlg::OnDestroy() 
{
	CDialog::OnDestroy();
	
	// TODO: Add your message handler code here
	delete controller;
	delete world;
}

void COpenGLDlg::OnTimer() 
{
	// TODO: Add your message handler code here and/or call default
	world->evolve();
 	InvalidateRect(NULL,FALSE);

	//CDialog::OnTimer(nIDEvent);
}

void COpenGLDlg::OnKeyDown(UINT nChar, UINT nRepCnt, UINT nFlags) 
{
	//37 lewo
	//38 gora
	//39 prawo
	//40 dol
	if (nChar == '3')
		world->onSetXYZMove(0.0f,-1.0f,0.0f);
	else if (nChar == '4')
		world->onSetXYZMove(0.0f,1.0f,0.0f);
	else if (nChar == '1')
		world->onSetXYZMove(1.0f,0.0f,0.0f);
	else if (nChar == '2')
		world->onSetXYZMove(-1.0f,0.0f,0.0f);
	else if (nChar == '5')
		world->onSetXYZMove(0.0f,0.0f,-1.0f);
	else if (nChar == '6')
		world->onSetXYZMove(0.0f,0.0f,1.0f);

	InvalidateRect(NULL,FALSE);
	CDialog::OnKeyDown(nChar, nRepCnt, nFlags);
}

void COpenGLDlg::OnRobotView(bool robotView)
{
	world->OnRobotview(robotView);
}


BOOL COpenGLDlg::SetWindowPixelFormat(HDC hDC)
{
	PIXELFORMATDESCRIPTOR pixelDesc;

	pixelDesc.nSize = sizeof(PIXELFORMATDESCRIPTOR);
	pixelDesc.nVersion = 1;

	pixelDesc.dwFlags = PFD_DRAW_TO_WINDOW | 
						PFD_SUPPORT_OPENGL |
						PFD_DOUBLEBUFFER;
					//	PFD_STEREO_DONTCARE;
pixelDesc.iPixelType = PFD_TYPE_RGBA;
pixelDesc.cColorBits = 32;
pixelDesc.cRedBits = 8;
pixelDesc.cRedShift = 16;
pixelDesc.cGreenBits = 8;
pixelDesc.cGreenShift = 8;
pixelDesc.cBlueBits = 8;
pixelDesc.cBlueShift = 0;
pixelDesc.cAlphaBits = 0;
pixelDesc.cAlphaShift = 0;
pixelDesc.cAccumBits = 64;
pixelDesc.cAccumRedBits = 16;
pixelDesc.cAccumGreenBits = 16;
pixelDesc.cAccumBlueBits = 16;
pixelDesc.cAccumAlphaBits = 0;
pixelDesc.cDepthBits = 32;
pixelDesc.cStencilBits = 8;
pixelDesc.cAuxBuffers = 0;
pixelDesc.iLayerType = PFD_MAIN_PLANE;
pixelDesc.bReserved = 0;
pixelDesc.dwLayerMask = 0;
pixelDesc.dwVisibleMask = 0;
pixelDesc.dwDamageMask = 0;


	pixelFormat = ChoosePixelFormat(hDC,&pixelDesc);
	if(pixelFormat==0) // Choose default
	{
		exit(150);
		pixelFormat = 1;
		if(DescribePixelFormat(hDC,pixelFormat,
			sizeof(PIXELFORMATDESCRIPTOR),&pixelDesc)==0)
		{
			return false;
		}
	}

	if( SetPixelFormat(hDC,pixelFormat,&pixelDesc) == false )
	{
		return false;
	}

	return true;

}

BOOL COpenGLDlg::CreateViewGLContext(HDC hDC)
{
	hRC = wglCreateContext(hDC);
	if(hRC==NULL)
		return false;
	if(wglMakeCurrent(hDC,hRC)==FALSE)
		return false;
	return true;	
}