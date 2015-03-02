package org.transgalactica.batch.salaire.context;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.transgalactica.batch.salaire.AbstractBatchTest;
import org.transgalactica.batch.salaire.bo.SalaireTo;
import org.transgalactica.batch.salaire.mapper.SalaireMapper;
import org.transgalactica.batch.salaire.rule.FicheSalaireRule;

public class ChargementContextTest extends AbstractBatchTest {

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	public void testInitialisationContext() {
		assertNotNull(applicationContext);
	}

	@Test
	public void testInitialisationBatchFramework() {
		assertNotNull(applicationContext.getBean("jobLauncher"));
		assertNotNull(applicationContext.getBean("jobRepository"));
	}

	@Test
	public void testInitialisationJobsAndFolks() {
		assertNotNull(applicationContext.getBean("ficheSalaireJob"));
	}

	@Test
	public void testInitialisationItems() {
		assertNotNull(applicationContext.getBean("employeEntityItemReader"));
		assertNotNull(applicationContext.getBean("computeSalaireItemProcessor"));
		assertNotNull(applicationContext.getBean("salaireToItemWriter"));
		assertNotNull(applicationContext.getBean("salaireToItemReader"));
		assertNotNull(applicationContext.getBean("salairePdfItemWriter"));
	}

	@Test
	public void testInitialisationBos() {
		assertNotNull(applicationContext.getBean(SalaireTo.class));
	}

	@Test
	public void testInitialisationRules() {
		assertNotNull(applicationContext.getBean(FicheSalaireRule.class));
	}

	@Test
	public void testInitialisationMappers() {
		assertNotNull(applicationContext.getBean(SalaireMapper.class));
	}
}
