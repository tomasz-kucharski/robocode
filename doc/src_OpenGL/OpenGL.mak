# Microsoft Developer Studio Generated NMAKE File, Based on OpenGL.dsp
!IF "$(CFG)" == ""
CFG=OpenGL - Win32 Debug
!MESSAGE No configuration specified. Defaulting to OpenGL - Win32 Debug.
!ENDIF 

!IF "$(CFG)" != "OpenGL - Win32 Release" && "$(CFG)" != "OpenGL - Win32 Debug"
!MESSAGE Invalid configuration "$(CFG)" specified.
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
!ERROR An invalid configuration is specified.
!ENDIF 

!IF "$(OS)" == "Windows_NT"
NULL=
!ELSE 
NULL=nul
!ENDIF 

!IF  "$(CFG)" == "OpenGL - Win32 Release"

OUTDIR=.\Release
INTDIR=.\Release
# Begin Custom Macros
OutDir=.\Release
# End Custom Macros

ALL : "$(OUTDIR)\OpenGL.exe"


CLEAN :
	-@erase "$(INTDIR)\Controller.obj"
	-@erase "$(INTDIR)\DeployWorld.obj"
	-@erase "$(INTDIR)\Depot.obj"
	-@erase "$(INTDIR)\DepotGL.obj"
	-@erase "$(INTDIR)\Direction.obj"
	-@erase "$(INTDIR)\Eter.obj"
	-@erase "$(INTDIR)\Floor.obj"
	-@erase "$(INTDIR)\FloorGL.obj"
	-@erase "$(INTDIR)\Furniture.obj"
	-@erase "$(INTDIR)\FurnitureGL.obj"
	-@erase "$(INTDIR)\Instruction.obj"
	-@erase "$(INTDIR)\IntelligentObject.obj"
	-@erase "$(INTDIR)\LightGL.obj"
	-@erase "$(INTDIR)\List.obj"
	-@erase "$(INTDIR)\ListNode.obj"
	-@erase "$(INTDIR)\Message.obj"
	-@erase "$(INTDIR)\Object.obj"
	-@erase "$(INTDIR)\ObjectGL.obj"
	-@erase "$(INTDIR)\OpenGL.obj"
	-@erase "$(INTDIR)\OpenGL.pch"
	-@erase "$(INTDIR)\OpenGL.res"
	-@erase "$(INTDIR)\OpenGLDlg.obj"
	-@erase "$(INTDIR)\PathObject.obj"
	-@erase "$(INTDIR)\Position.obj"
	-@erase "$(INTDIR)\ProgramList.obj"
	-@erase "$(INTDIR)\Robot.obj"
	-@erase "$(INTDIR)\RobotBattery.obj"
	-@erase "$(INTDIR)\RobotGL.obj"
	-@erase "$(INTDIR)\RobotMemory.obj"
	-@erase "$(INTDIR)\RobotPath.obj"
	-@erase "$(INTDIR)\RobotProcessor.obj"
	-@erase "$(INTDIR)\RobotProgramLoader.obj"
	-@erase "$(INTDIR)\RobotScaner.obj"
	-@erase "$(INTDIR)\Rubbish.obj"
	-@erase "$(INTDIR)\RubbishGL.obj"
	-@erase "$(INTDIR)\Stack.obj"
	-@erase "$(INTDIR)\StackObject.obj"
	-@erase "$(INTDIR)\StdAfx.obj"
	-@erase "$(INTDIR)\TableGL.obj"
	-@erase "$(INTDIR)\vc60.idb"
	-@erase "$(INTDIR)\Wall.obj"
	-@erase "$(INTDIR)\WallGL.obj"
	-@erase "$(INTDIR)\World.obj"
	-@erase "$(INTDIR)\WorldGL.obj"
	-@erase "$(INTDIR)\WorldObject.obj"
	-@erase "$(INTDIR)\WorldObjectList.obj"
	-@erase "$(INTDIR)\WorldObjectVerifier.obj"
	-@erase "$(INTDIR)\WorldService.obj"
	-@erase "$(OUTDIR)\OpenGL.exe"

"$(OUTDIR)" :
    if not exist "$(OUTDIR)/$(NULL)" mkdir "$(OUTDIR)"

CPP=cl.exe
CPP_PROJ=/nologo /MD /W3 /GX /O2 /D "WIN32" /D "NDEBUG" /D "_WINDOWS" /D "_AFXDLL" /D "_MBCS" /Fp"$(INTDIR)\OpenGL.pch" /Yu"stdafx.h" /Fo"$(INTDIR)\\" /Fd"$(INTDIR)\\" /FD /c 

.c{$(INTDIR)}.obj::
   $(CPP) @<<
   $(CPP_PROJ) $< 
<<

.cpp{$(INTDIR)}.obj::
   $(CPP) @<<
   $(CPP_PROJ) $< 
<<

.cxx{$(INTDIR)}.obj::
   $(CPP) @<<
   $(CPP_PROJ) $< 
<<

.c{$(INTDIR)}.sbr::
   $(CPP) @<<
   $(CPP_PROJ) $< 
<<

.cpp{$(INTDIR)}.sbr::
   $(CPP) @<<
   $(CPP_PROJ) $< 
<<

.cxx{$(INTDIR)}.sbr::
   $(CPP) @<<
   $(CPP_PROJ) $< 
<<

MTL=midl.exe
MTL_PROJ=/nologo /D "NDEBUG" /mktyplib203 /win32 
RSC=rc.exe
RSC_PROJ=/l 0x415 /fo"$(INTDIR)\OpenGL.res" /d "NDEBUG" /d "_AFXDLL" 
BSC32=bscmake.exe
BSC32_FLAGS=/nologo /o"$(OUTDIR)\OpenGL.bsc" 
BSC32_SBRS= \
	
LINK32=link.exe
LINK32_FLAGS=/nologo /subsystem:windows /incremental:no /pdb:"$(OUTDIR)\OpenGL.pdb" /machine:I386 /out:"$(OUTDIR)\OpenGL.exe" 
LINK32_OBJS= \
	"$(INTDIR)\Controller.obj" \
	"$(INTDIR)\DeployWorld.obj" \
	"$(INTDIR)\Depot.obj" \
	"$(INTDIR)\DepotGL.obj" \
	"$(INTDIR)\Direction.obj" \
	"$(INTDIR)\Eter.obj" \
	"$(INTDIR)\Floor.obj" \
	"$(INTDIR)\FloorGL.obj" \
	"$(INTDIR)\Furniture.obj" \
	"$(INTDIR)\FurnitureGL.obj" \
	"$(INTDIR)\Instruction.obj" \
	"$(INTDIR)\IntelligentObject.obj" \
	"$(INTDIR)\LightGL.obj" \
	"$(INTDIR)\List.obj" \
	"$(INTDIR)\ListNode.obj" \
	"$(INTDIR)\Message.obj" \
	"$(INTDIR)\Object.obj" \
	"$(INTDIR)\ObjectGL.obj" \
	"$(INTDIR)\OpenGL.obj" \
	"$(INTDIR)\OpenGLDlg.obj" \
	"$(INTDIR)\PathObject.obj" \
	"$(INTDIR)\Position.obj" \
	"$(INTDIR)\ProgramList.obj" \
	"$(INTDIR)\Robot.obj" \
	"$(INTDIR)\RobotBattery.obj" \
	"$(INTDIR)\RobotGL.obj" \
	"$(INTDIR)\RobotMemory.obj" \
	"$(INTDIR)\RobotPath.obj" \
	"$(INTDIR)\RobotProcessor.obj" \
	"$(INTDIR)\RobotProgramLoader.obj" \
	"$(INTDIR)\RobotScaner.obj" \
	"$(INTDIR)\Rubbish.obj" \
	"$(INTDIR)\RubbishGL.obj" \
	"$(INTDIR)\Stack.obj" \
	"$(INTDIR)\StackObject.obj" \
	"$(INTDIR)\StdAfx.obj" \
	"$(INTDIR)\TableGL.obj" \
	"$(INTDIR)\Wall.obj" \
	"$(INTDIR)\WallGL.obj" \
	"$(INTDIR)\World.obj" \
	"$(INTDIR)\WorldGL.obj" \
	"$(INTDIR)\WorldObject.obj" \
	"$(INTDIR)\WorldObjectList.obj" \
	"$(INTDIR)\WorldObjectVerifier.obj" \
	"$(INTDIR)\WorldService.obj" \
	"$(INTDIR)\OpenGL.res"

"$(OUTDIR)\OpenGL.exe" : "$(OUTDIR)" $(DEF_FILE) $(LINK32_OBJS)
    $(LINK32) @<<
  $(LINK32_FLAGS) $(LINK32_OBJS)
<<

!ELSEIF  "$(CFG)" == "OpenGL - Win32 Debug"

OUTDIR=.\Debug
INTDIR=.\Debug
# Begin Custom Macros
OutDir=.\Debug
# End Custom Macros

ALL : "$(OUTDIR)\OpenGL.exe" "$(OUTDIR)\OpenGL.bsc"


CLEAN :
	-@erase "$(INTDIR)\Controller.obj"
	-@erase "$(INTDIR)\Controller.sbr"
	-@erase "$(INTDIR)\DeployWorld.obj"
	-@erase "$(INTDIR)\DeployWorld.sbr"
	-@erase "$(INTDIR)\Depot.obj"
	-@erase "$(INTDIR)\Depot.sbr"
	-@erase "$(INTDIR)\DepotGL.obj"
	-@erase "$(INTDIR)\DepotGL.sbr"
	-@erase "$(INTDIR)\Direction.obj"
	-@erase "$(INTDIR)\Direction.sbr"
	-@erase "$(INTDIR)\Eter.obj"
	-@erase "$(INTDIR)\Eter.sbr"
	-@erase "$(INTDIR)\Floor.obj"
	-@erase "$(INTDIR)\Floor.sbr"
	-@erase "$(INTDIR)\FloorGL.obj"
	-@erase "$(INTDIR)\FloorGL.sbr"
	-@erase "$(INTDIR)\Furniture.obj"
	-@erase "$(INTDIR)\Furniture.sbr"
	-@erase "$(INTDIR)\FurnitureGL.obj"
	-@erase "$(INTDIR)\FurnitureGL.sbr"
	-@erase "$(INTDIR)\Instruction.obj"
	-@erase "$(INTDIR)\Instruction.sbr"
	-@erase "$(INTDIR)\IntelligentObject.obj"
	-@erase "$(INTDIR)\IntelligentObject.sbr"
	-@erase "$(INTDIR)\LightGL.obj"
	-@erase "$(INTDIR)\LightGL.sbr"
	-@erase "$(INTDIR)\List.obj"
	-@erase "$(INTDIR)\List.sbr"
	-@erase "$(INTDIR)\ListNode.obj"
	-@erase "$(INTDIR)\ListNode.sbr"
	-@erase "$(INTDIR)\Message.obj"
	-@erase "$(INTDIR)\Message.sbr"
	-@erase "$(INTDIR)\Object.obj"
	-@erase "$(INTDIR)\Object.sbr"
	-@erase "$(INTDIR)\ObjectGL.obj"
	-@erase "$(INTDIR)\ObjectGL.sbr"
	-@erase "$(INTDIR)\OpenGL.obj"
	-@erase "$(INTDIR)\OpenGL.pch"
	-@erase "$(INTDIR)\OpenGL.res"
	-@erase "$(INTDIR)\OpenGL.sbr"
	-@erase "$(INTDIR)\OpenGLDlg.obj"
	-@erase "$(INTDIR)\OpenGLDlg.sbr"
	-@erase "$(INTDIR)\PathObject.obj"
	-@erase "$(INTDIR)\PathObject.sbr"
	-@erase "$(INTDIR)\Position.obj"
	-@erase "$(INTDIR)\Position.sbr"
	-@erase "$(INTDIR)\ProgramList.obj"
	-@erase "$(INTDIR)\ProgramList.sbr"
	-@erase "$(INTDIR)\Robot.obj"
	-@erase "$(INTDIR)\Robot.sbr"
	-@erase "$(INTDIR)\RobotBattery.obj"
	-@erase "$(INTDIR)\RobotBattery.sbr"
	-@erase "$(INTDIR)\RobotGL.obj"
	-@erase "$(INTDIR)\RobotGL.sbr"
	-@erase "$(INTDIR)\RobotMemory.obj"
	-@erase "$(INTDIR)\RobotMemory.sbr"
	-@erase "$(INTDIR)\RobotPath.obj"
	-@erase "$(INTDIR)\RobotPath.sbr"
	-@erase "$(INTDIR)\RobotProcessor.obj"
	-@erase "$(INTDIR)\RobotProcessor.sbr"
	-@erase "$(INTDIR)\RobotProgramLoader.obj"
	-@erase "$(INTDIR)\RobotProgramLoader.sbr"
	-@erase "$(INTDIR)\RobotScaner.obj"
	-@erase "$(INTDIR)\RobotScaner.sbr"
	-@erase "$(INTDIR)\Rubbish.obj"
	-@erase "$(INTDIR)\Rubbish.sbr"
	-@erase "$(INTDIR)\RubbishGL.obj"
	-@erase "$(INTDIR)\RubbishGL.sbr"
	-@erase "$(INTDIR)\Stack.obj"
	-@erase "$(INTDIR)\Stack.sbr"
	-@erase "$(INTDIR)\StackObject.obj"
	-@erase "$(INTDIR)\StackObject.sbr"
	-@erase "$(INTDIR)\StdAfx.obj"
	-@erase "$(INTDIR)\StdAfx.sbr"
	-@erase "$(INTDIR)\TableGL.obj"
	-@erase "$(INTDIR)\TableGL.sbr"
	-@erase "$(INTDIR)\vc60.idb"
	-@erase "$(INTDIR)\vc60.pdb"
	-@erase "$(INTDIR)\Wall.obj"
	-@erase "$(INTDIR)\Wall.sbr"
	-@erase "$(INTDIR)\WallGL.obj"
	-@erase "$(INTDIR)\WallGL.sbr"
	-@erase "$(INTDIR)\World.obj"
	-@erase "$(INTDIR)\World.sbr"
	-@erase "$(INTDIR)\WorldGL.obj"
	-@erase "$(INTDIR)\WorldGL.sbr"
	-@erase "$(INTDIR)\WorldObject.obj"
	-@erase "$(INTDIR)\WorldObject.sbr"
	-@erase "$(INTDIR)\WorldObjectList.obj"
	-@erase "$(INTDIR)\WorldObjectList.sbr"
	-@erase "$(INTDIR)\WorldObjectVerifier.obj"
	-@erase "$(INTDIR)\WorldObjectVerifier.sbr"
	-@erase "$(INTDIR)\WorldService.obj"
	-@erase "$(INTDIR)\WorldService.sbr"
	-@erase "$(OUTDIR)\OpenGL.bsc"
	-@erase "$(OUTDIR)\OpenGL.exe"
	-@erase "$(OUTDIR)\OpenGL.ilk"
	-@erase "$(OUTDIR)\OpenGL.pdb"

"$(OUTDIR)" :
    if not exist "$(OUTDIR)/$(NULL)" mkdir "$(OUTDIR)"

CPP=cl.exe
CPP_PROJ=/nologo /MDd /W3 /Gm /GX /ZI /Od /D "WIN32" /D "_DEBUG" /D "_WINDOWS" /D "_AFXDLL" /D "_MBCS" /FR"$(INTDIR)\\" /Fp"$(INTDIR)\OpenGL.pch" /Yu"stdafx.h" /Fo"$(INTDIR)\\" /Fd"$(INTDIR)\\" /FD /GZ /Zm250 /c 

.c{$(INTDIR)}.obj::
   $(CPP) @<<
   $(CPP_PROJ) $< 
<<

.cpp{$(INTDIR)}.obj::
   $(CPP) @<<
   $(CPP_PROJ) $< 
<<

.cxx{$(INTDIR)}.obj::
   $(CPP) @<<
   $(CPP_PROJ) $< 
<<

.c{$(INTDIR)}.sbr::
   $(CPP) @<<
   $(CPP_PROJ) $< 
<<

.cpp{$(INTDIR)}.sbr::
   $(CPP) @<<
   $(CPP_PROJ) $< 
<<

.cxx{$(INTDIR)}.sbr::
   $(CPP) @<<
   $(CPP_PROJ) $< 
<<

MTL=midl.exe
MTL_PROJ=/nologo /D "_DEBUG" /mktyplib203 /win32 
RSC=rc.exe
RSC_PROJ=/l 0x415 /fo"$(INTDIR)\OpenGL.res" /d "_DEBUG" /d "_AFXDLL" 
BSC32=bscmake.exe
BSC32_FLAGS=/nologo /o"$(OUTDIR)\OpenGL.bsc" 
BSC32_SBRS= \
	"$(INTDIR)\Controller.sbr" \
	"$(INTDIR)\DeployWorld.sbr" \
	"$(INTDIR)\Depot.sbr" \
	"$(INTDIR)\DepotGL.sbr" \
	"$(INTDIR)\Direction.sbr" \
	"$(INTDIR)\Eter.sbr" \
	"$(INTDIR)\Floor.sbr" \
	"$(INTDIR)\FloorGL.sbr" \
	"$(INTDIR)\Furniture.sbr" \
	"$(INTDIR)\FurnitureGL.sbr" \
	"$(INTDIR)\Instruction.sbr" \
	"$(INTDIR)\IntelligentObject.sbr" \
	"$(INTDIR)\LightGL.sbr" \
	"$(INTDIR)\List.sbr" \
	"$(INTDIR)\ListNode.sbr" \
	"$(INTDIR)\Message.sbr" \
	"$(INTDIR)\Object.sbr" \
	"$(INTDIR)\ObjectGL.sbr" \
	"$(INTDIR)\OpenGL.sbr" \
	"$(INTDIR)\OpenGLDlg.sbr" \
	"$(INTDIR)\PathObject.sbr" \
	"$(INTDIR)\Position.sbr" \
	"$(INTDIR)\ProgramList.sbr" \
	"$(INTDIR)\Robot.sbr" \
	"$(INTDIR)\RobotBattery.sbr" \
	"$(INTDIR)\RobotGL.sbr" \
	"$(INTDIR)\RobotMemory.sbr" \
	"$(INTDIR)\RobotPath.sbr" \
	"$(INTDIR)\RobotProcessor.sbr" \
	"$(INTDIR)\RobotProgramLoader.sbr" \
	"$(INTDIR)\RobotScaner.sbr" \
	"$(INTDIR)\Rubbish.sbr" \
	"$(INTDIR)\RubbishGL.sbr" \
	"$(INTDIR)\Stack.sbr" \
	"$(INTDIR)\StackObject.sbr" \
	"$(INTDIR)\StdAfx.sbr" \
	"$(INTDIR)\TableGL.sbr" \
	"$(INTDIR)\Wall.sbr" \
	"$(INTDIR)\WallGL.sbr" \
	"$(INTDIR)\World.sbr" \
	"$(INTDIR)\WorldGL.sbr" \
	"$(INTDIR)\WorldObject.sbr" \
	"$(INTDIR)\WorldObjectList.sbr" \
	"$(INTDIR)\WorldObjectVerifier.sbr" \
	"$(INTDIR)\WorldService.sbr"

"$(OUTDIR)\OpenGL.bsc" : "$(OUTDIR)" $(BSC32_SBRS)
    $(BSC32) @<<
  $(BSC32_FLAGS) $(BSC32_SBRS)
<<

LINK32=link.exe
LINK32_FLAGS=OpenGL32.lib GLu32.lib GLaux.lib /nologo /subsystem:windows /incremental:yes /pdb:"$(OUTDIR)\OpenGL.pdb" /debug /machine:I386 /out:"$(OUTDIR)\OpenGL.exe" /pdbtype:sept 
LINK32_OBJS= \
	"$(INTDIR)\Controller.obj" \
	"$(INTDIR)\DeployWorld.obj" \
	"$(INTDIR)\Depot.obj" \
	"$(INTDIR)\DepotGL.obj" \
	"$(INTDIR)\Direction.obj" \
	"$(INTDIR)\Eter.obj" \
	"$(INTDIR)\Floor.obj" \
	"$(INTDIR)\FloorGL.obj" \
	"$(INTDIR)\Furniture.obj" \
	"$(INTDIR)\FurnitureGL.obj" \
	"$(INTDIR)\Instruction.obj" \
	"$(INTDIR)\IntelligentObject.obj" \
	"$(INTDIR)\LightGL.obj" \
	"$(INTDIR)\List.obj" \
	"$(INTDIR)\ListNode.obj" \
	"$(INTDIR)\Message.obj" \
	"$(INTDIR)\Object.obj" \
	"$(INTDIR)\ObjectGL.obj" \
	"$(INTDIR)\OpenGL.obj" \
	"$(INTDIR)\OpenGLDlg.obj" \
	"$(INTDIR)\PathObject.obj" \
	"$(INTDIR)\Position.obj" \
	"$(INTDIR)\ProgramList.obj" \
	"$(INTDIR)\Robot.obj" \
	"$(INTDIR)\RobotBattery.obj" \
	"$(INTDIR)\RobotGL.obj" \
	"$(INTDIR)\RobotMemory.obj" \
	"$(INTDIR)\RobotPath.obj" \
	"$(INTDIR)\RobotProcessor.obj" \
	"$(INTDIR)\RobotProgramLoader.obj" \
	"$(INTDIR)\RobotScaner.obj" \
	"$(INTDIR)\Rubbish.obj" \
	"$(INTDIR)\RubbishGL.obj" \
	"$(INTDIR)\Stack.obj" \
	"$(INTDIR)\StackObject.obj" \
	"$(INTDIR)\StdAfx.obj" \
	"$(INTDIR)\TableGL.obj" \
	"$(INTDIR)\Wall.obj" \
	"$(INTDIR)\WallGL.obj" \
	"$(INTDIR)\World.obj" \
	"$(INTDIR)\WorldGL.obj" \
	"$(INTDIR)\WorldObject.obj" \
	"$(INTDIR)\WorldObjectList.obj" \
	"$(INTDIR)\WorldObjectVerifier.obj" \
	"$(INTDIR)\WorldService.obj" \
	"$(INTDIR)\OpenGL.res"

"$(OUTDIR)\OpenGL.exe" : "$(OUTDIR)" $(DEF_FILE) $(LINK32_OBJS)
    $(LINK32) @<<
  $(LINK32_FLAGS) $(LINK32_OBJS)
<<

!ENDIF 


!IF "$(NO_EXTERNAL_DEPS)" != "1"
!IF EXISTS("OpenGL.dep")
!INCLUDE "OpenGL.dep"
!ELSE 
!MESSAGE Warning: cannot find "OpenGL.dep"
!ENDIF 
!ENDIF 


!IF "$(CFG)" == "OpenGL - Win32 Release" || "$(CFG)" == "OpenGL - Win32 Debug"
SOURCE=.\Controller.cpp

!IF  "$(CFG)" == "OpenGL - Win32 Release"


"$(INTDIR)\Controller.obj" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ELSEIF  "$(CFG)" == "OpenGL - Win32 Debug"


"$(INTDIR)\Controller.obj"	"$(INTDIR)\Controller.sbr" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ENDIF 

SOURCE=.\DeployWorld.cpp

!IF  "$(CFG)" == "OpenGL - Win32 Release"


"$(INTDIR)\DeployWorld.obj" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ELSEIF  "$(CFG)" == "OpenGL - Win32 Debug"


"$(INTDIR)\DeployWorld.obj"	"$(INTDIR)\DeployWorld.sbr" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ENDIF 

SOURCE=.\Depot.cpp

!IF  "$(CFG)" == "OpenGL - Win32 Release"


"$(INTDIR)\Depot.obj" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ELSEIF  "$(CFG)" == "OpenGL - Win32 Debug"


"$(INTDIR)\Depot.obj"	"$(INTDIR)\Depot.sbr" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ENDIF 

SOURCE=.\DepotGL.cpp

!IF  "$(CFG)" == "OpenGL - Win32 Release"


"$(INTDIR)\DepotGL.obj" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ELSEIF  "$(CFG)" == "OpenGL - Win32 Debug"


"$(INTDIR)\DepotGL.obj"	"$(INTDIR)\DepotGL.sbr" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ENDIF 

SOURCE=.\Direction.cpp

!IF  "$(CFG)" == "OpenGL - Win32 Release"


"$(INTDIR)\Direction.obj" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ELSEIF  "$(CFG)" == "OpenGL - Win32 Debug"


"$(INTDIR)\Direction.obj"	"$(INTDIR)\Direction.sbr" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ENDIF 

SOURCE=.\Eter.cpp

!IF  "$(CFG)" == "OpenGL - Win32 Release"


"$(INTDIR)\Eter.obj" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ELSEIF  "$(CFG)" == "OpenGL - Win32 Debug"


"$(INTDIR)\Eter.obj"	"$(INTDIR)\Eter.sbr" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ENDIF 

SOURCE=.\Floor.cpp

!IF  "$(CFG)" == "OpenGL - Win32 Release"


"$(INTDIR)\Floor.obj" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ELSEIF  "$(CFG)" == "OpenGL - Win32 Debug"


"$(INTDIR)\Floor.obj"	"$(INTDIR)\Floor.sbr" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ENDIF 

SOURCE=.\FloorGL.cpp

!IF  "$(CFG)" == "OpenGL - Win32 Release"


"$(INTDIR)\FloorGL.obj" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ELSEIF  "$(CFG)" == "OpenGL - Win32 Debug"


"$(INTDIR)\FloorGL.obj"	"$(INTDIR)\FloorGL.sbr" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ENDIF 

SOURCE=.\Furniture.cpp

!IF  "$(CFG)" == "OpenGL - Win32 Release"


"$(INTDIR)\Furniture.obj" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ELSEIF  "$(CFG)" == "OpenGL - Win32 Debug"


"$(INTDIR)\Furniture.obj"	"$(INTDIR)\Furniture.sbr" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ENDIF 

SOURCE=.\FurnitureGL.cpp

!IF  "$(CFG)" == "OpenGL - Win32 Release"


"$(INTDIR)\FurnitureGL.obj" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ELSEIF  "$(CFG)" == "OpenGL - Win32 Debug"


"$(INTDIR)\FurnitureGL.obj"	"$(INTDIR)\FurnitureGL.sbr" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ENDIF 

SOURCE=.\Instruction.cpp

!IF  "$(CFG)" == "OpenGL - Win32 Release"


"$(INTDIR)\Instruction.obj" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ELSEIF  "$(CFG)" == "OpenGL - Win32 Debug"


"$(INTDIR)\Instruction.obj"	"$(INTDIR)\Instruction.sbr" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ENDIF 

SOURCE=.\IntelligentObject.cpp

!IF  "$(CFG)" == "OpenGL - Win32 Release"


"$(INTDIR)\IntelligentObject.obj" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ELSEIF  "$(CFG)" == "OpenGL - Win32 Debug"


"$(INTDIR)\IntelligentObject.obj"	"$(INTDIR)\IntelligentObject.sbr" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ENDIF 

SOURCE=.\LightGL.cpp

!IF  "$(CFG)" == "OpenGL - Win32 Release"


"$(INTDIR)\LightGL.obj" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ELSEIF  "$(CFG)" == "OpenGL - Win32 Debug"


"$(INTDIR)\LightGL.obj"	"$(INTDIR)\LightGL.sbr" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ENDIF 

SOURCE=.\List.cpp

!IF  "$(CFG)" == "OpenGL - Win32 Release"


"$(INTDIR)\List.obj" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ELSEIF  "$(CFG)" == "OpenGL - Win32 Debug"


"$(INTDIR)\List.obj"	"$(INTDIR)\List.sbr" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ENDIF 

SOURCE=.\ListNode.cpp

!IF  "$(CFG)" == "OpenGL - Win32 Release"


"$(INTDIR)\ListNode.obj" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ELSEIF  "$(CFG)" == "OpenGL - Win32 Debug"


"$(INTDIR)\ListNode.obj"	"$(INTDIR)\ListNode.sbr" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ENDIF 

SOURCE=.\Message.cpp

!IF  "$(CFG)" == "OpenGL - Win32 Release"


"$(INTDIR)\Message.obj" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ELSEIF  "$(CFG)" == "OpenGL - Win32 Debug"


"$(INTDIR)\Message.obj"	"$(INTDIR)\Message.sbr" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ENDIF 

SOURCE=.\Object.cpp

!IF  "$(CFG)" == "OpenGL - Win32 Release"


"$(INTDIR)\Object.obj" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ELSEIF  "$(CFG)" == "OpenGL - Win32 Debug"


"$(INTDIR)\Object.obj"	"$(INTDIR)\Object.sbr" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ENDIF 

SOURCE=.\ObjectGL.cpp

!IF  "$(CFG)" == "OpenGL - Win32 Release"


"$(INTDIR)\ObjectGL.obj" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ELSEIF  "$(CFG)" == "OpenGL - Win32 Debug"


"$(INTDIR)\ObjectGL.obj"	"$(INTDIR)\ObjectGL.sbr" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ENDIF 

SOURCE=.\OpenGL.cpp

!IF  "$(CFG)" == "OpenGL - Win32 Release"


"$(INTDIR)\OpenGL.obj" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ELSEIF  "$(CFG)" == "OpenGL - Win32 Debug"


"$(INTDIR)\OpenGL.obj"	"$(INTDIR)\OpenGL.sbr" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ENDIF 

SOURCE=.\OpenGL.rc

"$(INTDIR)\OpenGL.res" : $(SOURCE) "$(INTDIR)"
	$(RSC) $(RSC_PROJ) $(SOURCE)


SOURCE=.\OpenGLDlg.cpp

!IF  "$(CFG)" == "OpenGL - Win32 Release"


"$(INTDIR)\OpenGLDlg.obj" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ELSEIF  "$(CFG)" == "OpenGL - Win32 Debug"


"$(INTDIR)\OpenGLDlg.obj"	"$(INTDIR)\OpenGLDlg.sbr" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ENDIF 

SOURCE=.\PathObject.cpp

!IF  "$(CFG)" == "OpenGL - Win32 Release"


"$(INTDIR)\PathObject.obj" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ELSEIF  "$(CFG)" == "OpenGL - Win32 Debug"


"$(INTDIR)\PathObject.obj"	"$(INTDIR)\PathObject.sbr" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ENDIF 

SOURCE=.\Position.cpp

!IF  "$(CFG)" == "OpenGL - Win32 Release"


"$(INTDIR)\Position.obj" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ELSEIF  "$(CFG)" == "OpenGL - Win32 Debug"


"$(INTDIR)\Position.obj"	"$(INTDIR)\Position.sbr" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ENDIF 

SOURCE=.\ProgramList.cpp

!IF  "$(CFG)" == "OpenGL - Win32 Release"


"$(INTDIR)\ProgramList.obj" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ELSEIF  "$(CFG)" == "OpenGL - Win32 Debug"


"$(INTDIR)\ProgramList.obj"	"$(INTDIR)\ProgramList.sbr" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ENDIF 

SOURCE=.\Robot.cpp

!IF  "$(CFG)" == "OpenGL - Win32 Release"


"$(INTDIR)\Robot.obj" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ELSEIF  "$(CFG)" == "OpenGL - Win32 Debug"


"$(INTDIR)\Robot.obj"	"$(INTDIR)\Robot.sbr" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ENDIF 

SOURCE=.\RobotBattery.cpp

!IF  "$(CFG)" == "OpenGL - Win32 Release"


"$(INTDIR)\RobotBattery.obj" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ELSEIF  "$(CFG)" == "OpenGL - Win32 Debug"


"$(INTDIR)\RobotBattery.obj"	"$(INTDIR)\RobotBattery.sbr" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ENDIF 

SOURCE=.\RobotGL.cpp

!IF  "$(CFG)" == "OpenGL - Win32 Release"


"$(INTDIR)\RobotGL.obj" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ELSEIF  "$(CFG)" == "OpenGL - Win32 Debug"


"$(INTDIR)\RobotGL.obj"	"$(INTDIR)\RobotGL.sbr" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ENDIF 

SOURCE=.\RobotMemory.cpp

!IF  "$(CFG)" == "OpenGL - Win32 Release"


"$(INTDIR)\RobotMemory.obj" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ELSEIF  "$(CFG)" == "OpenGL - Win32 Debug"


"$(INTDIR)\RobotMemory.obj"	"$(INTDIR)\RobotMemory.sbr" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ENDIF 

SOURCE=.\RobotPath.cpp

!IF  "$(CFG)" == "OpenGL - Win32 Release"


"$(INTDIR)\RobotPath.obj" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ELSEIF  "$(CFG)" == "OpenGL - Win32 Debug"


"$(INTDIR)\RobotPath.obj"	"$(INTDIR)\RobotPath.sbr" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ENDIF 

SOURCE=.\RobotProcessor.cpp

!IF  "$(CFG)" == "OpenGL - Win32 Release"


"$(INTDIR)\RobotProcessor.obj" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ELSEIF  "$(CFG)" == "OpenGL - Win32 Debug"


"$(INTDIR)\RobotProcessor.obj"	"$(INTDIR)\RobotProcessor.sbr" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ENDIF 

SOURCE=.\RobotProgramLoader.cpp

!IF  "$(CFG)" == "OpenGL - Win32 Release"


"$(INTDIR)\RobotProgramLoader.obj" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ELSEIF  "$(CFG)" == "OpenGL - Win32 Debug"


"$(INTDIR)\RobotProgramLoader.obj"	"$(INTDIR)\RobotProgramLoader.sbr" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ENDIF 

SOURCE=.\RobotScaner.cpp

!IF  "$(CFG)" == "OpenGL - Win32 Release"


"$(INTDIR)\RobotScaner.obj" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ELSEIF  "$(CFG)" == "OpenGL - Win32 Debug"


"$(INTDIR)\RobotScaner.obj"	"$(INTDIR)\RobotScaner.sbr" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ENDIF 

SOURCE=.\Rubbish.cpp

!IF  "$(CFG)" == "OpenGL - Win32 Release"


"$(INTDIR)\Rubbish.obj" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ELSEIF  "$(CFG)" == "OpenGL - Win32 Debug"


"$(INTDIR)\Rubbish.obj"	"$(INTDIR)\Rubbish.sbr" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ENDIF 

SOURCE=.\RubbishGL.cpp

!IF  "$(CFG)" == "OpenGL - Win32 Release"


"$(INTDIR)\RubbishGL.obj" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ELSEIF  "$(CFG)" == "OpenGL - Win32 Debug"


"$(INTDIR)\RubbishGL.obj"	"$(INTDIR)\RubbishGL.sbr" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ENDIF 

SOURCE=.\Stack.cpp

!IF  "$(CFG)" == "OpenGL - Win32 Release"


"$(INTDIR)\Stack.obj" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ELSEIF  "$(CFG)" == "OpenGL - Win32 Debug"


"$(INTDIR)\Stack.obj"	"$(INTDIR)\Stack.sbr" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ENDIF 

SOURCE=.\StackObject.cpp

!IF  "$(CFG)" == "OpenGL - Win32 Release"


"$(INTDIR)\StackObject.obj" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ELSEIF  "$(CFG)" == "OpenGL - Win32 Debug"


"$(INTDIR)\StackObject.obj"	"$(INTDIR)\StackObject.sbr" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ENDIF 

SOURCE=.\StdAfx.cpp

!IF  "$(CFG)" == "OpenGL - Win32 Release"

CPP_SWITCHES=/nologo /MD /W3 /GX /O2 /D "WIN32" /D "NDEBUG" /D "_WINDOWS" /D "_AFXDLL" /D "_MBCS" /Fp"$(INTDIR)\OpenGL.pch" /Yc"stdafx.h" /Fo"$(INTDIR)\\" /Fd"$(INTDIR)\\" /FD /c 

"$(INTDIR)\StdAfx.obj"	"$(INTDIR)\OpenGL.pch" : $(SOURCE) "$(INTDIR)"
	$(CPP) @<<
  $(CPP_SWITCHES) $(SOURCE)
<<


!ELSEIF  "$(CFG)" == "OpenGL - Win32 Debug"

CPP_SWITCHES=/nologo /MDd /W3 /Gm /GX /ZI /Od /D "WIN32" /D "_DEBUG" /D "_WINDOWS" /D "_AFXDLL" /D "_MBCS" /FR"$(INTDIR)\\" /Fp"$(INTDIR)\OpenGL.pch" /Yc"stdafx.h" /Fo"$(INTDIR)\\" /Fd"$(INTDIR)\\" /FD /GZ /Zm250 /c 

"$(INTDIR)\StdAfx.obj"	"$(INTDIR)\StdAfx.sbr"	"$(INTDIR)\OpenGL.pch" : $(SOURCE) "$(INTDIR)"
	$(CPP) @<<
  $(CPP_SWITCHES) $(SOURCE)
<<


!ENDIF 

SOURCE=.\TableGL.cpp

!IF  "$(CFG)" == "OpenGL - Win32 Release"


"$(INTDIR)\TableGL.obj" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ELSEIF  "$(CFG)" == "OpenGL - Win32 Debug"


"$(INTDIR)\TableGL.obj"	"$(INTDIR)\TableGL.sbr" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ENDIF 

SOURCE=.\Wall.cpp

!IF  "$(CFG)" == "OpenGL - Win32 Release"


"$(INTDIR)\Wall.obj" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ELSEIF  "$(CFG)" == "OpenGL - Win32 Debug"


"$(INTDIR)\Wall.obj"	"$(INTDIR)\Wall.sbr" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ENDIF 

SOURCE=.\WallGL.cpp

!IF  "$(CFG)" == "OpenGL - Win32 Release"


"$(INTDIR)\WallGL.obj" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ELSEIF  "$(CFG)" == "OpenGL - Win32 Debug"


"$(INTDIR)\WallGL.obj"	"$(INTDIR)\WallGL.sbr" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ENDIF 

SOURCE=.\World.cpp

!IF  "$(CFG)" == "OpenGL - Win32 Release"


"$(INTDIR)\World.obj" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ELSEIF  "$(CFG)" == "OpenGL - Win32 Debug"


"$(INTDIR)\World.obj"	"$(INTDIR)\World.sbr" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ENDIF 

SOURCE=.\WorldGL.cpp

!IF  "$(CFG)" == "OpenGL - Win32 Release"


"$(INTDIR)\WorldGL.obj" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ELSEIF  "$(CFG)" == "OpenGL - Win32 Debug"


"$(INTDIR)\WorldGL.obj"	"$(INTDIR)\WorldGL.sbr" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ENDIF 

SOURCE=.\WorldObject.cpp

!IF  "$(CFG)" == "OpenGL - Win32 Release"


"$(INTDIR)\WorldObject.obj" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ELSEIF  "$(CFG)" == "OpenGL - Win32 Debug"


"$(INTDIR)\WorldObject.obj"	"$(INTDIR)\WorldObject.sbr" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ENDIF 

SOURCE=.\WorldObjectList.cpp

!IF  "$(CFG)" == "OpenGL - Win32 Release"


"$(INTDIR)\WorldObjectList.obj" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ELSEIF  "$(CFG)" == "OpenGL - Win32 Debug"


"$(INTDIR)\WorldObjectList.obj"	"$(INTDIR)\WorldObjectList.sbr" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ENDIF 

SOURCE=.\WorldObjectVerifier.cpp

!IF  "$(CFG)" == "OpenGL - Win32 Release"


"$(INTDIR)\WorldObjectVerifier.obj" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ELSEIF  "$(CFG)" == "OpenGL - Win32 Debug"


"$(INTDIR)\WorldObjectVerifier.obj"	"$(INTDIR)\WorldObjectVerifier.sbr" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ENDIF 

SOURCE=.\WorldService.cpp

!IF  "$(CFG)" == "OpenGL - Win32 Release"


"$(INTDIR)\WorldService.obj" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ELSEIF  "$(CFG)" == "OpenGL - Win32 Debug"


"$(INTDIR)\WorldService.obj"	"$(INTDIR)\WorldService.sbr" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\OpenGL.pch"


!ENDIF 


!ENDIF 

