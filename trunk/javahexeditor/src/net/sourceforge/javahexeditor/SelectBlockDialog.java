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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * Go to dialog. Remembers previous state.
 * @author anb0s
 */
public class SelectBlockDialog extends Dialog {

class myModifyListener implements ModifyListener {
	long result;
	boolean empty;
	public myModifyListener() {
		result = -1L;
		empty = true;
	}
	public void modifyText(ModifyEvent e) {
		String newText = ((Text)e.widget).getText();
		int radix = 10;
		Matcher numberMatcher = null;
		if (hexRadioButton.getSelection()) {
			numberMatcher = patternHexDigits.matcher(newText);
			radix = 16;
		} else {
			numberMatcher = patternDecDigits.matcher(newText);
		}
		result = -1;
		if (numberMatcher.matches())
			result = Long.parseLong(newText, radix);
		empty = false;
		if ("".equals(newText))
			empty = true;
		validateResults();
	}
	public long getResult() {
		return result;
	}
	public boolean isEmpty() {
		return empty;
	}	
}


static final Pattern patternDecDigits = Pattern.compile("[0-9]+");
static final Pattern patternHexDigits = Pattern.compile("[0-9a-fA-F]+");
private Shell sShell = null;
private Composite composite = null;
private Composite composite2 = null;
private Button hexRadioButton = null;
private Button decRadioButton = null;
private Button button = null;
private Button button1 = null;
private Composite composite1 = null;
private Text startText = null;
private myModifyListener startTextListener = null;
private Text endText = null;
private myModifyListener endTextListener = null;
private Label label = null;
private Label label2 = null;
SelectionAdapter defaultSelectionAdapter = new SelectionAdapter() {
	public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
		startText.setFocus();
	}
};
long finalStartResult = -1L;
long finalEndResult = -1L;
boolean lastHexButtonSelected = true;
String lastStartText = null;
String lastEndText = null;
long limit = -1L;


public SelectBlockDialog(Shell aShell) {
	super(aShell);
	lastStartText = "";
	lastEndText = "";
}


/**
 * This method initializes composite
 */
private void createComposite() {
	RowLayout rowLayout1 = new RowLayout();
//	rowLayout1.marginHeight = 5;
	rowLayout1.marginTop = 2;
	rowLayout1.marginBottom = 2;
	//rowLayout1.marginWidth = 5;
	rowLayout1.type = SWT.VERTICAL;
	composite = new Composite(composite1, SWT.NONE);
	composite.setLayout(rowLayout1);
	SelectionAdapter hexTextSelectionAdapter = new SelectionAdapter() {
		public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
			startText.setText(startText.getText());  // generate event
			endText.setText(endText.getText());  // generate event
			lastHexButtonSelected = e.widget == hexRadioButton;
		}
	};
	hexRadioButton = new Button(composite, SWT.RADIO);
	hexRadioButton.setText("Hex");
	hexRadioButton.addSelectionListener(defaultSelectionAdapter);
	hexRadioButton.addSelectionListener(hexTextSelectionAdapter);
	decRadioButton = new Button(composite, SWT.RADIO);
	decRadioButton.setText("Dec");
	decRadioButton.addSelectionListener(defaultSelectionAdapter);
	decRadioButton.addSelectionListener(hexTextSelectionAdapter);
}


/**
 * This method initializes composite2	
 *
 */
private void createComposite2() {
	RowLayout rowLayout1 = new RowLayout();
	rowLayout1.type = org.eclipse.swt.SWT.VERTICAL;
	rowLayout1.marginHeight = 10;
	rowLayout1.marginWidth = 10;
	rowLayout1.fill = true;
	composite2 = new Composite(sShell, SWT.NONE);
	FormData formData = new FormData();
	formData.left = new FormAttachment(composite1);
	formData.right = new FormAttachment(100);
	composite2.setLayoutData(formData);
	composite2.setLayout(rowLayout1);
	button = new Button(composite2, SWT.NONE);
	button.setText("Select");
	button.addSelectionListener(defaultSelectionAdapter);
	button.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
		public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
			lastStartText = startText.getText();
			finalStartResult = startTextListener.getResult();
			lastEndText = endText.getText();
			finalEndResult = endTextListener.getResult();
			sShell.close();
		}
	});
	sShell.setDefaultButton(button);
	button1 = new Button(composite2, SWT.NONE);
	button1.setText("Close");
	button1.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
		public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
			sShell.close();
		}
	});
}


/**
 * This method initializes composite1
 */
private void createComposite1() {
	GridLayout gridLayout = new GridLayout();
	gridLayout.numColumns = 3;
	composite1 = new Composite(sShell, SWT.NONE);
	composite1.setLayout(gridLayout);
	createComposite();
	startText = new Text(composite1, SWT.BORDER | SWT.SINGLE);
	startText.setTextLimit(30);
	int columns = 35;
	GC gc = new GC(startText);
	FontMetrics fm = gc.getFontMetrics();
	int width = columns * fm.getAverageCharWidth();
	gc.dispose();
	startText.setLayoutData(new GridData(width, SWT.DEFAULT));
	startTextListener = new myModifyListener();
	startText.addModifyListener(startTextListener);
	endText = new Text(composite1, SWT.BORDER | SWT.SINGLE);
	endText.setTextLimit(30);
	gc = new GC(endText);
	fm = gc.getFontMetrics();
	width = columns * fm.getAverageCharWidth();
	gc.dispose();
	endText.setLayoutData(new GridData(width, SWT.DEFAULT));	
	endTextListener = new myModifyListener();
	endText.addModifyListener(endTextListener);
	FormData formData = new FormData();
	formData.top = new FormAttachment(label);
	composite1.setLayoutData(formData);
}


/**
 * This method initializes sShell	
 *
 */
private void createSShell() {
//	sShell = new Shell(/*XXX getParent(),*/ SWT.MODELESS | SWT.DIALOG_TRIM);
	sShell = new Shell(getParent(), SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM);
	sShell.setText("Select block");
	FormLayout formLayout = new FormLayout();
	formLayout.marginHeight = 4;
	formLayout.marginWidth = 3;
	sShell.setLayout(formLayout);
	label = new Label(sShell, SWT.NONE);
	FormData formData = new FormData();
	formData.left = new FormAttachment(0, 5);
	formData.right = new FormAttachment(100);
	label.setLayoutData(formData);
	createComposite1();
	createComposite2();
	label2 = new Label(sShell, SWT.CENTER);
	FormData formData2 = new FormData();
	formData2.left = new FormAttachment(0);
	formData2.right = new FormAttachment(100);
	formData2.top = new FormAttachment(composite1);
	formData2.bottom = new FormAttachment(100, -10);
	label2.setLayoutData(formData2);
}


public long open(long aLimit) {
	limit = aLimit;
	finalStartResult = -1L;
	finalEndResult = -1L;
	if (sShell == null || sShell.isDisposed())
		createSShell();
	sShell.pack();
	Manager.reduceDistance(getParent(), sShell);
	if (lastHexButtonSelected) {
		hexRadioButton.setSelection(true);
	} else {
		decRadioButton.setSelection(true);
	}
	label.setText(
			"Enter start and end number, 0 to " + limit + " (0x0 to 0x" + Long.toHexString(limit) + ")");
	startText.setText(lastStartText);
	endText.setText(lastEndText);
	startText.selectAll();
	startText.setFocus();
	sShell.open();
	Display display = getParent().getDisplay();
	while (!sShell.isDisposed()) {
		if (!display.readAndDispatch())
			display.sleep();
	}
	
	return finalStartResult;
}

public void validateResults() {
	long result1 = startTextListener.getResult();
	long result2 = endTextListener.getResult();
	if ((result1 >= 0L) && (result1 <= limit) && (result2 >= 0L) && (result2 <= limit) && (result2 > result1)) {
		button.setEnabled(true);
		label2.setText("");
	} else {
		button.setEnabled(false);
		if (startTextListener.isEmpty() || endTextListener.isEmpty())
			label2.setText("");
		else if ((result1 < 0) || (result2 < 0))
			label2.setText("Not a number");
		else if (result2 <= result1)
			label2.setText("End smaller than or equal to start");
		else
			label2.setText("Location out of range");
	}
}

public long getFinalStartResult() {
	return finalStartResult;
}

public long getFinalEndResult() {
	return finalEndResult;
}

}
