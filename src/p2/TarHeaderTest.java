package p2;
import static org.junit.Assert.*;
//import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import java.io.File;

import org.junit.Test;

public class TarHeaderTest {

	@Test
	public void testParseOctal() throws InvalidHeaderException {
		TarHeader test = new TarHeader();
		byte[] bytes = new byte[5000];
		int offset = 10;
		int length = 50;
		assertEquals(0, test.parseOctal(bytes, offset, length));
	}
	/*
	@Test
	void testParseName() {
		fail("Not yet implemented");
	}

	@Test
	void testGetNameBytes() {
		fail("Not yet implemented");
	}

	@Test
	void testGetOctalBytes() {
		fail("Not yet implemented");
	}

	@Test
	void testGetLongOctalBytes() {
		fail("Not yet implemented");
	}

	@Test
	void testGetCheckSumOctalBytes() {
		fail("Not yet implemented");
	}

	@Test
	void testParseTarHeader() {
		fail("Not yet implemented");
	}

	
	@Test
	void testInitializeHeader() {
		File test = new File("Output.txt");
		String name = "testingName";
		TarHeader header = mock(TarHeader.class);
		header.initializeHeader(test, name);
		verify(header, atLeastOnce()).initializeHeader(test, name);
	}
	*/
	
	
	
	@Test
	public void testGetSetName() {
		TarHeader test = new TarHeader();
		test.setName( new StringBuffer("test"));
		assertEquals("test", test.getName());
	}
	
	@Test
	public void testMode() {
		TarHeader test = new TarHeader();
		test.setMode(5);
		assertEquals(5, test.getMode());
	}

	
	@Test
	public void testGetUserId() {
		TarHeader test = new TarHeader();
		test.setUserId(0);
		assertEquals(0, test.getUserId());
	}


	@Test
	public void testGetGroupId() {
		TarHeader test = new TarHeader();
		test.setGroupId(100);
		assertEquals(100, test.getGroupId());
	}


	@Test
	public void testGetSize() {
		TarHeader test = new TarHeader();
		test.setSize(5000);
		assertEquals(5000, test.getSize());
	}
	
	@Test
	public void testSetModTime() {
		TarHeader test = new TarHeader();
		test.setModTime(5000);
		assertEquals(5000, test.getModTime());
	}
	
	@Test 
	public void checkSumTest() {
		TarHeader test = new TarHeader();
		test.setcheckSum(5);;
		assertEquals(5, test.getCheckSum());
	}
	@Test
	public void testLinkFlag() {
		TarHeader test = new TarHeader();
		byte b = 5;
		test.setLinkFlag(b);
		assertEquals(b, test.getLinkFlag());
	}
	@Test 
	public void linkNameTest() {
		TarHeader test = new TarHeader();
		test.setLinkName(new StringBuffer("test"));
		assertEquals("test", test.getLinkName());
	}
	
	@Test 
	public void magicTest() {
		TarHeader test = new TarHeader();
		test.setMagic(new StringBuffer("test"));
		assertEquals("test", test.getMagic());
	}
	
	@Test
	public void testGetUserName() {
		TarHeader test = new TarHeader();
		StringBuffer testing = new StringBuffer("testing");
		test.setUserName(testing);
		assertEquals("testing", test.getUserName());
	}
	@Test
	public void testGetGroupName() {
		TarHeader test = new TarHeader();
		StringBuffer testing = new StringBuffer("testing");
		test.setGroupName(testing);
		assertEquals("testing", test.getGroupName());
	}
	@Test
	public void testDevMajor() {
		TarHeader test = new TarHeader();
		test.setDevMajor(5);
		assertEquals(5, test.getDevMajor());
	}
	
	@Test
	public void testDevMinor() {
		TarHeader test = new TarHeader();
		test.setDevMinor(5);
		assertEquals(5, test.getDevMinor());
	}

}
	