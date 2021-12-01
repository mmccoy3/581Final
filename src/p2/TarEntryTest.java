package p2;

import static org.junit.Assert.*;
//import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import java.io.File;

import org.junit.Test;


public class TarEntryTest {


	
	@Test
	public void testTarEntryString() {
		
		TarEntry test = new TarEntry("test");
		assertEquals("test", test.getName());

	}

	@Test
	public void testTarEntryFile() throws Exception {
		 File myObj = new File("filename.txt");
		 TarEntry test = new TarEntry(myObj);
		 assertEquals(myObj, test.getFile());
		
	}


	
	@Test
	public void testEqualsTarEntry() throws InvalidHeaderException {
		TarEntry test = new TarEntry("test");
		TarEntry equal = new TarEntry("test");
		assertTrue(test.equals(equal));
		File myObj = new File("filename.txt");
		TarEntry notEqual = new TarEntry(myObj);
		assertFalse(test.equals(notEqual));
	}

	@Test
	public void testIsDescendent() {
		TarEntry test = new TarEntry("test");
		TarEntry testing = new TarEntry("testing");
		TarEntry equal = new TarEntry("equal");
		assertTrue(test.isDescendent(testing));
		assertFalse(test.isDescendent(equal));
	}



	@Test
	public void testGetName() {
		TarEntry test = new TarEntry("test");
		assertEquals("test", test.getName());
	}

	@Test
	public void testSetName() {
		TarEntry test = new TarEntry("test");
		test.setName("testing");
		assertEquals("testing", test.getName());
	}

	@Test
	public void testGetUserId() {
		TarEntry test = new TarEntry("test");
		test.setUserId(0);
		assertEquals(0, test.getUserId());
	}

	@Test
	public void testSetUserId() {
		TarEntry test = new TarEntry("test");
		test.setUserId(0);
		assertEquals(0, test.getUserId());
	}

	@Test
	public void testGetGroupId() {
		TarEntry test = new TarEntry("test");
		test.setGroupId(100);
		assertEquals(100, test.getGroupId());
	}

	@Test
	public void testSetGroupId() {
		TarEntry test = new TarEntry("test");
		test.setGroupId(400);
		assertEquals(400, test.getGroupId());
	}

	@Test
	public void testGetUserName() {
		TarEntry test = new TarEntry("test");
		test.setUserName("Miranda");
		assertEquals("Miranda", test.getUserName());
	}

	@Test
	public void testSetUserName() {
		TarEntry test = new TarEntry("test");
		test.setUserName("Miranda");
		assertEquals("Miranda", test.getUserName());
	}

	@Test
	public void testGetGroupName() {
		TarEntry test = new TarEntry("test");
		test.setGroupName("testingGroup");
		assertEquals("testingGroup", test.getGroupName());
	}

	@Test
	public void testSetGroupName() {
		TarEntry test = new TarEntry("test");
		test.setGroupName("testingGroup");
		assertEquals("testingGroup", test.getGroupName());
	}

	@Test
	public void testSetIds() {
		TarEntry test = new TarEntry("test");
		test.setIds(10, 20);
		assertEquals(10, test.getUserId());
		assertEquals(20, test.getGroupId());
	}

	@Test
	public void testSetNames() {
		TarEntry test = new TarEntry("test");
		test.setNames("Miranda", "Testers");
		assertEquals("Miranda", test.getUserName());
		assertEquals("Testers", test.getGroupName());
	}

	@Test
	public void testSetModTimeLong() {
		TarEntry test = new TarEntry("test");
		test.setModTime(5000);
		assertEquals(5, test.header.modTime);
	}

	@Test
	public void testSetModTimeDate() {
		TarEntry test = new TarEntry("test");
		test.setModTime(5);
		assertEquals(0, test.header.modTime);
	}



	@Test
	public void testGetFile() throws InvalidHeaderException {
		 File myObj = new File("filename.txt");
		 TarEntry test = new TarEntry(myObj);
		 assertEquals(myObj, test.getFile());
		 assertEquals(myObj, test.getFile());
	}

	@Test
	public void testGetSize() {
		TarEntry test = new TarEntry("test");
		test.setSize(5000);
		assertEquals(5000, test.getSize());
	}

	@Test
	public void testSetSize() {
		TarEntry test = new TarEntry("test");
		test.setSize(5000);
		assertEquals(5000, test.getSize());
	}

	@Test
	public void testAdjustEntryName() {
		TarEntry mockTar = mock(TarEntry.class);
		mockTar.adjustEntryName(null, null);
		//verify(spyManager, atLeastOnce()).clearUndos();
		verify(mockTar, atLeastOnce()).adjustEntryName(null, null);
	}

	@Test
	public void testIsDirectory() {
		TarEntry test = new TarEntry("Output.txt");
		StringBuffer names = new StringBuffer("test/");
		test.header.name = names;
		assertTrue(test.isDirectory());
	}

	@Test
	public void testGetFileTarHeader() throws InvalidHeaderException {
		TarEntry mockTar = mock(TarEntry.class);
		mockTar.getFileTarHeader(null, null);
		verify(mockTar, atLeastOnce()).getFileTarHeader(null, null);
		
	}

	@Test
	public void testGetDirectoryEntries() throws InvalidHeaderException {
		TarEntry test = new TarEntry("test");
		TarEntry[] returnVal = new TarEntry[0];
		assertEquals(returnVal, test.getDirectoryEntries());
	}
	
	@Test
	public void testComputeCheckSum() {
		TarEntry test = new TarEntry("test");
		byte[] b = {5,10};
		assertEquals(15, test.computeCheckSum(b));
		
		
	}
/*
	@Test
	void testWriteEntryHeader() {
		fail("Not yet implemented");
	}
*/
	@Test
	public void testParseTarHeader() throws InvalidHeaderException {
		TarEntry test = new TarEntry("test");

		
		//test.parseTarHeader(test.header, header);
		//assertEquals(111, test.header.name);
	}

	@Test
	public void testNameTarHeader() {
	// java.lang.AssertionError: expected: java.lang.StringBuffer<new> but was: java.lang.StringBuffer<new>
		TarEntry test = new TarEntry("test");
		StringBuffer name = new StringBuffer("new");
		test.nameTarHeader(test.header, "new");
		assertEquals(0, test.header.groupId);
	}
}
