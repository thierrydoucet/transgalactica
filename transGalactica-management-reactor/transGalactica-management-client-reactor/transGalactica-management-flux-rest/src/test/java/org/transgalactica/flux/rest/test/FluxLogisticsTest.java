package org.transgalactica.flux.rest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.URISyntaxException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.transgalactica.flux.rest.HangarDetailDto;
import org.transgalactica.flux.rest.HangarDtos;
import org.transgalactica.flux.rest.VaisseauDetailDto;
import org.transgalactica.flux.rest.VaisseauDtos;
import org.transgalactica.test.AbstractContextTest;

public class FluxLogisticsTest extends AbstractContextTest {

	@Autowired
	private RestTemplate restTemplate;

	@Test
	public void testAppelSearchHangar_xml() throws URISyntaxException {
		HangarDtos result = restTemplate.getForObject(
				"http://localhost:8080/transGalactica-rest/hangars/search.xml?localisation=%", HangarDtos.class);
		assertNotNull(result);
		assertEquals(3, result.getHangars().size());

		assertEquals(new Long(1), result.getHangars().get(0).getNumero());
		assertEquals("Alderaan", result.getHangars().get(0).getLocalisation());
		assertEquals(10, result.getHangars().get(0).getNombreEmplacements());
	}

	@Test
	public void testAppelSearchHangar_json() throws URISyntaxException {
		HangarDtos result = restTemplate.getForObject(
				"http://localhost:8080/transGalactica-rest/hangars/search.json?localisation=%", HangarDtos.class);
		assertNotNull(result);
		assertEquals(3, result.getHangars().size());

		assertEquals(new Long(1), result.getHangars().get(0).getNumero());
		assertEquals("Alderaan", result.getHangars().get(0).getLocalisation());
		assertEquals(10, result.getHangars().get(0).getNombreEmplacements());
	}

	@Test
	public void testAppelHangarDetail_xml() throws URISyntaxException {
		HangarDetailDto result = restTemplate.getForObject(
				"http://localhost:8080/transGalactica-rest/hangars/{localisation}.xml", HangarDetailDto.class,
				"Alderaan");
		assertNotNull(result);
		assertEquals(new Long(1), result.getNumero());
		assertEquals(10, result.getNombreEmplacements());
		assertEquals("Alderaan", result.getLocalisation());
		assertNotNull(result.getVaisseaux());
		assertEquals(3, result.getVaisseaux().size());

		assertEquals("Serenity", result.getVaisseaux().get(0).getImmatriculation());
		assertEquals(150000, result.getVaisseaux().get(0).getCapaciteDeFret());
		assertEquals("Firefly", result.getVaisseaux().get(0).getModele());
		assertEquals(8, result.getVaisseaux().get(0).getNombreDePassagers());
		assertEquals(150000, result.getVaisseaux().get(0).getAutonomie());
		assertEquals(95, result.getVaisseaux().get(0).getVitesse());
	}

	@Test
	public void testAppelHangarDetail_json() throws URISyntaxException {
		HangarDetailDto result = restTemplate.getForObject(
				"http://localhost:8080/transGalactica-rest/hangars/{localisation}.json", HangarDetailDto.class,
				"Alderaan");
		assertNotNull(result);
		assertEquals(new Long(1), result.getNumero());
		assertEquals(10, result.getNombreEmplacements());
		assertEquals("Alderaan", result.getLocalisation());
		assertNotNull(result.getVaisseaux());
		assertEquals(3, result.getVaisseaux().size());

		assertEquals("Serenity", result.getVaisseaux().get(0).getImmatriculation());
		assertEquals(150000, result.getVaisseaux().get(0).getCapaciteDeFret());
		assertEquals("Firefly", result.getVaisseaux().get(0).getModele());
		assertEquals(8, result.getVaisseaux().get(0).getNombreDePassagers());
		assertEquals(150000, result.getVaisseaux().get(0).getAutonomie());
		assertEquals(95, result.getVaisseaux().get(0).getVitesse());
	}

	@Test
	public void testAppelSearchVaisseau_json() throws URISyntaxException {
		VaisseauDtos result = restTemplate
				.getForObject(
						"http://localhost:8080/transGalactica-rest/vaisseaux/search.json?immatriculation=%&modele=%&intergalactique=false",
						VaisseauDtos.class);
		assertNotNull(result);
		assertEquals(6, result.getVaisseaux().size());

		assertEquals("DS-61-2 (Mauler Mithel)", result.getVaisseaux().get(0).getImmatriculation());
		assertEquals("Tie fighter", result.getVaisseaux().get(0).getModele());
		assertEquals(null, result.getVaisseaux().get(0).getLocalisationHangar());
	}

	@Test
	public void testAppelVaisseauDetail_xml() throws URISyntaxException {
		VaisseauDetailDto result = restTemplate.getForObject(
				"http://localhost:8080/transGalactica-rest/vaisseaux/{vaisseau}.xml", VaisseauDetailDto.class,
				"Serenity");
		assertNotNull(result);
		assertEquals("Serenity", result.getImmatriculation());
		assertEquals("Firefly", result.getModele());

		assertEquals(95, result.getVitesse());
		assertEquals(150000, result.getCapaciteDeFret());
		assertEquals(8, result.getNombreDePassagers());
		assertEquals(150000, result.getAutonomie());
		assertEquals(new Short((short) 0), result.getMultiplicateurHyperdrive());

		assertEquals(new Long(1), result.getNumeroHangar());
		assertEquals("Alderaan", result.getLocalisationHangar());
	}
}
