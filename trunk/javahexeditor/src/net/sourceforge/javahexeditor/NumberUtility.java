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

/**
 * Utility to handle number conversions.
 * 
 * @author Peter Dell
 * 
 */
public final class NumberUtility {

    private static String hexPrefix;

    static {
	hexPrefix = "0x";
    }

    /**
     * Creation is private.
     */
    private NumberUtility() {

    }

    public static void setHexPrefix(String hexPrefix) {
	if (hexPrefix == null) {
	    throw new IllegalArgumentException(
		    "Parameter 'hexPrefix' must not be null.");
	}
	hexPrefix = hexPrefix.trim();
	if (hexPrefix.length() == 0) {
	    throw new IllegalArgumentException(
		    "Parameter 'hexPrefix' must not be empty.");
	}
	NumberUtility.hexPrefix = hexPrefix;
    }

    public static String getDecimalString(long value) {
	return Long.toString(value);
    }

    public static String getHexString(long value) {
	return hexPrefix + Long.toHexString(value).toUpperCase();
    }

    public static String getDecimalAndHexString(long value) {
	return getDecimalString(value) + " (" + getHexString(value) + ")";
    }

    public static String getDecimalAndHexRangeString(long from, long to) {
	return getDecimalString(from) + " - " + getDecimalString(to) + " ("
		+ getHexString(from) + " - " + getHexString(to) + ")";
    }
}
