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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * Manager of all preferences-editing widgets, with an optional standalone dialog.
 * @author Jordi
 */
public class PreferencesManager {
	

static final int itemsDisplayed = 9;  // Number of font names displayed in list
static final TreeSet scalableSizes = new TreeSet(Arrays.asList(new Integer[]
	{new Integer(6), new Integer(7), new Integer(8), new Integer(9), new Integer(10),
	new Integer(11), new Integer(12), new Integer(13), new Integer(14), new Integer(16),
	new Integer(18), new Integer(22), new Integer(32), new Integer(72)}));
static final String textBold = "Bold";
static final String textBoldItalic = "Bold Italic";
static final String textItalic = "Italic";
static final String textRegular = "Regular";


int dialogResult = SWT.CANCEL;
ArrayList fontsListCurrent = null;
ArrayList fontsNonScalable = null;
ArrayList fontsScalable = null;
GC fontsGc = null;
HashSet fontsRejected = null;
TreeMap fontsSorted = null;
FontData initialFontData = null;
FontData sampleFontData = null;

// visual components
private Button buttonCancel = null;
private Button buttonDefault = null;
private Button buttonOk = null;
private Composite composite = null;
private Composite compositeOkCancel = null;
private Composite parent = null;
private Group group = null;
private Label label = null;
private Text text = null;
private Text text1 = null;
private Text text2 = null;
private List list = null;
private List list1 = null;
private List list2 = null;
private Font sampleFont = null;
private Text sampleText = null;
private Label label1 = null;
private Label label2 = null;
private Label label3 = null;
private Shell dialog = null;


static int fontStyleToInt(String styleString) {
	int style = SWT.NORMAL;
	if (textBold.equals(styleString))
		style = SWT.BOLD;
	else if (textItalic.equals(styleString))
		style = SWT.ITALIC;
	else if (textBoldItalic.equals(styleString))
		style = SWT.BOLD | SWT.ITALIC;
	
	return style;
}


static String fontStyleToString(int style) {
	switch (style) {
		case SWT.BOLD: return textBold;
		case SWT.ITALIC: return textItalic;
		case SWT.BOLD | SWT.ITALIC: return textBoldItalic;
		default: return textRegular;
	}
}


public PreferencesManager(FontData aFontData) {
	initialFontData = sampleFontData = aFontData;
	fontsSorted = new TreeMap();
}


/**
 * Creates all internal widgets
 */
void createComposite() {
	composite = new Composite(parent, SWT.NONE);
	composite.setLayout(new FillLayout());

	group = new Group(composite, SWT.NONE);
	group.setText("Font selection");
	group.setVisible(true);
	GridLayout gridLayout = new GridLayout();
	gridLayout.numColumns = 3;
	group.setLayout(gridLayout);

	label = new Label(group, SWT.NONE);
	label.setText("Available fixed char width fonts:");
	label.setVisible(true);
	GridData gridData = new GridData();
	gridData.horizontalSpan = 3;
	label.setLayoutData(gridData);
	label1 = new Label(group, SWT.NONE);
	label1.setText("Name");
	label2 = new Label(group, SWT.NONE);
	label2.setText("Style");
	label3 = new Label(group, SWT.NONE);
	label3.setText("Size");

	text = new Text(group, SWT.SINGLE | SWT.BORDER);
	GridData gridData4 = new GridData();
	gridData4.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
	text.setLayoutData(gridData4);
	text1 = new Text(group, SWT.BORDER);
	GridData gridData5 = new GridData();
	gridData5.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
	text1.setLayoutData(gridData5);
	text1.setEnabled(false);
	text2 = new Text(group, SWT.BORDER);
	GridData gridData6 = new GridData();
	gridData6.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
	GC gc = new GC(group);
	int averageCharWidth = gc.getFontMetrics().getAverageCharWidth();
	gc.dispose();
	gridData6.widthHint = averageCharWidth * 6;
	text2.setLayoutData(gridData6);

	list = new List(group, SWT.SINGLE | SWT.V_SCROLL | SWT.BORDER);
	GridData gridData52 = new GridData();
	gridData52.heightHint = itemsDisplayed * list.getItemHeight();
	gridData52.widthHint = averageCharWidth * 40;
	list.setLayoutData(gridData52);
	list.addSelectionListener(new SelectionAdapter() {
		public void widgetSelected(SelectionEvent e) {
			text.setText(list.getSelection()[0]);
			updateSizeItemsAndGuessSelected();
			updateAndRefreshSample();
		}
	});

	list1 = new List(group, SWT.SINGLE | SWT.BORDER);
	GridData gridData21 = new GridData();
	gridData21.verticalAlignment = org.eclipse.swt.layout.GridData.FILL;
	gridData21.widthHint = averageCharWidth * textBoldItalic.length() * 2;
	list1.setLayoutData(gridData21);
	list1.setItems(new String[]{textRegular, textBold, textItalic, textBoldItalic});
	list1.addSelectionListener(new SelectionAdapter() {
		public void widgetSelected(SelectionEvent e) {
			text1.setText(list1.getSelection()[0]);
			updateAndRefreshSample();
		}
	});
	
	list2 = new List(group, SWT.SINGLE | SWT.V_SCROLL | SWT.BORDER);
	GridData gridData7 = new GridData();
	gridData7.widthHint = gridData6.widthHint;
	gridData7.heightHint = 	gridData52.heightHint;
	list2.setLayoutData(gridData7);
	list2.addSelectionListener(new SelectionAdapter() {
		public void widgetSelected(SelectionEvent e) {
			text2.setText(list2.getSelection()[0]);
			updateAndRefreshSample();
		}
	});
	sampleText = new Text(group, SWT.MULTI | SWT.WRAP | SWT.V_SCROLL | SWT.READ_ONLY | SWT.BORDER);
	sampleText.setText("ca fe ba be 00 00 01 2d");
	sampleText.setEditable(false);
	GridData gridData8 = new GridData();
	gridData8.horizontalSpan = 3;
	gridData8.widthHint = gridData52.widthHint + gridData21.widthHint + gridData7.widthHint +
		gridLayout.horizontalSpacing * 2;
	gridData8.heightHint = 50;
	gridData8.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
	sampleText.setLayoutData(gridData8);
	sampleText.addDisposeListener(new DisposeListener() {
		public void widgetDisposed(DisposeEvent e) {
			if (sampleFont != null && !sampleFont.isDisposed()) {
				sampleFont.dispose();
			}
		}
	});
}


private void createCompositeOkCancel() {
	GridData gridData2 = new GridData();
	gridData2.horizontalAlignment = org.eclipse.swt.layout.GridData.END;
	RowLayout rowLayout1 = new RowLayout();
	rowLayout1.type = org.eclipse.swt.SWT.HORIZONTAL;
	rowLayout1.marginHeight = 10;
	rowLayout1.marginWidth = 10;
	rowLayout1.pack = false;
	compositeOkCancel = new Composite(dialog, SWT.NONE);
	compositeOkCancel.setLayout(rowLayout1);
	compositeOkCancel.setLayoutData(gridData2);
	buttonOk = new Button(compositeOkCancel, SWT.NONE);
	buttonOk.setText("OK");
	buttonOk.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
		public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
			initialFontData = sampleFontData;
			dialogResult = SWT.OK;
			dialog.close();
		}
	});
	dialog.setDefaultButton(buttonOk);
	buttonCancel = new Button(compositeOkCancel, SWT.NONE);
	buttonCancel.setText("Cancel");
	buttonCancel.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
		public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
			sampleFontData = initialFontData;
			dialogResult = SWT.CANCEL;
			dialog.close();
		}
	});
}


private void createDialog(Shell parentShell) {
	dialog = new Shell(parentShell, SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM);
	GridLayout gridLayout1 = new GridLayout();
	gridLayout1.marginHeight = 3;
	gridLayout1.marginWidth = 3;
	dialog.setLayout(gridLayout1);
	dialog.setText("Font preferences");
	createPreferencesPart(dialog);
	buttonDefault = new Button(dialog, SWT.CENTER);
	buttonDefault.setText("Default");
	GridData gridData = new GridData();
	gridData.horizontalIndent = 3;
	buttonDefault.setLayoutData(gridData);
	buttonDefault.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
		public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
			setFontData(null);
		}
	});
	createCompositeOkCancel();
}


/**
 * Creates the part containing all preferences-editing widgets, that is, ok and cancel
 * buttons are left out so we can call this method from both standalone and plugin.
 * @param aParent composite where preferences will be drawn
 */
public Composite createPreferencesPart(Composite aParent) {
	parent = aParent;
	createComposite();
	if (fontsSorted.size() < 1) {
		populateFixedCharWidthFonts();
	} else {
		list.setItems((String[])fontsSorted.keySet().toArray(new String[0]));
		refreshWidgets();
	}
	
	return composite;
}


/**
 * Get the preferred font data
 * @return a copy of the preferred font data
 */
public FontData getFontData() {
	return new FontData(sampleFontData.getName(), sampleFontData.getHeight(),
			sampleFontData.getStyle());
}


FontData getNextFontData() {
	if (fontsListCurrent.size() == 0) {
		fontsListCurrent = fontsScalable;
	}
	FontData aData = (FontData)fontsListCurrent.get(0);
	fontsListCurrent.remove(0);
	while (fontsRejected.contains(aData.getName()) && fontsScalable.size() > 0) {
		if (fontsListCurrent.size() == 0) {
			fontsListCurrent = fontsScalable;
		}
		aData = (FontData)fontsListCurrent.get(0);
		fontsListCurrent.remove(0);
	}
	
	return aData;
}


int getSize() {
	int size = 0;
	if (!"".equals(text2.getText())) {
		try {
			size = Integer.parseInt(text2.getText());
		} catch (NumberFormatException e) {}  // was not a number, keep it 0
	}
	// bugfix: HexText's raw array overflows when font is very small and window very big
	// very small sizes would compromise responsiveness in large windows, and they are too small
	// to see anyway
	if (size == 1 || size == 2) size = 3;
	
	return size;
}


/**
 * Creates a self contained standalone dialog
 * @param aParentShell
 * @return SWT.OK or SWT.CANCEL
 */
public int openDialog(Shell aParentShell) {
	dialogResult = SWT.CANCEL;  // when user presses escape
	if (dialog == null || dialog.isDisposed())
		createDialog(aParentShell);
	dialog.pack();
	Manager.reduceDistance(aParentShell, dialog);
	dialog.open();
	Display display = parent.getDisplay();
	while (!dialog.isDisposed()) {
		if (!display.readAndDispatch())
			display.sleep();
	}
	
	return dialogResult;
}


void populateFixedCharWidthFonts() {
	fontsNonScalable = new ArrayList(Arrays.asList(Display.getCurrent().getFontList(null, false)));
	fontsScalable = new ArrayList(Arrays.asList(Display.getCurrent().getFontList(null, true)));
	if (fontsNonScalable.size() == 0 && fontsScalable.size() == 0) {
		fontsNonScalable = null;
		fontsScalable = null;
		
		return;
	}
	fontsListCurrent = fontsNonScalable;
	fontsRejected = new HashSet();
	fontsGc = new GC(parent);
	Display.getCurrent().asyncExec(new Runnable() { public void run() {
		populateFixedCharWidthFontsAsync();
	}});
}


void populateFixedCharWidthFontsAsync() {
	FontData aData = getNextFontData();
	if (!fontsRejected.contains(aData.getName())) {
		boolean isScalable = fontsListCurrent == fontsScalable;
		int height = 10;
		if (!isScalable) height = aData.getHeight();
		Font font = new Font(Display.getCurrent(), aData.getName(), height, SWT.NORMAL);
		fontsGc.setFont(font);
		int width = fontsGc.getAdvanceWidth((char)0x020);
		boolean isFixedWidth = true;
		for (int j = 0x021; j < 0x0100 && isFixedWidth; ++j) {
			if (HexTexts.byteToChar[j] == '.' && j != '.') continue;
			if (width != fontsGc.getAdvanceWidth((char)j)) isFixedWidth = false;
		}
		font.dispose();
		if (isFixedWidth) {
			if (isScalable) {
				fontsSorted.put(aData.getName(), scalableSizes);
			} else {
				TreeSet heights = (TreeSet)fontsSorted.get(aData.getName());
				if (heights == null) {
					heights = new TreeSet();
					fontsSorted.put(aData.getName(), heights);
				}
				heights.add(new Integer(aData.getHeight()));
			}
			if (!list.isDisposed())
				list.setItems((String[])fontsSorted.keySet().toArray(new String[0]));
			refreshWidgets();
		} else {
			fontsRejected.add(aData.getName());
		}
	}
	if (fontsNonScalable.size() == 0 && fontsScalable.size() == 0) {
		if (!parent.isDisposed()) fontsGc.dispose();
		fontsGc = null;
		fontsNonScalable = fontsScalable = fontsListCurrent = null;
		fontsRejected = null;
	} else {
		Display.getCurrent().asyncExec(new Runnable() { public void run() {
			populateFixedCharWidthFontsAsync();
		}});
	}
}


void refreshSample() {
	if (sampleFont != null && !sampleFont.isDisposed()) {
		sampleFont.dispose();
	}
	sampleFont = new Font(Display.getCurrent(), sampleFontData);
	sampleText.setFont(sampleFont);
}


void refreshWidgets() {
	if (composite.isDisposed())
		return;
	
	if (fontsSorted == null || !fontsSorted.containsKey(sampleFontData.getName())) {
		text.setText("default font");
	} else {
		text.setText(sampleFontData.getName());
	}
	showSelected(list, sampleFontData.getName());
	
	text1.setText(fontStyleToString(sampleFontData.getStyle()));
	list1.select(list1.indexOf(fontStyleToString(sampleFontData.getStyle())));
	
	updateSizeItems();
	text2.setText(Integer.toString(sampleFontData.getHeight()));
	showSelected(list2, Integer.toString(sampleFontData.getHeight()));

	refreshSample();
}


/**
 * Set preferences to show a font. Use null to show default font.
 * @param aFontData the font to be shown.
 */
public void setFontData(FontData aFontData) {
	if (aFontData == null)
		aFontData = HexTexts.fontDataDefault;
	sampleFontData = aFontData;
	refreshWidgets();
}


void showSelected(List aList, String item) {
	int selected = aList.indexOf(item);
	if (selected >= 0) {
		aList.setSelection(selected);
		aList.setTopIndex(Math.max(0, selected - itemsDisplayed + 1));
	} else {
		aList.deselectAll();
		aList.setTopIndex(0);
	}
}


void updateAndRefreshSample() {
	sampleFontData = new FontData(text.getText(), getSize(), fontStyleToInt(text1.getText()));
	refreshSample();
}


void updateSizeItems() {
	TreeSet sizes = (TreeSet)fontsSorted.get(text.getText());
	if (sizes == null) {
		list2.removeAll();
		return;
	}
	String[] items = new String[sizes.size()];
	int i = 0;
	for (Iterator j=sizes.iterator(); i<items.length; ++i) items[i] = ((Integer)j.next()).toString();
	list2.setItems(items);
}


void updateSizeItemsAndGuessSelected() {
	int lastSize = getSize();
	updateSizeItems();

	int position = 0;
	String[] items = list2.getItems();
	for (int i=1; i<items.length; ++i) {
		if (lastSize >= Integer.parseInt(items[i]))
			position = i;
	}
	text2.setText(items[position]);
	showSelected(list2, items[position]);
}
}
