package org.transgalactica.swing.test.logistics.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.HttpStatusCodeException;
import org.transgalactica.management.data.rest.bo.VaisseauSearchCriteria;
import org.transgalactica.management.data.rest.bo.VaisseauSummaryTo;
import org.transgalactica.management.data.rest.bo.VaisseauTo;
import org.transgalactica.management.data.rest.bo.impl.BasicVaisseauSearchCriteria;
import org.transgalactica.swing.logistics.service.VaisseauService;
import org.transgalactica.swing.test.AbstractMockedSpringContextTest;

public class VaisseauServiceTest extends AbstractMockedSpringContextTest {

	@Autowired
	private VaisseauService serviceVaisseau;

	@Test
	public void testChargerVaisseauInexistant() {
		try {
			serviceVaisseau.chargerVaisseau("immatriculation test");
		}
		catch (HttpStatusCodeException e) {
			assertEquals(404, e.getStatusCode().value());
		}
	}

	@Test
	public void testChargerVaisseau() {
		VaisseauTo vaisseau = serviceVaisseau.chargerVaisseau("Serenity");
		assertEquals("Serenity", vaisseau.getImmatriculation());
	}

	@Test
	public void testRechercherVaisseaux() {
		VaisseauSearchCriteria criteres = applicationContext.getBean(BasicVaisseauSearchCriteria.class);
		criteres.setImmatriculation("%");
		criteres.setModele("%");
		criteres.setIntergalactique(true);
		List<VaisseauSummaryTo> vaisseaux = serviceVaisseau.rechercherVaisseaux(criteres);
		assertEquals(3, vaisseaux.size());
	}

	@Test
	public void testSupprimerVaisseau() {
		VaisseauTo vaisseau = serviceVaisseau.chargerVaisseau("Serenity");
		serviceVaisseau.supprimerVaisseau(vaisseau);
	}

	@Test
	public void testEnregistrerVaisseau() {
		VaisseauTo vaisseau = applicationContext.getBean(VaisseauTo.class);
		vaisseau.setImmatriculation("immatriculation test");
		vaisseau.setModele("modele");

		serviceVaisseau.enregistrerVaisseau(vaisseau);
	}
}
