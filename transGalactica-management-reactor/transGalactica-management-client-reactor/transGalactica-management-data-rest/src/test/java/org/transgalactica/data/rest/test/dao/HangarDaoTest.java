package org.transgalactica.data.rest.test.dao;

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
import org.transgalactica.data.rest.bo.HangarSearchCriteria;
import org.transgalactica.data.rest.bo.HangarSummaryTo;
import org.transgalactica.data.rest.bo.HangarTo;
import org.transgalactica.data.rest.bo.impl.BasicHangarSearchCriteria;
import org.transgalactica.data.rest.bo.impl.BasicHangarTo;
import org.transgalactica.data.rest.dao.HangarDao;
import org.transgalactica.test.AbstractContextTest;

@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class HangarDaoTest extends AbstractContextTest {

	@Inject
	@Named("mockClientHttpRequestFactory")
	private ClientHttpRequestFactory mock;

	@Inject
	private HangarDao hangarDao;

	@Before
	public void mockDao() {
		RestTemplate template = (RestTemplate) ReflectionTestUtils.getField(hangarDao, "restTemplate");
		template.setRequestFactory(mock);
	}

	@Test
	public void testSearchByCriteria() {
		HangarSearchCriteria criteres = BeanUtils.instantiateClass(BasicHangarSearchCriteria.class);
		criteres.setLocalisationHangar("A%");

		List<HangarSummaryTo> hangars = hangarDao.searchByCriteria(criteres);

		assertEquals(2, hangars.size());
		assertEquals(new Long(1), hangars.get(0).getNumero());
		assertEquals("Alderaan", hangars.get(0).getLocalisation());
		assertEquals(10, hangars.get(0).getNombreEmplacements());
	}

	@Test
	public void testGetByNumero() {
		HangarTo hangar = hangarDao.getByNumero(1L);

		assertNotNull(hangar);
		assertEquals(new Long(1), hangar.getNumero());
		assertEquals(10, hangar.getNombreEmplacements());
		assertEquals("Alderaan", hangar.getLocalisation());

		assertNotNull(hangar.getVaisseaux());
		assertEquals(3, hangar.getVaisseaux().size());

		assertEquals("Serenity", hangar.getVaisseaux().get(0).getImmatriculation());
		assertEquals(150000, hangar.getVaisseaux().get(0).getCapaciteDeFret());
		assertEquals("Firefly", hangar.getVaisseaux().get(0).getModele());
		assertEquals(8, hangar.getVaisseaux().get(0).getNombreDePassagers());
		assertEquals(150000, hangar.getVaisseaux().get(0).getAutonomie());
		assertEquals(95, hangar.getVaisseaux().get(0).getVitesse());
	}

	@Test
	public void testPersist_new() {
		HangarTo hangar = BeanUtils.instantiateClass(BasicHangarTo.class);
		hangar.setLocalisation("localisation");
		hangar.setNombreEmplacements(100);

		hangarDao.persist(hangar);
	}

	@Test
	public void testPersist_update() {
		HangarTo hangar = hangarDao.getByNumero(1L);
		hangar.setNombreEmplacements(12);

		hangarDao.persist(hangar);
	}

	@Test
	public void testRemove() {
		hangarDao.remove(1L);
	}

	@Test
	public void testAddVaisseau() {
		hangarDao.addVaisseau(1L, "Serenity");
	}

	@Test
	public void testRemoveVaisseau() {
		hangarDao.removeVaisseau(1L, "Serenity");
	}
}
