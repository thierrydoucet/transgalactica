package org.transgalactica.management.data.people.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.transgalactica.management.data.materiel.bo.VaisseauEntity;
import org.transgalactica.management.data.materiel.dao.VaisseauDao;
import org.transgalactica.management.data.people.TestConfig;
import org.transgalactica.management.data.people.bo.EmployeEntity;
import org.transgalactica.management.data.people.bo.EmployeSearchCriteria;
import org.transgalactica.management.data.people.bo.EmployeSummary;
import org.transgalactica.management.data.people.bo.MecanicienEntity;
import org.transgalactica.management.data.people.bo.PiloteEntity;
import org.transgalactica.management.data.people.bo.impl.JpaPiloteEntity;
import org.transgalactica.management.data.referentiel.bo.EmployeType;
import org.transgalactica.management.data.referentiel.bo.MecanicienSpecialiteEntity;
import org.transgalactica.management.data.referentiel.dao.MecanicienSpecialiteDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@TransactionConfiguration
@Transactional
public class EmployeDaoTest {

	@Autowired
	private BeanFactory beanFactory;

	@Autowired
	private EmployeDao employeDao;

	@Autowired
	private MecanicienSpecialiteDao mecanicienSpecialiteDao;

	@Autowired
	private VaisseauDao vaisseauDao;

	private TimeZone defaultTimeZone;

	@Before
	public void setTimeZone() {
		defaultTimeZone = TimeZone.getDefault();
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+2"));
	}

	@After
	public void setRestoreTimeZone() {
		TimeZone.setDefault(defaultTimeZone);
	}

	@Test
	public void testPersist_SavePilote() {
		PiloteEntity toSave = BeanUtils.instantiateClass(JpaPiloteEntity.class);
		toSave.setNom("Chewbaca");
		toSave.setDateEmbauche(new GregorianCalendar(2000, 0, 1).getTime());
		toSave.setNombreHeuresVol(78);
		VaisseauEntity vaisseau = vaisseauDao.findByImmatriculation("Faucon Millenium");
		toSave.addVaisseau(vaisseau);
		assertNull(toSave.getMatricule());

		employeDao.save(toSave);

		assertNotNull(toSave.getMatricule());
		PiloteEntity found = (PiloteEntity) employeDao.findByMatricule(toSave.getMatricule());
		assertNotNull(found);
		assertEquals(EmployeType.PILOTE, found.getType());
		assertEquals(new GregorianCalendar(2000, 0, 1).getTime(), found.getDateEmbauche());
		assertEquals("Chewbaca", found.getNom());
		assertEquals(78, found.getNombreHeuresVol());
		assertNotNull(found.getVaisseaux());
		assertEquals(1, found.getVaisseaux().size());
	}

	@Test
	public void testPersist_SaveMecanicien() {
		MecanicienEntity toSave = beanFactory.getBean(MecanicienEntity.class);
		toSave.setNom("Toto");
		toSave.setDateEmbauche(new GregorianCalendar(2000, 0, 1).getTime());
		MecanicienSpecialiteEntity specialite = mecanicienSpecialiteDao.findByNomSpecialite("Hyperpropulsion");
		toSave.getSpecialites().add(specialite);
		VaisseauEntity vaisseau = vaisseauDao.findByImmatriculation("Faucon Millenium");
		toSave.addVaisseau(vaisseau);
		assertNull(toSave.getMatricule());

		employeDao.save(toSave);

		assertNotNull(toSave.getMatricule());
		MecanicienEntity found = (MecanicienEntity) employeDao.findByMatricule(toSave.getMatricule());
		assertNotNull(found);
		assertEquals(EmployeType.MECANICIEN, found.getType());
		assertEquals(new GregorianCalendar(2000, 0, 1).getTime(), found.getDateEmbauche());
		assertEquals("Toto", found.getNom());
		assertEquals("Hyperpropulsion", found.getSpecialites().iterator().next().getNomSpecialite());
		assertNotNull(found.getVaisseaux());
		assertEquals(1, found.getVaisseaux().size());
	}

	@Test
	public void testPersist_UpdatePilote() {
		PiloteEntity toUpdate = (PiloteEntity) employeDao.findByMatricule(1L);
		assertNotNull(toUpdate);
		toUpdate.setDateEmbauche(new GregorianCalendar(1999, 11, 31).getTime());
		toUpdate.setNom("Yan");
		toUpdate.setNombreHeuresVol(88);
		toUpdate.getVaisseaux().clear();

		employeDao.save(toUpdate);

		PiloteEntity found = (PiloteEntity) employeDao.findByMatricule(1L);

		assertNotNull(found);
		assertEquals(EmployeType.PILOTE, found.getType());
		assertEquals(new GregorianCalendar(1999, 11, 31).getTime(), found.getDateEmbauche());
		assertEquals("Yan", found.getNom());
		assertEquals(88, found.getNombreHeuresVol());
		assertEquals(0, found.getVaisseaux().size());
	}

	@Test
	public void testPersist_UpdateMecanicien() {
		MecanicienEntity toUpdate = (MecanicienEntity) employeDao.findByMatricule(2L);
		assertNotNull(toUpdate);
		toUpdate.setDateEmbauche(new GregorianCalendar(1999, 11, 31).getTime());
		toUpdate.setNom("Yan Solo");
		toUpdate.getSpecialites().clear();
		toUpdate.getVaisseaux().clear();

		employeDao.save(toUpdate);

		MecanicienEntity found = (MecanicienEntity) employeDao.findByMatricule(2L);

		assertNotNull(found);
		assertEquals(EmployeType.MECANICIEN, found.getType());
		assertEquals(new GregorianCalendar(1999, 11, 31).getTime(), found.getDateEmbauche());
		assertEquals("Yan Solo", found.getNom());
		assertTrue(found.getSpecialites().isEmpty());
		assertEquals(0, found.getVaisseaux().size());
	}

	@Test
	public void testRemove_Pilote() {
		PiloteEntity toDelete = (PiloteEntity) employeDao.findByMatricule(3L);
		assertNotNull(toDelete);

		employeDao.delete(toDelete);

		PiloteEntity deleted = (PiloteEntity) employeDao.findByMatricule(3L);
		assertNull(deleted);
	}

	@Test
	public void testRemove_Mecanicien() {
		MecanicienEntity toDelete = (MecanicienEntity) employeDao.findByMatricule(7L);
		assertNotNull(toDelete);

		employeDao.delete(toDelete);

		MecanicienEntity deleted = (MecanicienEntity) employeDao.findByMatricule(7L);
		assertNull(deleted);
	}

	@Test
	public void testRemove_EmployeAffecteAVaisseau() {
		EmployeEntity toDelete = employeDao.findByMatricule(1L);
		assertNotNull(toDelete);

		employeDao.delete(toDelete);

		EmployeEntity deleted = employeDao.findByMatricule(1L);
		assertNull(deleted);
	}

	@Test
	public void testFindByMatricule_Pilote() {
		PiloteEntity found = (PiloteEntity) employeDao.findByMatricule(1L);
		assertNotNull(found);
		assertEquals(EmployeType.PILOTE, found.getType());
		assertEquals("Han Solo", found.getNom());
		assertEquals(542, found.getNombreHeuresVol());
		assertEquals(new GregorianCalendar(1977, 5, 9).getTime(), found.getDateEmbauche());
		assertNotNull(found.getVaisseaux());
		assertEquals(1, found.getVaisseaux().size());
		assertEquals("Faucon Millenium", found.getVaisseaux().iterator().next().getImmatriculation());
	}

	@Test
	public void testFindByMatricule_Mecanicien() {
		MecanicienEntity found = (MecanicienEntity) employeDao.findByMatricule(2L);
		assertNotNull(found);
		assertEquals(EmployeType.MECANICIEN, found.getType());
		assertEquals("Chewbacca", found.getNom());
		assertEquals(new GregorianCalendar(1977, 5, 9).getTime(), found.getDateEmbauche());
		assertEquals("Armement", found.getSpecialites().iterator().next().getNomSpecialite());
		assertNotNull(found.getVaisseaux());
		assertEquals(1, found.getVaisseaux().size());
		assertEquals("Faucon Millenium", found.getVaisseaux().iterator().next().getImmatriculation());
	}

	@Test
	public void testFindByMatricule_Absent() {
		EmployeEntity found = employeDao.findByMatricule(99L);
		assertNull(found);
	}

	@Test
	public void testFindEmployesByCriteria() {
		EmployeSearchCriteria critereRechercheEmploye = beanFactory.getBean(EmployeSearchCriteria.class);
		critereRechercheEmploye.setNomEmploye("%chew%");
		critereRechercheEmploye.setDateEmbaucheEmployeDebut(new GregorianCalendar(1977, 0, 1).getTime());
		critereRechercheEmploye.setDateEmbaucheEmployeFin(new GregorianCalendar(1977, 11, 31).getTime());
		critereRechercheEmploye.setImmatriculationVaisseau("Faucon Millenium");

		List<EmployeSummary> pilotes = employeDao.findEmployesByCriteria(critereRechercheEmploye);

		assertNotNull(pilotes);
		assertEquals(1, pilotes.size());
		assertEquals(2, pilotes.get(0).getMatriculeEmploye().intValue());
		assertEquals("Chewbacca", pilotes.get(0).getNomEmploye());
		assertEquals(new GregorianCalendar(1977, 5, 9).getTime(), pilotes.get(0).getDateEmbaucheEmploye());
		assertEquals(EmployeType.MECANICIEN, pilotes.get(0).getTypeEmploye());
	}

	@Test
	public void testFindEmployesByCriteria_immatriculation() {
		EmployeSearchCriteria critereRechercheEmploye = beanFactory.getBean(EmployeSearchCriteria.class);
		critereRechercheEmploye.setImmatriculationVaisseau("Serenity");

		List<EmployeSummary> employes = employeDao.findEmployesByCriteria(critereRechercheEmploye);

		assertNotNull(employes);
		assertEquals(2, employes.size());

		EmployeSummary wash = employes.get(0);
		assertEquals(4, wash.getMatriculeEmploye().intValue());
		assertEquals("Hoban Washburne (Wash)", wash.getNomEmploye());
		assertEquals(1032472800000L, wash.getDateEmbaucheEmploye().getTime());
		assertEquals(EmployeType.PILOTE, wash.getTypeEmploye());

		assertEquals(5, employes.get(1).getMatriculeEmploye().longValue());
	}

	@Test
	public void testFindEmployesByModeleDeVaisseau() {
		List<EmployeSummary> employes = employeDao.findEmployesByModeleDeVaisseau("Firefly");

		assertNotNull(employes);
		assertEquals(2, employes.size());

		EmployeSummary wash = employes.get(0);
		assertEquals(4, wash.getMatriculeEmploye().intValue());
		assertEquals("Hoban Washburne (Wash)", wash.getNomEmploye());
		assertEquals(1032472800000L, wash.getDateEmbaucheEmploye().getTime());
		assertEquals(EmployeType.PILOTE, wash.getTypeEmploye());

		assertEquals(5, employes.get(1).getMatriculeEmploye().longValue());
	}

	@Test
	public void testFindEmployesOfVaisseauIntergalactique() {
		List<EmployeSummary> employes = employeDao.findEmployesOfVaisseauIntergalactique();

		assertNotNull(employes);
		assertEquals(4, employes.size());

		EmployeSummary hanSolo = employes.get(0);
		assertEquals(1, hanSolo.getMatriculeEmploye().intValue());
		assertEquals("Han Solo", hanSolo.getNomEmploye());
		assertEquals(234655200000L, hanSolo.getDateEmbaucheEmploye().getTime());
		assertEquals(EmployeType.PILOTE, hanSolo.getTypeEmploye());

		assertEquals(2, employes.get(1).getMatriculeEmploye().intValue());
		assertEquals(4, employes.get(2).getMatriculeEmploye().intValue());
		assertEquals(5, employes.get(3).getMatriculeEmploye().intValue());
	}
}
