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

import net.sourceforge.javahexeditor.BinaryContent.RangeSelection;
import net.sourceforge.javahexeditor.common.NumberUtility;
import net.sourceforge.javahexeditor.common.TextUtility;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * Status line component of the editor. Displays the current position and the
 * insert/overwrite status.
 */
final class StatusLine extends Composite {

    private Label positionLabel;
    private Label valueLabel;
    private Label insertModeLabel;

    /**
     * Create a status line part
     *
     * @param parent
     *            parent in the widget hierarchy
     * @param style
     *            not used
     * @param withLeftSeparator
     *            so it can be put besides other status items (for plugin)
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
        } else {
            GridData statusLineGridData = new GridData();
            statusLineGridData.grabExcessVerticalSpace = true;
            statusLineGridData.verticalAlignment = SWT.FILL;
            setLayoutData(statusLineGridData);
        }

        GC gc = new GC(this);
        FontMetrics fontMetrics = gc.getFontMetrics();

        positionLabel = new Label(this, SWT.SHADOW_NONE);
        GridData gridData1 = new GridData(
                /* SWT.DEFAULT */(11 + 10 + 12 + 3 + 10 + 12)
                * fontMetrics.getAverageCharWidth(), SWT.DEFAULT);
        positionLabel.setLayoutData(gridData1);

        GridData separator23GridData = new GridData();
        separator23GridData.grabExcessVerticalSpace = true;
        separator23GridData.verticalAlignment = SWT.FILL;
        Label separator2 = new Label(this, SWT.SEPARATOR);
        separator2.setLayoutData(separator23GridData);

        valueLabel = new Label(this, SWT.SHADOW_NONE);
        GridData gridData2 = new GridData(
                /* SWT.DEFAULT */(7 + 3 + 9 + 2 + 9 + 8 + 6)
                * fontMetrics.getAverageCharWidth(), SWT.DEFAULT);
        valueLabel.setLayoutData(gridData2);

        // From Eclipse 3.1's GridData javadoc:
        // NOTE: Do not reuse GridData objects.
        // Every control in a Composite that is managed by a
        // GridLayout must have a unique GridData
        GridData separator3GridData = new GridData();
        separator3GridData.grabExcessVerticalSpace = true;
        separator3GridData.verticalAlignment = SWT.FILL;
        Label separator3 = new Label(this, SWT.SEPARATOR);
        separator3.setLayoutData(separator3GridData);

        insertModeLabel = new Label(this, SWT.SHADOW_NONE);
        int maxLength = Math.max(Texts.STATUS_LINE_MODE_INSERT.length(),
                Texts.STATUS_LINE_MODE_OVERWRITE.length());
        GridData gridData3 = new GridData((maxLength + 2)
                * fontMetrics.getAverageCharWidth(), SWT.DEFAULT);
        insertModeLabel.setLayoutData(gridData3);
        gc.dispose();
    }

    /**
     * Update the insert/overwrite mode.
     *
     * @param insert
     *            <code>true</code> for insert mode, or <code>false</code> for
     *            overwrite
     */
    public void updateInsertMode(boolean insert) {
        if (isDisposed() || insertModeLabel.isDisposed()) {
            return;
        }

        insertModeLabel.setText(insert ? Texts.STATUS_LINE_MODE_INSERT
                : Texts.STATUS_LINE_MODE_OVERWRITE);
    }

    /**
     * Update the position status and value.
     *
     * @param position
     * @param value
     *
     * @see #updatePosition
     * @see #updateValue
     */
    public void updatePositionValue(long position, byte value) {
        updatePosition(position);
        updateValue(value);
    }

    /**
     * Update the selection status and value.
     *
     * @param rangeSelection
     * @param value
     *
     * @see #updateSelection
     * @see #updateValue
     */
    public void updateSelectionValue(RangeSelection rangeSelection, byte value) {
        if (rangeSelection == null) {
            throw new IllegalArgumentException(
                    "Parameter 'rangeSelection' must not be null.");
        }
        updateSelection(rangeSelection);
        updateValue(value);
    }

    /**
     * Update the position status. Displays its decimal and hex value.
     *
     * @param position
     *            position to display
     */
    private void updatePosition(long position) {
        if (isDisposed() || positionLabel.isDisposed()) {
            return;
        }

        String text = TextUtility.format(Texts.STATUS_LINE_MESSAGE_POSITION,
                NumberUtility.getDecimalAndHexString(position));

        positionLabel.setText(text);
    }

    /**
     * Update the value. Displays its decimal, hex and binary value
     *
     * @param value
     *            value to display
     */
    private void updateValue(byte value) {
        if (isDisposed() || positionLabel.isDisposed()) {
            return;
        }

        int unsignedValue = value & 0xff;
        String binaryText = "0000000" + Integer.toBinaryString(unsignedValue);
        binaryText = binaryText.substring(binaryText.length() - 8);

        String text = TextUtility.format(Texts.STATUS_LINE_MESSAGE_VALUE,
                NumberUtility.getDecimalString(unsignedValue),
                NumberUtility.getHexString(unsignedValue), binaryText);

        valueLabel.setText(text);
    }

    /**
     * Update the selection status. Displays its decimal and hex values for
     * start and end selection
     *
     * @param rangeSelection
     *            selection array to display: [0] = start, [1] = end
     */
    private void updateSelection(RangeSelection rangeSelection) {
        if (isDisposed() || positionLabel.isDisposed())
            return;
        String text = TextUtility.format(Texts.STATUS_LINE_MESSAGE_SELECTION,
                NumberUtility.getDecimalAndHexRangeString(rangeSelection.start,
                        rangeSelection.end));

        positionLabel.setText(text);
    }

}
