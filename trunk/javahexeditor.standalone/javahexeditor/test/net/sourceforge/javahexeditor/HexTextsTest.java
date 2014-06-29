package net.sourceforge.javahexeditor;

import java.nio.ByteBuffer;
import java.util.ArrayList;

import junit.framework.TestCase;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class HexTextsTest extends TestCase {

	BinaryContent content = null;
	Display display = null;
	Shell shell = null;
	HexTexts hexTexts = null;
	
	protected void setUp() throws Exception {
		super.setUp();
		display = Display.getDefault();
		shell = new Shell(Display.getDefault(), SWT.MODELESS | SWT.SHELL_TRIM);
		hexTexts = new HexTexts(shell, SWT.DEFAULT);
		content = new BinaryContent();
		content.insert(ByteBuffer.allocate(200), 0L);
		hexTexts.setContentProvider(content);
	}
	
	protected void tearDown() throws Exception {
		content.dispose();
		hexTexts.dispose();
		shell.dispose();
		display.dispose();
		super.tearDown();
	}

	/*
	 * Test method for 'net.sourceforge.javahexeditor.HexTexts.mergeRanges(ArrayList, int)'
	 */
	public void testMergeRanges() {
		ArrayList changes = new ArrayList();
		ArrayList highlights = new ArrayList();
		ArrayList merged = hexTexts.mergeRanges(changes, highlights);  // _ _ _ _
		assertTrue(merged == null || merged.isEmpty());
		
		changes.add(new Long(0));
		changes.add(new Long(1));
		merged = hexTexts.mergeRanges(changes, highlights);  // C _ _ _
		assertEquals(1, merged.size());
		assertEquals(0, ((StyleRange)merged.get(0)).start);
		assertEquals(1, ((StyleRange)merged.get(0)).length);
		
		changes.set(0, new Long(1));
		changes.set(1, new Long(2));
		merged = hexTexts.mergeRanges(changes, highlights);  // _ C C _
		assertEquals(1, merged.size());
		assertEquals(1, ((StyleRange)merged.get(0)).start);
		assertEquals(2, ((StyleRange)merged.get(0)).length);
		
		changes.clear();
		highlights.add(new Integer(0));
		highlights.add(new Integer(1));
		merged = hexTexts.mergeRanges(changes, highlights);  // H _ _ _
		assertEquals(1, merged.size());
		assertEquals(0, ((StyleRange)merged.get(0)).start);
		assertEquals(1, ((StyleRange)merged.get(0)).length);
		
		highlights.set(0, new Integer(1));
		highlights.set(1, new Integer(2));
		merged = hexTexts.mergeRanges(changes, highlights);  // _ H H _
		assertEquals(1, merged.size());
		assertEquals(1, ((StyleRange)merged.get(0)).start);
		assertEquals(2, ((StyleRange)merged.get(0)).length);
		
		changes.add(new Long(0));
		changes.add(new Long(1));
		merged = hexTexts.mergeRanges(changes, highlights);  // C H H _
		assertEquals(2, merged.size());
		assertEquals(0, ((StyleRange)merged.get(0)).start);
		assertEquals(1, ((StyleRange)merged.get(0)).length);
		assertEquals(1, ((StyleRange)merged.get(1)).start);
		assertEquals(2, ((StyleRange)merged.get(1)).length);
		
		changes.set(0, new Long(3));
		changes.set(1, new Long(1));
		merged = hexTexts.mergeRanges(changes, highlights);  // _ H H C _ 
		assertEquals(2, merged.size());
		assertEquals(1, ((StyleRange)merged.get(0)).start);
		assertEquals(2, ((StyleRange)merged.get(0)).length);
		assertEquals(3, ((StyleRange)merged.get(1)).start);
		assertEquals(1, ((StyleRange)merged.get(1)).length);
		
		changes.set(0, new Long(4));
		changes.set(1, new Long(2));
		merged = hexTexts.mergeRanges(changes, highlights);  // _ H H _ C C _
		assertEquals(2, merged.size());
		assertEquals(1, ((StyleRange)merged.get(0)).start);
		assertEquals(2, ((StyleRange)merged.get(0)).length);
		assertEquals(4, ((StyleRange)merged.get(1)).start);
		assertEquals(2, ((StyleRange)merged.get(1)).length);
		
		changes.set(0, new Long(1));
		changes.set(1, new Long(2));
		merged = hexTexts.mergeRanges(changes, highlights);  // _ CH CH _
		assertEquals(1, merged.size());
		assertEquals(1, ((StyleRange)merged.get(0)).start);
		assertEquals(2, ((StyleRange)merged.get(0)).length);
		
		changes.set(0, new Long(1));
		changes.set(1, new Long(1));
		merged = hexTexts.mergeRanges(changes, highlights);  // _ CH H _
		assertEquals(2, merged.size());
		assertEquals(1, ((StyleRange)merged.get(0)).start);
		assertEquals(1, ((StyleRange)merged.get(0)).length);
		assertEquals(2, ((StyleRange)merged.get(1)).start);
		assertEquals(1, ((StyleRange)merged.get(1)).length);
		
		changes.set(0, new Long(2));
		changes.set(1, new Long(1));
		merged = hexTexts.mergeRanges(changes, highlights);  // _ H CH _
		assertEquals(2, merged.size());
		assertEquals(1, ((StyleRange)merged.get(0)).start);
		assertEquals(1, ((StyleRange)merged.get(0)).length);
		assertEquals(2, ((StyleRange)merged.get(1)).start);
		assertEquals(1, ((StyleRange)merged.get(1)).length);
		
		changes.set(0, new Long(2));
		changes.set(1, new Long(2));
		merged = hexTexts.mergeRanges(changes, highlights);  // _ H CH C _
		assertEquals(3, merged.size());
		assertEquals(1, ((StyleRange)merged.get(0)).start);
		assertEquals(1, ((StyleRange)merged.get(0)).length);
		assertEquals(2, ((StyleRange)merged.get(1)).start);
		assertEquals(1, ((StyleRange)merged.get(1)).length);
		assertEquals(3, ((StyleRange)merged.get(2)).start);
		assertEquals(1, ((StyleRange)merged.get(2)).length);
		
		highlights.set(1, new Integer(4));
		merged = hexTexts.mergeRanges(changes, highlights);  // _ H CH CH H _
		assertEquals(3, merged.size());
		assertEquals(1, ((StyleRange)merged.get(0)).start);
		assertEquals(1, ((StyleRange)merged.get(0)).length);
		assertEquals(2, ((StyleRange)merged.get(1)).start);
		assertEquals(2, ((StyleRange)merged.get(1)).length);
		assertEquals(4, ((StyleRange)merged.get(2)).start);
		assertEquals(1, ((StyleRange)merged.get(2)).length);
		
		highlights.set(0, new Integer(2));
		merged = hexTexts.mergeRanges(changes, highlights);  // _ _ CH CH H H _
		assertEquals(2, merged.size());
		assertEquals(2, ((StyleRange)merged.get(0)).start);
		assertEquals(2, ((StyleRange)merged.get(0)).length);
		assertEquals(4, ((StyleRange)merged.get(1)).start);
		assertEquals(2, ((StyleRange)merged.get(1)).length);
	}
}
