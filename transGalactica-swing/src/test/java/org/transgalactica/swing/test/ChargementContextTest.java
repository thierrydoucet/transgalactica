package org.transgalactica.swing.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.transgalactica.swing.logistics.service.HangarService;
import org.transgalactica.swing.logistics.service.VaisseauService;
import org.transgalactica.test.AbstractContextTest;

public class ChargementContextTest extends AbstractContextTest {

	@Test
	public void testInitialisationContext() {
		assertNotNull(applicationContext);
	}

	@Test
	public void testInitialisationControllers() {
	}

	@Test
	public void testInitialisationModels() {
	}

	@Test
	public void testInitialisationViews() {
	}

	@Test
	public void testInitialisationServices() {
		assertNotNull(applicationContext.getBean(VaisseauService.class));
		assertNotNull(applicationContext.getBean(HangarService.class));
	}
}
