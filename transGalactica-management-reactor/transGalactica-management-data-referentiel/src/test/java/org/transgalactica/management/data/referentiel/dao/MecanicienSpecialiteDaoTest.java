package org.transgalactica.management.data.referentiel.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.transgalactica.management.data.referentiel.bo.MecanicienSpecialiteEntity;
import org.transgalactica.management.data.referentiel.dao.MecanicienSpecialiteDao;
import org.transgalactica.test.AbstractTransactionalTest;

public class MecanicienSpecialiteDaoTest extends AbstractTransactionalTest {

	@Autowired
	private MecanicienSpecialiteDao mecanicienSpecialiteDao;

	@Test
	public void testFindByNomSpecialite() {
		MecanicienSpecialiteEntity found = mecanicienSpecialiteDao.findByNomSpecialite("Moteur");
		assertNotNull(found);
		assertEquals("Moteur", found.getNomSpecialite());
	}

	@Test
	public void testFindByNomSpecialite_Absent() {
		MecanicienSpecialiteEntity found = mecanicienSpecialiteDao.findByNomSpecialite("Cassoulet Lorrain");
		assertNull(found);
	}

	@Test
	public void testFindAll() {
		List<MecanicienSpecialiteEntity> founds = mecanicienSpecialiteDao.findAll();
		assertNotNull(founds);
		assertEquals(5, founds.size());
	}
}
