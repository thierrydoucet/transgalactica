package org.transgalactica.swing.test.logistics.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.web.client.HttpStatusCodeException;
import org.transgalactica.management.data.rest.bo.HangarSearchCriteria;
import org.transgalactica.management.data.rest.bo.HangarSummaryTo;
import org.transgalactica.management.data.rest.bo.HangarTo;
import org.transgalactica.swing.logistics.service.HangarService;
import org.transgalactica.swing.test.AbstractMockedSpringContextTest;

public class HangarServiceTest extends AbstractMockedSpringContextTest {

	@Inject
	private HangarService hangarService;

	@Test
	public void testChargerHangarInexistant() {
		try {
			hangarService.chargerHangar(999999L);
		}
		catch (HttpStatusCodeException e) {
			assertEquals(404, e.getStatusCode().value());
		}
	}

	@Test
	public void testChargerHangar() {
		HangarTo hangar = hangarService.chargerHangar(1L);

		assertEquals(1L, hangar.getNumero().longValue());
	}

	@Test
	public void testRechercherHangars() {
		HangarSearchCriteria criteres = applicationContext.getBean(HangarSearchCriteria.class);
		criteres.setLocalisationHangar("A%");

		List<HangarSummaryTo> hangars = hangarService.rechercherHangars(criteres);

		assertEquals(2, hangars.size());
	}

	@Test
	public void testSupprimerHangar() {
		HangarTo hangar = hangarService.chargerHangar(1L);

		hangarService.supprimerHangar(hangar);
	}

	@Test
	public void testEnregistrerHangar() {
		HangarTo hangar = applicationContext.getBean(HangarTo.class);
		hangar.setLocalisation("localisationTest");
		hangar.setNombreEmplacements(1);
		hangarService.enregistrerHangar(hangar);
	}
}
