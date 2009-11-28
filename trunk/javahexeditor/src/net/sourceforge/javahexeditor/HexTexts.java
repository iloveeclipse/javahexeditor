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
package net.sourceforge.javahexeditor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.custom.VerifyKeyListener;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Caret;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.widgets.Text;


/**
 * A binary file editor, composed of two synchronized displays: an hexadecimal and a basic ascii char
 * display. The file size has no effect on the memory footprint of the editor. It has binary, ascii
 * and unicode find functionality. Use addListener(SWT.Modify, Listener) to listen to changes of the
 * 'dirty', 'overwrite/insert', 'selection' and 'canUndo/canRedo' status.
 * @author Jordi
 */
public class HexTexts extends Composite
{


/**
 * Map of displayed chars. Chars that cannot be displayed correctly are changed for a '.' char.
 * There are slight differences on which chars can correctly be displayed in each operating system,
 * or font system. This is a lowest common denominator. Currently 0-1f, 7f-a0, and ad map to '.'
 */
public static final char[] byteToChar = new char[256];
static final String[] byteToHex = new String[256];
static final int charsForAddress = 12;  // Files up to 16 Ters: 11 hex digits + ':'
static final Color colorBlue = Display.getCurrent().getSystemColor(SWT.COLOR_BLUE);
static final Color colorLightShadow =
	Display.getCurrent().getSystemColor(SWT.COLOR_WIDGET_LIGHT_SHADOW);
static final Color colorNormalShadow =
	Display.getCurrent().getSystemColor(SWT.COLOR_WIDGET_NORMAL_SHADOW);
static final FontData fontDataDefault = new FontData("Courier New", 10, SWT.NORMAL);
static String headerRow = null;
static final byte[] hexToNibble = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, -1, -1, -1, -1, -1, -1, -1,
	10, 11, 12, 13, 14, 15, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
	-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15};
static final int maxScreenResolution = 1920;
static final int minCharSize = 5;
static final char[] nibbleToHex = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
static final int SET_TEXT = 0;
static final int SHIFT_FORWARD = 1;  // frame
static final int SHIFT_BACKWARD = 2;

int charsForFileSizeAddress = 0;
String charset = null;
boolean delayedInQueue = false;
Runnable delayedWaiting = null;
boolean dragging = false;
int fontCharWidth = -1;
ArrayList highlightRangesInScreen = null;
private ArrayList mergeChangeRanges = null;
private ArrayList mergeHighlightRanges = null;
private int mergeIndexChange = -2;
private int mergeIndexHighlight = -2;
private boolean mergeRangesIsBlue = false;
private boolean mergeRangesIsHighlight = false;
private int mergeRangesPosition = -1;
private int myBytesPerLine = 16;
private boolean myCaretStickToStart = false;  // stick to end
BinaryClipboard myClipboard = null;
BinaryContent myContent = null;
private long myEnd = 0L;
Finder myFinder = null;
boolean myInserting = false;
private KeyListener myKeyAdapter = new MyKeyAdapter();
private int myLastFocusedTextArea = -1;  // 1 or 2;
private long myLastLocationPosition = -1L;
ArrayList myLongSelectionListeners = null;
long myPreviousFindEnd = -1;
boolean myPreviousFindIgnoredCase = false;
String myPreviousFindString = null;
boolean myPreviousFindStringWasHex = false;
int myPreviousLine = -1;
long myPreviousRedrawStart = -1;
private long myStart = 0L;
private long myTextAreasStart = -1L;
final MyTraverseAdapter myTraverseAdapter = new MyTraverseAdapter();
int myUpANibble = 0;  // always 0 or 1
final MyVerifyKeyAdapter myVerifyKeyAdapter = new MyVerifyKeyAdapter();
int numberOfLines = 16;
int numberOfLines_1 = numberOfLines - 1;
private boolean stopSearching = false;
private byte[] tmpRawBuffer = new byte[maxScreenResolution / minCharSize / 3 * maxScreenResolution /
	minCharSize];
private int verticalBarFactor = 0;

// visual components
Color colorCaretLine = null;
Color colorHighlight = null;
Font fontCurrent = null;  // disposed externally
Font fontDefault = null;  // disposed internally
GridData gridData5 = null;
GridData gridData6 = null;
private GC styledText1GC = null;
private GC styledText2GC = null;
// indentation means containment (ie. 'textSeparator' and 'styledText' are contained within 'column')
private Composite column = null;
private Text          textSeparator = null;
private StyledText    styledText = null;
private Composite column1 = null;
private Composite     column1Header = null;
private StyledText        header1Text = null;
private StyledText    styledText1 = null;
private Composite column2 = null;
private Text          textSeparator2 = null;
private StyledText    styledText2 = null;

/**
 * compose byte-to-hex map
 */
private void composeByteToHexMap() {
	for (int i = 0; i < 256; ++i) {
		byteToHex[i] = Character.toString(nibbleToHex[i >>> 4]) + nibbleToHex[i & 0x0f];
	}
}

/**
 * compose byte-to-char map
 */
private void composeByteToCharMap() {
	CharsetDecoder d = Charset.forName(charset).newDecoder().
				onMalformedInput(CodingErrorAction.REPLACE).
				onUnmappableCharacter(CodingErrorAction.REPLACE).
				replaceWith(".");
	ByteBuffer bb = ByteBuffer.allocate(1);
	CharBuffer cb = CharBuffer.allocate(1);
	for (int i = 0; i < 256; ++i) {
		if (i < 0x20 || i == 0x7f) {
			byteToChar[i] = '.';
		} else {
			bb.clear();
			bb.put((byte)i);
			bb.rewind();
			cb.clear();
			d.reset();
			d.decode(bb, cb, true);
			d.flush(cb);
			cb.rewind();
			byteToChar[i] = cb.get();
		}
	}	
}

/**
 * compose header row
 */
private void composeHeaderRow() {
	StringBuffer rowChars = new StringBuffer();
	for (int i = 0; i < maxScreenResolution / minCharSize / 3; ++i)
		rowChars.append(byteToHex[i & 0x0ff]).append(' ');
	headerRow = rowChars.toString().toUpperCase();
}

public void setCharset(String name) {
	charset = name;
}

public String getCharset() {
	return charset;
}

public String getSystemCharset() {
	return System.getProperty("file.encoding", "utf-8");
//	return Charset.defaultCharset().toString();
}

public void setCharsetAndCompose(String name) {
	if ((name == null) || (name.length() == 0))
		name = getSystemCharset();
	setCharset(name);
	composeByteToCharMap();
}

/**
 * Get long selection start and end points. Helper method for long selection listeners.
 * The start point is formed by event.width as the most significant int and event.x as the least
 * significant int. The end point is similarly formed by event.height and event.y
 * @param selectionEvent an event with long selection start and end points
 * @see addLongSelectionListener(org.eclipse.swt.events.SelectionListener)
 */
public static long[] getLongSelection(SelectionEvent event) {
	return new long[] {((long)event.width) << 32 | (event.x & 0x0ffffffffL),
			((long)event.height) << 32 | (event.y & 0x0ffffffffL)};
}


/**
 * Converts a hex String to byte[]. Will convert full bytes only, odd number of hex characters will
 * have a leading '0' added. Big endian.
 * @param hexString an hex string (ie. "0fdA1").
 * @return the byte[] value of the hex string
 */
public static byte[] hexStringToByte(String hexString) {
	if ((hexString.length() & 1) == 1)  // nibbles promote to a full byte
		hexString = '0' + hexString;
	byte[] tmp = new byte[hexString.length() / 2];
	for (int i = 0; i < tmp.length; ++i) {
		String hexByte = hexString.substring(i * 2, i * 2 + 2);
		tmp[i] = (byte)Integer.parseInt(hexByte, 16);
	}
	
	return tmp;
}


private class MyKeyAdapter extends KeyAdapter {
	public void keyPressed(KeyEvent e) {
		switch (e.keyCode) {
			case SWT.ARROW_UP:
			case SWT.ARROW_DOWN:
			case SWT.ARROW_LEFT:
			case SWT.ARROW_RIGHT:
			case SWT.END:
			case SWT.HOME:
			case SWT.PAGE_UP:
			case SWT.PAGE_DOWN:
				boolean selection = myStart != myEnd;
				boolean ctrlKey = (e.stateMask & SWT.CONTROL) != 0;
				if ((e.stateMask & SWT.SHIFT) != 0) {  // shift mod2
					long newPos = doNavigateKeyPressed(ctrlKey, e.keyCode, getCaretPos(), false);
					shiftStartAndEnd(newPos);
				} else {  // if no modifier or control or alt
					myEnd = myStart = doNavigateKeyPressed(ctrlKey, e.keyCode, getCaretPos(),
							e.widget == styledText1 && !myInserting);
					myCaretStickToStart = false;
				}
				ensureCaretIsVisible();
				Runnable delayed = new Runnable() {public void run() {
					redrawTextAreas(false);
					runnableEnd();
				}};
				runnableAdd(delayed);
				notifyLongSelectionListeners();
				if (selection != (myStart != myEnd))
					notifyListeners(SWT.Modify, null);
				e.doit = false;
			break;
			case SWT.INSERT:
				if ((e.stateMask & SWT.MODIFIER_MASK) == 0) {
					redrawCaret(true);
				} else if (e.stateMask == SWT.SHIFT) {
					paste();
				} else if (e.stateMask == SWT.CONTROL) {
					copy();
				}
			break;
			case 'a':
				if (e.stateMask == SWT.CONTROL)  // control mod1
					selectAll();
			break;
			case 'c':
				if (e.stateMask == SWT.CONTROL)  // control mod1
					copy();
			break;
			case 'v':
				if (e.stateMask == SWT.CONTROL)  // control mod1
					paste();
			break;
			case 'x':
				if (e.stateMask == SWT.CONTROL)  // control mod1
					cut();
			break;
			case 'y':
				if (e.stateMask == SWT.CONTROL)  // control mod1
					redo();
			break;
			case 'z':
				if (e.stateMask == SWT.CONTROL)  // control mod1
					undo();
			break;
			default:
			break;
		}
	}
}


private class MyMouseAdapter extends MouseAdapter {
	int charLen;
	
	public MyMouseAdapter(boolean hexContent) {
		charLen = 1;
		if (hexContent) charLen = 3;
	}
	
	public void mouseDown(MouseEvent e) {
		if (e.button == 1)
			dragging = true;
		int textOffset = 0;
		try {
			textOffset = ((StyledText)e.widget).getOffsetAtLocation(new Point(e.x, e.y));
		} catch (IllegalArgumentException ex) {
			textOffset = ((StyledText)e.widget).getCharCount();
		}
		int byteOffset = textOffset / charLen;
		((StyledText)e.widget).setTopIndex(0);
		if (e.button == 1 && (e.stateMask & SWT.MODIFIER_MASK & ~SWT.SHIFT) == 0) {// no modif or shift
			if ((e.stateMask & SWT.MODIFIER_MASK) == 0) {
				myCaretStickToStart = false;
				myStart = myEnd = myTextAreasStart + byteOffset;
			} else {  // shift
				shiftStartAndEnd(myTextAreasStart + byteOffset);
			}
			refreshCaretsPosition();
			setFocus();
			refreshSelections();
			notifyListeners(SWT.Modify, null);
			notifyLongSelectionListeners();
		}
	}
	
	public void mouseUp(MouseEvent e) {
		if (e.button == 1)
			dragging = false;
	}
}


private class MyPaintAdapter implements PaintListener {
	boolean hexContent = false;
	MyPaintAdapter(boolean isHexText) {
		hexContent = isHexText;
	}
	public void paintControl(PaintEvent event) {
		event.gc.setForeground(colorLightShadow);
		int lineWidth = 1;
		int charLen = 1;
		int rightHalfWidth = 0;  // is 1, but better to tread on leftmost char pixel than rightmost one
		if (hexContent) {
			lineWidth = fontCharWidth;
			charLen = 3;
			rightHalfWidth = (lineWidth + 1) / 2;  // line spans to both sides of its position
		}
		event.gc.setLineWidth(lineWidth);
		for (int block=8; block<=myBytesPerLine; block+=8) {
			int xPos = (charLen * block) * fontCharWidth - rightHalfWidth;
			event.gc.drawLine(xPos, event.y, xPos, event.y + event.height);
		}
	}
}


private class MySelectionAdapter extends SelectionAdapter implements SelectionListener {
	int charLen;
	
	public MySelectionAdapter(boolean hexContent) {
		charLen = 1;
		if (hexContent) charLen = 3;
	}
	
	public void widgetSelected(SelectionEvent e) {
		if (!dragging)
			return;

		boolean selection = myStart != myEnd;
		int lower = e.x / charLen;
		int higher = e.y / charLen;
		int caretPos = ((StyledText)e.widget).getCaretOffset() / charLen;
		myCaretStickToStart = caretPos < higher || caretPos < lower;
		if (lower > higher) {
			lower = higher;
			higher = e.x / charLen;
		}
		
		select(myTextAreasStart + lower, myTextAreasStart + higher);
		if (selection != (myStart != myEnd))
			notifyListeners(SWT.Modify, null);

		redrawTextAreas(false);
	}
}


private class MyTraverseAdapter implements TraverseListener {
	public void keyTraversed(TraverseEvent e) {
		if (e.detail == SWT.TRAVERSE_TAB_NEXT)
			e.doit = true;
	}
}


private class MyVerifyKeyAdapter implements VerifyKeyListener {
	public void verifyKey(VerifyEvent e) {
//System.out.println("int:"+(int)e.character+", char:"+e.character+", keycode:"+e.keyCode);
		if ((e.character == SWT.DEL || e.character == SWT.BS) && myInserting) {
			if (!deleteSelected()) {
				if (e.character == SWT.BS) {
					myStart += myUpANibble;
					if (myStart > 0L) {
						myContent.delete(myStart - 1L, 1L);
						myEnd = --myStart;
					}
				} else {  // e.character == SWT.DEL
					myContent.delete(myStart, 1L);
				}
				ensureWholeScreenIsVisible();
				ensureCaretIsVisible();
				Runnable delayed = new Runnable() {public void run() {
					redrawTextAreas(true);
					runnableEnd();
				}};
				runnableAdd(delayed);
				updateScrollBar();
				
				notifyListeners(SWT.Modify, null);
				notifyLongSelectionListeners();
			}
			myUpANibble = 0;
		} else {
			doModifyKeyPressed(e);
		}

		e.doit = false;
	}
}


/**
 * Create a binary text editor
 * @param parent parent in the widget hierarchy
 * @param style not used for the moment
 */
public HexTexts(final Composite parent, int style) {
	super(parent, style | SWT.BORDER | SWT.V_SCROLL);
	
	colorCaretLine = new Color(Display.getCurrent(), 232, 242, 254);  // very light blue
	colorHighlight = new Color(Display.getCurrent(), 255, 248, 147);  // mellow yellow
	highlightRangesInScreen = new ArrayList();

	// set default charset & compose
	setCharsetAndCompose(null);
	composeByteToHexMap();	
	composeHeaderRow();

	myClipboard = new BinaryClipboard(parent.getDisplay());
	myLongSelectionListeners = new ArrayList();
	addDisposeListener(new DisposeListener() {
		public void widgetDisposed(DisposeEvent e) {
			colorCaretLine.dispose();
			colorHighlight.dispose();
			if (fontDefault != null && !fontDefault.isDisposed())
				fontDefault.dispose();
			try {
				myClipboard.dispose();
			} catch (IOException ex) {
				MessageBox box = new MessageBox(parent.getShell(), SWT.ICON_WARNING | SWT.OK);
				box.setText("Inconsistent clipboard files");
				box.setMessage( "Could not cleanup temporary clipboard files.\n" +
								"Clipboard files are stored in your user temp directory\n" +
								"with names 'javahexeditorClipboard' and 'javahexeditorPasted'");
				box.open();
			}
		}
	});
	initialize();
	myLastFocusedTextArea = 1;
	myPreviousLine = -1;
}

/**
 * redraw the caret with respect of Inserting/Overwriting mode
 */
public void redrawCaret(boolean focus) {
	drawUnfocusedCaret(false);
	setCaretsSize(focus ? (!myInserting) : myInserting);
	if (myInserting && myUpANibble != 0) {
		myUpANibble = 0;
		refreshCaretsPosition();
		if (focus) setFocus();
	} else {
		drawUnfocusedCaret(true);
	}
	if (focus) notifyListeners(SWT.Modify, null);
}

/**
 * Adds a long selection listener. Events sent to the listener have long start and end points.
 * The start point is formed by event.width as the most significant int and event.x as the least
 * significant int. The end point is similarly formed by event.height and event.y
 * A listener can obtain the long selection with this code: getLongSelection(SelectionEvent)
 * long start = ((long)event.width) << 32 | (event.x & 0x0ffffffffL)
 * Similarly for the end point:
 * long end = ((long)event.height) << 32 | (event.y & 0x0ffffffffL)
 * @param listener the listener
 * @see StyledText#addSelectionListener(org.eclipse.swt.events.SelectionListener)
 * @see getLongSelection(SelectionEvent)
 */
public void addLongSelectionListener(SelectionListener listener) {
	if (listener == null)
		throw new IllegalArgumentException();
	
	if (!myLongSelectionListeners.contains(listener))
		myLongSelectionListeners.add(listener);
}


/**
 * This method initializes composite	
 */
private void initialize() {
	GridLayout gridLayout1 = new GridLayout();
	gridLayout1.numColumns = 3;
	gridLayout1.marginHeight = 0;
	gridLayout1.verticalSpacing = 0;
	gridLayout1.horizontalSpacing = 0;
	gridLayout1.marginWidth = 0;
	setLayout(gridLayout1);

	column = new Composite(this, SWT.NONE);
	GridLayout columnLayout = new GridLayout();
	columnLayout.marginHeight = 0;
	columnLayout.verticalSpacing = 1;
	columnLayout.horizontalSpacing = 0;
	columnLayout.marginWidth = 0;
	column.setLayout(columnLayout);
	column.setBackground(colorLightShadow);
	GridData gridDataColumn = new GridData(SWT.BEGINNING, SWT.FILL, false, true);
	column.setLayoutData(gridDataColumn);
	
	GridData gridDataTextSeparator = new GridData(SWT.FILL, SWT.BEGINNING, true, false);
	gridDataTextSeparator.widthHint = 10;
	textSeparator = new Text(column, SWT.SEPARATOR);
	textSeparator.setEnabled(false);
	textSeparator.setBackground(colorLightShadow);
	textSeparator.setLayoutData(gridDataTextSeparator);

	styledText = new StyledText(column, SWT.MULTI | SWT.READ_ONLY);
	styledText.setEditable(false);
	styledText.setEnabled(false);
	styledText.setBackground(colorLightShadow);
	styledText.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_BLACK));
	fontDefault = new Font(Display.getCurrent(), fontDataDefault);
	fontCurrent = fontDefault;
	styledText.setFont(fontCurrent);
	GC styledTextGC = new GC(styledText);
	fontCharWidth = styledTextGC.getFontMetrics().getAverageCharWidth();
	styledTextGC.dispose();
	GridData gridDataAddresses = new GridData(SWT.BEGINNING, SWT.FILL, false, true);
	gridDataAddresses.heightHint = numberOfLines * styledText.getLineHeight();
	styledText.setLayoutData(gridDataAddresses);
	setAddressesGridDataWidthHint();
	styledText.setContent(new DisplayedContent(styledText, charsForAddress, numberOfLines));
	
	column1 = new Composite(this, SWT.NONE);
	GridLayout column1Layout = new GridLayout();
	column1Layout.marginHeight = 0;
	column1Layout.verticalSpacing = 1;
	column1Layout.horizontalSpacing = 0;
	column1Layout.marginWidth = 0;
	column1.setLayout(column1Layout);
	column1.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
	GridData gridDataColumn1 = new GridData(SWT.BEGINNING, SWT.FILL, false, true);
	column1.setLayoutData(gridDataColumn1);
	
	column1Header = new Composite(column1, SWT.NONE);
	column1Header.setBackground(colorLightShadow);
	GridLayout column1HeaderLayout = new GridLayout();
	column1HeaderLayout.marginHeight = 0;
	column1HeaderLayout.marginWidth = 0;
	column1Header.setLayout(column1HeaderLayout);
	GridData gridDataColumn1Header = new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false);
	column1Header.setLayoutData(gridDataColumn1Header);
		
	GridData gridData = new GridData();
	gridData.horizontalIndent = 1;
	header1Text = new StyledText(column1Header, SWT.SINGLE | SWT.READ_ONLY);
	header1Text.setEditable(false);
	header1Text.setEnabled(false);
	header1Text.setBackground(colorLightShadow);
	header1Text.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
	header1Text.setLayoutData(gridData);
	header1Text.setFont(fontCurrent);
	refreshHeader();

	styledText1 = new StyledText(column1, SWT.MULTI);
	styledText1.setFont(fontCurrent);
	styledText1GC = new GC(styledText1);
	int width = myBytesPerLine * 3 * fontCharWidth;
	gridData5 = new GridData();
	gridData5.horizontalIndent = 1;
	gridData5.verticalAlignment = SWT.FILL;
	gridData5.widthHint = styledText1.computeTrim(0, 0, width, 0).width;
	gridData5.grabExcessVerticalSpace = true;
	styledText1.setLayoutData(gridData5);
	styledText1.addKeyListener(myKeyAdapter);
	FocusListener myFocusAdapter = new FocusAdapter() {
		public void focusGained(FocusEvent e) {
			drawUnfocusedCaret(false);
			myLastFocusedTextArea = 1;
			if (e.widget == styledText2)
				myLastFocusedTextArea = 2;
			getDisplay().asyncExec(new Runnable() { public void run() {
				drawUnfocusedCaret(true);
			}});
		}
	};
	styledText1.addFocusListener(myFocusAdapter);
	styledText1.addMouseListener(new MyMouseAdapter(true));
	styledText1.addPaintListener(new MyPaintAdapter(true));
	styledText1.addTraverseListener(myTraverseAdapter);
	styledText1.addVerifyKeyListener(myVerifyKeyAdapter);
	styledText1.setContent(new DisplayedContent(styledText1, myBytesPerLine * 3, numberOfLines));
	styledText1.setDoubleClickEnabled(false);
	styledText1.addSelectionListener(new MySelectionAdapter(true));
	// StyledText.setCaretOffset() version 3.448 bug resets the caret size if using the default one,
	// so we use not the default one.
	Caret defaultCaret = styledText1.getCaret();
	Caret nonDefaultCaret = new Caret(defaultCaret.getParent(), defaultCaret.getStyle());
	nonDefaultCaret.setBounds(defaultCaret.getBounds());
	styledText1.setCaret(nonDefaultCaret);
	
	column2 = new Composite(this, SWT.NONE);
	GridLayout column2Layout = new GridLayout();
	column2Layout.marginHeight = 0;
	column2Layout.verticalSpacing = 1;
	column2Layout.horizontalSpacing = 0;
	column2Layout.marginWidth = 0;
	column2.setLayout(column2Layout);
	column2.setBackground(styledText1.getBackground());
	GridData gridDataColumn2 = new GridData(SWT.FILL, SWT.FILL, true, true);
	column2.setLayoutData(gridDataColumn2);

	GridData gridDataTextSeparator2 = new GridData();
	gridDataTextSeparator2.horizontalAlignment = SWT.FILL;
	gridDataTextSeparator2.verticalAlignment = SWT.FILL;
	gridDataTextSeparator2.grabExcessHorizontalSpace = true;
	textSeparator2 = new Text(column2, SWT.SEPARATOR);
	textSeparator2.setEnabled(false);
	textSeparator2.setBackground(colorLightShadow);
	textSeparator2.setLayoutData(gridDataTextSeparator2);
	makeFirstRowSameHeight();

	styledText2 = new StyledText(column2, SWT.MULTI);
	styledText2.setFont(fontCurrent);
	width = myBytesPerLine * fontCharWidth + 1;  // one pixel for caret in last column
	gridData6 = new GridData();
	gridData6.verticalAlignment = SWT.FILL;
	gridData6.widthHint = styledText2.computeTrim(0, 0, width, 0).width;
	gridData6.grabExcessVerticalSpace = true;
	styledText2.setLayoutData(gridData6);
	styledText2.addKeyListener(myKeyAdapter);
	styledText2.addFocusListener(myFocusAdapter);
	styledText2.addMouseListener(new MyMouseAdapter(false));
	styledText2.addPaintListener(new MyPaintAdapter(false));
	styledText2.addTraverseListener(myTraverseAdapter);
	styledText2.addVerifyKeyListener(myVerifyKeyAdapter);
	styledText2.setContent(new DisplayedContent(styledText2, myBytesPerLine, numberOfLines));
	styledText2.setDoubleClickEnabled(false);
	styledText2.addSelectionListener(new MySelectionAdapter(false));
	// StyledText.setCaretOffset() version 3.448 bug resets the caret size if using the default one,
	// so we use not the default one.
	defaultCaret = styledText2.getCaret();
	nonDefaultCaret = new Caret(defaultCaret.getParent(), defaultCaret.getStyle());
	nonDefaultCaret.setBounds(defaultCaret.getBounds());
	styledText2.setCaret(nonDefaultCaret);
	styledText2GC = new GC(styledText2);
	
	super.setFont(fontCurrent);
	ScrollBar vertical = getVerticalBar();
	vertical.setSelection(0);
	vertical.setMinimum(0);
	vertical.setIncrement(1);
	vertical.addSelectionListener(new SelectionAdapter() {
		public void widgetSelected(SelectionEvent e) {
			e.doit = false;
			long previousStart = myTextAreasStart;
			myTextAreasStart =
				(((long)getVerticalBar().getSelection()) << verticalBarFactor) * (long)myBytesPerLine;
			if (previousStart == myTextAreasStart) return;
			
			Runnable delayed = new Runnable() {public void run() {
				redrawTextAreas(false);
				setFocus();
				runnableEnd();
			}};
			runnableAdd(delayed);
		}
	});
	updateScrollBar();
	addMouseListener(new org.eclipse.swt.events.MouseAdapter() {
		public void mouseDown(org.eclipse.swt.events.MouseEvent e) {
			setFocus();
		}
	});
	addControlListener(new org.eclipse.swt.events.ControlAdapter() {
		public void controlResized(org.eclipse.swt.events.ControlEvent e) {
			updateTextsMetrics();
		}
	});
	addDisposeListener(new org.eclipse.swt.events.DisposeListener() {
		public void widgetDisposed(org.eclipse.swt.events.DisposeEvent e) {
			if (myContent != null)
				myContent.dispose();
		}
	});
}


/**
 * Tells whether the last action can be redone
 * @return true: an action ca be redone
 */
public boolean canRedo() {
	return myContent != null && myContent.canRedo();
}


/**
 * Tells whether the last action can be undone
 * @return true: an action ca be undone
 */
public boolean canUndo() {
	return myContent != null && myContent.canUndo();
}


/**
 * Copies the selection into the clipboard. If nothing is selected leaves the clipboard with its
 * current contents. The clipboard will hold text data (for pasting into a text editor) and binary
 * data (internal for HexText). Text data is limited to 4Mbytes, binary data is limited by disk space.
 */
public void copy() {
	if (myStart >= myEnd) return;

	myClipboard.setContents(myContent, myStart, myEnd - myStart);
}


StringBuffer cookAddresses(long address, int limit) {
	StringBuffer theText = new StringBuffer();
	for (int i = 0; i < limit; i += myBytesPerLine, address += myBytesPerLine) {
		boolean indenting = true;
		for (int j = (charsForAddress - 2) * 4; j > 0; j -= 4) {
			int nibble = ((int)(address >>> j)) & 0x0f;
			if (nibble != 0)
				indenting = false;
			if (indenting) {
				if (j >= (charsForFileSizeAddress * 4))
					theText.append(' ');
				else
					theText.append('0');
			} else {
				theText.append(nibbleToHex[nibble]);
			}
		}
		theText.append(nibbleToHex[((int)address) & 0x0f]).append(':');
	}
	
	return theText;
}


StringBuffer cookTexts(boolean isHexOutput, int length) {
	if (length > tmpRawBuffer.length) length = tmpRawBuffer.length;
	StringBuffer result = null;
	
	if (isHexOutput) {
		result = new StringBuffer(length * 3);
		for (int i=0; i<length; ++i) {
			result.append(byteToHex[tmpRawBuffer[i] & 0x0ff]).append(' ');
		}
	} else {
		result = new StringBuffer(length);
		for (int i=0; i<length; ++i) {
			result.append(byteToChar[tmpRawBuffer[i] & 0x0ff]);
		}
	}

	return result;
}


/**
 * Calls copy();deleteSelected();
 * @see copy(), deleteSelected()
 */
public void cut() {
	copy();
	deleteSelected();
}


/**
 * While in insert mode, trims the selection
 * @return did delete something
 */
public boolean deleteNotSelected() {
	if (!myInserting || myStart < 1L && myEnd >= myContent.length()) return false;
	
	myContent.delete(myEnd, myContent.length() - myEnd);
	myContent.delete(0L, myStart);
	myStart = 0L;
	myEnd = myContent.length();

	myUpANibble = 0;
	ensureWholeScreenIsVisible();
	restoreStateAfterModify();

	return true;
}


/**
 * While in insert mode, deletes the selection
 * @return did delete something
 */
public boolean deleteSelected() {
	if (!handleSelectedPreModify()) {
		return false;
	}
	myUpANibble = 0;
	ensureWholeScreenIsVisible();
	restoreStateAfterModify();
	
	return true;
}


void doModifyKeyPressed(KeyEvent event) {
	char aChar = event.character;
	if (aChar == '\0' || aChar == '\b' || aChar == '\u007f' || event.stateMask == SWT.CTRL ||
		event.widget == styledText1 && ((event.stateMask & SWT.MODIFIER_MASK) != 0 ||
		aChar < '0' || aChar > '9' && aChar < 'A' || aChar > 'F' && aChar < 'a' || aChar > 'f')) {
		return;
	}
	
	if (getCaretPos() == myContent.length() && !myInserting) {
		ensureCaretIsVisible();
		redrawTextAreas(false);
		return;
	}
	handleSelectedPreModify();
	try {
		if (myInserting) {
			if (event.widget == styledText2) {
				myContent.insert((byte)aChar, getCaretPos());
			} else if (myUpANibble == 0) {
				myContent.insert((byte)(hexToNibble[aChar - '0'] << 4), getCaretPos());
			} else {
				myContent.overwrite(hexToNibble[aChar - '0'], 4, 4, getCaretPos());
			}
		} else {
			if (event.widget == styledText2) {
				myContent.overwrite((byte)aChar, getCaretPos());
			} else {
				myContent.overwrite(hexToNibble[aChar - '0'], myUpANibble * 4, 4, getCaretPos());
			}
			myContent.get(ByteBuffer.wrap(tmpRawBuffer, 0, 1), null, getCaretPos());
			int offset = (int)(getCaretPos() - myTextAreasStart);
			styledText1.replaceTextRange(offset * 3, 2, byteToHex[tmpRawBuffer[0] & 0x0ff]);
			styledText1.setStyleRange(new StyleRange(offset * 3, 2, colorBlue, null));
			styledText2.replaceTextRange(offset, 1,
					Character.toString(byteToChar[tmpRawBuffer[0] & 0x0ff]));
			styledText2.setStyleRange(new StyleRange(offset, 1, colorBlue, null));
		}
	} catch (IOException e) {
		throw new RuntimeException(e);
	}
	myStart = myEnd = incrementPosWithinLimits(getCaretPos(), event.widget == styledText1);
	Runnable delayed = new Runnable() {public void run() {
		ensureCaretIsVisible();
		redrawTextAreas(false);
		if (myInserting) {
			updateScrollBar();
			redrawTextAreas(true);
		}
		refreshSelections();
		runnableEnd();
	}};
	runnableAdd(delayed);
	notifyListeners(SWT.Modify, null);
	notifyLongSelectionListeners();
}


private long doNavigateKeyPressed(boolean ctrlKey, int keyCode, long oldPos, boolean countNibbles)
{
	if (!countNibbles)
		myUpANibble = 0;
	switch (keyCode)
	{
		case SWT.ARROW_UP:
			if (oldPos >= myBytesPerLine) oldPos -= myBytesPerLine;
		break;
			
		case SWT.ARROW_DOWN:
			if (oldPos <= myContent.length() - myBytesPerLine) oldPos += myBytesPerLine;
			if (countNibbles && oldPos == myContent.length()) myUpANibble = 0;
		break;
		
		case SWT.ARROW_LEFT:
			if (countNibbles && (oldPos > 0 || oldPos == 0 && myUpANibble > 0)) {
				if (myUpANibble == 0) --oldPos;
				myUpANibble ^= 1;  // 1->0, 0->1
			}
			if (!countNibbles && oldPos > 0)
				--oldPos;
		break;
			
		case SWT.ARROW_RIGHT:
			oldPos = incrementPosWithinLimits(oldPos, countNibbles);
		break;
		
		case SWT.END:
			if (ctrlKey) {
				oldPos = myContent.length();
			} else {
				oldPos = oldPos - oldPos % myBytesPerLine + myBytesPerLine - 1L;
				if (oldPos >= myContent.length()) oldPos = myContent.length();
			}
			myUpANibble = 0;
			if (countNibbles && oldPos < myContent.length()) myUpANibble = 1;
		break;

		case SWT.HOME:
			if (ctrlKey) {
				oldPos = 0;
			} else {
				oldPos = oldPos - oldPos % myBytesPerLine;
			}
			myUpANibble = 0;
		break;
		
		case SWT.PAGE_UP:
			if (oldPos >= myBytesPerLine)
			{
				oldPos = oldPos - myBytesPerLine * numberOfLines_1;
				if (oldPos < 0L)
					oldPos = (oldPos + myBytesPerLine * numberOfLines_1) % myBytesPerLine;
			}
		break;
		
		case SWT.PAGE_DOWN:
			if (oldPos <= myContent.length() - myBytesPerLine)
			{
				oldPos = oldPos + myBytesPerLine * numberOfLines_1;
				if (oldPos > myContent.length())
					oldPos = oldPos -
						((oldPos - 1 - myContent.length()) / myBytesPerLine + 1) * myBytesPerLine;
			}
			if (countNibbles && oldPos == myContent.length()) myUpANibble = 0;
		break;
	}
	
	return oldPos;
}


void drawUnfocusedCaret(boolean visible) {
	if (styledText1.isDisposed()) return;
	
	GC unfocusedGC = null;
	Caret unfocusedCaret = null;
	int chars = 0;
	int shift = 0;
	if (myLastFocusedTextArea == 1) {
		unfocusedCaret = styledText2.getCaret();
		unfocusedGC = styledText2GC;
	} else {
		unfocusedCaret = styledText1.getCaret();
		unfocusedGC = styledText1GC;
		chars = 1;
		if (styledText1.getCaretOffset() % 3 == 1)
			shift = -1;
	}
	if (unfocusedCaret.getVisible()) {
		Rectangle unfocused = unfocusedCaret.getBounds();
		unfocusedGC.setForeground(visible ? colorNormalShadow : colorCaretLine);
		unfocusedGC.drawRectangle(unfocused.x + shift * unfocused.width, unfocused.y,
				unfocused.width << chars, unfocused.height - 1);
	}
}


void ensureCaretIsVisible() {
	long caretPos = getCaretPos();
	long posInLine = caretPos % myBytesPerLine;

	if (myTextAreasStart > caretPos) {
		myTextAreasStart = caretPos - posInLine;
	} else if (myTextAreasStart + myBytesPerLine * numberOfLines < caretPos ||
			myTextAreasStart + myBytesPerLine * numberOfLines == caretPos &&
			caretPos != myContent.length()) {
		myTextAreasStart = caretPos - posInLine - myBytesPerLine * numberOfLines_1;
		if (caretPos == myContent.length() && posInLine == 0)
			myTextAreasStart = caretPos - myBytesPerLine * numberOfLines;
		if (myTextAreasStart < 0L) myTextAreasStart = 0L;
	} else {

		return;
	}
	getVerticalBar().setSelection((int)((myTextAreasStart / myBytesPerLine) >>> verticalBarFactor));
}


private void ensureWholeScreenIsVisible() {
	if (myTextAreasStart + myBytesPerLine * numberOfLines > myContent.length())
		myTextAreasStart = myContent.length() - (myContent.length() - 1L) % myBytesPerLine - 1L -
							myBytesPerLine * numberOfLines_1;
	
	if (myTextAreasStart < 0L)
		myTextAreasStart = 0L;
}


/**
 * Performs a find on the text and sets the selection accordingly.
 * The find starts at the current caret position.
 * @param findString the literal to find
 * @param isHexString consider the literal as an hex string (ie. "0fdA1"). Used for binary finds.
 * Will search full bytes only, odd number of hex characters will have a leading '0' added.
 * @param searchForward look for matches after current position
 * @param ignoreCase match upper case with lower case characters
 * @return whether a match was found
 */
public boolean findAndSelect(String findString, boolean isHexString, boolean searchForward,
		boolean ignoreCase) throws IOException {
	boolean result  = findAndSelectInternal(findString, isHexString, searchForward, ignoreCase, true);
	
	return result;
}


private boolean findAndSelectInternal(String findString, boolean isHexString, boolean searchForward,
		boolean ignoreCase, boolean updateGui) throws IOException {
	if (findString == null) return true;
	
	initFinder(findString, isHexString, searchForward, ignoreCase);
	final Object[] result = new Object[2];
	Manager.blockUntilFinished(new Runnable() {
		public void run() {
			try {
				result[0] = myFinder.getNextMatch();
			} catch (IOException e) {
				result[1] = e;
			}
		}
	});
	if (result[1] != null) {
		throw (IOException)result[1];
	}
	Object[] vector = (Object[])result[0];
	if (vector != null && vector.length > 1 && vector[0] != null && vector[1] != null) {
		myStart = ((Long)vector[0]).longValue();
		myCaretStickToStart = false;
		if (updateGui) {
			setSelection(myStart, myStart + ((Integer)vector[1]).intValue());
		} else {
			select(myStart, myStart + ((Integer)vector[1]).intValue());
		}
		myPreviousFindEnd = getCaretPos();

		return true;
	}
	
	return false;
}


/**
 * Get caret position in file, which can be out of view
 * @return the current caret position
 */
public long getCaretPos()
{
	if (myCaretStickToStart)
		return myStart;
	else
		return myEnd;
}

public byte getActualValue() {
	return getValue(getCaretPos());
}

public byte getValue(long pos) {
	try {
		myContent.get(ByteBuffer.wrap(tmpRawBuffer, 0, 1), null, pos);
	} catch (IOException e) {
		e.printStackTrace();
	}
	return tmpRawBuffer[0];
}

/**
 * Get the binary content
 * @return the content being edited
 */
public BinaryContent getContent() {
	return myContent;
}


private void getHighlightRangesInScreen(long start, int length) {
	highlightRangesInScreen.clear();
	if (myLastLocationPosition >= start && myLastLocationPosition < start + length) {
		highlightRangesInScreen.add(new Integer((int)(myLastLocationPosition - myTextAreasStart)));
		highlightRangesInScreen.add(new Integer(1));
	}
}


/**
 * Gets the selection start and end points as long values
 * @return 2 elements long array, first one the start point (inclusive), second one the end point
 * (exclusive)
 */
public long[] getSelection() {
	return new long[]{myStart, myEnd};
}

public boolean isSelected() {
	return (myStart != myEnd);
}

boolean handleSelectedPreModify() {
	if (myStart == myEnd || !myInserting) return false;
	
	myContent.delete(myStart, myEnd - myStart);
	myEnd = myStart;
	
	return true;
}


long incrementPosWithinLimits(long oldPos, boolean countNibbles) {
	if (oldPos < myContent.length())
		if (countNibbles) {
			if (myUpANibble > 0) ++oldPos;
			myUpANibble ^= 1;  // 1->0, 0->1
		} else {
			++oldPos;
		}
	
	return oldPos;
}


private void initFinder(String findString, boolean isHexString, boolean searchForward,
		boolean ignoreCase) {
	if (!searchForward)
		myCaretStickToStart = true; 
	if (myFinder == null || !findString.equals(myPreviousFindString) ||
			isHexString != myPreviousFindStringWasHex || ignoreCase != myPreviousFindIgnoredCase) {
		myPreviousFindString = findString;
		myPreviousFindStringWasHex = isHexString;
		myPreviousFindIgnoredCase = ignoreCase;

		if (isHexString) {
			myFinder = new Finder(hexStringToByte(findString), myContent);
		} else {
			myFinder = new Finder(findString, myContent);
			if (ignoreCase)
				myFinder.setCaseSensitive(false);
		}
		myFinder.setNewStart(getCaretPos());
	}
	if (myPreviousFindEnd != getCaretPos()) {
		myFinder.setNewStart(getCaretPos());
	}
	myFinder.setDirectionForward(searchForward);
}


/**
 * Tells whether the input is in overwrite or insert mode
 * @return true: overwriting, false: inserting
 */
public boolean isOverwriteMode() {
	return !myInserting;
}


void makeFirstRowSameHeight() {
	((GridData)textSeparator.getLayoutData()).heightHint = 
		header1Text.computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
	((GridData)textSeparator2.getLayoutData()).heightHint = 
		header1Text.computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
}


/**
 * Merge ranges of changes in file with ranges of highlighted elements.
 * Finds lowest range border, finds next lowest range border. That's the first result. Keeps going
 * until last range border.
 * @return list of StyleRanges, each with a style of type 'changed', 'highlighted', or both.
 */
ArrayList mergeRanges(ArrayList changeRanges, ArrayList highlightRanges) {
	if (!mergerInit(changeRanges, highlightRanges)) {
		return null;
	}
	ArrayList result = new ArrayList();
	mergerNext();
	int start = mergeRangesPosition;
	boolean blue = mergeRangesIsBlue;
	boolean highlight = mergeRangesIsHighlight;
	while (mergerNext()) {
		if (blue || highlight) {
			result.add(new StyleRange(start, mergeRangesPosition - start, blue ? colorBlue : null,
					highlight ? colorHighlight : null));
		}
		start = mergeRangesPosition;
		blue = mergeRangesIsBlue;
		highlight = mergeRangesIsHighlight;
	}
	
	return result;
}


boolean mergerCatchUps() {
	boolean withinRange = false;
	if (mergeChangeRanges != null && mergeChangeRanges.size() > mergeIndexChange) {
		withinRange = true;
		if (mergerPosition(true) < mergeRangesPosition) {
			++mergeIndexChange;
		}
	}
	if (mergeHighlightRanges != null && mergeHighlightRanges.size() > mergeIndexHighlight) {
		withinRange = true;
		if (mergerPosition(false) < mergeRangesPosition) {
			++mergeIndexHighlight;
		}
	}
	
	return withinRange;
}


/**
 * Initialise merger variables
 * @return whether the parameters hold any data
 */
boolean mergerInit(ArrayList changeRanges, ArrayList highlightRanges) {
	if ((changeRanges == null || changeRanges.size() < 2) &&
		(highlightRanges == null || highlightRanges.size() < 2)) {
		return false;
	}
	this.mergeChangeRanges = changeRanges;
	this.mergeHighlightRanges = highlightRanges;
	mergeRangesIsBlue = false;
	mergeRangesIsHighlight = false;
	mergeRangesPosition = -1;
	mergeIndexChange = 0;
	mergeIndexHighlight = 0;
	
	return true;
}


int mergerMinimumInChangesHighlights() {
	int change = Integer.MAX_VALUE;
	if (mergeChangeRanges != null && mergeChangeRanges.size() > mergeIndexChange) {
		change = mergerPosition(true);
	}
	int highlight = Integer.MAX_VALUE;
	if (mergeHighlightRanges != null && mergeHighlightRanges.size() > mergeIndexHighlight) {
		highlight = mergerPosition(false);
	}
	int result = Math.min(change, highlight);
	if (change == result) {
		mergeRangesIsBlue = (mergeIndexChange & 1) == 0;
	}
	if (highlight == result) {
		mergeRangesIsHighlight = (mergeIndexHighlight & 1) == 0;
	}
	
	return result;
}


boolean mergerNext() {
	++mergeRangesPosition;
	if (!mergerCatchUps()) {
		return false;
	}
	mergeRangesPosition = mergerMinimumInChangesHighlights();
	
	return true;
}


int mergerPosition(boolean changesNotHighlights) {
	int result = -1;
	if (changesNotHighlights) {
		result = (int)(((Long)mergeChangeRanges.get(mergeIndexChange & 0xfffffffe)).longValue() -
				myTextAreasStart);
		if ((mergeIndexChange & 1) == 1) {
			result = (int)Math.min(myBytesPerLine * numberOfLines,
					result + ((Long)mergeChangeRanges.get(mergeIndexChange)).longValue());
		}
	} else {
		result = ((Integer)mergeHighlightRanges.get(mergeIndexHighlight & 0xfffffffe)).intValue();
		if ((mergeIndexHighlight & 1) == 1) {
			result += ((Integer)mergeHighlightRanges.get(mergeIndexHighlight)).intValue();
		}
	}
	
	return result;
}


void notifyLongSelectionListeners() {
	if (myLongSelectionListeners.isEmpty()) return;
	
	Event basicEvent = new Event();
	basicEvent.widget = this;
	SelectionEvent anEvent = new SelectionEvent(basicEvent);
	anEvent.width = (int)(myStart >>> 32);
	anEvent.x = (int)myStart;
	anEvent.height = (int)(myEnd >>> 32);
	anEvent.y = (int)myEnd;
	
	Iterator listeners = myLongSelectionListeners.iterator();
	
	while (listeners.hasNext()) {
		SelectionListener aListener = (SelectionListener)listeners.next();
		aListener.widgetSelected(anEvent);
	}
}


/**
 * Pastes the clipboard content. The result depends on which insertion mode is currently active:
 * Insert mode replaces the selection with the DND.CLIPBOARD clipboard contents or, if there is no
 * selection, inserts at the current caret offset.
 * Overwrite mode replaces contents at the current caret offset, unless pasting would overflow the
 * content length, in which case does nothing.
 */
public void paste() {
	if (!myClipboard.hasContents()) return;
	
	handleSelectedPreModify();
	long caretPos = getCaretPos();
	long total = myClipboard.getContents(myContent, caretPos, myInserting);
	myStart = caretPos;
	myEnd = caretPos + total;
	myCaretStickToStart = false;
	redrawTextAreas(true);
	restoreStateAfterModify();
}


/**
 * Redoes the last undone action
 */
public void redo() {
	undo(false);
}


void redrawTextAreas(int mode, StringBuffer newText, StringBuffer resultHex, StringBuffer resultChar,
				ArrayList viewRanges) {
	styledText1.getCaret().setVisible(false);
	styledText2.getCaret().setVisible(false);
	if (mode == SET_TEXT) {
		styledText.getContent().setText(newText.toString());
		styledText1.getContent().setText(resultHex.toString());
		styledText2.getContent().setText(resultChar.toString());
		myPreviousLine = -1;
	} else {
		boolean forward = mode == SHIFT_FORWARD;
		styledText.setRedraw(false);
		styledText1.setRedraw(false);
		styledText2.setRedraw(false);
		((DisplayedContent)styledText.getContent()).shiftLines(newText.toString(), forward);
		((DisplayedContent)styledText1.getContent()).shiftLines(resultHex.toString(), forward);
		((DisplayedContent)styledText2.getContent()).shiftLines(resultChar.toString(), forward);
		styledText.setRedraw(true);
		styledText1.setRedraw(true);
		styledText2.setRedraw(true);
		if (myPreviousLine >= 0 && myPreviousLine < numberOfLines)
			myPreviousLine += newText.length() / charsForAddress * (forward ? 1 : -1);
		if (myPreviousLine < -1 || myPreviousLine >= numberOfLines)
			myPreviousLine = -1;
	}
	if (viewRanges != null) {
		for (Iterator i=viewRanges.iterator(); i.hasNext();) {
			StyleRange styleRange = (StyleRange)i.next();
			styledText2.setStyleRange(styleRange);
			styleRange = (StyleRange)styleRange.clone();
			styleRange.start *= 3;
			styleRange.length *= 3;
			styledText1.setStyleRange(styleRange);
		}
	}
}


void redrawTextAreas(boolean fromScratch) {
	if (myContent == null || styledText1.isDisposed()) return;

	long newLinesStart = myTextAreasStart;
	int linesShifted = numberOfLines;
	int mode = SET_TEXT;
	if (!fromScratch && myPreviousRedrawStart >= 0L) {
		long lines = (myTextAreasStart - myPreviousRedrawStart) / myBytesPerLine;
		if (Math.abs(lines) < numberOfLines) {
			mode = lines > 0L ? SHIFT_BACKWARD : SHIFT_FORWARD;
			linesShifted = Math.abs((int)lines);
			if (linesShifted < 1) {
				refreshSelections();
				refreshCaretsPosition();

				return;
			}
			if (mode == SHIFT_BACKWARD)
				newLinesStart = myTextAreasStart + (numberOfLines - (int)lines) * myBytesPerLine;
		}
	}
	myPreviousRedrawStart = myTextAreasStart;
	
	StringBuffer newText = cookAddresses(newLinesStart, linesShifted * myBytesPerLine);
	
	ArrayList changeRanges = new ArrayList();
	int actuallyRead = 0;
	try {
		actuallyRead = myContent.get(ByteBuffer.wrap(tmpRawBuffer, 0, linesShifted * myBytesPerLine),
									changeRanges, newLinesStart);
	} catch (IOException e) {
		actuallyRead = 0;
	}
	StringBuffer resultHex = cookTexts(true, actuallyRead);
	StringBuffer resultChar = cookTexts(false, actuallyRead);
	getHighlightRangesInScreen(newLinesStart, linesShifted * myBytesPerLine);
	ArrayList viewRanges = mergeRanges(changeRanges, highlightRangesInScreen);
	redrawTextAreas(mode, newText, resultHex, resultChar, viewRanges);
	refreshSelections();
	refreshCaretsPosition();
}


private void refreshCaretsPosition() {
	drawUnfocusedCaret(false);
	long caretLocation = getCaretPos() - myTextAreasStart;
	if (caretLocation >= 0L && caretLocation < myBytesPerLine * numberOfLines ||
		getCaretPos() == myContent.length() && caretLocation == myBytesPerLine * numberOfLines) {
		int tmp = (int)caretLocation;
		if (tmp == myBytesPerLine * numberOfLines) {
			styledText1.setCaretOffset(tmp * 3 - 1);
			styledText2.setCaretOffset(tmp);
		} else {
			styledText1.setCaretOffset(tmp * 3 + myUpANibble);
			styledText2.setCaretOffset(tmp);
		}
		int line = styledText1.getLineAtOffset(styledText1.getCaretOffset());
		if (line != myPreviousLine) {
			if (myPreviousLine >= 0 && myPreviousLine < numberOfLines) {
				styledText1.setLineBackground(myPreviousLine, 1, null);
				styledText2.setLineBackground(myPreviousLine, 1, null);
			}
			styledText1.setLineBackground(line, 1, colorCaretLine);
			styledText2.setLineBackground(line, 1, colorCaretLine);
			myPreviousLine = line;
		}
		styledText1.getCaret().setVisible(true);
		styledText2.getCaret().setVisible(true);
		getDisplay().asyncExec(new Runnable() { public void run() {
			drawUnfocusedCaret(true);
		}});
	} else {
		styledText1.getCaret().setVisible(false);
		styledText2.getCaret().setVisible(false);
	}
}


void refreshHeader() {
	header1Text.setText(headerRow.substring(0, Math.min(myBytesPerLine * 3, headerRow.length())));
}


void refreshSelections() {
	if (myStart >= myEnd ||
		myStart > myTextAreasStart + myBytesPerLine * numberOfLines ||
		myEnd <= myTextAreasStart)
		return;
	
	long startLocation = myStart - myTextAreasStart;
	if (startLocation < 0L) startLocation = 0L;
	int intStart = (int)startLocation;
	
	long endLocation = myEnd - myTextAreasStart;
	if (endLocation > myBytesPerLine * numberOfLines)
		endLocation = myBytesPerLine * numberOfLines;
	int intEnd = (int)endLocation;
	
	if (myCaretStickToStart) {
		int tmp = intStart;
		intStart = intEnd;
		intEnd = tmp;
	}

	styledText1.setSelection(intStart * 3, intEnd * 3);
	styledText1.setTopIndex(0);
	styledText2.setSelection(intStart, intEnd);
	styledText2.setTopIndex(0);
}


/**
 * Removes the specified selection listener
 * @see StyledText#removeSelectionListener(org.eclipse.swt.events.SelectionListener)
 */
public void removeLongSelectionListener(SelectionListener listener)
{
	if (listener == null)
		throw new IllegalArgumentException();
	
	myLongSelectionListeners.remove(listener);
}


/**
 * Replaces the selection. The result depends on which insertion mode is currently active:
 * Insert mode replaces the selection with the replaceString or, if there is no selection, inserts
 * at the current caret offset.
 * Overwrite mode replaces contents at the current selection start.
 * @param replaceString the new string
 * @param isHexString consider the literal as an hex string (ie. "0fdA1"). Used for binary finds.
 * Will replace full bytes only, odd number of hex characters will have a leading '0' added.
 */
public void replace(String replaceString, boolean isHexString) {
	handleSelectedPreModify();
	byte[] replaceData = replaceString.getBytes();
	if (isHexString) {
		replaceData = hexStringToByte(replaceString);
	}
	ByteBuffer newSelection = ByteBuffer.wrap(replaceData);
	if (myInserting) {
		myContent.insert(newSelection, myStart);
	} else {
		newSelection.limit((int)Math.min(newSelection.limit(), myContent.length() - myStart));
		myContent.overwrite(newSelection, myStart);
	}
	myEnd = myStart + newSelection.limit() - newSelection.position();
	myCaretStickToStart = false;
	redrawTextAreas(true);
	restoreStateAfterModify();
}


/**
 * Replaces all occurrences of findString with replaceString.
 * The find starts at the current caret position.
 * @param findString the literal to find
 * @param isFindHexString consider the literal as an hex string (ie. "0fdA1"). Used for binary finds.
 * Will search full bytes only, odd number of hex characters will have a leading '0' added.
 * @param searchForward look for matches after current position
 * @param ignoreCase match upper case with lower case characters
 * @param replaceString the new string
 * @param isReplaceHexString consider the literal as an hex string (ie. "0fdA1"). Used for binary
 * finds. Will replace full bytes only, odd number of hex characters will have a leading '0' added.
 * @return number of replacements
 */
public int replaceAll(String findString, boolean isFindHexString, boolean searchForward,
		boolean ignoreCase, String replaceString, boolean isReplaceHexString) throws IOException {
	int result = 0;
	stopSearching = false;
	while (!stopSearching && 
			findAndSelectInternal(findString, isFindHexString, searchForward, ignoreCase, false)) {
		++result;
		replace(replaceString, isReplaceHexString);
	}
	if (result > 0) {
		setSelection(getSelection()[0], getSelection()[1]);
	}
	
	return result;
}


void restoreStateAfterModify() {
	ensureCaretIsVisible();
	redrawTextAreas(true);
	updateScrollBar();
	
	notifyListeners(SWT.Modify, null);
	notifyLongSelectionListeners();
}


void runnableAdd(Runnable delayed) {
	if (delayedInQueue) {
		delayedWaiting = delayed;
	} else {
		delayedInQueue = true;
		Display.getCurrent().asyncExec(delayed);
	}
}


void runnableEnd() {
	if (delayedWaiting != null) {
		Display.getCurrent().asyncExec(delayedWaiting);
		delayedWaiting = null;
	} else {
		delayedInQueue = false;
	}
}


/**
 * Sets the selection to the entire text. Caret remains either at the selection start or end
 */
public void selectAll() {
	select(0L, myContent.length());
	refreshSelections();
}

/**
 * Sets the selection from start to end.
 */
public void selectBlock(long start, long end) {
	select(start, end);
	refreshSelections();
	showMark(start);
}

void select(long start, long end) {
	myUpANibble = 0;
	boolean selection = myStart != myEnd;
	myStart = 0L;
	if (start > 0L) {
		myStart = start;
		if (myStart > myContent.length()) myStart = myContent.length();
	}
	
	myEnd = myStart;
	if (end > myStart) {
		myEnd = end;
		if (myEnd > myContent.length()) myEnd = myContent.length();
	}

	notifyLongSelectionListeners();
	if (selection != (myStart != myEnd))
		notifyListeners(SWT.Modify, null);
}


void setAddressesGridDataWidthHint() {
	((GridData)styledText.getLayoutData()).widthHint = charsForAddress * fontCharWidth;
}


void setCaretsSize(boolean insert) {
	myInserting = insert;
	int width = 0;
	int height = styledText1.getCaret().getSize().y;
	if (!myInserting)
		width = fontCharWidth;
	
	styledText1.getCaret().setSize(width, height);
	styledText2.getCaret().setSize(width, height);
}


/**
 * Sets the content to be displayed. Replacing an existing content keeps the display area in the
 * same position, but only if it falls within the new content's limits.
 * @param aContent the content to be displayed
 */
public void setContentProvider(BinaryContent aContent) {
	boolean firstContent = myContent == null;
	myContent = aContent;
	myFinder = null;
	if (myContent != null) {		
		setCharsetAndCompose(myContent.getCharset());
		myContent.setActionsHistory();
	}

	if (firstContent || myEnd > myContent.length() || myTextAreasStart >= myContent.length()) {
		myTextAreasStart = myStart = myEnd = 0L;
		myCaretStickToStart = false;
	}
	
	charsForFileSizeAddress = Long.toHexString(myContent.length()).length();

	updateScrollBar();
	redrawTextAreas(true);
	notifyLongSelectionListeners();
	notifyListeners(SWT.Modify, null);
}


/**
 * Causes the receiver to have the keyboard focus. Within Eclipse, never call setFocus() before
 * the workbench has called EditorActionBarContributor.setActiveEditor()
 * @see Composite#setFocus()
 */
public boolean setFocus() {
	redrawCaret(false);
	if (myLastFocusedTextArea == 1)
		return styledText1.setFocus();
	else
		return styledText2.setFocus();
}


/**
 * @see Control#setFont(org.eclipse.swt.graphics.Font)
 * Font height must not be 1 or 2.
 * @throws IllegalArgumentException if font height is 1 or 2
 */
public void setFont(Font font) {
	// bugfix: HexText's raw array overflows when font is very small and window very big
	// very small sizes would compromise responsiveness in large windows, and they are too small
	// to see anyway
	if (font != null) {
		int newSize = font.getFontData()[0].getHeight();
		if (newSize == 1 || newSize == 2)
			throw new IllegalArgumentException("Font size is " + newSize + ", too small");
	}

	fontCurrent = font;
	if (fontCurrent == null) {
		fontCurrent = fontDefault;
	}
	super.setFont(fontCurrent);
	header1Text.setFont(fontCurrent);
	header1Text.pack(true);
	GC gc = new GC(header1Text);
	fontCharWidth = gc.getFontMetrics().getAverageCharWidth();
	gc.dispose();
	makeFirstRowSameHeight();
	styledText.setFont(fontCurrent);
	setAddressesGridDataWidthHint();
	styledText.pack(true);
	styledText1.setFont(fontCurrent);
	styledText1.pack(true);
	styledText2.setFont(fontCurrent);
	styledText2.pack(true);
	updateTextsMetrics();
	layout();
	setCaretsSize(myInserting);
}


/**
 * Sets the selection. The caret may change position but stays at the same selection point (if it
 * was at the start of the selection it will move to the new start, otherwise to the new end point).
 * The new selection is made visible
 * @param start inclusive start selection char
 * @param end exclusive end selection char
 */
public void setSelection(long start, long end) {
	select(start, end);
	ensureCaretIsVisible();
	redrawTextAreas(false);
}


void shiftStartAndEnd(long newPos) {
	if (myCaretStickToStart) {
		myStart = Math.min(newPos, myEnd);
		myEnd = Math.max(newPos, myEnd);
	} else {
		myEnd = Math.max(newPos, myStart);
		myStart = Math.min(newPos, myStart);
	}
	myCaretStickToStart = myEnd != newPos;
}


/**
 * Shows the position on screen.
 * @param position where relocation should go
 */
public void showMark(long position) {
	myLastLocationPosition = position;
	if (position < 0) return;
	
	position = position - position % myBytesPerLine;
	myTextAreasStart = position;
	if (numberOfLines > 2)
		myTextAreasStart = position - (numberOfLines / 2) * myBytesPerLine;
	ensureWholeScreenIsVisible();
	redrawTextAreas(true);
//	setFocus();
	updateScrollBar();
}


/**
 * Stop findAndSelect() or replaceAll() calls. Long running searches can be stopped from another
 * thread.
 */
public void stopSearching() {
	stopSearching = true;
	if (myFinder != null) {
		myFinder.stopSearching();
	}
}


long totalNumberOfLines() {
	long result = 1L;
	if (myContent != null) {
		result = (myContent.length() - 1L) / myBytesPerLine + 1L;
	}
	
	return result;
}


/**
 * Undoes the last action
 */
public void undo() {
	undo(true);
}


void undo(boolean previousAction) {
	long[] selection = previousAction ? myContent.undo() : myContent.redo();
	if (selection == null) return;
	
	myUpANibble = 0;
	myStart = selection[0];
	myEnd = selection[1];
	myCaretStickToStart = false;
	ensureWholeScreenIsVisible();
	restoreStateAfterModify();
}


void updateNumberOfLines() {
	int height = getClientArea().height - header1Text.computeSize(SWT.DEFAULT, SWT.DEFAULT, false).y;

	numberOfLines = height / styledText.getLineHeight();
	if (numberOfLines < 1)
		numberOfLines = 1;
	
	numberOfLines_1 = numberOfLines - 1;
	
	((DisplayedContent)styledText.getContent()).setDimensions(charsForAddress, numberOfLines);
	((DisplayedContent)styledText1.getContent()).setDimensions(myBytesPerLine * 3, numberOfLines);
	((DisplayedContent)styledText2.getContent()).setDimensions(myBytesPerLine, numberOfLines);
}


private void updateScrollBar() {
	ScrollBar vertical = getVerticalBar();
	long max = totalNumberOfLines();
	verticalBarFactor = 0;
	while (max > Integer.MAX_VALUE) {
		max >>>= 1;
		++verticalBarFactor;
	}
	vertical.setMaximum((int)max);
	vertical.setSelection((int)((myTextAreasStart / myBytesPerLine) >>> verticalBarFactor));
	vertical.setPageIncrement(numberOfLines_1);
	vertical.setThumb(numberOfLines);
}


void updateTextsMetrics() {
	int width = getClientArea().width - styledText.computeSize(SWT.DEFAULT, SWT.DEFAULT).x;
	int displayedNumberWidth = fontCharWidth * 4;  // styledText1 and styledText2
	myBytesPerLine = (width / displayedNumberWidth) & 0xfffffff8;  // 0, 8, 16, 24, etc.
	if (myBytesPerLine < 16)
		myBytesPerLine = 16;
	gridData5.widthHint = styledText1.computeTrim(0, 0,
			myBytesPerLine * 3 * fontCharWidth, 100).width;
	gridData6.widthHint = styledText2.computeTrim(0, 0,
			myBytesPerLine * fontCharWidth, 100).width;
	updateNumberOfLines();
	changed(new Control[]{header1Text, styledText, styledText1, styledText2});
	updateScrollBar();
	refreshHeader();
	myTextAreasStart = (((long)getVerticalBar().getSelection()) * myBytesPerLine) << verticalBarFactor;
	redrawTextAreas(true);
}

}
