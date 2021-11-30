package p1;

import static org.junit.Assert.*;
//import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import java.util.Vector;

import org.junit.Test;

import CH.ifa.draw.util.Undoable;

public class UndoManagerTest {

	@Mock
	UndoManager mockManager = mock(UndoManager.class);
	UndoManager spyManager = spy(UndoManager.class);
	
	@Test
	public void testUndoManager() {
		UndoManager test = new UndoManager();
		assertEquals(20, test.getStackSize());
		assertEquals(20, test.getStackSize());
	}

	@Test
	public void testUndoManagerInt() {
		UndoManager test = new UndoManager(15);
		test.getRedoSize();
		assertEquals(15, test.getStackSize());
		assertEquals(15, test.getStackSize());
	}

	@Test
	public void testPushUndo() {
		Undoable  undoableMock = mock(Undoable.class);
		doReturn(true).when(undoableMock).isUndoable();
		spyManager.pushUndo(undoableMock);
		assertEquals(1, spyManager.getUndoSize());
		doReturn(false).when(undoableMock).isUndoable();
		spyManager.pushUndo(undoableMock);
		assertEquals(0, spyManager.getUndoSize());
	}

	@Test
	public void testPushRedo() {
		Undoable  undoableMock = mock(Undoable.class);
		doReturn(true).when(undoableMock).isRedoable();
		spyManager.pushRedo(undoableMock);
		assertEquals(1, spyManager.getRedoSize());
		doReturn(false).when(undoableMock).isRedoable();
		spyManager.pushRedo(undoableMock);
		assertEquals(0, spyManager.getRedoSize());
	}

	
	@Test
	public void testIsUndoable() {

		doReturn(0).when(mockManager).getUndoSize();
		assertFalse(mockManager.isUndoable());

	}

	@Test
	public void testIsRedoable() {
		doReturn(0).when(mockManager).getRedoSize();
		assertFalse(mockManager.isRedoable());
	}

	@Test
	public void testPeekUndo() {
		doReturn(0).when(mockManager).getUndoSize();
		assertNull(mockManager.peekUndo());
	}

	@Test
	public void testPeekRedo() {
		doReturn(0).when(mockManager).getRedoSize();
		assertNull(mockManager.peekRedo());
	}

	@Test
	public void testGetUndoSize() {
		Undoable  undoableMock = mock(Undoable.class);
		doReturn(true).when(undoableMock).isUndoable();
		assertEquals(0, spyManager.getUndoSize());
		spyManager.pushUndo(undoableMock);
		assertEquals(1, spyManager.getUndoSize());
	}

	@Test
	public void testGetRedoSize() {
		Undoable  undoableMock = mock(Undoable.class);
		doReturn(true).when(undoableMock).isRedoable();
		assertEquals(0, spyManager.getRedoSize());
		spyManager.pushRedo(undoableMock);
		assertEquals(1, spyManager.getRedoSize());
	}


	@Test
	public void testClearUndos() {
		// 			verify(spyCommentLines, atLeastOnce()).visitToken(mockAST);
		spyManager.clearUndos();
		verify(spyManager, atLeastOnce()).clearUndos();
	}

	@Test
	public void testClearRedos() {
		spyManager.clearRedos();
		verify(spyManager, atLeastOnce()).clearRedos();
	}



}
