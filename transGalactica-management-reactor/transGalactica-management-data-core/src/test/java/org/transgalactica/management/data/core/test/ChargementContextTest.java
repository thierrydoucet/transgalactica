package org.transgalactica.management.data.core.test;

import static org.junit.Assert.assertNotNull;

import org.dozer.Mapper;
import org.junit.Test;
import org.transgalactica.test.AbstractContextTest;

public class ChargementContextTest extends AbstractContextTest {

	@Test
	public void testInitialisationContext() {
		assertNotNull(applicationContext);
	}

	@Test
	public void testInitialisationDozerMapper() {
		assertNotNull(applicationContext.getBean(Mapper.class));
	}
}
