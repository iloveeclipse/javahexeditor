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

import java.util.Formatter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * Status line component of the editor. Displays the current position and the insert/overwrite status.
 */
public class StatusLine extends Composite {

	
static final String textInsert = "Insert";
static final String textOverwrite = "Overwrite";

private Label position = null;
private Label value = null;
private Label insertMode = null;


/**
 * Create a status line part
 * @param parent parent in the widget hierarchy
 * @param style not used
 * @param withLeftSeparator so it can be put besides other status items (for plugin)
 */
public StatusLine(Composite parent, int style, boolean withLeftSeparator) {
	super(parent, style);
	initialize(withLeftSeparator);
}


private void initialize(boolean withSeparator) {
	GridLayout statusLayout = new GridLayout();
	statusLayout.numColumns = withSeparator ? 6 : 5;
	statusLayout.marginHeight = 0;
	setLayout(statusLayout);
	
	if (withSeparator) {
		GridData separator1GridData = new GridData();
		separator1GridData.grabExcessVerticalSpace = true;
		separator1GridData.verticalAlignment = SWT.FILL;
		Label separator1 = new Label(this, SWT.SEPARATOR);
		separator1.setLayoutData(separator1GridData);
	}

	GC gc= new GC(this);
	FontMetrics fontMetrics = gc.getFontMetrics();
	
	position = new Label(this, SWT.SHADOW_NONE);
	GridData gridData1 = new GridData(/*SWT.DEFAULT*/ (11+10+12+3+10+12) * fontMetrics.getAverageCharWidth(), SWT.DEFAULT);
	position.setLayoutData(gridData1);

	GridData separator23GridData = new GridData();
	separator23GridData.grabExcessVerticalSpace = true;
	separator23GridData.verticalAlignment = SWT.FILL;
	Label separator2 = new Label(this, SWT.SEPARATOR);
	separator2.setLayoutData(separator23GridData);
	
	value = new Label(this, SWT.SHADOW_NONE);
	GridData gridData2 = new GridData(/*SWT.DEFAULT*/ (7+3+9+2+9+8+6) * fontMetrics.getAverageCharWidth(), SWT.DEFAULT);
	value.setLayoutData(gridData2);
	
	Label separator3 = new Label(this, SWT.SEPARATOR);
	separator3.setLayoutData(separator23GridData);

	insertMode = new Label(this, SWT.SHADOW_NONE);
	GridData gridData3 = new GridData(/*SWT.DEFAULT*/ (textOverwrite.length() + 2) * fontMetrics.getAverageCharWidth(), SWT.DEFAULT);
	insertMode.setLayoutData(gridData3);
	gc.dispose();
}


/**
 * Update the insert mode status. Can be "Insert" or "Overwrite"
 * @param insert true will display "Insert"
 */
public void updateInsertModeText(boolean insert) {
	if (isDisposed() || insertMode.isDisposed()) return;
	
	insertMode.setText(insert ? textInsert : textOverwrite);
}

/**
 * Update the position status and value.
 * @see updatePositionText
 * @see updateValueText
 */
public void updatePositionValueText(long pos, byte val) {
	updatePositionText(pos);
	updateValueText(val);
}

/**
 * Update the selection status and value.
 * @see updateSelectionText
 * @see updateValueText
 */
public void updateSelectionValueText(long[] sel, byte val) {
	updateSelectionText(sel);
	updateValueText(val);
}

/**
 * Update the position status. Displays its decimal and hex value.
 * @param newPos position to display
 */
public void updatePositionText(long pos) {
	if (isDisposed() || position.isDisposed()) return;
	
	String posText = String.format("Offset: %1$d (dec) = %1$X (hex)", pos);
	position.setText(posText);
	//position.pack(true);
}

/**
 * Update the value. Displays its decimal, hex and binary value
 * @param val value to display
 */
public void updateValueText(byte val) {
	if (isDisposed() || position.isDisposed()) return;
	
	String valBinText = "0000000" + Long.toBinaryString(val);
	String valText = String.format("Value: %1$d (dec) = %1$X (hex) = %2$s (bin)", val, valBinText.substring(valBinText.length()-8));
	value.setText(valText);
	//value.pack(true);
}

/**
 * Update the selection status. Displays its decimal and hex values for start and end selection
 * @param sel selection array to display: [0] = start, [1] = end
 */
public void updateSelectionText(long[] sel) {
	if (isDisposed() || position.isDisposed()) return;
	
	String selText = String.format("Selection: %1$d (0x%1$X) - %2$d (0x%2$X)", sel[0], sel[1]);
	position.setText(selText);
	//position.pack(true);
}

}
