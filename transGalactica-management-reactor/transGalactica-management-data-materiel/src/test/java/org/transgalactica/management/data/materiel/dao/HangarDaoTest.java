package org.transgalactica.management.data.materiel.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.transgalactica.management.data.materiel.bo.HangarEntity;
import org.transgalactica.management.data.materiel.bo.HangarSummary;
import org.transgalactica.management.data.materiel.bo.VaisseauEntity;
import org.transgalactica.management.data.materiel.dao.HangarDao;
import org.transgalactica.management.data.materiel.dao.VaisseauDao;
import org.transgalactica.test.AbstractTransactionalTest;

public class HangarDaoTest extends AbstractTransactionalTest {

	@Autowired
	private HangarDao hangarDao;

	@Autowired
	private VaisseauDao vaisseauDao;

	@Test
	public void testPersist_Save() {
		HangarEntity toSave = applicationContext.getBean(HangarEntity.class);
		toSave.setLocalisation("AAA");
		toSave.setNombreEmplacements(5);
		VaisseauEntity vaisseau = vaisseauDao.findByImmatriculation("Faucon Millenium");
		toSave.add(vaisseau);
		assertNull(toSave.getNumero());

		hangarDao.persist(toSave);

		assertNotNull(toSave.getNumero());
		HangarEntity found = hangarDao.findByNumero(toSave.getNumero());
		assertNotNull(found);
		assertEquals(5, found.getNombreEmplacements());
		assertEquals("AAA", found.getLocalisation());
		assertNotNull(found.getVaisseaux());
		assertEquals(1, found.getVaisseaux().size());
	}

	@Test
	public void testPersist_Update() {
		HangarEntity toUpdate = hangarDao.findByNumero(1L);
		assertNotNull(toUpdate);
		toUpdate.setNombreEmplacements(2);
		toUpdate.setLocalisation("BBB");
		assertEquals(3, toUpdate.getVaisseaux().size());
		toUpdate.remove(toUpdate.getVaisseaux().iterator().next());

		hangarDao.persist(toUpdate);

		HangarEntity found = hangarDao.findByNumero(1L);
		assertNotNull(found);
		assertEquals(2, found.getNombreEmplacements());
		assertEquals("BBB", found.getLocalisation());
		assertEquals(2, found.getVaisseaux().size());
	}

	@Test
	public void testRemove() {
		HangarEntity toDelete = hangarDao.findByNumero(2L);
		hangarDao.remove(toDelete);
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
	public void testRefresh() {
		HangarEntity found = hangarDao.findByNumero(1L);
		assertNotNull(found);
		found.setLocalisation("Quelque part dans l'espace");

		hangarDao.refresh(found);
		assertEquals("Alderaan", found.getLocalisation());
	}

	@Test
	public void testFindAllHangars() {
		List<HangarSummary> hangars = hangarDao.findAllHangars();

		assertNotNull(hangars);
		assertEquals(3, hangars.size());

		assertEquals(1, hangars.get(0).getNumeroHangar().intValue());
		assertEquals("Alderaan", hangars.get(0).getLocalisationHangar());
		assertEquals(10, hangars.get(0).getNombreEmplacementsHangar());

	}

	@Test
	public void testFindHangarsByLocalisation() {
		List<HangarSummary> hangars = hangarDao.findHangarsByLocalisation("Arakis");

		assertNotNull(hangars);
		assertEquals(1, hangars.size());

		assertEquals(2, hangars.get(0).getNumeroHangar().intValue());
		assertEquals("Arakis", hangars.get(0).getLocalisationHangar());
		assertEquals(100, hangars.get(0).getNombreEmplacementsHangar());
	}

	@Test
	public void testFindHangarsWithVaisseauxOfModele() {
		List<HangarSummary> hangars = hangarDao.findHangarsWithVaisseauxOfModele("cargo YT-1300");

		assertNotNull(hangars);
		assertEquals(1, hangars.size());

		assertEquals(1, hangars.get(0).getNumeroHangar().intValue());
		assertEquals("Alderaan", hangars.get(0).getLocalisationHangar());
		assertEquals(10, hangars.get(0).getNombreEmplacementsHangar());
	}
}
