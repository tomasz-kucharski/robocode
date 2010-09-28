# Microsoft Developer Studio Project File - Name="OpenGL" - Package Owner=<4>
# Microsoft Developer Studio Generated Build File, Format Version 6.00
# ** DO NOT EDIT **

# TARGTYPE "Win32 (x86) Application" 0x0101

CFG=OpenGL - Win32 Debug
!MESSAGE This is not a valid makefile. To build this project using NMAKE,
!MESSAGE use the Export Makefile command and run
!MESSAGE 
!MESSAGE NMAKE /f "OpenGL.mak".
!MESSAGE 
!MESSAGE You can specify a configuration when running NMAKE
!MESSAGE by defining the macro CFG on the command line. For example:
!MESSAGE 
!MESSAGE NMAKE /f "OpenGL.mak" CFG="OpenGL - Win32 Debug"
!MESSAGE 
!MESSAGE Possible choices for configuration are:
!MESSAGE 
!MESSAGE "OpenGL - Win32 Release" (based on "Win32 (x86) Application")
!MESSAGE "OpenGL - Win32 Debug" (based on "Win32 (x86) Application")
!MESSAGE 

# Begin Project
# PROP AllowPerConfigDependencies 0
# PROP Scc_ProjName ""
# PROP Scc_LocalPath ""
CPP=cl.exe
MTL=midl.exe
RSC=rc.exe

!IF  "$(CFG)" == "OpenGL - Win32 Release"

# PROP BASE Use_MFC 6
# PROP BASE Use_Debug_Libraries 0
# PROP BASE Output_Dir "Release"
# PROP BASE Intermediate_Dir "Release"
# PROP BASE Target_Dir ""
# PROP Use_MFC 6
# PROP Use_Debug_Libraries 0
# PROP Output_Dir "Release"
# PROP Intermediate_Dir "Release"
# PROP Target_Dir ""
# ADD BASE CPP /nologo /MD /W3 /GX /O2 /D "WIN32" /D "NDEBUG" /D "_WINDOWS" /D "_AFXDLL" /Yu"stdafx.h" /FD /c
# ADD CPP /nologo /MD /W3 /GX /O2 /D "WIN32" /D "NDEBUG" /D "_WINDOWS" /D "_AFXDLL" /D "_MBCS" /Yu"stdafx.h" /FD /c
# ADD BASE MTL /nologo /D "NDEBUG" /mktyplib203 /win32
# ADD MTL /nologo /D "NDEBUG" /mktyplib203 /win32
# ADD BASE RSC /l 0x415 /d "NDEBUG" /d "_AFXDLL"
# ADD RSC /l 0x415 /d "NDEBUG" /d "_AFXDLL"
BSC32=bscmake.exe
# ADD BASE BSC32 /nologo
# ADD BSC32 /nologo
LINK32=link.exe
# ADD BASE LINK32 /nologo /subsystem:windows /machine:I386
# ADD LINK32 /nologo /subsystem:windows /machine:I386

!ELSEIF  "$(CFG)" == "OpenGL - Win32 Debug"

# PROP BASE Use_MFC 6
# PROP BASE Use_Debug_Libraries 1
# PROP BASE Output_Dir "Debug"
# PROP BASE Intermediate_Dir "Debug"
# PROP BASE Target_Dir ""
# PROP Use_MFC 6
# PROP Use_Debug_Libraries 1
# PROP Output_Dir "Debug"
# PROP Intermediate_Dir "Debug"
# PROP Ignore_Export_Lib 0
# PROP Target_Dir ""
# ADD BASE CPP /nologo /MDd /W3 /Gm /GX /ZI /Od /D "WIN32" /D "_DEBUG" /D "_WINDOWS" /D "_AFXDLL" /Yu"stdafx.h" /FD /GZ /c
# ADD CPP /nologo /MDd /W3 /Gm /GX /ZI /Od /D "WIN32" /D "_DEBUG" /D "_WINDOWS" /D "_AFXDLL" /D "_MBCS" /FR /Yu"stdafx.h" /FD /GZ /Zm250 /c
# ADD BASE MTL /nologo /D "_DEBUG" /mktyplib203 /win32
# ADD MTL /nologo /D "_DEBUG" /mktyplib203 /win32
# ADD BASE RSC /l 0x415 /d "_DEBUG" /d "_AFXDLL"
# ADD RSC /l 0x415 /d "_DEBUG" /d "_AFXDLL"
BSC32=bscmake.exe
# ADD BASE BSC32 /nologo
# ADD BSC32 /nologo
LINK32=link.exe
# ADD BASE LINK32 /nologo /subsystem:windows /debug /machine:I386 /pdbtype:sept
# ADD LINK32 OpenGL32.lib GLu32.lib GLaux.lib /nologo /subsystem:windows /debug /machine:I386 /pdbtype:sept

!ENDIF 

# Begin Target

# Name "OpenGL - Win32 Release"
# Name "OpenGL - Win32 Debug"
# Begin Group "Source Files"

# PROP Default_Filter "cpp;c;cxx;rc;def;r;odl;idl;hpj;bat"
# Begin Source File

SOURCE=.\Controller.cpp
# End Source File
# Begin Source File

SOURCE=.\DeployWorld.cpp
# End Source File
# Begin Source File

SOURCE=.\Depot.cpp
# End Source File
# Begin Source File

SOURCE=.\DepotGL.cpp
# End Source File
# Begin Source File

SOURCE=.\Direction.cpp
# End Source File
# Begin Source File

SOURCE=.\Eter.cpp
# End Source File
# Begin Source File

SOURCE=.\Floor.cpp
# End Source File
# Begin Source File

SOURCE=.\FloorGL.cpp
# End Source File
# Begin Source File

SOURCE=.\Furniture.cpp
# End Source File
# Begin Source File

SOURCE=.\FurnitureGL.cpp
# End Source File
# Begin Source File

SOURCE=.\Instruction.cpp
# End Source File
# Begin Source File

SOURCE=.\IntelligentObject.cpp
# End Source File
# Begin Source File

SOURCE=.\LightGL.cpp
# End Source File
# Begin Source File

SOURCE=.\List.cpp
# End Source File
# Begin Source File

SOURCE=.\ListNode.cpp
# End Source File
# Begin Source File

SOURCE=.\Message.cpp
# End Source File
# Begin Source File

SOURCE=.\Object.cpp
# End Source File
# Begin Source File

SOURCE=.\ObjectGL.cpp
# End Source File
# Begin Source File

SOURCE=.\OpenGL.cpp
# End Source File
# Begin Source File

SOURCE=.\OpenGL.rc
# End Source File
# Begin Source File

SOURCE=.\OpenGLDlg.cpp
# End Source File
# Begin Source File

SOURCE=.\PathObject.cpp
# End Source File
# Begin Source File

SOURCE=.\Position.cpp
# End Source File
# Begin Source File

SOURCE=.\ProgramList.cpp
# End Source File
# Begin Source File

SOURCE=.\Robot.cpp
# End Source File
# Begin Source File

SOURCE=.\RobotBattery.cpp
# End Source File
# Begin Source File

SOURCE=.\RobotGL.cpp
# End Source File
# Begin Source File

SOURCE=.\RobotMemory.cpp
# End Source File
# Begin Source File

SOURCE=.\RobotPath.cpp
# End Source File
# Begin Source File

SOURCE=.\RobotProcessor.cpp
# End Source File
# Begin Source File

SOURCE=.\RobotProgramLoader.cpp
# End Source File
# Begin Source File

SOURCE=.\RobotScaner.cpp
# End Source File
# Begin Source File

SOURCE=.\Rubbish.cpp
# End Source File
# Begin Source File

SOURCE=.\RubbishGL.cpp
# End Source File
# Begin Source File

SOURCE=.\Stack.cpp
# End Source File
# Begin Source File

SOURCE=.\StackObject.cpp
# End Source File
# Begin Source File

SOURCE=.\StdAfx.cpp
# ADD CPP /Yc"stdafx.h"
# End Source File
# Begin Source File

SOURCE=.\TableGL.cpp
# End Source File
# Begin Source File

SOURCE=.\Wall.cpp
# End Source File
# Begin Source File

SOURCE=.\WallGL.cpp
# End Source File
# Begin Source File

SOURCE=.\World.cpp
# End Source File
# Begin Source File

SOURCE=.\WorldGL.cpp
# End Source File
# Begin Source File

SOURCE=.\WorldObject.cpp
# End Source File
# Begin Source File

SOURCE=.\WorldObjectList.cpp
# End Source File
# Begin Source File

SOURCE=.\WorldObjectVerifier.cpp
# End Source File
# Begin Source File

SOURCE=.\WorldService.cpp
# End Source File
# End Group
# Begin Group "Header Files"

# PROP Default_Filter "h;hpp;hxx;hm;inl"
# Begin Source File

SOURCE=.\Controller.h
# End Source File
# Begin Source File

SOURCE=.\DeployWorld.h
# End Source File
# Begin Source File

SOURCE=.\Depot.h
# End Source File
# Begin Source File

SOURCE=.\DepotGL.h
# End Source File
# Begin Source File

SOURCE=.\Direction.h
# End Source File
# Begin Source File

SOURCE=.\Eter.h
# End Source File
# Begin Source File

SOURCE=.\Floor.h
# End Source File
# Begin Source File

SOURCE=.\FloorGL.h
# End Source File
# Begin Source File

SOURCE=.\Furniture.h
# End Source File
# Begin Source File

SOURCE=.\FurnitureGL.h
# End Source File
# Begin Source File

SOURCE=.\Inkludy.h
# End Source File
# Begin Source File

SOURCE=.\Instruction.h
# End Source File
# Begin Source File

SOURCE=.\IntelligentObject.h
# End Source File
# Begin Source File

SOURCE=.\LightGL.h
# End Source File
# Begin Source File

SOURCE=.\List.h
# End Source File
# Begin Source File

SOURCE=.\ListNode.h
# End Source File
# Begin Source File

SOURCE=.\Message.h
# End Source File
# Begin Source File

SOURCE=.\Object.h
# End Source File
# Begin Source File

SOURCE=.\ObjectGL.h
# End Source File
# Begin Source File

SOURCE=.\OpenGL.h
# End Source File
# Begin Source File

SOURCE=.\OpenGLDlg.h
# End Source File
# Begin Source File

SOURCE=.\PathObject.h
# End Source File
# Begin Source File

SOURCE=.\Position.h
# End Source File
# Begin Source File

SOURCE=.\ProgramList.h
# End Source File
# Begin Source File

SOURCE=.\Resource.h
# End Source File
# Begin Source File

SOURCE=.\Robot.h
# End Source File
# Begin Source File

SOURCE=.\RobotBattery.h
# End Source File
# Begin Source File

SOURCE=.\RobotGL.h
# End Source File
# Begin Source File

SOURCE=.\RobotMemory.h
# End Source File
# Begin Source File

SOURCE=.\RobotPath.h
# End Source File
# Begin Source File

SOURCE=.\RobotProcessor.h
# End Source File
# Begin Source File

SOURCE=.\RobotProgramLoader.h
# End Source File
# Begin Source File

SOURCE=.\RobotScaner.h
# End Source File
# Begin Source File

SOURCE=.\Rubbish.h
# End Source File
# Begin Source File

SOURCE=.\RubbishGL.h
# End Source File
# Begin Source File

SOURCE=.\Stack.h
# End Source File
# Begin Source File

SOURCE=.\StackObject.h
# End Source File
# Begin Source File

SOURCE=.\StdAfx.h
# End Source File
# Begin Source File

SOURCE=.\TableGL.h
# End Source File
# Begin Source File

SOURCE=.\Wall.h
# End Source File
# Begin Source File

SOURCE=.\WallGL.h
# End Source File
# Begin Source File

SOURCE=.\World.h
# End Source File
# Begin Source File

SOURCE=.\WorldGL.h
# End Source File
# Begin Source File

SOURCE=.\WorldObject.h
# End Source File
# Begin Source File

SOURCE=.\WorldObjectList.h
# End Source File
# Begin Source File

SOURCE=.\WorldObjectVerifier.h
# End Source File
# Begin Source File

SOURCE=.\WorldService.h
# End Source File
# End Group
# Begin Group "Resource Files"

# PROP Default_Filter "ico;cur;bmp;dlg;rc2;rct;bin;rgs;gif;jpg;jpeg;jpe"
# Begin Source File

SOURCE=.\res\OpenGL.ico
# End Source File
# Begin Source File

SOURCE=.\res\OpenGL.rc2
# End Source File
# End Group
# Begin Source File

SOURCE=.\ReadMe.txt
# End Source File
# End Target
# End Project
