package org.transgalactica.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.transgalactica.test.AbstractContextTest;

public class TestChargementContext extends AbstractContextTest {

	@Test
	public void testInitialisationContext() {
		assertNotNull(applicationContext);
	}
}
