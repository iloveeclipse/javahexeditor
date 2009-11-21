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

import junit.framework.TestCase;


public class FinderTest extends TestCase {

	static final int bufferSize = Finder.mapSize * 3 / 2;
	BinaryContent content = null;
	Finder find = null;
	BinaryContent longContent = null;

	public FinderTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		content =
			new BinaryContent(new File(getClass().getResource(AllTests.resourceData).getPath()));
		File longFile = AllTests.setUpLongData(bufferSize + 1);  // MapSize + 1
		longContent = new BinaryContent(longFile);
	}

	protected void tearDown() throws Exception {
		content.dispose();
		longContent.dispose();
		AllTests.tearDownLongData();
		super.tearDown();
	}

	public final void testFindBackwards() {
		try {
			find = new Finder(new byte[]{(byte)0x0a}, content);
			assertEquals(new Long(0x0aL), find.getNextMatch()[0]);
			assertNull(find.getNextMatch());
			find.setDirectionForward(false);
			find.setNewStart(0x0c);
			assertEquals(new Long(0x0aL), find.getNextMatch()[0]);

			find = new Finder(new byte[]{(byte)0x0a}, content);
			find.setNewStart(40);
			find.setDirectionForward(false);
			assertEquals(new Long(0x0aL), find.getNextMatch()[0]);
			assertNull(find.getNextMatch());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public final void testFindModifyFind() {
		try {
			find = new Finder(new byte[]{(byte)0x0a}, content);
			assertEquals(new Long(0x0aL), find.getNextMatch()[0]);
			content.insert((byte)1, 1L);
			assertEquals(new Long(0x0bL), find.getNextMatch()[0]);
			assertNull(find.getNextMatch());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/*
	 * Test method for 'net.sourceforge.javahexeditor.Find.getNextMatch()'
	 */
	public final void testGetNextMatch() {
		try {
			find = new Finder(new byte[]{0, 2}, content);
			assertNull(find.getNextMatch());
			
			find = new Finder(new byte[]{0}, content);
			assertEquals(new Long(0L), find.getNextMatch()[0]);

			find = new Finder(new byte[]{1}, content);
			assertEquals(new Long(1L), find.getNextMatch()[0]);
			
			find = new Finder(new byte[]{(byte)254}, content);
			assertEquals(new Long(254L), find.getNextMatch()[0]);
			
			find = new Finder(new byte[]{(byte)255}, content);
			assertEquals(new Long(255L), find.getNextMatch()[0]);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public final void testIgnoreCase() {
		try {
			BinaryContent content3 = new BinaryContent(
					new File(getClass().getResource(AllTests.resourceUnicode).getPath()));
			find = new Finder("her", content3);
			find.setCaseSensitive(false);
			assertEquals(new Long(2L), find.getNextMatch()[0]);
			assertEquals(new Long(3L), find.getNextMatch()[0]);
			assertEquals(new Long(33L), find.getNextMatch()[0]);
			assertEquals(new Long(34L), find.getNextMatch()[0]);
			assertNull(find.getNextMatch());
			
			find.setNewStart(48L);
			find.setDirectionForward(false);
			Number[] result = find.getNextMatch();
			assertEquals(new Long(34L), result[0]);
			assertEquals(new Integer(6), result[1]);
			assertEquals(new Long(33L), find.getNextMatch()[0]);
			assertEquals(new Long(3L), find.getNextMatch()[0]);
			assertEquals(new Long(2L), find.getNextMatch()[0]);
			assertNull(find.getNextMatch());
			content3.dispose();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public final void testLongFind() {
		try {
			find = new Finder(new byte[]{0, 0}, longContent);
			assertEquals(new Long(0L), find.getNextMatch()[0]);
			find.setNewStart(bufferSize - 3);
			assertEquals(new Long(bufferSize - 3), find.getNextMatch()[0]);
			assertEquals(new Long(bufferSize - 2), find.getNextMatch()[0]);
			assertEquals(new Long(bufferSize - 1), find.getNextMatch()[0]);
			assertNull(find.getNextMatch());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public final void testSetNewStart() {
		try {
			find = new Finder(new byte[]{(byte)254}, content);
			find.setNewStart(0);
			assertEquals(new Long(254L), find.getNextMatch()[0]);
			find.setNewStart(0);
			assertEquals(new Long(254L), find.getNextMatch()[0]);
			find.setNewStart(254);
			assertEquals(new Long(254L), find.getNextMatch()[0]);
			find.setNewStart(255);
			assertNull(find.getNextMatch());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
