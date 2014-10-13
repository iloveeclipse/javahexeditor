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

    public static String FILE_READ_ERROR_TITLE = "File Read Error";
    public static final String FILE_READ_ERROR_MESSAGE = "The file {0}\n cannot be opened for reading: {1}";

    public static String SAVE_DIALOG_TITLE_SAVE_SELECTION_AS = "Save Selection As";
    public static String SAVE_DIALOG_TITLE_SAVE_AS = "Save As";
    public static String SAVE_DIALOG_TITLE_FILE_ALREADY_EXISTS = "File already exists";
    public static String SAVE_DIALOG_MESSAGE_FILE_ALREADY_EXISTS = "The file {0} already exists.\nOverwrite file?";

    public static String BUTTON_OK_LABEL = "OK";
    public static String BUTTON_DEFAULT_LABEL = "Default";
    public static String BUTTON_CANCEL_LABEL = "Cancel";
    public static String BUTTON_CLOSE_LABEL = "Close";

    public static String PREFERENCES_MANAGER_DIALOG_TITLE = "Font preferences";
    public static String PREFERENCES_MANAGER_BOLD = "Bold";
    public static String PREFERENCES_MANAGER_BOLD_ITALIC = "Bold Italic";
    public static String PREFERENCES_MANAGER_ITALIC = "Italic";
    public static String PREFERENCES_MANAGER_REGULAR = "Regular";
    public static String PREFERENCES_MANAGER_SAMPLE_TEXT = "ca fe ba be 00 00 01 2d";

    public static String DIALOG_ERROR_NOT_A_NUMBER_MESSAGE = "Not a number";
    public static String DIALOG_ERROR_LOCATION_OUT_OF_RANGE_MESSAGE = "Location out of range";
    public static String DIALOG_ERROR_END_SMALLER_THAN_OR_EQUAL_TO_START_MESSAGE = "End smaller than or equal to start";
    public static String DIALOG_ERROR_INCONSISTENT_CLIPBOARD_FILES_TITLE = "Inconsistent clipboard files";
    public static String DIALOG_ERROR_INCONSISTENT_CLIPBOARD_FILES_MESSAGE = "Could not cleanup temporary clipboard files.\nClipboard files are stored in your temporary directory '{0}' as '{1}' and '{2}'";

    public static String GOTO_DIALOG_MESSAGE_ENTER_LOCATION = "Enter location number, {0}:";

    public static String GOTO_DIALOG_SHOW_LOCATION_BUTTON_LABEL = "Show location";
    public static String GOTO_DIALOG_GOTO_LOCATION_BUTTON_LABEL = "Go to location";
    public static String GOTO_DIALOG_GOTO_LOCATION_SHELL_LABEL = "Go to location";
    public static String GOTO_DIALOG_HEX = "Hex";
    public static String GOTO_DIALOG_DECIMAL = "Decimal";

    public static String FIND_REPLACE_DIALOG_TITLE = "Find/Replace";
    public static String FIND_REPLACE_DIALOG_FIND_GROUP_LABEL = "Find";
    public static String FIND_REPLACE_DIALOG_REPLACE_GROUP_LABEL = "Replace With";
    public static String FIND_REPLACE_DIALOG_HEX_RADIO_LABEL = "Hex";
    public static String FIND_REPLACE_DIALOG_TEXT_RADIO_LABEL = "Text";

    public static String FIND_REPLACE_DIALOG_DIRECTION_GROUP_LABEL = "Direction";
    public static String FIND_REPLACE_DIALOG_DIRECTION_BACKWARD_RADIO_LABEL = "&Backward";
    public static String FIND_REPLACE_DIALOG_DIRECTION_FORWARD_RADIO_LABEL = "F&orward";
    public static String FIND_REPLACE_DIALOG_IGNORE_CASE_CHECKBOX_LABEL = "&Ignore case";

    public static String FIND_REPLACE_DIALOG_FIND_BUTTON_LABEL = "Fi&nd";
    public static String FIND_REPLACE_DIALOG_REPLACE_BUTTON_LABEL = "&Replace";
    public static String FIND_REPLACE_DIALOG_REPLACE_ALL_BUTTON_LABEL = "Replace &All";
    public static String FIND_REPLACE_DIALOG_REPLACE_FIND_BUTTON_LABEL = "Replace/Fin&d";

    public static String FIND_REPLACE_DIALOG_MESSAGE_SPECIFY_VALUE_TO_FIND = "Specify the value to find";
    public static String FIND_REPLACE_DIALOG_MESSAGE_SEARCHING = "Searching";
    public static String FIND_REPLACE_DIALOG_STOP_SEARCHING_BUTTON_LABEL = "Stop";

    public static String FIND_REPLACE_DIALOG_MESSAGE_FOUND = "'{0}' found at position {1}";
    public static String FIND_REPLACE_DIALOG_MESSAGE_NOT_FOUND = "'{0}' not found";
    public static String FIND_REPLACE_DIALOG_MESSAGE_ERROR_WHILE_FINDING = "Error file finding '{0}': {1}";

    public static String FIND_REPLACE_DIALOG_MESSAGE_ONE_REPLACEMENT = "1 occurance of '{0}' replaced with '{1}' at position {2}";
    public static String FIND_REPLACE_DIALOG_MESSAGE_MANY_REPLACEMENTS = "{0} occurances of '{1}' replaced with '{2}'";
    public static String FIND_REPLACE_DIALOG_MESSAGE_ERROR_WHILE_REPLACING = "Error file replacing '{0}' with '{1}': {2}";

    public static String MANAGER_MESSAGE_COULD_NOT_WRITE_ON_TEMP_FILE = "Could not write on temp file '{0}'";
    public static String MANAGER_MESSAGE_COULD_NOT_SAVE_FILE = "Could save file '{0}': {1}";
    public static String MANAGER_MESSAGE_COULD_NOT_READ_FROM_SAVED_FILE = "Could not read from saved file '{0}': {1}. Try reopening the editor";
    public static String MANAGER_MESSAGE_COULD_OVERWRITE_FILE = "Could not overwrite file '{0}', a temporary copy can be found in file '{1}'";
    public static String MANAGER_MESSAGE_CANNOT_BE_OVERWRITTEN = "{0} is currently being used and cannot be overwritten";
    public static String MANAGER_MESSAGE_COULD_NOT_CREATE_TEMP_FILE_WITH_UNIQUE_NAME="Could not create temporary file with a unique name";
    
    public static String EDITOR_MESSAGE_SAVING_FILE_PLEASE_WAIT = "Saving file, please wait";
    public static String EDITOR_MESSAGE_FILE_SAVED = "{0} saved";

    public static String EDITOR_MENU_SAVE_SELECTION_AS_LABEL = "Save Selection As...";
    public static String EDITOR_MENU_TRIM_LABEL = "Trim";
    public static String EDITOR_MENU_SELECT_BLOCK_LABEL = "Select &Block...\tCtrl+E";
    public static String EDITOR_MENU_GOTO_LINE_LABEL = "Go to &Location...\tCtrl+L";

    public static String STATUS_LINE_MESSAGE_SELECTION = "Selection: {0}";
    public static String STATUS_LINE_MESSAGE_OFFSET = "Offset: {0}";
    public static String STATUS_LINE_MESSAGE_VALUE = "Value: {0} = {1} = {2}";

    public static String STATUS_LINE_MODE_INSERT = "Insert";
    public static String STATUS_LINE_MODE_OVERWRITE = "Overwrite";

}