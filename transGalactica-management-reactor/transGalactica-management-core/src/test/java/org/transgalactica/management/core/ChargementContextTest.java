package org.transgalactica.management.core;

import static org.junit.Assert.assertNotNull;

import org.dozer.Mapper;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.transaction.PlatformTransactionManager;
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

	@Test
	public void testInitialisationAuthenticationManager() {
		assertNotNull(applicationContext.getBean(AuthenticationManager.class));
	}

	@Test
	@Ignore
	public void testInitialisationEhcache() {
		assertNotNull(null);
	}

	@Test
	public void testInitialisationEntityManagerFactory() {
		assertNotNull(applicationContext.getBean("entityManagerFactory"));
	}

	@Test
	public void testInitialisationTransactionManager() {
		assertNotNull(applicationContext.getBean(PlatformTransactionManager.class));
	}
}
