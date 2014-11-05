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

import org.eclipse.osgi.util.NLS;

public final class Texts extends NLS {
    public static final String EMPTY = "";

    public static String FILE_READ_ERROR_TITLE;
    public static String FILE_READ_ERROR_MESSAGE;

    public static String BUTTON_OK_LABEL;
    public static String BUTTON_RESET_LABEL;
    public static String BUTTON_CANCEL_LABEL;
    public static String BUTTON_CLOSE_LABEL;

    public static String DIALOG_ERROR_NOT_A_NUMBER_MESSAGE;
    public static String DIALOG_ERROR_LOCATION_OUT_OF_RANGE_MESSAGE;
    public static String DIALOG_ERROR_END_SMALLER_THAN_OR_EQUAL_TO_START_MESSAGE;

    // Editor
    public static String EDITOR_MESSAGE_SAVING_FILE_PLEASE_WAIT;
    public static String EDITOR_MESSAGE_FILE_SAVED;

    public static String EDITOR_MENU_SAVE_SELECTION_AS_LABEL;
    public static String EDITOR_MENU_TRIM_LABEL;
    public static String EDITOR_MENU_SELECT_BLOCK_LABEL;
    public static String EDITOR_MENU_GOTO_LINE_LABEL;

    // Find Replace Dialog
    public static String FIND_REPLACE_DIALOG_TITLE;
    public static String FIND_REPLACE_DIALOG_FIND_GROUP_LABEL;
    public static String FIND_REPLACE_DIALOG_REPLACE_GROUP_LABEL;
    public static String FIND_REPLACE_DIALOG_HEX_RADIO_LABEL;
    public static String FIND_REPLACE_DIALOG_TEXT_RADIO_LABEL;

    public static String FIND_REPLACE_DIALOG_DIRECTION_GROUP_LABEL;
    public static String FIND_REPLACE_DIALOG_DIRECTION_BACKWARD_RADIO_LABEL;
    public static String FIND_REPLACE_DIALOG_DIRECTION_FORWARD_RADIO_LABEL;
    public static String FIND_REPLACE_DIALOG_IGNORE_CASE_CHECKBOX_LABEL;

    public static String FIND_REPLACE_DIALOG_FIND_BUTTON_LABEL;
    public static String FIND_REPLACE_DIALOG_REPLACE_BUTTON_LABEL;
    public static String FIND_REPLACE_DIALOG_REPLACE_ALL_BUTTON_LABEL;

    public static String FIND_REPLACE_DIALOG_MESSAGE_SPECIFY_VALUE_TO_FIND;
    public static String FIND_REPLACE_DIALOG_MESSAGE_SEARCHING;
    public static String FIND_REPLACE_DIALOG_STOP_SEARCHING_BUTTON_LABEL;

    public static String FIND_REPLACE_DIALOG_MESSAGE_FOUND;
    public static String FIND_REPLACE_DIALOG_MESSAGE_NOT_FOUND;
    public static String FIND_REPLACE_DIALOG_MESSAGE_ERROR_WHILE_FINDING;

    public static String FIND_REPLACE_DIALOG_MESSAGE_ONE_REPLACEMENT;
    public static String FIND_REPLACE_DIALOG_MESSAGE_MANY_REPLACEMENTS;
    public static String FIND_REPLACE_DIALOG_MESSAGE_ERROR_WHILE_REPLACING;

    // GoTo Dialog
    public static String GOTO_DIALOG_MESSAGE_ENTER_LOCATION;
    public static String GOTO_DIALOG_SHOW_LOCATION_BUTTON_LABEL;
    public static String GOTO_DIALOG_GOTO_LOCATION_BUTTON_LABEL;
    public static String GOTO_DIALOG_GOTO_LOCATION_SHELL_LABEL;
    public static String GOTO_DIALOG_HEX;
    public static String GOTO_DIALOG_DECIMAL;

    // Hex Texts
    public static String HEX_TEXTS_TITLE_INCONSISTENT_CLIPBOARD_FILES;
    public static String HEX_TEXTS_MESSAGE_INCONSISTENT_CLIPBOARD_FILES;

    // Manager
    public static String MANAGER_SAVE_DIALOG_TITLE_SAVE_SELECTION_AS;
    public static String MANAGER_SAVE_DIALOG_TITLE_SAVE_AS;
    public static String MANAGER_SAVE_DIALOG_TITLE_FILE_ALREADY_EXISTS;
    public static String MANAGER_SAVE_DIALOG_MESSAGE_FILE_ALREADY_EXISTS;

    public static String MANAGER_SAVE_MESSAGE_COULD_NOT_WRITE_ON_TEMP_FILE;
    public static String MANAGER_SAVE_MESSAGE_COULD_NOT_SAVE_FILE;
    public static String MANAGER_SAVE_MESSAGE_COULD_NOT_READ_FROM_SAVED_FILE;
    public static String MANAGER_SAVE_MESSAGE_COULD_OVERWRITE_FILE;
    public static String MANAGER_SAVE_MESSAGE_CANNOT_BE_OVERWRITTEN;
    public static String MANAGER_SAVE_MESSAGE_COULD_NOT_CREATE_TEMP_FILE_WITH_UNIQUE_NAME;

    // Preferences Manager
    public static String PREFERENCES_MANAGER_DIALOG_TITLE;

    public static String PREFERENCES_MANAGER_SAMPLE_TEXT;
    public static String PREFERENCES_MANAGER_FONT_SELECTION_TITLE;
    public static String PREFERENCES_MANAGER_FONT_NAME;
    public static String PREFERENCES_MANAGER_FONT_STYLE;
    public static String PREFERENCES_MANAGER_FONT_STYLE_BOLD;
    public static String PREFERENCES_MANAGER_FONT_STYLE_BOLD_ITALIC;
    public static String PREFERENCES_MANAGER_FONT_STYLE_ITALIC;
    public static String PREFERENCES_MANAGER_FONT_STYLE_REGULAR;
    public static String PREFERENCES_MANAGER_FONT_SIZE;
    public static String PREFERENCES_MANAGER_DEFAULT_FONT_NAME;

    // Selection Block Dialog
    public static String SELECTION_BLOCK_DIALOG_TITLE;
    public static String SELECTION_BLOCK_DIALOG_DEC_LABEL;
    public static String SELECTION_BLOCK_DIALOG_HEX_LABEL;
    public static String SELECTION_BLOCK_DIALOG_SELECT_BUTTON_LABEL;
    public static String SELECTION_BLOCK_DIALOG_RANGE_LABEL;

    // Status Line
    public static String STATUS_LINE_MESSAGE_SELECTION;
    public static String STATUS_LINE_MESSAGE_OFFSET;
    public static String STATUS_LINE_MESSAGE_VALUE;

    public static String STATUS_LINE_MODE_INSERT;
    public static String STATUS_LINE_MODE_OVERWRITE;

    /**
     * Initializes the constants.
     */
    static {
	NLS.initializeMessages(Texts.class.getName(), Texts.class);
    }
}