package org.transgalactica.management.data.rest.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;
import org.transgalactica.management.data.rest.bo.VaisseauSearchCriteria;
import org.transgalactica.management.data.rest.bo.VaisseauSummaryTo;
import org.transgalactica.management.data.rest.bo.VaisseauTo;
import org.transgalactica.management.data.rest.bo.impl.BasicVaisseauSearchCriteria;
import org.transgalactica.management.data.rest.bo.impl.BasicVaisseauTo;
import org.transgalactica.management.data.rest.dao.VaisseauDao;
import org.transgalactica.test.AbstractContextTest;

@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class VaisseauDaoTest extends AbstractContextTest {

	@Inject
	@Named("mockClientHttpRequestFactory")
	private ClientHttpRequestFactory mock;

	@Inject
	private VaisseauDao vaisseauDao;

	@Before
	public void mockDao() {
		RestTemplate template = (RestTemplate) ReflectionTestUtils.getField(vaisseauDao, "restTemplate");
		template.setRequestFactory(mock);
	}

	@Test
	public void testSearchByCriteria() {
		VaisseauSearchCriteria criteres = BeanUtils.instantiateClass(BasicVaisseauSearchCriteria.class);
		criteres.setImmatriculation("%");
		criteres.setModele("%");
		criteres.setIntergalactique(true);
		List<VaisseauSummaryTo> vaisseaus = vaisseauDao.searchByCriteria(criteres);

		assertEquals(6, vaisseaus.size());
		assertEquals("N7242C", vaisseaus.get(0).getImmatriculation());
		assertEquals("Vipers Mark II", vaisseaus.get(0).getModele());
		assertEquals("Alderaan", vaisseaus.get(0).getLocalisationHangar());

	}

	@Test
	public void testGetByNumero() {
		VaisseauTo vaisseau = vaisseauDao.getByImmatriculation("Serenity");

		assertNotNull(vaisseau);
		assertEquals("Serenity", vaisseau.getImmatriculation());
		assertEquals(150000, vaisseau.getCapaciteDeFret());
		assertEquals("Firefly", vaisseau.getModele());
		assertEquals(8, vaisseau.getNombreDePassagers());
		assertEquals(150000, vaisseau.getAutonomie());
		assertEquals(95, vaisseau.getVitesse());
		assertEquals(new Short((short) 0), vaisseau.getMultiplicateurHyperdrive());
	}

	@Test
	public void testPersist_new() {
		VaisseauTo vaisseau = BeanUtils.instantiateClass(BasicVaisseauTo.class);
		vaisseau.setImmatriculation("immatriculation test");
		vaisseau.setModele("modele test");
		vaisseau.setCapaciteDeFret(10);
		vaisseau.setMultiplicateurHyperdrive(null);
		vaisseau.setNombreDePassagers((short) 100);
		vaisseau.setAutonomie(12);
		vaisseau.setVitesse(50);

		vaisseauDao.persist(vaisseau);
	}

	@Test
	public void testPersist_update() {
		VaisseauTo vaisseau = vaisseauDao.getByImmatriculation("Serenity");
		vaisseau.setCapaciteDeFret(200000);

		vaisseauDao.persist(vaisseau);
	}

	@Test
	public void testRemove() {
		vaisseauDao.remove("Serenity");
	}
}
