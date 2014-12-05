package org.transgalactica.management.business.hr;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.transgalactica.management.business.hr.service.EmployeService;
import org.transgalactica.test.AbstractContextTest;

public class ChargementContextTest extends AbstractContextTest {

	@Test
	public void testInitialisationContext() {
		assertNotNull(applicationContext);
	}

	@Test
	public void testInitialisationServices() {
		applicationContext.getBean(EmployeService.class);
	}

	@Test
	public void testInitialisationRules() {
		// No HR rules yet
	}
}
