package org.transgalactica.management.rest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.After;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.transgalactica.management.rest.context.AppConfig;
import org.transgalactica.management.rest.context.mvc.WebMvcConfig;
import org.transgalactica.test.context.TestConfig;

@WebAppConfiguration
// TODO : spring 3.2.0-RELEASE @ContextHierarchy({ @ContextConfiguration(name =
// "root", classes = AppConfig.class),
// @ContextConfiguration(name = "mvc", classes = WebMvcConfig.class) })
@ContextConfiguration(classes = { AppConfig.class, WebMvcConfig.class, TestConfig.class })
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
