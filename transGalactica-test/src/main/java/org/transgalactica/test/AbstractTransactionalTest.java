package org.transgalactica.test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.After;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = { "classpath*:META-INF/beanRefFactory.xml",
		"classpath*:META-INF/beanRefFactory-test.xml" })
@ActiveProfiles("test")
@TransactionConfiguration
@Transactional
public abstract class AbstractTransactionalTest extends AbstractTransactionalJUnit4SpringContextTests {

	@PersistenceContext
	private EntityManager em;

	/**
	 * To avoid "false positives"
	 */
	@After
	public void flushSession() {
		em.flush();
	}
}
