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

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sourceforge.javahexeditor.BinaryContent.Range;

/**
 * Keeps track of actions performed on a BinaryContent so they can be undone and redone.
 * Actions can be single or block deletes, inserts or overwrites.
 * Consecutive single actions are merged into a block action if they are of the same type, their data
 * is contiguous, and are performed with a time difference lower than MERGE_TIME.
 * Block actions are sequences of BinaryContent.Range. Single actions are one range of size 1.
 * @author Jordi
 */
public class ActionHistory {


/**
 * Waiting time before a single action is considered separate from the previous one.
 * Current value is 1500 milliseconds.
 */
static final int MERGE_TIME = 1500;  // milliseconds
static final Integer TYPE_DELETE = new Integer(0);
static final Integer TYPE_INSERT = new Integer(1);
static final Integer TYPE_OVERWRITE = new Integer(2);

private BinaryContent.Range actionLastRange = null;
BinaryContent content = null;
List deletedList = null;  // of Integers
boolean isBackspace = false;
ArrayList myActions = null;  // contains ArrayLists (from currentAction)
int myActionsIndex = 0;
ArrayList myCurrentAction = null;  // contains Ranges
Integer myCurrentActionType = null;
long myMergedSinglesTop = -1L;
boolean myMergingSingles = false;
long myPreviousTime = 0L;
long newRangeLength = -1L;
long newRangePosition = -1L;


/**
 * Create new action history storage object
 */
ActionHistory(BinaryContent aContent) {
	if (aContent == null)
		throw new NullPointerException("null BinaryContent");
	
	content = aContent;
	myActions = new ArrayList();
}


private long actionExclusiveEnd() {
	long result = 0L;
	if (myCurrentAction != null && myCurrentAction.size() > 0) {
		BinaryContent.Range highest =
			(BinaryContent.Range)myCurrentAction.get(myCurrentAction.size() - 1);
		result = highest.exclusiveEnd();
	}
	long newRangeExclusiveEnd = newRangePosition + newRangeLength;
	if (newRangeExclusiveEnd > result)
		result = newRangeExclusiveEnd;
	
	return result;
}


private long actionPosition() {
	long result = -1L;
	if (myCurrentAction != null && myCurrentAction.size() > 0) {
		BinaryContent.Range lowest = (BinaryContent.Range)myCurrentAction.get(0);
		result = lowest.position;
	}
	if (result < 0 || newRangePosition >= 0 && newRangePosition < result)
		result = newRangePosition;
	
	return result;
}


/**
 * Adds a list of deleted integers to the current action. If possible, merges integerList with the list
 * in the previous call to this method.
 * @param position starting delete point
 * @param integerList deleted integers
 * @param isSingle used when integerList.size == 1 to tell whether it is a single or a piece of a block
 * delete. When integerList.size() > 1 (a block delete for sure) isSingle is ignored.
 */
void addDeleted(long position, List integerList, boolean isSingle) {
	if (integerList.size() > 1L || !isSingle) {  // block delete
		BinaryContent.Range range = newRangeFromIntegerList(position, integerList);
		ArrayList oneElementList = new ArrayList();
		oneElementList.add(range);
		addLostRanges(oneElementList);
	} else {
		addLostByte(position, (Integer)integerList.get(0));
	}
	myPreviousTime = System.currentTimeMillis();
}


void addLostByte(long position, Integer integer) {
	if (deletedList == null)
		deletedList = new ArrayList();

	updateNewRange(position);
	if (isBackspace) {
		deletedList.add(0, integer);
	} else {  // delete(Del) or overwite
		deletedList.add(integer);
	}
	myPreviousTime = System.currentTimeMillis();
}


void addLostRange(BinaryContent.Range aRange) {
	if (myMergingSingles) {
		if (myMergedSinglesTop < 0L) {
			myMergedSinglesTop = aRange.exclusiveEnd();
		// merging singles shifts aRange
		} else if (myCurrentActionType == TYPE_DELETE && !isBackspace) {
			aRange.position = myMergedSinglesTop++;
		}
		myPreviousTime = System.currentTimeMillis();
	}
	mergeRange(aRange);
}


void addLostRanges(ArrayList ranges) {
	if (ranges == null)
		return;
	
	for (int i = 0; i < ranges.size(); ++i) {
		addLostRange((BinaryContent.Range)ranges.get(i));
	}
}


void addRangeToCurrentAction(BinaryContent.Range aRange) {
	if (actionPosition() <= aRange.position) {  // they're == when ending an overwrite action
		myCurrentAction.add(aRange);
	} else {
		myCurrentAction.add(0, aRange);
	}
	actionLastRange = aRange;
}


/**
 * Adds an inserted range to a new action. Does not merge Ranges nor single actions.
 * @param aRange the range being inserted
 */
void addInserted(BinaryContent.Range aRange) {
	myCurrentAction.add(aRange);
	endAction();
}


/**
 * Tells whether a redo is possible
 * @return true if something can be redone
 */
public boolean canRedo() {
	return myActionsIndex < myActions.size() && myCurrentAction == null;
}


/**
 * Tells whether an undo is possible
 * @return true if something can be undone
 */
public boolean canUndo() {
	return myCurrentAction != null || myActionsIndex > 0;
}


void dispose() {
	if (myActions != null) {
		for (Iterator i = myActions.iterator(); i.hasNext();) {
			Object[] tuple = (Object[])i.next();
			ArrayList ranges = (ArrayList)tuple[1];
			disposeRanges(ranges);
		}
	}
	disposeRanges(myCurrentAction);
}


private void disposeRanges(ArrayList ranges) {
	if (ranges == null) return;
	
	for (Iterator j = ranges.iterator(); j.hasNext();) {
		BinaryContent.Range range = (Range)j.next();
		if (range.data instanceof RandomAccessFile) {
			try {
				((RandomAccessFile)range.data).close();
			} catch (IOException e) {
				// ok, leave this file alone and close the rest
			}
		}
	}
}


/**
 * Sets the last processed action as finished. Calling this method will prevent single action merging.
 * Must be called after each block action.
 */
void endAction() {
	if (myCurrentAction == null) return;
	
	if (myMergingSingles)
		newRangeToCurrentAction();
	Object[] tuple = {myCurrentActionType, myCurrentAction};
	myActions.subList(myActionsIndex, myActions.size()).clear();
	myActions.add(tuple);
	myActionsIndex = myActions.size();
	
	isBackspace = false;
	myCurrentActionType = null;
	myCurrentAction = null;
	actionLastRange = null;
	newRangePosition = -1L;
	newRangeLength = -1L;
	myMergedSinglesTop = -1L;
}


/**
 * User event: single/block delete/insert/overwrite. Called before any change has been done
 * @param type
 * @param position
 */
void eventPreModify(Integer type, long position, boolean isSingle) {
	if (type != myCurrentActionType ||
		!isSingle ||
		System.currentTimeMillis() - myPreviousTime > MERGE_TIME ||
		(type == TYPE_INSERT || type == TYPE_OVERWRITE) && actionExclusiveEnd() != position ||
		type == TYPE_DELETE && actionPosition() != position && actionPosition() - 1L != position) {
		startAction(type, isSingle);
	} else {
		isBackspace = actionPosition() > position;
	}
	if (isSingle && type == TYPE_INSERT) {  // never calls addInserted...
		updateNewRange(position);
		myPreviousTime = System.currentTimeMillis();
	}
}


/**
 * Closes all files for termination
 * @see Object#finalize()
 */
protected void finalize() {
	dispose();
}


private void mergeRange(BinaryContent.Range aRange) {
	if (actionLastRange == null || actionLastRange.data != aRange.data) {
		newRangeToCurrentAction();
		addRangeToCurrentAction(aRange);
	} else {
		if (actionLastRange.compareTo(aRange) > 0) {
			actionLastRange.position -= aRange.length;
			actionLastRange.dataOffset -= aRange.length;
			newRangePosition = aRange.position;
		}
		actionLastRange.length += aRange.length;
	}
	if (myCurrentActionType == TYPE_OVERWRITE && myMergingSingles) {
		if (newRangePosition < 0L) {
			newRangePosition = aRange.position;
			newRangeLength = 1L;
		} else {
			++newRangeLength;
		}
	}
}


private ByteBuffer newBufferFromIntegerList(List integerList) {
	ByteBuffer store = ByteBuffer.allocate(integerList.size());
	for (Iterator iterator = integerList.iterator(); iterator.hasNext();) {
		store.put(((Integer)iterator.next()).byteValue());
	}
	store.position(0);
	
	return store;
}


private BinaryContent.Range newRangeFromIntegerList(long position, List integerList) {
	ByteBuffer store = newBufferFromIntegerList(integerList);
	
	return new BinaryContent.Range(position, store, true);
}


private void newRangeToCurrentAction() {
	BinaryContent.Range newRange = null;
	if (myCurrentActionType == TYPE_DELETE) {
		if (deletedList == null)
			return;
		
		newRange = newRangeFromIntegerList(newRangePosition, deletedList);
		deletedList = null;
	} else {  // myCurrentActionType == TYPE_INSERT || myCurrentActionType == TYPE_OVERWRITE
		if (newRangePosition < 0L)
			return;
		
		content.actionsOn(false);
		content.commitChanges();
		content.actionsOn(true);
		newRange = (BinaryContent.Range)content.getRangeAt(newRangePosition).clone();
	}
	addRangeToCurrentAction(newRange);
}


/**
 * Redoes last action on BinaryContent.
 */
Object[] redoAction() {
	if (!canRedo()) return null;
	
	return (Object[])myActions.get(myActionsIndex++);
}


/**
 * Starts the processing of a new action.
 * @param type one of TYPE_DELETE, TYPE_INSERT or TYPE_OVERWRITE
 * @param isSingle whether the action is a single byte or more
 */
private void startAction(Integer type, boolean isSingle) {
	endAction();
	myCurrentAction = new ArrayList();
	myCurrentActionType = type;
	myMergingSingles = isSingle;
}


public String toString() {
	return myActions.toString();
}


/**
 * Undoes last action on BinaryContent.
 */
Object[] undoAction() {
	if (!canUndo()) return null;
	
	endAction();
	--myActionsIndex;
	
	return (Object[])myActions.get(myActionsIndex);
}


private void updateNewRange(long position) {
	if (newRangePosition < 0L) {
		newRangePosition = position;
		newRangeLength = 1L;
	} else {
		if (newRangePosition > position) {  // Backspace (BS)
			newRangePosition = position;
		}
		++newRangeLength;
	}
}
}
