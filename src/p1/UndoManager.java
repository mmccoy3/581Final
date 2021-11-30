/*
 * @(#)UndoManager.java
 *
 * Project:		JHotdraw - a GUI framework for technical drawings
 *				http://www.jhotdraw.org
 *				http://jhotdraw.sourceforge.net
 * Copyright:	� by the original author(s) and all contributors
 * License:		Lesser GNU Public License (LGPL)
 *				http://www.opensource.org/licenses/lgpl-license.html
 */

package p1;

import CH.ifa.draw.util.Undoable;

import java.util.*;

/**
 * This class manages all the undoable commands. It keeps track of all 
 * the modifications done through user interactions.
 *
 * @version <$CURRENT_VERSION$>
 */
public class UndoManager {
	/**
	 * Maximum default buffer size for undo and redo stack
	 */
	public static final int DEFAULT_BUFFER_SIZE = 20;

	/**
	 * Collection of undo activities
	 */
	private Stack<Undoable> redoStack;
		
	/**
	 * Collection of undo activities
	 */
	private Stack<Undoable> undoStack;
	private final int maxStackCapacity;
	
	public UndoManager() {
		this(DEFAULT_BUFFER_SIZE);
	}

	public UndoManager(int newUndoStackSize) {
		maxStackCapacity = newUndoStackSize;
		undoStack = new Stack<Undoable>();
		redoStack = new Stack<Undoable>();
	}
	
	public int getStackSize() {
		return this.maxStackCapacity;
	}

	public void pushUndo(Undoable undoActivity) {
		if (undoActivity.isUndoable()) {
			// If buffersize exceeds, remove the oldest command
			if (getUndoSize() >= maxStackCapacity) {
				undoStack.remove(0);
			}
			undoStack.push(undoActivity);
		}
		else {
			// a not undoable activity clears the stack because
			// the last activity does not correspond with the
			// last undo activity
			undoStack = new Stack<Undoable>();
		}
	}

	public void pushRedo(Undoable redoActivity) {
		if (redoActivity.isRedoable()) {
			// If buffersize exceeds, remove the oldest command
			if (getRedoSize() >= maxStackCapacity) {
				redoStack.remove(0);
			}
			// add redo activity only if it is not already the last
			// one in the buffer
			if ((getRedoSize() == 0) || (peekRedo() != redoActivity)) {
				redoStack.push(redoActivity);
			}
		}
		else {
			// a not undoable activity clears the tack because
			// the last activity does not correspond with the
			// last undo activity
			redoStack = new Stack<Undoable>();
		}
	}

	public boolean isUndoable() {
		if (getUndoSize() > 0) {
			return undoStack.peek().isUndoable();
		}
		else {
			return false;
		}
	}
	
	public boolean isRedoable() {
		if (getRedoSize() > 0) {
			return redoStack.peek().isRedoable();
		}
		else {
			return false;
		}
	}

	protected Undoable peekUndo() {
		if (getUndoSize() > 0) {
			return undoStack.peek();
		}
		else {
			return null;
		}
	}

	protected Undoable peekRedo() {
		if (getRedoSize() > 0) {
			return redoStack.peek();
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the current size of undo buffer.
	 */
	public int getUndoSize() {
		return undoStack.size();
	}

	/**
	 * Returns the current size of redo buffer.
	 */
	public int getRedoSize() {
		return redoStack.size();
	}

	/**
	 * Throw NoSuchElementException if there is none
	 */
	public Undoable popUndo() {
		// Get the last element - throw NoSuchElementException if there is none
		// Remove it from undo collection
		if (getUndoSize() > 0) {
			return undoStack.pop();
		}
		else {
			return null;
		}
	}

	/**
	 * Throw NoSuchElementException if there is none
	 */
	public Undoable popRedo() {
		// Get the last element - throw NoSuchElementException if there is none
		// Remove it from redo collection
		if (getRedoSize() > 0) {
			return redoStack.pop();
		}
		else {
			return null;
		}
	}
	
	//Clears Undo Stack
	public void clearUndos() {
		undoStack.clear();
	}
	// Clears Redo stack
	public void clearRedos() {
		redoStack.clear();
	}
	

}
