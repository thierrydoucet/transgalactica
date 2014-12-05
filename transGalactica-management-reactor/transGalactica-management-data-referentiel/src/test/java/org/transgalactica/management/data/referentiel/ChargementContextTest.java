package org.transgalactica.management.data.referentiel;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.transgalactica.management.data.referentiel.bo.MecanicienSpecialiteEntity;
import org.transgalactica.management.data.referentiel.dao.MecanicienSpecialiteDao;
import org.transgalactica.test.AbstractContextTest;

public class ChargementContextTest extends AbstractContextTest {

	@Test
	public void testInitialisationContext() {
		assertNotNull(applicationContext);
	}

	@Test
	public void testInitialisationBos() {
		assertNotNull(applicationContext.getBean(MecanicienSpecialiteEntity.class));
	}

	@Test
	public void testInitialisationDaos() {
		assertNotNull(applicationContext.getBean(MecanicienSpecialiteDao.class));
	}
}
