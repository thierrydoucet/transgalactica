package org.transgalactica.management.data.rest.context;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.transgalactica.management.data.rest.TestConfig;
import org.transgalactica.management.data.rest.bo.EmployeSearchCriteria;
import org.transgalactica.management.data.rest.bo.EmployeSummaryTo;
import org.transgalactica.management.data.rest.bo.EmployeTo;
import org.transgalactica.management.data.rest.bo.HangarSearchCriteria;
import org.transgalactica.management.data.rest.bo.HangarSummaryTo;
import org.transgalactica.management.data.rest.bo.HangarTo;
import org.transgalactica.management.data.rest.bo.MecanicienTo;
import org.transgalactica.management.data.rest.bo.PiloteTo;
import org.transgalactica.management.data.rest.bo.VaisseauSummaryTo;
import org.transgalactica.management.data.rest.bo.VaisseauTo;
import org.transgalactica.management.data.rest.dao.EmployeDao;
import org.transgalactica.management.data.rest.dao.HangarDao;
import org.transgalactica.management.data.rest.dao.HrReferentielDao;
import org.transgalactica.management.data.rest.dao.VaisseauDao;
import org.transgalactica.management.data.rest.mapper.HrMapper;
import org.transgalactica.management.data.rest.mapper.LogisticsMapper;

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
		assertNotNull(applicationContext.getBean(HangarSummaryTo.class.getName(), HangarSummaryTo.class));
		assertNotNull(applicationContext.getBean(HangarTo.class));
		assertNotNull(applicationContext.getBean(HangarTo.VaisseauTo.class));
		assertNotNull(applicationContext.getBean(HangarSearchCriteria.class));

		assertNotNull(applicationContext.getBean(VaisseauSummaryTo.class.getName(), VaisseauSummaryTo.class));
		assertNotNull(applicationContext.getBean(VaisseauTo.class));
		assertNotNull(applicationContext.getBean(HangarSearchCriteria.class));

		assertNotNull(applicationContext.getBean(EmployeSummaryTo.class.getName(), EmployeSummaryTo.class));
		assertNotNull(applicationContext.getBean(MecanicienTo.class));
		assertNotNull(applicationContext.getBean(PiloteTo.class));
		assertNotNull(applicationContext.getBean(EmployeTo.VaisseauTo.class));
		assertNotNull(applicationContext.getBean(EmployeSearchCriteria.class));
	}

	@Test
	public void testInitialisationDaos() {
		assertNotNull(applicationContext.getBean(HangarDao.class));
		assertNotNull(applicationContext.getBean(VaisseauDao.class));
		assertNotNull(applicationContext.getBean(EmployeDao.class));
		assertNotNull(applicationContext.getBean(HrReferentielDao.class));
	}

	@Test
	public void testInitialisationMappers() {
		assertNotNull(applicationContext.getBean(LogisticsMapper.class));
		assertNotNull(applicationContext.getBean(HrMapper.class));
	}
}
