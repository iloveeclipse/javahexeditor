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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sourceforge.javahexeditor.BinaryContent.RangeSelection;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

/**
 * Manager of the javahexeditor application, either in its stand alone or
 * Eclipse plugin version. Manages creation of widgets, and executes menu
 * actions, like File->Save. Call createEditorPart() before any menu actions.
 * 
 * @author Jordi
 */
public final class Manager {

    public static final String APPLICATION_NAME = "javahexeditor";

    private BinaryContent content;
    private File contentFile;

    private FindReplaceHistory findReplaceHistory;
    private FontData fontData;
    Font font;
    private List<Listener> listOfStatusChangedListeners;
    private List<SelectionListener> listOfLongListeners;

    // Visual controls
    private Shell shell;
    private Composite textsParent;
    private HexTexts hexTexts;
    private StatusLine statusLine;

    private FindReplaceDialog findDialog;
    private GoToDialog goToDialog;
    private SelectBlockDialog selectBlockDialog;

    /**
     * Creates editor part of parent application. Can only be called once per
     * Manager object.
     * 
     * @param parent
     *            Composite where the part will be created, not
     *            <code>null</code>.
     */
    public void createEditorPart(Composite parent) {
	if (parent == null) {
	    throw new IllegalArgumentException(
		    "Parameter 'parent' must not be null.");
	}
	if (hexTexts != null) {
	    throw new IllegalStateException("Editor part exists already");
	}

	shell = parent.getShell();
	textsParent = parent;
	hexTexts = new HexTexts(textsParent, SWT.NONE);
	hexTexts.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	hexTexts.setEnabled(false);

	hexTexts.addDisposeListener(new DisposeListener() {
	    @Override
	    public void widgetDisposed(DisposeEvent e) {
		if (font != null && !font.isDisposed()) {
		    font.dispose();
		}
	    }
	});
	if (fontData != null) {
	    font = new Font(Display.getCurrent(), fontData);
	    hexTexts.setFont(font);
	}

	hexTexts.addLongSelectionListener(new SelectionAdapter() {
	    @Override
	    public void widgetSelected(SelectionEvent e) {
		updateStatusLineAfterLongSelection();
	    }
	});
	hexTexts.addListener(SWT.Modify, new Listener() {
	    @Override
	    public void handleEvent(Event event) {
		updateStatusLineAfterModify();
	    }
	});

	if (listOfStatusChangedListeners != null) {
	    for (Listener listener : listOfStatusChangedListeners) {
		hexTexts.addListener(SWT.Modify, listener);
	    }
	    listOfStatusChangedListeners = null;
	}

	if (listOfLongListeners != null) {
	    for (SelectionListener listener : listOfLongListeners) {
		hexTexts.addLongSelectionListener(listener);
	    }
	    listOfLongListeners = null;
	}
    }

    /**
     * Add a listener to changes of the 'dirty', 'insert/overwrite', 'selection'
     * and 'canUndo/canRedo' status
     * 
     * @param listener
     *            the listener to be notified of changes
     */
    public void addListener(Listener listener) {
	if (listener == null)
	    return;

	if (hexTexts == null) {
	    if (listOfStatusChangedListeners == null)
		listOfStatusChangedListeners = new ArrayList<Listener>();
	    listOfStatusChangedListeners.add(listener);
	} else {
	    hexTexts.addListener(SWT.Modify, listener);
	}
    }

    /**
     * Adds a long selection listener. Events sent to the listener have long
     * start and end points.
     * 
     * @see HexTexts#addLongSelectionListener(SelectionListener)
     * @param listener
     *            the listener
     * @see StyledText#addSelectionListener(org.eclipse.swt.events.SelectionListener)
     */
    public void addLongSelectionListener(SelectionListener listener) {
	if (listener == null)
	    throw new IllegalArgumentException();

	if (hexTexts == null) {
	    if (listOfLongListeners == null) {
		listOfLongListeners = new ArrayList<SelectionListener>();
	    }
	    listOfLongListeners.add(listener);
	} else {
	    hexTexts.addLongSelectionListener(listener);
	}
    }

    /**
     * Get long selection start and end points. Helper method for long selection
     * listeners. The start point is formed by event.width as the most
     * significant int and event.x as the least significant int. The end point
     * is similarly formed by event.height and event.y
     * 
     * @param event
     *            an event with long selection start and end points
     * @return
     * @see #addLongSelectionListener(org.eclipse.swt.events.SelectionListener)
     */
    public long[] getLongSelection(SelectionEvent event) {
	return new long[] {
		((long) event.width) << 32 | (event.x & 0x0ffffffffL),
		((long) event.height) << 32 | (event.y & 0x0ffffffffL) };
    }

    /**
     * Tells whether the last action can be redone
     * 
     * @return true: an action ca be redone
     */
    public boolean canRedo() {
	return hexTexts != null && hexTexts.canRedo();
    }

    /**
     * Tells whether the last action can be undone
     * 
     * @return true: an action ca be undone
     */
    public boolean canUndo() {
	return hexTexts != null && hexTexts.canUndo();
    }

    /**
     * Creates status part of parent application.
     * 
     * @param parent
     *            Composite where the part will be created, not
     *            <code>null</code>.
     * @param withLeftSeparator
     *            so it can be put besides other status items (for plugin)
     */
    public void createStatusPart(Composite parent, boolean withLeftSeparator) {
	if (parent == null) {
	    throw new IllegalArgumentException(
		    "Parameter 'parent' must not be null.");
	}
	statusLine = new StatusLine(parent, SWT.NONE, withLeftSeparator);
	if (hexTexts != null && hexTexts.isEnabled()) {
	    statusLine.updateInsertMode(!hexTexts.isOverwriteMode());
	    if (hexTexts.isSelected())
		statusLine.updateSelectionValue(hexTexts.getSelection(),
			hexTexts.getActualValue());
	    else
		statusLine.updatePositionValue(hexTexts.getCaretPos(),
			hexTexts.getActualValue());
	}
    }

    /**
     * Copies selection into clipboard
     */
    public void doCopy() {
	if (hexTexts == null)
	    return;

	hexTexts.copy();
    }

    /**
     * Cuts selection into clipboard
     */
    public void doCut() {
	if (hexTexts == null)
	    return;

	hexTexts.cut();
    }

    /**
     * While in insert mode, deletes the selection
     */
    public void doDelete() {
	hexTexts.deleteSelected();
    }

    /**
     * Open find dialog
     */
    public void doFind() {
	if (findDialog == null) {
	    findDialog = new FindReplaceDialog(textsParent.getShell());
	    if (findReplaceHistory == null) {
		findReplaceHistory = new FindReplaceHistory();
	    }
	}

	findDialog.open(hexTexts, findReplaceHistory);
    }

    /**
     * Open 'go to' dialog
     */
    public void doGoTo() {
	if (content.length() < 1L)
	    return;

	if (goToDialog == null)
	    goToDialog = new GoToDialog(textsParent.getShell());

	long location = goToDialog.open(content.length() - 1L);
	if (location >= 0L) {
	    long button = goToDialog.getButtonPressed();
	    if (button == 1)
		hexTexts.showMark(location);
	    else
		hexTexts.selectBlock(location, location);
	}
    }

    /**
     * Open 'select block' dialog
     */
    public void doSelectBlock() {
	if (content.length() < 1L)
	    return;

	if (selectBlockDialog == null) {
	    selectBlockDialog = new SelectBlockDialog(textsParent.getShell());
	}
	long start = selectBlockDialog.open(hexTexts.getSelection(),
		content.length() - 1L);
	long end = selectBlockDialog.getFinalEndResult();
	if ((start >= 0L) && (end >= 0L) && (start != end)) {
	    hexTexts.selectBlock(start, end);
	}
    }

    @SuppressWarnings("null")
    public void doOpen(File forceThisFile, boolean newFile, String charset) {

	if (forceThisFile == null && !newFile) {
	    String fileName = new FileDialog(shell, SWT.OPEN).open();
	    if (fileName == null) {
		return;
	    }
	    forceThisFile = new File(fileName);
	}
	if (forceThisFile != null) {
	    try {
		forceThisFile = forceThisFile.getCanonicalFile();
	    } catch (IOException e) {
		// use non-canonical one then
	    }
	}

	try {
	    openFile(forceThisFile, charset);
	} catch (IOException ex) {
	    SWTUtility.showMessage(shell, SWT.ICON_ERROR | SWT.OK,
		    Texts.FILE_READ_ERROR_TITLE, Texts.FILE_READ_ERROR_MESSAGE,
		    forceThisFile.getAbsolutePath(), ex.getMessage());
	}

	hexTexts.setFocus();
	if (newFile) {
	    hexTexts.setInsertMode(true);
	}
    }

    /**
     * Pastes clipboard into editor
     */
    public void doPaste() {
	if (hexTexts == null)
	    return;

	hexTexts.paste();
    }

    /**
     * Perform save-selected-as action on selected data
     * 
     * @param file
     *            The file, not <code>null</code>.
     * 
     * @throws IOException
     *             If the operation fails
     */
    public void doSaveSelectionAs(File file) throws IOException {
	if (isFileBeingRead(file)) {
	    throw new IOException(TextUtility.format(
		    Texts.MANAGER_MESSAGE_CANNOT_BE_OVERWRITTEN,
		    file.getAbsolutePath()));
	}

	RangeSelection selection = hexTexts.getSelection();
	try {
	    content.get(file, selection.start, selection.getLength());
	} catch (IOException ex) {
	    throw new IOException(TextUtility.format(
		    Texts.MANAGER_MESSAGE_COULD_NOT_SAVE_FILE,
		    file.getAbsolutePath(), ex.getMessage()));

	}
    }

    /**
     * Selects all file contents in editor
     */
    public void doSelectAll() {
	if (hexTexts == null)
	    return;

	hexTexts.selectAll();
    }

    /**
     * Redoes the last undone action
     */
    public void doRedo() {
	hexTexts.redo();
    }

    /**
     * While in insert mode, trims the selection
     */
    public void doTrim() {
	hexTexts.deleteNotSelected();
    }

    /**
     * Undoes the last action
     */
    public void doUndo() {
	hexTexts.undo();
    }

    /**
     * Gets the binary content.
     * 
     * @return The content being edited or <code>null</code> if there is no
     *         content.
     */
    public BinaryContent getContent() {
	return content;
    }

    /**
     * Gets the binary content file.
     * 
     * @return The file which represents the content being edited or
     *         <code>null</code> if there is no file.
     */
    public File getContentFile() {
	return contentFile;
    }

    /**
     * Gets the current selection.
     * 
     * @return The current selection, not <code>null</code>.
     * 
     * @see HexTexts#getSelection()
     */
    public RangeSelection getSelection() {
	if (hexTexts == null) {
	    return new RangeSelection(0, 0);
	}

	return hexTexts.getSelection();
    }

    /**
     * Get whether the content has been modified or not
     * 
     * @return if changes have been performed
     */
    public boolean isDirty() {
	if (content == null)
	    return false;

	return content.isDirty();
    }

    private boolean isFileBeingRead(File file) {
	return file.equals(contentFile)
		|| content.getOpenFiles().contains(file);
    }

    /**
     * Tells whether the input is in overwrite or insert mode
     * 
     * @return true: overwriting, false: inserting
     */
    public boolean isOverwriteMode() {
	if (hexTexts == null) {
	    return true;
	}

	return hexTexts.isOverwriteMode();
    }

    /**
     * Tells whether the input has text selected
     * 
     * @return true: text is selected, false: no text selected
     */
    public boolean isTextSelected() {
	if (hexTexts == null) {
	    return false;
	}

	return hexTexts.getSelection().getLength() > 0;
    }

    /**
     * Open file for editing
     * 
     * @param contentFile
     *            the file to be edited
     * @param charset
     * @throws IOException
     *             when a file has no read access
     */
    public void openFile(File contentFile, String charset) throws IOException {
	content = new BinaryContent(contentFile); // throws IOException
	this.contentFile = contentFile;
	hexTexts.setCharset(charset);
	hexTexts.setContentProvider(content);
	hexTexts.setEnabled(true);

    }

    /**
     * Reuse the status line control from another manager. Useful for multiple
     * open editors
     * 
     * @param other
     *            manager to copy its control from
     */
    public void reuseStatusLinelFrom(Manager other) {
	if (other == null) {
	    throw new IllegalArgumentException(
		    "Parameter 'other' must not be null.");
	}
	statusLine = other.statusLine;
    }

    /**
     * Perform save-as action on opened file
     * 
     * @param file
     *            The new file, not <code>null</code>.
     * 
     * @throws IOException
     *             If the operation fails
     */
    public void saveAsFile(File file) throws IOException {
	if (file == null) {
	    throw new IllegalArgumentException(
		    "Parameter 'file' must not be null.");
	}
	if (file.equals(contentFile)) {
	    saveFile();
	    return;
	}

	if (isFileBeingRead(file)) {
	    throw new IOException(TextUtility.format(
		    Texts.MANAGER_MESSAGE_CANNOT_BE_OVERWRITTEN,
		    file.getAbsolutePath()));
	}

	try {
	    content.get(file);
	    content.dispose();
	    content = new BinaryContent();
	    contentFile = null;
	} catch (IOException ex) {
	    throw new IOException(TextUtility.format(
		    Texts.MANAGER_MESSAGE_COULD_NOT_SAVE_FILE,
		    file.getAbsolutePath(), ex.getMessage()));
	}
	try {
	    content = new BinaryContent(file);
	    contentFile = file;
	} catch (IOException ex) {
	    TextUtility.format(
		    Texts.MANAGER_MESSAGE_COULD_NOT_READ_FROM_SAVED_FILE,
		    file.getAbsolutePath(), ex.getMessage());
	}
	hexTexts.setContentProvider(content);
    }

    /**
     * Perform save action on opened file
     * 
     * @throws IOException
     *             If the operation fails
     */
    public void saveFile() throws IOException {
	boolean successful = false;
	String errorMessage = Texts.MANAGER_MESSAGE_COULD_NOT_CREATE_TEMP_FILE_WITH_UNIQUE_NAME;
	File tempFile = null;
	// It can happen that in two successive "Save File"'s the first one
	// didn't get the temp file deleted due to limitations in the OS
	// (windows). With this loop it's possible to save many times
	for (int tries = 9999; tries >= 0 && !successful; --tries) {
	    try {
		// + "99" is to avoid IllegalArgumentException
		tempFile = File.createTempFile(contentFile.getName() + "99", ""
			+ tries, contentFile.getParentFile());
	    } catch (IOException e1) {
		e1.printStackTrace();
	    }
	}
	if (tempFile != null) {
	    successful = false;
	    try {
		errorMessage = TextUtility.format(
			Texts.MANAGER_MESSAGE_COULD_NOT_WRITE_ON_TEMP_FILE,
			tempFile.getAbsolutePath());
		content.get(tempFile);
		content.dispose();
		content = new BinaryContent();
		errorMessage = TextUtility.format(
			Texts.MANAGER_MESSAGE_COULD_OVERWRITE_FILE,
			contentFile.getAbsolutePath(),
			tempFile.getAbsolutePath());

		BinaryContentClipboard.deleteFileALaMs(contentFile);
		// Successful delete or not try
		if (tempFile.renameTo(contentFile)) {
		    // renaming anyway
		    errorMessage = TextUtility
			    .format(Texts.MANAGER_MESSAGE_COULD_NOT_READ_FROM_SAVED_FILE,
				    contentFile.getAbsolutePath());
		    content = new BinaryContent(contentFile);
		    successful = true;
		}
	    } catch (IOException e) {
		// error handling below
	    }
	    hexTexts.setContentProvider(content);
	}
	if (!successful) {
	    throw new IOException(errorMessage);
	}

    }

    /**
     * Sets Find/Replace combo lists pre-exisiting values.
     * 
     * @param findReplaceHistory
     *            The modifiable find-replace history, not <code>null</code>.
     */
    public void setFindReplaceHistory(FindReplaceHistory findReplaceHistory) {
	if (findReplaceHistory == null) {
	    throw new IllegalArgumentException(
		    "Parameter 'findReplaceHistory' must not be null.");
	}
	this.findReplaceHistory = findReplaceHistory;
    }

    /**
     * Causes the text areas to have the keyboard focus
     */
    public void setFocus() {
	if (hexTexts != null) {
	    hexTexts.setFocus();
	}

	if (statusLine != null) {
	    statusLine.updateInsertMode(hexTexts == null ? true : !hexTexts
		    .isOverwriteMode());
	    if (hexTexts != null) {
		if (hexTexts.isSelected())
		    statusLine.updateSelectionValue(hexTexts.getSelection(),
			    hexTexts.getActualValue());
		else
		    statusLine.updatePositionValue(hexTexts.getCaretPos(),
			    hexTexts.getActualValue());
	    } else {
		statusLine.updatePositionValue(0L, (byte) 0);
	    }
	}
    }

    public void setSelection(RangeSelection selection) {
	if (selection == null) {
	    throw new IllegalArgumentException(
		    "Parameter 'selection' must not be null.");
	}
	if (hexTexts == null) {
	    return;
	}
	hexTexts.setSelection(selection.start, selection.end);

    }

    /**
     * Set the editor text font.
     * 
     * @param aFont
     *            new font to be used; should be a constant char width font. Use
     *            <code>null</code> to set to the default font.
     */
    public void setTextFont(FontData aFont) {
	fontData = aFont;
	if (HexTexts.fontDataDefault.equals(fontData)) {
	    fontData = null;
	}
	// dispose it after setting new one
	// StyledTextRenderer 3.448 bug in line 994
	Font fontToDispose = font;
	font = null;
	if (hexTexts != null) {
	    if (fontData != null) {
		font = new Font(Display.getCurrent(), fontData);
	    }
	    hexTexts.setFont(font);
	}
	if (fontToDispose != null && !fontToDispose.isDisposed())
	    fontToDispose.dispose();
    }

    /**
     * Show a file dialog with a save-as message
     * 
     * @param aShell
     *            parent of the dialog
     * @param selection
     * @return
     */
    public File showSaveAsDialog(Shell aShell, boolean selection) {
	FileDialog dialog = new FileDialog(aShell, SWT.SAVE);
	if (selection) {
	    dialog.setText(Texts.SAVE_DIALOG_TITLE_SAVE_SELECTION_AS);
	} else {
	    dialog.setText(Texts.SAVE_DIALOG_TITLE_SAVE_AS);
	}
	String filePath = dialog.open();
	if (filePath == null) {
	    return null;
	}

	File file = new File(filePath);
	if (file.exists()) {
	    if (SWTUtility.showMessage(aShell, SWT.ICON_WARNING | SWT.YES
		    | SWT.NO, Texts.SAVE_DIALOG_TITLE_FILE_ALREADY_EXISTS,
		    Texts.SAVE_DIALOG_MESSAGE_FILE_ALREADY_EXISTS,
		    file.getAbsolutePath()) != SWT.YES) {
		return null;
	    }
	}
	return file;
    }

    /**
     * Event handler for updating the status line.
     */
    void updateStatusLineAfterLongSelection() {
	if (statusLine != null) {
	    if (hexTexts != null) {
		if (hexTexts.isSelected()) {
		    statusLine.updateSelectionValue(hexTexts.getSelection(),
			    hexTexts.getActualValue());
		} else {
		    statusLine.updatePositionValue(hexTexts.getCaretPos(),
			    hexTexts.getActualValue());
		}
	    } else {
		statusLine.updatePositionValue(0L, (byte) 0);
	    }
	}
    }

    /**
     * Event handler for updating the status line.
     */
    void updateStatusLineAfterModify() {
	if (statusLine != null) {
	    statusLine.updateInsertMode(hexTexts == null ? true : !hexTexts
		    .isOverwriteMode());
	}
    }

}
