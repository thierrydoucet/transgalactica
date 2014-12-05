package org.transgalactica.management.business.logistics;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.transgalactica.management.business.logistics.service.HangarService;
import org.transgalactica.management.business.logistics.service.VaisseauService;
import org.transgalactica.test.AbstractContextTest;

public class ChargementContextTest extends AbstractContextTest {

	@Test
	public void testInitialisationContext() {
		assertNotNull(applicationContext);
	}

	@Test
	public void testInitialisationServices() {
		assertNotNull(applicationContext.getBeansOfType(VaisseauService.class));
		assertNotNull(applicationContext.getBeansOfType(HangarService.class));
	}

	@Test
	public void testInitialisationRules() {
		// pas encore de règles
	}
}
