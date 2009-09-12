/*
 * javahexeditor, a java hex editor
 * Copyright (C) 2006, 2009 Jordi Bergenthal, pestatije(-at_)users.sourceforge.net
 * The official javahexeditor site is sourceforge.net/projects/javahexeditor
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */
#include <stdio.h>
#include <string.h>
#include <jni.h>

#define COM_NATIVE(func) Java_org_eclipse_swt_internal_ole_win32_COM_##func
#define OS_NATIVE(func) Java_org_eclipse_swt_internal_win32_OS_##func
#define SWT_PTR jint
#define SWTEXPORT extern "C" __stdcall


// From os.c
SWTEXPORT jint JNICALL OS_NATIVE(AbortDoc)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jint JNICALL OS_NATIVE(ActivateKeyboardLayout)(JNIEnv *env, jclass that, jint arg0, jint arg1);
SWTEXPORT jboolean JNICALL OS_NATIVE(AdjustWindowRectEx)(JNIEnv *env, jclass that, jobject arg0, jint arg1, jboolean arg2, jint arg3);
SWTEXPORT jboolean JNICALL OS_NATIVE(AlphaBlend)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3, jint arg4, jint arg5, jint arg6, jint arg7, jint arg8, jint arg9, jobject arg10);
SWTEXPORT jboolean JNICALL OS_NATIVE(Arc)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3, jint arg4, jint arg5, jint arg6, jint arg7, jint arg8);
SWTEXPORT jint JNICALL OS_NATIVE(BeginDeferWindowPos)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jint JNICALL OS_NATIVE(BeginPaint)(JNIEnv *env, jclass that, jint arg0, jobject arg1);
SWTEXPORT jboolean JNICALL OS_NATIVE(BeginPath)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(BitBlt)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3, jint arg4, jint arg5, jint arg6, jint arg7, jint arg8);
SWTEXPORT jboolean JNICALL OS_NATIVE(BringWindowToTop)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jint JNICALL OS_NATIVE(Call)(JNIEnv *env, jclass that, jint arg0, jobject arg1);
SWTEXPORT jint JNICALL OS_NATIVE(CallNextHookEx)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3);
SWTEXPORT jint JNICALL OS_NATIVE(CallWindowProcA)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3, jint arg4);
SWTEXPORT jint JNICALL OS_NATIVE(CallWindowProcW)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3, jint arg4);
SWTEXPORT jshort JNICALL OS_NATIVE(CharLowerA)(JNIEnv *env, jclass that, jshort arg0);
SWTEXPORT jshort JNICALL OS_NATIVE(CharLowerW)(JNIEnv *env, jclass that, jshort arg0);
SWTEXPORT jshort JNICALL OS_NATIVE(CharUpperA)(JNIEnv *env, jclass that, jshort arg0);
SWTEXPORT jshort JNICALL OS_NATIVE(CharUpperW)(JNIEnv *env, jclass that, jshort arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(CheckMenuItem)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2);
SWTEXPORT jboolean JNICALL OS_NATIVE(ChooseColorA)(JNIEnv *env, jclass that, jobject arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(ChooseColorW)(JNIEnv *env, jclass that, jobject arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(ChooseFontA)(JNIEnv *env, jclass that, jobject arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(ChooseFontW)(JNIEnv *env, jclass that, jobject arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(ClientToScreen)(JNIEnv *env, jclass that, jint arg0, jobject arg1);
SWTEXPORT jboolean JNICALL OS_NATIVE(CloseClipboard)(JNIEnv *env, jclass that);
SWTEXPORT jint JNICALL OS_NATIVE(CloseThemeData)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jint JNICALL OS_NATIVE(CoCreateInstance)(JNIEnv *env, jclass that, jbyteArray arg0, jint arg1, jint arg2, jbyteArray arg3, jintArray arg4);
SWTEXPORT jint JNICALL OS_NATIVE(CombineRgn)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3);
SWTEXPORT jint JNICALL OS_NATIVE(CommDlgExtendedError)(JNIEnv *env, jclass that);
#ifdef WIN32_PLATFORM_HPC2000
SWTEXPORT jboolean JNICALL OS_NATIVE(CommandBar_1AddAdornments)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2);
SWTEXPORT jint JNICALL OS_NATIVE(CommandBar_1Create)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2);
#endif
#ifdef _WIN32_WCE
SWTEXPORT void JNICALL OS_NATIVE(CommandBar_1Destroy)(JNIEnv *env, jclass that, jint arg0);
#endif
#ifdef WIN32_PLATFORM_HPC2000
SWTEXPORT jboolean JNICALL OS_NATIVE(CommandBar_1DrawMenuBar)(JNIEnv *env, jclass that, jint arg0, jint arg1);
SWTEXPORT jint JNICALL OS_NATIVE(CommandBar_1Height)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(CommandBar_1InsertMenubarEx)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3);
SWTEXPORT jboolean JNICALL OS_NATIVE(CommandBar_1Show)(JNIEnv *env, jclass that, jint arg0, jboolean arg1);
#endif
SWTEXPORT jint JNICALL OS_NATIVE(CopyImage)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3, jint arg4);
SWTEXPORT jint JNICALL OS_NATIVE(CreateAcceleratorTableA)(JNIEnv *env, jclass that, jbyteArray arg0, jint arg1);
SWTEXPORT jint JNICALL OS_NATIVE(CreateAcceleratorTableW)(JNIEnv *env, jclass that, jbyteArray arg0, jint arg1);
SWTEXPORT jint JNICALL OS_NATIVE(CreateBitmap)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3, jbyteArray arg4);
SWTEXPORT jboolean JNICALL OS_NATIVE(CreateCaret)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3);
SWTEXPORT jint JNICALL OS_NATIVE(CreateCompatibleBitmap)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2);
SWTEXPORT jint JNICALL OS_NATIVE(CreateCompatibleDC)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jint JNICALL OS_NATIVE(CreateCursor)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3, jint arg4, jbyteArray arg5, jbyteArray arg6);
SWTEXPORT jint JNICALL OS_NATIVE(CreateDCA)(JNIEnv *env, jclass that, jbyteArray arg0, jbyteArray arg1, jint arg2, jint arg3);
SWTEXPORT jint JNICALL OS_NATIVE(CreateDCW)(JNIEnv *env, jclass that, jcharArray arg0, jcharArray arg1, jint arg2, jint arg3);
SWTEXPORT jint JNICALL OS_NATIVE(CreateDIBSection)(JNIEnv *env, jclass that, jint arg0, jbyteArray arg1, jint arg2, jintArray arg3, jint arg4, jint arg5);
SWTEXPORT jint JNICALL OS_NATIVE(CreateFontIndirectA__I)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jint JNICALL OS_NATIVE(CreateFontIndirectA__Lorg_eclipse_swt_internal_win32_LOGFONTA_2)(JNIEnv *env, jclass that, jobject arg0);
SWTEXPORT jint JNICALL OS_NATIVE(CreateFontIndirectW__I)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jint JNICALL OS_NATIVE(CreateFontIndirectW__Lorg_eclipse_swt_internal_win32_LOGFONTW_2)(JNIEnv *env, jclass that, jobject arg0);
SWTEXPORT jint JNICALL OS_NATIVE(CreateIconIndirect)(JNIEnv *env, jclass that, jobject arg0);
SWTEXPORT jint JNICALL OS_NATIVE(CreateMenu)(JNIEnv *env, jclass that);
SWTEXPORT jint JNICALL OS_NATIVE(CreatePalette)(JNIEnv *env, jclass that, jbyteArray arg0);
SWTEXPORT jint JNICALL OS_NATIVE(CreatePatternBrush)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jint JNICALL OS_NATIVE(CreatePen)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2);
SWTEXPORT jint JNICALL OS_NATIVE(CreatePolygonRgn)(JNIEnv *env, jclass that, jintArray arg0, jint arg1, jint arg2);
SWTEXPORT jint JNICALL OS_NATIVE(CreatePopupMenu)(JNIEnv *env, jclass that);
SWTEXPORT jint JNICALL OS_NATIVE(CreateRectRgn)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3);
SWTEXPORT jint JNICALL OS_NATIVE(CreateSolidBrush)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jint JNICALL OS_NATIVE(CreateStreamOnHGlobal)(JNIEnv *env, jclass that, jint arg0, jboolean arg1, jintArray arg2);
SWTEXPORT jint JNICALL OS_NATIVE(CreateWindowExA)(JNIEnv *env, jclass that, jint arg0, jbyteArray arg1, jbyteArray arg2, jint arg3, jint arg4, jint arg5, jint arg6, jint arg7, jint arg8, jint arg9, jint arg10, jobject arg11);
SWTEXPORT jint JNICALL OS_NATIVE(CreateWindowExW)(JNIEnv *env, jclass that, jint arg0, jcharArray arg1, jcharArray arg2, jint arg3, jint arg4, jint arg5, jint arg6, jint arg7, jint arg8, jint arg9, jint arg10, jobject arg11);
SWTEXPORT jint JNICALL OS_NATIVE(DefFrameProcA)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3, jint arg4);
SWTEXPORT jint JNICALL OS_NATIVE(DefFrameProcW)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3, jint arg4);
SWTEXPORT jint JNICALL OS_NATIVE(DefMDIChildProcA)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3);
SWTEXPORT jint JNICALL OS_NATIVE(DefMDIChildProcW)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3);
SWTEXPORT jint JNICALL OS_NATIVE(DefWindowProcA)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3);
SWTEXPORT jint JNICALL OS_NATIVE(DefWindowProcW)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3);
SWTEXPORT jint JNICALL OS_NATIVE(DeferWindowPos)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3, jint arg4, jint arg5, jint arg6, jint arg7);
SWTEXPORT jboolean JNICALL OS_NATIVE(DeleteDC)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(DeleteMenu)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2);
SWTEXPORT jboolean JNICALL OS_NATIVE(DeleteObject)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(DestroyAcceleratorTable)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(DestroyCaret)(JNIEnv *env, jclass that);
SWTEXPORT jboolean JNICALL OS_NATIVE(DestroyCursor)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(DestroyIcon)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(DestroyMenu)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(DestroyWindow)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jint JNICALL OS_NATIVE(DispatchMessageA)(JNIEnv *env, jclass that, jobject arg0);
SWTEXPORT jint JNICALL OS_NATIVE(DispatchMessageW)(JNIEnv *env, jclass that, jobject arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(DragDetect)(JNIEnv *env, jclass that, jint arg0, jobject arg1);
SWTEXPORT void JNICALL OS_NATIVE(DragFinish)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jint JNICALL OS_NATIVE(DragQueryFileA)(JNIEnv *env, jclass that, jint arg0, jint arg1, jbyteArray arg2, jint arg3);
SWTEXPORT jint JNICALL OS_NATIVE(DragQueryFileW)(JNIEnv *env, jclass that, jint arg0, jint arg1, jcharArray arg2, jint arg3);
SWTEXPORT jboolean JNICALL OS_NATIVE(DrawEdge)(JNIEnv *env, jclass that, jint arg0, jobject arg1, jint arg2, jint arg3);
SWTEXPORT jboolean JNICALL OS_NATIVE(DrawFocusRect)(JNIEnv *env, jclass that, jint arg0, jobject arg1);
SWTEXPORT jboolean JNICALL OS_NATIVE(DrawFrameControl)(JNIEnv *env, jclass that, jint arg0, jobject arg1, jint arg2, jint arg3);
SWTEXPORT jboolean JNICALL OS_NATIVE(DrawIconEx)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3, jint arg4, jint arg5, jint arg6, jint arg7, jint arg8);
SWTEXPORT jboolean JNICALL OS_NATIVE(DrawMenuBar)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(DrawStateA)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3, jint arg4, jint arg5, jint arg6, jint arg7, jint arg8, jint arg9);
SWTEXPORT jboolean JNICALL OS_NATIVE(DrawStateW)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3, jint arg4, jint arg5, jint arg6, jint arg7, jint arg8, jint arg9);
SWTEXPORT jint JNICALL OS_NATIVE(DrawTextA)(JNIEnv *env, jclass that, jint arg0, jbyteArray arg1, jint arg2, jobject arg3, jint arg4);
SWTEXPORT jint JNICALL OS_NATIVE(DrawTextW)(JNIEnv *env, jclass that, jint arg0, jcharArray arg1, jint arg2, jobject arg3, jint arg4);
SWTEXPORT jint JNICALL OS_NATIVE(DrawThemeBackground)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3, jobject arg4, jobject arg5);
SWTEXPORT jboolean JNICALL OS_NATIVE(Ellipse)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3, jint arg4);
SWTEXPORT jboolean JNICALL OS_NATIVE(EnableMenuItem)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2);
SWTEXPORT jboolean JNICALL OS_NATIVE(EnableScrollBar)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2);
SWTEXPORT jboolean JNICALL OS_NATIVE(EnableWindow)(JNIEnv *env, jclass that, jint arg0, jboolean arg1);
SWTEXPORT jboolean JNICALL OS_NATIVE(EndDeferWindowPos)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jint JNICALL OS_NATIVE(EndDoc)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jint JNICALL OS_NATIVE(EndPage)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jint JNICALL OS_NATIVE(EndPaint)(JNIEnv *env, jclass that, jint arg0, jobject arg1);
SWTEXPORT jboolean JNICALL OS_NATIVE(EndPath)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(EnumDisplayMonitors)(JNIEnv *env, jclass that, jint arg0, jobject arg1, jint arg2, jint arg3);
SWTEXPORT jint JNICALL OS_NATIVE(EnumFontFamiliesA)(JNIEnv *env, jclass that, jint arg0, jbyteArray arg1, jint arg2, jint arg3);
SWTEXPORT jint JNICALL OS_NATIVE(EnumFontFamiliesExA)(JNIEnv *env, jclass that, jint arg0, jobject arg1, jint arg2, jint arg3, jint arg4);
SWTEXPORT jint JNICALL OS_NATIVE(EnumFontFamiliesExW)(JNIEnv *env, jclass that, jint arg0, jobject arg1, jint arg2, jint arg3, jint arg4);
SWTEXPORT jint JNICALL OS_NATIVE(EnumFontFamiliesW)(JNIEnv *env, jclass that, jint arg0, jcharArray arg1, jint arg2, jint arg3);
SWTEXPORT jboolean JNICALL OS_NATIVE(EnumSystemLanguageGroupsA)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2);
SWTEXPORT jboolean JNICALL OS_NATIVE(EnumSystemLanguageGroupsW)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2);
SWTEXPORT jboolean JNICALL OS_NATIVE(EnumSystemLocalesA)(JNIEnv *env, jclass that, jint arg0, jint arg1);
SWTEXPORT jboolean JNICALL OS_NATIVE(EnumSystemLocalesW)(JNIEnv *env, jclass that, jint arg0, jint arg1);
SWTEXPORT jboolean JNICALL OS_NATIVE(EqualRect)(JNIEnv *env, jclass that, jobject arg0, jobject arg1);
SWTEXPORT jboolean JNICALL OS_NATIVE(EqualRgn)(JNIEnv *env, jclass that, jint arg0, jint arg1);
SWTEXPORT jint JNICALL OS_NATIVE(ExcludeClipRect)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3, jint arg4);
SWTEXPORT jint JNICALL OS_NATIVE(ExpandEnvironmentStringsA)(JNIEnv *env, jclass that, jbyteArray arg0, jbyteArray arg1, jint arg2);
SWTEXPORT jint JNICALL OS_NATIVE(ExpandEnvironmentStringsW)(JNIEnv *env, jclass that, jcharArray arg0, jcharArray arg1, jint arg2);
SWTEXPORT jint JNICALL OS_NATIVE(ExtCreatePen)(JNIEnv *env, jclass that, jint arg0, jint arg1, jobject arg2, jint arg3, jintArray arg4);
SWTEXPORT jint JNICALL OS_NATIVE(ExtCreateRegion)(JNIEnv *env, jclass that, jfloatArray arg0, jint arg1, jintArray arg2);
SWTEXPORT jboolean JNICALL OS_NATIVE(ExtTextOutA)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3, jobject arg4, jbyteArray arg5, jint arg6, jintArray arg7);
SWTEXPORT jboolean JNICALL OS_NATIVE(ExtTextOutW)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3, jobject arg4, jcharArray arg5, jint arg6, jintArray arg7);
SWTEXPORT jint JNICALL OS_NATIVE(ExtractIconExA)(JNIEnv *env, jclass that, jbyteArray arg0, jint arg1, jintArray arg2, jintArray arg3, jint arg4);
SWTEXPORT jint JNICALL OS_NATIVE(ExtractIconExW)(JNIEnv *env, jclass that, jcharArray arg0, jint arg1, jintArray arg2, jintArray arg3, jint arg4);
SWTEXPORT jboolean JNICALL OS_NATIVE(FillPath)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jint JNICALL OS_NATIVE(FillRect)(JNIEnv *env, jclass that, jint arg0, jobject arg1, jint arg2);
SWTEXPORT jint JNICALL OS_NATIVE(FindWindowA)(JNIEnv *env, jclass that, jbyteArray arg0, jbyteArray arg1);
SWTEXPORT jint JNICALL OS_NATIVE(FindWindowW)(JNIEnv *env, jclass that, jcharArray arg0, jcharArray arg1);
SWTEXPORT jint JNICALL OS_NATIVE(FormatMessageA)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3, jintArray arg4, jint arg5, jint arg6);
SWTEXPORT jint JNICALL OS_NATIVE(FormatMessageW)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3, jintArray arg4, jint arg5, jint arg6);
SWTEXPORT jboolean JNICALL OS_NATIVE(FreeLibrary)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jint JNICALL OS_NATIVE(GdiSetBatchLimit)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jint JNICALL OS_NATIVE(GetACP)(JNIEnv *env, jclass that);
SWTEXPORT jint JNICALL OS_NATIVE(GetActiveWindow)(JNIEnv *env, jclass that);
SWTEXPORT jint JNICALL OS_NATIVE(GetBkColor)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jint JNICALL OS_NATIVE(GetCapture)(JNIEnv *env, jclass that);
SWTEXPORT jboolean JNICALL OS_NATIVE(GetCaretPos)(JNIEnv *env, jclass that, jobject arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(GetCharABCWidthsA)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jintArray arg3);
SWTEXPORT jboolean JNICALL OS_NATIVE(GetCharABCWidthsW)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jintArray arg3);
SWTEXPORT jboolean JNICALL OS_NATIVE(GetCharWidthA)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jintArray arg3);
SWTEXPORT jboolean JNICALL OS_NATIVE(GetCharWidthW)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jintArray arg3);
SWTEXPORT jint JNICALL OS_NATIVE(GetCharacterPlacementA)(JNIEnv *env, jclass that, jint arg0, jbyteArray arg1, jint arg2, jint arg3, jobject arg4, jint arg5);
SWTEXPORT jint JNICALL OS_NATIVE(GetCharacterPlacementW)(JNIEnv *env, jclass that, jint arg0, jcharArray arg1, jint arg2, jint arg3, jobject arg4, jint arg5);
SWTEXPORT jboolean JNICALL OS_NATIVE(GetClassInfoA)(JNIEnv *env, jclass that, jint arg0, jbyteArray arg1, jobject arg2);
SWTEXPORT jboolean JNICALL OS_NATIVE(GetClassInfoW)(JNIEnv *env, jclass that, jint arg0, jcharArray arg1, jobject arg2);
SWTEXPORT jint JNICALL OS_NATIVE(GetClassNameA)(JNIEnv *env, jclass that, jint arg0, jbyteArray arg1, jint arg2);
SWTEXPORT jint JNICALL OS_NATIVE(GetClassNameW)(JNIEnv *env, jclass that, jint arg0, jcharArray arg1, jint arg2);
SWTEXPORT jboolean JNICALL OS_NATIVE(GetClientRect)(JNIEnv *env, jclass that, jint arg0, jobject arg1);
SWTEXPORT jint JNICALL OS_NATIVE(GetClipBox)(JNIEnv *env, jclass that, jint arg0, jobject arg1);
SWTEXPORT jint JNICALL OS_NATIVE(GetClipRgn)(JNIEnv *env, jclass that, jint arg0, jint arg1);
SWTEXPORT jint JNICALL OS_NATIVE(GetClipboardData)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jint JNICALL OS_NATIVE(GetClipboardFormatNameA)(JNIEnv *env, jclass that, jint arg0, jbyteArray arg1, jint arg2);
SWTEXPORT jint JNICALL OS_NATIVE(GetClipboardFormatNameW)(JNIEnv *env, jclass that, jint arg0, jcharArray arg1, jint arg2);
SWTEXPORT jboolean JNICALL OS_NATIVE(GetComboBoxInfo)(JNIEnv *env, jclass that, jint arg0, jobject arg1);
SWTEXPORT jint JNICALL OS_NATIVE(GetCurrentObject)(JNIEnv *env, jclass that, jint arg0, jint arg1);
SWTEXPORT jint JNICALL OS_NATIVE(GetCurrentProcessId)(JNIEnv *env, jclass that);
SWTEXPORT jint JNICALL OS_NATIVE(GetCurrentThreadId)(JNIEnv *env, jclass that);
SWTEXPORT jint JNICALL OS_NATIVE(GetCursor)(JNIEnv *env, jclass that);
SWTEXPORT jboolean JNICALL OS_NATIVE(GetCursorPos)(JNIEnv *env, jclass that, jobject arg0);
SWTEXPORT jint JNICALL OS_NATIVE(GetDC)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jint JNICALL OS_NATIVE(GetDCEx)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2);
SWTEXPORT jint JNICALL OS_NATIVE(GetDIBColorTable)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jbyteArray arg3);
SWTEXPORT jint JNICALL OS_NATIVE(GetDIBits)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3, jint arg4, jbyteArray arg5, jint arg6);
SWTEXPORT jint JNICALL OS_NATIVE(GetDesktopWindow)(JNIEnv *env, jclass that);
SWTEXPORT jint JNICALL OS_NATIVE(GetDeviceCaps)(JNIEnv *env, jclass that, jint arg0, jint arg1);
SWTEXPORT jint JNICALL OS_NATIVE(GetDialogBaseUnits)(JNIEnv *env, jclass that);
SWTEXPORT jint JNICALL OS_NATIVE(GetDlgItem)(JNIEnv *env, jclass that, jint arg0, jint arg1);
SWTEXPORT jint JNICALL OS_NATIVE(GetDoubleClickTime)(JNIEnv *env, jclass that);
SWTEXPORT jint JNICALL OS_NATIVE(GetFocus)(JNIEnv *env, jclass that);
SWTEXPORT jint JNICALL OS_NATIVE(GetFontLanguageInfo)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jint JNICALL OS_NATIVE(GetForegroundWindow)(JNIEnv *env, jclass that);
SWTEXPORT jboolean JNICALL OS_NATIVE(GetGUIThreadInfo)(JNIEnv *env, jclass that, jint arg0, jobject arg1);
SWTEXPORT jboolean JNICALL OS_NATIVE(GetIconInfo)(JNIEnv *env, jclass that, jint arg0, jobject arg1);
SWTEXPORT jint JNICALL OS_NATIVE(GetKeyNameTextA)(JNIEnv *env, jclass that, jint arg0, jbyteArray arg1, jint arg2);
SWTEXPORT jint JNICALL OS_NATIVE(GetKeyNameTextW)(JNIEnv *env, jclass that, jint arg0, jcharArray arg1, jint arg2);
SWTEXPORT jshort JNICALL OS_NATIVE(GetKeyState)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jint JNICALL OS_NATIVE(GetKeyboardLayout)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jint JNICALL OS_NATIVE(GetKeyboardLayoutList)(JNIEnv *env, jclass that, jint arg0, jintArray arg1);
SWTEXPORT jboolean JNICALL OS_NATIVE(GetKeyboardState)(JNIEnv *env, jclass that, jbyteArray arg0);
SWTEXPORT jint JNICALL OS_NATIVE(GetLastActivePopup)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jint JNICALL OS_NATIVE(GetLastError)(JNIEnv *env, jclass that);
SWTEXPORT jint JNICALL OS_NATIVE(GetLayout)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jint JNICALL OS_NATIVE(GetLocaleInfoA)(JNIEnv *env, jclass that, jint arg0, jint arg1, jbyteArray arg2, jint arg3);
SWTEXPORT jint JNICALL OS_NATIVE(GetLocaleInfoW)(JNIEnv *env, jclass that, jint arg0, jint arg1, jcharArray arg2, jint arg3);
SWTEXPORT jint JNICALL OS_NATIVE(GetMenu)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(GetMenuBarInfo)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jobject arg3);
SWTEXPORT jint JNICALL OS_NATIVE(GetMenuDefaultItem)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2);
SWTEXPORT jboolean JNICALL OS_NATIVE(GetMenuInfo)(JNIEnv *env, jclass that, jint arg0, jobject arg1);
SWTEXPORT jint JNICALL OS_NATIVE(GetMenuItemCount)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(GetMenuItemInfoA)(JNIEnv *env, jclass that, jint arg0, jint arg1, jboolean arg2, jobject arg3);
SWTEXPORT jboolean JNICALL OS_NATIVE(GetMenuItemInfoW)(JNIEnv *env, jclass that, jint arg0, jint arg1, jboolean arg2, jobject arg3);
SWTEXPORT jboolean JNICALL OS_NATIVE(GetMenuItemRect)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jobject arg3);
SWTEXPORT jboolean JNICALL OS_NATIVE(GetMessageA)(JNIEnv *env, jclass that, jobject arg0, jint arg1, jint arg2, jint arg3);
SWTEXPORT jint JNICALL OS_NATIVE(GetMessagePos)(JNIEnv *env, jclass that);
SWTEXPORT jint JNICALL OS_NATIVE(GetMessageTime)(JNIEnv *env, jclass that);
SWTEXPORT jboolean JNICALL OS_NATIVE(GetMessageW)(JNIEnv *env, jclass that, jobject arg0, jint arg1, jint arg2, jint arg3);
SWTEXPORT jint JNICALL OS_NATIVE(GetMetaRgn)(JNIEnv *env, jclass that, jint arg0, jint arg1);
SWTEXPORT jint JNICALL OS_NATIVE(GetModuleHandleA)(JNIEnv *env, jclass that, jbyteArray arg0);
SWTEXPORT jint JNICALL OS_NATIVE(GetModuleHandleW)(JNIEnv *env, jclass that, jcharArray arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(GetMonitorInfoA)(JNIEnv *env, jclass that, jint arg0, jobject arg1);
SWTEXPORT jboolean JNICALL OS_NATIVE(GetMonitorInfoW)(JNIEnv *env, jclass that, jint arg0, jobject arg1);
SWTEXPORT jint JNICALL OS_NATIVE(GetNearestPaletteIndex)(JNIEnv *env, jclass that, jint arg0, jint arg1);
SWTEXPORT jint JNICALL OS_NATIVE(GetObjectA__III)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2);
SWTEXPORT jint JNICALL OS_NATIVE(GetObjectA__IILorg_eclipse_swt_internal_win32_BITMAP_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jobject arg2);
SWTEXPORT jint JNICALL OS_NATIVE(GetObjectA__IILorg_eclipse_swt_internal_win32_DIBSECTION_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jobject arg2);
SWTEXPORT jint JNICALL OS_NATIVE(GetObjectA__IILorg_eclipse_swt_internal_win32_EXTLOGPEN_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jobject arg2);
SWTEXPORT jint JNICALL OS_NATIVE(GetObjectA__IILorg_eclipse_swt_internal_win32_LOGBRUSH_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jobject arg2);
SWTEXPORT jint JNICALL OS_NATIVE(GetObjectA__IILorg_eclipse_swt_internal_win32_LOGFONTA_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jobject arg2);
SWTEXPORT jint JNICALL OS_NATIVE(GetObjectA__IILorg_eclipse_swt_internal_win32_LOGPEN_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jobject arg2);
SWTEXPORT jint JNICALL OS_NATIVE(GetObjectW__III)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2);
SWTEXPORT jint JNICALL OS_NATIVE(GetObjectW__IILorg_eclipse_swt_internal_win32_BITMAP_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jobject arg2);
SWTEXPORT jint JNICALL OS_NATIVE(GetObjectW__IILorg_eclipse_swt_internal_win32_DIBSECTION_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jobject arg2);
SWTEXPORT jint JNICALL OS_NATIVE(GetObjectW__IILorg_eclipse_swt_internal_win32_EXTLOGPEN_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jobject arg2);
SWTEXPORT jint JNICALL OS_NATIVE(GetObjectW__IILorg_eclipse_swt_internal_win32_LOGBRUSH_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jobject arg2);
SWTEXPORT jint JNICALL OS_NATIVE(GetObjectW__IILorg_eclipse_swt_internal_win32_LOGFONTW_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jobject arg2);
SWTEXPORT jint JNICALL OS_NATIVE(GetObjectW__IILorg_eclipse_swt_internal_win32_LOGPEN_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jobject arg2);
SWTEXPORT jboolean JNICALL OS_NATIVE(GetOpenFileNameA)(JNIEnv *env, jclass that, jobject arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(GetOpenFileNameW)(JNIEnv *env, jclass that, jobject arg0);
SWTEXPORT jint JNICALL OS_NATIVE(GetPaletteEntries)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jbyteArray arg3);
SWTEXPORT jint JNICALL OS_NATIVE(GetParent)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jint JNICALL OS_NATIVE(GetPixel)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2);
SWTEXPORT jint JNICALL OS_NATIVE(GetPolyFillMode)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jint JNICALL OS_NATIVE(GetProcAddress)(JNIEnv *env, jclass that, jint arg0, jbyteArray arg1);
SWTEXPORT jint JNICALL OS_NATIVE(GetProcessHeap)(JNIEnv *env, jclass that);
SWTEXPORT jint JNICALL OS_NATIVE(GetProfileStringA)(JNIEnv *env, jclass that, jbyteArray arg0, jbyteArray arg1, jbyteArray arg2, jbyteArray arg3, jint arg4);
SWTEXPORT jint JNICALL OS_NATIVE(GetProfileStringW)(JNIEnv *env, jclass that, jcharArray arg0, jcharArray arg1, jcharArray arg2, jcharArray arg3, jint arg4);
SWTEXPORT jint JNICALL OS_NATIVE(GetPropA)(JNIEnv *env, jclass that, jint arg0, jint arg1);
SWTEXPORT jint JNICALL OS_NATIVE(GetPropW)(JNIEnv *env, jclass that, jint arg0, jint arg1);
SWTEXPORT jint JNICALL OS_NATIVE(GetROP2)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jint JNICALL OS_NATIVE(GetRandomRgn)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2);
SWTEXPORT jint JNICALL OS_NATIVE(GetRegionData)(JNIEnv *env, jclass that, jint arg0, jint arg1, jintArray arg2);
SWTEXPORT jint JNICALL OS_NATIVE(GetRgnBox)(JNIEnv *env, jclass that, jint arg0, jobject arg1);
SWTEXPORT jboolean JNICALL OS_NATIVE(GetSaveFileNameA)(JNIEnv *env, jclass that, jobject arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(GetSaveFileNameW)(JNIEnv *env, jclass that, jobject arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(GetScrollInfo)(JNIEnv *env, jclass that, jint arg0, jint arg1, jobject arg2);
SWTEXPORT jint JNICALL OS_NATIVE(GetStockObject)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jint JNICALL OS_NATIVE(GetSysColor)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jint JNICALL OS_NATIVE(GetSysColorBrush)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jshort JNICALL OS_NATIVE(GetSystemDefaultUILanguage)(JNIEnv *env, jclass that);
SWTEXPORT jint JNICALL OS_NATIVE(GetSystemMenu)(JNIEnv *env, jclass that, jint arg0, jboolean arg1);
SWTEXPORT jint JNICALL OS_NATIVE(GetSystemMetrics)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jint JNICALL OS_NATIVE(GetSystemPaletteEntries)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jbyteArray arg3);
SWTEXPORT jint JNICALL OS_NATIVE(GetTextCharset)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jint JNICALL OS_NATIVE(GetTextColor)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(GetTextExtentPoint32A)(JNIEnv *env, jclass that, jint arg0, jbyteArray arg1, jint arg2, jobject arg3);
SWTEXPORT jboolean JNICALL OS_NATIVE(GetTextExtentPoint32W)(JNIEnv *env, jclass that, jint arg0, jcharArray arg1, jint arg2, jobject arg3);
SWTEXPORT jboolean JNICALL OS_NATIVE(GetTextMetricsA)(JNIEnv *env, jclass that, jint arg0, jobject arg1);
SWTEXPORT jboolean JNICALL OS_NATIVE(GetTextMetricsW)(JNIEnv *env, jclass that, jint arg0, jobject arg1);
SWTEXPORT jint JNICALL OS_NATIVE(GetTickCount)(JNIEnv *env, jclass that);
SWTEXPORT jboolean JNICALL OS_NATIVE(GetUpdateRect)(JNIEnv *env, jclass that, jint arg0, jobject arg1, jboolean arg2);
SWTEXPORT jint JNICALL OS_NATIVE(GetUpdateRgn)(JNIEnv *env, jclass that, jint arg0, jint arg1, jboolean arg2);
SWTEXPORT jboolean JNICALL OS_NATIVE(GetVersionExA)(JNIEnv *env, jclass that, jobject arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(GetVersionExW)(JNIEnv *env, jclass that, jobject arg0);
SWTEXPORT jint JNICALL OS_NATIVE(GetWindow)(JNIEnv *env, jclass that, jint arg0, jint arg1);
SWTEXPORT jint JNICALL OS_NATIVE(GetWindowLongA)(JNIEnv *env, jclass that, jint arg0, jint arg1);
SWTEXPORT jint JNICALL OS_NATIVE(GetWindowLongW)(JNIEnv *env, jclass that, jint arg0, jint arg1);
SWTEXPORT jboolean JNICALL OS_NATIVE(GetWindowOrgEx)(JNIEnv *env, jclass that, jint arg0, jobject arg1);
SWTEXPORT jboolean JNICALL OS_NATIVE(GetWindowPlacement)(JNIEnv *env, jclass that, jint arg0, jobject arg1);
SWTEXPORT jboolean JNICALL OS_NATIVE(GetWindowRect)(JNIEnv *env, jclass that, jint arg0, jobject arg1);
SWTEXPORT jint JNICALL OS_NATIVE(GetWindowRgn)(JNIEnv *env, jclass that, jint arg0, jint arg1);
SWTEXPORT jint JNICALL OS_NATIVE(GetWindowTextA)(JNIEnv *env, jclass that, jint arg0, jbyteArray arg1, jint arg2);
SWTEXPORT jint JNICALL OS_NATIVE(GetWindowTextLengthA)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jint JNICALL OS_NATIVE(GetWindowTextLengthW)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jint JNICALL OS_NATIVE(GetWindowTextW)(JNIEnv *env, jclass that, jint arg0, jcharArray arg1, jint arg2);
SWTEXPORT jint JNICALL OS_NATIVE(GetWindowThreadProcessId)(JNIEnv *env, jclass that, jint arg0, jintArray arg1);
SWTEXPORT jboolean JNICALL OS_NATIVE(GetWorldTransform)(JNIEnv *env, jclass that, jint arg0, jfloatArray arg1);
SWTEXPORT jint JNICALL OS_NATIVE(GlobalAddAtomA)(JNIEnv *env, jclass that, jbyteArray arg0);
SWTEXPORT jint JNICALL OS_NATIVE(GlobalAddAtomW)(JNIEnv *env, jclass that, jcharArray arg0);
SWTEXPORT jint JNICALL OS_NATIVE(GlobalAlloc)(JNIEnv *env, jclass that, jint arg0, jint arg1);
SWTEXPORT jint JNICALL OS_NATIVE(GlobalFree)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jint JNICALL OS_NATIVE(GlobalLock)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jint JNICALL OS_NATIVE(GlobalSize)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(GlobalUnlock)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(GradientFill)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3, jint arg4, jint arg5);
SWTEXPORT jint JNICALL OS_NATIVE(HeapAlloc)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2);
SWTEXPORT jboolean JNICALL OS_NATIVE(HeapFree)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2);
SWTEXPORT jboolean JNICALL OS_NATIVE(HideCaret)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jint JNICALL OS_NATIVE(IIDFromString)(JNIEnv *env, jclass that, jcharArray arg0, jbyteArray arg1);
SWTEXPORT jint JNICALL OS_NATIVE(ImageList_1Add)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2);
SWTEXPORT jint JNICALL OS_NATIVE(ImageList_1AddMasked)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2);
SWTEXPORT jint JNICALL OS_NATIVE(ImageList_1Create)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3, jint arg4);
SWTEXPORT jboolean JNICALL OS_NATIVE(ImageList_1Destroy)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jint JNICALL OS_NATIVE(ImageList_1GetIcon)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2);
SWTEXPORT jboolean JNICALL OS_NATIVE(ImageList_1GetIconSize)(JNIEnv *env, jclass that, jint arg0, jintArray arg1, jintArray arg2);
SWTEXPORT jint JNICALL OS_NATIVE(ImageList_1GetImageCount)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(ImageList_1Remove)(JNIEnv *env, jclass that, jint arg0, jint arg1);
SWTEXPORT jboolean JNICALL OS_NATIVE(ImageList_1Replace)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3);
SWTEXPORT jint JNICALL OS_NATIVE(ImageList_1ReplaceIcon)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2);
SWTEXPORT jboolean JNICALL OS_NATIVE(ImageList_1SetIconSize)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2);
SWTEXPORT jint JNICALL OS_NATIVE(ImmAssociateContext)(JNIEnv *env, jclass that, jint arg0, jint arg1);
SWTEXPORT jint JNICALL OS_NATIVE(ImmCreateContext)(JNIEnv *env, jclass that);
SWTEXPORT jboolean JNICALL OS_NATIVE(ImmDestroyContext)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(ImmDisableTextFrameService)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(ImmGetCompositionFontA)(JNIEnv *env, jclass that, jint arg0, jobject arg1);
SWTEXPORT jboolean JNICALL OS_NATIVE(ImmGetCompositionFontW)(JNIEnv *env, jclass that, jint arg0, jobject arg1);
SWTEXPORT jint JNICALL OS_NATIVE(ImmGetCompositionStringA)(JNIEnv *env, jclass that, jint arg0, jint arg1, jbyteArray arg2, jint arg3);
SWTEXPORT jint JNICALL OS_NATIVE(ImmGetCompositionStringW)(JNIEnv *env, jclass that, jint arg0, jint arg1, jcharArray arg2, jint arg3);
SWTEXPORT jint JNICALL OS_NATIVE(ImmGetContext)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(ImmGetConversionStatus)(JNIEnv *env, jclass that, jint arg0, jintArray arg1, jintArray arg2);
SWTEXPORT jint JNICALL OS_NATIVE(ImmGetDefaultIMEWnd)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(ImmGetOpenStatus)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(ImmReleaseContext)(JNIEnv *env, jclass that, jint arg0, jint arg1);
SWTEXPORT jboolean JNICALL OS_NATIVE(ImmSetCompositionFontA)(JNIEnv *env, jclass that, jint arg0, jobject arg1);
SWTEXPORT jboolean JNICALL OS_NATIVE(ImmSetCompositionFontW)(JNIEnv *env, jclass that, jint arg0, jobject arg1);
SWTEXPORT jboolean JNICALL OS_NATIVE(ImmSetCompositionWindow)(JNIEnv *env, jclass that, jint arg0, jobject arg1);
SWTEXPORT jboolean JNICALL OS_NATIVE(ImmSetConversionStatus)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2);
SWTEXPORT jboolean JNICALL OS_NATIVE(ImmSetOpenStatus)(JNIEnv *env, jclass that, jint arg0, jboolean arg1);
SWTEXPORT void JNICALL OS_NATIVE(InitCommonControls)(JNIEnv *env, jclass that);
SWTEXPORT jboolean JNICALL OS_NATIVE(InitCommonControlsEx)(JNIEnv *env, jclass that, jobject arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(InsertMenuA)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3, jbyteArray arg4);
SWTEXPORT jboolean JNICALL OS_NATIVE(InsertMenuItemA)(JNIEnv *env, jclass that, jint arg0, jint arg1, jboolean arg2, jobject arg3);
SWTEXPORT jboolean JNICALL OS_NATIVE(InsertMenuItemW)(JNIEnv *env, jclass that, jint arg0, jint arg1, jboolean arg2, jobject arg3);
SWTEXPORT jboolean JNICALL OS_NATIVE(InsertMenuW)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3, jcharArray arg4);
SWTEXPORT jint JNICALL OS_NATIVE(IntersectClipRect)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3, jint arg4);
SWTEXPORT jboolean JNICALL OS_NATIVE(IntersectRect)(JNIEnv *env, jclass that, jobject arg0, jobject arg1, jobject arg2);
SWTEXPORT jboolean JNICALL OS_NATIVE(InvalidateRect)(JNIEnv *env, jclass that, jint arg0, jobject arg1, jboolean arg2);
SWTEXPORT jboolean JNICALL OS_NATIVE(InvalidateRgn)(JNIEnv *env, jclass that, jint arg0, jint arg1, jboolean arg2);
SWTEXPORT jboolean JNICALL OS_NATIVE(IsAppThemed)(JNIEnv *env, jclass that);
SWTEXPORT jboolean JNICALL OS_NATIVE(IsDBCSLeadByte)(JNIEnv *env, jclass that, jbyte arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(IsHungAppWindow)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(IsIconic)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(IsWindowEnabled)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(IsWindowVisible)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(IsZoomed)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(KillTimer)(JNIEnv *env, jclass that, jint arg0, jint arg1);
SWTEXPORT jboolean JNICALL OS_NATIVE(LineTo)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2);
SWTEXPORT jint JNICALL OS_NATIVE(LoadBitmapA)(JNIEnv *env, jclass that, jint arg0, jint arg1);
SWTEXPORT jint JNICALL OS_NATIVE(LoadBitmapW)(JNIEnv *env, jclass that, jint arg0, jint arg1);
SWTEXPORT jint JNICALL OS_NATIVE(LoadCursorA)(JNIEnv *env, jclass that, jint arg0, jint arg1);
SWTEXPORT jint JNICALL OS_NATIVE(LoadCursorW)(JNIEnv *env, jclass that, jint arg0, jint arg1);
SWTEXPORT jint JNICALL OS_NATIVE(LoadIconA)(JNIEnv *env, jclass that, jint arg0, jint arg1);
SWTEXPORT jint JNICALL OS_NATIVE(LoadIconW)(JNIEnv *env, jclass that, jint arg0, jint arg1);
SWTEXPORT jint JNICALL OS_NATIVE(LoadImageA__IIIIII)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3, jint arg4, jint arg5);
SWTEXPORT jint JNICALL OS_NATIVE(LoadImageA__I_3BIIII)(JNIEnv *env, jclass that, jint arg0, jbyteArray arg1, jint arg2, jint arg3, jint arg4, jint arg5);
SWTEXPORT jint JNICALL OS_NATIVE(LoadImageW__IIIIII)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3, jint arg4, jint arg5);
SWTEXPORT jint JNICALL OS_NATIVE(LoadImageW__I_3CIIII)(JNIEnv *env, jclass that, jint arg0, jcharArray arg1, jint arg2, jint arg3, jint arg4, jint arg5);
SWTEXPORT jint JNICALL OS_NATIVE(LoadLibraryA)(JNIEnv *env, jclass that, jbyteArray arg0);
SWTEXPORT jint JNICALL OS_NATIVE(LoadLibraryW)(JNIEnv *env, jclass that, jcharArray arg0);
SWTEXPORT jint JNICALL OS_NATIVE(LoadStringA)(JNIEnv *env, jclass that, jint arg0, jint arg1, jbyteArray arg2, jint arg3);
SWTEXPORT jint JNICALL OS_NATIVE(LoadStringW)(JNIEnv *env, jclass that, jint arg0, jint arg1, jcharArray arg2, jint arg3);
SWTEXPORT jint JNICALL OS_NATIVE(LocalFree)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jint JNICALL OS_NATIVE(MapVirtualKeyA)(JNIEnv *env, jclass that, jint arg0, jint arg1);
SWTEXPORT jint JNICALL OS_NATIVE(MapVirtualKeyW)(JNIEnv *env, jclass that, jint arg0, jint arg1);
SWTEXPORT jint JNICALL OS_NATIVE(MapWindowPoints__IILorg_eclipse_swt_internal_win32_POINT_2I)(JNIEnv *env, jclass that, jint arg0, jint arg1, jobject arg2, jint arg3);
SWTEXPORT jint JNICALL OS_NATIVE(MapWindowPoints__IILorg_eclipse_swt_internal_win32_RECT_2I)(JNIEnv *env, jclass that, jint arg0, jint arg1, jobject arg2, jint arg3);
SWTEXPORT jboolean JNICALL OS_NATIVE(MessageBeep)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jint JNICALL OS_NATIVE(MessageBoxA)(JNIEnv *env, jclass that, jint arg0, jbyteArray arg1, jbyteArray arg2, jint arg3);
SWTEXPORT jint JNICALL OS_NATIVE(MessageBoxW)(JNIEnv *env, jclass that, jint arg0, jcharArray arg1, jcharArray arg2, jint arg3);
SWTEXPORT jint JNICALL OS_NATIVE(MonitorFromWindow)(JNIEnv *env, jclass that, jint arg0, jint arg1);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__ILorg_eclipse_swt_internal_win32_DROPFILES_2I)(JNIEnv *env, jclass that, jint arg0, jobject arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__ILorg_eclipse_swt_internal_win32_GRADIENT_1RECT_2I)(JNIEnv *env, jclass that, jint arg0, jobject arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__ILorg_eclipse_swt_internal_win32_KEYBDINPUT_2I)(JNIEnv *env, jclass that, jint arg0, jobject arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__ILorg_eclipse_swt_internal_win32_LOGFONTA_2I)(JNIEnv *env, jclass that, jint arg0, jobject arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__ILorg_eclipse_swt_internal_win32_LOGFONTW_2I)(JNIEnv *env, jclass that, jint arg0, jobject arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__ILorg_eclipse_swt_internal_win32_MEASUREITEMSTRUCT_2I)(JNIEnv *env, jclass that, jint arg0, jobject arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__ILorg_eclipse_swt_internal_win32_MINMAXINFO_2I)(JNIEnv *env, jclass that, jint arg0, jobject arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__ILorg_eclipse_swt_internal_win32_MOUSEINPUT_2I)(JNIEnv *env, jclass that, jint arg0, jobject arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__ILorg_eclipse_swt_internal_win32_MSG_2I)(JNIEnv *env, jclass that, jint arg0, jobject arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__ILorg_eclipse_swt_internal_win32_NMLVCUSTOMDRAW_2I)(JNIEnv *env, jclass that, jint arg0, jobject arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__ILorg_eclipse_swt_internal_win32_NMLVDISPINFO_2I)(JNIEnv *env, jclass that, jint arg0, jobject arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__ILorg_eclipse_swt_internal_win32_NMTTDISPINFOA_2I)(JNIEnv *env, jclass that, jint arg0, jobject arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__ILorg_eclipse_swt_internal_win32_NMTTDISPINFOW_2I)(JNIEnv *env, jclass that, jint arg0, jobject arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__ILorg_eclipse_swt_internal_win32_NMTVCUSTOMDRAW_2I)(JNIEnv *env, jclass that, jint arg0, jobject arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__ILorg_eclipse_swt_internal_win32_NMTVDISPINFO_2I)(JNIEnv *env, jclass that, jint arg0, jobject arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__ILorg_eclipse_swt_internal_win32_RECT_2I)(JNIEnv *env, jclass that, jint arg0, jobject arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__ILorg_eclipse_swt_internal_win32_TRIVERTEX_2I)(JNIEnv *env, jclass that, jint arg0, jobject arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__ILorg_eclipse_swt_internal_win32_UDACCEL_2I)(JNIEnv *env, jclass that, jint arg0, jobject arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__ILorg_eclipse_swt_internal_win32_WINDOWPOS_2I)(JNIEnv *env, jclass that, jint arg0, jobject arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__I_3BI)(JNIEnv *env, jclass that, jint arg0, jbyteArray arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__I_3CI)(JNIEnv *env, jclass that, jint arg0, jcharArray arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__I_3DI)(JNIEnv *env, jclass that, jint arg0, jdoubleArray arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__I_3FI)(JNIEnv *env, jclass that, jint arg0, jfloatArray arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__I_3II)(JNIEnv *env, jclass that, jint arg0, jintArray arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__I_3SI)(JNIEnv *env, jclass that, jint arg0, jshortArray arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_BITMAPINFOHEADER_2_3BI)(JNIEnv *env, jclass that, jobject arg0, jbyteArray arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_DRAWITEMSTRUCT_2II)(JNIEnv *env, jclass that, jobject arg0, jint arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_EXTLOGPEN_2II)(JNIEnv *env, jclass that, jobject arg0, jint arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_HDITEM_2II)(JNIEnv *env, jclass that, jobject arg0, jint arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_HELPINFO_2II)(JNIEnv *env, jclass that, jobject arg0, jint arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_LOGFONTA_2II)(JNIEnv *env, jclass that, jobject arg0, jint arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_LOGFONTW_2II)(JNIEnv *env, jclass that, jobject arg0, jint arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_MEASUREITEMSTRUCT_2II)(JNIEnv *env, jclass that, jobject arg0, jint arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_MINMAXINFO_2II)(JNIEnv *env, jclass that, jobject arg0, jint arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_MSG_2II)(JNIEnv *env, jclass that, jobject arg0, jint arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_NMCUSTOMDRAW_2II)(JNIEnv *env, jclass that, jobject arg0, jint arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_NMHDR_2II)(JNIEnv *env, jclass that, jobject arg0, jint arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_NMHEADER_2II)(JNIEnv *env, jclass that, jobject arg0, jint arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_NMLINK_2II)(JNIEnv *env, jclass that, jobject arg0, jint arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_NMLISTVIEW_2II)(JNIEnv *env, jclass that, jobject arg0, jint arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_NMLVCUSTOMDRAW_2II)(JNIEnv *env, jclass that, jobject arg0, jint arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_NMLVDISPINFO_2II)(JNIEnv *env, jclass that, jobject arg0, jint arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_NMLVFINDITEM_2II)(JNIEnv *env, jclass that, jobject arg0, jint arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_NMREBARCHEVRON_2II)(JNIEnv *env, jclass that, jobject arg0, jint arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_NMREBARCHILDSIZE_2II)(JNIEnv *env, jclass that, jobject arg0, jint arg1, jint arg2);
#ifdef WIN32_PLATFORM_PSPC
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_NMRGINFO_2II)(JNIEnv *env, jclass that, jobject arg0, jint arg1, jint arg2);
#endif
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_NMTBHOTITEM_2II)(JNIEnv *env, jclass that, jobject arg0, jint arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_NMTOOLBAR_2II)(JNIEnv *env, jclass that, jobject arg0, jint arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_NMTTDISPINFOA_2II)(JNIEnv *env, jclass that, jobject arg0, jint arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_NMTTDISPINFOW_2II)(JNIEnv *env, jclass that, jobject arg0, jint arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_NMTVCUSTOMDRAW_2II)(JNIEnv *env, jclass that, jobject arg0, jint arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_NMTVDISPINFO_2II)(JNIEnv *env, jclass that, jobject arg0, jint arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_NMUPDOWN_2II)(JNIEnv *env, jclass that, jobject arg0, jint arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_POINT_2II)(JNIEnv *env, jclass that, jobject arg0, jint arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_SCRIPT_1ITEM_2II)(JNIEnv *env, jclass that, jobject arg0, jint arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_SCRIPT_1LOGATTR_2II)(JNIEnv *env, jclass that, jobject arg0, jint arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_SCRIPT_1PROPERTIES_2II)(JNIEnv *env, jclass that, jobject arg0, jint arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_TEXTMETRICA_2II)(JNIEnv *env, jclass that, jobject arg0, jint arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_TEXTMETRICW_2II)(JNIEnv *env, jclass that, jobject arg0, jint arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_TVITEM_2II)(JNIEnv *env, jclass that, jobject arg0, jint arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_UDACCEL_2II)(JNIEnv *env, jclass that, jobject arg0, jint arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_WINDOWPOS_2II)(JNIEnv *env, jclass that, jobject arg0, jint arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory___3BII)(JNIEnv *env, jclass that, jbyteArray arg0, jint arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory___3BLorg_eclipse_swt_internal_win32_ACCEL_2I)(JNIEnv *env, jclass that, jbyteArray arg0, jobject arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory___3BLorg_eclipse_swt_internal_win32_BITMAPINFOHEADER_2I)(JNIEnv *env, jclass that, jbyteArray arg0, jobject arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory___3CII)(JNIEnv *env, jclass that, jcharArray arg0, jint arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory___3DII)(JNIEnv *env, jclass that, jdoubleArray arg0, jint arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory___3FII)(JNIEnv *env, jclass that, jfloatArray arg0, jint arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory___3III)(JNIEnv *env, jclass that, jintArray arg0, jint arg1, jint arg2);
SWTEXPORT void JNICALL OS_NATIVE(MoveMemory___3SII)(JNIEnv *env, jclass that, jshortArray arg0, jint arg1, jint arg2);
SWTEXPORT jboolean JNICALL OS_NATIVE(MoveToEx)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3);
SWTEXPORT jint JNICALL OS_NATIVE(MsgWaitForMultipleObjectsEx)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3, jint arg4);
SWTEXPORT jint JNICALL OS_NATIVE(MultiByteToWideChar__IIII_3CI)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3, jcharArray arg4, jint arg5);
SWTEXPORT jint JNICALL OS_NATIVE(MultiByteToWideChar__II_3BI_3CI)(JNIEnv *env, jclass that, jint arg0, jint arg1, jbyteArray arg2, jint arg3, jcharArray arg4, jint arg5);
SWTEXPORT void JNICALL OS_NATIVE(NotifyWinEvent)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3);
SWTEXPORT jboolean JNICALL OS_NATIVE(OffsetRect)(JNIEnv *env, jclass that, jobject arg0, jint arg1, jint arg2);
SWTEXPORT jint JNICALL OS_NATIVE(OffsetRgn)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2);
SWTEXPORT jint JNICALL OS_NATIVE(OleInitialize)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT void JNICALL OS_NATIVE(OleUninitialize)(JNIEnv *env, jclass that);
SWTEXPORT jboolean JNICALL OS_NATIVE(OpenClipboard)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jint JNICALL OS_NATIVE(OpenThemeData)(JNIEnv *env, jclass that, jint arg0, jcharArray arg1);
SWTEXPORT jshort JNICALL OS_NATIVE(PRIMARYLANGID)(JNIEnv *env, jclass that, jshort arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(PatBlt)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3, jint arg4, jint arg5);
SWTEXPORT jboolean JNICALL OS_NATIVE(PeekMessageA)(JNIEnv *env, jclass that, jobject arg0, jint arg1, jint arg2, jint arg3, jint arg4);
SWTEXPORT jboolean JNICALL OS_NATIVE(PeekMessageW)(JNIEnv *env, jclass that, jobject arg0, jint arg1, jint arg2, jint arg3, jint arg4);
SWTEXPORT jboolean JNICALL OS_NATIVE(Pie)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3, jint arg4, jint arg5, jint arg6, jint arg7, jint arg8);
SWTEXPORT jboolean JNICALL OS_NATIVE(Polygon)(JNIEnv *env, jclass that, jint arg0, jintArray arg1, jint arg2);
SWTEXPORT jboolean JNICALL OS_NATIVE(Polyline)(JNIEnv *env, jclass that, jint arg0, jintArray arg1, jint arg2);
SWTEXPORT jboolean JNICALL OS_NATIVE(PostMessageA)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3);
SWTEXPORT jboolean JNICALL OS_NATIVE(PostMessageW)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3);
SWTEXPORT jboolean JNICALL OS_NATIVE(PostThreadMessageA)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3);
SWTEXPORT jboolean JNICALL OS_NATIVE(PostThreadMessageW)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3);
SWTEXPORT jboolean JNICALL OS_NATIVE(PrintDlgA)(JNIEnv *env, jclass that, jobject arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(PrintDlgW)(JNIEnv *env, jclass that, jobject arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(PtInRect)(JNIEnv *env, jclass that, jobject arg0, jobject arg1);
SWTEXPORT jboolean JNICALL OS_NATIVE(PtInRegion)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2);
SWTEXPORT jint JNICALL OS_NATIVE(RealizePalette)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(RectInRegion)(JNIEnv *env, jclass that, jint arg0, jobject arg1);
SWTEXPORT jboolean JNICALL OS_NATIVE(Rectangle)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3, jint arg4);
SWTEXPORT jboolean JNICALL OS_NATIVE(RedrawWindow)(JNIEnv *env, jclass that, jint arg0, jobject arg1, jint arg2, jint arg3);
SWTEXPORT jint JNICALL OS_NATIVE(RegCloseKey)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jint JNICALL OS_NATIVE(RegEnumKeyExA)(JNIEnv *env, jclass that, jint arg0, jint arg1, jbyteArray arg2, jintArray arg3, jintArray arg4, jbyteArray arg5, jintArray arg6, jobject arg7);
SWTEXPORT jint JNICALL OS_NATIVE(RegEnumKeyExW)(JNIEnv *env, jclass that, jint arg0, jint arg1, jcharArray arg2, jintArray arg3, jintArray arg4, jcharArray arg5, jintArray arg6, jobject arg7);
SWTEXPORT jint JNICALL OS_NATIVE(RegOpenKeyExA)(JNIEnv *env, jclass that, jint arg0, jbyteArray arg1, jint arg2, jint arg3, jintArray arg4);
SWTEXPORT jint JNICALL OS_NATIVE(RegOpenKeyExW)(JNIEnv *env, jclass that, jint arg0, jcharArray arg1, jint arg2, jint arg3, jintArray arg4);
SWTEXPORT jint JNICALL OS_NATIVE(RegQueryInfoKeyA)(JNIEnv *env, jclass that, jint arg0, jint arg1, jintArray arg2, jint arg3, jintArray arg4, jintArray arg5, jintArray arg6, jintArray arg7, jintArray arg8, jintArray arg9, jintArray arg10, jint arg11);
SWTEXPORT jint JNICALL OS_NATIVE(RegQueryInfoKeyW)(JNIEnv *env, jclass that, jint arg0, jint arg1, jintArray arg2, jint arg3, jintArray arg4, jintArray arg5, jintArray arg6, jintArray arg7, jintArray arg8, jintArray arg9, jintArray arg10, jint arg11);
SWTEXPORT jint JNICALL OS_NATIVE(RegQueryValueExA__I_3BI_3I_3B_3I)(JNIEnv *env, jclass that, jint arg0, jbyteArray arg1, jint arg2, jintArray arg3, jbyteArray arg4, jintArray arg5);
SWTEXPORT jint JNICALL OS_NATIVE(RegQueryValueExA__I_3BI_3I_3I_3I)(JNIEnv *env, jclass that, jint arg0, jbyteArray arg1, jint arg2, jintArray arg3, jintArray arg4, jintArray arg5);
SWTEXPORT jint JNICALL OS_NATIVE(RegQueryValueExW__I_3CI_3I_3C_3I)(JNIEnv *env, jclass that, jint arg0, jcharArray arg1, jint arg2, jintArray arg3, jcharArray arg4, jintArray arg5);
SWTEXPORT jint JNICALL OS_NATIVE(RegQueryValueExW__I_3CI_3I_3I_3I)(JNIEnv *env, jclass that, jint arg0, jcharArray arg1, jint arg2, jintArray arg3, jintArray arg4, jintArray arg5);
SWTEXPORT jint JNICALL OS_NATIVE(RegisterClassA)(JNIEnv *env, jclass that, jobject arg0);
SWTEXPORT jint JNICALL OS_NATIVE(RegisterClassW)(JNIEnv *env, jclass that, jobject arg0);
SWTEXPORT jint JNICALL OS_NATIVE(RegisterClipboardFormatA)(JNIEnv *env, jclass that, jbyteArray arg0);
SWTEXPORT jint JNICALL OS_NATIVE(RegisterClipboardFormatW)(JNIEnv *env, jclass that, jcharArray arg0);
SWTEXPORT jint JNICALL OS_NATIVE(RegisterWindowMessageA)(JNIEnv *env, jclass that, jbyteArray arg0);
SWTEXPORT jint JNICALL OS_NATIVE(RegisterWindowMessageW)(JNIEnv *env, jclass that, jcharArray arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(ReleaseCapture)(JNIEnv *env, jclass that);
SWTEXPORT jint JNICALL OS_NATIVE(ReleaseDC)(JNIEnv *env, jclass that, jint arg0, jint arg1);
SWTEXPORT jboolean JNICALL OS_NATIVE(RemoveMenu)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2);
SWTEXPORT jint JNICALL OS_NATIVE(RemovePropA)(JNIEnv *env, jclass that, jint arg0, jint arg1);
SWTEXPORT jint JNICALL OS_NATIVE(RemovePropW)(JNIEnv *env, jclass that, jint arg0, jint arg1);
SWTEXPORT jboolean JNICALL OS_NATIVE(RestoreDC)(JNIEnv *env, jclass that, jint arg0, jint arg1);
SWTEXPORT jboolean JNICALL OS_NATIVE(RoundRect)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3, jint arg4, jint arg5, jint arg6);
SWTEXPORT jint JNICALL OS_NATIVE(SHBrowseForFolderA)(JNIEnv *env, jclass that, jobject arg0);
SWTEXPORT jint JNICALL OS_NATIVE(SHBrowseForFolderW)(JNIEnv *env, jclass that, jobject arg0);
#if defined(WIN32_PLATFORM_PSPC) || defined(WIN32_PLATFORM_WFSP)
SWTEXPORT jboolean JNICALL OS_NATIVE(SHCreateMenuBar)(JNIEnv *env, jclass that, jobject arg0);
#endif
SWTEXPORT jint JNICALL OS_NATIVE(SHGetMalloc)(JNIEnv *env, jclass that, jintArray arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(SHGetPathFromIDListA)(JNIEnv *env, jclass that, jint arg0, jbyteArray arg1);
SWTEXPORT jboolean JNICALL OS_NATIVE(SHGetPathFromIDListW)(JNIEnv *env, jclass that, jint arg0, jcharArray arg1);
#ifdef WIN32_PLATFORM_PSPC
SWTEXPORT jboolean JNICALL OS_NATIVE(SHHandleWMSettingChange)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jobject arg3);
SWTEXPORT jint JNICALL OS_NATIVE(SHRecognizeGesture)(JNIEnv *env, jclass that, jobject arg0);
#endif
#ifdef WIN32_PLATFORM_WFSP
SWTEXPORT void JNICALL OS_NATIVE(SHSendBackToFocusWindow)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2);
#endif
#if defined(WIN32_PLATFORM_PSPC) || defined(WIN32_PLATFORM_WFSP)
SWTEXPORT jboolean JNICALL OS_NATIVE(SHSetAppKeyWndAssoc)(JNIEnv *env, jclass that, jbyte arg0, jint arg1);
#endif
#ifdef WIN32_PLATFORM_PSPC
SWTEXPORT jboolean JNICALL OS_NATIVE(SHSipPreference)(JNIEnv *env, jclass that, jint arg0, jint arg1);
#endif
SWTEXPORT jint JNICALL OS_NATIVE(SaveDC)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(ScreenToClient)(JNIEnv *env, jclass that, jint arg0, jobject arg1);
SWTEXPORT jint JNICALL OS_NATIVE(ScriptBreak)(JNIEnv *env, jclass that, jcharArray arg0, jint arg1, jobject arg2, jint arg3);
SWTEXPORT jint JNICALL OS_NATIVE(ScriptCPtoX)(JNIEnv *env, jclass that, jint arg0, jboolean arg1, jint arg2, jint arg3, jint arg4, jint arg5, jint arg6, jobject arg7, jintArray arg8);
SWTEXPORT jint JNICALL OS_NATIVE(ScriptCacheGetHeight)(JNIEnv *env, jclass that, jint arg0, jint arg1, jintArray arg2);
SWTEXPORT jint JNICALL OS_NATIVE(ScriptFreeCache)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jint JNICALL OS_NATIVE(ScriptGetFontProperties)(JNIEnv *env, jclass that, jint arg0, jint arg1, jobject arg2);
SWTEXPORT jint JNICALL OS_NATIVE(ScriptGetLogicalWidths)(JNIEnv *env, jclass that, jobject arg0, jint arg1, jint arg2, jint arg3, jint arg4, jint arg5, jintArray arg6);
SWTEXPORT jint JNICALL OS_NATIVE(ScriptGetProperties)(JNIEnv *env, jclass that, jintArray arg0, jintArray arg1);
SWTEXPORT jint JNICALL OS_NATIVE(ScriptItemize)(JNIEnv *env, jclass that, jcharArray arg0, jint arg1, jint arg2, jobject arg3, jobject arg4, jint arg5, jintArray arg6);
SWTEXPORT jint JNICALL OS_NATIVE(ScriptLayout)(JNIEnv *env, jclass that, jint arg0, jbyteArray arg1, jintArray arg2, jintArray arg3);
SWTEXPORT jint JNICALL OS_NATIVE(ScriptPlace)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3, jint arg4, jobject arg5, jint arg6, jint arg7, jintArray arg8);
SWTEXPORT jint JNICALL OS_NATIVE(ScriptShape)(JNIEnv *env, jclass that, jint arg0, jint arg1, jcharArray arg2, jint arg3, jint arg4, jobject arg5, jint arg6, jint arg7, jint arg8, jintArray arg9);
SWTEXPORT jint JNICALL OS_NATIVE(ScriptTextOut)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3, jint arg4, jobject arg5, jobject arg6, jint arg7, jint arg8, jint arg9, jint arg10, jint arg11, jintArray arg12, jint arg13);
SWTEXPORT jint JNICALL OS_NATIVE(ScriptXtoCP)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3, jint arg4, jint arg5, jobject arg6, jintArray arg7, jintArray arg8);
SWTEXPORT jint JNICALL OS_NATIVE(ScrollWindowEx)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jobject arg3, jobject arg4, jint arg5, jobject arg6, jint arg7);
SWTEXPORT jint JNICALL OS_NATIVE(SelectClipRgn)(JNIEnv *env, jclass that, jint arg0, jint arg1);
SWTEXPORT jint JNICALL OS_NATIVE(SelectObject)(JNIEnv *env, jclass that, jint arg0, jint arg1);
SWTEXPORT jint JNICALL OS_NATIVE(SelectPalette)(JNIEnv *env, jclass that, jint arg0, jint arg1, jboolean arg2);
SWTEXPORT jint JNICALL OS_NATIVE(SendInput)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2);
SWTEXPORT jint JNICALL OS_NATIVE(SendMessageA__IIII)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3);
SWTEXPORT jint JNICALL OS_NATIVE(SendMessageA__IIILorg_eclipse_swt_internal_win32_BUTTON_1IMAGELIST_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jobject arg3);
SWTEXPORT jint JNICALL OS_NATIVE(SendMessageA__IIILorg_eclipse_swt_internal_win32_HDITEM_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jobject arg3);
SWTEXPORT jint JNICALL OS_NATIVE(SendMessageA__IIILorg_eclipse_swt_internal_win32_HDLAYOUT_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jobject arg3);
SWTEXPORT jint JNICALL OS_NATIVE(SendMessageA__IIILorg_eclipse_swt_internal_win32_LITEM_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jobject arg3);
SWTEXPORT jint JNICALL OS_NATIVE(SendMessageA__IIILorg_eclipse_swt_internal_win32_LVCOLUMN_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jobject arg3);
SWTEXPORT jint JNICALL OS_NATIVE(SendMessageA__IIILorg_eclipse_swt_internal_win32_LVHITTESTINFO_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jobject arg3);
SWTEXPORT jint JNICALL OS_NATIVE(SendMessageA__IIILorg_eclipse_swt_internal_win32_LVITEM_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jobject arg3);
SWTEXPORT jint JNICALL OS_NATIVE(SendMessageA__IIILorg_eclipse_swt_internal_win32_MARGINS_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jobject arg3);
SWTEXPORT jint JNICALL OS_NATIVE(SendMessageA__IIILorg_eclipse_swt_internal_win32_REBARBANDINFO_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jobject arg3);
SWTEXPORT jint JNICALL OS_NATIVE(SendMessageA__IIILorg_eclipse_swt_internal_win32_RECT_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jobject arg3);
SWTEXPORT jint JNICALL OS_NATIVE(SendMessageA__IIILorg_eclipse_swt_internal_win32_SIZE_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jobject arg3);
SWTEXPORT jint JNICALL OS_NATIVE(SendMessageA__IIILorg_eclipse_swt_internal_win32_TBBUTTONINFO_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jobject arg3);
SWTEXPORT jint JNICALL OS_NATIVE(SendMessageA__IIILorg_eclipse_swt_internal_win32_TBBUTTON_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jobject arg3);
SWTEXPORT jint JNICALL OS_NATIVE(SendMessageA__IIILorg_eclipse_swt_internal_win32_TCITEM_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jobject arg3);
SWTEXPORT jint JNICALL OS_NATIVE(SendMessageA__IIILorg_eclipse_swt_internal_win32_TOOLINFO_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jobject arg3);
SWTEXPORT jint JNICALL OS_NATIVE(SendMessageA__IIILorg_eclipse_swt_internal_win32_TVHITTESTINFO_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jobject arg3);
SWTEXPORT jint JNICALL OS_NATIVE(SendMessageA__IIILorg_eclipse_swt_internal_win32_TVINSERTSTRUCT_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jobject arg3);
SWTEXPORT jint JNICALL OS_NATIVE(SendMessageA__IIILorg_eclipse_swt_internal_win32_TVITEM_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jobject arg3);
SWTEXPORT jint JNICALL OS_NATIVE(SendMessageA__IIILorg_eclipse_swt_internal_win32_UDACCEL_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jobject arg3);
SWTEXPORT jint JNICALL OS_NATIVE(SendMessageA__III_3B)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jbyteArray arg3);
SWTEXPORT jint JNICALL OS_NATIVE(SendMessageA__III_3I)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jintArray arg3);
SWTEXPORT jint JNICALL OS_NATIVE(SendMessageA__III_3S)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jshortArray arg3);
SWTEXPORT jint JNICALL OS_NATIVE(SendMessageA__II_3II)(JNIEnv *env, jclass that, jint arg0, jint arg1, jintArray arg2, jint arg3);
SWTEXPORT jint JNICALL OS_NATIVE(SendMessageA__II_3I_3I)(JNIEnv *env, jclass that, jint arg0, jint arg1, jintArray arg2, jintArray arg3);
SWTEXPORT jint JNICALL OS_NATIVE(SendMessageW__IIII)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3);
SWTEXPORT jint JNICALL OS_NATIVE(SendMessageW__IIILorg_eclipse_swt_internal_win32_BUTTON_1IMAGELIST_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jobject arg3);
SWTEXPORT jint JNICALL OS_NATIVE(SendMessageW__IIILorg_eclipse_swt_internal_win32_HDITEM_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jobject arg3);
SWTEXPORT jint JNICALL OS_NATIVE(SendMessageW__IIILorg_eclipse_swt_internal_win32_HDLAYOUT_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jobject arg3);
SWTEXPORT jint JNICALL OS_NATIVE(SendMessageW__IIILorg_eclipse_swt_internal_win32_LITEM_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jobject arg3);
SWTEXPORT jint JNICALL OS_NATIVE(SendMessageW__IIILorg_eclipse_swt_internal_win32_LVCOLUMN_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jobject arg3);
SWTEXPORT jint JNICALL OS_NATIVE(SendMessageW__IIILorg_eclipse_swt_internal_win32_LVHITTESTINFO_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jobject arg3);
SWTEXPORT jint JNICALL OS_NATIVE(SendMessageW__IIILorg_eclipse_swt_internal_win32_LVITEM_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jobject arg3);
SWTEXPORT jint JNICALL OS_NATIVE(SendMessageW__IIILorg_eclipse_swt_internal_win32_MARGINS_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jobject arg3);
SWTEXPORT jint JNICALL OS_NATIVE(SendMessageW__IIILorg_eclipse_swt_internal_win32_REBARBANDINFO_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jobject arg3);
SWTEXPORT jint JNICALL OS_NATIVE(SendMessageW__IIILorg_eclipse_swt_internal_win32_RECT_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jobject arg3);
SWTEXPORT jint JNICALL OS_NATIVE(SendMessageW__IIILorg_eclipse_swt_internal_win32_SIZE_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jobject arg3);
SWTEXPORT jint JNICALL OS_NATIVE(SendMessageW__IIILorg_eclipse_swt_internal_win32_TBBUTTONINFO_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jobject arg3);
SWTEXPORT jint JNICALL OS_NATIVE(SendMessageW__IIILorg_eclipse_swt_internal_win32_TBBUTTON_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jobject arg3);
SWTEXPORT jint JNICALL OS_NATIVE(SendMessageW__IIILorg_eclipse_swt_internal_win32_TCITEM_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jobject arg3);
SWTEXPORT jint JNICALL OS_NATIVE(SendMessageW__IIILorg_eclipse_swt_internal_win32_TOOLINFO_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jobject arg3);
SWTEXPORT jint JNICALL OS_NATIVE(SendMessageW__IIILorg_eclipse_swt_internal_win32_TVHITTESTINFO_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jobject arg3);
SWTEXPORT jint JNICALL OS_NATIVE(SendMessageW__IIILorg_eclipse_swt_internal_win32_TVINSERTSTRUCT_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jobject arg3);
SWTEXPORT jint JNICALL OS_NATIVE(SendMessageW__IIILorg_eclipse_swt_internal_win32_TVITEM_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jobject arg3);
SWTEXPORT jint JNICALL OS_NATIVE(SendMessageW__IIILorg_eclipse_swt_internal_win32_UDACCEL_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jobject arg3);
SWTEXPORT jint JNICALL OS_NATIVE(SendMessageW__III_3C)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jcharArray arg3);
SWTEXPORT jint JNICALL OS_NATIVE(SendMessageW__III_3I)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jintArray arg3);
SWTEXPORT jint JNICALL OS_NATIVE(SendMessageW__III_3S)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jshortArray arg3);
SWTEXPORT jint JNICALL OS_NATIVE(SendMessageW__II_3II)(JNIEnv *env, jclass that, jint arg0, jint arg1, jintArray arg2, jint arg3);
SWTEXPORT jint JNICALL OS_NATIVE(SetActiveWindow)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jint JNICALL OS_NATIVE(SetBkColor)(JNIEnv *env, jclass that, jint arg0, jint arg1);
SWTEXPORT jint JNICALL OS_NATIVE(SetBkMode)(JNIEnv *env, jclass that, jint arg0, jint arg1);
SWTEXPORT jint JNICALL OS_NATIVE(SetCapture)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(SetCaretPos)(JNIEnv *env, jclass that, jint arg0, jint arg1);
SWTEXPORT jint JNICALL OS_NATIVE(SetClipboardData)(JNIEnv *env, jclass that, jint arg0, jint arg1);
SWTEXPORT jint JNICALL OS_NATIVE(SetCursor)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(SetCursorPos)(JNIEnv *env, jclass that, jint arg0, jint arg1);
SWTEXPORT jint JNICALL OS_NATIVE(SetDIBColorTable)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jbyteArray arg3);
SWTEXPORT jint JNICALL OS_NATIVE(SetErrorMode)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jint JNICALL OS_NATIVE(SetFocus)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(SetForegroundWindow)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jint JNICALL OS_NATIVE(SetGraphicsMode)(JNIEnv *env, jclass that, jint arg0, jint arg1);
SWTEXPORT jint JNICALL OS_NATIVE(SetLayout)(JNIEnv *env, jclass that, jint arg0, jint arg1);
SWTEXPORT jboolean JNICALL OS_NATIVE(SetMenu)(JNIEnv *env, jclass that, jint arg0, jint arg1);
SWTEXPORT jboolean JNICALL OS_NATIVE(SetMenuDefaultItem)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2);
SWTEXPORT jboolean JNICALL OS_NATIVE(SetMenuInfo)(JNIEnv *env, jclass that, jint arg0, jobject arg1);
SWTEXPORT jboolean JNICALL OS_NATIVE(SetMenuItemInfoA)(JNIEnv *env, jclass that, jint arg0, jint arg1, jboolean arg2, jobject arg3);
SWTEXPORT jboolean JNICALL OS_NATIVE(SetMenuItemInfoW)(JNIEnv *env, jclass that, jint arg0, jint arg1, jboolean arg2, jobject arg3);
SWTEXPORT jint JNICALL OS_NATIVE(SetMetaRgn)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jint JNICALL OS_NATIVE(SetPaletteEntries)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jbyteArray arg3);
SWTEXPORT jint JNICALL OS_NATIVE(SetParent)(JNIEnv *env, jclass that, jint arg0, jint arg1);
SWTEXPORT jint JNICALL OS_NATIVE(SetPixel)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3);
SWTEXPORT jint JNICALL OS_NATIVE(SetPolyFillMode)(JNIEnv *env, jclass that, jint arg0, jint arg1);
SWTEXPORT jboolean JNICALL OS_NATIVE(SetPropA)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2);
SWTEXPORT jboolean JNICALL OS_NATIVE(SetPropW)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2);
SWTEXPORT jint JNICALL OS_NATIVE(SetROP2)(JNIEnv *env, jclass that, jint arg0, jint arg1);
SWTEXPORT jboolean JNICALL OS_NATIVE(SetRect)(JNIEnv *env, jclass that, jobject arg0, jint arg1, jint arg2, jint arg3, jint arg4);
SWTEXPORT jboolean JNICALL OS_NATIVE(SetRectRgn)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3, jint arg4);
SWTEXPORT jboolean JNICALL OS_NATIVE(SetScrollInfo)(JNIEnv *env, jclass that, jint arg0, jint arg1, jobject arg2, jboolean arg3);
SWTEXPORT jint JNICALL OS_NATIVE(SetStretchBltMode)(JNIEnv *env, jclass that, jint arg0, jint arg1);
SWTEXPORT jint JNICALL OS_NATIVE(SetTextAlign)(JNIEnv *env, jclass that, jint arg0, jint arg1);
SWTEXPORT jint JNICALL OS_NATIVE(SetTextColor)(JNIEnv *env, jclass that, jint arg0, jint arg1);
SWTEXPORT jint JNICALL OS_NATIVE(SetTimer)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3);
SWTEXPORT jint JNICALL OS_NATIVE(SetWindowLongA)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2);
SWTEXPORT jint JNICALL OS_NATIVE(SetWindowLongW)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2);
SWTEXPORT jboolean JNICALL OS_NATIVE(SetWindowOrgEx)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jobject arg3);
SWTEXPORT jboolean JNICALL OS_NATIVE(SetWindowPlacement)(JNIEnv *env, jclass that, jint arg0, jobject arg1);
SWTEXPORT jboolean JNICALL OS_NATIVE(SetWindowPos)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3, jint arg4, jint arg5, jint arg6);
SWTEXPORT jint JNICALL OS_NATIVE(SetWindowRgn)(JNIEnv *env, jclass that, jint arg0, jint arg1, jboolean arg2);
SWTEXPORT jboolean JNICALL OS_NATIVE(SetWindowTextA)(JNIEnv *env, jclass that, jint arg0, jbyteArray arg1);
SWTEXPORT jboolean JNICALL OS_NATIVE(SetWindowTextW)(JNIEnv *env, jclass that, jint arg0, jcharArray arg1);
SWTEXPORT jint JNICALL OS_NATIVE(SetWindowsHookExA)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3);
SWTEXPORT jint JNICALL OS_NATIVE(SetWindowsHookExW)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3);
SWTEXPORT jboolean JNICALL OS_NATIVE(SetWorldTransform)(JNIEnv *env, jclass that, jint arg0, jfloatArray arg1);
SWTEXPORT jboolean JNICALL OS_NATIVE(ShellExecuteExA)(JNIEnv *env, jclass that, jobject arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(ShellExecuteExW)(JNIEnv *env, jclass that, jobject arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(Shell_1NotifyIconA)(JNIEnv *env, jclass that, jint arg0, jobject arg1);
SWTEXPORT jboolean JNICALL OS_NATIVE(Shell_1NotifyIconW)(JNIEnv *env, jclass that, jint arg0, jobject arg1);
SWTEXPORT jboolean JNICALL OS_NATIVE(ShowCaret)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(ShowOwnedPopups)(JNIEnv *env, jclass that, jint arg0, jboolean arg1);
SWTEXPORT jboolean JNICALL OS_NATIVE(ShowScrollBar)(JNIEnv *env, jclass that, jint arg0, jint arg1, jboolean arg2);
SWTEXPORT jboolean JNICALL OS_NATIVE(ShowWindow)(JNIEnv *env, jclass that, jint arg0, jint arg1);
#ifdef WIN32_PLATFORM_PSPC
SWTEXPORT jboolean JNICALL OS_NATIVE(SipGetInfo)(JNIEnv *env, jclass that, jobject arg0);
#endif
SWTEXPORT jint JNICALL OS_NATIVE(StartDocA)(JNIEnv *env, jclass that, jint arg0, jobject arg1);
SWTEXPORT jint JNICALL OS_NATIVE(StartDocW)(JNIEnv *env, jclass that, jint arg0, jobject arg1);
SWTEXPORT jint JNICALL OS_NATIVE(StartPage)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(StretchBlt)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3, jint arg4, jint arg5, jint arg6, jint arg7, jint arg8, jint arg9, jint arg10);
SWTEXPORT jboolean JNICALL OS_NATIVE(StrokePath)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(SystemParametersInfoA__IILorg_eclipse_swt_internal_win32_HIGHCONTRAST_2I)(JNIEnv *env, jclass that, jint arg0, jint arg1, jobject arg2, jint arg3);
SWTEXPORT jboolean JNICALL OS_NATIVE(SystemParametersInfoA__IILorg_eclipse_swt_internal_win32_NONCLIENTMETRICSA_2I)(JNIEnv *env, jclass that, jint arg0, jint arg1, jobject arg2, jint arg3);
SWTEXPORT jboolean JNICALL OS_NATIVE(SystemParametersInfoA__IILorg_eclipse_swt_internal_win32_RECT_2I)(JNIEnv *env, jclass that, jint arg0, jint arg1, jobject arg2, jint arg3);
SWTEXPORT jboolean JNICALL OS_NATIVE(SystemParametersInfoA__II_3II)(JNIEnv *env, jclass that, jint arg0, jint arg1, jintArray arg2, jint arg3);
SWTEXPORT jboolean JNICALL OS_NATIVE(SystemParametersInfoW__IILorg_eclipse_swt_internal_win32_HIGHCONTRAST_2I)(JNIEnv *env, jclass that, jint arg0, jint arg1, jobject arg2, jint arg3);
SWTEXPORT jboolean JNICALL OS_NATIVE(SystemParametersInfoW__IILorg_eclipse_swt_internal_win32_NONCLIENTMETRICSW_2I)(JNIEnv *env, jclass that, jint arg0, jint arg1, jobject arg2, jint arg3);
SWTEXPORT jboolean JNICALL OS_NATIVE(SystemParametersInfoW__IILorg_eclipse_swt_internal_win32_RECT_2I)(JNIEnv *env, jclass that, jint arg0, jint arg1, jobject arg2, jint arg3);
SWTEXPORT jboolean JNICALL OS_NATIVE(SystemParametersInfoW__II_3II)(JNIEnv *env, jclass that, jint arg0, jint arg1, jintArray arg2, jint arg3);
SWTEXPORT jint JNICALL OS_NATIVE(ToAscii)(JNIEnv *env, jclass that, jint arg0, jint arg1, jbyteArray arg2, jshortArray arg3, jint arg4);
SWTEXPORT jint JNICALL OS_NATIVE(ToUnicode)(JNIEnv *env, jclass that, jint arg0, jint arg1, jbyteArray arg2, jcharArray arg3, jint arg4, jint arg5);
SWTEXPORT jboolean JNICALL OS_NATIVE(TrackMouseEvent)(JNIEnv *env, jclass that, jobject arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(TrackPopupMenu)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3, jint arg4, jint arg5, jobject arg6);
SWTEXPORT jint JNICALL OS_NATIVE(TranslateAcceleratorA)(JNIEnv *env, jclass that, jint arg0, jint arg1, jobject arg2);
SWTEXPORT jint JNICALL OS_NATIVE(TranslateAcceleratorW)(JNIEnv *env, jclass that, jint arg0, jint arg1, jobject arg2);
SWTEXPORT jboolean JNICALL OS_NATIVE(TranslateCharsetInfo)(JNIEnv *env, jclass that, jint arg0, jintArray arg1, jint arg2);
SWTEXPORT jboolean JNICALL OS_NATIVE(TranslateMDISysAccel)(JNIEnv *env, jclass that, jint arg0, jobject arg1);
SWTEXPORT jboolean JNICALL OS_NATIVE(TranslateMessage)(JNIEnv *env, jclass that, jobject arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(TransparentBlt)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3, jint arg4, jint arg5, jint arg6, jint arg7, jint arg8, jint arg9, jint arg10);
#ifdef _WIN32_WCE
SWTEXPORT jboolean JNICALL OS_NATIVE(TransparentImage)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3, jint arg4, jint arg5, jint arg6, jint arg7, jint arg8, jint arg9, jint arg10);
#endif
SWTEXPORT jboolean JNICALL OS_NATIVE(UnhookWindowsHookEx)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(UnregisterClassA)(JNIEnv *env, jclass that, jbyteArray arg0, jint arg1);
SWTEXPORT jboolean JNICALL OS_NATIVE(UnregisterClassW)(JNIEnv *env, jclass that, jcharArray arg0, jint arg1);
SWTEXPORT jboolean JNICALL OS_NATIVE(UpdateWindow)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jboolean JNICALL OS_NATIVE(ValidateRect)(JNIEnv *env, jclass that, jint arg0, jobject arg1);
SWTEXPORT jshort JNICALL OS_NATIVE(VkKeyScanA)(JNIEnv *env, jclass that, jshort arg0);
SWTEXPORT jshort JNICALL OS_NATIVE(VkKeyScanW)(JNIEnv *env, jclass that, jshort arg0);
SWTEXPORT jint JNICALL OS_NATIVE(VtblCall__II)(JNIEnv *env, jclass that, jint arg0, jint arg1);
SWTEXPORT jint JNICALL OS_NATIVE(VtblCall__III)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2);
SWTEXPORT jint JNICALL OS_NATIVE(VtblCall__IIIII_3I)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3, jint arg4, jintArray arg5);
SWTEXPORT jint JNICALL OS_NATIVE(VtblCall__II_3CII_3I_3I)(JNIEnv *env, jclass that, jint arg0, jint arg1, jcharArray arg2, jint arg3, jint arg4, jintArray arg5, jintArray arg6);
SWTEXPORT jboolean JNICALL OS_NATIVE(WaitMessage)(JNIEnv *env, jclass that);
SWTEXPORT jint JNICALL OS_NATIVE(WideCharToMultiByte__II_3CIII_3B_3Z)(JNIEnv *env, jclass that, jint arg0, jint arg1, jcharArray arg2, jint arg3, jint arg4, jint arg5, jbyteArray arg6, jbooleanArray arg7);
SWTEXPORT jint JNICALL OS_NATIVE(WideCharToMultiByte__II_3CI_3BI_3B_3Z)(JNIEnv *env, jclass that, jint arg0, jint arg1, jcharArray arg2, jint arg3, jbyteArray arg4, jint arg5, jbyteArray arg6, jbooleanArray arg7);
SWTEXPORT jint JNICALL OS_NATIVE(WindowFromDC)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jint JNICALL OS_NATIVE(WindowFromPoint)(JNIEnv *env, jclass that, jobject arg0);
SWTEXPORT jint JNICALL OS_NATIVE(strlen)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jint JNICALL OS_NATIVE(wcslen)(JNIEnv *env, jclass that, jint arg0);

// From os_custom.c
SWTEXPORT jint JNICALL OS_NATIVE(GetLibraryHandle)(JNIEnv *env, jclass that);
SWTEXPORT jboolean JNICALL OS_NATIVE(IsPPC)(JNIEnv *env, jclass that);
SWTEXPORT jboolean JNICALL OS_NATIVE(IsSP)(JNIEnv *env, jclass that);
SWTEXPORT jint JNICALL OS_NATIVE(SendMessageW__II_3I_3I)(JNIEnv *env, jclass that, jint arg0, jint arg1, jintArray arg2, jintArray arg3);

// From callback.c
SWTEXPORT jint JNICALL Java_org_eclipse_swt_internal_Callback_PTR_1sizeof(JNIEnv *env, jclass that);
SWTEXPORT SWT_PTR JNICALL Java_org_eclipse_swt_internal_Callback_bind(JNIEnv *env, jclass that, jobject callback, jobject object, jstring method, jstring signature, jint argCount, jboolean isStatic, jboolean isArrayBased, SWT_PTR errorResult);
SWTEXPORT jboolean JNICALL Java_org_eclipse_swt_internal_Callback_getEnabled(JNIEnv *env, jclass that);
SWTEXPORT jint JNICALL Java_org_eclipse_swt_internal_Callback_getEntryCount(JNIEnv *env, jclass that);
//SWTEXPORT jstring JNICALL Java_org_eclipse_swt_internal_Callback_getPlatform(JNIEnv *env, jclass that);  // Where is this implemented?
SWTEXPORT void JNICALL Java_org_eclipse_swt_internal_Callback_reset(JNIEnv *env, jclass that);
SWTEXPORT void JNICALL Java_org_eclipse_swt_internal_Callback_setEnabled(JNIEnv *env, jclass that, jboolean enable);
SWTEXPORT void JNICALL Java_org_eclipse_swt_internal_Callback_unbind(JNIEnv *env, jclass that, jobject callback);

// From com.c
SWTEXPORT jint JNICALL COM_NATIVE(CLSIDFromProgID)(JNIEnv *env, jclass that, jcharArray arg0, jobject arg1);
SWTEXPORT jint JNICALL COM_NATIVE(CLSIDFromString)(JNIEnv *env, jclass that, jcharArray arg0, jobject arg1);
SWTEXPORT jint JNICALL COM_NATIVE(CoCreateInstance)(JNIEnv *env, jclass that, jobject arg0, jint arg1, jint arg2, jobject arg3, jintArray arg4);
SWTEXPORT void JNICALL COM_NATIVE(CoFreeUnusedLibraries)(JNIEnv *env, jclass that);
SWTEXPORT jint JNICALL COM_NATIVE(CoGetClassObject)(JNIEnv *env, jclass that, jobject arg0, jint arg1, jint arg2, jobject arg3, jintArray arg4);
SWTEXPORT jint JNICALL COM_NATIVE(CoLockObjectExternal)(JNIEnv *env, jclass that, jint arg0, jboolean arg1, jboolean arg2);
SWTEXPORT jint JNICALL COM_NATIVE(CoTaskMemAlloc)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT void JNICALL COM_NATIVE(CoTaskMemFree)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jint JNICALL COM_NATIVE(CreateStdAccessibleObject)(JNIEnv *env, jclass that, jint arg0, jint arg1, jobject arg2, jintArray arg3);
SWTEXPORT jint JNICALL COM_NATIVE(DoDragDrop)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jintArray arg3);
SWTEXPORT jint JNICALL COM_NATIVE(GetClassFile)(JNIEnv *env, jclass that, jcharArray arg0, jobject arg1);
SWTEXPORT jint JNICALL COM_NATIVE(IIDFromString)(JNIEnv *env, jclass that, jcharArray arg0, jobject arg1);
SWTEXPORT jboolean JNICALL COM_NATIVE(IsEqualGUID)(JNIEnv *env, jclass that, jobject arg0, jobject arg1);
SWTEXPORT jint JNICALL COM_NATIVE(LresultFromObject)(JNIEnv *env, jclass that, jobject arg0, jint arg1, jint arg2);
SWTEXPORT void JNICALL COM_NATIVE(MoveMemory__ILorg_eclipse_swt_internal_ole_win32_FORMATETC_2I)(JNIEnv *env, jclass that, jint arg0, jobject arg1, jint arg2);
SWTEXPORT void JNICALL COM_NATIVE(MoveMemory__ILorg_eclipse_swt_internal_ole_win32_GUID_2I)(JNIEnv *env, jclass that, jint arg0, jobject arg1, jint arg2);
SWTEXPORT void JNICALL COM_NATIVE(MoveMemory__ILorg_eclipse_swt_internal_ole_win32_OLEINPLACEFRAMEINFO_2I)(JNIEnv *env, jclass that, jint arg0, jobject arg1, jint arg2);
SWTEXPORT void JNICALL COM_NATIVE(MoveMemory__ILorg_eclipse_swt_internal_ole_win32_STATSTG_2I)(JNIEnv *env, jclass that, jint arg0, jobject arg1, jint arg2);
SWTEXPORT void JNICALL COM_NATIVE(MoveMemory__ILorg_eclipse_swt_internal_ole_win32_STGMEDIUM_2I)(JNIEnv *env, jclass that, jint arg0, jobject arg1, jint arg2);
SWTEXPORT void JNICALL COM_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_ole_win32_DISPPARAMS_2II)(JNIEnv *env, jclass that, jobject arg0, jint arg1, jint arg2);
SWTEXPORT void JNICALL COM_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_ole_win32_FORMATETC_2II)(JNIEnv *env, jclass that, jobject arg0, jint arg1, jint arg2);
SWTEXPORT void JNICALL COM_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_ole_win32_FUNCDESC_2II)(JNIEnv *env, jclass that, jobject arg0, jint arg1, jint arg2);
SWTEXPORT void JNICALL COM_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_ole_win32_GUID_2II)(JNIEnv *env, jclass that, jobject arg0, jint arg1, jint arg2);
SWTEXPORT void JNICALL COM_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_ole_win32_STATSTG_2II)(JNIEnv *env, jclass that, jobject arg0, jint arg1, jint arg2);
SWTEXPORT void JNICALL COM_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_ole_win32_STGMEDIUM_2II)(JNIEnv *env, jclass that, jobject arg0, jint arg1, jint arg2);
SWTEXPORT void JNICALL COM_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_ole_win32_TYPEATTR_2II)(JNIEnv *env, jclass that, jobject arg0, jint arg1, jint arg2);
SWTEXPORT void JNICALL COM_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_ole_win32_VARDESC_2II)(JNIEnv *env, jclass that, jobject arg0, jint arg1, jint arg2);
SWTEXPORT void JNICALL COM_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_RECT_2II)(JNIEnv *env, jclass that, jobject arg0, jint arg1, jint arg2);
SWTEXPORT jint JNICALL COM_NATIVE(OleCreate)(JNIEnv *env, jclass that, jobject arg0, jobject arg1, jint arg2, jobject arg3, jint arg4, jint arg5, jintArray arg6);
SWTEXPORT jint JNICALL COM_NATIVE(OleCreateFromFile)(JNIEnv *env, jclass that, jobject arg0, jcharArray arg1, jobject arg2, jint arg3, jobject arg4, jint arg5, jint arg6, jintArray arg7);
SWTEXPORT jint JNICALL COM_NATIVE(OleCreatePropertyFrame)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jcharArray arg3, jint arg4, jintArray arg5, jint arg6, jint arg7, jint arg8, jint arg9, jint arg10);
SWTEXPORT jint JNICALL COM_NATIVE(OleDraw)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3);
SWTEXPORT jint JNICALL COM_NATIVE(OleFlushClipboard)(JNIEnv *env, jclass that);
SWTEXPORT jint JNICALL COM_NATIVE(OleGetClipboard)(JNIEnv *env, jclass that, jintArray arg0);
SWTEXPORT jint JNICALL COM_NATIVE(OleIsCurrentClipboard)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jboolean JNICALL COM_NATIVE(OleIsRunning)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jint JNICALL COM_NATIVE(OleLoad)(JNIEnv *env, jclass that, jint arg0, jobject arg1, jint arg2, jintArray arg3);
SWTEXPORT jint JNICALL COM_NATIVE(OleRun)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jint JNICALL COM_NATIVE(OleSave)(JNIEnv *env, jclass that, jint arg0, jint arg1, jboolean arg2);
SWTEXPORT jint JNICALL COM_NATIVE(OleSetClipboard)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jint JNICALL COM_NATIVE(OleSetContainedObject)(JNIEnv *env, jclass that, jint arg0, jboolean arg1);
SWTEXPORT jint JNICALL COM_NATIVE(OleSetMenuDescriptor)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3, jint arg4);
SWTEXPORT jint JNICALL COM_NATIVE(OleTranslateColor)(JNIEnv *env, jclass that, jint arg0, jint arg1, jintArray arg2);
SWTEXPORT jint JNICALL COM_NATIVE(ProgIDFromCLSID)(JNIEnv *env, jclass that, jobject arg0, jintArray arg1);
SWTEXPORT jint JNICALL COM_NATIVE(RegisterDragDrop)(JNIEnv *env, jclass that, jint arg0, jint arg1);
SWTEXPORT void JNICALL COM_NATIVE(ReleaseStgMedium)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jint JNICALL COM_NATIVE(RevokeDragDrop)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jint JNICALL COM_NATIVE(StgCreateDocfile)(JNIEnv *env, jclass that, jcharArray arg0, jint arg1, jint arg2, jintArray arg3);
SWTEXPORT jint JNICALL COM_NATIVE(StgIsStorageFile)(JNIEnv *env, jclass that, jcharArray arg0);
SWTEXPORT jint JNICALL COM_NATIVE(StgOpenStorage)(JNIEnv *env, jclass that, jcharArray arg0, jint arg1, jint arg2, jint arg3, jint arg4, jintArray arg5);
SWTEXPORT jint JNICALL COM_NATIVE(StringFromCLSID)(JNIEnv *env, jclass that, jobject arg0, jintArray arg1);
SWTEXPORT jint JNICALL COM_NATIVE(SysAllocString)(JNIEnv *env, jclass that, jcharArray arg0);
SWTEXPORT void JNICALL COM_NATIVE(SysFreeString)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jint JNICALL COM_NATIVE(SysStringByteLen)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jint JNICALL COM_NATIVE(VariantChangeType)(JNIEnv *env, jclass that, jint arg0, jint arg1, jshort arg2, jshort arg3);
SWTEXPORT jint JNICALL COM_NATIVE(VariantClear)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT void JNICALL COM_NATIVE(VariantInit)(JNIEnv *env, jclass that, jint arg0);
SWTEXPORT jint JNICALL COM_NATIVE(VtblCall__IIII)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3);
SWTEXPORT jint JNICALL COM_NATIVE(VtblCall__IIIII)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3, jint arg4);
SWTEXPORT jint JNICALL COM_NATIVE(VtblCall__IIIIII)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3, jint arg4, jint arg5);
SWTEXPORT jint JNICALL COM_NATIVE(VtblCall__IIIIIII)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3, jint arg4, jint arg5, jint arg6);
SWTEXPORT jint JNICALL COM_NATIVE(VtblCall__IIIIIIII)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3, jint arg4, jint arg5, jint arg6, jint arg7);
SWTEXPORT jint JNICALL COM_NATIVE(VtblCall__IIIIIIIIII)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3, jint arg4, jint arg5, jint arg6, jint arg7, jint arg8, jint arg9);
SWTEXPORT jint JNICALL COM_NATIVE(VtblCall__IIIILorg_eclipse_swt_internal_ole_win32_DVTARGETDEVICE_2Lorg_eclipse_swt_internal_win32_SIZE_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3, jobject arg4, jobject arg5);
SWTEXPORT jint JNICALL COM_NATIVE(VtblCall__IIIILorg_eclipse_swt_internal_ole_win32_GUID_2I_3I)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3, jobject arg4, jint arg5, jintArray arg6);
SWTEXPORT jint JNICALL COM_NATIVE(VtblCall__IIII_3I)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jint arg3, jintArray arg4);
SWTEXPORT jint JNICALL COM_NATIVE(VtblCall__IIILorg_eclipse_swt_internal_ole_win32_FORMATETC_2_3I)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jobject arg3, jintArray arg4);
SWTEXPORT jint JNICALL COM_NATIVE(VtblCall__IIILorg_eclipse_swt_internal_ole_win32_GUID_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jobject arg3);
SWTEXPORT jint JNICALL COM_NATIVE(VtblCall__IIILorg_eclipse_swt_internal_ole_win32_GUID_2II)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jobject arg3, jint arg4, jint arg5);
SWTEXPORT jint JNICALL COM_NATIVE(VtblCall__IIILorg_eclipse_swt_internal_ole_win32_GUID_2IILorg_eclipse_swt_internal_ole_win32_DISPPARAMS_2ILorg_eclipse_swt_internal_ole_win32_EXCEPINFO_2_3I)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jobject arg3, jint arg4, jint arg5, jobject arg6, jint arg7, jobject arg8, jintArray arg9);
SWTEXPORT jint JNICALL COM_NATIVE(VtblCall__IIILorg_eclipse_swt_internal_ole_win32_STATSTG_2_3I)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jobject arg3, jintArray arg4);
SWTEXPORT jint JNICALL COM_NATIVE(VtblCall__IIILorg_eclipse_swt_internal_win32_MSG_2IIILorg_eclipse_swt_internal_win32_RECT_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jobject arg3, jint arg4, jint arg5, jint arg6, jobject arg7);
SWTEXPORT jint JNICALL COM_NATIVE(VtblCall__IIILorg_eclipse_swt_internal_win32_SIZE_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jobject arg3);
SWTEXPORT jint JNICALL COM_NATIVE(VtblCall__IIIZ)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jboolean arg3);
SWTEXPORT jint JNICALL COM_NATIVE(VtblCall__III_3I)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jintArray arg3);
SWTEXPORT jint JNICALL COM_NATIVE(VtblCall__III_3II_3I)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jintArray arg3, jint arg4, jintArray arg5);
SWTEXPORT jint JNICALL COM_NATIVE(VtblCall__III_3I_3I_3I_3I)(JNIEnv *env, jclass that, jint arg0, jint arg1, jint arg2, jintArray arg3, jintArray arg4, jintArray arg5, jintArray arg6);
SWTEXPORT jint JNICALL COM_NATIVE(VtblCall__IILorg_eclipse_swt_internal_ole_win32_CAUUID_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jobject arg2);
SWTEXPORT jint JNICALL COM_NATIVE(VtblCall__IILorg_eclipse_swt_internal_ole_win32_CONTROLINFO_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jobject arg2);
SWTEXPORT jint JNICALL COM_NATIVE(VtblCall__IILorg_eclipse_swt_internal_ole_win32_FORMATETC_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jobject arg2);
SWTEXPORT jint JNICALL COM_NATIVE(VtblCall__IILorg_eclipse_swt_internal_ole_win32_FORMATETC_2Lorg_eclipse_swt_internal_ole_win32_STGMEDIUM_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jobject arg2, jobject arg3);
SWTEXPORT jint JNICALL COM_NATIVE(VtblCall__IILorg_eclipse_swt_internal_ole_win32_FORMATETC_2Lorg_eclipse_swt_internal_ole_win32_STGMEDIUM_2Z)(JNIEnv *env, jclass that, jint arg0, jint arg1, jobject arg2, jobject arg3, jboolean arg4);
SWTEXPORT jint JNICALL COM_NATIVE(VtblCall__IILorg_eclipse_swt_internal_ole_win32_GUID_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jobject arg2);
SWTEXPORT jint JNICALL COM_NATIVE(VtblCall__IILorg_eclipse_swt_internal_ole_win32_GUID_2IIII)(JNIEnv *env, jclass that, jint arg0, jint arg1, jobject arg2, jint arg3, jint arg4, jint arg5, jint arg6);
SWTEXPORT jint JNICALL COM_NATIVE(VtblCall__IILorg_eclipse_swt_internal_ole_win32_GUID_2III_3I)(JNIEnv *env, jclass that, jint arg0, jint arg1, jobject arg2, jint arg3, jint arg4, jint arg5, jintArray arg6);
SWTEXPORT jint JNICALL COM_NATIVE(VtblCall__IILorg_eclipse_swt_internal_ole_win32_GUID_2ILorg_eclipse_swt_internal_ole_win32_OLECMD_2Lorg_eclipse_swt_internal_ole_win32_OLECMDTEXT_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jobject arg2, jint arg3, jobject arg4, jobject arg5);
SWTEXPORT jint JNICALL COM_NATIVE(VtblCall__IILorg_eclipse_swt_internal_ole_win32_GUID_2_3I)(JNIEnv *env, jclass that, jint arg0, jint arg1, jobject arg2, jintArray arg3);
SWTEXPORT jint JNICALL COM_NATIVE(VtblCall__IILorg_eclipse_swt_internal_ole_win32_LICINFO_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jobject arg2);
SWTEXPORT jint JNICALL COM_NATIVE(VtblCall__IILorg_eclipse_swt_internal_win32_MSG_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jobject arg2);
SWTEXPORT jint JNICALL COM_NATIVE(VtblCall__IILorg_eclipse_swt_internal_win32_RECT_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jobject arg2);
SWTEXPORT jint JNICALL COM_NATIVE(VtblCall__IILorg_eclipse_swt_internal_win32_RECT_2IZ)(JNIEnv *env, jclass that, jint arg0, jint arg1, jobject arg2, jint arg3, jboolean arg4);
SWTEXPORT jint JNICALL COM_NATIVE(VtblCall__IILorg_eclipse_swt_internal_win32_RECT_2Lorg_eclipse_swt_internal_win32_RECT_2)(JNIEnv *env, jclass that, jint arg0, jint arg1, jobject arg2, jobject arg3);
SWTEXPORT jint JNICALL COM_NATIVE(VtblCall__II_3C)(JNIEnv *env, jclass that, jint arg0, jint arg1, jcharArray arg2);
SWTEXPORT jint JNICALL COM_NATIVE(VtblCall__II_3CI)(JNIEnv *env, jclass that, jint arg0, jint arg1, jcharArray arg2, jint arg3);
SWTEXPORT jint JNICALL COM_NATIVE(VtblCall__II_3CIIII_3I)(JNIEnv *env, jclass that, jint arg0, jint arg1, jcharArray arg2, jint arg3, jint arg4, jint arg5, jint arg6, jintArray arg7);
SWTEXPORT jint JNICALL COM_NATIVE(VtblCall__II_3CIII_3I)(JNIEnv *env, jclass that, jint arg0, jint arg1, jcharArray arg2, jint arg3, jint arg4, jint arg5, jintArray arg6);
SWTEXPORT jint JNICALL COM_NATIVE(VtblCall__II_3C_3C)(JNIEnv *env, jclass that, jint arg0, jint arg1, jcharArray arg2, jcharArray arg3);
SWTEXPORT jint JNICALL COM_NATIVE(VtblCall__II_3I)(JNIEnv *env, jclass that, jint arg0, jint arg1, jintArray arg2);
SWTEXPORT jint JNICALL COM_NATIVE(WriteClassStg)(JNIEnv *env, jclass that, jint arg0, jobject arg1);


JNINativeMethod methodsOS[] = {
"AbortDoc", "(I)I", (void *)OS_NATIVE(AbortDoc),
"ActivateKeyboardLayout", "(II)I", (void *)OS_NATIVE(ActivateKeyboardLayout),
"AdjustWindowRectEx", "(Lorg.eclipse.swt.internal.win32.RECT;IZI)Z", (void *)OS_NATIVE(AdjustWindowRectEx),
"AlphaBlend", "(IIIIIIIIIILorg.eclipse.swt.internal.win32.BLENDFUNCTION;)Z", (void *)OS_NATIVE(AlphaBlend),
"Arc", "(IIIIIIIII)Z", (void *)OS_NATIVE(Arc),
"BeginDeferWindowPos", "(I)I", (void *)OS_NATIVE(BeginDeferWindowPos),
"BeginPaint", "(ILorg.eclipse.swt.internal.win32.PAINTSTRUCT;)I", (void *)OS_NATIVE(BeginPaint),
"BeginPath", "(I)Z", (void *)OS_NATIVE(BeginPath),
"BitBlt", "(IIIIIIIII)Z", (void *)OS_NATIVE(BitBlt),
"BringWindowToTop", "(I)Z", (void *)OS_NATIVE(BringWindowToTop),
"Call", "(ILorg.eclipse.swt.internal.win32.DLLVERSIONINFO;)I", (void *)OS_NATIVE(Call),
"CallNextHookEx", "(IIII)I", (void *)OS_NATIVE(CallNextHookEx),
"CallWindowProcA", "(IIIII)I", (void *)OS_NATIVE(CallWindowProcA),
"CallWindowProcW", "(IIIII)I", (void *)OS_NATIVE(CallWindowProcW),
"CharLowerA", "(S)S", (void *)OS_NATIVE(CharLowerA),
"CharLowerW", "(S)S", (void *)OS_NATIVE(CharLowerW),
"CharUpperA", "(S)S", (void *)OS_NATIVE(CharUpperA),
"CharUpperW", "(S)S", (void *)OS_NATIVE(CharUpperW),
"CheckMenuItem", "(III)Z", (void *)OS_NATIVE(CheckMenuItem),
"ChooseColorA", "(Lorg.eclipse.swt.internal.win32.CHOOSECOLOR;)Z", (void *)OS_NATIVE(ChooseColorA),
"ChooseColorW", "(Lorg.eclipse.swt.internal.win32.CHOOSECOLOR;)Z", (void *)OS_NATIVE(ChooseColorW),
"ChooseFontA", "(Lorg.eclipse.swt.internal.win32.CHOOSEFONT;)Z", (void *)OS_NATIVE(ChooseFontA),
"ChooseFontW", "(Lorg.eclipse.swt.internal.win32.CHOOSEFONT;)Z", (void *)OS_NATIVE(ChooseFontW),
"ClientToScreen", "(ILorg.eclipse.swt.internal.win32.POINT;)Z", (void *)OS_NATIVE(ClientToScreen),
"CloseClipboard", "()Z", (void *)OS_NATIVE(CloseClipboard),
"CloseThemeData", "(I)I", (void *)OS_NATIVE(CloseThemeData),
"CoCreateInstance", "([BII[B[I)I", (void *)OS_NATIVE(CoCreateInstance),
"CombineRgn", "(IIII)I", (void *)OS_NATIVE(CombineRgn),
"CommDlgExtendedError", "()I", (void *)OS_NATIVE(CommDlgExtendedError),
#ifdef WIN32_PLATFORM_HPC2000
"CommandBar_AddAdornments", "(III)Z", (void *)OS_NATIVE(CommandBar_1AddAdornments),
"CommandBar_Create", "(III)I", (void *)OS_NATIVE(CommandBar_1Create),
#endif
#ifdef _WIN32_WCE
"CommandBar_Destroy", "(I)V", (void *)OS_NATIVE(CommandBar_1Destroy),
#endif
#ifdef WIN32_PLATFORM_HPC2000
"CommandBar_DrawMenuBar", "(II)Z", (void *)OS_NATIVE(CommandBar_1DrawMenuBar),
"CommandBar_Height", "(I)I", (void *)OS_NATIVE(CommandBar_1Height),
"CommandBar_InsertMenubarEx", "(IIII)Z", (void *)OS_NATIVE(CommandBar_1InsertMenubarEx),
"CommandBar_Show", "(IZ)Z", (void *)OS_NATIVE(CommandBar_1Show),
#endif
"CopyImage", "(IIIII)I", (void *)OS_NATIVE(CopyImage),
"CreateAcceleratorTableA", "([BI)I", (void *)OS_NATIVE(CreateAcceleratorTableA),
"CreateAcceleratorTableW", "([BI)I", (void *)OS_NATIVE(CreateAcceleratorTableW),
"CreateBitmap", "(IIII[B)I", (void *)OS_NATIVE(CreateBitmap),
"CreateCaret", "(IIII)Z", (void *)OS_NATIVE(CreateCaret),
"CreateCompatibleBitmap", "(III)I", (void *)OS_NATIVE(CreateCompatibleBitmap),
"CreateCompatibleDC", "(I)I", (void *)OS_NATIVE(CreateCompatibleDC),
"CreateCursor", "(IIIII[B[B)I", (void *)OS_NATIVE(CreateCursor),
"CreateDCA", "([B[BII)I", (void *)OS_NATIVE(CreateDCA),
"CreateDCW", "([C[CII)I", (void *)OS_NATIVE(CreateDCW),
"CreateDIBSection", "(I[BI[III)I", (void *)OS_NATIVE(CreateDIBSection),
"CreateFontIndirectA", "(I)I", (void *)OS_NATIVE(CreateFontIndirectA__I),
"CreateFontIndirectA", "(Lorg.eclipse.swt.internal.win32.LOGFONTA;)I", (void *)OS_NATIVE(CreateFontIndirectA__Lorg_eclipse_swt_internal_win32_LOGFONTA_2),
"CreateFontIndirectW", "(I)I", (void *)OS_NATIVE(CreateFontIndirectW__I),
"CreateFontIndirectW", "(Lorg.eclipse.swt.internal.win32.LOGFONTW;)I", (void *)OS_NATIVE(CreateFontIndirectW__Lorg_eclipse_swt_internal_win32_LOGFONTW_2),
"CreateIconIndirect", "(Lorg.eclipse.swt.internal.win32.ICONINFO;)I", (void *)OS_NATIVE(CreateIconIndirect),
"CreateMenu", "()I", (void *)OS_NATIVE(CreateMenu),
"CreatePalette", "([B)I", (void *)OS_NATIVE(CreatePalette),
"CreatePatternBrush", "(I)I", (void *)OS_NATIVE(CreatePatternBrush),
"CreatePen", "(III)I", (void *)OS_NATIVE(CreatePen),
"CreatePolygonRgn", "([III)I", (void *)OS_NATIVE(CreatePolygonRgn),
"CreatePopupMenu", "()I", (void *)OS_NATIVE(CreatePopupMenu),
"CreateRectRgn", "(IIII)I", (void *)OS_NATIVE(CreateRectRgn),
"CreateSolidBrush", "(I)I", (void *)OS_NATIVE(CreateSolidBrush),
"CreateStreamOnHGlobal", "(IZ[I)I", (void *)OS_NATIVE(CreateStreamOnHGlobal),
"CreateWindowExA", "(I[B[BIIIIIIIILorg.eclipse.swt.internal.win32.CREATESTRUCT;)I", (void *)OS_NATIVE(CreateWindowExA),
"CreateWindowExW", "(I[C[CIIIIIIIILorg.eclipse.swt.internal.win32.CREATESTRUCT;)I", (void *)OS_NATIVE(CreateWindowExW),
"DefFrameProcA", "(IIIII)I", (void *)OS_NATIVE(DefFrameProcA),
"DefFrameProcW", "(IIIII)I", (void *)OS_NATIVE(DefFrameProcW),
"DefMDIChildProcA", "(IIII)I", (void *)OS_NATIVE(DefMDIChildProcA),
"DefMDIChildProcW", "(IIII)I", (void *)OS_NATIVE(DefMDIChildProcW),
"DefWindowProcA", "(IIII)I", (void *)OS_NATIVE(DefWindowProcA),
"DefWindowProcW", "(IIII)I", (void *)OS_NATIVE(DefWindowProcW),
"DeferWindowPos", "(IIIIIIII)I", (void *)OS_NATIVE(DeferWindowPos),
"DeleteDC", "(I)Z", (void *)OS_NATIVE(DeleteDC),
"DeleteMenu", "(III)Z", (void *)OS_NATIVE(DeleteMenu),
"DeleteObject", "(I)Z", (void *)OS_NATIVE(DeleteObject),
"DestroyAcceleratorTable", "(I)Z", (void *)OS_NATIVE(DestroyAcceleratorTable),
"DestroyCaret", "()Z", (void *)OS_NATIVE(DestroyCaret),
"DestroyCursor", "(I)Z", (void *)OS_NATIVE(DestroyCursor),
"DestroyIcon", "(I)Z", (void *)OS_NATIVE(DestroyIcon),
"DestroyMenu", "(I)Z", (void *)OS_NATIVE(DestroyMenu),
"DestroyWindow", "(I)Z", (void *)OS_NATIVE(DestroyWindow),
"DispatchMessageA", "(Lorg.eclipse.swt.internal.win32.MSG;)I", (void *)OS_NATIVE(DispatchMessageA),
"DispatchMessageW", "(Lorg.eclipse.swt.internal.win32.MSG;)I", (void *)OS_NATIVE(DispatchMessageW),
"DragDetect", "(ILorg.eclipse.swt.internal.win32.POINT;)Z", (void *)OS_NATIVE(DragDetect),
"DragFinish", "(I)V", (void *)OS_NATIVE(DragFinish),
"DragQueryFileA", "(II[BI)I", (void *)OS_NATIVE(DragQueryFileA),
"DragQueryFileW", "(II[CI)I", (void *)OS_NATIVE(DragQueryFileW),
"DrawEdge", "(ILorg.eclipse.swt.internal.win32.RECT;II)Z", (void *)OS_NATIVE(DrawEdge),
"DrawFocusRect", "(ILorg.eclipse.swt.internal.win32.RECT;)Z", (void *)OS_NATIVE(DrawFocusRect),
"DrawFrameControl", "(ILorg.eclipse.swt.internal.win32.RECT;II)Z", (void *)OS_NATIVE(DrawFrameControl),
"DrawIconEx", "(IIIIIIIII)Z", (void *)OS_NATIVE(DrawIconEx),
"DrawMenuBar", "(I)Z", (void *)OS_NATIVE(DrawMenuBar),
"DrawStateA", "(IIIIIIIIII)Z", (void *)OS_NATIVE(DrawStateA),
"DrawStateW", "(IIIIIIIIII)Z", (void *)OS_NATIVE(DrawStateW),
"DrawTextA", "(I[BILorg.eclipse.swt.internal.win32.RECT;I)I", (void *)OS_NATIVE(DrawTextA),
"DrawTextW", "(I[CILorg.eclipse.swt.internal.win32.RECT;I)I", (void *)OS_NATIVE(DrawTextW),
"DrawThemeBackground", "(IIIILorg.eclipse.swt.internal.win32.RECT;Lorg.eclipse.swt.internal.win32.RECT;)I", (void *)OS_NATIVE(DrawThemeBackground),
"Ellipse", "(IIIII)Z", (void *)OS_NATIVE(Ellipse),
"EnableMenuItem", "(III)Z", (void *)OS_NATIVE(EnableMenuItem),
"EnableScrollBar", "(III)Z", (void *)OS_NATIVE(EnableScrollBar),
"EnableWindow", "(IZ)Z", (void *)OS_NATIVE(EnableWindow),
"EndDeferWindowPos", "(I)Z", (void *)OS_NATIVE(EndDeferWindowPos),
"EndDoc", "(I)I", (void *)OS_NATIVE(EndDoc),
"EndPage", "(I)I", (void *)OS_NATIVE(EndPage),
"EndPaint", "(ILorg.eclipse.swt.internal.win32.PAINTSTRUCT;)I", (void *)OS_NATIVE(EndPaint),
"EndPath", "(I)Z", (void *)OS_NATIVE(EndPath),
"EnumDisplayMonitors", "(ILorg.eclipse.swt.internal.win32.RECT;II)Z", (void *)OS_NATIVE(EnumDisplayMonitors),
"EnumFontFamiliesA", "(I[BII)I", (void *)OS_NATIVE(EnumFontFamiliesA),
"EnumFontFamiliesExA", "(ILorg.eclipse.swt.internal.win32.LOGFONTA;III)I", (void *)OS_NATIVE(EnumFontFamiliesExA),
"EnumFontFamiliesExW", "(ILorg.eclipse.swt.internal.win32.LOGFONTW;III)I", (void *)OS_NATIVE(EnumFontFamiliesExW),
"EnumFontFamiliesW", "(I[CII)I", (void *)OS_NATIVE(EnumFontFamiliesW),
"EnumSystemLanguageGroupsA", "(III)Z", (void *)OS_NATIVE(EnumSystemLanguageGroupsA),
"EnumSystemLanguageGroupsW", "(III)Z", (void *)OS_NATIVE(EnumSystemLanguageGroupsW),
"EnumSystemLocalesA", "(II)Z", (void *)OS_NATIVE(EnumSystemLocalesA),
"EnumSystemLocalesW", "(II)Z", (void *)OS_NATIVE(EnumSystemLocalesW),
"EqualRect", "(Lorg.eclipse.swt.internal.win32.RECT;Lorg.eclipse.swt.internal.win32.RECT;)Z", (void *)OS_NATIVE(EqualRect),
"EqualRgn", "(II)Z", (void *)OS_NATIVE(EqualRgn),
"ExcludeClipRect", "(IIIII)I", (void *)OS_NATIVE(ExcludeClipRect),
"ExpandEnvironmentStringsA", "([B[BI)I", (void *)OS_NATIVE(ExpandEnvironmentStringsA),
"ExpandEnvironmentStringsW", "([C[CI)I", (void *)OS_NATIVE(ExpandEnvironmentStringsW),
"ExtCreatePen", "(IILorg.eclipse.swt.internal.win32.LOGBRUSH;I[I)I", (void *)OS_NATIVE(ExtCreatePen),
"ExtCreateRegion", "([FI[I)I", (void *)OS_NATIVE(ExtCreateRegion),
"ExtTextOutA", "(IIIILorg.eclipse.swt.internal.win32.RECT;[BI[I)Z", (void *)OS_NATIVE(ExtTextOutA),
"ExtTextOutW", "(IIIILorg.eclipse.swt.internal.win32.RECT;[CI[I)Z", (void *)OS_NATIVE(ExtTextOutW),
"ExtractIconExA", "([BI[I[II)I", (void *)OS_NATIVE(ExtractIconExA),
"ExtractIconExW", "([CI[I[II)I", (void *)OS_NATIVE(ExtractIconExW),
"FillPath", "(I)Z", (void *)OS_NATIVE(FillPath),
"FillRect", "(ILorg.eclipse.swt.internal.win32.RECT;I)I", (void *)OS_NATIVE(FillRect),
"FindWindowA", "([B[B)I", (void *)OS_NATIVE(FindWindowA),
"FindWindowW", "([C[C)I", (void *)OS_NATIVE(FindWindowW),
"FormatMessageA", "(IIII[III)I", (void *)OS_NATIVE(FormatMessageA),
"FormatMessageW", "(IIII[III)I", (void *)OS_NATIVE(FormatMessageW),
"FreeLibrary", "(I)Z", (void *)OS_NATIVE(FreeLibrary),
"GdiSetBatchLimit", "(I)I", (void *)OS_NATIVE(GdiSetBatchLimit),
"GetACP", "()I", (void *)OS_NATIVE(GetACP),
"GetActiveWindow", "()I", (void *)OS_NATIVE(GetActiveWindow),
"GetBkColor", "(I)I", (void *)OS_NATIVE(GetBkColor),
"GetCapture", "()I", (void *)OS_NATIVE(GetCapture),
"GetCaretPos", "(Lorg.eclipse.swt.internal.win32.POINT;)Z", (void *)OS_NATIVE(GetCaretPos),
"GetCharABCWidthsA", "(III[I)Z", (void *)OS_NATIVE(GetCharABCWidthsA),
"GetCharABCWidthsW", "(III[I)Z", (void *)OS_NATIVE(GetCharABCWidthsW),
"GetCharWidthA", "(III[I)Z", (void *)OS_NATIVE(GetCharWidthA),
"GetCharWidthW", "(III[I)Z", (void *)OS_NATIVE(GetCharWidthW),
"GetCharacterPlacementA", "(I[BIILorg.eclipse.swt.internal.win32.GCP_RESULTS;I)I", (void *)OS_NATIVE(GetCharacterPlacementA),
"GetCharacterPlacementW", "(I[CIILorg.eclipse.swt.internal.win32.GCP_RESULTS;I)I", (void *)OS_NATIVE(GetCharacterPlacementW),
"GetClassInfoA", "(I[BLorg.eclipse.swt.internal.win32.WNDCLASS;)Z", (void *)OS_NATIVE(GetClassInfoA),
"GetClassInfoW", "(I[CLorg.eclipse.swt.internal.win32.WNDCLASS;)Z", (void *)OS_NATIVE(GetClassInfoW),
"GetClassNameA", "(I[BI)I", (void *)OS_NATIVE(GetClassNameA),
"GetClassNameW", "(I[CI)I", (void *)OS_NATIVE(GetClassNameW),
"GetClientRect", "(ILorg.eclipse.swt.internal.win32.RECT;)Z", (void *)OS_NATIVE(GetClientRect),
"GetClipBox", "(ILorg.eclipse.swt.internal.win32.RECT;)I", (void *)OS_NATIVE(GetClipBox),
"GetClipRgn", "(II)I", (void *)OS_NATIVE(GetClipRgn),
"GetClipboardData", "(I)I", (void *)OS_NATIVE(GetClipboardData),
"GetClipboardFormatNameA", "(I[BI)I", (void *)OS_NATIVE(GetClipboardFormatNameA),
"GetClipboardFormatNameW", "(I[CI)I", (void *)OS_NATIVE(GetClipboardFormatNameW),
"GetComboBoxInfo", "(ILorg.eclipse.swt.internal.win32.COMBOBOXINFO;)Z", (void *)OS_NATIVE(GetComboBoxInfo),
"GetCurrentObject", "(II)I", (void *)OS_NATIVE(GetCurrentObject),
"GetCurrentProcessId", "()I", (void *)OS_NATIVE(GetCurrentProcessId),
"GetCurrentThreadId", "()I", (void *)OS_NATIVE(GetCurrentThreadId),
"GetCursor", "()I", (void *)OS_NATIVE(GetCursor),
"GetCursorPos", "(Lorg.eclipse.swt.internal.win32.POINT;)Z", (void *)OS_NATIVE(GetCursorPos),
"GetDC", "(I)I", (void *)OS_NATIVE(GetDC),
"GetDCEx", "(III)I", (void *)OS_NATIVE(GetDCEx),
"GetDIBColorTable", "(III[B)I", (void *)OS_NATIVE(GetDIBColorTable),
"GetDIBits", "(IIIII[BI)I", (void *)OS_NATIVE(GetDIBits),
"GetDesktopWindow", "()I", (void *)OS_NATIVE(GetDesktopWindow),
"GetDeviceCaps", "(II)I", (void *)OS_NATIVE(GetDeviceCaps),
"GetDialogBaseUnits", "()I", (void *)OS_NATIVE(GetDialogBaseUnits),
"GetDlgItem", "(II)I", (void *)OS_NATIVE(GetDlgItem),
"GetDoubleClickTime", "()I", (void *)OS_NATIVE(GetDoubleClickTime),
"GetFocus", "()I", (void *)OS_NATIVE(GetFocus),
"GetFontLanguageInfo", "(I)I", (void *)OS_NATIVE(GetFontLanguageInfo),
"GetForegroundWindow", "()I", (void *)OS_NATIVE(GetForegroundWindow),
"GetGUIThreadInfo", "(ILorg.eclipse.swt.internal.win32.GUITHREADINFO;)Z", (void *)OS_NATIVE(GetGUIThreadInfo),
"GetIconInfo", "(ILorg.eclipse.swt.internal.win32.ICONINFO;)Z", (void *)OS_NATIVE(GetIconInfo),
"GetKeyNameTextA", "(I[BI)I", (void *)OS_NATIVE(GetKeyNameTextA),
"GetKeyNameTextW", "(I[CI)I", (void *)OS_NATIVE(GetKeyNameTextW),
"GetKeyState", "(I)S", (void *)OS_NATIVE(GetKeyState),
"GetKeyboardLayout", "(I)I", (void *)OS_NATIVE(GetKeyboardLayout),
"GetKeyboardLayoutList", "(I[I)I", (void *)OS_NATIVE(GetKeyboardLayoutList),
"GetKeyboardState", "([B)Z", (void *)OS_NATIVE(GetKeyboardState),
"GetLastActivePopup", "(I)I", (void *)OS_NATIVE(GetLastActivePopup),
"GetLastError", "()I", (void *)OS_NATIVE(GetLastError),
"GetLayout", "(I)I", (void *)OS_NATIVE(GetLayout),
"GetLibraryHandle", "()I", (void *)OS_NATIVE(GetLibraryHandle),
"GetLocaleInfoA", "(II[BI)I", (void *)OS_NATIVE(GetLocaleInfoA),
"GetLocaleInfoW", "(II[CI)I", (void *)OS_NATIVE(GetLocaleInfoW),
"GetMenu", "(I)I", (void *)OS_NATIVE(GetMenu),
"GetMenuBarInfo", "(IIILorg.eclipse.swt.internal.win32.MENUBARINFO;)Z", (void *)OS_NATIVE(GetMenuBarInfo),
"GetMenuDefaultItem", "(III)I", (void *)OS_NATIVE(GetMenuDefaultItem),
"GetMenuInfo", "(ILorg.eclipse.swt.internal.win32.MENUINFO;)Z", (void *)OS_NATIVE(GetMenuInfo),
"GetMenuItemCount", "(I)I", (void *)OS_NATIVE(GetMenuItemCount),
"GetMenuItemInfoA", "(IIZLorg.eclipse.swt.internal.win32.MENUITEMINFO;)Z", (void *)OS_NATIVE(GetMenuItemInfoA),
"GetMenuItemInfoW", "(IIZLorg.eclipse.swt.internal.win32.MENUITEMINFO;)Z", (void *)OS_NATIVE(GetMenuItemInfoW),
"GetMenuItemRect", "(IIILorg.eclipse.swt.internal.win32.RECT;)Z", (void *)OS_NATIVE(GetMenuItemRect),
"GetMessageA", "(Lorg.eclipse.swt.internal.win32.MSG;III)Z", (void *)OS_NATIVE(GetMessageA),
"GetMessagePos", "()I", (void *)OS_NATIVE(GetMessagePos),
"GetMessageTime", "()I", (void *)OS_NATIVE(GetMessageTime),
"GetMessageW", "(Lorg.eclipse.swt.internal.win32.MSG;III)Z", (void *)OS_NATIVE(GetMessageW),
"GetMetaRgn", "(II)I", (void *)OS_NATIVE(GetMetaRgn),
"GetModuleHandleA", "([B)I", (void *)OS_NATIVE(GetModuleHandleA),
"GetModuleHandleW", "([C)I", (void *)OS_NATIVE(GetModuleHandleW),
"GetMonitorInfoA", "(ILorg.eclipse.swt.internal.win32.MONITORINFO;)Z", (void *)OS_NATIVE(GetMonitorInfoA),
"GetMonitorInfoW", "(ILorg.eclipse.swt.internal.win32.MONITORINFO;)Z", (void *)OS_NATIVE(GetMonitorInfoW),
"GetNearestPaletteIndex", "(II)I", (void *)OS_NATIVE(GetNearestPaletteIndex),
"GetObjectA", "(III)I", (void *)OS_NATIVE(GetObjectA__III),
"GetObjectA", "(IILorg.eclipse.swt.internal.win32.BITMAP;)I", (void *)OS_NATIVE(GetObjectA__IILorg_eclipse_swt_internal_win32_BITMAP_2),
"GetObjectA", "(IILorg.eclipse.swt.internal.win32.DIBSECTION;)I", (void *)OS_NATIVE(GetObjectA__IILorg_eclipse_swt_internal_win32_DIBSECTION_2),
"GetObjectA", "(IILorg.eclipse.swt.internal.win32.EXTLOGPEN;)I", (void *)OS_NATIVE(GetObjectA__IILorg_eclipse_swt_internal_win32_EXTLOGPEN_2),
"GetObjectA", "(IILorg.eclipse.swt.internal.win32.LOGBRUSH;)I", (void *)OS_NATIVE(GetObjectA__IILorg_eclipse_swt_internal_win32_LOGBRUSH_2),
"GetObjectA", "(IILorg.eclipse.swt.internal.win32.LOGFONTA;)I", (void *)OS_NATIVE(GetObjectA__IILorg_eclipse_swt_internal_win32_LOGFONTA_2),
"GetObjectA", "(IILorg.eclipse.swt.internal.win32.LOGPEN;)I", (void *)OS_NATIVE(GetObjectA__IILorg_eclipse_swt_internal_win32_LOGPEN_2),
"GetObjectW", "(III)I", (void *)OS_NATIVE(GetObjectW__III),
"GetObjectW", "(IILorg.eclipse.swt.internal.win32.BITMAP;)I", (void *)OS_NATIVE(GetObjectW__IILorg_eclipse_swt_internal_win32_BITMAP_2),
"GetObjectW", "(IILorg.eclipse.swt.internal.win32.DIBSECTION;)I", (void *)OS_NATIVE(GetObjectW__IILorg_eclipse_swt_internal_win32_DIBSECTION_2),
"GetObjectW", "(IILorg.eclipse.swt.internal.win32.EXTLOGPEN;)I", (void *)OS_NATIVE(GetObjectW__IILorg_eclipse_swt_internal_win32_EXTLOGPEN_2),
"GetObjectW", "(IILorg.eclipse.swt.internal.win32.LOGBRUSH;)I", (void *)OS_NATIVE(GetObjectW__IILorg_eclipse_swt_internal_win32_LOGBRUSH_2),
"GetObjectW", "(IILorg.eclipse.swt.internal.win32.LOGFONTW;)I", (void *)OS_NATIVE(GetObjectW__IILorg_eclipse_swt_internal_win32_LOGFONTW_2),
"GetObjectW", "(IILorg.eclipse.swt.internal.win32.LOGPEN;)I", (void *)OS_NATIVE(GetObjectW__IILorg_eclipse_swt_internal_win32_LOGPEN_2),
"GetOpenFileNameA", "(Lorg.eclipse.swt.internal.win32.OPENFILENAME;)Z", (void *)OS_NATIVE(GetOpenFileNameA),
"GetOpenFileNameW", "(Lorg.eclipse.swt.internal.win32.OPENFILENAME;)Z", (void *)OS_NATIVE(GetOpenFileNameW),
"GetPaletteEntries", "(III[B)I", (void *)OS_NATIVE(GetPaletteEntries),
"GetParent", "(I)I", (void *)OS_NATIVE(GetParent),
"GetPixel", "(III)I", (void *)OS_NATIVE(GetPixel),
"GetPolyFillMode", "(I)I", (void *)OS_NATIVE(GetPolyFillMode),
"GetProcAddress", "(I[B)I", (void *)OS_NATIVE(GetProcAddress),
"GetProcessHeap", "()I", (void *)OS_NATIVE(GetProcessHeap),
"GetProfileStringA", "([B[B[B[BI)I", (void *)OS_NATIVE(GetProfileStringA),
"GetProfileStringW", "([C[C[C[CI)I", (void *)OS_NATIVE(GetProfileStringW),
"GetPropA", "(II)I", (void *)OS_NATIVE(GetPropA),
"GetPropW", "(II)I", (void *)OS_NATIVE(GetPropW),
"GetROP2", "(I)I", (void *)OS_NATIVE(GetROP2),
"GetRandomRgn", "(III)I", (void *)OS_NATIVE(GetRandomRgn),
"GetRegionData", "(II[I)I", (void *)OS_NATIVE(GetRegionData),
"GetRgnBox", "(ILorg.eclipse.swt.internal.win32.RECT;)I", (void *)OS_NATIVE(GetRgnBox),
"GetSaveFileNameA", "(Lorg.eclipse.swt.internal.win32.OPENFILENAME;)Z", (void *)OS_NATIVE(GetSaveFileNameA),
"GetSaveFileNameW", "(Lorg.eclipse.swt.internal.win32.OPENFILENAME;)Z", (void *)OS_NATIVE(GetSaveFileNameW),
"GetScrollInfo", "(IILorg.eclipse.swt.internal.win32.SCROLLINFO;)Z", (void *)OS_NATIVE(GetScrollInfo),
"GetStockObject", "(I)I", (void *)OS_NATIVE(GetStockObject),
"GetSysColor", "(I)I", (void *)OS_NATIVE(GetSysColor),
"GetSysColorBrush", "(I)I", (void *)OS_NATIVE(GetSysColorBrush),
"GetSystemDefaultUILanguage", "()S", (void *)OS_NATIVE(GetSystemDefaultUILanguage),
"GetSystemMenu", "(IZ)I", (void *)OS_NATIVE(GetSystemMenu),
"GetSystemMetrics", "(I)I", (void *)OS_NATIVE(GetSystemMetrics),
"GetSystemPaletteEntries", "(III[B)I", (void *)OS_NATIVE(GetSystemPaletteEntries),
"GetTextCharset", "(I)I", (void *)OS_NATIVE(GetTextCharset),
"GetTextColor", "(I)I", (void *)OS_NATIVE(GetTextColor),
"GetTextExtentPoint32A", "(I[BILorg.eclipse.swt.internal.win32.SIZE;)Z", (void *)OS_NATIVE(GetTextExtentPoint32A),
"GetTextExtentPoint32W", "(I[CILorg.eclipse.swt.internal.win32.SIZE;)Z", (void *)OS_NATIVE(GetTextExtentPoint32W),
"GetTextMetricsA", "(ILorg.eclipse.swt.internal.win32.TEXTMETRICA;)Z", (void *)OS_NATIVE(GetTextMetricsA),
"GetTextMetricsW", "(ILorg.eclipse.swt.internal.win32.TEXTMETRICW;)Z", (void *)OS_NATIVE(GetTextMetricsW),
"GetTickCount", "()I", (void *)OS_NATIVE(GetTickCount),
"GetUpdateRect", "(ILorg.eclipse.swt.internal.win32.RECT;Z)Z", (void *)OS_NATIVE(GetUpdateRect),
"GetUpdateRgn", "(IIZ)I", (void *)OS_NATIVE(GetUpdateRgn),
"GetVersionExA", "(Lorg.eclipse.swt.internal.win32.OSVERSIONINFOA;)Z", (void *)OS_NATIVE(GetVersionExA),
"GetVersionExW", "(Lorg.eclipse.swt.internal.win32.OSVERSIONINFOW;)Z", (void *)OS_NATIVE(GetVersionExW),
"GetWindow", "(II)I", (void *)OS_NATIVE(GetWindow),
"GetWindowLongA", "(II)I", (void *)OS_NATIVE(GetWindowLongA),
"GetWindowLongW", "(II)I", (void *)OS_NATIVE(GetWindowLongW),
"GetWindowOrgEx", "(ILorg.eclipse.swt.internal.win32.POINT;)Z", (void *)OS_NATIVE(GetWindowOrgEx),
"GetWindowPlacement", "(ILorg.eclipse.swt.internal.win32.WINDOWPLACEMENT;)Z", (void *)OS_NATIVE(GetWindowPlacement),
"GetWindowRect", "(ILorg.eclipse.swt.internal.win32.RECT;)Z", (void *)OS_NATIVE(GetWindowRect),
"GetWindowRgn", "(II)I", (void *)OS_NATIVE(GetWindowRgn),
"GetWindowTextA", "(I[BI)I", (void *)OS_NATIVE(GetWindowTextA),
"GetWindowTextLengthA", "(I)I", (void *)OS_NATIVE(GetWindowTextLengthA),
"GetWindowTextLengthW", "(I)I", (void *)OS_NATIVE(GetWindowTextLengthW),
"GetWindowTextW", "(I[CI)I", (void *)OS_NATIVE(GetWindowTextW),
"GetWindowThreadProcessId", "(I[I)I", (void *)OS_NATIVE(GetWindowThreadProcessId),
"GetWorldTransform", "(I[F)Z", (void *)OS_NATIVE(GetWorldTransform),
"GlobalAddAtomA", "([B)I", (void *)OS_NATIVE(GlobalAddAtomA),
"GlobalAddAtomW", "([C)I", (void *)OS_NATIVE(GlobalAddAtomW),
"GlobalAlloc", "(II)I", (void *)OS_NATIVE(GlobalAlloc),
"GlobalFree", "(I)I", (void *)OS_NATIVE(GlobalFree),
"GlobalLock", "(I)I", (void *)OS_NATIVE(GlobalLock),
"GlobalSize", "(I)I", (void *)OS_NATIVE(GlobalSize),
"GlobalUnlock", "(I)Z", (void *)OS_NATIVE(GlobalUnlock),
"GradientFill", "(IIIIII)Z", (void *)OS_NATIVE(GradientFill),
"HeapAlloc", "(III)I", (void *)OS_NATIVE(HeapAlloc),
"HeapFree", "(III)Z", (void *)OS_NATIVE(HeapFree),
"HideCaret", "(I)Z", (void *)OS_NATIVE(HideCaret),
"IIDFromString", "([C[B)I", (void *)OS_NATIVE(IIDFromString),
"ImageList_Add", "(III)I", (void *)OS_NATIVE(ImageList_1Add),
"ImageList_AddMasked", "(III)I", (void *)OS_NATIVE(ImageList_1AddMasked),
"ImageList_Create", "(IIIII)I", (void *)OS_NATIVE(ImageList_1Create),
"ImageList_Destroy", "(I)Z", (void *)OS_NATIVE(ImageList_1Destroy),
"ImageList_GetIcon", "(III)I", (void *)OS_NATIVE(ImageList_1GetIcon),
"ImageList_GetIconSize", "(I[I[I)Z", (void *)OS_NATIVE(ImageList_1GetIconSize),
"ImageList_GetImageCount", "(I)I", (void *)OS_NATIVE(ImageList_1GetImageCount),
"ImageList_Remove", "(II)Z", (void *)OS_NATIVE(ImageList_1Remove),
"ImageList_Replace", "(IIII)Z", (void *)OS_NATIVE(ImageList_1Replace),
"ImageList_ReplaceIcon", "(III)I", (void *)OS_NATIVE(ImageList_1ReplaceIcon),
"ImageList_SetIconSize", "(III)Z", (void *)OS_NATIVE(ImageList_1SetIconSize),
"ImmAssociateContext", "(II)I", (void *)OS_NATIVE(ImmAssociateContext),
"ImmCreateContext", "()I", (void *)OS_NATIVE(ImmCreateContext),
"ImmDestroyContext", "(I)Z", (void *)OS_NATIVE(ImmDestroyContext),
"ImmDisableTextFrameService", "(I)Z", (void *)OS_NATIVE(ImmDisableTextFrameService),
"ImmGetCompositionFontA", "(ILorg.eclipse.swt.internal.win32.LOGFONTA;)Z", (void *)OS_NATIVE(ImmGetCompositionFontA),
"ImmGetCompositionFontW", "(ILorg.eclipse.swt.internal.win32.LOGFONTW;)Z", (void *)OS_NATIVE(ImmGetCompositionFontW),
"ImmGetCompositionStringA", "(II[BI)I", (void *)OS_NATIVE(ImmGetCompositionStringA),
"ImmGetCompositionStringW", "(II[CI)I", (void *)OS_NATIVE(ImmGetCompositionStringW),
"ImmGetContext", "(I)I", (void *)OS_NATIVE(ImmGetContext),
"ImmGetConversionStatus", "(I[I[I)Z", (void *)OS_NATIVE(ImmGetConversionStatus),
"ImmGetDefaultIMEWnd", "(I)I", (void *)OS_NATIVE(ImmGetDefaultIMEWnd),
"ImmGetOpenStatus", "(I)Z", (void *)OS_NATIVE(ImmGetOpenStatus),
"ImmReleaseContext", "(II)Z", (void *)OS_NATIVE(ImmReleaseContext),
"ImmSetCompositionFontA", "(ILorg.eclipse.swt.internal.win32.LOGFONTA;)Z", (void *)OS_NATIVE(ImmSetCompositionFontA),
"ImmSetCompositionFontW", "(ILorg.eclipse.swt.internal.win32.LOGFONTW;)Z", (void *)OS_NATIVE(ImmSetCompositionFontW),
"ImmSetCompositionWindow", "(ILorg.eclipse.swt.internal.win32.COMPOSITIONFORM;)Z", (void *)OS_NATIVE(ImmSetCompositionWindow),
"ImmSetConversionStatus", "(III)Z", (void *)OS_NATIVE(ImmSetConversionStatus),
"ImmSetOpenStatus", "(IZ)Z", (void *)OS_NATIVE(ImmSetOpenStatus),
"InitCommonControls", "()V", (void *)OS_NATIVE(InitCommonControls),
"InitCommonControlsEx", "(Lorg.eclipse.swt.internal.win32.INITCOMMONCONTROLSEX;)Z", (void *)OS_NATIVE(InitCommonControlsEx),
"InsertMenuA", "(IIII[B)Z", (void *)OS_NATIVE(InsertMenuA),
"InsertMenuItemA", "(IIZLorg.eclipse.swt.internal.win32.MENUITEMINFO;)Z", (void *)OS_NATIVE(InsertMenuItemA),
"InsertMenuItemW", "(IIZLorg.eclipse.swt.internal.win32.MENUITEMINFO;)Z", (void *)OS_NATIVE(InsertMenuItemW),
"InsertMenuW", "(IIII[C)Z", (void *)OS_NATIVE(InsertMenuW),
"IntersectClipRect", "(IIIII)I", (void *)OS_NATIVE(IntersectClipRect),
"IntersectRect", "(Lorg.eclipse.swt.internal.win32.RECT;Lorg.eclipse.swt.internal.win32.RECT;Lorg.eclipse.swt.internal.win32.RECT;)Z", (void *)OS_NATIVE(IntersectRect),
"InvalidateRect", "(ILorg.eclipse.swt.internal.win32.RECT;Z)Z", (void *)OS_NATIVE(InvalidateRect),
"InvalidateRgn", "(IIZ)Z", (void *)OS_NATIVE(InvalidateRgn),
"IsAppThemed", "()Z", (void *)OS_NATIVE(IsAppThemed),
"IsDBCSLeadByte", "(B)Z", (void *)OS_NATIVE(IsDBCSLeadByte),
"IsHungAppWindow", "(I)Z", (void *)OS_NATIVE(IsHungAppWindow),
"IsIconic", "(I)Z", (void *)OS_NATIVE(IsIconic),
"IsPPC", "()Z", (void *)OS_NATIVE(IsPPC),
"IsSP", "()Z", (void *)OS_NATIVE(IsSP),
"IsWindowEnabled", "(I)Z", (void *)OS_NATIVE(IsWindowEnabled),
"IsWindowVisible", "(I)Z", (void *)OS_NATIVE(IsWindowVisible),
"IsZoomed", "(I)Z", (void *)OS_NATIVE(IsZoomed),
"KillTimer", "(II)Z", (void *)OS_NATIVE(KillTimer),
"LineTo", "(III)Z", (void *)OS_NATIVE(LineTo),
"LoadBitmapA", "(II)I", (void *)OS_NATIVE(LoadBitmapA),
"LoadBitmapW", "(II)I", (void *)OS_NATIVE(LoadBitmapW),
"LoadCursorA", "(II)I", (void *)OS_NATIVE(LoadCursorA),
"LoadCursorW", "(II)I", (void *)OS_NATIVE(LoadCursorW),
"LoadIconA", "(II)I", (void *)OS_NATIVE(LoadIconA),
"LoadIconW", "(II)I", (void *)OS_NATIVE(LoadIconW),
"LoadImageA", "(IIIIII)I", (void *)OS_NATIVE(LoadImageA__IIIIII),
"LoadImageA", "(I[BIIII)I", (void *)OS_NATIVE(LoadImageA__I_3BIIII),
"LoadImageW", "(IIIIII)I", (void *)OS_NATIVE(LoadImageW__IIIIII),
"LoadImageW", "(I[CIIII)I", (void *)OS_NATIVE(LoadImageW__I_3CIIII),
"LoadLibraryA", "([B)I", (void *)OS_NATIVE(LoadLibraryA),
"LoadLibraryW", "([C)I", (void *)OS_NATIVE(LoadLibraryW),
"LoadStringA", "(II[BI)I", (void *)OS_NATIVE(LoadStringA),
"LoadStringW", "(II[CI)I", (void *)OS_NATIVE(LoadStringW),
"LocalFree", "(I)I", (void *)OS_NATIVE(LocalFree),
"MapVirtualKeyA", "(II)I", (void *)OS_NATIVE(MapVirtualKeyA),
"MapVirtualKeyW", "(II)I", (void *)OS_NATIVE(MapVirtualKeyW),
"MapWindowPoints", "(IILorg.eclipse.swt.internal.win32.POINT;I)I", (void *)OS_NATIVE(MapWindowPoints__IILorg_eclipse_swt_internal_win32_POINT_2I),
"MapWindowPoints", "(IILorg.eclipse.swt.internal.win32.RECT;I)I", (void *)OS_NATIVE(MapWindowPoints__IILorg_eclipse_swt_internal_win32_RECT_2I),
"MessageBeep", "(I)Z", (void *)OS_NATIVE(MessageBeep),
"MessageBoxA", "(I[B[BI)I", (void *)OS_NATIVE(MessageBoxA),
"MessageBoxW", "(I[C[CI)I", (void *)OS_NATIVE(MessageBoxW),
"MonitorFromWindow", "(II)I", (void *)OS_NATIVE(MonitorFromWindow),
"MoveMemory", "(ILorg.eclipse.swt.internal.win32.DROPFILES;I)V", (void *)OS_NATIVE(MoveMemory__ILorg_eclipse_swt_internal_win32_DROPFILES_2I),
"MoveMemory", "(ILorg.eclipse.swt.internal.win32.GRADIENT_RECT;I)V", (void *)OS_NATIVE(MoveMemory__ILorg_eclipse_swt_internal_win32_GRADIENT_1RECT_2I),
"MoveMemory", "(ILorg.eclipse.swt.internal.win32.KEYBDINPUT;I)V", (void *)OS_NATIVE(MoveMemory__ILorg_eclipse_swt_internal_win32_KEYBDINPUT_2I),
"MoveMemory", "(ILorg.eclipse.swt.internal.win32.LOGFONTA;I)V", (void *)OS_NATIVE(MoveMemory__ILorg_eclipse_swt_internal_win32_LOGFONTA_2I),
"MoveMemory", "(ILorg.eclipse.swt.internal.win32.LOGFONTW;I)V", (void *)OS_NATIVE(MoveMemory__ILorg_eclipse_swt_internal_win32_LOGFONTW_2I),
"MoveMemory", "(ILorg.eclipse.swt.internal.win32.MEASUREITEMSTRUCT;I)V", (void *)OS_NATIVE(MoveMemory__ILorg_eclipse_swt_internal_win32_MEASUREITEMSTRUCT_2I),
"MoveMemory", "(ILorg.eclipse.swt.internal.win32.MINMAXINFO;I)V", (void *)OS_NATIVE(MoveMemory__ILorg_eclipse_swt_internal_win32_MINMAXINFO_2I),
"MoveMemory", "(ILorg.eclipse.swt.internal.win32.MOUSEINPUT;I)V", (void *)OS_NATIVE(MoveMemory__ILorg_eclipse_swt_internal_win32_MOUSEINPUT_2I),
"MoveMemory", "(ILorg.eclipse.swt.internal.win32.MSG;I)V", (void *)OS_NATIVE(MoveMemory__ILorg_eclipse_swt_internal_win32_MSG_2I),
"MoveMemory", "(ILorg.eclipse.swt.internal.win32.NMLVCUSTOMDRAW;I)V", (void *)OS_NATIVE(MoveMemory__ILorg_eclipse_swt_internal_win32_NMLVCUSTOMDRAW_2I),
"MoveMemory", "(ILorg.eclipse.swt.internal.win32.NMLVDISPINFO;I)V", (void *)OS_NATIVE(MoveMemory__ILorg_eclipse_swt_internal_win32_NMLVDISPINFO_2I),
"MoveMemory", "(ILorg.eclipse.swt.internal.win32.NMTTDISPINFOA;I)V", (void *)OS_NATIVE(MoveMemory__ILorg_eclipse_swt_internal_win32_NMTTDISPINFOA_2I),
"MoveMemory", "(ILorg.eclipse.swt.internal.win32.NMTTDISPINFOW;I)V", (void *)OS_NATIVE(MoveMemory__ILorg_eclipse_swt_internal_win32_NMTTDISPINFOW_2I),
"MoveMemory", "(ILorg.eclipse.swt.internal.win32.NMTVCUSTOMDRAW;I)V", (void *)OS_NATIVE(MoveMemory__ILorg_eclipse_swt_internal_win32_NMTVCUSTOMDRAW_2I),
"MoveMemory", "(ILorg.eclipse.swt.internal.win32.NMTVDISPINFO;I)V", (void *)OS_NATIVE(MoveMemory__ILorg_eclipse_swt_internal_win32_NMTVDISPINFO_2I),
"MoveMemory", "(ILorg.eclipse.swt.internal.win32.RECT;I)V", (void *)OS_NATIVE(MoveMemory__ILorg_eclipse_swt_internal_win32_RECT_2I),
"MoveMemory", "(ILorg.eclipse.swt.internal.win32.TRIVERTEX;I)V", (void *)OS_NATIVE(MoveMemory__ILorg_eclipse_swt_internal_win32_TRIVERTEX_2I),
"MoveMemory", "(ILorg.eclipse.swt.internal.win32.UDACCEL;I)V", (void *)OS_NATIVE(MoveMemory__ILorg_eclipse_swt_internal_win32_UDACCEL_2I),
"MoveMemory", "(ILorg.eclipse.swt.internal.win32.WINDOWPOS;I)V", (void *)OS_NATIVE(MoveMemory__ILorg_eclipse_swt_internal_win32_WINDOWPOS_2I),
"MoveMemory", "(I[BI)V", (void *)OS_NATIVE(MoveMemory__I_3BI),
"MoveMemory", "(I[CI)V", (void *)OS_NATIVE(MoveMemory__I_3CI),
"MoveMemory", "(I[DI)V", (void *)OS_NATIVE(MoveMemory__I_3DI),
"MoveMemory", "(I[FI)V", (void *)OS_NATIVE(MoveMemory__I_3FI),
"MoveMemory", "(I[II)V", (void *)OS_NATIVE(MoveMemory__I_3II),
"MoveMemory", "(I[SI)V", (void *)OS_NATIVE(MoveMemory__I_3SI),
"MoveMemory", "(Lorg.eclipse.swt.internal.win32.BITMAPINFOHEADER;[BI)V", (void *)OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_BITMAPINFOHEADER_2_3BI),
"MoveMemory", "(Lorg.eclipse.swt.internal.win32.DRAWITEMSTRUCT;II)V", (void *)OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_DRAWITEMSTRUCT_2II),
"MoveMemory", "(Lorg.eclipse.swt.internal.win32.EXTLOGPEN;II)V", (void *)OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_EXTLOGPEN_2II),
"MoveMemory", "(Lorg.eclipse.swt.internal.win32.HDITEM;II)V", (void *)OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_HDITEM_2II),
"MoveMemory", "(Lorg.eclipse.swt.internal.win32.HELPINFO;II)V", (void *)OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_HELPINFO_2II),
"MoveMemory", "(Lorg.eclipse.swt.internal.win32.LOGFONTA;II)V", (void *)OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_LOGFONTA_2II),
"MoveMemory", "(Lorg.eclipse.swt.internal.win32.LOGFONTW;II)V", (void *)OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_LOGFONTW_2II),
"MoveMemory", "(Lorg.eclipse.swt.internal.win32.MEASUREITEMSTRUCT;II)V", (void *)OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_MEASUREITEMSTRUCT_2II),
"MoveMemory", "(Lorg.eclipse.swt.internal.win32.MINMAXINFO;II)V", (void *)OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_MINMAXINFO_2II),
"MoveMemory", "(Lorg.eclipse.swt.internal.win32.MSG;II)V", (void *)OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_MSG_2II),
"MoveMemory", "(Lorg.eclipse.swt.internal.win32.NMCUSTOMDRAW;II)V", (void *)OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_NMCUSTOMDRAW_2II),
"MoveMemory", "(Lorg.eclipse.swt.internal.win32.NMHDR;II)V", (void *)OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_NMHDR_2II),
"MoveMemory", "(Lorg.eclipse.swt.internal.win32.NMHEADER;II)V", (void *)OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_NMHEADER_2II),
"MoveMemory", "(Lorg.eclipse.swt.internal.win32.NMLINK;II)V", (void *)OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_NMLINK_2II),
"MoveMemory", "(Lorg.eclipse.swt.internal.win32.NMLISTVIEW;II)V", (void *)OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_NMLISTVIEW_2II),
"MoveMemory", "(Lorg.eclipse.swt.internal.win32.NMLVCUSTOMDRAW;II)V", (void *)OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_NMLVCUSTOMDRAW_2II),
"MoveMemory", "(Lorg.eclipse.swt.internal.win32.NMLVDISPINFO;II)V", (void *)OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_NMLVDISPINFO_2II),
"MoveMemory", "(Lorg.eclipse.swt.internal.win32.NMLVFINDITEM;II)V", (void *)OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_NMLVFINDITEM_2II),
"MoveMemory", "(Lorg.eclipse.swt.internal.win32.NMREBARCHEVRON;II)V", (void *)OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_NMREBARCHEVRON_2II),
"MoveMemory", "(Lorg.eclipse.swt.internal.win32.NMREBARCHILDSIZE;II)V", (void *)OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_NMREBARCHILDSIZE_2II),
#ifdef WIN32_PLATFORM_PSPC
"MoveMemory", "(Lorg.eclipse.swt.internal.win32.NMRGINFO;II)V", (void *)OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_NMRGINFO_2II),
#endif
"MoveMemory", "(Lorg.eclipse.swt.internal.win32.NMTBHOTITEM;II)V", (void *)OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_NMTBHOTITEM_2II),
"MoveMemory", "(Lorg.eclipse.swt.internal.win32.NMTOOLBAR;II)V", (void *)OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_NMTOOLBAR_2II),
"MoveMemory", "(Lorg.eclipse.swt.internal.win32.NMTTDISPINFOA;II)V", (void *)OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_NMTTDISPINFOA_2II),
"MoveMemory", "(Lorg.eclipse.swt.internal.win32.NMTTDISPINFOW;II)V", (void *)OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_NMTTDISPINFOW_2II),
"MoveMemory", "(Lorg.eclipse.swt.internal.win32.NMTVCUSTOMDRAW;II)V", (void *)OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_NMTVCUSTOMDRAW_2II),
"MoveMemory", "(Lorg.eclipse.swt.internal.win32.NMTVDISPINFO;II)V", (void *)OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_NMTVDISPINFO_2II),
"MoveMemory", "(Lorg.eclipse.swt.internal.win32.NMUPDOWN;II)V", (void *)OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_NMUPDOWN_2II),
"MoveMemory", "(Lorg.eclipse.swt.internal.win32.POINT;II)V", (void *)OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_POINT_2II),
"MoveMemory", "(Lorg.eclipse.swt.internal.win32.SCRIPT_ITEM;II)V", (void *)OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_SCRIPT_1ITEM_2II),
"MoveMemory", "(Lorg.eclipse.swt.internal.win32.SCRIPT_LOGATTR;II)V", (void *)OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_SCRIPT_1LOGATTR_2II),
"MoveMemory", "(Lorg.eclipse.swt.internal.win32.SCRIPT_PROPERTIES;II)V", (void *)OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_SCRIPT_1PROPERTIES_2II),
"MoveMemory", "(Lorg.eclipse.swt.internal.win32.TEXTMETRICA;II)V", (void *)OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_TEXTMETRICA_2II),
"MoveMemory", "(Lorg.eclipse.swt.internal.win32.TEXTMETRICW;II)V", (void *)OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_TEXTMETRICW_2II),
"MoveMemory", "(Lorg.eclipse.swt.internal.win32.TVITEM;II)V", (void *)OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_TVITEM_2II),
"MoveMemory", "(Lorg.eclipse.swt.internal.win32.UDACCEL;II)V", (void *)OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_UDACCEL_2II),
"MoveMemory", "(Lorg.eclipse.swt.internal.win32.WINDOWPOS;II)V", (void *)OS_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_WINDOWPOS_2II),
"MoveMemory", "([BII)V", (void *)OS_NATIVE(MoveMemory___3BII),
"MoveMemory", "([BLorg.eclipse.swt.internal.win32.ACCEL;I)V", (void *)OS_NATIVE(MoveMemory___3BLorg_eclipse_swt_internal_win32_ACCEL_2I),
"MoveMemory", "([BLorg.eclipse.swt.internal.win32.BITMAPINFOHEADER;I)V", (void *)OS_NATIVE(MoveMemory___3BLorg_eclipse_swt_internal_win32_BITMAPINFOHEADER_2I),
"MoveMemory", "([CII)V", (void *)OS_NATIVE(MoveMemory___3CII),
"MoveMemory", "([DII)V", (void *)OS_NATIVE(MoveMemory___3DII),
"MoveMemory", "([FII)V", (void *)OS_NATIVE(MoveMemory___3FII),
"MoveMemory", "([III)V", (void *)OS_NATIVE(MoveMemory___3III),
"MoveMemory", "([SII)V", (void *)OS_NATIVE(MoveMemory___3SII),
"MoveToEx", "(IIII)Z", (void *)OS_NATIVE(MoveToEx),
"MsgWaitForMultipleObjectsEx", "(IIIII)I", (void *)OS_NATIVE(MsgWaitForMultipleObjectsEx),
"MultiByteToWideChar", "(IIII[CI)I", (void *)OS_NATIVE(MultiByteToWideChar__IIII_3CI),
"MultiByteToWideChar", "(II[BI[CI)I", (void *)OS_NATIVE(MultiByteToWideChar__II_3BI_3CI),
"NotifyWinEvent", "(IIII)V", (void *)OS_NATIVE(NotifyWinEvent),
"OffsetRect", "(Lorg.eclipse.swt.internal.win32.RECT;II)Z", (void *)OS_NATIVE(OffsetRect),
"OffsetRgn", "(III)I", (void *)OS_NATIVE(OffsetRgn),
"OleInitialize", "(I)I", (void *)OS_NATIVE(OleInitialize),
"OleUninitialize", "()V", (void *)OS_NATIVE(OleUninitialize),
"OpenClipboard", "(I)Z", (void *)OS_NATIVE(OpenClipboard),
"OpenThemeData", "(I[C)I", (void *)OS_NATIVE(OpenThemeData),
"PRIMARYLANGID", "(S)S", (void *)OS_NATIVE(PRIMARYLANGID),
"PatBlt", "(IIIIII)Z", (void *)OS_NATIVE(PatBlt),
"PeekMessageA", "(Lorg.eclipse.swt.internal.win32.MSG;IIII)Z", (void *)OS_NATIVE(PeekMessageA),
"PeekMessageW", "(Lorg.eclipse.swt.internal.win32.MSG;IIII)Z", (void *)OS_NATIVE(PeekMessageW),
"Pie", "(IIIIIIIII)Z", (void *)OS_NATIVE(Pie),
"Polygon", "(I[II)Z", (void *)OS_NATIVE(Polygon),
"Polyline", "(I[II)Z", (void *)OS_NATIVE(Polyline),
"PostMessageA", "(IIII)Z", (void *)OS_NATIVE(PostMessageA),
"PostMessageW", "(IIII)Z", (void *)OS_NATIVE(PostMessageW),
"PostThreadMessageA", "(IIII)Z", (void *)OS_NATIVE(PostThreadMessageA),
"PostThreadMessageW", "(IIII)Z", (void *)OS_NATIVE(PostThreadMessageW),
"PrintDlgA", "(Lorg.eclipse.swt.internal.win32.PRINTDLG;)Z", (void *)OS_NATIVE(PrintDlgA),
"PrintDlgW", "(Lorg.eclipse.swt.internal.win32.PRINTDLG;)Z", (void *)OS_NATIVE(PrintDlgW),
"PtInRect", "(Lorg.eclipse.swt.internal.win32.RECT;Lorg.eclipse.swt.internal.win32.POINT;)Z", (void *)OS_NATIVE(PtInRect),
"PtInRegion", "(III)Z", (void *)OS_NATIVE(PtInRegion),
"RealizePalette", "(I)I", (void *)OS_NATIVE(RealizePalette),
"RectInRegion", "(ILorg.eclipse.swt.internal.win32.RECT;)Z", (void *)OS_NATIVE(RectInRegion),
"Rectangle", "(IIIII)Z", (void *)OS_NATIVE(Rectangle),
"RedrawWindow", "(ILorg.eclipse.swt.internal.win32.RECT;II)Z", (void *)OS_NATIVE(RedrawWindow),
"RegCloseKey", "(I)I", (void *)OS_NATIVE(RegCloseKey),
"RegEnumKeyExA", "(II[B[I[I[B[ILorg.eclipse.swt.internal.win32.FILETIME;)I", (void *)OS_NATIVE(RegEnumKeyExA),
"RegEnumKeyExW", "(II[C[I[I[C[ILorg.eclipse.swt.internal.win32.FILETIME;)I", (void *)OS_NATIVE(RegEnumKeyExW),
"RegOpenKeyExA", "(I[BII[I)I", (void *)OS_NATIVE(RegOpenKeyExA),
"RegOpenKeyExW", "(I[CII[I)I", (void *)OS_NATIVE(RegOpenKeyExW),
"RegQueryInfoKeyA", "(II[II[I[I[I[I[I[I[II)I", (void *)OS_NATIVE(RegQueryInfoKeyA),
"RegQueryInfoKeyW", "(II[II[I[I[I[I[I[I[II)I", (void *)OS_NATIVE(RegQueryInfoKeyW),
"RegQueryValueExA", "(I[BI[I[B[I)I", (void *)OS_NATIVE(RegQueryValueExA__I_3BI_3I_3B_3I),
"RegQueryValueExA", "(I[BI[I[I[I)I", (void *)OS_NATIVE(RegQueryValueExA__I_3BI_3I_3I_3I),
"RegQueryValueExW", "(I[CI[I[C[I)I", (void *)OS_NATIVE(RegQueryValueExW__I_3CI_3I_3C_3I),
"RegQueryValueExW", "(I[CI[I[I[I)I", (void *)OS_NATIVE(RegQueryValueExW__I_3CI_3I_3I_3I),
"RegisterClassA", "(Lorg.eclipse.swt.internal.win32.WNDCLASS;)I", (void *)OS_NATIVE(RegisterClassA),
"RegisterClassW", "(Lorg.eclipse.swt.internal.win32.WNDCLASS;)I", (void *)OS_NATIVE(RegisterClassW),
"RegisterClipboardFormatA", "([B)I", (void *)OS_NATIVE(RegisterClipboardFormatA),
"RegisterClipboardFormatW", "([C)I", (void *)OS_NATIVE(RegisterClipboardFormatW),
"RegisterWindowMessageA", "([B)I", (void *)OS_NATIVE(RegisterWindowMessageA),
"RegisterWindowMessageW", "([C)I", (void *)OS_NATIVE(RegisterWindowMessageW),
"ReleaseCapture", "()Z", (void *)OS_NATIVE(ReleaseCapture),
"ReleaseDC", "(II)I", (void *)OS_NATIVE(ReleaseDC),
"RemoveMenu", "(III)Z", (void *)OS_NATIVE(RemoveMenu),
"RemovePropA", "(II)I", (void *)OS_NATIVE(RemovePropA),
"RemovePropW", "(II)I", (void *)OS_NATIVE(RemovePropW),
"RestoreDC", "(II)Z", (void *)OS_NATIVE(RestoreDC),
"RoundRect", "(IIIIIII)Z", (void *)OS_NATIVE(RoundRect),
"SHBrowseForFolderA", "(Lorg.eclipse.swt.internal.win32.BROWSEINFO;)I", (void *)OS_NATIVE(SHBrowseForFolderA),
"SHBrowseForFolderW", "(Lorg.eclipse.swt.internal.win32.BROWSEINFO;)I", (void *)OS_NATIVE(SHBrowseForFolderW),
#if defined(WIN32_PLATFORM_PSPC) || defined(WIN32_PLATFORM_WFSP)
"SHCreateMenuBar", "(Lorg.eclipse.swt.internal.win32.SHMENUBARINFO;)Z", (void *)OS_NATIVE(SHCreateMenuBar),
#endif
"SHGetMalloc", "([I)I", (void *)OS_NATIVE(SHGetMalloc),
"SHGetPathFromIDListA", "(I[B)Z", (void *)OS_NATIVE(SHGetPathFromIDListA),
"SHGetPathFromIDListW", "(I[C)Z", (void *)OS_NATIVE(SHGetPathFromIDListW),
#ifdef WIN32_PLATFORM_PSPC
"SHHandleWMSettingChange", "(IIILorg.eclipse.swt.internal.win32.SHACTIVATEINFO;)Z", (void *)OS_NATIVE(SHHandleWMSettingChange),
"SHRecognizeGesture", "(Lorg.eclipse.swt.internal.win32.SHRGINFO;)I", (void *)OS_NATIVE(SHRecognizeGesture),
#endif
#ifdef WIN32_PLATFORM_WFSP
"SHSendBackToFocusWindow", "(III)V", (void *)OS_NATIVE(SHSendBackToFocusWindow),
#endif
#if defined(WIN32_PLATFORM_PSPC) || defined(WIN32_PLATFORM_WFSP)
"SHSetAppKeyWndAssoc", "(BI)Z", (void *)OS_NATIVE(SHSetAppKeyWndAssoc),
#endif
#ifdef WIN32_PLATFORM_PSPC
"SHSipPreference", "(II)Z", (void *)OS_NATIVE(SHSipPreference),
#endif
"SaveDC", "(I)I", (void *)OS_NATIVE(SaveDC),
"ScreenToClient", "(ILorg.eclipse.swt.internal.win32.POINT;)Z", (void *)OS_NATIVE(ScreenToClient),
"ScriptBreak", "([CILorg.eclipse.swt.internal.win32.SCRIPT_ANALYSIS;I)I", (void *)OS_NATIVE(ScriptBreak),
"ScriptCPtoX", "(IZIIIIILorg.eclipse.swt.internal.win32.SCRIPT_ANALYSIS;[I)I", (void *)OS_NATIVE(ScriptCPtoX),
"ScriptCacheGetHeight", "(II[I)I", (void *)OS_NATIVE(ScriptCacheGetHeight),
"ScriptFreeCache", "(I)I", (void *)OS_NATIVE(ScriptFreeCache),
"ScriptGetFontProperties", "(IILorg.eclipse.swt.internal.win32.SCRIPT_FONTPROPERTIES;)I", (void *)OS_NATIVE(ScriptGetFontProperties),
"ScriptGetLogicalWidths", "(Lorg.eclipse.swt.internal.win32.SCRIPT_ANALYSIS;IIIII[I)I", (void *)OS_NATIVE(ScriptGetLogicalWidths),
"ScriptGetProperties", "([I[I)I", (void *)OS_NATIVE(ScriptGetProperties),
"ScriptItemize", "([CIILorg.eclipse.swt.internal.win32.SCRIPT_CONTROL;Lorg.eclipse.swt.internal.win32.SCRIPT_STATE;I[I)I", (void *)OS_NATIVE(ScriptItemize),
"ScriptLayout", "(I[B[I[I)I", (void *)OS_NATIVE(ScriptLayout),
"ScriptPlace", "(IIIIILorg.eclipse.swt.internal.win32.SCRIPT_ANALYSIS;II[I)I", (void *)OS_NATIVE(ScriptPlace),
"ScriptShape", "(II[CIILorg.eclipse.swt.internal.win32.SCRIPT_ANALYSIS;III[I)I", (void *)OS_NATIVE(ScriptShape),
"ScriptTextOut", "(IIIIILorg.eclipse.swt.internal.win32.RECT;Lorg.eclipse.swt.internal.win32.SCRIPT_ANALYSIS;IIIII[II)I", (void *)OS_NATIVE(ScriptTextOut),
"ScriptXtoCP", "(IIIIIILorg.eclipse.swt.internal.win32.SCRIPT_ANALYSIS;[I[I)I", (void *)OS_NATIVE(ScriptXtoCP),
"ScrollWindowEx", "(IIILorg.eclipse.swt.internal.win32.RECT;Lorg.eclipse.swt.internal.win32.RECT;ILorg.eclipse.swt.internal.win32.RECT;I)I", (void *)OS_NATIVE(ScrollWindowEx),
"SelectClipRgn", "(II)I", (void *)OS_NATIVE(SelectClipRgn),
"SelectObject", "(II)I", (void *)OS_NATIVE(SelectObject),
"SelectPalette", "(IIZ)I", (void *)OS_NATIVE(SelectPalette),
"SendInput", "(III)I", (void *)OS_NATIVE(SendInput),
"SendMessageA", "(IIII)I", (void *)OS_NATIVE(SendMessageA__IIII),
"SendMessageA", "(IIILorg.eclipse.swt.internal.win32.BUTTON_IMAGELIST;)I", (void *)OS_NATIVE(SendMessageA__IIILorg_eclipse_swt_internal_win32_BUTTON_1IMAGELIST_2),
"SendMessageA", "(IIILorg.eclipse.swt.internal.win32.HDITEM;)I", (void *)OS_NATIVE(SendMessageA__IIILorg_eclipse_swt_internal_win32_HDITEM_2),
"SendMessageA", "(IIILorg.eclipse.swt.internal.win32.HDLAYOUT;)I", (void *)OS_NATIVE(SendMessageA__IIILorg_eclipse_swt_internal_win32_HDLAYOUT_2),
"SendMessageA", "(IIILorg.eclipse.swt.internal.win32.LITEM;)I", (void *)OS_NATIVE(SendMessageA__IIILorg_eclipse_swt_internal_win32_LITEM_2),
"SendMessageA", "(IIILorg.eclipse.swt.internal.win32.LVCOLUMN;)I", (void *)OS_NATIVE(SendMessageA__IIILorg_eclipse_swt_internal_win32_LVCOLUMN_2),
"SendMessageA", "(IIILorg.eclipse.swt.internal.win32.LVHITTESTINFO;)I", (void *)OS_NATIVE(SendMessageA__IIILorg_eclipse_swt_internal_win32_LVHITTESTINFO_2),
"SendMessageA", "(IIILorg.eclipse.swt.internal.win32.LVITEM;)I", (void *)OS_NATIVE(SendMessageA__IIILorg_eclipse_swt_internal_win32_LVITEM_2),
"SendMessageA", "(IIILorg.eclipse.swt.internal.win32.MARGINS;)I", (void *)OS_NATIVE(SendMessageA__IIILorg_eclipse_swt_internal_win32_MARGINS_2),
"SendMessageA", "(IIILorg.eclipse.swt.internal.win32.REBARBANDINFO;)I", (void *)OS_NATIVE(SendMessageA__IIILorg_eclipse_swt_internal_win32_REBARBANDINFO_2),
"SendMessageA", "(IIILorg.eclipse.swt.internal.win32.RECT;)I", (void *)OS_NATIVE(SendMessageA__IIILorg_eclipse_swt_internal_win32_RECT_2),
"SendMessageA", "(IIILorg.eclipse.swt.internal.win32.SIZE;)I", (void *)OS_NATIVE(SendMessageA__IIILorg_eclipse_swt_internal_win32_SIZE_2),
"SendMessageA", "(IIILorg.eclipse.swt.internal.win32.TBBUTTONINFO;)I", (void *)OS_NATIVE(SendMessageA__IIILorg_eclipse_swt_internal_win32_TBBUTTONINFO_2),
"SendMessageA", "(IIILorg.eclipse.swt.internal.win32.TBBUTTON;)I", (void *)OS_NATIVE(SendMessageA__IIILorg_eclipse_swt_internal_win32_TBBUTTON_2),
"SendMessageA", "(IIILorg.eclipse.swt.internal.win32.TCITEM;)I", (void *)OS_NATIVE(SendMessageA__IIILorg_eclipse_swt_internal_win32_TCITEM_2),
"SendMessageA", "(IIILorg.eclipse.swt.internal.win32.TOOLINFO;)I", (void *)OS_NATIVE(SendMessageA__IIILorg_eclipse_swt_internal_win32_TOOLINFO_2),
"SendMessageA", "(IIILorg.eclipse.swt.internal.win32.TVHITTESTINFO;)I", (void *)OS_NATIVE(SendMessageA__IIILorg_eclipse_swt_internal_win32_TVHITTESTINFO_2),
"SendMessageA", "(IIILorg.eclipse.swt.internal.win32.TVINSERTSTRUCT;)I", (void *)OS_NATIVE(SendMessageA__IIILorg_eclipse_swt_internal_win32_TVINSERTSTRUCT_2),
"SendMessageA", "(IIILorg.eclipse.swt.internal.win32.TVITEM;)I", (void *)OS_NATIVE(SendMessageA__IIILorg_eclipse_swt_internal_win32_TVITEM_2),
"SendMessageA", "(IIILorg.eclipse.swt.internal.win32.UDACCEL;)I", (void *)OS_NATIVE(SendMessageA__IIILorg_eclipse_swt_internal_win32_UDACCEL_2),
"SendMessageA", "(III[B)I", (void *)OS_NATIVE(SendMessageA__III_3B),
"SendMessageA", "(III[I)I", (void *)OS_NATIVE(SendMessageA__III_3I),
"SendMessageA", "(III[S)I", (void *)OS_NATIVE(SendMessageA__III_3S),
"SendMessageA", "(II[II)I", (void *)OS_NATIVE(SendMessageA__II_3II),
"SendMessageA", "(II[I[I)I", (void *)OS_NATIVE(SendMessageA__II_3I_3I),
"SendMessageW", "(IIII)I", (void *)OS_NATIVE(SendMessageW__IIII),
"SendMessageW", "(IIILorg.eclipse.swt.internal.win32.BUTTON_IMAGELIST;)I", (void *)OS_NATIVE(SendMessageW__IIILorg_eclipse_swt_internal_win32_BUTTON_1IMAGELIST_2),
"SendMessageW", "(IIILorg.eclipse.swt.internal.win32.HDITEM;)I", (void *)OS_NATIVE(SendMessageW__IIILorg_eclipse_swt_internal_win32_HDITEM_2),
"SendMessageW", "(IIILorg.eclipse.swt.internal.win32.HDLAYOUT;)I", (void *)OS_NATIVE(SendMessageW__IIILorg_eclipse_swt_internal_win32_HDLAYOUT_2),
"SendMessageW", "(IIILorg.eclipse.swt.internal.win32.LITEM;)I", (void *)OS_NATIVE(SendMessageW__IIILorg_eclipse_swt_internal_win32_LITEM_2),
"SendMessageW", "(IIILorg.eclipse.swt.internal.win32.LVCOLUMN;)I", (void *)OS_NATIVE(SendMessageW__IIILorg_eclipse_swt_internal_win32_LVCOLUMN_2),
"SendMessageW", "(IIILorg.eclipse.swt.internal.win32.LVHITTESTINFO;)I", (void *)OS_NATIVE(SendMessageW__IIILorg_eclipse_swt_internal_win32_LVHITTESTINFO_2),
"SendMessageW", "(IIILorg.eclipse.swt.internal.win32.LVITEM;)I", (void *)OS_NATIVE(SendMessageW__IIILorg_eclipse_swt_internal_win32_LVITEM_2),
"SendMessageW", "(IIILorg.eclipse.swt.internal.win32.MARGINS;)I", (void *)OS_NATIVE(SendMessageW__IIILorg_eclipse_swt_internal_win32_MARGINS_2),
"SendMessageW", "(IIILorg.eclipse.swt.internal.win32.REBARBANDINFO;)I", (void *)OS_NATIVE(SendMessageW__IIILorg_eclipse_swt_internal_win32_REBARBANDINFO_2),
"SendMessageW", "(IIILorg.eclipse.swt.internal.win32.RECT;)I", (void *)OS_NATIVE(SendMessageW__IIILorg_eclipse_swt_internal_win32_RECT_2),
"SendMessageW", "(IIILorg.eclipse.swt.internal.win32.SIZE;)I", (void *)OS_NATIVE(SendMessageW__IIILorg_eclipse_swt_internal_win32_SIZE_2),
"SendMessageW", "(IIILorg.eclipse.swt.internal.win32.TBBUTTONINFO;)I", (void *)OS_NATIVE(SendMessageW__IIILorg_eclipse_swt_internal_win32_TBBUTTONINFO_2),
"SendMessageW", "(IIILorg.eclipse.swt.internal.win32.TBBUTTON;)I", (void *)OS_NATIVE(SendMessageW__IIILorg_eclipse_swt_internal_win32_TBBUTTON_2),
"SendMessageW", "(IIILorg.eclipse.swt.internal.win32.TCITEM;)I", (void *)OS_NATIVE(SendMessageW__IIILorg_eclipse_swt_internal_win32_TCITEM_2),
"SendMessageW", "(IIILorg.eclipse.swt.internal.win32.TOOLINFO;)I", (void *)OS_NATIVE(SendMessageW__IIILorg_eclipse_swt_internal_win32_TOOLINFO_2),
"SendMessageW", "(IIILorg.eclipse.swt.internal.win32.TVHITTESTINFO;)I", (void *)OS_NATIVE(SendMessageW__IIILorg_eclipse_swt_internal_win32_TVHITTESTINFO_2),
"SendMessageW", "(IIILorg.eclipse.swt.internal.win32.TVINSERTSTRUCT;)I", (void *)OS_NATIVE(SendMessageW__IIILorg_eclipse_swt_internal_win32_TVINSERTSTRUCT_2),
"SendMessageW", "(IIILorg.eclipse.swt.internal.win32.TVITEM;)I", (void *)OS_NATIVE(SendMessageW__IIILorg_eclipse_swt_internal_win32_TVITEM_2),
"SendMessageW", "(IIILorg.eclipse.swt.internal.win32.UDACCEL;)I", (void *)OS_NATIVE(SendMessageW__IIILorg_eclipse_swt_internal_win32_UDACCEL_2),
"SendMessageW", "(III[C)I", (void *)OS_NATIVE(SendMessageW__III_3C),
"SendMessageW", "(III[I)I", (void *)OS_NATIVE(SendMessageW__III_3I),
"SendMessageW", "(III[S)I", (void *)OS_NATIVE(SendMessageW__III_3S),
"SendMessageW", "(II[II)I", (void *)OS_NATIVE(SendMessageW__II_3II),
"SendMessageW", "(II[I[I)I", (void *)OS_NATIVE(SendMessageW__II_3I_3I),
"SetActiveWindow", "(I)I", (void *)OS_NATIVE(SetActiveWindow),
"SetBkColor", "(II)I", (void *)OS_NATIVE(SetBkColor),
"SetBkMode", "(II)I", (void *)OS_NATIVE(SetBkMode),
"SetCapture", "(I)I", (void *)OS_NATIVE(SetCapture),
"SetCaretPos", "(II)Z", (void *)OS_NATIVE(SetCaretPos),
"SetClipboardData", "(II)I", (void *)OS_NATIVE(SetClipboardData),
"SetCursor", "(I)I", (void *)OS_NATIVE(SetCursor),
"SetCursorPos", "(II)Z", (void *)OS_NATIVE(SetCursorPos),
"SetDIBColorTable", "(III[B)I", (void *)OS_NATIVE(SetDIBColorTable),
"SetErrorMode", "(I)I", (void *)OS_NATIVE(SetErrorMode),
"SetFocus", "(I)I", (void *)OS_NATIVE(SetFocus),
"SetForegroundWindow", "(I)Z", (void *)OS_NATIVE(SetForegroundWindow),
"SetGraphicsMode", "(II)I", (void *)OS_NATIVE(SetGraphicsMode),
"SetLayout", "(II)I", (void *)OS_NATIVE(SetLayout),
"SetMenu", "(II)Z", (void *)OS_NATIVE(SetMenu),
"SetMenuDefaultItem", "(III)Z", (void *)OS_NATIVE(SetMenuDefaultItem),
"SetMenuInfo", "(ILorg.eclipse.swt.internal.win32.MENUINFO;)Z", (void *)OS_NATIVE(SetMenuInfo),
"SetMenuItemInfoA", "(IIZLorg.eclipse.swt.internal.win32.MENUITEMINFO;)Z", (void *)OS_NATIVE(SetMenuItemInfoA),
"SetMenuItemInfoW", "(IIZLorg.eclipse.swt.internal.win32.MENUITEMINFO;)Z", (void *)OS_NATIVE(SetMenuItemInfoW),
"SetMetaRgn", "(I)I", (void *)OS_NATIVE(SetMetaRgn),
"SetPaletteEntries", "(III[B)I", (void *)OS_NATIVE(SetPaletteEntries),
"SetParent", "(II)I", (void *)OS_NATIVE(SetParent),
"SetPixel", "(IIII)I", (void *)OS_NATIVE(SetPixel),
"SetPolyFillMode", "(II)I", (void *)OS_NATIVE(SetPolyFillMode),
"SetPropA", "(III)Z", (void *)OS_NATIVE(SetPropA),
"SetPropW", "(III)Z", (void *)OS_NATIVE(SetPropW),
"SetROP2", "(II)I", (void *)OS_NATIVE(SetROP2),
"SetRect", "(Lorg.eclipse.swt.internal.win32.RECT;IIII)Z", (void *)OS_NATIVE(SetRect),
"SetRectRgn", "(IIIII)Z", (void *)OS_NATIVE(SetRectRgn),
"SetScrollInfo", "(IILorg.eclipse.swt.internal.win32.SCROLLINFO;Z)Z", (void *)OS_NATIVE(SetScrollInfo),
"SetStretchBltMode", "(II)I", (void *)OS_NATIVE(SetStretchBltMode),
"SetTextAlign", "(II)I", (void *)OS_NATIVE(SetTextAlign),
"SetTextColor", "(II)I", (void *)OS_NATIVE(SetTextColor),
"SetTimer", "(IIII)I", (void *)OS_NATIVE(SetTimer),
"SetWindowLongA", "(III)I", (void *)OS_NATIVE(SetWindowLongA),
"SetWindowLongW", "(III)I", (void *)OS_NATIVE(SetWindowLongW),
"SetWindowOrgEx", "(IIILorg.eclipse.swt.internal.win32.POINT;)Z", (void *)OS_NATIVE(SetWindowOrgEx),
"SetWindowPlacement", "(ILorg.eclipse.swt.internal.win32.WINDOWPLACEMENT;)Z", (void *)OS_NATIVE(SetWindowPlacement),
"SetWindowPos", "(IIIIIII)Z", (void *)OS_NATIVE(SetWindowPos),
"SetWindowRgn", "(IIZ)I", (void *)OS_NATIVE(SetWindowRgn),
"SetWindowTextA", "(I[B)Z", (void *)OS_NATIVE(SetWindowTextA),
"SetWindowTextW", "(I[C)Z", (void *)OS_NATIVE(SetWindowTextW),
"SetWindowsHookExA", "(IIII)I", (void *)OS_NATIVE(SetWindowsHookExA),
"SetWindowsHookExW", "(IIII)I", (void *)OS_NATIVE(SetWindowsHookExW),
"SetWorldTransform", "(I[F)Z", (void *)OS_NATIVE(SetWorldTransform),
"ShellExecuteExA", "(Lorg.eclipse.swt.internal.win32.SHELLEXECUTEINFO;)Z", (void *)OS_NATIVE(ShellExecuteExA),
"ShellExecuteExW", "(Lorg.eclipse.swt.internal.win32.SHELLEXECUTEINFO;)Z", (void *)OS_NATIVE(ShellExecuteExW),
"Shell_NotifyIconA", "(ILorg.eclipse.swt.internal.win32.NOTIFYICONDATAA;)Z", (void *)OS_NATIVE(Shell_1NotifyIconA),
"Shell_NotifyIconW", "(ILorg.eclipse.swt.internal.win32.NOTIFYICONDATAW;)Z", (void *)OS_NATIVE(Shell_1NotifyIconW),
"ShowCaret", "(I)Z", (void *)OS_NATIVE(ShowCaret),
"ShowOwnedPopups", "(IZ)Z", (void *)OS_NATIVE(ShowOwnedPopups),
"ShowScrollBar", "(IIZ)Z", (void *)OS_NATIVE(ShowScrollBar),
"ShowWindow", "(II)Z", (void *)OS_NATIVE(ShowWindow),
#ifdef WIN32_PLATFORM_PSPC
"SipGetInfo", "(Lorg.eclipse.swt.internal.win32.SIPINFO;)Z", (void *)OS_NATIVE(SipGetInfo),
#endif
"StartDocA", "(ILorg.eclipse.swt.internal.win32.DOCINFO;)I", (void *)OS_NATIVE(StartDocA),
"StartDocW", "(ILorg.eclipse.swt.internal.win32.DOCINFO;)I", (void *)OS_NATIVE(StartDocW),
"StartPage", "(I)I", (void *)OS_NATIVE(StartPage),
"StretchBlt", "(IIIIIIIIIII)Z", (void *)OS_NATIVE(StretchBlt),
"StrokePath", "(I)Z", (void *)OS_NATIVE(StrokePath),
"SystemParametersInfoA", "(IILorg.eclipse.swt.internal.win32.HIGHCONTRAST;I)Z", (void *)OS_NATIVE(SystemParametersInfoA__IILorg_eclipse_swt_internal_win32_HIGHCONTRAST_2I),
"SystemParametersInfoA", "(IILorg.eclipse.swt.internal.win32.NONCLIENTMETRICSA;I)Z", (void *)OS_NATIVE(SystemParametersInfoA__IILorg_eclipse_swt_internal_win32_NONCLIENTMETRICSA_2I),
"SystemParametersInfoA", "(IILorg.eclipse.swt.internal.win32.RECT;I)Z", (void *)OS_NATIVE(SystemParametersInfoA__IILorg_eclipse_swt_internal_win32_RECT_2I),
"SystemParametersInfoA", "(II[II)Z", (void *)OS_NATIVE(SystemParametersInfoA__II_3II),
"SystemParametersInfoW", "(IILorg.eclipse.swt.internal.win32.HIGHCONTRAST;I)Z", (void *)OS_NATIVE(SystemParametersInfoW__IILorg_eclipse_swt_internal_win32_HIGHCONTRAST_2I),
"SystemParametersInfoW", "(IILorg.eclipse.swt.internal.win32.NONCLIENTMETRICSW;I)Z", (void *)OS_NATIVE(SystemParametersInfoW__IILorg_eclipse_swt_internal_win32_NONCLIENTMETRICSW_2I),
"SystemParametersInfoW", "(IILorg.eclipse.swt.internal.win32.RECT;I)Z", (void *)OS_NATIVE(SystemParametersInfoW__IILorg_eclipse_swt_internal_win32_RECT_2I),
"SystemParametersInfoW", "(II[II)Z", (void *)OS_NATIVE(SystemParametersInfoW__II_3II),
"ToAscii", "(II[B[SI)I", (void *)OS_NATIVE(ToAscii),
"ToUnicode", "(II[B[CII)I", (void *)OS_NATIVE(ToUnicode),
"TrackMouseEvent", "(Lorg.eclipse.swt.internal.win32.TRACKMOUSEEVENT;)Z", (void *)OS_NATIVE(TrackMouseEvent),
"TrackPopupMenu", "(IIIIIILorg.eclipse.swt.internal.win32.RECT;)Z", (void *)OS_NATIVE(TrackPopupMenu),
"TranslateAcceleratorA", "(IILorg.eclipse.swt.internal.win32.MSG;)I", (void *)OS_NATIVE(TranslateAcceleratorA),
"TranslateAcceleratorW", "(IILorg.eclipse.swt.internal.win32.MSG;)I", (void *)OS_NATIVE(TranslateAcceleratorW),
"TranslateCharsetInfo", "(I[II)Z", (void *)OS_NATIVE(TranslateCharsetInfo),
"TranslateMDISysAccel", "(ILorg.eclipse.swt.internal.win32.MSG;)Z", (void *)OS_NATIVE(TranslateMDISysAccel),
"TranslateMessage", "(Lorg.eclipse.swt.internal.win32.MSG;)Z", (void *)OS_NATIVE(TranslateMessage),
"TransparentBlt", "(IIIIIIIIIII)Z", (void *)OS_NATIVE(TransparentBlt),
#ifdef _WIN32_WCE
"TransparentImage", "(IIIIIIIIIII)Z", (void *)OS_NATIVE(TransparentImage),
#endif
"UnhookWindowsHookEx", "(I)Z", (void *)OS_NATIVE(UnhookWindowsHookEx),
"UnregisterClassA", "([BI)Z", (void *)OS_NATIVE(UnregisterClassA),
"UnregisterClassW", "([CI)Z", (void *)OS_NATIVE(UnregisterClassW),
"UpdateWindow", "(I)Z", (void *)OS_NATIVE(UpdateWindow),
"ValidateRect", "(ILorg.eclipse.swt.internal.win32.RECT;)Z", (void *)OS_NATIVE(ValidateRect),
"VkKeyScanA", "(S)S", (void *)OS_NATIVE(VkKeyScanA),
"VkKeyScanW", "(S)S", (void *)OS_NATIVE(VkKeyScanW),
"VtblCall", "(II)I", (void *)OS_NATIVE(VtblCall__II),
"VtblCall", "(III)I", (void *)OS_NATIVE(VtblCall__III),
"VtblCall", "(IIIII[I)I", (void *)OS_NATIVE(VtblCall__IIIII_3I),
"VtblCall", "(II[CII[I[I)I", (void *)OS_NATIVE(VtblCall__II_3CII_3I_3I),
"WaitMessage", "()Z", (void *)OS_NATIVE(WaitMessage),
"WideCharToMultiByte", "(II[CIII[B[Z)I", (void *)OS_NATIVE(WideCharToMultiByte__II_3CIII_3B_3Z),
"WideCharToMultiByte", "(II[CI[BI[B[Z)I", (void *)OS_NATIVE(WideCharToMultiByte__II_3CI_3BI_3B_3Z),
"WindowFromDC", "(I)I", (void *)OS_NATIVE(WindowFromDC),
"WindowFromPoint", "(Lorg.eclipse.swt.internal.win32.POINT;)I", (void *)OS_NATIVE(WindowFromPoint),
"strlen", "(I)I", (void *)OS_NATIVE(strlen),
"wcslen", "(I)I", (void *)OS_NATIVE(wcslen)
};

JNINativeMethod methodsCallback[] = {
"PTR_sizeof", "()I", (void *)Java_org_eclipse_swt_internal_Callback_PTR_1sizeof,
"bind", "(Lorg.eclipse.swt.internal.Callback;Ljava.lang.Object;Ljava.lang.String;Ljava.lang.String;IZZI)I", (void *)Java_org_eclipse_swt_internal_Callback_bind,
"getEnabled", "()Z", (void *)Java_org_eclipse_swt_internal_Callback_getEnabled,
"getEntryCount", "()I", (void *)Java_org_eclipse_swt_internal_Callback_getEntryCount,
//"getPlatform", "()Ljava.lang.String;", (void *)Java_org_eclipse_swt_internal_Callback_getPlatform,  // Where is this implemented?
"reset", "()V", (void *)Java_org_eclipse_swt_internal_Callback_reset,
"setEnabled", "(Z)V", (void *)Java_org_eclipse_swt_internal_Callback_setEnabled,
"unbind", "(Lorg.eclipse.swt.internal.Callback;)V", (void *)Java_org_eclipse_swt_internal_Callback_unbind
};

JNINativeMethod methodsCOM[] = {
"CLSIDFromProgID", "([CLorg.eclipse.swt.internal.ole.win32.GUID;)I", (void *)COM_NATIVE(CLSIDFromProgID),
"CLSIDFromString", "([CLorg.eclipse.swt.internal.ole.win32.GUID;)I", (void *)COM_NATIVE(CLSIDFromString),
"CoCreateInstance", "(Lorg.eclipse.swt.internal.ole.win32.GUID;IILorg.eclipse.swt.internal.ole.win32.GUID;[I)I", (void *)COM_NATIVE(CoCreateInstance),
"CoFreeUnusedLibraries", "()V", (void *)COM_NATIVE(CoFreeUnusedLibraries),
"CoGetClassObject", "(Lorg.eclipse.swt.internal.ole.win32.GUID;IILorg.eclipse.swt.internal.ole.win32.GUID;[I)I", (void *)COM_NATIVE(CoGetClassObject),
"CoLockObjectExternal", "(IZZ)I", (void *)COM_NATIVE(CoLockObjectExternal),
"CoTaskMemAlloc", "(I)I", (void *)COM_NATIVE(CoTaskMemAlloc),
"CoTaskMemFree", "(I)V", (void *)COM_NATIVE(CoTaskMemFree),
"CreateStdAccessibleObject", "(IILorg.eclipse.swt.internal.ole.win32.GUID;[I)I", (void *)COM_NATIVE(CreateStdAccessibleObject),
"DoDragDrop", "(III[I)I", (void *)COM_NATIVE(DoDragDrop),
"GetClassFile", "([CLorg.eclipse.swt.internal.ole.win32.GUID;)I", (void *)COM_NATIVE(GetClassFile),
"IIDFromString", "([CLorg.eclipse.swt.internal.ole.win32.GUID;)I", (void *)COM_NATIVE(IIDFromString),
"IsEqualGUID", "(Lorg.eclipse.swt.internal.ole.win32.GUID;Lorg.eclipse.swt.internal.ole.win32.GUID;)Z", (void *)COM_NATIVE(IsEqualGUID),
"LresultFromObject", "(Lorg.eclipse.swt.internal.ole.win32.GUID;II)I", (void *)COM_NATIVE(LresultFromObject),
"MoveMemory", "(ILorg.eclipse.swt.internal.ole.win32.FORMATETC;I)V", (void *)COM_NATIVE(MoveMemory__ILorg_eclipse_swt_internal_ole_win32_FORMATETC_2I),
"MoveMemory", "(ILorg.eclipse.swt.internal.ole.win32.GUID;I)V", (void *)COM_NATIVE(MoveMemory__ILorg_eclipse_swt_internal_ole_win32_GUID_2I),
"MoveMemory", "(ILorg.eclipse.swt.internal.ole.win32.OLEINPLACEFRAMEINFO;I)V", (void *)COM_NATIVE(MoveMemory__ILorg_eclipse_swt_internal_ole_win32_OLEINPLACEFRAMEINFO_2I),
"MoveMemory", "(ILorg.eclipse.swt.internal.ole.win32.STATSTG;I)V", (void *)COM_NATIVE(MoveMemory__ILorg_eclipse_swt_internal_ole_win32_STATSTG_2I),
"MoveMemory", "(ILorg.eclipse.swt.internal.ole.win32.STGMEDIUM;I)V", (void *)COM_NATIVE(MoveMemory__ILorg_eclipse_swt_internal_ole_win32_STGMEDIUM_2I),
"MoveMemory", "(Lorg.eclipse.swt.internal.ole.win32.STGMEDIUM;II)V", (void *)COM_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_ole_win32_STGMEDIUM_2II),
"MoveMemory", "(Lorg.eclipse.swt.internal.ole.win32.DISPPARAMS;II)V", (void *)COM_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_ole_win32_DISPPARAMS_2II),
"MoveMemory", "(Lorg.eclipse.swt.internal.ole.win32.FORMATETC;II)V", (void *)COM_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_ole_win32_FORMATETC_2II),
"MoveMemory", "(Lorg.eclipse.swt.internal.ole.win32.GUID;II)V", (void *)COM_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_ole_win32_GUID_2II),
"MoveMemory", "(Lorg.eclipse.swt.internal.ole.win32.STATSTG;II)V", (void *)COM_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_ole_win32_STATSTG_2II),
"MoveMemory", "(Lorg.eclipse.swt.internal.ole.win32.TYPEATTR;II)V", (void *)COM_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_ole_win32_TYPEATTR_2II),
"MoveMemory", "(Lorg.eclipse.swt.internal.win32.RECT;II)V", (void *)COM_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_win32_RECT_2II),
"MoveMemory", "(Lorg.eclipse.swt.internal.ole.win32.FUNCDESC;II)V", (void *)COM_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_ole_win32_FUNCDESC_2II),
"MoveMemory", "(Lorg.eclipse.swt.internal.ole.win32.VARDESC;II)V", (void *)COM_NATIVE(MoveMemory__Lorg_eclipse_swt_internal_ole_win32_VARDESC_2II),
"OleCreate", "(Lorg.eclipse.swt.internal.ole.win32.GUID;Lorg.eclipse.swt.internal.ole.win32.GUID;ILorg.eclipse.swt.internal.ole.win32.FORMATETC;II[I)I", (void *)COM_NATIVE(OleCreate),
"OleCreateFromFile", "(Lorg.eclipse.swt.internal.ole.win32.GUID;[CLorg.eclipse.swt.internal.ole.win32.GUID;ILorg.eclipse.swt.internal.ole.win32.FORMATETC;II[I)I", (void *)COM_NATIVE(OleCreateFromFile),
"OleCreatePropertyFrame", "(III[CI[IIIIII)I", (void *)COM_NATIVE(OleCreatePropertyFrame),
"OleDraw", "(IIII)I", (void *)COM_NATIVE(OleDraw),
"OleFlushClipboard", "()I", (void *)COM_NATIVE(OleFlushClipboard),
"OleGetClipboard", "([I)I", (void *)COM_NATIVE(OleGetClipboard),
"OleIsCurrentClipboard", "(I)I", (void *)COM_NATIVE(OleIsCurrentClipboard),
"OleIsRunning", "(I)Z", (void *)COM_NATIVE(OleIsRunning),
"OleLoad", "(ILorg.eclipse.swt.internal.ole.win32.GUID;I[I)I", (void *)COM_NATIVE(OleLoad),
"OleRun", "(I)I", (void *)COM_NATIVE(OleRun),
"OleSave", "(IIZ)I", (void *)COM_NATIVE(OleSave),
"OleSetClipboard", "(I)I", (void *)COM_NATIVE(OleSetClipboard),
"OleSetContainedObject", "(IZ)I", (void *)COM_NATIVE(OleSetContainedObject),
"OleSetMenuDescriptor", "(IIIII)I", (void *)COM_NATIVE(OleSetMenuDescriptor),
"OleTranslateColor", "(II[I)I", (void *)COM_NATIVE(OleTranslateColor),
"ProgIDFromCLSID", "(Lorg.eclipse.swt.internal.ole.win32.GUID;[I)I", (void *)COM_NATIVE(ProgIDFromCLSID),
"RegisterDragDrop", "(II)I", (void *)COM_NATIVE(RegisterDragDrop),
"ReleaseStgMedium", "(I)V", (void *)COM_NATIVE(ReleaseStgMedium),
"RevokeDragDrop", "(I)I", (void *)COM_NATIVE(RevokeDragDrop),
"StgCreateDocfile", "([CII[I)I", (void *)COM_NATIVE(StgCreateDocfile),
"StgIsStorageFile", "([C)I", (void *)COM_NATIVE(StgIsStorageFile),
"StgOpenStorage", "([CIIII[I)I", (void *)COM_NATIVE(StgOpenStorage),
"StringFromCLSID", "(Lorg.eclipse.swt.internal.ole.win32.GUID;[I)I", (void *)COM_NATIVE(StringFromCLSID),
"SysAllocString", "([C)I", (void *)COM_NATIVE(SysAllocString),
"SysFreeString", "(I)V", (void *)COM_NATIVE(SysFreeString),
"SysStringByteLen", "(I)I", (void *)COM_NATIVE(SysStringByteLen),
"VariantChangeType", "(IISS)I", (void *)COM_NATIVE(VariantChangeType),
"VariantClear", "(I)I", (void *)COM_NATIVE(VariantClear),
"VariantInit", "(I)V", (void *)COM_NATIVE(VariantInit),
"VtblCall", "(II[C)I", (void *)COM_NATIVE(VtblCall__II_3C),
"VtblCall", "(II[C[C)I", (void *)COM_NATIVE(VtblCall__II_3C_3C),
"VtblCall", "(II[CI)I", (void *)COM_NATIVE(VtblCall__II_3CI),
"VtblCall", "(II[CIII[I)I", (void *)COM_NATIVE(VtblCall__II_3CIII_3I),
"VtblCall", "(II[CIIII[I)I", (void *)COM_NATIVE(VtblCall__II_3CIIII_3I),
"VtblCall", "(II[I)I", (void *)COM_NATIVE(VtblCall__II_3I),
"VtblCall", "(III[I)I", (void *)COM_NATIVE(VtblCall__III_3I),
"VtblCall", "(IIII)I", (void *)COM_NATIVE(VtblCall__IIII),
"VtblCall", "(IIII[I)I", (void *)COM_NATIVE(VtblCall__IIII_3I),
"VtblCall", "(IIIII)I", (void *)COM_NATIVE(VtblCall__IIIII),
"VtblCall", "(IIIILorg.eclipse.swt.internal.ole.win32.DVTARGETDEVICE;Lorg.eclipse.swt.internal.win32.SIZE;)I", (void *)COM_NATIVE(VtblCall__IIIILorg_eclipse_swt_internal_ole_win32_DVTARGETDEVICE_2Lorg_eclipse_swt_internal_win32_SIZE_2),
"VtblCall", "(IIIILorg.eclipse.swt.internal.ole.win32.GUID;I[I)I", (void *)COM_NATIVE(VtblCall__IIIILorg_eclipse_swt_internal_ole_win32_GUID_2I_3I),
"VtblCall", "(IIILorg.eclipse.swt.internal.ole.win32.FORMATETC;[I)I", (void *)COM_NATIVE(VtblCall__IIILorg_eclipse_swt_internal_ole_win32_FORMATETC_2_3I),
"VtblCall", "(IIILorg.eclipse.swt.internal.ole.win32.GUID;)I", (void *)COM_NATIVE(VtblCall__IIILorg_eclipse_swt_internal_ole_win32_GUID_2),
"VtblCall", "(IIILorg.eclipse.swt.internal.ole.win32.GUID;II)I", (void *)COM_NATIVE(VtblCall__IIILorg_eclipse_swt_internal_ole_win32_GUID_2II),
"VtblCall", "(IIILorg.eclipse.swt.internal.ole.win32.GUID;IILorg.eclipse.swt.internal.ole.win32.DISPPARAMS;ILorg.eclipse.swt.internal.ole.win32.EXCEPINFO;[I)I", (void *)COM_NATIVE(VtblCall__IIILorg_eclipse_swt_internal_ole_win32_GUID_2IILorg_eclipse_swt_internal_ole_win32_DISPPARAMS_2ILorg_eclipse_swt_internal_ole_win32_EXCEPINFO_2_3I),
"VtblCall", "(IIILorg.eclipse.swt.internal.ole.win32.STATSTG;[I)I", (void *)COM_NATIVE(VtblCall__IIILorg_eclipse_swt_internal_ole_win32_STATSTG_2_3I),
"VtblCall", "(IILorg.eclipse.swt.internal.win32.MSG;)I", (void *)COM_NATIVE(VtblCall__IILorg_eclipse_swt_internal_win32_MSG_2),
"VtblCall", "(IIILorg.eclipse.swt.internal.win32.MSG;IIILorg.eclipse.swt.internal.win32.RECT;)I", (void *)COM_NATIVE(VtblCall__IIILorg_eclipse_swt_internal_win32_MSG_2IIILorg_eclipse_swt_internal_win32_RECT_2),
"VtblCall", "(IIILorg.eclipse.swt.internal.win32.SIZE;)I", (void *)COM_NATIVE(VtblCall__IIILorg_eclipse_swt_internal_win32_SIZE_2),
"VtblCall", "(IIIZ)I", (void *)COM_NATIVE(VtblCall__IIIZ),
"VtblCall", "(IILorg.eclipse.swt.internal.ole.win32.CAUUID;)I", (void *)COM_NATIVE(VtblCall__IILorg_eclipse_swt_internal_ole_win32_CAUUID_2),
"VtblCall", "(IILorg.eclipse.swt.internal.ole.win32.CONTROLINFO;)I", (void *)COM_NATIVE(VtblCall__IILorg_eclipse_swt_internal_ole_win32_CONTROLINFO_2),
"VtblCall", "(IILorg.eclipse.swt.internal.ole.win32.FORMATETC;)I", (void *)COM_NATIVE(VtblCall__IILorg_eclipse_swt_internal_ole_win32_FORMATETC_2),
"VtblCall", "(IILorg.eclipse.swt.internal.ole.win32.FORMATETC;Lorg.eclipse.swt.internal.ole.win32.STGMEDIUM;)I", (void *)COM_NATIVE(VtblCall__IILorg_eclipse_swt_internal_ole_win32_FORMATETC_2Lorg_eclipse_swt_internal_ole_win32_STGMEDIUM_2),
"VtblCall", "(IILorg.eclipse.swt.internal.ole.win32.FORMATETC;Lorg.eclipse.swt.internal.ole.win32.STGMEDIUM;Z)I", (void *)COM_NATIVE(VtblCall__IILorg_eclipse_swt_internal_ole_win32_FORMATETC_2Lorg_eclipse_swt_internal_ole_win32_STGMEDIUM_2Z),
"VtblCall", "(IILorg.eclipse.swt.internal.ole.win32.GUID;)I", (void *)COM_NATIVE(VtblCall__IILorg_eclipse_swt_internal_ole_win32_GUID_2),
"VtblCall", "(IILorg.eclipse.swt.internal.ole.win32.GUID;[I)I", (void *)COM_NATIVE(VtblCall__IILorg_eclipse_swt_internal_ole_win32_GUID_2_3I),
"VtblCall", "(IILorg.eclipse.swt.internal.ole.win32.GUID;III[I)I", (void *)COM_NATIVE(VtblCall__IILorg_eclipse_swt_internal_ole_win32_GUID_2III_3I),
"VtblCall", "(IILorg.eclipse.swt.internal.ole.win32.GUID;IIII)I", (void *)COM_NATIVE(VtblCall__IILorg_eclipse_swt_internal_ole_win32_GUID_2IIII),
"VtblCall", "(IILorg.eclipse.swt.internal.ole.win32.GUID;ILorg.eclipse.swt.internal.ole.win32.OLECMD;Lorg.eclipse.swt.internal.ole.win32.OLECMDTEXT;)I", (void *)COM_NATIVE(VtblCall__IILorg_eclipse_swt_internal_ole_win32_GUID_2ILorg_eclipse_swt_internal_ole_win32_OLECMD_2Lorg_eclipse_swt_internal_ole_win32_OLECMDTEXT_2),
"VtblCall", "(IILorg.eclipse.swt.internal.ole.win32.LICINFO;)I", (void *)COM_NATIVE(VtblCall__IILorg_eclipse_swt_internal_ole_win32_LICINFO_2),
"VtblCall", "(IILorg.eclipse.swt.internal.win32.RECT;IZ)I", (void *)COM_NATIVE(VtblCall__IILorg_eclipse_swt_internal_win32_RECT_2IZ),
"VtblCall", "(IILorg.eclipse.swt.internal.win32.RECT;Lorg.eclipse.swt.internal.win32.RECT;)I", (void *)COM_NATIVE(VtblCall__IILorg_eclipse_swt_internal_win32_RECT_2Lorg_eclipse_swt_internal_win32_RECT_2),
"VtblCall", "(IILorg.eclipse.swt.internal.win32.RECT;)I", (void *)COM_NATIVE(VtblCall__IILorg_eclipse_swt_internal_win32_RECT_2),
"VtblCall", "(III[I[I[I[I)I", (void *)COM_NATIVE(VtblCall__III_3I_3I_3I_3I),
"VtblCall", "(III[II[I)I", (void *)COM_NATIVE(VtblCall__III_3II_3I),
"VtblCall", "(IIIIII)I", (void *)COM_NATIVE(VtblCall__IIIIII),
"VtblCall", "(IIIIIII)I", (void *)COM_NATIVE(VtblCall__IIIIIII),
"VtblCall", "(IIIIIIII)I", (void *)COM_NATIVE(VtblCall__IIIIIIII),
"VtblCall", "(IIIIIIIIII)I", (void *)COM_NATIVE(VtblCall__IIIIIIIIII),
"WriteClassStg", "(ILorg.eclipse.swt.internal.ole.win32.GUID;)I", (void *)COM_NATIVE(WriteClassStg)
};

void register_natives(JNIEnv* env) {
	jclass classOS = env->FindClass("org.eclipse.swt.internal.win32.OS");
	int number_of_methodsOS = 653;  // max
	for (int i = 0; i < number_of_methodsOS; ++i) {
		if (strcmp("wcslen", methodsOS[i].name) == 0) {
			number_of_methodsOS = i + 1;
			break;
		}
	}
	jclass classCallback = env->FindClass("org.eclipse.swt.internal.Callback");
	int number_of_methodsCallback = 8;  // max
	for (int i = 0; i < number_of_methodsCallback; ++i) {
		if (strcmp("unbind", methodsCallback[i].name) == 0) {
			number_of_methodsCallback = i + 1;
			break;
		}
	}
	jclass classCOM = env->FindClass("org.eclipse.swt.internal.ole.win32.COM");
	int number_of_methodsCOM = 99;  // max
	for (int i = 0; i < number_of_methodsCOM; ++i) {
		if (strcmp("WriteClassStg", methodsCOM[i].name) == 0) {
			number_of_methodsCOM = i + 1;
			break;
		}
	}
//////////////////////
/*	int* aa=(int*)classOS;
	__jclass* a = (__jclass *)classOS;
	printf("%p\n", a);fflush(NULL);
	printf("0 - %p: ", (void *)aa[0]);
	fflush(NULL);
	for (int i = 0; i < 30; ++i) {printf("%c", ((char*)aa[0])[i]);}
	fflush(NULL);
	printf("\n2 - %p: ", (void *)aa[2]);
	for (int i = 0; i < 60; ++i) {printf("%c", ((char*)aa[2])[i]);}
	printf("\n4 - %p: ", (void *)aa[4]);
	for (int i = 0; i < 30; ++i) {printf("%c", ((char*)aa[4])[i]);}
	printf("\n6 - %p: ", (void *)aa[6]);
	for (int i = 0; i < 30; ++i) {printf("%c", ((char*)aa[6])[i]);}
	printf("\n7 - %p: ", (void *)aa[7]);
	fflush(NULL);
	for (int i = 0; i < 30; ++i) {printf("%c", ((char*)aa[7])[i]);}
	fflush(NULL);
	printf("\n8 - %p: ", (void *)aa[8]);
	for (int i = 0; i < 30; ++i) {printf("%c", ((char*)aa[8])[i]);}
	printf("\n10 - %p: ", (void *)aa[10]);
	for (int i = 0; i < 30; ++i) {printf("%c", ((char*)aa[10])[i]);}
	printf("\n13 - %p: ", (void *)aa[13]);
	fflush(NULL);
	for (int i = 0; i < 30; ++i) {printf("%c", ((char*)aa[13])[i]);}
//	printf("\n18 - %p: ", (void *)aa[18]);
//	for (int i = 0; i < 30; ++i) {printf("%c", ((char*)aa[18])[i]);}
	printf("\n");
	fflush(NULL);
	char* b=(char*)classOS;
	for (int i = 0; i < 30; ++i) {
		printf("%i - %p ", i, aa[i]);
	}
	int* methodsp=(int*)aa[8];
	for (int i = 0; i < 20; ++i) {
		char* name=(char*)methodsp[i*5];
		for (int j = 4; j < 30; ++j) {printf("%c", name[j]);}
	printf("\n");
		char* signature=(char*)methodsp[i*5+1];
		for (int j = 4; j < 30; ++j) {printf("%c", signature[j]);}
	printf("\n");
	}
	printf("\n");
*////////////////////////////

	jint error = env->RegisterNatives(classOS, methodsOS, number_of_methodsOS);
	jmethodID mid = env->GetStaticMethodID(classOS, "initStatic", "()V"); 
	env->CallStaticVoidMethod(classOS, mid);

	error |= env->RegisterNatives(classCallback, methodsCallback, number_of_methodsCallback);
	mid = env->GetStaticMethodID(classCallback, "initStatic", "()V"); 
	env->CallStaticVoidMethod(classCallback, mid);

	error |= env->RegisterNatives(classCOM, methodsCOM, number_of_methodsCOM);
	mid = env->GetStaticMethodID(classCOM, "initStatic", "()V"); 
	env->CallStaticVoidMethod(classCOM, mid);

	printf(error == 0 ? "Registered natives OK\n" : "Natives registration ERROR\n");
	fflush(NULL);
}


int main (int argc, const char **argv)
{
	JavaVM *jvm;       /* denotes a Java VM */ 
	JNIEnv *env;       /* pointer to native method interface */ 

	JavaVMInitArgs vm_args; /* JDK 1.1 VM initialization arguments */ 

	vm_args.version = JNI_VERSION_1_4; /* New in 1.1.2: VM version */ 
	/* Get the default initialization arguments and set the classpath */
	JNI_GetDefaultJavaVMInitArgs(&vm_args); 

	/* load and initialize a Java VM, return a JNI interface  
	 * pointer in env */ 
	JNI_CreateJavaVM(&jvm, (void **)&env, &vm_args); 

	// Emulate JNI DLL load
	JNI_OnLoad(jvm, NULL);

	/* invoke the Main.test method using the JNI */ 
	jclass cls = env->FindClass("net.sourceforge.javahexeditor.Manager");

	register_natives(env);

	jmethodID mid = env->GetStaticMethodID(cls, "main", "([Ljava/lang/String;)V");
	jobjectArray jargs = NULL;
	if (argc > 1)
	{
		jargs = (jobjectArray)env->NewObjectArray(1, env->FindClass("java/lang/String"), env->NewStringUTF(argv[1]));
	}
	env->CallStaticVoidMethod(cls, mid, jargs);

	/* We are done. */ 
	jvm->DestroyJavaVM();
}
