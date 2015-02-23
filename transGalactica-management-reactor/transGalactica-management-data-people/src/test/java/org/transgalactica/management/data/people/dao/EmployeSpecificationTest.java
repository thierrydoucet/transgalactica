package org.transgalactica.management.data.people.dao;

import static org.junit.Assert.assertEquals;
import static org.transgalactica.management.data.people.dao.EmployeSpecification.affecteA;
import static org.transgalactica.management.data.people.dao.EmployeSpecification.estEmbaucheApresLe;
import static org.transgalactica.management.data.people.dao.EmployeSpecification.estMecanicien;
import static org.transgalactica.management.data.people.dao.EmployeSpecification.nomContient;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.transgalactica.management.data.materiel.bo.VaisseauEntity;
import org.transgalactica.management.data.materiel.dao.VaisseauDao;
import org.transgalactica.management.data.people.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@TransactionConfiguration
@Transactional(readOnly = true)
public class EmployeSpecificationTest {

	@Autowired
	private EmployeDao employeDao;

	@Autowired
	private VaisseauDao vaisseauDao;

	@Test
	public void testEstMecanicien() {
		assertEquals(3, employeDao.findAll(estMecanicien()).size());
	}

	@Test
	public void testNomContient() {
		assertEquals(1, employeDao.findAll(nomContient("Frye")).size());
	}

	@Test
	public void testEstEmbaucheApresLe() {
		assertEquals(2, employeDao.findAll(estEmbaucheApresLe(LocalDate.of(2000, 2, 1))).size());
	}

	@Test
	public void testAffecteA() {
		VaisseauEntity v = vaisseauDao.findByImmatriculation("Serenity");
		assertEquals(2, employeDao.findAll(affecteA(v)).size());
	}
}
