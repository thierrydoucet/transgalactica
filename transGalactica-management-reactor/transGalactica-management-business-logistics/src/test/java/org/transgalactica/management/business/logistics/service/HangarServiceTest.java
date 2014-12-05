package org.transgalactica.management.business.logistics.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import javax.validation.ValidationException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.transgalactica.fwk.test.util.SecurityContextTestUtils;
import org.transgalactica.fwk.validation.exception.BusinessException;
import org.transgalactica.management.business.logistics.exception.HangarInexistantException;
import org.transgalactica.management.business.logistics.service.HangarService;
import org.transgalactica.management.business.logistics.service.VaisseauService;
import org.transgalactica.management.data.materiel.bo.HangarEntity;
import org.transgalactica.management.data.materiel.bo.HangarSearchCriteria;
import org.transgalactica.management.data.materiel.bo.HangarSummary;
import org.transgalactica.management.data.materiel.bo.VaisseauEntity;
import org.transgalactica.management.data.materiel.bo.impl.BasicHangarSearchCriteria;
import org.transgalactica.test.AbstractTransactionalTest;

public class HangarServiceTest extends AbstractTransactionalTest {

	@Autowired
	@Qualifier("daoHangarService")
	private HangarService hangarService;

	@Autowired
	@Qualifier("daoVaisseauService")
	private VaisseauService vaisseauService;

	@Before
	public void setSecurityRoleInContext() {
		SecurityContextTestUtils.setUsernamePasswordAuthenticationTokenInSecurityContext("superGestionnaire", "xxx",
				"ROLE_SUPER_GESTIONNAIRE");
	}

	@Test(expected = HangarInexistantException.class)
	public void testChargerHangarInexistant() {
		hangarService.chargerHangar(999999L);
	}

	@Test
	public void testChargerHangar() {
		HangarEntity hangar = hangarService.chargerHangar(1L);
		assertEquals(1L, hangar.getNumero().longValue());
	}

	@Test
	public void testRechercherHangars() {
		List<HangarSummary> hangars = hangarService.rechercherHangars();
		assertEquals(3, hangars.size());
	}

	@Test
	public void testRechercherHangarsParLocalisation() {
		HangarSearchCriteria criteres = BeanUtils.instantiateClass(BasicHangarSearchCriteria.class);
		criteres.setLocalisationHangar("Arakis");
		List<HangarSummary> hangars = hangarService.rechercherHangars(criteres);
		assertEquals(1, hangars.size());
	}

	@Test(expected = ValidationException.class)
	public void testRechercherHangarsParLocalisation_invalide() {
		HangarSearchCriteria criteres = BeanUtils.instantiateClass(BasicHangarSearchCriteria.class);
		criteres.setLocalisationHangar("");
		hangarService.rechercherHangars(criteres);
	}

	@Test(expected = ValidationException.class)
	public void testRechercherHangarsParLocalisation_null() {
		hangarService.rechercherHangars(null);
	}

	@Test
	public void testSupprimerHangarVide() {
		HangarEntity hangar = hangarService.chargerHangar(2L);
		hangarService.supprimerHangar(hangar);
		try {
			hangarService.chargerHangar(2L);
		}
		catch (HangarInexistantException e) {
			return;
		}
		fail();
	}

	@Test
	public void testSupprimerHangarContenantDesVaisseaux() {
		HangarEntity hangar = hangarService.chargerHangar(1L);
		try {
			hangarService.supprimerHangar(hangar);
		}
		catch (BusinessException e) {
			assertEquals("HANGAR_VALIDATION_2", e.getMessage());
		}
	}

	@Test
	public void testEnregistrerHangar() {
		HangarEntity hangar = applicationContext.getBean(HangarEntity.class);
		hangar.setLocalisation("localisationTest");
		hangar.setNombreEmplacements(1);
		hangarService.enregistrerHangar(hangar);
		HangarEntity hangar2 = hangarService.chargerHangar(hangar.getNumero());
		assertEquals("localisationTest", hangar2.getLocalisation());
	}

	@Test
	public void testAffecterVaisseauAuHangar() {
		HangarEntity hangar = hangarService.chargerHangar(1L);
		VaisseauEntity vaisseau = vaisseauService.chargerVaisseau("Dark Vador's Tie Advanced");
		hangarService.affecterVaisseauAuHangar(vaisseau, hangar);
		HangarEntity hangar2 = hangarService.chargerHangar(1L);
		assertTrue(hangar2.getVaisseaux().contains(vaisseau));
	}

	@Test
	public void testAffecterVaisseauAuHangarPlein() {
		HangarEntity hangar = hangarService.chargerHangar(1L);
		hangar.setNombreEmplacements(hangar.getVaisseaux().size());
		VaisseauEntity vaisseau = vaisseauService.chargerVaisseau("DS-61-2 (Mauler Mithel)");
		try {
			hangarService.affecterVaisseauAuHangar(vaisseau, hangar);
		}
		catch (BusinessException e) {
			assertEquals("HANGAR_VALIDATION_4", e.getMessage());
		}
	}
}
