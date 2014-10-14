package net.sourceforge.javahexeditor.standalone;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public final class HexEditorMenu {

    public final class Actions {
	private Actions() {
	}

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
	public static final int HELP_CONTENTS = 13;
	public static final int WEB_SITE = 14;
	public static final int NEW = 15;
	public static final int PREFERENCES = 16;
	public static final int REDO = 17;
	public static final int TRIM = 18;
	public static final int UNDO = 19;
	public static final int SELECT_BLOCK = 20;
    }

    private final class MySelectionAdapter extends SelectionAdapter {
	private int actionId;

	public MySelectionAdapter(int actionId) {
	    this.actionId = actionId;
	}

	@Override
	public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
	    hexEditor.performAction(actionId);
	}
    }

    HexEditor hexEditor;

    public final Menu menuBar;
    public final Menu fileSubMenu;
    public final Menu editSubMenu;
    public final Menu helpSubMenu;
    public final MenuItem pushCut;
    public final MenuItem pushCopy;
    public final MenuItem pushDelete;
    public final MenuItem pushFind;
    public final MenuItem pushGoTo;
    public final MenuItem pushPaste;
    public final MenuItem pushRedo;
    public final MenuItem saveMenuItem;
    public final MenuItem saveAsMenuItem;
    public final MenuItem saveSelectionAsMenuItem;
    public final MenuItem pushSelectBlock;
    public final MenuItem pushSelectAll;
    public final MenuItem pushTrim;
    public final MenuItem pushUndo;

    public final MenuItem helpContentsMenuItem;
    
    public HexEditorMenu(HexEditor hexEditor) {
	this.hexEditor = hexEditor;
	menuBar = new Menu(hexEditor.shell, SWT.BAR);

	// File menu
	MenuItem fileMenuItem = new MenuItem(menuBar, SWT.CASCADE);
	fileMenuItem.setText(Texts.HEX_EDITOR_FILE_MENU_ITEM_LABEL);
	fileSubMenu = new Menu(fileMenuItem);
	MenuItem newMenuItem = createMenuItem(fileSubMenu,
		Texts.HEX_EDITOR_NEW_MENU_ITEM_LABEL, Actions.NEW);
	newMenuItem.setAccelerator(SWT.CONTROL | 'N');
	MenuItem pushOpen = createMenuItem(fileSubMenu,
		Texts.HEX_EDITOR_OPEN_MENU_ITEM_LABEL, Actions.OPEN);
	pushOpen.setAccelerator(SWT.CONTROL | 'O');
	createMenuSeparator(fileSubMenu);
	saveMenuItem = createMenuItem(fileSubMenu,
		Texts.HEX_EDITOR_SAVE_MENU_ITEM_LABEL, Actions.SAVE);
	saveMenuItem.setAccelerator(SWT.CONTROL | 'S');
	saveAsMenuItem = createMenuItem(fileSubMenu,
		Texts.HEX_EDITOR_SAVE_AS_MENU_ITEM_LABEL, Actions.SAVE_AS);
	saveSelectionAsMenuItem = createMenuItem(fileSubMenu,
		Texts.HEX_EDITOR_SAVE_SELECTION_AS_MENU_ITEM_LABEL,
		Actions.SAVE_SELECTION_AS);
	createMenuSeparator(fileSubMenu);
	createMenuItem(fileSubMenu, Texts.HEX_EDITOR_EXIT_MENU_ITEM_LABEL,
		Actions.EXIT);

	fileMenuItem.setMenu(fileSubMenu);

	// Edit menu
	MenuItem editMenuItem = new MenuItem(menuBar, SWT.CASCADE);
	editMenuItem.setText(Texts.HEX_EDITOR_EDIT_MENU_ITEM_LABEL);
	editSubMenu = new Menu(editMenuItem);
	pushUndo = createMenuItem(editSubMenu,
		Texts.HEX_EDITOR_UNDO_MENU_ITEM_LABEL, Actions.UNDO);

	pushRedo = createMenuItem(editSubMenu,
		Texts.HEX_EDITOR_REDO_MENU_ITEM_LABEL, Actions.REDO);

	createMenuSeparator(editSubMenu);
	pushCut = createMenuItem(editSubMenu,
		Texts.HEX_EDITOR_CUT_MENU_ITEM_LABEL, Actions.CUT);

	pushCopy = createMenuItem(editSubMenu,
		Texts.HEX_EDITOR_COPY_MENU_ITEM_LABEL, Actions.COPY);

	pushPaste = createMenuItem(editSubMenu,
		Texts.HEX_EDITOR_PASTE_MENU_ITEM_LABEL, Actions.PASTE);

	createMenuSeparator(editSubMenu);
	pushDelete = createMenuItem(editSubMenu,
		Texts.HEX_EDITOR_DELETE_MENU_ITEM_LABEL, Actions.DELETE);

	pushTrim = createMenuItem(editSubMenu,
		Texts.HEX_EDITOR_TRIM_MENU_ITEM_LABEL, Actions.TRIM);

	pushSelectAll = createMenuItem(editSubMenu,
		Texts.HEX_EDITOR_SELECT_ALL_MENU_ITEM_LABEL, Actions.SELECT_ALL);

	pushSelectBlock = createMenuItem(editSubMenu,
		Texts.HEX_EDITOR_SELECT_BLOCK_MENU_ITEM_LABEL,
		Actions.SELECT_BLOCK);
	pushSelectBlock.setAccelerator(SWT.CONTROL | 'E');

	createMenuSeparator(editSubMenu);
	pushGoTo = createMenuItem(editSubMenu,
		Texts.HEX_EDITOR_GO_TO_MENU_ITEM_LABEL, Actions.GO_TO);
	pushGoTo.setAccelerator(SWT.CONTROL | 'L');

	pushFind = createMenuItem(editSubMenu,
		Texts.HEX_EDITOR_FIND_MENU_ITEM_LABEL, Actions.FIND);
	pushFind.setAccelerator(SWT.CONTROL | 'F');

	createMenuSeparator(editSubMenu);
	createMenuItem(editSubMenu,
		Texts.HEX_EDITOR_PREFERENCES_MENU_ITEM_LABEL,
		Actions.PREFERENCES);

	editMenuItem.setMenu(editSubMenu);

	// Help menu
	MenuItem helpMenuItem = new MenuItem(menuBar, SWT.CASCADE);
	helpMenuItem.setText(Texts.HEX_EDITOR_HELP_MENU_ITEM_LABEL);
	helpSubMenu = new Menu(helpMenuItem);
	helpContentsMenuItem=createMenuItem(helpSubMenu,
		Texts.HEX_EDITOR_HELP_CONTENTS_MENU_ITEM_LABEL, Actions.HELP_CONTENTS);
	helpContentsMenuItem.setAccelerator(SWT.F1);

	createMenuItem(helpSubMenu,
		Texts.HEX_EDITOR_WEB_SITE_MENU_ITEM_LABEL,
		Actions.WEB_SITE);
	createMenuSeparator(helpSubMenu);
	createMenuItem(helpSubMenu, Texts.HEX_EDITOR_ABOUT_MENU_ITEM_LABEL,
		Actions.ABOUT);
	helpMenuItem.setMenu(helpSubMenu);
    }

    private MenuItem createMenuItem(Menu menu, String text, int actionId) {
	MenuItem result = new MenuItem(menu, SWT.PUSH);
	result.setText(text);
	result.addSelectionListener(new MySelectionAdapter(actionId));
	return result;
    }

    @SuppressWarnings("unused")
    private void createMenuSeparator(Menu menu) {
	new MenuItem(menu, SWT.SEPARATOR);
    }
}