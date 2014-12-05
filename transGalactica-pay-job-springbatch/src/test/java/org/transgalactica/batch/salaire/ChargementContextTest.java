package org.transgalactica.batch.salaire;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.transgalactica.batch.salaire.bo.SalaireTo;
import org.transgalactica.batch.salaire.mapper.SalaireMapper;
import org.transgalactica.batch.salaire.rule.FicheSalaireRule;

public class ChargementContextTest extends AbstractBatchContextTest {

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
		assertNotNull(applicationContext.getBean("org.transgalactica.batch.salaire.FicheSalaireBatch"));
	}

	@Test
	public void testInitialisationItems() {
		assertNotNull(applicationContext.getBean("org.transgalactica.batch.salaire.item.EmployeEntityItemReader"));
		assertNotNull(applicationContext.getBean("org.transgalactica.batch.salaire.item.SalaireMapperItemProcessor"));
		assertNotNull(applicationContext.getBean("org.transgalactica.batch.salaire.item.SalaireToItemWriter"));
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
