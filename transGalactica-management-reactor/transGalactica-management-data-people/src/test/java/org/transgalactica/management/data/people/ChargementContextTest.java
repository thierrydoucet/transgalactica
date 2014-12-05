package org.transgalactica.management.data.people;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.transgalactica.management.data.people.bo.EmployeEntity;
import org.transgalactica.management.data.people.bo.EmployeSearchCriteria;
import org.transgalactica.management.data.people.bo.EmployeSummary;
import org.transgalactica.management.data.people.bo.MecanicienEntity;
import org.transgalactica.management.data.people.bo.PiloteEntity;
import org.transgalactica.management.data.people.dao.EmployeDao;
import org.transgalactica.test.AbstractContextTest;

public class ChargementContextTest extends AbstractContextTest {

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
