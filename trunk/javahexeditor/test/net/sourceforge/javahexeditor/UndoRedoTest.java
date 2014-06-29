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
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

public final class UndoRedoTest extends TestCase {

	private BinaryContent content;
	private ByteBuffer data;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		content = new BinaryContent(new File(getClass().getResource(
				AllTests.resourceData).getPath()));
		content.insert(ByteBuffer.wrap(new byte[] { 88, 77, 66 }), 128L);
		content.setActionsHistory();
		data = ByteBuffer.allocate(8);
	}

	@Override
	protected void tearDown() throws Exception {
		content.dispose();
		data = null;
		super.tearDown();
	}

	/*
	 * Test method for 'net.sourceforge.javahexeditor.BinaryContent.delete(long,
	 * long)'
	 */
	public void testSingleDelete() {
		try {
			assertFalse(content.canUndo());
			assertFalse(content.canRedo());

			content.delete(0, 1);
			assertTrue(content.canUndo());
			assertFalse(content.canRedo());

			long[] result = content.undo();
			assertFalse(content.canUndo());
			assertTrue(content.canRedo());
			assertEquals(0L, result[0]);
			assertEquals(1L, result[1]);

			assertEquals(8, content.get(data, 0));
			assertEquals(0, data.get(0));
			assertEquals(1, data.get(1));

			result = content.redo();
			assertTrue(content.canUndo());
			assertFalse(content.canRedo());
			assertEquals(0L, result[0]);
			assertEquals(0L, result[1]);

			data.position(0);
			assertEquals(8, content.get(data, 0));
			assertEquals(1, data.get(0));
			assertEquals(2, data.get(1));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void testSingleDeleteManyRanges() {
		try {
			content.delete(127, 1);
			content.delete(127, 1);
			long[] result = content.undo();
			assertEquals(127L, result[0]);
			assertEquals(129L, result[1]);
			assertEquals(8, content.get(data, 126));
			assertEquals(126, data.get(0));
			assertEquals(127, data.get(1));
			assertEquals(88, data.get(2));
			assertEquals(77, data.get(3));

			result = content.redo();
			assertEquals(127L, result[0]);
			assertEquals(127L, result[1]);
			data.position(0);
			assertEquals(8, content.get(data, 126));
			assertEquals(126, data.get(0));
			assertEquals(77, data.get(1));

			content.delete(126, 1);
			result = content.undo();
			assertEquals(126L, result[0]);
			assertEquals(127L, result[1]);
			data.position(0);
			assertEquals(8, content.get(data, 125));
			assertEquals(125, data.get(0));
			assertEquals(126, data.get(1));
			assertEquals(77, data.get(2));
			assertEquals(66, data.get(3));
			result = content.redo();
			assertEquals(126L, result[0]);
			assertEquals(126L, result[1]);
			data.position(0);
			assertEquals(8, content.get(data, 125));
			assertEquals(125, data.get(0));
			assertEquals(77, data.get(1));
			assertEquals(66, data.get(2));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void testBlockDelete() {
		try {
			content.delete(127, 2);
			long[] result = content.undo();
			assertEquals(127L, result[0]);
			assertEquals(129L, result[1]);
			assertEquals(8, content.get(data, 126));
			assertEquals(126, data.get(0));
			assertEquals(127, data.get(1));
			assertEquals(88, data.get(2));
			assertEquals(77, data.get(3));

			result = content.redo();
			assertEquals(127L, result[0]);
			assertEquals(127L, result[1]);
			data.position(0);
			assertEquals(8, content.get(data, 126));
			assertEquals(126, data.get(0));
			assertEquals(77, data.get(1));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void testBlockDeleteAfterSingleInserts() {
		try {
			content.insert((byte) 44, 127); // 125, 126, 44, 127, 88, 77, 66,
			// 128
			content.delete(126, 3); // 125, 88, 77, 66, 128
			long[] result = content.undo(); // 125, 126, 44, 127, 88, 77, 66,
			// 128
			assertEquals(126L, result[0]);
			assertEquals(129L, result[1]);
			assertEquals(8, content.get(data, 125));
			assertEquals(125, data.get(0));
			assertEquals(126, data.get(1));
			assertEquals(44, data.get(2));
			assertEquals(127, data.get(3));
			assertEquals(88, data.get(4));
			assertEquals(77, data.get(5));

			result = content.redo(); // 125, 88, 77, 66, 128
			assertEquals(126L, result[0]);
			assertEquals(126L, result[1]);
			data.position(0);
			assertEquals(8, content.get(data, 125));
			assertEquals(125, data.get(0));
			assertEquals(88, data.get(1));
			assertEquals(77, data.get(2));
			assertEquals(66, data.get(3));
			assertEquals((byte) 128, data.get(4));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void testSigleDeletesToTheRightAfterSingleInserts() {
		try {
			content.insert((byte) 44, 127); // 124, 125, 126, 44, 127, 88, 77,
			// 66, 128
			content.delete(125, 1); // 124, 126, 44, 127, 88, 77, 66, 128
			content.delete(125, 1); // 124, 44, 127, 88, 77, 66, 128
			content.delete(125, 1); // 124, 127, 88, 77, 66, 128
			long[] result = content.undo(); // 124, 125, 126, 44, 127, 88, 77,
			// 66, 128
			assertEquals(125L, result[0]);
			assertEquals(128L, result[1]);
			assertEquals(8, content.get(data, 124));
			assertEquals(124, data.get(0));
			assertEquals(125, data.get(1));
			assertEquals(126, data.get(2));
			assertEquals(44, data.get(3));
			assertEquals(127, data.get(4));
			assertEquals(88, data.get(5));
			assertEquals(77, data.get(6));

			result = content.redo(); // 124, 127, 88, 77, 66, 128
			assertEquals(125L, result[0]);
			assertEquals(125L, result[1]);
			data.position(0);
			assertEquals(8, content.get(data, 124));
			assertEquals(124, data.get(0));
			assertEquals(127, data.get(1));
			assertEquals(88, data.get(2));
			assertEquals(77, data.get(3));
			assertEquals(66, data.get(4));
			assertEquals((byte) 128, data.get(5));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void testSigleDeletesToTheLeftAfterSingleInserts() {
		try {
			content.insert((byte) 44, 127); // 124, 125, 126, 44, 127, 88, 77,
			// 66, 128
			content.delete(127, 1); // 124, 125, 126, 127, 88, 77, 66, 128
			content.delete(126, 1); // 124, 125, 127, 88, 77, 66, 128
			content.delete(125, 1); // 124, 127, 88, 77, 66, 128
			long[] result = content.undo(); // 124, 125, 126, 44, 127, 88, 77,
			// 66, 128
			assertEquals(125L, result[0]);
			assertEquals(128L, result[1]);
			assertEquals(8, content.get(data, 124));
			assertEquals(124, data.get(0));
			assertEquals(125, data.get(1));
			assertEquals(126, data.get(2));
			assertEquals(44, data.get(3));
			assertEquals(127, data.get(4));
			assertEquals(88, data.get(5));
			assertEquals(77, data.get(6));

			result = content.redo(); // 124, 127, 88, 77, 66, 128
			assertEquals(125L, result[0]);
			assertEquals(125L, result[1]);
			data.position(0);
			assertEquals(8, content.get(data, 124));
			assertEquals(124, data.get(0));
			assertEquals(127, data.get(1));
			assertEquals(88, data.get(2));
			assertEquals(77, data.get(3));
			assertEquals(66, data.get(4));
			assertEquals((byte) 128, data.get(5));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void test2SingleInserts1SingleDelete() {
		try {
			content.insert((byte) 44, 127); // 125, 126, 44, 127, 88, 77, 66,
			// 128
			content.insert((byte) 45, 128); // 125, 126, 44, 45, 127, 88, 77,
			// 66, 128
			content.delete(128, 1); // 125, 126, 44, 127, 88, 77, 66, 128
			long[] result = content.undo(); // 125, 126, 44, 45, 127, 88, 77,
			// 66, 128
			assertEquals(128L, result[0]);
			assertEquals(129L, result[1]);
			data.position(0);
			assertEquals(8, content.get(data, 127));
			assertEquals(44, data.get(0));
			assertEquals(45, data.get(1));
			assertEquals(127, data.get(2));

			result = content.redo(); // 125, 126, 44, 127, 88, 77, 66, 128
			assertEquals(128L, result[0]);
			assertEquals(128L, result[1]);
			data.position(0);
			assertEquals(8, content.get(data, 127));
			assertEquals(44, data.get(0));
			assertEquals(127, data.get(1));
			assertEquals(88, data.get(2));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void testNotContiguousDelete() {
		try {
			// 125, 126, 127, 88, 77, 66, 128
			content.delete(126, 3); // 125, 77, 66, 128, 129
			content.delete(127, 2); // 125, 77, 129
			assertEquals(8, content.get(data, 125));

			long[] result = content.undo(); // 125, 77, 66, 128, 129
			assertEquals(127L, result[0]);
			assertEquals(129L, result[1]);
			data.position(0);
			assertEquals(8, content.get(data, 125));
			assertEquals(125, data.get(0));
			assertEquals(77, data.get(1));
			assertEquals(66, data.get(2));
			assertEquals((byte) 128, data.get(3));
			assertEquals((byte) 129, data.get(4));

			result = content.undo(); // 125, 126, 127, 88, 77, 66, 128
			assertEquals(126L, result[0]);
			assertEquals(129L, result[1]);
			data.position(0);
			assertEquals(8, content.get(data, 125));
			assertEquals(125, data.get(0));
			assertEquals(126, data.get(1));
			assertEquals(127, data.get(2));
			assertEquals(88, data.get(3));
			assertEquals(77, data.get(4));
			assertEquals(66, data.get(5));
			assertEquals((byte) 128, data.get(6));

			result = content.redo(); // 125, 77, 66, 128
			assertEquals(126L, result[0]);
			assertEquals(126L, result[1]);
			data.position(0);
			assertEquals(8, content.get(data, 125));
			assertEquals(125, data.get(0));
			assertEquals(77, data.get(1));
			assertEquals(66, data.get(2));
			assertEquals((byte) 128, data.get(3));

			result = content.redo(); // 125, 77, 129
			assertEquals(127L, result[0]);
			assertEquals(127L, result[1]);
			data.position(0);
			assertEquals(8, content.get(data, 125));
			assertEquals(125, data.get(0));
			assertEquals(77, data.get(1));
			assertEquals((byte) 129, data.get(2));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void testDeleteLastChange() {
		try {
			content.insert((byte) 44, 127); // 126, 44, 127, 88, 77, 66, 128
			content.delete(127, 1); // 126, 127, 88, 77, 66, 128
			long[] result = content.undo(); // 126, 44, 127, 88, 77, 66, 128
			assertEquals(127L, result[0]);
			assertEquals(128L, result[1]);
			assertEquals(8, content.get(data, 126));
			assertEquals(126, data.get(0));
			assertEquals(44, data.get(1));
			assertEquals(127, data.get(2));
			assertEquals(88, data.get(3));
			assertEquals(77, data.get(4));
			assertEquals(66, data.get(5));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void testDeleteInsertCanUndo() {
		try {
			content.insert((byte) 44, 127); // 126, 44, 127, 88, 77, 66, 128
			content.delete(127, 1); // 126, 127, 88, 77, 66, 128
			content.insert((byte) 44, 128); // 126, 88, 44, 77
			assertTrue(content.canUndo());
			assertEquals(8, content.get(data, 126));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void testBlockInsert() {
		try {
			content.insert(
					new File(getClass().getResource(AllTests.resourceData)
							.getPath()), 127L);
			content.insert(
					new File(getClass().getResource(AllTests.resourceUnicode)
							.getPath()), 383L);
			assertTrue(content.canUndo());
			assertFalse(content.canRedo());

			long[] result = content.undo();
			assertEquals(383L, result[0]);
			assertEquals(383L, result[1]);
			assertTrue(content.canUndo());
			assertTrue(content.canRedo());
			assertEquals(8, content.get(data, 126L));
			assertEquals(126, data.get(0));
			assertEquals(0, data.get(1));
			data.position(0);
			assertEquals(8, content.get(data, 382L));
			assertEquals((byte) 255, data.get(0));
			assertEquals(127, data.get(1));

			result = content.undo();
			assertEquals(127L, result[0]);
			assertEquals(127L, result[1]);
			assertFalse(content.canUndo());
			assertTrue(content.canRedo());
			data.position(0);
			assertEquals(8, content.get(data, 126L));
			assertEquals(126, data.get(0));
			assertEquals(127, data.get(1));

			result = content.redo();
			assertEquals(127L, result[0]);
			assertEquals(383L, result[1]);
			assertTrue(content.canUndo());
			assertTrue(content.canRedo());
			data.position(0);
			assertEquals(8, content.get(data, 126L));
			assertEquals(126, data.get(0));
			assertEquals(0, data.get(1));
			data.position(0);
			assertEquals(8, content.get(data, 382L));
			assertEquals((byte) 255, data.get(0));
			assertEquals(127, data.get(1));

			result = content.redo();
			assertEquals(383L, result[0]);
			assertEquals(431L, result[1]);
			assertTrue(content.canUndo());
			assertFalse(content.canRedo());
			data.position(0);
			assertEquals(8, content.get(data, 382L));
			assertEquals((byte) 255, data.get(0));
			assertEquals((byte) 254, data.get(1));
			data.position(0);
			assertEquals(8, content.get(data, 430L));
			assertEquals(0, data.get(0));
			assertEquals(127, data.get(1));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void testSingleInsert() {
		try {
			content.insert((byte) 44, 127); // 126, 44, 127, 88, 77, 66, 128
			assertTrue(content.canUndo());
			assertFalse(content.canRedo());

			long[] result = content.undo();
			assertEquals(127L, result[0]);
			assertEquals(127L, result[1]);
			assertFalse(content.canUndo());
			assertTrue(content.canRedo());
			assertEquals(8, content.get(data, 126L));
			assertEquals(126, data.get(0));
			assertEquals(127, data.get(1));

			result = content.redo();
			assertEquals(127L, result[0]);
			assertEquals(128L, result[1]);
			assertTrue(content.canUndo());
			assertFalse(content.canRedo());
			data.position(0);
			assertEquals(8, content.get(data, 126L));
			assertEquals(126, data.get(0));
			assertEquals(44, data.get(1));
			assertEquals(127, data.get(2));

			content.insert((byte) 33, 129); // 126, 44, 127, 33, 88, 77
			content.insert((byte) 22, 130); // 126, 44, 127, 33, 22, 88, 77
			assertTrue(content.canUndo());
			assertFalse(content.canRedo());

			result = content.undo(); // 126, 44, 127, 88, 77, 66, 128
			assertEquals(129L, result[0]);
			assertEquals(129L, result[1]);
			assertTrue(content.canUndo());
			assertTrue(content.canRedo());
			data.position(0);
			assertEquals(8, content.get(data, 126));
			assertEquals(126, data.get(0));
			assertEquals(44, data.get(1));
			assertEquals(127, data.get(2));
			assertEquals(88, data.get(3));
			assertEquals(77, data.get(4));
			assertEquals(66, data.get(5));

			result = content.redo(); // 126, 44, 127, 33, 22, 88, 77
			assertEquals(129L, result[0]);
			assertEquals(131L, result[1]);
			assertTrue(content.canUndo());
			assertFalse(content.canRedo());
			data.position(0);
			assertEquals(8, content.get(data, 126));
			assertEquals(126, data.get(0));
			assertEquals(44, data.get(1));
			assertEquals(127, data.get(2));
			assertEquals(33, data.get(3));
			assertEquals(22, data.get(4));
			assertEquals(88, data.get(5));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void testInsert() {
		try {
			content = new BinaryContent();
			content.setActionsHistory();
			content.insert((byte) 0xaa, 0L);
			content.insert((byte) 0xbb, 1L);
			content.insert((byte) 0xcc, 2L);
			content.insert((byte) 0xdd, 3L);
			content.insert((byte) 0xee, 4L);
			content.insert((byte) 0xf0, 5L);

			long[] result = content.undo();
			assertEquals(0L, result[0]);
			assertEquals(0L, result[1]);
			assertEquals(0L, content.length());
			result = content.redo();
			assertEquals(0L, result[0]);
			assertEquals(6L, result[1]);
			assertEquals(6L, content.length());
			assertEquals(6, content.get(data, 0L));
			assertEquals((byte) 0xaa, data.get(0));
			assertEquals((byte) 0xbb, data.get(1));
			assertEquals((byte) 0xcc, data.get(2));
			assertEquals((byte) 0xdd, data.get(3));
			assertEquals((byte) 0xee, data.get(4));
			assertEquals((byte) 0xf0, data.get(5));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void testMixedInsertDelete() {
		try {
			content = new BinaryContent();
			content.setActionsHistory();
			content.insert((byte) 0xaa, 0L);
			content.insert((byte) 0xbb, 1L);
			content.insert((byte) 0xcc, 2L);
			content.insert((byte) 0xdd, 3L);
			content.insert((byte) 0xee, 4L);
			content.insert((byte) 0xf0, 5L);
			content.undo();
			content.redo();
			content.delete(4L, 2L);

			long[] result = content.undo();
			assertEquals(4L, result[0]);
			assertEquals(6L, result[1]);
			assertEquals(6L, content.length());

			result = content.undo();
			assertEquals(0L, result[0]);
			assertEquals(0L, result[1]);
			assertEquals(0L, content.length());

			result = content.redo();
			assertEquals(0L, result[0]);
			assertEquals(6L, result[1]);
			assertEquals(6L, content.length());
			assertEquals(6, content.get(data, 0L));
			assertEquals((byte) 0xaa, data.get(0));
			assertEquals((byte) 0xbb, data.get(1));
			assertEquals((byte) 0xcc, data.get(2));
			assertEquals((byte) 0xdd, data.get(3));
			assertEquals((byte) 0xee, data.get(4));
			assertEquals((byte) 0xf0, data.get(5));

			result = content.redo();
			assertEquals(4L, result[0]);
			assertEquals(4L, result[1]);
			assertEquals(4L, content.length());
			data.position(0);
			assertEquals(4, content.get(data, 0L));
			assertEquals((byte) 0xaa, data.get(0));
			assertEquals((byte) 0xbb, data.get(1));
			assertEquals((byte) 0xcc, data.get(2));
			assertEquals((byte) 0xdd, data.get(3));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void testSingleOverwrite() {
		try {
			content.overwrite((byte) 44, 127L); // 126, 44, 88, 77, 66, 128
			assertTrue(content.canUndo());
			assertFalse(content.canRedo());

			long[] result = content.undo(); // 126, 127, 88, 77, 66, 128
			assertEquals(127L, result[0]);
			assertEquals(128L, result[1]);
			assertFalse(content.canUndo());
			assertTrue(content.canRedo());
			assertEquals(8, content.get(data, 126L));
			assertEquals(126, data.get(0));
			assertEquals(127, data.get(1));
			assertEquals(88, data.get(2));

			result = content.redo(); // 126, 44, 88, 77, 66, 128
			assertEquals(127L, result[0]);
			assertEquals(128L, result[1]);
			assertTrue(content.canUndo());
			assertFalse(content.canRedo());
			data.position(0);
			assertEquals(8, content.get(data, 126L));
			assertEquals(126, data.get(0));
			assertEquals(44, data.get(1));
			assertEquals(88, data.get(2));

			content.overwrite((byte) 33, 129L); // 126, 44, 88, 33, 66, 128
			content.overwrite((byte) 22, 130L); // 126, 44, 88, 33, 22, 128
			assertTrue(content.canUndo());
			assertFalse(content.canRedo());

			result = content.undo(); // 126, 44, 88, 77, 66, 128
			assertEquals(129L, result[0]);
			assertEquals(131L, result[1]);
			assertTrue(content.canUndo());
			assertTrue(content.canRedo());
			data.position(0);
			assertEquals(8, content.get(data, 126));
			assertEquals(126, data.get(0));
			assertEquals(44, data.get(1));
			assertEquals(88, data.get(2));
			assertEquals(77, data.get(3));
			assertEquals(66, data.get(4));
			assertEquals((byte) 128, data.get(5));

			result = content.redo(); // 126, 44, 88, 33, 22, 128
			assertEquals(129L, result[0]);
			assertEquals(131L, result[1]);
			assertTrue(content.canUndo());
			assertFalse(content.canRedo());
			data.position(0);
			assertEquals(8, content.get(data, 126));
			assertEquals(126, data.get(0));
			assertEquals(44, data.get(1));
			assertEquals(88, data.get(2));
			assertEquals(33, data.get(3));
			assertEquals(22, data.get(4));
			assertEquals((byte) 128, data.get(5));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void testBlockOverwrite() {
		try {
			content.overwrite(ByteBuffer.wrap(new byte[] { 33, 44, 55 }), 127L); // 126,
			// 33,
			// 44,
			// 55,
			// 66
			content.overwrite(ByteBuffer.wrap(new byte[] { 0, 1, 2 }), 130L); // 126,
			// 33,
			// 44,
			// 55,
			// 0,
			// 1,
			// 2
			assertTrue(content.canUndo());
			assertFalse(content.canRedo());

			long[] result = content.undo(); // 126, 33, 44, 55, 66, 128
			assertEquals(130L, result[0]);
			assertEquals(133L, result[1]);
			assertTrue(content.canUndo());
			assertTrue(content.canRedo());
			assertEquals(8, content.get(data, 129L));
			assertEquals(55, data.get(0));
			assertEquals(66, data.get(1));
			assertEquals((byte) 128, data.get(2));

			result = content.undo(); // 126, 127, 88, 77, 66
			assertEquals(127L, result[0]);
			assertEquals(130L, result[1]);
			assertFalse(content.canUndo());
			assertTrue(content.canRedo());
			data.position(0);
			assertEquals(8, content.get(data, 126L));
			assertEquals(126, data.get(0));
			assertEquals(127, data.get(1));
			assertEquals(88, data.get(2));
			assertEquals(77, data.get(3));
			assertEquals(66, data.get(4));

			result = content.redo(); // 126, 33, 44, 55, 66, 128
			assertEquals(127L, result[0]);
			assertEquals(130L, result[1]);
			assertTrue(content.canUndo());
			assertTrue(content.canRedo());
			data.position(0);
			assertEquals(8, content.get(data, 126L));
			assertEquals(126, data.get(0));
			assertEquals(33, data.get(1));
			assertEquals(44, data.get(2));
			assertEquals(55, data.get(3));
			assertEquals(66, data.get(4));

			result = content.redo(); // 126, 33, 44, 55, 0, 1, 2, 130
			assertEquals(130L, result[0]);
			assertEquals(133L, result[1]);
			assertTrue(content.canUndo());
			assertFalse(content.canRedo());
			data.position(0);
			assertEquals(8, content.get(data, 129L));
			assertEquals(55, data.get(0));
			assertEquals(0, data.get(1));
			assertEquals(1, data.get(2));
			assertEquals(2, data.get(3));
			assertEquals((byte) 130, data.get(4));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void testNibbleInput() {
		try {
			content.overwrite((byte) 0x0b, 0, 4, 127L); // 126, 0x0bf, 88, 77,
			// 66
			content.overwrite((byte) 0x0c, 4, 4, 127L); // 126, 0x0bc, 88, 77,
			// 66
			assertTrue(content.canUndo());
			assertFalse(content.canRedo());
			assertEquals(8, content.get(data, 126L));
			assertEquals(126, data.get(0));
			assertEquals((byte) 0x0bc, data.get(1));
			assertEquals(88, data.get(2));

			long[] result = content.undo(); // 126, 127, 88, 77, 66
			assertEquals(127L, result[0]);
			assertEquals(128L, result[1]);
			assertFalse(content.canUndo());
			assertTrue(content.canRedo());
			data.position(0);
			assertEquals(8, content.get(data, 126L));
			assertEquals(126, data.get(0));
			assertEquals(127, data.get(1));
			assertEquals(88, data.get(2));

			result = content.redo(); // 126, 0x0bc, 88, 77, 66
			assertEquals(127L, result[0]);
			assertEquals(128L, result[1]);
			assertTrue(content.canUndo());
			assertFalse(content.canRedo());
			data.position(0);
			assertEquals(8, content.get(data, 126L));
			assertEquals(126, data.get(0));
			assertEquals((byte) 0x0bc, data.get(1));
			assertEquals(88, data.get(2));

			content.insert((byte) 0x0d0, 128L);
			content.overwrite((byte) 0x0e, 4, 4, 128L); // 126, 0x0bc, 0x0de,
			// 88, 77, 66
			assertTrue(content.canUndo());
			assertFalse(content.canRedo());
			data.position(0);
			assertEquals(8, content.get(data, 127L));
			assertEquals((byte) 0x0bc, data.get(0));
			assertEquals((byte) 0x0de, data.get(1));
			assertEquals(88, data.get(2));

			result = content.undo(); // 126, 0x0bc, 88, 77, 66
			assertEquals(128L, result[0]);
			assertEquals(128L, result[1]);
			assertTrue(content.canUndo());
			assertTrue(content.canRedo());
			data.position(0);
			assertEquals(8, content.get(data, 127L));
			assertEquals((byte) 0x0bc, data.get(0));
			assertEquals(88, data.get(1));
			assertEquals(77, data.get(2));

			result = content.redo(); // 126, 0x0bc, 0x0de, 88, 77, 66
			assertEquals(128L, result[0]);
			assertEquals(129L, result[1]);
			assertTrue(content.canUndo());
			assertFalse(content.canRedo());
			data.position(0);
			assertEquals(8, content.get(data, 127L));
			assertEquals((byte) 0x0bc, data.get(0));
			assertEquals((byte) 0x0de, data.get(1));
			assertEquals(88, data.get(2));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void testRedoSplitsExistingRange() {
		try {
			content.overwrite(ByteBuffer.wrap(new byte[] { 33, 44, 55 }), 127L); // 126,
			// 33,
			// 44,
			// 55,
			// 66
			content.insert((byte) 99, 129L); // 126, 33, 44, 99, 55, 66
			content.undo();
			content.undo();
			content.redo();
			long[] result = content.redo();
			assertEquals(129L, result[0]);
			assertEquals(130L, result[1]);
			assertTrue(content.canUndo());
			assertFalse(content.canRedo());
			assertEquals(8, content.get(data, 126L));
			assertEquals(126, data.get(0));
			assertEquals(33, data.get(1));
			assertEquals(44, data.get(2));
			assertEquals(99, data.get(3));
			assertEquals(55, data.get(4));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void testRedoSplitsExistingRangeAtEnd() {
		try {
			content.overwrite(ByteBuffer.wrap(new byte[] { 22, 33, 44, 55 }),
					127L); // 126, 22, 33, 44
			content.overwrite((byte) 99, 129L); // 126, 22, 33, 99, 55
			content.undo();
			content.undo();
			content.redo();
			long[] result = content.redo();
			assertEquals(129L, result[0]);
			assertEquals(130L, result[1]);
			assertTrue(content.canUndo());
			assertFalse(content.canRedo());
			assertEquals(8, content.get(data, 126L));
			assertEquals(126, data.get(0));
			assertEquals(22, data.get(1));
			assertEquals(33, data.get(2));
			assertEquals(99, data.get(3));
			assertEquals(55, data.get(4));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void testRangesModified() {
		try {
			List<Long> rangesModified = new ArrayList<Long>();
			content = new BinaryContent(new File(getClass().getResource(
					AllTests.resourceData).getPath()));
			content.setActionsHistory();
			content.overwrite((byte) 99, 128L); // 127, 99, 129
			content.undo();

			content.get(data, rangesModified, 126L);
			assertEquals(0, rangesModified.size());
			/*
			 * assertEquals(2, rangesModified.size()); assertEquals(128L,
			 * ((Long)rangesModified.get(0)).longValue()); assertEquals(1L,
			 * ((Long)rangesModified.get(0)).longValue());
			 */
			// TODO
			System.out.println(rangesModified);
			System.out.println(data.get(0) + " " + data.get(1) + " "
					+ data.get(2) + " " + data.get(3) + " " + data.get(4) + " "
					+ data.get(5) + " " + data.get(6) + " " + data.get(7));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
