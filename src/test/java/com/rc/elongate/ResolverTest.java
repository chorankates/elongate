package com.rc.elongate;

import org.junit.*;

import com.rc.elongate.Resolver;

public class ResolverTest {

	Resolver r;
	Resolver s;
	
	public static final String shortGoogle = "http://bit.ly/LmvF";
	public static final String longGoogle  = "http://www.google.com/"; // or should this be www. ?
	
	@Before
	public void setup () {
		r = new Resolver();
		s = new Resolver(shortGoogle);
	}
	
	@Test 
	public void testCreate () {
		Assert.assertEquals(s.getShortURL(), shortGoogle);
	}
	
	@Test
	public void testResolverCanResolve () {
		Assert.assertEquals(longGoogle, s.resolve());
	}
	
	@Test
	public void testResolverCanNoop () {
		Assert.assertEquals(longGoogle, longGoogle);
	}
	
}
