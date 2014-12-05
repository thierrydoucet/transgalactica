package org.transgalactica.management.business.hr.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import javax.validation.ValidationException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.transgalactica.fwk.test.util.SecurityContextTestUtils;
import org.transgalactica.management.business.hr.exception.EmployeInexistantException;
import org.transgalactica.management.business.hr.service.EmployeService;
import org.transgalactica.management.data.materiel.bo.VaisseauEntity;
import org.transgalactica.management.data.materiel.dao.VaisseauDao;
import org.transgalactica.management.data.people.bo.EmployeEntity;
import org.transgalactica.management.data.people.bo.EmployeSearchCriteria;
import org.transgalactica.management.data.people.bo.EmployeSummary;
import org.transgalactica.management.data.people.bo.MecanicienEntity;
import org.transgalactica.management.data.people.bo.impl.BasicEmployeSearchCriteria;
import org.transgalactica.test.AbstractTransactionalTest;

public class EmployeServiceTest extends AbstractTransactionalTest {

	@Autowired
	private EmployeService employeService;

	@Autowired
	private VaisseauDao vaisseauDao;

	@Before
	public void setSecurityRoleInContext() {
		SecurityContextTestUtils.setUsernamePasswordAuthenticationTokenInSecurityContext("superGestionnaire", "xxx",
				"ROLE_SUPER_GESTIONNAIRE");
	}

	@Test(expected = EmployeInexistantException.class)
	public void testChargerEmployeInexistant() {
		employeService.chargerEmploye(999999L);
	}

	@Test
	public void testChargerEmploye() {
		EmployeEntity employe = employeService.chargerEmploye(1L);
		assertEquals(1L, employe.getMatricule().longValue());
	}

	@Test
	public void testEnregistrerEmploye() {
		MecanicienEntity employe = applicationContext.getBean(MecanicienEntity.class);
		employe.setNom("Bob l'Eponge");
		employe.setDateEmbauche(new Date());
		employeService.enregistrerEmploye(employe);
		EmployeEntity employe2 = employeService.chargerEmploye(employe.getMatricule());
		assertEquals("Bob l'Eponge", employe2.getNom());
	}

	@Test(expected = ValidationException.class)
	public void testEnregistrerHangar_null() {
		employeService.enregistrerEmploye(null);
	}

	@Test(expected = ValidationException.class)
	public void testEnregistrerHangar_Invalide() {
		MecanicienEntity employe = applicationContext.getBean(MecanicienEntity.class);
		employe.setDateEmbauche(new Date());
		employeService.enregistrerEmploye(employe);
	}

	@Test
	public void testSupprimerEmploye() {
		EmployeEntity employe = employeService.chargerEmploye(1L);
		employeService.supprimerEmploye(employe);
	}

	@Test
	public void testRechercherHangarsParLocalisation() {
		EmployeSearchCriteria criteres = BeanUtils.instantiateClass(BasicEmployeSearchCriteria.class);
		criteres.setNomEmploye("%chew%");
		List<EmployeSummary> employes = employeService.rechercherEmployes(criteres);
		assertEquals(1, employes.size());
	}

	@Test
	public void testAffecterVaisseauAuHangar() {
		EmployeEntity employe = employeService.chargerEmploye(1L);
		VaisseauEntity vaisseau = vaisseauDao.findByImmatriculation("Dark Vador's Tie Advanced");

		employeService.affecterVaisseauAEmploye(vaisseau, employe);

		EmployeEntity employe2 = employeService.chargerEmploye(1L);
		assertTrue(employe2.getVaisseaux().contains(vaisseau));
	}
}
