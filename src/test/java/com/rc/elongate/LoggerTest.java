package com.rc.elongate;

import org.junit.*;

import com.rc.elongate.Logger;

public class LoggerTest {

	Logger l;
	Logger m;
	
	@Before
	public void setup() {
		l = new Logger();
		m = new Logger(true);
	}
	
	@Test
	public void testFilenameGenerationIsUnique () {
		Assert.assertFalse(l.getFile().equals(m.getFile()));
	}
	
	@Test
	public void testDefaultStdout () {
		Assert.assertFalse(l.isStdout());
	}
	
	@Test
	public void testNonDefaultStdout () {
		Assert.assertTrue(m.isStdout());
	}
	
}
