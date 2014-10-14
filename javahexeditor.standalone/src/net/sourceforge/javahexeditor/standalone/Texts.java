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

import org.eclipse.osgi.util.NLS;

final class Texts extends NLS {
    public static final String EMPTY = "";

    public static String HEX_EDITOR_FILE_MENU_ITEM_LABEL = "&File";
    public static String HEX_EDITOR_EDIT_MENU_ITEM_LABEL = "&Edit";

    public static String HEX_EDITOR_EXIT_MENU_ITEM_LABEL = "E&xit\tAlt+F4";
    public static String HEX_EDITOR_NEW_MENU_ITEM_LABEL = "&New\tCtrl+N";
    public static String HEX_EDITOR_OPEN_MENU_ITEM_LABEL = "&Open File...\tCtrl+O";
    public static String HEX_EDITOR_SAVE_MENU_ITEM_LABEL = "&Save\tCtrl+S";
    public static String HEX_EDITOR_SAVE_AS_MENU_ITEM_LABEL = "Save &As...";
    public static String HEX_EDITOR_SAVE_SELECTION_AS_MENU_ITEM_LABEL = "Save S&election As...";

    // Edit Menu
    public static String HEX_EDITOR_UNDO_MENU_ITEM_LABEL = "&Undo\tCtrl+Z";
    public static String HEX_EDITOR_REDO_MENU_ITEM_LABEL = "Red&o\tCtrl+Y";
    public static String HEX_EDITOR_CUT_MENU_ITEM_LABEL = "Cu&t\tCtrl+X";
    public static String HEX_EDITOR_COPY_MENU_ITEM_LABEL = "&Copy\tCtrl+C";
    public static String HEX_EDITOR_PASTE_MENU_ITEM_LABEL = "&Paste\tCtrl+V";
    public static String HEX_EDITOR_DELETE_MENU_ITEM_LABEL = "&Delete\tDelete";
    public static String HEX_EDITOR_TRIM_MENU_ITEM_LABEL = "T&rim";
    public static String HEX_EDITOR_SELECT_ALL_MENU_ITEM_LABEL = "&Select All\tCtrl+A";
    public static String HEX_EDITOR_SELECT_BLOCK_MENU_ITEM_LABEL = "Select &Block...\tCtrl+E";
    public static String HEX_EDITOR_GO_TO_MENU_ITEM_LABEL = "&Go to Location...\tCtrl+L";
    public static String HEX_EDITOR_FIND_MENU_ITEM_LABEL = "&Find/Replace...\tCtrl+F";

    public static String HEX_EDITOR_PREFERENCES_MENU_ITEM_LABEL = "Pr&eferences...";

    // Help Menu
    public static String HEX_EDITOR_HELP_MENU_ITEM_LABEL = "&Help";
    public static String HEX_EDITOR_HELP_CONTENTS_MENU_ITEM_LABEL = "&Help Contents\tF1";
    public static String HEX_EDITOR_WEB_SITE_MENU_ITEM_LABEL = "javahexeditor &Web Site";
    public static String HEX_EDITOR_ABOUT_MENU_ITEM_LABEL = "&About javahexeditor";

    // Preferences Dialog
    public static String PREFERENCES_COULD_NOT_WRITE_FILE_ERROR_MESSAGE = "Could not write file '{0}': {1}";

    // Error messages.
    public static String OPEN_ERROR_TITLE = "Open Error";
    public static String OPEN_ERROR_MESSAGE = "Cannot open the file '{0}' for reading.";

    public static String SAVE_ERROR_TITLE = "Save Error";
    public static String SAVE_ERROR_MESSAGE = "Cannot save the file '{0}': {1}";

    public static String FATAL_ERROR_TITLE = "Unexpected Fatal Error";
    public static String FATAL_ERROR_MESSAGE = "The application has crashed. Please report this stack trace to the developers:\n{0}";

    public static String OUT_OF_MEMORY_ERROR_TITLE = "Out of Memory Error";
    public static String OUT_OF_MEMORY_ERROR_MESSAGE = "The application has run out of memory.\nTry saving the current data and repeating the last action.";

    public static String OPEN_USER_GUIDE_ERROR_MESSAGE = "Error while opening user guide '{0}': {1}";

    public static String ABOUT_DIALOG_TITLE = "About javahexeditor";
    public static String ABOUT_DIALOG_TEXT = "javahexeditor, copyright (c) 2006 - 2014 Jordi Bergenthal.\n"
	    + "Released under the terms of the GNU General Public License.\n"
	    + "Visit http://javahexeditor.sourceforge.net\n"
	    + "\nContributions:\n"
	    + "Andre Bossert\t\tMenus, dialogs, status bar.\n"
	    + "Alexander Kuramshin\tCharset encoding.\n"
	    + "Peter Dell\t\t\tRefactoring, localization.\n"
	    + "Paul Fisher\t\t\tIcons.";
}