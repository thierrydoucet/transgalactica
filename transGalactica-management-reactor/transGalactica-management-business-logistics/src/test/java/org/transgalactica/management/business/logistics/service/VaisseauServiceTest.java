package org.transgalactica.management.business.logistics.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.transgalactica.fwk.test.util.SecurityContextTestUtils;
import org.transgalactica.management.business.logistics.exception.VaisseauInexistantException;
import org.transgalactica.management.business.logistics.service.VaisseauService;
import org.transgalactica.management.data.materiel.bo.VaisseauEntity;
import org.transgalactica.management.data.materiel.bo.VaisseauSearchCriteria;
import org.transgalactica.management.data.materiel.bo.VaisseauSummary;
import org.transgalactica.management.data.materiel.bo.impl.BasicVaisseauSearchCriteria;
import org.transgalactica.test.AbstractTransactionalTest;

public class VaisseauServiceTest extends AbstractTransactionalTest {

	@Autowired
	@Qualifier("daoVaisseauService")
	private VaisseauService vaisseauService;

	@Before
	public void setSecurityRoleInContext() {
		SecurityContextTestUtils.setUsernamePasswordAuthenticationTokenInSecurityContext("superGestionnaire", "xxx",
				"ROLE_SUPER_GESTIONNAIRE");
	}

	@Test(expected = VaisseauInexistantException.class)
	public void testChargerVaisseauInexistant() {
		vaisseauService.chargerVaisseau("999999");
	}

	@Test
	public void testChargerVaisseau() {
		VaisseauEntity vaisseau = vaisseauService.chargerVaisseau("Faucon Millenium");
		assertEquals("Faucon Millenium", vaisseau.getImmatriculation());
	}

	@Test
	public void testRechercherVaisseaux() {
		VaisseauSearchCriteria criteres = applicationContext.getBean(BasicVaisseauSearchCriteria.class);
		criteres.setModele("cargo YT-1300");
		List<VaisseauSummary> vaisseaux = vaisseauService.rechercherVaisseaux(criteres);
		assertEquals(2, vaisseaux.size());
	}

	@Test
	public void testSupprimerVaisseau() {
		VaisseauEntity vaisseau = vaisseauService.chargerVaisseau("Leader rouge");
		assertNotNull(vaisseau);

		vaisseauService.supprimerVaisseau(vaisseau);
	}

	@Test
	public void testEnregistrerVaisseau() {
		VaisseauEntity vaisseau = applicationContext.getBean(VaisseauEntity.class.getName(), VaisseauEntity.class);
		vaisseau.setImmatriculation("immatriculation");
		vaisseau.setModele("modele");
		vaisseauService.enregistrerVaisseau(vaisseau);
		VaisseauEntity vaisseau2 = vaisseauService.chargerVaisseau(vaisseau.getImmatriculation());
		assertEquals("immatriculation", vaisseau2.getImmatriculation());
		assertEquals("modele", vaisseau2.getModele());
	}
}
