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
	statusLayout.numColumns = withSeparator ? 4 : 3;
	statusLayout.marginHeight = 0;
	setLayout(statusLayout);
	
	if (withSeparator) {
		GridData separator1GridData = new GridData();
		separator1GridData.grabExcessVerticalSpace = true;
		separator1GridData.verticalAlignment = SWT.FILL;
		Label separator1 = new Label(this, SWT.SEPARATOR);
		separator1.setLayoutData(separator1GridData);
	}

	position = new Label(this, SWT.SHADOW_NONE);
	GC gc= new GC(this);
	FontMetrics fontMetrics = gc.getFontMetrics();
	GridData gridData = 
		new GridData(fontMetrics.getAverageCharWidth() * (14 + 4 + 11 + 1), SWT.DEFAULT);
	position.setLayoutData(gridData);

	GridData separator2GridData = new GridData();
	separator2GridData.grabExcessVerticalSpace = true;
	separator2GridData.verticalAlignment = SWT.FILL;
	Label separator2 = new Label(this, SWT.SEPARATOR);
	separator2.setLayoutData(separator2GridData);

	insertMode = new Label(this, SWT.SHADOW_NONE);
	GridData gridData2 = new GridData(fontMetrics.getAverageCharWidth() *
									(textOverwrite.length() + 2), SWT.DEFAULT);
	insertMode.setLayoutData(gridData2);
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
 * Update the position status. Displays its decimal and hex value
 * @param newPos value to display
 */
public void updatePositionText(long newPos) {
	if (isDisposed() || position.isDisposed()) return;
	
	position.setText(newPos + " (0x" + Long.toHexString(newPos) + ')');
}
}
