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


    public static final String textCouldNotRead = "Could not read from saved file, try reopening the editor";
    public static final String textCouldNotWriteOnFile = "Could not write on file ";
    public static final String FIND_MENU_ITEM_LABEL = "&Find/Replace...\tCtrl+F";
    public static final String textIsBeingUsed = "\nis currently being used by the editor.\n"
	    + "Cannot overwrite file.";
    public static final String textTheFile = "The file ";
    public static final String textErrorFatal = "Unexpected fatal error";
    public static final String textErrorSave = "Save error";
    public static final String textErrorOutOfMemory = "Out of memory error";

}