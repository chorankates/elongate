package com.rc.elongate;

import org.junit.*;

public class ParserTest {

	Parser p;
	
	@Before
	public void setup () {
		p = new Parser();
	}
	
	@Test
	public void testInvalidRequest () {
		Assert.assertEquals("invalid URL", p.parse("this is not a URL"));
	}
	
	@Test public void testValidRequest () {
		Assert.assertTrue(p.parse(ResolverTest.shortGoogle).matches("^https?://.*"));
	}
	
	@Test // so i remember to remove this 
	public void testBackdoor () {
		Assert.assertEquals("TEST: THIS IS ONE", p.parse("test: this is one"));
	}
	
	
}
