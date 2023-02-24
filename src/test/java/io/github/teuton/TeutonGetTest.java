package io.github.teuton;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TeutonGetTest {
	
	@Test
	public void testVersion() {
		assertEquals("0.2.4", TeutonGet.version());
	}
		
}