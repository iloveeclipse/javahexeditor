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

    // File Menu
    public static String HEX_EDITOR_FILE_MENU_ITEM_LABEL;
    public static String HEX_EDITOR_EXIT_MENU_ITEM_LABEL;
    public static String HEX_EDITOR_NEW_MENU_ITEM_LABEL;
    public static String HEX_EDITOR_OPEN_MENU_ITEM_LABEL;
    public static String HEX_EDITOR_SAVE_MENU_ITEM_LABEL;
    public static String HEX_EDITOR_SAVE_AS_MENU_ITEM_LABEL;

    // Edit Menu
    public static String HEX_EDITOR_EDIT_MENU_ITEM_LABEL;
    public static String HEX_EDITOR_UNDO_MENU_ITEM_LABEL;
    public static String HEX_EDITOR_REDO_MENU_ITEM_LABEL;
    public static String HEX_EDITOR_CUT_MENU_ITEM_LABEL;
    public static String HEX_EDITOR_COPY_MENU_ITEM_LABEL;
    public static String HEX_EDITOR_PASTE_MENU_ITEM_LABEL;
    public static String HEX_EDITOR_DELETE_MENU_ITEM_LABEL;
    public static String HEX_EDITOR_SELECT_ALL_MENU_ITEM_LABEL;
    public static String HEX_EDITOR_FIND_MENU_ITEM_LABEL;
    public static String HEX_EDITOR_PREFERENCES_MENU_ITEM_LABEL;

    // Help Menu
    public static String HEX_EDITOR_HELP_MENU_ITEM_LABEL;
    public static String HEX_EDITOR_HELP_CONTENTS_MENU_ITEM_LABEL;
    public static String HEX_EDITOR_WEB_SITE_MENU_ITEM_LABEL;
    public static String HEX_EDITOR_ABOUT_MENU_ITEM_LABEL;

    // Preferences Dialog
    public static String PREFERENCES_MESSAGE_CANNOT_NOT_WRITE_FILE;

    // Error messages.
    public static String OPEN_ERROR_TITLE;
    public static String OPEN_ERROR_MESSAGE;
    public static String OPEN_ERROR_MESSAGE_CANNOT_OPEN_DROPPED_FILE;

    public static String SAVE_ON_CLOSE_TITLE;
    public static String SAVE_ON_CLOSE_MESSAGE;

    public static String SAVE_ERROR_TITLE;
    public static String SAVE_ERROR_MESSAGE;

    public static String FATAL_ERROR_TITLE;
    public static String FATAL_ERROR_MESSAGE;

    public static String OUT_OF_MEMORY_ERROR_TITLE;
    public static String OUT_OF_MEMORY_ERROR_MESSAGE;

    public static String OPEN_USER_GUIDE_ERROR_MESSAGE;

    public static String ABOUT_DIALOG_TITLE;
    public static String ABOUT_DIALOG_TEXT;

    /**
     * Initializes the constants.
     */
    static {
	NLS.initializeMessages(Texts.class.getName(), Texts.class);
    }
}