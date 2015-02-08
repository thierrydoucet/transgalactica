package org.transgalactica.management.data.materiel.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
import org.transgalactica.management.data.materiel.bo.VaisseauEntity;
import org.transgalactica.management.data.materiel.bo.VaisseauIntergalactiqueEntity;
import org.transgalactica.management.data.materiel.bo.VaisseauSearchCriteria;
import org.transgalactica.management.data.materiel.bo.VaisseauSummary;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@TransactionConfiguration
@Transactional
public class VaisseauDaoTest {

	@Autowired
	private BeanFactory beanFactory;

	@Autowired
	private HangarDao hangarDao;

	@Autowired
	private VaisseauDao vaisseauDao;

	@Test
	public void testInsert() {
		VaisseauEntity toSave = beanFactory.getBean(VaisseauEntity.class.getName(), VaisseauEntity.class);
		toSave.setCapaciteDeFret(1L);
		toSave.setImmatriculation("immatriculation");
		toSave.setModele("modele");
		toSave.setNombreDePassagers((short) 2);
		toSave.setAutonomie(3);
		toSave.setVitesse(4);

		HangarEntity hangar = hangarDao.findByNumero(1L);
		toSave.setHangar(hangar);

		vaisseauDao.save(toSave);

		VaisseauEntity found = vaisseauDao.findByImmatriculation("immatriculation");

		assertNotNull(found);
		assertNotNull(found.getImmatriculation());
		assertEquals(1, found.getCapaciteDeFret());
		assertEquals("immatriculation", found.getImmatriculation());
		assertEquals("modele", found.getModele());
		assertEquals(2, found.getNombreDePassagers());
		assertEquals(3, found.getAutonomie());
		assertEquals(4, found.getVitesse());

		assertNotNull(found.getHangar());
	}

	@Test
	public void testInsert_VaisseauIntergalactique() {
		VaisseauIntergalactiqueEntity toSave = beanFactory.getBean(VaisseauIntergalactiqueEntity.class);
		toSave.setCapaciteDeFret(1L);
		toSave.setImmatriculation("immatriculation");
		toSave.setModele("modele");
		toSave.setNombreDePassagers((short) 2);
		toSave.setAutonomie(3);
		toSave.setVitesse(4);
		toSave.setMultiplicateurHyperdrive((short) 10);

		HangarEntity hangar = hangarDao.findByNumero(1L);
		toSave.setHangar(hangar);

		vaisseauDao.save(toSave);

		VaisseauIntergalactiqueEntity found = (VaisseauIntergalactiqueEntity) vaisseauDao
				.findByImmatriculation("immatriculation");

		assertNotNull(found);
		assertEquals(1, found.getCapaciteDeFret());
		assertEquals("immatriculation", found.getImmatriculation());
		assertEquals("modele", found.getModele());
		assertEquals(2, found.getNombreDePassagers());
		assertEquals(3, found.getAutonomie());
		assertEquals(4, found.getVitesse());
		assertEquals(10, found.getMultiplicateurHyperdrive());

		assertNotNull(found.getHangar());
	}

	@Test
	public void testUpdate() {
		VaisseauEntity toUpdate = vaisseauDao.findByImmatriculation("Flying Bird");
		assertNotNull(toUpdate);
		toUpdate.setCapaciteDeFret(1L);
		toUpdate.setImmatriculation("immatriculation");
		toUpdate.setModele("modele");
		toUpdate.setNombreDePassagers((short) 2);
		toUpdate.setAutonomie(3);
		toUpdate.setVitesse(4);
		toUpdate.setHangar(null);

		vaisseauDao.save(toUpdate);

		VaisseauEntity found = vaisseauDao.findByImmatriculation("immatriculation");

		assertNotNull(found);
		assertEquals("immatriculation", found.getImmatriculation());
		assertEquals(1, found.getCapaciteDeFret());
		assertEquals("immatriculation", found.getImmatriculation());
		assertEquals("modele", found.getModele());
		assertEquals(2, found.getNombreDePassagers());
		assertEquals(3, found.getAutonomie());
		assertEquals(4, found.getVitesse());

		assertNull(found.getHangar());
	}

	@Test
	public void testUpdate_VaisseauIntergalactique() {
		VaisseauIntergalactiqueEntity toUpdate = (VaisseauIntergalactiqueEntity) vaisseauDao
				.findByImmatriculation("Faucon Millenium");
		assertNotNull(toUpdate);
		toUpdate.setCapaciteDeFret(1L);
		toUpdate.setImmatriculation("immatriculation");
		toUpdate.setModele("modele");
		toUpdate.setNombreDePassagers((short) 2);
		toUpdate.setAutonomie(3);
		toUpdate.setVitesse(4);
		toUpdate.setMultiplicateurHyperdrive((short) 10);
		toUpdate.setHangar(null);

		vaisseauDao.save(toUpdate);

		VaisseauIntergalactiqueEntity found = (VaisseauIntergalactiqueEntity) vaisseauDao
				.findByImmatriculation("immatriculation");

		assertNotNull(found);
		assertEquals(1, found.getCapaciteDeFret());
		assertEquals("immatriculation", found.getImmatriculation());
		assertEquals("modele", found.getModele());
		assertEquals(2, found.getNombreDePassagers());
		assertEquals(3, found.getAutonomie());
		assertEquals(4, found.getVitesse());
		assertEquals(10, found.getMultiplicateurHyperdrive());

		assertNull(found.getHangar());
	}

	@Test
	public void testDelete() {
		VaisseauEntity toDelete = vaisseauDao.findByImmatriculation("DS-61-2 (Mauler Mithel)");
		assertNotNull(toDelete);

		vaisseauDao.delete(toDelete);

		VaisseauEntity deleted = vaisseauDao.findByImmatriculation("DS-61-2 (Mauler Mithel)");
		assertNull(deleted);
	}

	@Test
	public void testDelete_AvecPersonnelAttache() {
		VaisseauEntity toDelete = vaisseauDao.findByImmatriculation("Dark Vador's Tie Advanced");

		vaisseauDao.delete(toDelete);

		VaisseauEntity deleted = vaisseauDao.findByImmatriculation("Dark Vador's Tie Advanced");
		assertNull(deleted);
	}

	@Test
	public void testDelete_VaisseauIntergalactique() {
		VaisseauIntergalactiqueEntity toDelete = (VaisseauIntergalactiqueEntity) vaisseauDao
				.findByImmatriculation("Leader rouge");
		assertNotNull(toDelete);

		vaisseauDao.delete(toDelete);

		VaisseauEntity deleted = vaisseauDao.findByImmatriculation("Leader rouge");
		assertNull(deleted);
	}

	@Test
	public void testDelete_VaisseauIntergalactique_AvecPersonnelAttache() {
		VaisseauIntergalactiqueEntity toDelete = (VaisseauIntergalactiqueEntity) vaisseauDao
				.findByImmatriculation("Serenity");
		assertNotNull(toDelete);

		vaisseauDao.delete(toDelete);

		VaisseauEntity deleted = vaisseauDao.findByImmatriculation("Serenity");
		assertNull(deleted);
	}

	@Test
	public void testFindByImmatriculation() {
		VaisseauEntity found = vaisseauDao.findByImmatriculation("Dark Vador's Tie Advanced");
		assertNotNull(found);
		assertEquals(0, found.getCapaciteDeFret());
		assertEquals("Dark Vador's Tie Advanced", found.getImmatriculation());
		assertEquals("Tie Advanced", found.getModele());
		assertEquals(0, found.getNombreDePassagers());
		assertEquals(1000, found.getAutonomie());
		assertEquals(103, found.getVitesse());

		assertNotNull(found.getHangar());
		assertEquals(3L, found.getHangar().getNumero().longValue());
	}

	@Test
	public void testFindByImmatriculation_VaisseauIntergalactique() {
		VaisseauIntergalactiqueEntity found = (VaisseauIntergalactiqueEntity) vaisseauDao
				.findByImmatriculation("Faucon Millenium");
		assertNotNull(found);
		assertEquals(100000, found.getCapaciteDeFret());
		assertEquals("Faucon Millenium", found.getImmatriculation());
		assertEquals("cargo YT-1300", found.getModele());
		assertEquals(6, found.getNombreDePassagers());
		assertEquals(100000, found.getAutonomie());
		assertEquals(105, found.getVitesse());
		assertEquals(2, found.getMultiplicateurHyperdrive());

		assertNotNull(found.getHangar());
		assertEquals(1L, found.getHangar().getNumero().longValue());
	}

	@Test
	public void testFindByImmatriculation_Absent() {
		VaisseauEntity found = vaisseauDao.findByImmatriculation("inconnu");
		assertNull(found);
	}

	@Test
	public void testFindByHangarLocalisationOrderByImmatriculation() {
		List<VaisseauSummary> vaisseaux = vaisseauDao.findByHangarLocalisationOrderByImmatriculation("Aldera%");

		assertNotNull(vaisseaux);
		assertEquals(3, vaisseaux.size());

		assertEquals("Faucon Millenium", vaisseaux.get(0).getImmatriculationVaisseau());
		assertEquals("cargo YT-1300", vaisseaux.get(0).getModeleVaisseau());
		assertEquals("Alderaan", vaisseaux.get(0).getLocalisationHangar());

		assertEquals("Flying Bird", vaisseaux.get(1).getImmatriculationVaisseau());
		assertEquals("Serenity", vaisseaux.get(2).getImmatriculationVaisseau());
	}

	@Test
	public void testFindVaisseauIntergalactique() {
		List<VaisseauSummary> vaisseaux = vaisseauDao.findVaisseauxIntergalactique();

		assertNotNull(vaisseaux);
		assertEquals(3, vaisseaux.size());

		assertEquals("Faucon Millenium", vaisseaux.get(0).getImmatriculationVaisseau());
		assertEquals("cargo YT-1300", vaisseaux.get(0).getModeleVaisseau());
		assertEquals("Alderaan", vaisseaux.get(0).getLocalisationHangar());

		assertEquals("Leader rouge", vaisseaux.get(1).getImmatriculationVaisseau());
		assertEquals("Serenity", vaisseaux.get(2).getImmatriculationVaisseau());
	}

	@Test
	public void testFindByCriteria() {
		VaisseauSearchCriteria criteres = beanFactory.getBean(VaisseauSearchCriteria.class);
		criteres.setImmatriculation("Faucon Millenium");
		criteres.setIntergalactique(true);
		criteres.setModele("cargo YT-1300");

		List<VaisseauSummary> vaisseaux = vaisseauDao.findByCriteria(criteres);

		assertNotNull(vaisseaux);
		assertEquals(1, vaisseaux.size());

		assertEquals("Faucon Millenium", vaisseaux.get(0).getImmatriculationVaisseau());
		assertEquals("cargo YT-1300", vaisseaux.get(0).getModeleVaisseau());
		assertEquals("Alderaan", vaisseaux.get(0).getLocalisationHangar());
	}

	@Test
	public void testFindByCriteria_SansHangars() {
		VaisseauSearchCriteria criteres = beanFactory.getBean(VaisseauSearchCriteria.class);
		criteres.setImmatriculation("%");

		List<VaisseauSummary> vaisseaux = vaisseauDao.findByCriteria(criteres);

		assertNotNull(vaisseaux);
		assertEquals(6, vaisseaux.size());
	}

	@Test
	public void testCountByHangarLocalisation() {
		int nbVaisseaux = vaisseauDao.countByHangarLocalisation("Alderaan");
		assertEquals(3, nbVaisseaux);
	}

	@Test
	public void testCountByHangar() {
		HangarEntity hangar = hangarDao.findByNumero(1L);
		int nbVaisseaux = vaisseauDao.countByHangar(hangar);
		assertEquals(3, nbVaisseaux);
	}

	@Test
	public void testFindByHangarIsNullOrderByImmatriculation() {
		List<VaisseauSummary> vaisseaux = vaisseauDao.findByHangarIsNullOrderByImmatriculation();

		assertNotNull(vaisseaux);
		assertEquals(2, vaisseaux.size());

		assertEquals("DS-61-2 (Mauler Mithel)", vaisseaux.get(0).getImmatriculationVaisseau());
		assertEquals("Tie fighter", vaisseaux.get(0).getModeleVaisseau());
		assertNull(vaisseaux.get(0).getLocalisationHangar());

		assertEquals("Leader rouge", vaisseaux.get(1).getImmatriculationVaisseau());
	}
}
