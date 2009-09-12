@echo off
rem *******************************************************************************
rem  Copyright (c) 2000, 2005 IBM Corporation and others.
rem  All rights reserved. This program and the accompanying materials
rem  are made available under the terms of the Eclipse Public License v1.0
rem  which accompanies this distribution, and is available at
rem  http://www.eclipse.org/legal/epl-v10.html
rem  
rem  Contributors:
rem      IBM Corporation - initial API and implementation
rem *******************************************************************************

@echo off

IF NOT "%JAVA_HOME%"=="" GOTO MAKE

rem *****
rem Javah
rem *****
set JAVA_HOME=c:\Program Files\Java\jdk1.5.0_06
set path=%JAVA_HOME%;%path%

rem ********
rem MSVC 6.0
rem ********
call "C:\Program Files\Microsoft Visual Studio 8\VC\vcvarsall.bat"

rem ****** 
rem MS-SDK
rem ******
rem call "C:\Program Files\Microsoft Platform SDK\SetEnv" /XP32 /DEBUG
call "C:\Program Files\Microsoft Platform SDK\SetEnv" /XP32 /RETAIL

set OUTPUT_DIR=..\..\bin

:MAKE
nmake -f make_win32.mak %1 %2 %3 %4
