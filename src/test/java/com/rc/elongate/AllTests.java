package com.rc.elongate;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.rc.elongate.LoggerTest;
import com.rc.elongate.ResolverTest;

// this is a lot easier in junit4
@RunWith(Suite.class)
@Suite.SuiteClasses( { LoggerTest.class, ParserTest.class, ResolverTest.class })

public class AllTests {
}