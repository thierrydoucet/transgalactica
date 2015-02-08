package org.transgalactica.management.business.logistics.context;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.transgalactica.management.business.logistics.TestConfig;
import org.transgalactica.management.business.logistics.service.HangarService;
import org.transgalactica.management.business.logistics.service.VaisseauService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class ChargementContextTest {

	@Autowired
	private ApplicationContext applicationContext;

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
