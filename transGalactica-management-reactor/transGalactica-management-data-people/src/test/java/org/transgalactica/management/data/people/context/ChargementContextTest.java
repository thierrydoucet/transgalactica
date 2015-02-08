package org.transgalactica.management.data.people.context;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.transgalactica.management.data.people.TestConfig;
import org.transgalactica.management.data.people.bo.EmployeEntity;
import org.transgalactica.management.data.people.bo.EmployeSearchCriteria;
import org.transgalactica.management.data.people.bo.EmployeSummary;
import org.transgalactica.management.data.people.bo.MecanicienEntity;
import org.transgalactica.management.data.people.bo.PiloteEntity;
import org.transgalactica.management.data.people.dao.EmployeDao;

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
	public void testInitialisationBos() {
		assertFalse(applicationContext.containsBean(EmployeEntity.class.getName()));
		assertNotNull(applicationContext.getBean(PiloteEntity.class));
		assertNotNull(applicationContext.getBean(MecanicienEntity.class));

		assertNotNull(applicationContext.getBean(EmployeSearchCriteria.class));

		assertNotNull(applicationContext.getBean(EmployeSummary.class));
	}

	@Test
	public void testInitialisationDaos() {
		assertNotNull(applicationContext.getBean(EmployeDao.class));
	}
}
