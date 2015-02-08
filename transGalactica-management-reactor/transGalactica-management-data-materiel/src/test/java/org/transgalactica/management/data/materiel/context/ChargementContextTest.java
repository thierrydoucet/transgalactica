package org.transgalactica.management.data.materiel.context;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.transgalactica.management.data.materiel.TestConfig;
import org.transgalactica.management.data.materiel.bo.HangarEntity;
import org.transgalactica.management.data.materiel.bo.HangarSummary;
import org.transgalactica.management.data.materiel.bo.VaisseauEntity;
import org.transgalactica.management.data.materiel.bo.VaisseauIntergalactiqueEntity;
import org.transgalactica.management.data.materiel.bo.VaisseauSearchCriteria;
import org.transgalactica.management.data.materiel.bo.VaisseauSummary;
import org.transgalactica.management.data.materiel.dao.HangarDao;
import org.transgalactica.management.data.materiel.dao.VaisseauDao;

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
		assertNotNull(applicationContext.getBean(VaisseauEntity.class.getName()));
		assertNotNull(applicationContext.getBean(VaisseauIntergalactiqueEntity.class));
		assertNotNull(applicationContext.getBean(HangarEntity.class));

		assertNotNull(applicationContext.getBean(VaisseauSearchCriteria.class));
		assertNotNull(applicationContext.getBean(VaisseauSearchCriteria.class));

		assertNotNull(applicationContext.getBean(VaisseauSummary.class));
		assertNotNull(applicationContext.getBean(HangarSummary.class));
	}

	@Test
	public void testInitialisationDaos() {
		assertNotNull(applicationContext.getBean(HangarDao.class));
		assertNotNull(applicationContext.getBean(VaisseauDao.class));
	}
}
