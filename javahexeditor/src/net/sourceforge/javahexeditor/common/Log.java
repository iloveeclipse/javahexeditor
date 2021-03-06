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
package net.sourceforge.javahexeditor.common;

import net.sourceforge.javahexeditor.plugin.HexEditorPlugin;

/**
 * Utility class to issue log messages.
 *
 * @author Peter Dell
 */
public final class Log {

    static final boolean DEBUG = HexEditorPlugin.getDefault().isDebugging();

    /**
     * Creation is private.
     */
    private Log() {
    }

    public static void logError(String message, Object[] parameters,
            Throwable th) {
        if (message == null) {
            throw new IllegalArgumentException(
                    "Parameter 'message' must not be null.");
        }
        String m = createMessage("ERROR: ", message, parameters);
        HexEditorPlugin.logError(m, th);
    }

    public static void trace(Object owner, String message, Object... parameters) {
        if(DEBUG) {
            String m = createMessage(owner, message, parameters);
            System.out.println(m);
        }
    }

    private static String createMessage(Object owner, String message, Object... parameters) {
        if (message == null) {
            message = "";
        }
        String[] stringParameters = null;
        if (parameters != null) {
            stringParameters = new String[parameters.length];
            for (int i = 0; i < parameters.length; i++) {
                stringParameters[i] = String.valueOf(parameters[i]);
            }
        }
        return owner + ":" + TextUtility.format(message, stringParameters);
    }

}
