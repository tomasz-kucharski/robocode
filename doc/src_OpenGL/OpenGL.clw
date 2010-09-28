; CLW file contains information for the MFC ClassWizard

[General Info]
Version=1
LastClass=CController
LastTemplate=CDialog
NewFileInclude1=#include "stdafx.h"
NewFileInclude2=#include "OpenGL.h"

ClassCount=4
Class1=COpenGLApp
Class2=COpenGLDlg
Class3=CAboutDlg

ResourceCount=6
Resource1=IDD_ABOUTBOX
Resource2=IDR_MAINFRAME
Resource3=IDD_OPENGL_DIALOG
Resource4=IDD_CONTROLLER
Resource5=IDD_ABOUTBOX (English (U.S.))
Class4=CController
Resource6=IDD_OPENGL_DIALOG (English (U.S.))

[CLS:COpenGLApp]
Type=0
HeaderFile=OpenGL.h
ImplementationFile=OpenGL.cpp
Filter=N

[CLS:COpenGLDlg]
Type=0
HeaderFile=OpenGLDlg.h
ImplementationFile=OpenGLDlg.cpp
Filter=D
BaseClass=CDialog
VirtualFilter=dWC
LastObject=COpenGLDlg

[CLS:CAboutDlg]
Type=0
HeaderFile=OpenGLDlg.h
ImplementationFile=OpenGLDlg.cpp
Filter=D

[DLG:IDD_ABOUTBOX]
Type=1
ControlCount=4
Control1=IDC_STATIC,static,1342177283
Control2=IDC_STATIC,static,1342308352
Control3=IDC_STATIC,static,1342308352
Control4=IDOK,button,1342373889
Class=CAboutDlg


[DLG:IDD_OPENGL_DIALOG]
Type=1
ControlCount=3
Control1=IDOK,button,1342242817
Control2=IDCANCEL,button,1342242816
Control3=IDC_STATIC,static,1342308352
Class=COpenGLDlg

[DLG:IDD_OPENGL_DIALOG (English (U.S.))]
Type=1
Class=COpenGLDlg
ControlCount=0

[DLG:IDD_ABOUTBOX (English (U.S.))]
Type=1
Class=CAboutDlg
ControlCount=4
Control1=IDC_STATIC,static,1342177283
Control2=IDC_STATIC,static,1342308480
Control3=IDC_STATIC,static,1342308352
Control4=IDOK,button,1342373889

[DLG:IDD_CONTROLLER]
Type=1
Class=CController
ControlCount=12
Control1=IDC_SCALE,msctls_trackbar32,1342242840
Control2=IDC_ANTIALIASING,button,1342242819
Control3=IDC_WIREFRAME,button,1342242819
Control4=IDC_GO,button,1342242819
Control5=IDC_STATIC,static,1342308353
Control6=IDC_ROBOTVIEW,button,1342242819
Control7=IDC_STATIC,button,1342177287
Control8=IDC_BUTTONUP,button,1342242816
Control9=IDC_BUTTONRIGHT,button,1342242816
Control10=IDC_BUTTONDOWN,button,1342242816
Control11=IDC_BUTTONLEFT,button,1342242816
Control12=IDC_FPS,static,1342177793

[CLS:CController]
Type=0
HeaderFile=Controller.h
ImplementationFile=Controller.cpp
BaseClass=CDialog
Filter=D
VirtualFilter=dWC
LastObject=IDC_FPS

