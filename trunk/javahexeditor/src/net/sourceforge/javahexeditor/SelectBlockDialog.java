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

import net.sourceforge.javahexeditor.BinaryContent.RangeSelection;

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
 * Select block dialog. Remembers previous state.
 * 
 * @author anb0s
 */
final class SelectBlockDialog extends Dialog {

    private final class MyModifyListener implements ModifyListener {
	long result;
	boolean empty;

	public MyModifyListener() {
	    result = -1L;
	    empty = true;
	}

	@Override
	public void modifyText(ModifyEvent e) {
	    String newText = ((Text) e.widget).getText();
	    int radix = 10;
	    Matcher numberMatcher = null;
	    if (hexRadioButton.getSelection()) {
		numberMatcher = patternHexDigits.matcher(newText);
		radix = 16;
	    } else {
		numberMatcher = patternDecDigits.matcher(newText);
	    }
	    result = -1;
	    if (numberMatcher.matches()) {
		result = Long.parseLong(newText, radix);
	    }
	    empty = newText.isEmpty();
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

    Shell shell;
    private Composite compositeRadio;
    private Composite compositeRadioAndText;
    private Composite compositeButtons;
    Button hexRadioButton;
    private Button decRadioButton;
    private Button button;
    private Button button1;
    Text startText;
    MyModifyListener startTextListener;
    Text endText;
    MyModifyListener endTextListener;
    private Label label;
    private Label statusLabel;

    SelectionAdapter defaultSelectionAdapter = new SelectionAdapter() {
	@Override
	public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
	    startText.setFocus();
	}
    };

    long finalStartResult = -1L;
    long finalEndResult = -1L;
    boolean lastHexButtonSelected = true;
    String lastStartText;
    String lastEndText;
    private long limit = -1L;

    public SelectBlockDialog(Shell aShell) {
	super(aShell);
	lastStartText = Texts.EMPTY;
	lastEndText = Texts.EMPTY;
    }

    /**
     * This method initializes composite
     */
    private void createComposite() {
	RowLayout rowLayout1 = new RowLayout();
	// rowLayout1.marginHeight = 5;
	rowLayout1.marginTop = 2;
	rowLayout1.marginBottom = 2;
	// rowLayout1.marginWidth = 5;
	rowLayout1.type = SWT.VERTICAL;
	compositeRadio = new Composite(compositeRadioAndText, SWT.NONE);
	compositeRadio.setLayout(rowLayout1);

	SelectionAdapter hexTextSelectionAdapter = new SelectionAdapter() {
	    @Override
	    public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
		startText.setText(startText.getText()); // generate event
		endText.setText(endText.getText()); // generate event
		lastHexButtonSelected = e.widget == hexRadioButton;
		/*
		 * Crashes when the text is not a number if
		 * (lastHexButtonSelected) return; String startTextNew =
		 * startText.getText(); String endTextNew = endText.getText();
		 * startTextNew =
		 * Integer.toHexString(Integer.parseInt(startTextNew
		 * )).toUpperCase(); endTextNew =
		 * Integer.toHexString(Integer.parseInt
		 * (endTextNew)).toUpperCase(); startText.setText(startTextNew);
		 * // generate event endText.setText(endTextNew); // generate
		 * event lastHexButtonSelected = true;
		 */
	    }
	};
	/*
	 * Crashes when the text is not radix 16 SelectionAdapter
	 * decTextSelectionAdapter = new SelectionAdapter() { public void
	 * widgetSelected(org.eclipse.swt.events.SelectionEvent e) { if
	 * (!lastHexButtonSelected) return; String startTextNew =
	 * startText.getText(); String endTextNew = endText.getText();
	 * startTextNew = Integer.toString(Integer.parseInt(startTextNew, 16));
	 * endTextNew = Integer.toString(Integer.parseInt(endTextNew, 16));
	 * startText.setText(startTextNew); // generate event
	 * endText.setText(endTextNew); // generate event lastHexButtonSelected
	 * = false; } };
	 */
	// Besides the crashes: the user always knows which number is entering,
	// don't need any automatic
	// conversion. What does sometimes happen is one enters the right number
	// and the wrong hex or dec was
	// selected. In that case automatic conversion is the wrong thing to do
	// and very annoying.
	hexRadioButton = new Button(compositeRadio, SWT.RADIO);
	hexRadioButton.setText("Hex");
	hexRadioButton.addSelectionListener(defaultSelectionAdapter);
	hexRadioButton.addSelectionListener(hexTextSelectionAdapter);

	decRadioButton = new Button(compositeRadio, SWT.RADIO);
	decRadioButton.setText("Dec");
	decRadioButton.addSelectionListener(defaultSelectionAdapter);
	// decRadioButton.addSelectionListener(decTextSelectionAdapter);
	hexRadioButton.addSelectionListener(hexTextSelectionAdapter);
    }

    /**
     * This method initializes composite1
     */
    private void createComposite1() {
	GridLayout gridLayout = new GridLayout();
	gridLayout.numColumns = 3;

	compositeRadioAndText = new Composite(shell, SWT.NONE);
	compositeRadioAndText.setLayout(gridLayout);

	createComposite();

	startText = new Text(compositeRadioAndText, SWT.BORDER | SWT.SINGLE);
	startText.setTextLimit(30);
	int columns = 35;
	GC gc = new GC(startText);
	FontMetrics fm = gc.getFontMetrics();
	int width = columns * fm.getAverageCharWidth();
	gc.dispose();
	startText.setLayoutData(new GridData(width, SWT.DEFAULT));
	startTextListener = new MyModifyListener();
	startText.addModifyListener(startTextListener);

	endText = new Text(compositeRadioAndText, SWT.BORDER | SWT.SINGLE);
	endText.setTextLimit(30);
	gc = new GC(endText);
	fm = gc.getFontMetrics();
	width = columns * fm.getAverageCharWidth();
	gc.dispose();
	endText.setLayoutData(new GridData(width, SWT.DEFAULT));
	endTextListener = new MyModifyListener();
	endText.addModifyListener(endTextListener);

	FormData formData = new FormData();
	formData.top = new FormAttachment(label);
	compositeRadioAndText.setLayoutData(formData);
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
	compositeButtons = new Composite(shell, SWT.NONE);
	FormData formData = new FormData();
	formData.left = new FormAttachment(compositeRadioAndText);
	formData.right = new FormAttachment(100);
	compositeButtons.setLayoutData(formData);
	compositeButtons.setLayout(rowLayout1);
	button = new Button(compositeButtons, SWT.NONE);
	button.setText("Select");
	button.addSelectionListener(defaultSelectionAdapter);
	button.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
	    @Override
	    public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
		lastStartText = startText.getText();
		finalStartResult = startTextListener.getResult();
		lastEndText = endText.getText();
		finalEndResult = endTextListener.getResult();
		shell.close();
	    }
	});
	shell.setDefaultButton(button);
	button1 = new Button(compositeButtons, SWT.NONE);
	button1.setText(Texts.BUTTON_CLOSE_LABEL);
	button1.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
	    @Override
	    public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
		shell.close();
	    }
	});
    }

    /**
     * This method initializes sShell
     * 
     */
    private void createSShell() {
	shell = new Shell(getParent(), SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM);
	shell.setText("Select block");
	FormLayout formLayout = new FormLayout();
	formLayout.marginHeight = 4;
	formLayout.marginWidth = 3;
	shell.setLayout(formLayout);
	label = new Label(shell, SWT.NONE);
	FormData formData = new FormData();
	formData.left = new FormAttachment(0, 5);
	formData.right = new FormAttachment(100);
	label.setLayoutData(formData);
	createComposite1();
	createComposite2();
	statusLabel = new Label(shell, SWT.CENTER);
	FormData formData2 = new FormData();
	formData2.left = new FormAttachment(0);
	formData2.right = new FormAttachment(100);
	formData2.top = new FormAttachment(compositeRadioAndText);
	formData2.bottom = new FormAttachment(100, -10);
	statusLabel.setLayoutData(formData2);
    }

    public long open(RangeSelection rangeSelection, long aLimit) {
	if (rangeSelection == null) {
	    throw new IllegalArgumentException(
		    "Parameter 'rangeSelection' must not be null.");
	}
	limit = aLimit;
	finalStartResult = -1L;
	finalEndResult = -1L;
	if (shell == null || shell.isDisposed()) {
	    createSShell();
	}
	SWTUtility.reduceDistance(getParent(), shell);

	if (lastHexButtonSelected) {
	    hexRadioButton.setSelection(true);
	} else {
	    decRadioButton.setSelection(true);
	}
	label.setText("Enter start and end number, 0 to " + limit
		+ " (0x0 to 0x" + Long.toHexString(limit).toUpperCase() + ")");
	if (rangeSelection.getLength() > 0) {
	    if (lastHexButtonSelected) {
		lastStartText = NumberUtility
			.getHexString(rangeSelection.start);
		lastEndText = NumberUtility.getHexString(rangeSelection.end);
	    } else {
		lastStartText = NumberUtility
			.getDecimalString(rangeSelection.start);
		lastEndText = NumberUtility
			.getDecimalString(rangeSelection.end);
	    }
	}
	startText.setText(lastStartText);
	endText.setText(lastEndText);
	startText.selectAll();
	startText.setFocus();
	shell.open();
	Display display = getParent().getDisplay();
	while (!shell.isDisposed()) {
	    if (!display.readAndDispatch())
		display.sleep();
	}

	return finalStartResult;
    }

    public void validateResults() {
	long result1 = startTextListener.getResult();
	long result2 = endTextListener.getResult();
	if ((result1 >= 0L) && (result1 <= limit) && (result2 >= 0L)
		&& (result2 <= limit) && (result2 > result1)) {
	    button.setEnabled(true);
	    statusLabel.setText(Texts.EMPTY);
	} else {
	    button.setEnabled(false);
	    if (startTextListener.isEmpty() || endTextListener.isEmpty()) {
		statusLabel.setText(Texts.EMPTY);
	    } else if ((result1 < 0) || (result2 < 0)) {
		statusLabel.setText(Texts.DIALOG_ERROR_NOT_A_NUMBER_MESSAGE);
	    } else if (result2 <= result1) {
		statusLabel
			.setText(Texts.DIALOG_ERROR_END_SMALLER_THAN_OR_EQUAL_TO_START_MESSAGE);
	    } else {
		statusLabel.setText(Texts.DIALOG_ERROR_LOCATION_OUT_OF_RANGE_MESSAGE);
	    }
	}
    }

    public long getFinalStartResult() {
	return finalStartResult;
    }

    public long getFinalEndResult() {
	return finalEndResult;
    }

}
