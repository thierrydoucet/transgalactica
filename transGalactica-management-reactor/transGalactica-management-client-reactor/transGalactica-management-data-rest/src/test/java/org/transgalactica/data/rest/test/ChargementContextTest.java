package org.transgalactica.data.rest.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.transgalactica.data.rest.bo.EmployeSearchCriteria;
import org.transgalactica.data.rest.bo.EmployeSummaryTo;
import org.transgalactica.data.rest.bo.EmployeTo;
import org.transgalactica.data.rest.bo.HangarSearchCriteria;
import org.transgalactica.data.rest.bo.HangarSummaryTo;
import org.transgalactica.data.rest.bo.HangarTo;
import org.transgalactica.data.rest.bo.MecanicienTo;
import org.transgalactica.data.rest.bo.PiloteTo;
import org.transgalactica.data.rest.bo.VaisseauSummaryTo;
import org.transgalactica.data.rest.bo.VaisseauTo;
import org.transgalactica.data.rest.dao.EmployeDao;
import org.transgalactica.data.rest.dao.HangarDao;
import org.transgalactica.data.rest.dao.HrReferentielDao;
import org.transgalactica.data.rest.dao.VaisseauDao;
import org.transgalactica.data.rest.mapper.HrMapper;
import org.transgalactica.data.rest.mapper.LogisticsMapper;
import org.transgalactica.test.AbstractContextTest;

public class ChargementContextTest extends AbstractContextTest {

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
