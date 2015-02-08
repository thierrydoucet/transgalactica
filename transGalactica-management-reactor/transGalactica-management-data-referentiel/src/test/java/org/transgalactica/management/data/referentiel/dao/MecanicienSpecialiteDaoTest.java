package org.transgalactica.management.data.referentiel.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.transgalactica.management.data.referentiel.TestConfig;
import org.transgalactica.management.data.referentiel.bo.MecanicienSpecialiteEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@TransactionConfiguration
@Transactional(readOnly = true)
public class MecanicienSpecialiteDaoTest {

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
		List<MecanicienSpecialiteEntity> founds = mecanicienSpecialiteDao.findAll(new Sort("nomSpecialite"));
		assertNotNull(founds);
		assertEquals(5, founds.size());
		assertEquals("Armement", founds.get(0).getNomSpecialite());
	}
}
