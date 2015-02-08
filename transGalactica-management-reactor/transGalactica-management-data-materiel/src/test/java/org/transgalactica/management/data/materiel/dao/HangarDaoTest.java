package org.transgalactica.management.data.materiel.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.transgalactica.management.data.materiel.TestConfig;
import org.transgalactica.management.data.materiel.bo.HangarEntity;
import org.transgalactica.management.data.materiel.bo.HangarSummary;
import org.transgalactica.management.data.materiel.bo.VaisseauEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@TransactionConfiguration
@Transactional
public class HangarDaoTest {

	@Autowired
	private BeanFactory beanFactory;

	@Autowired
	private HangarDao hangarDao;

	@Autowired
	private VaisseauDao vaisseauDao;

	@Test
	public void testInsert() {
		HangarEntity toSave = beanFactory.getBean(HangarEntity.class);
		toSave.setLocalisation("AAA");
		toSave.setNombreEmplacements(5);
		VaisseauEntity vaisseau = vaisseauDao.findByImmatriculation("Faucon Millenium");
		toSave.add(vaisseau);
		assertNull(toSave.getNumero());

		hangarDao.save(toSave);

		assertNotNull(toSave.getNumero());
		HangarEntity found = hangarDao.findByNumero(toSave.getNumero());
		assertNotNull(found);
		assertEquals(5, found.getNombreEmplacements());
		assertEquals("AAA", found.getLocalisation());
		assertNotNull(found.getVaisseaux());
		assertEquals(1, found.getVaisseaux().size());
	}

	@Test
	public void testUpdate() {
		HangarEntity toUpdate = hangarDao.findByNumero(1L);
		assertNotNull(toUpdate);
		toUpdate.setNombreEmplacements(2);
		toUpdate.setLocalisation("BBB");
		assertEquals(3, toUpdate.getVaisseaux().size());
		toUpdate.remove(toUpdate.getVaisseaux().iterator().next());

		hangarDao.save(toUpdate);

		HangarEntity found = hangarDao.findByNumero(1L);
		assertNotNull(found);
		assertEquals(2, found.getNombreEmplacements());
		assertEquals("BBB", found.getLocalisation());
		assertEquals(2, found.getVaisseaux().size());
	}

	@Test
	public void testDelete() {
		HangarEntity toDelete = hangarDao.findByNumero(2L);
		hangarDao.delete(toDelete);
		HangarEntity deleted = hangarDao.findByNumero(2L);
		assertNull(deleted);
	}

	@Test
	public void testFindByNumero() {
		HangarEntity found = hangarDao.findByNumero(1L);
		assertNotNull(found);
		assertEquals(10, found.getNombreEmplacements());
		assertEquals("Alderaan", found.getLocalisation());
		assertNotNull(found.getVaisseaux());
		assertEquals(3, found.getVaisseaux().size());
		assertTrue(found.getVaisseaux().iterator().next() instanceof VaisseauEntity);
	}

	@Test
	public void testFindByNumero_Absent() {
		HangarEntity found = hangarDao.findByNumero(99L);
		assertNull(found);
	}

	@Test
	public void testFindAllOrderByNumero() {
		List<HangarSummary> hangars = hangarDao.findAllOrderByNumero();

		assertNotNull(hangars);
		assertEquals(3, hangars.size());

		assertEquals(1, hangars.get(0).getNumeroHangar().intValue());
		assertEquals("Alderaan", hangars.get(0).getLocalisationHangar());
		assertEquals(10, hangars.get(0).getNombreEmplacementsHangar());

	}

	@Test
	public void testFindByLocalisationOrderByNumero() {
		List<HangarSummary> hangars = hangarDao.findByLocalisationOrderByNumero("Arakis");

		assertNotNull(hangars);
		assertEquals(1, hangars.size());

		assertEquals(2, hangars.get(0).getNumeroHangar().intValue());
		assertEquals("Arakis", hangars.get(0).getLocalisationHangar());
		assertEquals(100, hangars.get(0).getNombreEmplacementsHangar());
	}

	@Test
	public void testFindByVaisseauxModeleOrderByNumero() {
		List<HangarSummary> hangars = hangarDao.findByVaisseauxModeleOrderByNumero("cargo YT-1300");

		assertNotNull(hangars);
		assertEquals(1, hangars.size());

		assertEquals(1, hangars.get(0).getNumeroHangar().intValue());
		assertEquals("Alderaan", hangars.get(0).getLocalisationHangar());
		assertEquals(10, hangars.get(0).getNombreEmplacementsHangar());
	}
}
