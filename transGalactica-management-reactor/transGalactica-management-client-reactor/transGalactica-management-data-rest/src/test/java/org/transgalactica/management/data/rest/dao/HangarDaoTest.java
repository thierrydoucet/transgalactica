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
import org.transgalactica.management.data.rest.bo.HangarSearchCriteria;
import org.transgalactica.management.data.rest.bo.HangarSummaryTo;
import org.transgalactica.management.data.rest.bo.HangarTo;
import org.transgalactica.management.data.rest.bo.impl.BasicHangarSearchCriteria;
import org.transgalactica.management.data.rest.bo.impl.BasicHangarTo;
import org.transgalactica.management.data.rest.dao.HangarDao;
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
		assertEquals(12500, hangar.getNombreEmplacements());
		assertEquals("Alderaan", hangar.getLocalisation());

		assertNotNull(hangar.getVaisseaux());
		assertEquals(5, hangar.getVaisseaux().size());

		assertEquals("Red Three", hangar.getVaisseaux().get(0).getImmatriculation());
		assertEquals(0, hangar.getVaisseaux().get(0).getCapaciteDeFret());
		assertEquals("T-65 X-Wings", hangar.getVaisseaux().get(0).getModele());
		assertEquals(0, hangar.getVaisseaux().get(0).getNombreDePassagers());
		assertEquals(7, hangar.getVaisseaux().get(0).getAutonomie());
		assertEquals(1050, hangar.getVaisseaux().get(0).getVitesse());
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
