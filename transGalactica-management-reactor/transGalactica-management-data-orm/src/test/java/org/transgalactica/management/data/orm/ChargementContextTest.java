package org.transgalactica.management.data.orm;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.transaction.PlatformTransactionManager;
import org.transgalactica.test.AbstractContextTest;

public class ChargementContextTest extends AbstractContextTest {

	@Test
	public void testInitialisationContext() {
		assertNotNull(applicationContext);
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
