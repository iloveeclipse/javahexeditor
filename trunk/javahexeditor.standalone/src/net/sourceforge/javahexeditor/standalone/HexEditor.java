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
package net.sourceforge.javahexeditor.standalone;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import net.sourceforge.javahexeditor.Manager;
import net.sourceforge.javahexeditor.PreferencesManager;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.MenuAdapter;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 * @author Jordi, Peter Dell
 */
public final class HexEditor {

    private final class MySelectionAdapter extends SelectionAdapter {
	public static final int ABOUT = 0;
	public static final int PASTE = 1;
	public static final int DELETE = 2;
	public static final int SELECT_ALL = 3;
	public static final int FIND = 4;
	public static final int OPEN = 5;
	public static final int SAVE = 6;
	public static final int SAVE_AS = 7;
	public static final int SAVE_SELECTION_AS = 8;
	public static final int EXIT = 9;
	public static final int CUT = 10;
	public static final int COPY = 11;
	public static final int GO_TO = 12;
	public static final int GUIDELOCAL = 13;
	public static final int GUIDEONLINE = 14;
	public static final int NEW = 15;
	public static final int PREFERENCES = 16;
	public static final int REDO = 17;
	public static final int TRIM = 18;
	public static final int UNDO = 19;
	public static final int SELECT_BLOCK = 20;

	private int myAction;

	MySelectionAdapter(int action) {
	    myAction = action;
	}

	@Override
	public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
	    switch (myAction) {
	    case ABOUT:
		MessageBox box = new MessageBox(shell, SWT.ICON_INFORMATION
			| SWT.OK);
		box.setText(Texts.ABOUT_DIALOG_TITLE);
		box.setMessage(Texts.ABOUT_DIALOG_TEXT);
		box.open();
		break;
	    case PASTE:
		manager.doPaste();
		break;
	    case DELETE:
		manager.doDelete();
		break;
	    case SELECT_ALL:
		manager.doSelectAll();
		break;
	    case FIND:
		manager.doFind();
		break;
	    case OPEN:
		doOpen(null, false, null);
		break;
	    case SAVE:
		doSave();
		break;
	    case SAVE_AS:
		doSaveAs();
		break;
	    case SAVE_SELECTION_AS:
		doSaveSelectionAs();
		break;
	    case EXIT:
		shell.close();
		shell.dispose();
		break;
	    case CUT:
		manager.doCut();
		break;
	    case COPY:
		manager.doCopy();
		break;
	    case GO_TO:
		manager.doGoTo();
		break;
	    case GUIDELOCAL:
		doOpenUserGuide(false);
		break;
	    case GUIDEONLINE:
		doOpenUserGuide(true);
		break;
	    case NEW:
		doOpen(null, true, null);
		break;
	    case PREFERENCES:
		doPreferences();
		break;
	    case REDO:
		manager.doRedo();
		break;
	    case TRIM:
		manager.doTrim();
		break;
	    case UNDO:
		manager.doUndo();
		break;
	    case SELECT_BLOCK:
		manager.doSelectBlock();
		break;
	    default:
		break;
	    }
	}
    }

    Shell shell;
    Manager manager;
    private HexEditorPreferences preferences;
    private PreferencesManager preferencesManager;

    private Menu menuBar;
    MenuItem pushCut;
    MenuItem pushCopy;
    MenuItem pushDelete;
    private MenuItem pushFind;
    private MenuItem pushGoTo;
    private MenuItem pushPaste;
    // private MenuItem pushPreferences;
    MenuItem pushRedo;
    MenuItem pushSave;
    private MenuItem pushSaveAs;
    MenuItem pushSaveSelectionAs;
    private MenuItem pushSelectBlock;
    private MenuItem pushSelectAll;
    MenuItem pushTrim;
    MenuItem pushUndo;

    private Menu fileSubMenu;
    private Menu editSubMenu;
    private Menu helpSubMenu;

    private Shell helpShell;
    private Browser helpBrowser;

    /**
     * Point of entry to the standalone version
     * 
     * @param args
     *            optional first String: name of a file to edit
     */
    public static void main(String[] args) {
	HexEditor ui = new HexEditor();
	ui.open(args);
    }

    /**
     * Point of entry to the standalone version
     * 
     * @param args
     *            The command line arguments, not <code>null</code>.
     */
    private void open(String[] args) {
	if (args == null) {
	    throw new IllegalArgumentException(
		    "Parameter 'args' must not be null.");
	}
	File file = null;
	if (args.length > 0 && args[0] != null) {
	    file = new File(args[0]);
	    if (!file.isFile() || !file.canRead()) {
		file = null;
	    }
	}
	Display display = Display.getDefault();
	manager = new Manager();
	preferences = new HexEditorPreferences();
	preferences.load();

	createShell();
	shell.pack();
	shell.open();
	if (file != null) {
	    doOpen(file, false, null);
	}

	String errorText;
	String errorMessage;
	while (!shell.isDisposed()) {
	    try {
		if (!display.readAndDispatch()) {
		    display.sleep();
		}
	    } catch (OutOfMemoryError e) {
		errorText = Texts.OUT_OF_MEMORY_ERROR;
		errorMessage = Manager.APPLICATION_NAME
			+ " has run out of memory.\n"
			+ "Try saving the current data and repeating the last action.";
		showErrorBox(errorText, errorMessage);
	    } catch (RuntimeException e) {
		errorText = Texts.FATAL_ERROR;
		errorMessage = Manager.APPLICATION_NAME
			+ " has crashed. The error cause is not "
			+ "known.\nFollowing is the error stack trace.\n\n" + e;
		Throwable t = e;
		while (t != null) {
		    for (int i = 0; i < t.getStackTrace().length; ++i) {
			errorMessage += "\nat " + t.getStackTrace()[i];
		    }
		    t = t.getCause();
		    if (t != null) {
			errorMessage += "\nCaused by " + t;
		    }
		}
		showErrorBox(errorText, errorMessage);
		display.dispose();
		throw e;
	    }
	}
	display.dispose();
    }

    private void showErrorBox(String text, String message) {
	MessageBox messageBox = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
	messageBox.setText(text);
	messageBox.setMessage(message);
	messageBox.open();
    }

    /**
     * This method initializes sShell
     */
    private void createShell() {
	shell = new Shell(Display.getDefault(), SWT.MODELESS | SWT.SHELL_TRIM);
	InputStream stream = ClassLoader
		.getSystemResourceAsStream("icons/hex.png");
	if (stream != null) {
	    try {
		final Image hexIcon = new Image(shell.getDisplay(), stream);
		shell.setImage(hexIcon);
		shell.addDisposeListener(new DisposeListener() {
		    @Override
		    public void widgetDisposed(DisposeEvent e) {
			hexIcon.dispose();
		    }
		});
	    } catch (SWTException ex) {
	    } finally {
		try {
		    stream.close();
		} catch (IOException ex) {
		}
	    }
	}
	shell.setText(Manager.APPLICATION_NAME);
	shell.setLayout(new FillLayout());
	menuBar = new Menu(shell, SWT.BAR);

	MenuItem fileMenuItem = new MenuItem(menuBar, SWT.CASCADE);
	fileMenuItem.setText("&File");
	MenuItem editMenuItem = new MenuItem(menuBar, SWT.CASCADE);
	editMenuItem.setText("&Edit");
	MenuItem helpMenuItem = new MenuItem(menuBar, SWT.CASCADE);
	helpMenuItem.setText("&Help");

	// File menu
	fileSubMenu = new Menu(fileMenuItem);
	MenuItem pushNew = createMenuItem(fileSubMenu, "&New\tCtrl+N",
		MySelectionAdapter.NEW);
	pushNew.setAccelerator(SWT.CONTROL | 'N');
	MenuItem pushOpen = createMenuItem(fileSubMenu,
		"&Open File...\tCtrl+O", MySelectionAdapter.OPEN);
	pushOpen.setAccelerator(SWT.CONTROL | 'O');
	createMenuSeparator(fileSubMenu);
	pushSave = createMenuItem(fileSubMenu, "&Save\tCtrl+S",
		MySelectionAdapter.SAVE);
	pushSave.setAccelerator(SWT.CONTROL | 'S');
	pushSaveAs = createMenuItem(fileSubMenu, "Save &As...",
		MySelectionAdapter.SAVE_AS);
	pushSaveSelectionAs = createMenuItem(fileSubMenu,
		"Save S&election As...", MySelectionAdapter.SAVE_SELECTION_AS);
	createMenuSeparator(fileSubMenu);
	createMenuItem(fileSubMenu, "E&xit", MySelectionAdapter.EXIT);
	fileSubMenu.addMenuListener(new MenuAdapter() {
	    @Override
	    public void menuShown(MenuEvent e) {
		pushSaveSelectionAs.setEnabled(manager.isTextSelected());
	    }
	});
	fileMenuItem.setMenu(fileSubMenu);

	// Edit menu
	editSubMenu = new Menu(editMenuItem);
	pushUndo = createMenuItem(editSubMenu, "&Undo\tCtrl+Z",
		MySelectionAdapter.UNDO);

	pushRedo = createMenuItem(editSubMenu, "Red&o\tCtrl+Y",
		MySelectionAdapter.REDO);

	createMenuSeparator(editSubMenu);
	pushCut = createMenuItem(editSubMenu, "Cu&t\tCtrl+X",
		MySelectionAdapter.CUT);

	pushCopy = createMenuItem(editSubMenu, "&Copy\tCtrl+C",
		MySelectionAdapter.COPY);

	pushPaste = createMenuItem(editSubMenu, "&Paste\tCtrl+V",
		MySelectionAdapter.PASTE);

	createMenuSeparator(editSubMenu);
	pushDelete = createMenuItem(editSubMenu, "&Delete\tDelete",
		MySelectionAdapter.DELETE);

	pushTrim = createMenuItem(editSubMenu, "T&rim", MySelectionAdapter.TRIM);

	pushSelectAll = createMenuItem(editSubMenu, "&Select All\tCtrl+A",
		MySelectionAdapter.SELECT_ALL);

	pushSelectBlock = createMenuItem(editSubMenu,
		"Select &Block...\tCtrl+E", MySelectionAdapter.SELECT_BLOCK);
	pushSelectBlock.setAccelerator(SWT.CONTROL | 'E');

	createMenuSeparator(editSubMenu);
	pushGoTo = createMenuItem(editSubMenu, "&Go to Location...\tCtrl+L",
		MySelectionAdapter.GO_TO);
	pushGoTo.setAccelerator(SWT.CONTROL | 'L');

	pushFind = createMenuItem(editSubMenu, Texts.FIND_MENU_ITEM_LABEL,
		MySelectionAdapter.FIND);
	pushFind.setAccelerator(SWT.CONTROL | 'F');

	createMenuSeparator(editSubMenu);
	createMenuItem(editSubMenu, "Font Pr&eferences...",
		MySelectionAdapter.PREFERENCES);

	editSubMenu.addMenuListener(new MenuAdapter() {
	    @Override
	    public void menuShown(MenuEvent e) {
		boolean selected = manager.isTextSelected();
		boolean lengthModifiable = selected
			&& !manager.isOverwriteMode();
		pushCopy.setEnabled(selected);
		pushCut.setEnabled(lengthModifiable);
		pushDelete.setEnabled(lengthModifiable);
		pushTrim.setEnabled(lengthModifiable);
		pushUndo.setEnabled(manager.canUndo());
		pushRedo.setEnabled(manager.canRedo());
	    }
	});
	editMenuItem.setMenu(editSubMenu);

	// Help menu
	helpSubMenu = new Menu(helpMenuItem);
	createMenuItem(helpSubMenu, "&User Guide (Online)...",
		MySelectionAdapter.GUIDEONLINE);
	createMenuItem(helpSubMenu, "User Guide (&Local)...",
		MySelectionAdapter.GUIDELOCAL);
	createMenuSeparator(helpSubMenu);
	createMenuItem(helpSubMenu, "&About javahexeditor",
		MySelectionAdapter.ABOUT);
	helpMenuItem.setMenu(helpSubMenu);

	shell.setMenuBar(menuBar);
	createComposite();
	shell.addListener(SWT.Close, new Listener() {
	    @Override
	    public void handleEvent(Event e) {
		if (!doClose())
		    e.doit = false;
	    }
	});
	manager.addListener(new Listener() {
	    @Override
	    public void handleEvent(Event event) {
		refreshTitleBar();
		pushSave.setEnabled(manager.isDirty());
	    }
	});
    }

    private MenuItem createMenuItem(Menu menu, String text, int actionId) {
	MenuItem result = new MenuItem(menu, SWT.PUSH);
	result.setText(text);
	result.addSelectionListener(new MySelectionAdapter(actionId));
	return result;
    }

    void refreshTitleBar() {
	StringBuilder title = new StringBuilder();
	File contentFile = manager.getContentFile();
	if (contentFile != null) {
	    if (manager.isDirty()) {
		title.append('*');
	    }
	    title.append(contentFile.getAbsolutePath()).append(" - ");
	}
	title.append(Manager.APPLICATION_NAME);
	shell.setText(title.toString());
    }

    @SuppressWarnings("unused")
    private void createMenuSeparator(Menu menu) {
	new MenuItem(menu, SWT.SEPARATOR);
    }

    /**
     * This method initializes composite
     */
    private void createComposite() {
	GridLayout gridLayout = new GridLayout(1, true);
	gridLayout.marginHeight = 0;
	gridLayout.verticalSpacing = 3;
	gridLayout.marginBottom = 2;
	Composite hexTextsParent = new Composite(shell, SWT.NONE);
	hexTextsParent.setLayout(gridLayout);
	manager.createEditorPart(hexTextsParent);

	GridData statusParentGridData = new GridData();
	GC gc = new GC(hexTextsParent);
	statusParentGridData.heightHint = gc.getFontMetrics().getHeight();
	gc.dispose();
	Composite statusParent = new Composite(hexTextsParent, SWT.NONE);
	statusParent.setLayoutData(statusParentGridData);
	GridLayout statusParentGridLayout = new GridLayout();
	statusParentGridLayout.marginHeight = 0;
	statusParentGridLayout.marginWidth = 0;
	statusParent.setLayout(statusParentGridLayout);

	manager.createStatusPart(statusParent, false);

	DropTarget target = new DropTarget(hexTextsParent, DND.DROP_MOVE
		| DND.DROP_COPY | DND.DROP_LINK);
	target.setTransfer(new Transfer[] { FileTransfer.getInstance(),
		TextTransfer.getInstance() });
	target.addDropListener(new DropTargetAdapter() {
	    @Override
	    public void dragEnter(DropTargetEvent e) {
		if (e.detail == DND.DROP_NONE)
		    e.detail = DND.DROP_COPY;
	    }

	    @Override
	    public void drop(DropTargetEvent event) {
		if (event.data == null || ((String[]) event.data).length < 1) {
		    event.detail = DND.DROP_NONE;

		    return;
		}
		File file = new File(((String[]) event.data)[0]);
		if (!file.exists() || file.isDirectory() || !file.canRead()) {
		    event.detail = DND.DROP_NONE;
		    MessageBox box = new MessageBox(shell, SWT.ICON_WARNING
			    | SWT.OK);
		    box.setText("File error");
		    box.setMessage("Cannot open the file " + file);
		    box.open();
		} else {
		    doOpen(file, false, null);
		}
	    }
	});
    }

    private void showErrorBox(Shell shell, String title, String message) {
	if (shell == null) {
	    throw new IllegalArgumentException(
		    "Parameter 'shell' must not be null.");
	}
	if (title == null) {
	    throw new IllegalArgumentException(
		    "Parameter 'title' must not be null.");
	}
	if (message == null) {
	    throw new IllegalArgumentException(
		    "Parameter 'message' must not be null.");
	}
	MessageBox messageBox = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
	messageBox.setText(title);
	messageBox.setMessage(message);
	messageBox.open();
    }

    void doOpen(File file, boolean newFile, String charset) {
	if (!doClose()) {
	    return;
	}
	manager.doOpen(file, newFile, charset);
	pushFind.setEnabled(true);
	pushGoTo.setEnabled(true);
	pushPaste.setEnabled(true);
	pushSelectAll.setEnabled(true);
	pushSelectBlock.setEnabled(true);
	pushSaveAs.setEnabled(true);

    }

    boolean doSave() {
	if (manager.getContentFile() == null) {
	    return doSaveAs();
	}

	try {
	    manager.saveFile();
	} catch (IOException ex) {
	    showErrorBox(shell, Texts.SAVE_ERROR, ex.getMessage());
	    return false;
	}

	return true;
    }

    boolean doClose() {
	// Anything open and any changes to save?
	if (manager.getContent() == null || !manager.isDirty()) {
	    return true;
	}

	MessageBox box = new MessageBox(shell, SWT.ICON_WARNING | SWT.YES
		| SWT.NO | SWT.CANCEL);
	box.setText("Modified file");
	box.setMessage("The current file has been modified.\nSave changes?");
	int result = box.open();
	if (result == SWT.CANCEL) {
	    return false;
	}
	if (result == SWT.YES) {
	    return doSave();
	}

	return true;
    }

    boolean doSaveAs() {
	File file = manager.showSaveAsDialog(shell, false);
	if (file == null) {
	    return false;
	}

	try {
	    manager.saveAsFile(file);
	} catch (IOException ex) {
	    showErrorBox(shell, Texts.SAVE_ERROR, ex.getMessage());
	    return false;
	}

	return true;
    }

    void doSaveSelectionAs() {
	File file = manager.showSaveAsDialog(shell, true);
	if (file == null) {
	    return;
	}

	try {
	    manager.doSaveSelectionAs(file);
	} catch (IOException ex) {
	    showErrorBox(shell, Texts.SAVE_ERROR, ex.getMessage());
	}

    }

    void doPreferences() {
	if (preferencesManager == null) {
	    preferencesManager = new PreferencesManager(
		    preferences.getFontData());
	}
	if (preferencesManager.openDialog(shell) == SWT.OK) {
	    preferences.setFontData(preferencesManager.getFontData());
	    preferences.store();
	    manager.setTextFont(preferencesManager.getFontData());
	}
    }

    void doOpenUserGuide(boolean online) {

	if (helpShell == null || helpShell.isDisposed()) {
	    helpShell = new Shell(Display.getCurrent());

	    GridLayout gridLayout = new GridLayout();
	    gridLayout.numColumns = 1;
	    helpShell.setLayout(gridLayout);

	    helpBrowser = new Browser(helpShell, SWT.NONE);
	    GridData data = new GridData();
	    data.horizontalAlignment = GridData.FILL;
	    data.verticalAlignment = GridData.FILL;
	    data.horizontalSpan = 1;
	    data.grabExcessHorizontalSpace = true;
	    data.grabExcessVerticalSpace = true;
	    helpBrowser.setLayoutData(data);
	}

	String fileName = "userGuide.html";
	String url;
	if (online) {
	    url = "http://javahexeditor.sourceforge.net/" + fileName;
	} else {
	    {
		InputStream inStream = getClass().getResourceAsStream(
			"/" + fileName);
		if (inStream == null) {
		    throw new RuntimeException("Help file '" + fileName
			    + "' missing in classpath.");
		}
		File localFile = new File(System.getProperty("java.io.tmpdir"),
			fileName);
		try {
		    FileOutputStream outStream = new FileOutputStream(localFile);
		    byte[] buffer = new byte[512];
		    int read = 0;
		    try {
			while ((read = inStream.read(buffer)) > 0) {
			    outStream.write(buffer, 0, read);
			}
		    } finally {
			outStream.close();
		    }
		} catch (IOException ignore) {
		    // Open browser anyway
		}
		try {
		    inStream.close();
		} catch (IOException ignore) {
		    // Open browser anyway
		}
		url = localFile.toURI().toString();
	    }
	}

	helpShell.open();
	helpShell.setText(url);
	helpBrowser.setUrl(url);

    }

}
