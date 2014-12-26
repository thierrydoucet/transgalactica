package org.transgalactica.management.flux.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.URISyntaxException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.transgalactica.management.flux.rest.HangarDetailDto;
import org.transgalactica.management.flux.rest.HangarDtos;
import org.transgalactica.management.flux.rest.VaisseauDetailDto;
import org.transgalactica.test.AbstractContextTest;

public class FluxLogisticsTest extends AbstractContextTest {

	@Autowired
	private RestTemplate restTemplate;

	@Test
	public void testAppelSearchHangar_xml() throws URISyntaxException {
		HangarDtos result = restTemplate.getForObject(
				"http://localhost:8080/transGalactica-rest/hangars/search.xml?localisation=%", HangarDtos.class);
		assertNotNull(result);
		assertEquals(3, result.getHangar().size());

		assertEquals(1L, result.getHangar().get(0).getNumero());
		assertEquals("Alderaan", result.getHangar().get(0).getLocalisation());
		assertEquals(12500, result.getHangar().get(0).getNombreEmplacements());
	}

	@Test
	public void testAppelHangarDetail_xml() throws URISyntaxException {
		HangarDetailDto result = restTemplate.getForObject(
				"http://localhost:8080/transGalactica-rest/hangars/{localisation}.xml", HangarDetailDto.class,
				"Alderaan");
		assertNotNull(result);
		assertEquals(1L, result.getNumero());
		assertEquals(12500, result.getNombreEmplacements());
		assertEquals("Alderaan", result.getLocalisation());
		assertNotNull(result.getVaisseaux());
		assertNotNull(result.getVaisseaux().getVaisseau());
		assertEquals(5, result.getVaisseaux().getVaisseau().size());

		assertEquals("Red Three", result.getVaisseaux().getVaisseau().get(0).getImmatriculation());
		assertEquals(0, result.getVaisseaux().getVaisseau().get(0).getCapaciteDeFret());
		assertEquals("T-65 X-Wings", result.getVaisseaux().getVaisseau().get(0).getModele());
		assertEquals(0, result.getVaisseaux().getVaisseau().get(0).getNombreDePassagers());
		assertEquals(7, result.getVaisseaux().getVaisseau().get(0).getAutonomie());
		assertEquals(1050, result.getVaisseaux().getVaisseau().get(0).getVitesse());
	}

	@Test
	public void testAppelVaisseauDetail_xml() throws URISyntaxException {
		VaisseauDetailDto result = restTemplate.getForObject(
				"http://localhost:8080/transGalactica-rest/vaisseaux/{vaisseau}.xml", VaisseauDetailDto.class,
				"Serenity");
		assertNotNull(result);
		assertEquals("Serenity", result.getImmatriculation());
		assertEquals("Firefly", result.getModele());

		assertEquals(950, result.getVitesse());
		assertEquals(20000, result.getCapaciteDeFret());
		assertEquals(8, result.getNombreDePassagers());
		assertEquals(365, result.getAutonomie());
		assertEquals((short) 2, result.getMultiplicateurHyperdrive());

		assertEquals(10L, result.getNumeroHangar());
		assertEquals("Tatooine", result.getLocalisationHangar());
	}
}
