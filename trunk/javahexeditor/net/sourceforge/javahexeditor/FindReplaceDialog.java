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
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;


/**
 * Find/Replace dialog with hex/text, forward/backward, and ignore case options. Remembers previous
 * state, in case it has been closed by the user and reopened again.
 * @author Jordi
 */
public class FindReplaceDialog extends Dialog {


static final Pattern patternHexDigits = Pattern.compile("[0-9a-fA-F]*");
static final String text1Replacement = "1 Replacement";
static final String textBackward = "&Backward";
static final String textCancel = "Cancel";
static final String textClose = "Close";
static final String textDirection = "Direction";
static final String textError = "Error: ";
static final String textFind = "Fi&nd";
static final String textFindLiteral = "Find literal";
static final String textFindReplace = "Find/Replace";
static final String textForward = "F&orward";
static final String textFoundLiteral = "Found literal";
static final String textHex = "Hex";
static final String textIgnoreCase = "&Ignore case";
static final String textLiteralNotFound = "Literal not found";
static final String textNewFind = "New find";
static final String textReplace = "&Replace";
static final String textReplaceAll = "Replace &All";
static final String textReplaceFind = "Replace/Fin&d";
static final String textReplaceWith = "Replace With";
static final String textReplacements = " Replacements";
static final String textSearching = "Searching";
static final String textStop = "Stop";
static final String textText = "Text";

SelectionAdapter defaultSelectionAdapter = new SelectionAdapter() {
	public void widgetSelected(SelectionEvent e) {
		if (lastIgnoreCase != checkBox.getSelection() ||
			lastForward != forwardRadioButton.getSelection() ||
			lastFindHexButtonSelected != findGroup.hexRadioButton.getSelection() ||
			lastReplaceHexButtonSelected != replaceGroup.hexRadioButton.getSelection()) {
			feedbackLabel.setText("");
		}
		lastFocused.textCombo.setFocus();
	}
};
private List findReplaceFindList = null;
private List findReplaceReplaceList = null;
private HexTexts myTarget = null;
private TextHexInputGroup lastFocused = null;
private boolean lastForward = true;
private boolean lastFindHexButtonSelected = true;
private boolean lastReplaceHexButtonSelected = true;
private boolean lastIgnoreCase = false;
private boolean searching = false;

// visual components
private Shell         sShell = null;
private TextHexInputGroup findGroup = null;
private TextHexInputGroup replaceGroup = null;
private Group             directionGroup = null;
private Button                forwardRadioButton = null;
private Button                backwardRadioButton = null;
private Composite         ignoreCaseComposite = null;
private Button                checkBox = null;
private Composite         findReplaceButtonsComposite = null;
private Button                findButton = null;
private Button                replaceFindButton = null;
private Button                replaceButton = null;
private Button                replaceAllButton = null;
private Composite         feedbackComposite = null;
private Label                 feedbackLabel = null;
private Composite             progressComposite = null;
private ProgressBar               progressBar = null;
private Button                    progressCancelButton = null;
private Button            closeButton = null;


/**
 * Group with text/hex selector and text input
 */
class TextHexInputGroup {
	private List items = null;  // list of tuples {String, Boolean}
	// visual components
	private Group group = null;
	private Composite composite = null;
	private Button        hexRadioButton = null;
	private Button        textRadioButton = null;
	private Combo     textCombo = null;
	
	public TextHexInputGroup(List oldItems) {
		items = oldItems;
	}
	
	private void initialise() {
		group = new Group(sShell, SWT.NONE);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		group.setLayout(gridLayout);
		createComposite();
		textCombo = new Combo(group, SWT.BORDER);
		int columns = 35;
		GC gc = new GC(textCombo);
		FontMetrics fm = gc.getFontMetrics();
		int width = columns * fm.getAverageCharWidth();
		gc.dispose();
		textCombo.setLayoutData(new GridData(width, SWT.DEFAULT));
		textCombo.addVerifyListener(new VerifyListener() {
			public void verifyText(org.eclipse.swt.events.VerifyEvent e) {
				if (e.keyCode == 0) return;  // a list selection
				if (hexRadioButton.getSelection()) {
					Matcher numberMatcher = patternHexDigits.matcher(e.text);
					e.doit = numberMatcher.matches();
				}
			}
		});
		textCombo.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				int index = textCombo.getSelectionIndex();
				if (index < 0) return;
				
 				Boolean selection = (Boolean)(items == null ? null : ((Object[])items.get(index))[1]);
				if (selection != null) {
					refreshHexOrText(selection.booleanValue());
				}
			}
		});
		textCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				feedbackLabel.setText("");
				if (TextHexInputGroup.this == findGroup) {
					enableDisableControls();
				}
			}
		});
	}

	/**
	 * This method initializes composite
	 */
	private void createComposite() {
		RowLayout rowLayout1 = new RowLayout();
		rowLayout1.marginTop = 2;
		rowLayout1.marginBottom = 2;
		rowLayout1.type = SWT.VERTICAL;
		composite = new Composite(group, SWT.NONE);
		composite.setLayout(rowLayout1);
		hexRadioButton = new Button(composite, SWT.RADIO);
		hexRadioButton.setText(textHex);
		hexRadioButton.addSelectionListener(defaultSelectionAdapter);
		hexRadioButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				Matcher numberMatcher = patternHexDigits.matcher(textCombo.getText());
				if (!numberMatcher.matches())
					textCombo.setText("");
			}
		});
		textRadioButton = new Button(composite, SWT.RADIO);
		textRadioButton.setText(textText);
		textRadioButton.addSelectionListener(defaultSelectionAdapter);
	}


	private void refreshCombo() {
		if (items == null) return;
		
		if (textCombo.getItemCount() > 0) {
			textCombo.remove(0, textCombo.getItemCount() - 1);
		}
		for (Iterator iterator = items.iterator(); iterator.hasNext();) {
			String itemString = (String)((Object[])iterator.next())[0];
			textCombo.add(itemString);
		}
		if (!items.isEmpty()) {
			textCombo.setText((String)((Object[])items.get(0))[0]);
		}
		selectText();
	}


	private void refreshHexOrText(boolean hex) {
		hexRadioButton.setSelection(hex);
		textRadioButton.setSelection(!hex);
	}
	
	
	private void rememberText() {
		String lastText = textCombo.getText();
		if ("".equals(lastText) || items == null) return;
		
		for (Iterator iterator = items.iterator(); iterator.hasNext();) {
			String itemString = (String)((Object[])iterator.next())[0];
			if (lastText.equals(itemString)) {
				iterator.remove();
			}
		}
		items.add(0, new Object[]{lastText, new Boolean(hexRadioButton.getSelection())});
		refreshCombo();
	}
	
	
	private void selectText() {
		textCombo.setSelection(new Point(0, textCombo.getText().length()));
	}
	
	
	private void setEnabled(boolean enabled) {
		group.setEnabled(enabled);
		hexRadioButton.setEnabled(enabled);
		textRadioButton.setEnabled(enabled);
		textCombo.setEnabled(enabled);
	}
}


/**
 * Create find/replace dialog always on top of shell
 * @param aShell where it is displayed
 */
public FindReplaceDialog(Shell aShell) {
	super(aShell);
}


private void activateProgressBar() {
	Display.getCurrent().timerExec(500, new Runnable() {
		public void run() {
			if (searching && !progressComposite.isDisposed()) {
				progressComposite.setVisible(true);
			}
		}
	});
	long max = myTarget.myContent.length();
	long min = myTarget.getCaretPos();
	if (backwardRadioButton.getSelection()) {
		max = min;
		min = 0L;
	}
	int factor = 0;
	while (max > Integer.MAX_VALUE) {
		max = max >>> 1;
		min = min >>> 1;
		++factor;
	}
	progressBar.setMaximum((int)max);
	progressBar.setMinimum((int)min);
	progressBar.setSelection(0);
	final int finalFactor = factor;
	Display.getCurrent().timerExec(1000, new Runnable() { public void run() {
		if (!searching || progressBar.isDisposed()) return;
		
		int selection = 0;
		if (myTarget.myFinder != null) {
			selection = (int)(myTarget.myFinder.getSearchPosition() >>> finalFactor);
			if (backwardRadioButton.getSelection()) {
				selection = progressBar.getMaximum() - selection;
			}
		}
		progressBar.setSelection(selection);
		Display.getCurrent().timerExec(1000, this);
	}});
}


/**
 * Open and display the dialog.
 */
public void open() {
	if (sShell == null || sShell.isDisposed()) {
		createSShell();
	}
	sShell.pack();
	Manager.reduceDistance(getParent(), sShell);
	findGroup.refreshCombo();
	long selectionLength = myTarget.getSelection()[1] - myTarget.getSelection()[0];
	if (selectionLength > 0L && selectionLength <= Finder.maxSequenceSize) {
		findGroup.refreshHexOrText(true);
		checkBox.setEnabled(false);
		StringBuffer selectedText = new StringBuffer();
		byte[] selection = new byte[(int)selectionLength];
		try {
			myTarget.myContent.get(ByteBuffer.wrap(selection), myTarget.getSelection()[0]);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		for (int i = 0; i < selectionLength; ++i) {
			selectedText.append(HexTexts.byteToHex[selection[i] & 0x0ff]);
		}
		findGroup.textCombo.setText(selectedText.toString());
		findGroup.selectText();
	} else {
		findGroup.refreshHexOrText(lastFindHexButtonSelected);
		checkBox.setEnabled(!lastFindHexButtonSelected);
	}

	replaceGroup.refreshHexOrText(lastReplaceHexButtonSelected);
	replaceGroup.refreshCombo();

	checkBox.setSelection(lastIgnoreCase);
	if (lastForward)
		forwardRadioButton.setSelection(true);
	else
		backwardRadioButton.setSelection(true);
	feedbackLabel.setText(textNewFind);

	lastFocused = findGroup;
	lastFocused.textCombo.setFocus();
	enableDisableControls();
	sShell.open();
}


/**
 * This method initializes find/replace buttons composite
 */
private void createFindReplaceButtonsComposite() {
	GridLayout gridLayout = new GridLayout();
	gridLayout.marginHeight = 5;
	gridLayout.marginWidth = 0;
	gridLayout.numColumns = 2;
	gridLayout.makeColumnsEqualWidth = true;
	findReplaceButtonsComposite = new Composite(sShell, SWT.NONE);
	FormData formData = new FormData();
	formData.top = new FormAttachment(directionGroup);
	formData.left = new FormAttachment(0);
	formData.right = new FormAttachment(100);
	findReplaceButtonsComposite.setLayoutData(formData);
	findReplaceButtonsComposite.setLayout(gridLayout);
	
	findButton = new Button(findReplaceButtonsComposite, SWT.NONE);
	findButton.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
	findButton.setText(textFind);
	findButton.addSelectionListener(defaultSelectionAdapter);
	findButton.addSelectionListener(new SelectionAdapter() {
		public void widgetSelected(SelectionEvent e) {
			doFind();
		}
	});
	replaceFindButton = new Button(findReplaceButtonsComposite, SWT.NONE);
	replaceFindButton.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
	replaceFindButton.setText(textReplaceFind);
	replaceFindButton.addSelectionListener(defaultSelectionAdapter);
	replaceFindButton.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
		public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
			replace();
			doFind();
		}
	});
	replaceButton = new Button(findReplaceButtonsComposite, SWT.NONE);
	replaceButton.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
	replaceButton.setText(textReplace);
	replaceButton.addSelectionListener(defaultSelectionAdapter);
	replaceButton.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
		public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
			replace();
			enableDisableControls();
			feedbackLabel.setText("");
		}
	});
	replaceAllButton = new Button(findReplaceButtonsComposite, SWT.NONE);
	replaceAllButton.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
	replaceAllButton.setText(textReplaceAll);
	replaceAllButton.addSelectionListener(defaultSelectionAdapter);
	replaceAllButton.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
		public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
			doReplaceAll();
		}
	});
	sShell.setDefaultButton(findButton);
}


/**
 * This method initializes composite3	
 */
private void createIgnoreCaseComposite() {
	FillLayout fillLayout = new FillLayout();
	fillLayout.marginHeight = 10;
	fillLayout.marginWidth = 10;
	ignoreCaseComposite = new Composite(sShell, SWT.NONE);
	ignoreCaseComposite.setLayout(fillLayout);
	checkBox = new Button(ignoreCaseComposite, SWT.CHECK);
	checkBox.setText(textIgnoreCase);
	checkBox.addSelectionListener(defaultSelectionAdapter);
	FormData formData = new FormData();
	formData.top = new FormAttachment(replaceGroup.group);
	formData.left = new FormAttachment(directionGroup);
	ignoreCaseComposite.setLayoutData(formData);
}


/**
 * This method initializes group1	
 */
private void createDirectionGroup() {
	RowLayout rowLayout = new RowLayout();
	rowLayout.fill = true;
	rowLayout.type = org.eclipse.swt.SWT.VERTICAL;
	directionGroup = new Group(sShell, SWT.NONE);
	directionGroup.setText(textDirection);
	FormData formData = new FormData();
	formData.top = new FormAttachment(replaceGroup.group);
	directionGroup.setLayoutData(formData);
	directionGroup.setLayout(rowLayout);
	forwardRadioButton = new Button(directionGroup, SWT.RADIO);
	forwardRadioButton.setText(textForward);
	forwardRadioButton.addSelectionListener(defaultSelectionAdapter);
	backwardRadioButton = new Button(directionGroup, SWT.RADIO);
	backwardRadioButton.setText(textBackward);
	backwardRadioButton.addSelectionListener(defaultSelectionAdapter);
}


/**
 * This method initializes sShell	
 */
private void createSShell() {
	sShell = new Shell(getParent(), SWT.MODELESS | SWT.DIALOG_TRIM);
	sShell.setText(textFindReplace);
	FormLayout formLayout = new FormLayout();
	formLayout.marginHeight = 5;
	formLayout.marginWidth = 5;
	formLayout.spacing = 5;
	sShell.setLayout(formLayout);
	sShell.addShellListener(new ShellAdapter() {
		public void shellActivated(ShellEvent e) {
			enableDisableControls();
		}
	});
	
	if (findGroup == null) {
		findGroup = new TextHexInputGroup(findReplaceFindList);
	}
	findGroup.initialise();
	findGroup.group.setText(textFindLiteral);
	SelectionAdapter hexTextSelectionAdapter = new SelectionAdapter() {
		public void widgetSelected(SelectionEvent e) {
			checkBox.setEnabled(e.widget == findGroup.textRadioButton);
		}
	};
	findGroup.textRadioButton.addSelectionListener(hexTextSelectionAdapter);
	findGroup.hexRadioButton.addSelectionListener(hexTextSelectionAdapter);
	
	if (replaceGroup == null) {
		replaceGroup = new TextHexInputGroup(findReplaceReplaceList);
	}
	replaceGroup.initialise();
	replaceGroup.group.setText(textReplaceWith);
	FormData formData = new FormData();
	formData.top = new FormAttachment(findGroup.group);
	replaceGroup.group.setLayoutData(formData);
	
	createDirectionGroup();
	createIgnoreCaseComposite();
	createFindReplaceButtonsComposite();

	feedbackComposite = new Composite(sShell, SWT.NONE);
	FormData formData2 = new FormData();
	formData2.top = new FormAttachment(findReplaceButtonsComposite);
	formData2.left = new FormAttachment(0);
	formData2.bottom = new FormAttachment(100);
	feedbackComposite.setLayoutData(formData2);
	FormLayout formLayout2 = new FormLayout();
//	formLayout2.spacing = 5;
	feedbackComposite.setLayout(formLayout2);

	feedbackLabel = new Label(feedbackComposite, SWT.CENTER);
	feedbackLabel.setText(textNewFind);
	FormData formData3 = new FormData();
	formData3.top = new FormAttachment(0);
	formData3.left = new FormAttachment(0);
	formData3.right = new FormAttachment(100);
	feedbackLabel.setLayoutData(formData3);

	progressComposite = new Composite(feedbackComposite, SWT.NONE);
	FormLayout formLayout3 = new FormLayout();
	formLayout3.spacing = 5;
	progressComposite.setLayout(formLayout3);
	FormData formData4 = new FormData();
	formData4.top = new FormAttachment(feedbackLabel);
	formData4.bottom = new FormAttachment(100);
	formData4.left = new FormAttachment(0);
	formData4.right = new FormAttachment(100);
	progressComposite.setLayoutData(formData4);
//progressComposite.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_CYAN));

	progressBar = new ProgressBar(progressComposite, SWT.NONE);
	FormData formData5 = new FormData();
	formData5.bottom = new FormAttachment(100);
	formData5.left = new FormAttachment(0);
	formData5.height = progressBar.computeSize(SWT.DEFAULT, SWT.DEFAULT, false).y;
	progressBar.setLayoutData(formData5);
	
	progressCancelButton = new Button(progressComposite, SWT.NONE);
	progressCancelButton.setText(textCancel);
	FormData formData6 = new FormData();
	formData6.right = new FormAttachment(100);
	progressCancelButton.setLayoutData(formData6);
	formData5.right = new FormAttachment(progressCancelButton);
	progressCancelButton.addSelectionListener(new SelectionAdapter() {
		public void widgetSelected(SelectionEvent e) {
			myTarget.stopSearching();
		}
	});
	progressComposite.setVisible(false);

	closeButton = new Button(sShell, SWT.NONE);
	closeButton.setText(textClose);
	FormData formData1 = new FormData();
	formData1.right = new FormAttachment(100);
	formData1.bottom = new FormAttachment(100);
	closeButton.setLayoutData(formData1);
	closeButton.addSelectionListener(new SelectionAdapter() {
		public void widgetSelected(SelectionEvent e) {
			sShell.close();
		}
	});

	formData2.right = new FormAttachment(closeButton);
	
	sShell.addListener(SWT.Close, new Listener () {
		public void handleEvent (Event event) {
			myTarget.stopSearching();
		}
	});
}


private void doFind() {
	prepareToRun();
	progressCancelButton.setText(textCancel);
	String message = textLiteralNotFound;
	String literal = findGroup.textCombo.getText();
	if (myTarget != null && literal.length() > 0) {
		try {
			if (myTarget.findAndSelect(literal, findGroup.hexRadioButton.getSelection(),
									forwardRadioButton.getSelection(), checkBox.getSelection()))
				message = textFoundLiteral;
		} catch (IOException e) {
			message = textError + e;
		}
	}
	endOfRun(message);
}


private void doReplaceAll() {
	prepareToRun();
	progressCancelButton.setText(textStop);
	String message = textLiteralNotFound;
	String literal = findGroup.textCombo.getText();
	if (myTarget != null && literal.length() > 0) {
		try {
			int replacements = myTarget.replaceAll(literal, findGroup.hexRadioButton.getSelection(),
					forwardRadioButton.getSelection(), checkBox.getSelection(),
					replaceGroup.textCombo.getText(), replaceGroup.hexRadioButton.getSelection());
			message = replacements + textReplacements;
			if (replacements == 1) {
				message = text1Replacement;
			}
		} catch (IOException e) {
			message = textError + e;
		}
	}
	endOfRun(message);
}


private void enableDisableControls() {
	findGroup.setEnabled(!searching);
	replaceGroup.setEnabled(!searching);

	directionGroup.setEnabled(!searching);
	forwardRadioButton.setEnabled(!searching);
	backwardRadioButton.setEnabled(!searching);

	checkBox.setEnabled(!searching);

	findButton.setEnabled(!searching);
	replaceFindButton.setEnabled(!searching);
	replaceButton.setEnabled(!searching);
	replaceAllButton.setEnabled(!searching);
	
	closeButton.setEnabled(!searching);
//		getParent().setEnabled(enableButtons);
	if (searching) {
		return;
	}
	
	boolean somethingToFind = findGroup.textCombo.getText().length() > 0;
	findButton.setEnabled(somethingToFind);
	replaceAllButton.setEnabled(somethingToFind);
	long selectionLength = 0L;
	if (myTarget != null) {
		selectionLength = myTarget.getSelection()[1] - myTarget.getSelection()[0];
	}
	replaceFindButton.setEnabled(selectionLength > 0L && somethingToFind);
	replaceButton.setEnabled(selectionLength > 0L);
}


private void endOfRun(String message) {
	searching = false;
	if (progressComposite.isDisposed()) return;
	
	progressComposite.setVisible(false);
	feedbackLabel.setText(message);
	enableDisableControls();
}


private void prepareToRun() {
	searching = true;
	lastFindHexButtonSelected = findGroup.hexRadioButton.getSelection();
	lastReplaceHexButtonSelected = replaceGroup.hexRadioButton.getSelection();
	replaceGroup.rememberText();
	findGroup.rememberText();
	lastForward = forwardRadioButton.getSelection();
	lastIgnoreCase = checkBox.getSelection();
	feedbackLabel.setText(textSearching);
	enableDisableControls();
	activateProgressBar();
}


private void replace() {
	myTarget.replace(replaceGroup.textCombo.getText(), replaceGroup.hexRadioButton.getSelection());
}


/**
 * Set Find/Replace combo lists pre-exisitng values
 * @param findList previous find values
 * @param replaceList previous replace values
 */
public void setFindReplaceLists(List findList, List replaceList) {
	findReplaceFindList = findList;
	findReplaceReplaceList = replaceList;
}


/**
 * Set the target editor to search
 * @param aTarget with data to search
 */
public void setTarget(HexTexts aTarget) {
	myTarget = aTarget;
}
}
