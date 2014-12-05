package org.transgalactica.management.business.core;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.security.authentication.AuthenticationManager;
import org.transgalactica.test.AbstractContextTest;

public class ChargementContextTest extends AbstractContextTest {

	@Test
	public void testInitialisationContext() {
		assertNotNull(applicationContext);
	}

	@Test
	public void testInitialisationAuthenticationManager() {
		assertNotNull(applicationContext.getBean(AuthenticationManager.class));
	}
}
