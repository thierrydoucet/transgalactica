package org.transgalactica.management.data.materiel;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.transgalactica.management.data.materiel.bo.HangarEntity;
import org.transgalactica.management.data.materiel.bo.HangarSummary;
import org.transgalactica.management.data.materiel.bo.VaisseauEntity;
import org.transgalactica.management.data.materiel.bo.VaisseauIntergalactiqueEntity;
import org.transgalactica.management.data.materiel.bo.VaisseauSearchCriteria;
import org.transgalactica.management.data.materiel.bo.VaisseauSummary;
import org.transgalactica.management.data.materiel.dao.HangarDao;
import org.transgalactica.management.data.materiel.dao.VaisseauDao;
import org.transgalactica.test.AbstractContextTest;

public class ChargementContextTest extends AbstractContextTest {

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
		assertNotNull(applicationContext.getBean(VaisseauDao.class));
		assertNotNull(applicationContext.getBean(HangarDao.class));
	}
}
