cd swt-3.5.1-win32-win32-x86/src
gcj -c -g0 -O2 -v -fjni -fsource=1.4 -o ../../../bin/libswt4javahexeditor.a \
org/eclipse/swt/SWT.java \
org/eclipse/swt/SWTError.java \
org/eclipse/swt/SWTException.java \
org/eclipse/swt/accessibility/Accessible.java \
org/eclipse/swt/accessibility/AccessibleAdapter.java \
org/eclipse/swt/accessibility/AccessibleControlAdapter.java \
org/eclipse/swt/accessibility/AccessibleControlEvent.java \
org/eclipse/swt/accessibility/AccessibleControlListener.java \
org/eclipse/swt/accessibility/AccessibleEvent.java \
org/eclipse/swt/accessibility/AccessibleListener.java \
org/eclipse/swt/accessibility/AccessibleTextAdapter.java \
org/eclipse/swt/accessibility/AccessibleTextListener.java \
org/eclipse/swt/custom/BidiSegmentEvent.java \
org/eclipse/swt/custom/BidiSegmentListener.java \
org/eclipse/swt/custom/Bullet.java \
org/eclipse/swt/custom/CaretEvent.java \
org/eclipse/swt/custom/CaretListener.java \
org/eclipse/swt/custom/DefaultContent.java \
org/eclipse/swt/custom/ExtendedModifyEvent.java \
org/eclipse/swt/custom/ExtendedModifyListener.java \
org/eclipse/swt/custom/LineBackgroundEvent.java \
org/eclipse/swt/custom/LineBackgroundListener.java \
org/eclipse/swt/custom/LineStyleEvent.java \
org/eclipse/swt/custom/LineStyleListener.java \
org/eclipse/swt/custom/MovementEvent.java \
org/eclipse/swt/custom/MovementListener.java \
org/eclipse/swt/custom/PaintObjectEvent.java \
org/eclipse/swt/custom/PaintObjectListener.java \
org/eclipse/swt/custom/StyleRange.java \
org/eclipse/swt/custom/StyledText.java \
org/eclipse/swt/custom/StyledTextContent.java \
org/eclipse/swt/custom/StyledTextDropTargetEffect.java \
org/eclipse/swt/custom/StyledTextEvent.java \
org/eclipse/swt/custom/StyledTextListener.java \
org/eclipse/swt/custom/StyledTextPrintOptions.java \
org/eclipse/swt/custom/StyledTextRenderer.java \
org/eclipse/swt/custom/TextChangeListener.java \
org/eclipse/swt/custom/TextChangedEvent.java \
org/eclipse/swt/custom/TextChangingEvent.java \
org/eclipse/swt/custom/VerifyKeyListener.java \
org/eclipse/swt/dnd/ByteArrayTransfer.java \
org/eclipse/swt/dnd/Clipboard.java \
org/eclipse/swt/dnd/DND.java \
org/eclipse/swt/dnd/DNDEvent.java \
org/eclipse/swt/dnd/DNDListener.java \
org/eclipse/swt/dnd/DragSource.java \
org/eclipse/swt/dnd/DragSourceAdapter.java \
org/eclipse/swt/dnd/DragSourceEffect.java \
org/eclipse/swt/dnd/DragSourceEvent.java \
org/eclipse/swt/dnd/DragSourceListener.java \
org/eclipse/swt/dnd/DropTarget.java \
org/eclipse/swt/dnd/DropTargetAdapter.java \
org/eclipse/swt/dnd/DropTargetEffect.java \
org/eclipse/swt/dnd/DropTargetEvent.java \
org/eclipse/swt/dnd/DropTargetListener.java \
org/eclipse/swt/dnd/FileTransfer.java \
org/eclipse/swt/dnd/OleEnumFORMATETC.java \
org/eclipse/swt/dnd/RTFTransfer.java \
org/eclipse/swt/dnd/TableDragSourceEffect.java \
org/eclipse/swt/dnd/TableDropTargetEffect.java \
org/eclipse/swt/dnd/TextTransfer.java \
org/eclipse/swt/dnd/Transfer.java \
org/eclipse/swt/dnd/TransferData.java \
org/eclipse/swt/dnd/TreeDragSourceEffect.java \
org/eclipse/swt/dnd/TreeDropTargetEffect.java \
org/eclipse/swt/events/ArmEvent.java \
org/eclipse/swt/events/ArmListener.java \
org/eclipse/swt/events/ControlAdapter.java \
org/eclipse/swt/events/ControlEvent.java \
org/eclipse/swt/events/ControlListener.java \
org/eclipse/swt/events/DisposeEvent.java \
org/eclipse/swt/events/DisposeListener.java \
org/eclipse/swt/events/DragDetectEvent.java \
org/eclipse/swt/events/DragDetectListener.java \
org/eclipse/swt/events/ExpandEvent.java \
org/eclipse/swt/events/ExpandListener.java \
org/eclipse/swt/events/FocusAdapter.java \
org/eclipse/swt/events/FocusEvent.java \
org/eclipse/swt/events/FocusListener.java \
org/eclipse/swt/events/HelpEvent.java \
org/eclipse/swt/events/HelpListener.java \
org/eclipse/swt/events/KeyAdapter.java \
org/eclipse/swt/events/KeyEvent.java \
org/eclipse/swt/events/KeyListener.java \
org/eclipse/swt/events/MenuAdapter.java \
org/eclipse/swt/events/MenuDetectEvent.java \
org/eclipse/swt/events/MenuDetectListener.java \
org/eclipse/swt/events/MenuEvent.java \
org/eclipse/swt/events/MenuListener.java \
org/eclipse/swt/events/ModifyEvent.java \
org/eclipse/swt/events/ModifyListener.java \
org/eclipse/swt/events/MouseAdapter.java \
org/eclipse/swt/events/MouseEvent.java \
org/eclipse/swt/events/MouseListener.java \
org/eclipse/swt/events/MouseMoveListener.java \
org/eclipse/swt/events/MouseTrackListener.java \
org/eclipse/swt/events/MouseWheelListener.java \
org/eclipse/swt/events/PaintEvent.java \
org/eclipse/swt/events/PaintListener.java \
org/eclipse/swt/events/SelectionAdapter.java \
org/eclipse/swt/events/SelectionEvent.java \
org/eclipse/swt/events/SelectionListener.java \
org/eclipse/swt/events/ShellAdapter.java \
org/eclipse/swt/events/ShellEvent.java \
org/eclipse/swt/events/ShellListener.java \
org/eclipse/swt/events/TraverseEvent.java \
org/eclipse/swt/events/TraverseListener.java \
org/eclipse/swt/events/TreeEvent.java \
org/eclipse/swt/events/TreeListener.java \
org/eclipse/swt/events/TypedEvent.java \
org/eclipse/swt/events/VerifyEvent.java \
org/eclipse/swt/events/VerifyListener.java \
org/eclipse/swt/graphics/Color.java \
org/eclipse/swt/graphics/Cursor.java \
org/eclipse/swt/graphics/Device.java \
org/eclipse/swt/graphics/DeviceData.java \
org/eclipse/swt/graphics/Drawable.java \
org/eclipse/swt/graphics/Font.java \
org/eclipse/swt/graphics/FontData.java \
org/eclipse/swt/graphics/FontMetrics.java \
org/eclipse/swt/graphics/GC.java \
org/eclipse/swt/graphics/GCData.java \
org/eclipse/swt/graphics/GlyphMetrics.java \
org/eclipse/swt/graphics/Image.java \
org/eclipse/swt/graphics/ImageData.java \
org/eclipse/swt/graphics/ImageDataLoader.java \
org/eclipse/swt/graphics/ImageLoader.java \
org/eclipse/swt/graphics/ImageLoaderEvent.java \
org/eclipse/swt/graphics/ImageLoaderListener.java \
org/eclipse/swt/graphics/LineAttributes.java \
org/eclipse/swt/graphics/PaletteData.java \
org/eclipse/swt/graphics/Path.java \
org/eclipse/swt/graphics/PathData.java \
org/eclipse/swt/graphics/Pattern.java \
org/eclipse/swt/graphics/Point.java \
org/eclipse/swt/graphics/RGB.java \
org/eclipse/swt/graphics/Rectangle.java \
org/eclipse/swt/graphics/Region.java \
org/eclipse/swt/graphics/Resource.java \
org/eclipse/swt/graphics/TextLayout.java \
org/eclipse/swt/graphics/TextStyle.java \
org/eclipse/swt/graphics/Transform.java \
org/eclipse/swt/internal/BidiUtil.java \
org/eclipse/swt/internal/C.java \
org/eclipse/swt/internal/Callback.java \
org/eclipse/swt/internal/CloneableCompatibility.java \
org/eclipse/swt/internal/Compatibility.java \
org/eclipse/swt/internal/ImageList.java \
org/eclipse/swt/internal/LONG.java \
org/eclipse/swt/internal/Library.java \
org/eclipse/swt/internal/Lock.java \
org/eclipse/swt/internal/Platform.java \
org/eclipse/swt/internal/SWTEventListener.java \
org/eclipse/swt/internal/SWTEventObject.java \
org/eclipse/swt/internal/SerializableCompatibility.java \
org/eclipse/swt/internal/gdip/BitmapData.java \
org/eclipse/swt/internal/gdip/ColorPalette.java \
org/eclipse/swt/internal/gdip/Gdip.java \
org/eclipse/swt/internal/gdip/GdiplusStartupInput.java \
org/eclipse/swt/internal/gdip/PointF.java \
org/eclipse/swt/internal/gdip/Rect.java \
org/eclipse/swt/internal/gdip/RectF.java \
org/eclipse/swt/internal/image/FileFormat.java \
org/eclipse/swt/internal/image/LEDataInputStream.java \
org/eclipse/swt/internal/image/LEDataOutputStream.java \
org/eclipse/swt/internal/image/PNGFileFormat.java \
org/eclipse/swt/internal/image/PngChunk.java \
org/eclipse/swt/internal/image/PngChunkReader.java \
org/eclipse/swt/internal/image/PngDecodingDataStream.java \
org/eclipse/swt/internal/image/PngDeflater.java \
org/eclipse/swt/internal/image/PngEncoder.java \
org/eclipse/swt/internal/image/PngFileReadState.java \
org/eclipse/swt/internal/image/PngHuffmanTable.java \
org/eclipse/swt/internal/image/PngHuffmanTables.java \
org/eclipse/swt/internal/image/PngIdatChunk.java \
org/eclipse/swt/internal/image/PngIendChunk.java \
org/eclipse/swt/internal/image/PngIhdrChunk.java \
org/eclipse/swt/internal/image/PngInputStream.java \
org/eclipse/swt/internal/image/PngLzBlockReader.java \
org/eclipse/swt/internal/image/PngPlteChunk.java \
org/eclipse/swt/internal/image/PngTrnsChunk.java \
org/eclipse/swt/internal/ole/win32/COM.java \
org/eclipse/swt/internal/ole/win32/COMObject.java \
org/eclipse/swt/internal/ole/win32/FORMATETC.java \
org/eclipse/swt/internal/ole/win32/GUID.java \
org/eclipse/swt/internal/ole/win32/IAccessible.java \
org/eclipse/swt/internal/ole/win32/IDataObject.java \
org/eclipse/swt/internal/ole/win32/IDispatch.java \
org/eclipse/swt/internal/ole/win32/IEnum.java \
org/eclipse/swt/internal/ole/win32/IEnumFORMATETC.java \
org/eclipse/swt/internal/ole/win32/IEnumVARIANT.java \
org/eclipse/swt/internal/ole/win32/IUnknown.java \
org/eclipse/swt/internal/ole/win32/STGMEDIUM.java \
org/eclipse/swt/internal/ole/win32/VARIANT.java \
org/eclipse/swt/internal/win32/ACCEL.java \
org/eclipse/swt/internal/win32/ACTCTX.java \
org/eclipse/swt/internal/win32/BITMAP.java \
org/eclipse/swt/internal/win32/BITMAPINFOHEADER.java \
org/eclipse/swt/internal/win32/BLENDFUNCTION.java \
org/eclipse/swt/internal/win32/BUTTON_IMAGELIST.java \
org/eclipse/swt/internal/win32/CANDIDATEFORM.java \
org/eclipse/swt/internal/win32/COMBOBOXINFO.java \
org/eclipse/swt/internal/win32/COMPOSITIONFORM.java \
org/eclipse/swt/internal/win32/CREATESTRUCT.java \
org/eclipse/swt/internal/win32/DEVMODE.java \
org/eclipse/swt/internal/win32/DEVMODEA.java \
org/eclipse/swt/internal/win32/DEVMODEW.java \
org/eclipse/swt/internal/win32/DIBSECTION.java \
org/eclipse/swt/internal/win32/DLLVERSIONINFO.java \
org/eclipse/swt/internal/win32/DOCINFO.java \
org/eclipse/swt/internal/win32/DRAWITEMSTRUCT.java \
org/eclipse/swt/internal/win32/DROPFILES.java \
org/eclipse/swt/internal/win32/EMR.java \
org/eclipse/swt/internal/win32/EMREXTCREATEFONTINDIRECTW.java \
org/eclipse/swt/internal/win32/EXTLOGFONTW.java \
org/eclipse/swt/internal/win32/FILETIME.java \
org/eclipse/swt/internal/win32/GCP_RESULTS.java \
org/eclipse/swt/internal/win32/GRADIENT_RECT.java \
org/eclipse/swt/internal/win32/GUITHREADINFO.java \
org/eclipse/swt/internal/win32/HDHITTESTINFO.java \
org/eclipse/swt/internal/win32/HDITEM.java \
org/eclipse/swt/internal/win32/HDLAYOUT.java \
org/eclipse/swt/internal/win32/HELPINFO.java \
org/eclipse/swt/internal/win32/HIGHCONTRAST.java \
org/eclipse/swt/internal/win32/ICONINFO.java \
org/eclipse/swt/internal/win32/INPUT.java \
org/eclipse/swt/internal/win32/KEYBDINPUT.java \
org/eclipse/swt/internal/win32/LOGBRUSH.java \
org/eclipse/swt/internal/win32/LOGFONT.java \
org/eclipse/swt/internal/win32/LOGFONTA.java \
org/eclipse/swt/internal/win32/LOGFONTW.java \
org/eclipse/swt/internal/win32/LOGPEN.java \
org/eclipse/swt/internal/win32/LRESULT.java \
org/eclipse/swt/internal/win32/LVCOLUMN.java \
org/eclipse/swt/internal/win32/LVHITTESTINFO.java \
org/eclipse/swt/internal/win32/LVITEM.java \
org/eclipse/swt/internal/win32/MEASUREITEMSTRUCT.java \
org/eclipse/swt/internal/win32/MENUBARINFO.java \
org/eclipse/swt/internal/win32/MENUINFO.java \
org/eclipse/swt/internal/win32/MENUITEMINFO.java \
org/eclipse/swt/internal/win32/MINMAXINFO.java \
org/eclipse/swt/internal/win32/MONITORINFO.java \
org/eclipse/swt/internal/win32/MOUSEINPUT.java \
org/eclipse/swt/internal/win32/MSG.java \
org/eclipse/swt/internal/win32/NMCUSTOMDRAW.java \
org/eclipse/swt/internal/win32/NMHDR.java \
org/eclipse/swt/internal/win32/NMHEADER.java \
org/eclipse/swt/internal/win32/NMLISTVIEW.java \
org/eclipse/swt/internal/win32/NMLVCUSTOMDRAW.java \
org/eclipse/swt/internal/win32/NMLVDISPINFO.java \
org/eclipse/swt/internal/win32/NMLVODSTATECHANGE.java \
org/eclipse/swt/internal/win32/NMRGINFO.java \
org/eclipse/swt/internal/win32/NMTREEVIEW.java \
org/eclipse/swt/internal/win32/NMTTCUSTOMDRAW.java \
org/eclipse/swt/internal/win32/NMTTDISPINFO.java \
org/eclipse/swt/internal/win32/NMTTDISPINFOA.java \
org/eclipse/swt/internal/win32/NMTTDISPINFOW.java \
org/eclipse/swt/internal/win32/NMTVCUSTOMDRAW.java \
org/eclipse/swt/internal/win32/NMTVDISPINFO.java \
org/eclipse/swt/internal/win32/NMTVITEMCHANGE.java \
org/eclipse/swt/internal/win32/NONCLIENTMETRICS.java \
org/eclipse/swt/internal/win32/NONCLIENTMETRICSA.java \
org/eclipse/swt/internal/win32/NONCLIENTMETRICSW.java \
org/eclipse/swt/internal/win32/NOTIFYICONDATA.java \
org/eclipse/swt/internal/win32/NOTIFYICONDATAA.java \
org/eclipse/swt/internal/win32/NOTIFYICONDATAW.java \
org/eclipse/swt/internal/win32/OFNOTIFY.java \
org/eclipse/swt/internal/win32/OPENFILENAME.java \
org/eclipse/swt/internal/win32/OS.java \
org/eclipse/swt/internal/win32/OSVERSIONINFO.java \
org/eclipse/swt/internal/win32/OSVERSIONINFOA.java \
org/eclipse/swt/internal/win32/OSVERSIONINFOEX.java \
org/eclipse/swt/internal/win32/OSVERSIONINFOEXA.java \
org/eclipse/swt/internal/win32/OSVERSIONINFOEXW.java \
org/eclipse/swt/internal/win32/OSVERSIONINFOW.java \
org/eclipse/swt/internal/win32/OUTLINETEXTMETRIC.java \
org/eclipse/swt/internal/win32/OUTLINETEXTMETRICA.java \
org/eclipse/swt/internal/win32/OUTLINETEXTMETRICW.java \
org/eclipse/swt/internal/win32/PAINTSTRUCT.java \
org/eclipse/swt/internal/win32/PANOSE.java \
org/eclipse/swt/internal/win32/POINT.java \
org/eclipse/swt/internal/win32/PRINTDLG.java \
org/eclipse/swt/internal/win32/PROCESS_INFORMATION.java \
org/eclipse/swt/internal/win32/RECT.java \
org/eclipse/swt/internal/win32/SCRIPT_ANALYSIS.java \
org/eclipse/swt/internal/win32/SCRIPT_CONTROL.java \
org/eclipse/swt/internal/win32/SCRIPT_DIGITSUBSTITUTE.java \
org/eclipse/swt/internal/win32/SCRIPT_FONTPROPERTIES.java \
org/eclipse/swt/internal/win32/SCRIPT_ITEM.java \
org/eclipse/swt/internal/win32/SCRIPT_LOGATTR.java \
org/eclipse/swt/internal/win32/SCRIPT_PROPERTIES.java \
org/eclipse/swt/internal/win32/SCRIPT_STATE.java \
org/eclipse/swt/internal/win32/SCROLLBARINFO.java \
org/eclipse/swt/internal/win32/SCROLLINFO.java \
org/eclipse/swt/internal/win32/SHACTIVATEINFO.java \
org/eclipse/swt/internal/win32/SHDRAGIMAGE.java \
org/eclipse/swt/internal/win32/SHELLEXECUTEINFO.java \
org/eclipse/swt/internal/win32/SHFILEINFO.java \
org/eclipse/swt/internal/win32/SHFILEINFOA.java \
org/eclipse/swt/internal/win32/SHFILEINFOW.java \
org/eclipse/swt/internal/win32/SHMENUBARINFO.java \
org/eclipse/swt/internal/win32/SHRGINFO.java \
org/eclipse/swt/internal/win32/SIPINFO.java \
org/eclipse/swt/internal/win32/SIZE.java \
org/eclipse/swt/internal/win32/STARTUPINFO.java \
org/eclipse/swt/internal/win32/TBBUTTON.java \
org/eclipse/swt/internal/win32/TBBUTTONINFO.java \
org/eclipse/swt/internal/win32/TCHAR.java \
org/eclipse/swt/internal/win32/TEXTMETRIC.java \
org/eclipse/swt/internal/win32/TEXTMETRICA.java \
org/eclipse/swt/internal/win32/TEXTMETRICW.java \
org/eclipse/swt/internal/win32/TF_DA_COLOR.java \
org/eclipse/swt/internal/win32/TF_DISPLAYATTRIBUTE.java \
org/eclipse/swt/internal/win32/TOOLINFO.java \
org/eclipse/swt/internal/win32/TRACKMOUSEEVENT.java \
org/eclipse/swt/internal/win32/TRIVERTEX.java \
org/eclipse/swt/internal/win32/TVHITTESTINFO.java \
org/eclipse/swt/internal/win32/TVINSERTSTRUCT.java \
org/eclipse/swt/internal/win32/TVITEM.java \
org/eclipse/swt/internal/win32/TVSORTCB.java \
org/eclipse/swt/internal/win32/WINDOWPLACEMENT.java \
org/eclipse/swt/internal/win32/WINDOWPOS.java \
org/eclipse/swt/internal/win32/WNDCLASS.java \
org/eclipse/swt/layout/FillData.java \
org/eclipse/swt/layout/FillLayout.java \
org/eclipse/swt/layout/FormAttachment.java \
org/eclipse/swt/layout/FormData.java \
org/eclipse/swt/layout/FormLayout.java \
org/eclipse/swt/layout/GridData.java \
org/eclipse/swt/layout/GridLayout.java \
org/eclipse/swt/layout/RowData.java \
org/eclipse/swt/layout/RowLayout.java \
org/eclipse/swt/ole/win32/OLE.java \
org/eclipse/swt/printing/Printer.java \
org/eclipse/swt/printing/PrinterData.java \
org/eclipse/swt/program/Program.java \
org/eclipse/swt/widgets/Button.java \
org/eclipse/swt/widgets/Canvas.java \
org/eclipse/swt/widgets/Caret.java \
org/eclipse/swt/widgets/Combo.java \
org/eclipse/swt/widgets/Composite.java \
org/eclipse/swt/widgets/Control.java \
org/eclipse/swt/widgets/Decorations.java \
org/eclipse/swt/widgets/Dialog.java \
org/eclipse/swt/widgets/Display.java \
org/eclipse/swt/widgets/Event.java \
org/eclipse/swt/widgets/EventTable.java \
org/eclipse/swt/widgets/FileDialog.java \
org/eclipse/swt/widgets/Group.java \
org/eclipse/swt/widgets/IME.java \
org/eclipse/swt/widgets/Item.java \
org/eclipse/swt/widgets/Label.java \
org/eclipse/swt/widgets/Layout.java \
org/eclipse/swt/widgets/List.java \
org/eclipse/swt/widgets/Listener.java \
org/eclipse/swt/widgets/Menu.java \
org/eclipse/swt/widgets/MenuItem.java \
org/eclipse/swt/widgets/MessageBox.java \
org/eclipse/swt/widgets/Monitor.java \
org/eclipse/swt/widgets/ProgressBar.java \
org/eclipse/swt/widgets/RunnableLock.java \
org/eclipse/swt/widgets/ScrollBar.java \
org/eclipse/swt/widgets/Scrollable.java \
org/eclipse/swt/widgets/Shell.java \
org/eclipse/swt/widgets/Synchronizer.java \
org/eclipse/swt/widgets/Table.java \
org/eclipse/swt/widgets/TableColumn.java \
org/eclipse/swt/widgets/TableItem.java \
org/eclipse/swt/widgets/Text.java \
org/eclipse/swt/widgets/ToolTip.java \
org/eclipse/swt/widgets/Tray.java \
org/eclipse/swt/widgets/TrayItem.java \
org/eclipse/swt/widgets/Tree.java \
org/eclipse/swt/widgets/TreeColumn.java \
org/eclipse/swt/widgets/TreeItem.java \
org/eclipse/swt/widgets/TypedListener.java \
org/eclipse/swt/widgets/Widget.java